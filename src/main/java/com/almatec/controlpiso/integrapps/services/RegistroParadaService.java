package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.RegistroParadaDTO;

public interface RegistroParadaService {

	ErrorMensaje registrarActualizarParada(RegistroParadaDTO registroParada);

}
