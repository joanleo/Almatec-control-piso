package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;

public interface ReportePiezaCtService {

	ResponseMessage guardarReporte(ReporteDTO reporte);

	Integer buscarCantidadesFabricadasConjunto(Long idItem, Integer idItemFab, Integer idCT);

	Integer buscarCantidadesFabricadasPerfil(Long idItem, Integer idPerfil, Integer idCT);

}
