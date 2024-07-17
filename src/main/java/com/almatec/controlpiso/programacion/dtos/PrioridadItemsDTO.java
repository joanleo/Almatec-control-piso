package com.almatec.controlpiso.programacion.dtos;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PrioridadItemsDTO {
	@NotNull(message = "La prioridad es requerida")
    @Positive(message = "La prioridad debe ser un número positivo")
	private Integer prioridad;
	
	@NotNull(message = "El ID del centro de trabajo es requerido")
	private Integer centroTrabajoId;
	
	@NotEmpty(message = "Se requiere al menos un ID de item")
	private List<Integer> itemsId;
	
	public PrioridadItemsDTO() {
		super();
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public Integer getCentroTrabajoId() {
		return centroTrabajoId;
	}

	public List<Integer> getItemsId() {
		return itemsId;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public void setCentroTrabajoId(Integer centroTrabajoId) {
		this.centroTrabajoId = centroTrabajoId;
	}

	public void setItemsId(List<Integer> itemsId) {
		this.itemsId = itemsId;
	}

	@Override
	public String toString() {
		return "PrioridadItemsDTO [prioridad=" + prioridad + ", centroTrabajoId=" + centroTrabajoId + ", itemsId="
				+ itemsId + "]";
	}

}
