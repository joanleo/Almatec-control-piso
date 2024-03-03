package com.almatec.controlpiso.integrapps.dtos;

public class PiezaOperarioDTO {
	
	private Integer idProceso;
	
	private Integer idOperario;
	
	private Integer idPieza;
	
	private Integer idPerfil;
	
	private Boolean estaActivo;

	public PiezaOperarioDTO() {
		super();
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public Integer getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Integer idPieza) {
		this.idPieza = idPieza;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idProItem) {
		this.idPerfil = idProItem;
	}

	public Boolean getEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(Boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

	@Override
	public String toString() {
		return "PiezaOperarioDTO [idProceso=" + idProceso + ", idOperario=" + idOperario + ", idPieza=" + idPieza
				+ ", idPerfil=" + idPerfil + ", estaActivo=" + estaActivo + "]";
	}

}

