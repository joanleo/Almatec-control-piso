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
    private String op;
    private String marca;
    
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

	public String getOp() {
		return op;
	}

	public String getMarca() {
		return marca;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "PrioridadFilterDTO [descripcion=" + descripcion + ", prioridad=" + prioridad + ", cantidad=" + cantidad
				+ ", proyecto=" + proyecto + ", zona=" + zona + ", centroTrabajoId=" + centroTrabajoId + ", op=" + op
				+ ", marca=" + marca + "]";
	}
    
    
}
