package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt;
import com.almatec.controlpiso.produccion.dtos.ReportePiezaCtDTO;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.almatec.controlpiso.exceptions.ServiceException;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;

public interface ReportePiezaCtService {

	ResponseMessage guardarReporte(ReporteDTO reporte) throws ServiceException;

	Integer buscarCantidadesFabricadasConjunto(Long idItem, Integer idItemFab, Integer idCT);

	Integer buscarCantidadesFabricadasPerfil(Long idItem, Integer idPerfil, Integer idCT);

	Page<ReportePiezaCtDTO> getReportesPiezaWithFilters(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			Integer idOperario, Integer idCentroT, String op, String co, String zona, ReportePiezaCt.Estado estado,
			int page, int size, String sortBy, String sortDirection);

	ReportePiezaCt validarYObtenerReporte(Integer idReporte) throws ServiceException;

	void reprocesarReporte(Integer idReporte) throws ServiceException, IOException;

}
