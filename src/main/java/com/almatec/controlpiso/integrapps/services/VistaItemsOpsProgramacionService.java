package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;
import com.almatec.controlpiso.programacion.dtos.PrioridadFilterDTO;

public interface VistaItemsOpsProgramacionService {

	List<VistaItemsOpsProgramacion> obtenerItemsOps();

	void actualizaPrioridad(Long idItem, Integer prioridad);

	Page<VistaItemsOpsProgramacion> obtenerItemsOpsPaginados(int page, int size, String sortDir, String sortField, PrioridadFilterDTO filter);

}
