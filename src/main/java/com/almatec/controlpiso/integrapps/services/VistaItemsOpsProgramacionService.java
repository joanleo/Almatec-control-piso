package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;

public interface VistaItemsOpsProgramacionService {

	List<VistaItemsOpsProgramacion> obtenerItemsOps();

	void actualizaPrioridad(Long idItem, Integer prioridad);

}
