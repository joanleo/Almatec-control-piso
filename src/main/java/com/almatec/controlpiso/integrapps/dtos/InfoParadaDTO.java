package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.interfaces.InfoParada;

public class InfoParadaDTO {

	private Integer idProceso;
	private Integer idParada;
	private Float tiempo;
	private String nombre;
	private Integer idOperario;
	private Boolean activo;
	
	
	public InfoParadaDTO() {
	}
	
	public InfoParadaDTO(InfoParada registro) {
		this.idParada = registro.getC_proparada_id();
		this.idProceso = registro.getC_proconfigproceso_id();
		this.nombre = registro.getA_nombre();
		this.tiempo = registro.getTiempo();
		this.idOperario = registro.getC_prooperario_id();
		this.activo = registro.getE_activo();
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public Integer getIdParada() {
		return idParada;
	}

	public Float getTiempo() {
		return tiempo;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public void setIdParada(Integer idParada) {
		this.idParada = idParada;
	}

	public void setTiempo(Float tiempo) {
		this.tiempo = tiempo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "InfoParadaDTO [idProceso=" + idProceso + ", idParada=" + idParada + ", tiempo=" + tiempo + ", nombre="
				+ nombre + ", idOperario=" + idOperario + ", activo=" + activo + "]";
	}
}
