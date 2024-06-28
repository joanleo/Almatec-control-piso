package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.NovedadDTO;
import com.almatec.controlpiso.integrapps.entities.NovedadCt;

public interface NovedadCtService {

	Integer obtenerUltimoConsecutivo();

	ErrorMensaje guardarNovedad(NovedadDTO novedad);

	NovedadCt actualizaEstado(Integer idNovedad);

}
