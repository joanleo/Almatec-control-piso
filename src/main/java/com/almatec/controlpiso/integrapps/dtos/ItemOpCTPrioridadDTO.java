package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;

public class ItemOpCTPrioridadDTO {
	
	private Long idItemOp;
	private String descripcion;
	private Double cantRequerida;
	private Double cantCumplida;
	private BigDecimal peso;
	private BigDecimal longitud;
	private Integer numOp;
	private Integer IdOpIntegrapps;
	private String cliente;
	private String esquemaPintura;
	private String zona;
	private String centroOperaciones;
	private Integer prioridad;
	

	public ItemOpCTPrioridadDTO(Long idItemOp, String descripcion, Double cantRequerida, Double cantCumplida,
			BigDecimal peso, BigDecimal longitud, Integer numOp, Integer idOpIntegrapps, String cliente,
			String esquemaPintura, String zona, String centroOperaciones, Integer prioridad) {
		super();
		this.idItemOp = idItemOp;
		this.descripcion = descripcion;
		this.cantRequerida = cantRequerida;
		this.cantCumplida = cantCumplida;
		this.peso = peso;
		this.longitud = longitud;
		this.numOp = numOp;
		IdOpIntegrapps = idOpIntegrapps;
		this.cliente = cliente;
		this.esquemaPintura = esquemaPintura;
		this.zona = zona;
		this.centroOperaciones = centroOperaciones;
		this.prioridad = prioridad;
	}

	public ItemOpCTPrioridadDTO() {
		super();
	}

	public Long getIdItemOp() {
		return idItemOp;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Double getCantRequerida() {
		return cantRequerida;
	}

	public Double getCantCumplida() {
		return cantCumplida;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public Integer getIdOpIntegrapps() {
		return IdOpIntegrapps;
	}

	public String getCliente() {
		return cliente;
	}

	public String getEsquemaPintura() {
		return esquemaPintura;
	}

	public String getZona() {
		return zona;
	}

	public String getCentroOperaciones() {
		return centroOperaciones;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setIdItemOp(Long idItemOp) {
		this.idItemOp = idItemOp;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCantRequerida(Double cantRequerida) {
		this.cantRequerida = cantRequerida;
	}

	public void setCantCumplida(Double cantCumplida) {
		this.cantCumplida = cantCumplida;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public void setIdOpIntegrapps(Integer idOpIntegrapps) {
		IdOpIntegrapps = idOpIntegrapps;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setEsquemaPintura(String esquemaPintura) {
		this.esquemaPintura = esquemaPintura;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public void setCentroOperaciones(String centroOperaciones) {
		this.centroOperaciones = centroOperaciones;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	@Override
	public String toString() {
		return "ItemOpCTPrioridadDTO [idItemOp=" + idItemOp + ", descripcion=" + descripcion + ", cantRequerida="
				+ cantRequerida + ", cantCumplida=" + cantCumplida + ", peso=" + peso + ", longitud=" + longitud
				+ ", numOp=" + numOp + ", IdOpIntegrapps=" + IdOpIntegrapps + ", cliente=" + cliente
				+ ", esquemaPintura=" + esquemaPintura + ", zona=" + zona + ", centroOperaciones=" + centroOperaciones
				+ ", prioridad=" + prioridad + "]";
	}

	}
