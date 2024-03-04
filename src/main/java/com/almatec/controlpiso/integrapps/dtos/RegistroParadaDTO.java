package com.almatec.controlpiso.integrapps.dtos;

public class RegistroParadaDTO {

	private Integer idConfigProceso;
	private Integer idOperario;
	private Long idParada;
	
	public RegistroParadaDTO() {
		super();
	}

	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public Long getIdParada() {
		return idParada;
	}

	public void setIdConfigProceso(Integer idConfigProceso) {
		this.idConfigProceso = idConfigProceso;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public void setIdParada(Long idParada) {
		this.idParada = idParada;
	}

	@Override
	public String toString() {
		return "RegistroParadaDTO [idConfigProceso=" + idConfigProceso + ", idOperario=" + idOperario + ", idParada="
				+ idParada + "]";
	}
	
	
}
