package com.almatec.controlpiso.integrapps.dtos;

import java.util.Date;

import com.almatec.controlpiso.integrapps.interfaces.VistaKgPorCt;

public class VistaKgPorCtDTO {
	
	private Date fecha;
	private String centroTrabajo;
	private Double kgsCumplidos;
	private Double mlsCumplidos;
	
	public VistaKgPorCtDTO() {
		super();
	}

	public VistaKgPorCtDTO(VistaKgPorCt interfaceKg) {
		super();
		this.fecha = interfaceKg.getfecha();
		this.centroTrabajo = interfaceKg.getcentro_trabajo();
		this.kgsCumplidos = interfaceKg.getkg_cumplidos();
		this.mlsCumplidos = interfaceKg.getml_cumplidos();
	}

	public Date getFecha() {
		return fecha;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public Double getKgsCumplidos() {
		return kgsCumplidos;
	}

	public Double getMlsCumplidos() {
		return mlsCumplidos;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public void setKgsCumplidos(Double kgsCumplidos) {
		this.kgsCumplidos = kgsCumplidos;
	}

	public void setMlsCumplidos(Double mlsCumplidos) {
		this.mlsCumplidos = mlsCumplidos;
	}

	
}
