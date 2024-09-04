package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.dtos.NovedadDTO;
import com.almatec.controlpiso.integrapps.entities.NovedadCt;

public interface NovedadCtService {

	Integer obtenerUltimoConsecutivo();

	ResponseMessage guardarNovedad(NovedadDTO novedad);

	NovedadCt actualizaEstado(Integer idNovedad);

}
