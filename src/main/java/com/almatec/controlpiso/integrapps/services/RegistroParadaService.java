package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.dtos.InfoParadaDTO;
import com.almatec.controlpiso.integrapps.dtos.RegistroParadaDTO;

public interface RegistroParadaService {

	ResponseMessage registrarActualizarParada(RegistroParadaDTO registroParada, Integer idCT);

	List<InfoParadaDTO> obtenerInfoParadasCT(Integer idConfigProceso);

}
