package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;

public interface ReportePiezaCtService {

	ErrorMensaje guardarReporte(ReporteDTO reporte);

	Integer buscarCantidadesFabricadasConjunto(Long idItem, Integer idItemFab, Integer idCT);

	Integer buscarCantidadesFabricadasPerfil(Long idItem, Integer idPerfil, Integer idCT);

}
