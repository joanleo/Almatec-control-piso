package com.almatec.controlpiso.utils;

import java.math.BigDecimal;

public class RowItemPdf {
	private String descripcion;
	private BigDecimal longitud;
	private Integer cant;
	private String proyecto;
	private String ref;
	private BigDecimal peso;
	private Integer prioridad;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getLongitud() {
		return longitud;
	}
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	public Integer getCant() {
		return cant;
	}
	public void setCant(Integer cant) {
		this.cant = cant;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getRef() {
		return ref;
	}
	public BigDecimal getPeso() {
		return peso;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public Integer getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	public RowItemPdf() {
		super();
	}
	@Override
	public String toString() {
		return "RowItemPdf [descripcion=" + descripcion + ", longitud=" + longitud + ", cant=" + cant + ", proyecto="
				+ proyecto + ", ref=" + ref + ", peso=" + peso + ", prioridad=" + prioridad + "]";
	}
	
}
