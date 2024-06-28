package com.almatec.controlpiso.utils;

import java.math.BigDecimal;

public class RowItemPdf {
	private String descripcion;
	private BigDecimal longitud;
	private BigDecimal cant;
	private String op;
	private String ref;
	private BigDecimal peso;
	private Integer prioridad;
	private String color;
	private String proyecto;
	
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
	public BigDecimal getCant() {
		return cant;
	}
	public void setCant(BigDecimal cant) {
		this.cant = cant;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String proyecto) {
		this.op = proyecto;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public RowItemPdf() {
		super();
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	@Override
	public String toString() {
		return "RowItemPdf [descripcion=" + descripcion + ", longitud=" + longitud + ", cant=" + cant + ", op=" + op
				+ ", ref=" + ref + ", peso=" + peso + ", prioridad=" + prioridad + ", color=" + color + ", proyecto="
				+ proyecto + "]";
	}
	
}
