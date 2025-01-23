package com.almatec.controlpiso.integrapps.services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.erp.webservices.services.ConsumosTepService;
import com.almatec.controlpiso.erp.webservices.services.EntregaService;
import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.exceptions.ServiceException;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.Item;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt;
import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt.Estado;
import com.almatec.controlpiso.integrapps.repositories.ReportePiezaCtRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ItemService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;
import com.almatec.controlpiso.integrapps.services.VistaOrdenPvService;
import com.almatec.controlpiso.produccion.dtos.ReportePiezaCtDTO;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ReportePiezaCtServiceImpl implements ReportePiezaCtService {

	private final ReportePiezaCtRepository reporteRepository;
	private final ItemOpService itemOpService;
	private final XmlService xmlService;
	private final ConsumosTepService consumosTepService;
	private final SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	private final UtilitiesApp util;
	private final ConfigurationService configService;
	private final EntregaService entregaService;
	private final ItemService itemService;
	private final VistaOrdenPvService ordenPvService;

	static final String RESPUESTA_EXITOSA = "Operacion realizada exitosamente";
	private static final String FORMATO_FECHA = "yyyyMMdd_HHmmss";
	private static final String RESPUESTA_ENTREGA_EXITOSA = "Consumo y TEP creado exitosamente. Entrega creada Exitosamente. ";

	private static final List<Integer> CENTROS_CONSUMO_PRINCIPAL = List.of(3, 4, 5, 6, 7, 8, 9);
	private static final List<Integer> CENTROS_CONSUMO_PLATINAS = List.of(10, 11);
	private static final int CENTRO_FINAL = 17;
	private static final int CENTRO_PUNZONADORA = 2;

	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Procesa y guarda un reporte de producción, gestionando TEP y consumos según
	 * el centro de trabajo.
	 */
	@Transactional
	@Override
	public ResponseMessage guardarReporte(ReporteDTO reporteDTO) throws ServiceException {
		ResponseMessage response = new ResponseMessage();
		ReportePiezaCt reporte = crearEntidadReporte(reporteDTO);
		reporte.setEstado(Estado.PENDIENTE);

		try {
			ItemOp itemOp = itemOpService.obtenerItemPorId(reporte.getItemId());
			Integer idItemReportar = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
			Item itemReporte = itemService.buscarItemFabrica(idItemReportar);
			log.info("Procesando reporte de {} en el centro de trabajo {} {} und(s).", reporteDTO.getRef(),
					reporteDTO.getIdCentroTrabajo(), reporteDTO.getCantReportar());
			
			reporte.setEstado(Estado.PROCESANDO);
	        reporteRepository.save(reporte);
			procesarReporteSegunCentroTrabajo(reporte, reporteDTO, itemOp, itemReporte, response);
			reporte.setEstado(Estado.COMPLETO);
			reporteRepository.save(reporte);
		} catch (DataIntegrityViolationException e) {
			reporte.setEstado(Estado.ERROR);
	        reporte.setMensajeError("Error de integridad: " + e.getMessage());
	        reporte.setUltimoIntento(LocalDateTime.now());
	        reporteRepository.save(reporte);
			manejarErrorIntegridad(response, e);
		} catch (Exception e) {
			reporte.setEstado(Estado.ERROR);
	        reporte.setMensajeError("Error de integridad: " + e.getMessage());
	        reporte.setUltimoIntento(LocalDateTime.now());
	        reporteRepository.save(reporte);
			manejarErrorGeneral(response, e);
		}
		return response;
	}

	private void procesarReporteSegunCentroTrabajo(ReportePiezaCt reporte, ReporteDTO reporteDTO, ItemOp itemOperacion,
			Item itemReporte, ResponseMessage response) throws ServiceException, IOException{
		StringBuilder mensajeResultado = new StringBuilder();

		if (CENTROS_CONSUMO_PRINCIPAL.contains(reporte.getIdCentroT())) {
			procesarReporteConConsumoPrincipal(reporte, reporteDTO, itemOperacion, itemReporte, mensajeResultado);
		} else if (CENTROS_CONSUMO_PLATINAS.contains(reporte.getIdCentroT())) {
			if (itemOperacion.getCentroTConsumo() == 0) {
				procesarReporteConConsumoPlatinas(reporte, reporteDTO, itemOperacion, itemReporte, mensajeResultado);
			} else {
				procesarReporteSimple(reporte, reporteDTO, itemOperacion, itemReporte, mensajeResultado);
			}
		} else if (reporte.getIdCentroT() == CENTRO_FINAL) {
			procesarReporteFinal(reporte, reporteDTO, itemOperacion, itemReporte, mensajeResultado);
		} else {
			procesarReporteSimple(reporte, reporteDTO, itemOperacion, itemReporte, mensajeResultado);
		}

		actualizarRespuesta(response, mensajeResultado.toString());
	}
	
	private void procesarReporteConConsumoPrincipal(ReportePiezaCt reporte, ReporteDTO reporteDTO, ItemOp itemOperacion,
			Item itemReporte, StringBuilder mensajeResultado) throws ServiceException, IOException {

		List<Conector> conectores = new ArrayList<>();

		// Verificar si ya existe consumo en algún centro de trabajo principal
		boolean tieneConsumoExistente = false;
		if (itemReporte.getCentroTrabajoConsumo() != 0) {
			log.info("La pieza {} ya tieneun consumo realizado en el centro de trabajo {}", reporteDTO.getRef(),
					itemOperacion.getCentroTConsumo());
			if (itemReporte.getCentroTrabajoConsumo().intValue() != reporteDTO.getIdCentroTrabajo().intValue()) {
				tieneConsumoExistente = true;
			}
		}

		if (!tieneConsumoExistente) {
			log.info("Creando conectores para consumo y tep para {}", reporteDTO.getRef());
			// Si no tiene consumo o el consumo se realizo es el mismo del reporte, crear tanto TEP como consumos
			conectores.addAll(agregarConsumoTepCentroTrabajo(reporteDTO, itemOperacion, false));
		} else {
			// Si ya tiene consumo, crear solo TEP
			log.info("Creando conectore tep para {}", reporteDTO.getRef());
			procesarReporteSimple(reporte, reporteDTO, itemOperacion, itemReporte, mensajeResultado);
		}

		// Procesar TEP y consumos
		String nombreArchivo = generarNombreArchivo("SCP_TEP_" + reporteDTO.getIdCentroTrabajo() + "_OP",
				reporteDTO.getNumOp().toString());
		log.info("Creando xml y archivo plano");
		guardarArchivos(conectores, nombreArchivo);

		log.info("Enviando xml al erp {}.", nombreArchivo);
		String respuestaWeService = xmlService.postImportarXML(conectores);
		log.info("Respuesta del webservices {}.", respuestaWeService);
		if (RESPUESTA_EXITOSA.equals(respuestaWeService)) {
			mensajeResultado.append("Consumo y TEP creados exitosamente.");
			log.info("Actualizando datos en integrapps item_op reporte");
			itemOperacion.setCentroTConsumo(reporte.getIdCentroT());
			actualizarCentrosTep(itemOperacion, reporteDTO.getIdCentroTrabajo(), false);
			reporte.setIsConsume(true);
			reporte.setIsTep(true);
		} else {
			String errorMsg = "Error al procesar la solicitud en el webservices: " + respuestaWeService;
			log.error(errorMsg);
			throw new ServiceException(errorMsg);
		}

		// Verificar si requiere proceso de punzonadora
		if (requierePunzonadora(itemReporte)) {
			log.info("La ruta del item incluye punzonadora, creando conector tep");
			conectores = new ArrayList<>();
			conectores.addAll(agregarConsumoPunzonadora(reporteDTO, itemOperacion));
			String nombrePunzonadora = "TEP_PUNZONADORA";
			nombreArchivo = generarNombreArchivo(nombrePunzonadora + "_OP", reporteDTO.getNumOp().toString());
			log.info("Creando xml y archivo plano");
			guardarArchivos(conectores, nombreArchivo);

			respuestaWeService = xmlService.postImportarXML(conectores);
			log.info("Respuesta del webservices {}.", respuestaWeService);
			if (RESPUESTA_EXITOSA.equals(respuestaWeService)) {
				mensajeResultado.append("TEP punzonadora creado exitosamente.");
				log.info("Actualizando datos en integrapps reporte");
				actualizarCentrosTep(itemOperacion, CENTRO_PUNZONADORA, false);
			} else {
				String errorMsg = "Error al procesar la solicitud en el webservices: " + respuestaWeService;
				log.error(errorMsg);
				throw new ServiceException(errorMsg);
			}
		}

	}


	private void procesarReporteSimple(ReportePiezaCt reporte, ReporteDTO reporteDTO, ItemOp itemOperacion,
			Item itemReporte, StringBuilder mensaje) throws IOException, ServiceException {

		String nombreArchivo = generarNombreArchivo("TEP_" + reporteDTO.getIdCentroTrabajo() + "_OP",
				reporteDTO.getNumOp().toString());
		List<Conector> tepSimple = consumosTepService.crearTEP(itemOperacion, reporteDTO, false);

		guardarArchivos(tepSimple, nombreArchivo);

		String respuestaWeService = xmlService.postImportarXML(tepSimple);
		if (RESPUESTA_EXITOSA.equals(respuestaWeService)) {
			reporte.setIsTep(true);
			reporte.setIsConsume(true);
			actualizarCentrosTep(itemOperacion, reporteDTO.getIdCentroTrabajo(), false);
			reporteRepository.save(reporte);
			mensaje.append("TEP creado exitosamente.");
		} else {
			String errorMsg = "Error al procesar la solicitud en el webservices: " + respuestaWeService;
			throw new ServiceException(errorMsg);
		}
	}

	private void procesarReporteFinal(ReportePiezaCt reporte, ReporteDTO reporteDTO, ItemOp itemOperacion,
			Item itemReporte, StringBuilder mensaje) throws IOException, ServiceException {

		List<Integer> centrosPendientes = obtenerCentrosPendientesPorReporteTepEnOP(itemOperacion, itemReporte);
		if (!centrosPendientes.isEmpty()) {
			log.info("Procesando centros de trabajo con tep pendientes");
			procesarCentrosPendientes(centrosPendientes, itemOperacion, itemReporte, reporteDTO, mensaje);
		}

		actualizarCantidadCumplida(reporteDTO, itemOperacion);
		itemOpService.guardarItemOp(itemOperacion);
		String respuestaWeService = entregaService.crearEntrega(itemOperacion, reporteDTO);
		if (RESPUESTA_ENTREGA_EXITOSA.equals(respuestaWeService)) {
			actualizarCentrosTep(itemOperacion, 14, true);
			reporte.setIsTep(true);
			reporte.setIsConsume(true);
		} else {
			String errorMsg = "Error al procesar la solicitud en el webservices: " + respuestaWeService;
			throw new ServiceException(errorMsg);
		}

		mensaje.append(respuestaWeService);
	}

	private List<Integer> obtenerCentrosPendientesPorReporteTepEnOP(ItemOp itemOperacion, Item itemReporte) {
		List<Integer> rutaCentrosTrabajoErp = itemOpService.obtenerCentrosTrabajoRutaPorIdOpIA(itemOperacion.getIdOpIntegrapps());
		
		List<Integer> centrosTepOp = ordenPvService.obtenerCentrosTrabajoTep(itemOperacion.getIdOpIntegrapps());
		return rutaCentrosTrabajoErp.stream().filter(centro -> !centrosTepOp.contains(centro))
				.collect(Collectors.toList());
	}

	private void procesarCentrosPendientes(List<Integer> centrosPendientes, ItemOp itemOperacion, Item itemReporte,
			ReporteDTO reporteDTO, StringBuilder mensajeResultado) throws IOException, ServiceException {

		for (Integer centro : centrosPendientes) {
			if (centro != 14) { // Excluir centro específico
				ReporteDTO reporteCentro = new ReporteDTO.Builder().from(reporteDTO).setIdCentroTrabajo(centro).build();
				List<Conector> tep = consumosTepService.crearTEP(itemOperacion, reporteCentro, true);
				String nombreArchivo = generarNombreArchivo("TEP_" + reporteDTO.getIdCentroTrabajo() + "_OP",
						reporteCentro.getNumOp().toString());

				guardarArchivos(tep, nombreArchivo);
				String respuestaWeService = xmlService.postImportarXML(tep);
				if (RESPUESTA_EXITOSA.equals(respuestaWeService)) {
					mensajeResultado.append("TEP centro de trabajo " + centro + " creado exitosamente.");
					actualizarCentrosTep(itemOperacion, centro, true);
				}else {
					String errorMsg = "Error al procesar la solicitud en el webservices: " + respuestaWeService;
					log.error(errorMsg);
					throw new ServiceException(errorMsg);
				}
			}
		}
	}

	private void procesarReporteConConsumoPlatinas(ReportePiezaCt reporte, ReporteDTO reporteDTO, ItemOp itemOperacion,
			Item itemReporte, StringBuilder mensajeResultado) throws IOException, ServiceException {
		List<Conector> conectores = new ArrayList<>();
		conectores.addAll(agregarConsumoTepCentroTrabajo(reporteDTO, itemOperacion, true));
		String nombreArchivo = generarNombreArchivo("SCP_TEP_" + reporteDTO.getIdCentroTrabajo() + "-OP",
				reporteDTO.getNumOp().toString());
		guardarArchivos(conectores, nombreArchivo);

		String respuestaWeService = xmlService.postImportarXML(conectores);
		if (RESPUESTA_EXITOSA.equals(respuestaWeService)) {
			mensajeResultado.append("Consumo y TEP creados exitosamente.");
			actualizarCentrosTep(itemOperacion, reporteDTO.getIdCentroTrabajo(), false);
			reporte.setIsConsume(true);
			reporte.setIsTep(true);
		} else {
			String errorMsg = "Error al procesar la solicitud en el webservices: " + respuestaWeService;
			log.error(errorMsg);
			throw new ServiceException(errorMsg);
		}

	}

	
	private List<Conector> agregarConsumoTepCentroTrabajo(ReporteDTO reporteDTO, ItemOp itemOperacion,
			Boolean platinas) {

		List<Conector> tepYConsumo = consumosTepService.crearTEPYConsumos(itemOperacion, reporteDTO, platinas);
		return tepYConsumo;
	}

	private boolean requierePunzonadora(Item itemReporte) {
		List<Integer> ruta = itemOpService.obtenerRutaItem(itemReporte.getIdItem());
		return ruta.contains(CENTRO_PUNZONADORA);
	}

	private List<Conector> agregarConsumoPunzonadora(ReporteDTO reporteDTO, ItemOp itemOperacion) {
		ReporteDTO reportePunzonadora = new ReporteDTO.Builder().from(reporteDTO).setIdCentroTrabajo(CENTRO_PUNZONADORA)
				.build();

		List<Conector> tepPunzonadora = consumosTepService.crearTEP(itemOperacion, reportePunzonadora, false);

		return tepPunzonadora;
	}

	private void actualizarCentrosTep(ItemOp itemOp, Integer nuevoCentro, Boolean centroTrabajoErp) {
		try {
			List<Integer> centrosTep = ordenPvService.obtenerCentrosTrabajoTep(itemOp.getIdOpIntegrapps());
			log.debug("Centros TEP actuales: {}", centrosTep);
			if (centrosTep.size() == 1 && centrosTep.get(0) == 0) {
				log.debug("Limpiando lista de centros TEP vacía");
				centrosTep.clear();
			}
			
			Integer idCTErp = nuevoCentro;
			if(Boolean.FALSE.equals(centroTrabajoErp)) {
				String idCTErpString = solicitudMateriaPrimaService.obtenerIdctErp(nuevoCentro).trim();
				log.debug("Convertido centro trabajo {} a centro trabajo ERP: {}", nuevoCentro, idCTErpString);
				idCTErp = Integer.valueOf(idCTErpString);			
			}
	
			if (!centrosTep.contains(idCTErp)) {
	            centrosTep.add(idCTErp);
	            log.debug("Lista final de centros TEP a actualizar: {}", centrosTep);
	            
	            // Actualizar en la base de datos
	            ordenPvService.actualizarCentrosTep(itemOp.getIdOpIntegrapps(), centrosTep);
	            log.debug("Actualización de centros TEP completada");
	        } else {
	            log.debug("Centro TEP {} ya existe en la lista, no se requiere actualización", idCTErp);
	        }
	
			//itemReporte.setCentrosTrabajoTep(centrosTep);
			//itemService.guardarItem(itemReporte);
			//ordenPvService.actualizarCentrosTep(itemOp.getIdOpIntegrapps(), centrosTep);
		} catch (Exception e) {
	        log.error("Error al actualizar centros TEP: {}", e.getMessage(), e);
	        throw new RuntimeException("Error al actualizar centros TEP: " + e.getMessage(), e);
	    }
	}

	private void guardarArchivos(List<Conector> conectores, String nombreArchivo) throws IOException {

		util.guardarRegistroXml(xmlService.crearPlanoXml(conectores), nombreArchivo);
		util.crearArchivoPlano(conectores, nombreArchivo, configService.getCIA());
	}

	private String generarNombreArchivo(String prefijo, String numeroOp) {
		return String.format("%s_%s_%s", prefijo, numeroOp,
				LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
	}

	private void actualizarRespuesta(ResponseMessage response, String mensaje) {
		response.setMensaje(mensaje + "Reporte guardado exitosamente.");
		response.setError(false);
	}

	private void manejarErrorIntegridad(ResponseMessage response, DataIntegrityViolationException e) {
		response.setMensaje("Error al guardar el reporte. Violación de integridad de datos: " + e.getMessage());
		response.setError(true);
	}

	private void manejarErrorGeneral(ResponseMessage response, Exception e) {
		Throwable causa = e.getCause();
		String mensaje = (causa instanceof SQLException)
				? "Error al guardar el reporte. Error de base de datos: " + causa.getMessage()
				: "Se presentó un error al guardar el reporte: " + e.getMessage();

		response.setMensaje(mensaje);
		response.setError(true);
	}

	private void actualizarCantidadCumplida(ReporteDTO reporteDTO, ItemOp item) {
		if (item.getCantCumplida() == null || item.getCantCumplida() == 0) {
			item.setCantCumplida(reporteDTO.getCantReportar().doubleValue());
		} else {
			item.setCantCumplida(item.getCantCumplida() + reporteDTO.getCantReportar().doubleValue());
		}
	}

	private ReportePiezaCt crearEntidadReporte(ReporteDTO reporteDTO) {
		return new ReportePiezaCt.Builder().idItemFab(reporteDTO.getIdItemFab()).idParte(reporteDTO.getIdParte())
				.idCentroT(reporteDTO.getIdCentroTrabajo()).idOperario(reporteDTO.getOperario().getId())
				.cant(reporteDTO.getCantReportar()).fechaCreacion(LocalDateTime.now()).itemId(reporteDTO.getIdItem())
				.lote(reporteDTO.getLote()).idConfigProceso(reporteDTO.getIdConfigProceso()).build();
	}

	@Override
	public Integer buscarCantidadesFabricadasConjunto(Long idItem, Integer idItemFab, Integer idCT) {
		return reporteRepository.buscarCantidadesFabricadasConjunto(idItem, idItemFab, idCT);
	}

	@Override
	public Integer buscarCantidadesFabricadasPerfil(Long idItem, Integer idPerfil, Integer idCT) {
		return reporteRepository.buscarCantidadesFabricadasPerfil(idItem, idPerfil, idCT);
	}

	public ReportePiezaCtServiceImpl(ReportePiezaCtRepository reportePiezaCtRepository, ItemOpService itemOpService,
			XmlService xmlService, ConsumosTepService consumosTepService,
			SolicitudMateriaPrimaService solicitudMateriaPrimaService, UtilitiesApp util,
			ConfigurationService configService, EntregaService entregaService, ItemService itemService,
			VistaOrdenPvService ordenPvService) {
		super();
		this.reporteRepository = reportePiezaCtRepository;
		this.itemOpService = itemOpService;
		this.xmlService = xmlService;
		this.consumosTepService = consumosTepService;
		this.solicitudMateriaPrimaService = solicitudMateriaPrimaService;
		this.util = util;
		this.configService = configService;
		this.entregaService = entregaService;
		this.itemService = itemService;
		this.ordenPvService = ordenPvService;
	}

	@Override
	public Page<ReportePiezaCtDTO> getReportesPiezaWithFilters(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Integer idOperario,
            Integer idCentroT,
            int page,
            int size,
            String sortBy,
            String sortDirection) {
            
        Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ? 
            Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        return reporteRepository.findReportesPiezaWithFilters(
            fechaInicio, fechaFin, idOperario, idCentroT, pageable); 
    }

	@Override
	public ReportePiezaCt validarYObtenerReporte(Integer idReporte) throws ServiceException {
		ReportePiezaCt reporte = reporteRepository.findById(idReporte)
	            .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + idReporte));
	        
	        if (Estado.PROCESANDO.equals(reporte.getEstado())) {
	            throw new ServiceException("Reporte ya está siendo procesado");
	        }
	        
	        if (Estado.COMPLETO.equals(reporte.getEstado())) {
	            log.warn("Reprocesando reporte que ya estaba completo. ID: {}", idReporte);
	        }
	        
	        return reporte;
	}
	
	private ReporteDTO construirReporteDTO(ReportePiezaCt reporte, Item itemReporte, ItemOp itemOp) {
		
        ReporteDTO reporteDTO = toReporteDTO(reporte);
        reporteDTO.setPeso(itemReporte.getPesoBruto());
        reporteDTO.setPesoPintura(BigDecimal.valueOf(itemOp.getPesoPintura()));
                
        return reporteDTO;
    }
	
	private void procesarReporte(ReportePiezaCt reporte, ReporteDTO reporteDTO, 
            ItemOp itemOp, Item itemReporte, ResponseMessage response) 
            throws ServiceException, IOException {
        try {
            reporte.setEstado(Estado.PROCESANDO);
            reporte.setUltimoIntento(LocalDateTime.now());
            reporteRepository.save(reporte);
            
            //procesarReporteSegunCentroTrabajo(reporte, reporteDTO, itemOp, itemReporte, response);
            
            reporte.setEstado(Estado.COMPLETO);
            reporte.setUltimoIntento(LocalDateTime.now());
            reporteRepository.save(reporte);
            
            //response.setMensaje(mensajeResultado.toString());
            //response.setError(false);
            
        } catch (Exception e) {
            manejarErrorProcesamiento(reporte, e);
            throw e;
        }
    }
	
	private void manejarErrorProcesamiento(ReportePiezaCt reporte, Exception e) {
        log.error("Error procesando reporte ID: {}", reporte.getId(), e);
        reporte.setEstado(Estado.ERROR);
        reporte.setMensajeError(e.getMessage());
        reporte.setUltimoIntento(LocalDateTime.now());
        reporteRepository.save(reporte);
    }

	@Transactional
	@Override
	public void reprocesarReporte(Integer idReporte) throws ServiceException, IOException {
		log.info("Iniciando reprocesamiento de reporte ID: {}", idReporte);
		ResponseMessage response = new ResponseMessage();
		try {
			ReportePiezaCt reporte = validarYObtenerReporte(idReporte);
			ItemOp itemOp = itemOpService.obtenerItemPorId(reporte.getItemId());
            Integer idItemReportar = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
            Item itemReporte = itemService.buscarItemFabrica(idItemReportar);
            
            // Construir DTO con datos ya obtenidos
            ReporteDTO reporteDTO = construirReporteDTO(reporte, itemReporte, itemOp);
            
            procesarReporte(reporte, reporteDTO, itemOp, itemReporte, response);
            
            log.info("Reprocesamiento exitoso para reporte ID: {}", idReporte);
			
		}catch (Exception e) {
            log.error("Error en reprocesamiento de reporte ID: {}", idReporte, e);
            manejarError(response, e);
            throw new ServiceException("Error en reprocesamiento: " + e.getMessage(), e);
        }				
	}
	
	private void manejarError(ResponseMessage response, Exception e) {
        response.setError(true);
        response.setMensaje(determinarMensajeError(e));
    }

    private String determinarMensajeError(Exception e) {
        if (e instanceof ResourceNotFoundException) {
            return "Reporte no encontrado: " + e.getMessage();
        } else if (e instanceof DataIntegrityViolationException) {
            return "Error de integridad de datos: " + e.getMessage();
        } else if (e instanceof ServiceException) {
            return "Error de servicio: " + e.getMessage();
        }
        return "Error inesperado: " + e.getMessage();
    }

	private ReporteDTO toReporteDTO(ReportePiezaCt reporte) {

		ReporteDTO reporteDTO = new ReporteDTO();
		reporteDTO.setIdCentroTrabajo(reporte.getIdCentroT());
		reporteDTO.setIdItemFab(reporte.getIdItemFab());
		reporteDTO.setIdParte(reporte.getIdParte());
		reporteDTO.setIdItem(reporte.getItemId());
		reporteDTO.setCantReportar(reporte.getCant());
		reporteDTO.setLote(reporte.getLote());
		reporteDTO.setIdConfigProceso(reporte.getIdConfigProceso());		
		
		return reporteDTO;
	}

}
