package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.Prioridad;
import com.almatec.controlpiso.programacion.dtos.PrioridadItemsDTO;

public interface PrioridadSevice {

	List<Prioridad> guardarActualizarPrioridades(PrioridadItemsDTO itemsPrioridad);

}
