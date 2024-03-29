package com.almatec.controlpiso.integrapps.services.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.OrdenPv;
import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt;
import com.almatec.controlpiso.integrapps.repositories.ReportePiezaCtRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;

@Service
public class ReportePiezaCtServiceImpl implements ReportePiezaCtService {
	
	@Autowired
	private ReportePiezaCtRepository reportePiezaCtRepository;
	
	@Autowired
	private ItemOpService itemOpService;
	
	@Autowired
	private OrdenPvService ordenPvService;

	@Transactional
	@Override
	public ErrorMensaje guardarReporte(ReporteDTO reporteDTO) {
		ReportePiezaCt reporte = generaReporte(reporteDTO);
		ErrorMensaje mensaje = new ErrorMensaje();
		try {
			reportePiezaCtRepository.save(reporte);
			if(reporte.getIdCentroT() == 17) {
				ItemOp item =  itemOpService.obtenerItemPorId(reporte.getItemId());
				if(item.getCantCumplida() == null) {
					item.setCantCumplida(reporteDTO.getCantReportar().doubleValue());
				}else {
					item.setCantCumplida(item.getCantCumplida() + reporteDTO.getCantReportar().doubleValue());					
				}
				itemOpService.guardarItemOp(item);
			}
			mensaje.setMensaje("Reporte guardado exitosamente");
			mensaje.setError(false);
			
		} catch (Exception e) {
			mensaje.setMensaje("Se presento un error al tratar de guardar el reporte." + e.getMessage());
			mensaje.setError(true);
		}
		return mensaje;
	}

	private ReportePiezaCt generaReporte(ReporteDTO reporteDTO) {
		ReportePiezaCt reporte = new ReportePiezaCt();
		reporte.setIdItemFab(reporteDTO.getIdItemFab());
		if(reporteDTO.getIdPerfil() != null) {
			reporte.setIdPerfil(reporteDTO.getIdPerfil());
		}
		reporte.setIdCentroT(reporteDTO.getIdCentroTrabajo());
		reporte.setIdOperario(reporteDTO.getOperario().getId());
		reporte.setCant(reporteDTO.getCantReportar());
		LocalDateTime fecha = LocalDateTime.now();
		reporte.setFechaCreacion(fecha);
		reporte.setItemId(reporteDTO.getIdItem());
		return reporte;
	}

	@Override
	public Integer buscarCantidadesFabricadasConjunto(Long idItem, Integer idItemFab, Integer idCT) {
		Integer cantidad = reportePiezaCtRepository.buscarCantidadesFabricadasConjunto(idItem, idItemFab, idCT);
		return cantidad;
	}

	@Override
	public Integer buscarCantidadesFabricadasPerfil(Long idItem, Integer idPerfil, Integer idCT) {
		Integer cantidad = reportePiezaCtRepository.buscarCantidadesFabricadasPerfil(idItem, idPerfil, idCT);
		return cantidad;
	}

}
