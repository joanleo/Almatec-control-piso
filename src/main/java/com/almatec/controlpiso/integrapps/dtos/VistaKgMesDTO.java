package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.interfaces.VistaKgMes;

public class VistaKgMesDTO {

	private String op;
	private String centrOperaciones;
	private Double kgSol;
	private Double kgMes;
	private String anoMes;
	
	public VistaKgMesDTO() {
		super();
	}

	public VistaKgMesDTO(VistaKgMes item) {
		op = item.gettipo_op() + "-" + item.getnum_op();
		centrOperaciones = item.getcentro_operacion();
		kgSol = item.gettotal_kg_sol();
		kgMes = item.gettotal_kg_mes();
		anoMes = item.getyear_month();
	}

	public String getOp() {
		return op;
	}

	public String getCentrOperaciones() {
		return centrOperaciones;
	}

	public Double getKgSol() {
		return kgSol;
	}

	public Double getKgMes() {
		return kgMes;
	}

	public String getAnoMes() {
		return anoMes;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public void setCentrOperaciones(String centrOperaciones) {
		this.centrOperaciones = centrOperaciones;
	}

	public void setKgSol(Double kgSol) {
		this.kgSol = kgSol;
	}

	public void setKgMes(Double kgMes) {
		this.kgMes = kgMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}
	
	
}