package com.almatec.controlpiso.integrapps.dtos;

public class OperarioDTO {

	private Integer idOperario;
	
	private Integer idCentroTrabajo;
	
	private Integer idConfigProceso;

	public OperarioDTO() {
		super();
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}

	public void setIdConfigProceso(Integer idConfigProceso) {
		this.idConfigProceso = idConfigProceso;
	}

	@Override
	public String toString() {
		return "OperarioDTO [idOperario=" + idOperario + ", idCentroTrabajo=" + idCentroTrabajo + ", idConfigProceso="
				+ idConfigProceso + ", getIdOperario()=" + getIdOperario() + ", getIdCentroTrabajo()="
				+ getIdCentroTrabajo() + ", getIdConfigProceso()=" + getIdConfigProceso() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
