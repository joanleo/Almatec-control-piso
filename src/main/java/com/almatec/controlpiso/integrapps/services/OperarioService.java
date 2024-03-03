package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.entities.Operario;

public interface OperarioService {

	Operario obtenerOperario(Integer numCedula);

	Operario buscarOperarioPorId(Integer idOperario);

}
