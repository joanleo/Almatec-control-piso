package com.almatec.controlpiso.programacion.dtos;

import java.io.Serializable;

public class PrioridadFilterDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -735396412614254670L;
	private String descripcion;
    private Integer prioridad;
    private Integer cantidad;
    private String proyecto;
    private String zona;
    private Integer centroTrabajoId;
    
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

	public Integer getCentroTrabajoId() {
		return centroTrabajoId;
	}

	public void setCentroTrabajoId(Integer centroTrabajoId) {
		this.centroTrabajoId = centroTrabajoId;
	}

	@Override
	public String toString() {
		return "PrioridadFilterDTO [descripcion=" + descripcion + ", prioridad=" + prioridad + ", cantidad=" + cantidad
				+ ", proyecto=" + proyecto + ", zona=" + zona + ", centroTrabajoId=" + centroTrabajoId + "]";
	}
    
    
}
