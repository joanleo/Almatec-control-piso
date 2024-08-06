package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.utils.dto.OperarioBarCodeDTO;

public interface OperarioService {

	Operario obtenerOperario(Integer numCedula);

	Operario buscarOperarioPorId(Integer idOperario);

	List<OperarioBarCodeDTO> obtenerDataBarCodeOperarios();

}
