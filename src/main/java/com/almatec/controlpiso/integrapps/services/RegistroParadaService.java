package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.InfoParadaDTO;
import com.almatec.controlpiso.integrapps.dtos.RegistroParadaDTO;

public interface RegistroParadaService {

	ErrorMensaje registrarActualizarParada(RegistroParadaDTO registroParada, Integer idCT);

	List<InfoParadaDTO> obtenerInfoParadasCT(Integer idConfigProceso);

}
