package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.exceptions.ServiceException;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;

public interface ReportePiezaCtService {

	ResponseMessage guardarReporte(ReporteDTO reporte) throws ServiceException;

	Integer buscarCantidadesFabricadasConjunto(Long idItem, Integer idItemFab, Integer idCT);

	Integer buscarCantidadesFabricadasPerfil(Long idItem, Integer idPerfil, Integer idCT);

}
