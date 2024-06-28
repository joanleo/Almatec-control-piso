package com.almatec.controlpiso.integrapps.services.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt;
import com.almatec.controlpiso.integrapps.repositories.ReportePiezaCtRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;

@Service
public class ReportePiezaCtServiceImpl implements ReportePiezaCtService {
	
	private final ReportePiezaCtRepository reportePiezaCtRepository;
	
	private final ItemOpService itemOpService;
	
	private final XmlService xmlService;

	/**
	 *SE REALIZA EL LLAMADO AL WEBSERVICE PARA  REPORTAR TEP YREALIZAR CONSUMOS
	 */
	@Transactional
	@Override
	public ErrorMensaje guardarReporte(ReporteDTO reporteDTO) {
		//Crea entidad reporte integrapps
		ReportePiezaCt reporte = generaReporte(reporteDTO);
		ErrorMensaje response = new ErrorMensaje();
		String message = "";
		try {
			//Guardo entidad reporte en integrapps 
			
			xmlService.asignarParametros();
			//Obtengo el itemOp 
			ItemOp item =  itemOpService.obtenerItemPorId(reporte.getItemId());
			//Se configuran centros de trabajo iniciales donde se realizara el consumo en la op
			List<Integer> centrosReporteConsumo = List.of(3, 4, 5, 6, 7, 8, 9);
			
			boolean isConsume = centrosReporteConsumo.contains(reporte.getIdCentroT());
			
			
			if(isConsume && item.getCentroTConsumo() == null) {
					//Se realiza consumo y TEP
					String responseService = xmlService.crearTEPYConsumos(item, reporteDTO);
					if("Operacion realizada exitosamente".equals(responseService)) {
						reporte.setIsConsume(true);//REVISAR RESPUESTA
						reporte.setIsTep(true);
						item.setCentroTConsumo(reporte.getIdCentroT());
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
				actualizarCantidadCumplida(reporteDTO, item);
				itemOpService.guardarItemOp(item);
				String responseEntrega = xmlService.crearEntrega(item, reporteDTO);
				if("Entrega creada Exitosamente".equals(responseEntrega)) reporte.setIsTep(true);
				message += responseEntrega;
			} else{
				String responseServiceTep = xmlService.crearTEP(item, reporteDTO);
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

	private void actualizarCantidadCumplida(ReporteDTO reporteDTO, ItemOp item) {
		if(item.getCantCumplida() == null) {
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
		ReportePiezaCt reporte = new ReportePiezaCt();
		reporte.setIdItemFab(reporteDTO.getIdItemFab());
		if(reporteDTO.getIdParte() != null) {
			reporte.setIdParte(reporteDTO.getIdParte());
		}
		reporte.setIdCentroT(reporteDTO.getIdCentroTrabajo());
		reporte.setIdOperario(reporteDTO.getOperario().getId());
		reporte.setCant(reporteDTO.getCantReportar());
		LocalDateTime fecha = LocalDateTime.now();
		reporte.setFechaCreacion(fecha);
		reporte.setItemId(reporteDTO.getIdItem());
		reporte.setLote(reporteDTO.getLote());
		return reporte;
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
			XmlService xmlService) {
		super();
		this.reportePiezaCtRepository = reportePiezaCtRepository;
		this.itemOpService = itemOpService;
		this.xmlService = xmlService;
	}

}
