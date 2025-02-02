package com.almatec.controlpiso.produccion.dtos;

import java.time.LocalDateTime;

import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt.Estado;

public class ReportePiezaCtDTO {
    private Integer id;
    private String operarioNombre;
    private String centroTrabajoNombre;
    private String itemDescripcion;
    private Integer cant;
    private LocalDateTime fechaCreacion;
    private String lote;
    private String estado;
    private LocalDateTime ultimoIntento;
    private String mensajeError;
    private String op;
    private String co;
    private String zona;
    
	public ReportePiezaCtDTO() {
		super();
	}

	public ReportePiezaCtDTO(Integer id, String operarioNombre, String centroTrabajoNombre, String itemDescripcion,
			Integer cant, LocalDateTime fechaCreacion, String lote, Estado estado, LocalDateTime ultimoIntento, 
			String mensajeError, String op, String co, String zona) {
		super();
		this.id = id;
		this.operarioNombre = operarioNombre;
		this.centroTrabajoNombre = centroTrabajoNombre;
		this.itemDescripcion = itemDescripcion;
		this.cant = cant;
		this.fechaCreacion = fechaCreacion;
		this.lote = lote;
		this.estado = estado.toString();
		this.ultimoIntento = ultimoIntento;
		this.mensajeError = mensajeError;
		this.op = op;
		this.co = co;
		this.zona = zona;
	}


	public Integer getId() {
		return id;
	}

	public String getOperarioNombre() {
		return operarioNombre;
	}

	public String getCentroTrabajoNombre() {
		return centroTrabajoNombre;
	}

	public String getItemDescripcion() {
		return itemDescripcion;
	}

	public Integer getCant() {
		return cant;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public String getLote() {
		return lote;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOperarioNombre(String operarioNombre) {
		this.operarioNombre = operarioNombre;
	}

	public void setCentroTrabajoNombre(String centroTrabajoNombre) {
		this.centroTrabajoNombre = centroTrabajoNombre;
	}

	public void setItemDescripcion(String itemDescripcion) {
		this.itemDescripcion = itemDescripcion;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getEstado() {
		return estado;
	}

	public LocalDateTime getUltimoIntento() {
		return ultimoIntento;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setUltimoIntento(LocalDateTime ultimoIntento) {
		this.ultimoIntento = ultimoIntento;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getCo() {
		return co;
	}

	public String getZona() {
		return zona;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
    
    
}
