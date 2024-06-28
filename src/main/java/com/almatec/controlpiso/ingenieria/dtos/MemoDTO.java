package com.almatec.controlpiso.ingenieria.dtos;

import java.util.List;

public class MemoDTO {

	private Long id;
	private Integer idOpIntegrapps;
	private Integer idUsuario;
	private String observacion;
	private List<MemoDetalleDto> detalles;
	
	public MemoDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idMemo) {
		this.id = idMemo;
	}

	public Integer getIdOpIntegrapps() {
		return idOpIntegrapps;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public List<MemoDetalleDto> getDetalles() {
		return detalles;
	}

	public void setIdOpIntegrapps(Integer idOpIntegrapps) {
		this.idOpIntegrapps = idOpIntegrapps;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setDetalles(List<MemoDetalleDto> detalles) {
		this.detalles = detalles;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
