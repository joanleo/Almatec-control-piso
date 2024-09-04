package com.almatec.controlpiso.integrapps.services.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.erp.webservices.services.ConsumosTepService;
import com.almatec.controlpiso.erp.webservices.services.EntregaService;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt;
import com.almatec.controlpiso.integrapps.repositories.ReportePiezaCtRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ReportePiezaCtServiceImpl implements ReportePiezaCtService {
	
	private final ReportePiezaCtRepository reportePiezaCtRepository;	
	private final ItemOpService itemOpService;	
	private final XmlService xmlService;	
	private final ConsumosTepService consumosTepService;
	private final SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	private final UtilitiesApp util;
	private final ConfigurationService configService;
	private final EntregaService entregaService;

	/**
	 *SE REALIZA EL LLAMADO AL WEBSERVICE PARA  REPORTAR TEP YREALIZAR CONSUMOS
	 */
	@Transactional
	@Override
	public ResponseMessage guardarReporte(ReporteDTO reporteDTO) {
		//Crea entidad reporte integrapps
		ReportePiezaCt reporte = generaReporte(reporteDTO);
		ResponseMessage response = new ResponseMessage();
		String message = "";
		try {			
			//Obtengo el itemOp 
			ItemOp item =  itemOpService.obtenerItemPorId(reporte.getItemId());
			//Se configuran centros de trabajo iniciales donde se realizara el consumo en la op
			List<Integer> centrosReporteConsumo = List.of(3, 4, 5, 6, 7, 8, 9);
			
			boolean isConsume = centrosReporteConsumo.contains(reporte.getIdCentroT());

			if(isConsume && (item.getCentroTConsumo() == null || item.getCentroTConsumo() == 0)) {
					//Se realiza consumo y TEP
					List<Conector> conectoresTepYConsumo = consumosTepService.crearTEPYConsumos(item, reporteDTO);
					String responseService = xmlService.postImportarXML(conectoresTepYConsumo);
					if("Operacion realizada exitosamente".equals(responseService)) {//
						reporte.setIsConsume(true);//REVISAR RESPUESTA
						reporte.setIsTep(true);
						item.setCentroTConsumo(reporte.getIdCentroT());
						
						List<Integer> centrosTep = item.getCentrosTep();
						String idCTErpString = solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroT()).trim();
						Integer idCTErp = Integer.valueOf(idCTErpString);
						centrosTep.add(idCTErp);
						item.setCentrosTep(centrosTep);
						
						itemOpService.guardarItemOp(item);
					}
					message += responseService;
					
			}else if (reporte.getIdCentroT() == 17) {
				/*
				 * Se valida si es el utltimo centro de trabajoj por donde pasan las piezas, aqui se debera actualizar los itemOp,
				 * consumir lo que este pendiente de la lista de materiales
				 * realizar entrega de los item terminados
				 */
				//Se valida si no se ha reportado ninguna cantidad del itemOp
				//Validar que haya reporte en todos los centros de trabajo
				List<Integer> centrosTep = item.getCentrosTep();
				centrosTep.add(14);
				List<Integer> idsCentroTrabajoRuta = itemOpService.obtenerCentrosTrabajoPorIdOpIA(item.getIdOpIntegrapps());
				
				List<Integer> centrosFaltantes = validarYObtenerCentrosTepFaltantes(idsCentroTrabajoRuta, centrosTep);
				if (!centrosFaltantes.isEmpty()) {
				    // Realizar reporte en los centros de trabajo faltantes
					reportarTepsFaltantes(centrosFaltantes, item, reporteDTO, centrosTep);
					//String responseServiceTep = xmlService.crearTEP(item, reporteDTO);					
				} 
				//System.exit(0);
				actualizarCantidadCumplida(reporteDTO, item);
				itemOpService.guardarItemOp(item);
				String responseEntrega = entregaService.crearEntrega(item, reporteDTO);
				if("Entrega creada Exitosamente".equals(responseEntrega)) reporte.setIsTep(true);
				message += responseEntrega;
			} else{
				List<Conector> xmlTep = consumosTepService.crearTEP(item, reporteDTO, false);
				String responseServiceTep = xmlService.postImportarXML(xmlTep);
				if("TEP creado Exitosamente".equals(responseServiceTep)) reporte.setIsTep(true);
				message += responseServiceTep;
			}
			
			if(!"".equals(message)) {
				message += "\n" + "Reporte guardado exitosamente";
			}else {
				message += "Reporte guardado exitosamente";
			}
			response.setMensaje(message);
			response.setError(false);
			
		} catch (DataIntegrityViolationException e) {
	        response.setMensaje("Error al guardar el reporte. Violación de integridad de datos: " + e.getMessage());
	        response.setError(true);
	    } catch (Exception e) {
	    	
	        response.setMensaje(obteneMensajeError(e));
	        response.setError(true);
	    }
    	
		reportePiezaCtRepository.save(reporte);			
		return response;
	}

	private void reportarTepsFaltantes(List<Integer> centrosFaltantes, ItemOp item, ReporteDTO reporteDTO, List<Integer> centrosTep) {
		centrosFaltantes.forEach(centro->{
			try {
				if(centro != 14) {
					ReporteDTO auxiliar = new ReporteDTO.Builder()
						    .from(reporteDTO)
						    .setIdCentroTrabajo(centro)
						    .build();
					List<Conector> tep = consumosTepService.crearTEP(item, auxiliar, true);
					util.crearArchivoPlano(tep, "TEP_CT" + centro, configService.getCIA());
					String response = xmlService.postImportarXML(tep);
					System.out.println(response);
					centrosTep.add(centro);
					item.setCentrosTep(centrosFaltantes);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private List<Integer> validarYObtenerCentrosTepFaltantes(List<Integer> idsCentroTrabajoRuta,
			List<Integer> centrosTep) {
		List<Integer> centrosFaltantes = idsCentroTrabajoRuta.stream()
		                .filter(centro -> !centrosTep.contains(centro))
		                .collect(Collectors.toList());
		return centrosFaltantes;
	}

	private void actualizarCantidadCumplida(ReporteDTO reporteDTO, ItemOp item) {
		if(item.getCantCumplida() == null || item.getCantCumplida() == 0) {
			item.setCantCumplida(reporteDTO.getCantReportar().doubleValue());
		}else {
			item.setCantCumplida(item.getCantCumplida() + reporteDTO.getCantReportar().doubleValue());					
		}
	}
	
	private String obteneMensajeError(Exception e) {
		Throwable cause = e.getCause();
        if (cause instanceof SQLException) {
            SQLException sqlEx = (SQLException) cause;
            return "Error al guardar el reporte. Error de base de datos: " + sqlEx.getMessage();
        } else {
            return "Se presentó un error al guardar el reporte: " + e.getMessage();
        }
	}

	private ReportePiezaCt generaReporte(ReporteDTO reporteDTO) {
	    return new ReportePiezaCt.Builder()
	        .idItemFab(reporteDTO.getIdItemFab())
	        .idParte(reporteDTO.getIdParte())
	        .idCentroT(reporteDTO.getIdCentroTrabajo())
	        .idOperario(reporteDTO.getOperario().getId())
	        .cant(reporteDTO.getCantReportar())
	        .fechaCreacion(LocalDateTime.now())
	        .itemId(reporteDTO.getIdItem())
	        .lote(reporteDTO.getLote())
	        .idConfigProceso(reporteDTO.getIdConfigProceso())
	        .build();
	}

	@Override
	public Integer buscarCantidadesFabricadasConjunto(Long idItem, Integer idItemFab, Integer idCT) {
		return reportePiezaCtRepository.buscarCantidadesFabricadasConjunto(idItem, idItemFab, idCT);
	}

	@Override
	public Integer buscarCantidadesFabricadasPerfil(Long idItem, Integer idPerfil, Integer idCT) {
		return reportePiezaCtRepository.buscarCantidadesFabricadasPerfil(idItem, idPerfil, idCT);
	}
	
	

	public ReportePiezaCtServiceImpl(ReportePiezaCtRepository reportePiezaCtRepository, ItemOpService itemOpService,
			XmlService xmlService, ConsumosTepService consumosTepService, SolicitudMateriaPrimaService solicitudMateriaPrimaService,
			UtilitiesApp util, ConfigurationService configService, EntregaService entregaService) {
		super();
		this.reportePiezaCtRepository = reportePiezaCtRepository;
		this.itemOpService = itemOpService;
		this.xmlService = xmlService;
		this.consumosTepService = consumosTepService;
		this.solicitudMateriaPrimaService = solicitudMateriaPrimaService;
		this.util = util;
		this.configService = configService;
		this.entregaService = entregaService;
	}

}
