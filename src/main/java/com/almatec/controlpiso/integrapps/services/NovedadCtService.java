package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.NovedadDTO;

public interface NovedadCtService {

	Integer obtenerUltimoConsecutivo();

	ErrorMensaje guardarNovedad(NovedadDTO novedad);

}
