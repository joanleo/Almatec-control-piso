package com.almatec.controlpiso.integrapps.dtos;

import java.util.Date;

public class PedidoSpecDTO {

	private String un;
	private String estado;
	private String asesor;
	private String descripcion;
	private String cliente;
	private String tipo;
	private Date fechaInicio;
	private Date fechaFin;
	
	public PedidoSpecDTO() {
		super();
	}

	public String getUn() {
		return un;
	}

	public String getEstado() {
		return estado;
	}

	public String getAsesor() {
		return asesor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCliente() {
		return cliente;
	}

	public String getTipo() {
		return tipo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "PedidoSpecDTO [un=" + un + ", estado=" + estado + ", asesor=" + asesor + ", descripcion=" + descripcion
				+ ", cliente=" + cliente + ", tipo=" + tipo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ "]";
	}
	
	
}
