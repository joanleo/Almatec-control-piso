package com.almatec.controlpiso.programacion.dtos;

public class PrioridadFilterDTO {

	private String descripcion;
    private Integer prioridad;
    private Integer cantidad;
    private String proyecto;
    private String zona;
    
	public PrioridadFilterDTO() {
		super();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
    
    
}
