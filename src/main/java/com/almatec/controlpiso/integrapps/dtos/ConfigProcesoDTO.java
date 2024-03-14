package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.entities.ConfigProceso;

public class ConfigProcesoDTO {

	private Integer id;
	private Boolean estado;
	
	public ConfigProcesoDTO(Integer id, Boolean estado) {
		super();
		this.id = id;
		this.estado = estado;
	}

	public ConfigProcesoDTO(ConfigProceso configProceso) {
		this.id = configProceso.getId();
		this.estado = configProceso.getIsActivo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ConfigProcesoDTO [id=" + id + ", estado=" + estado + "]";
	}
	
}
