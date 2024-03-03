package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.entities.Operario;

public class ReporteDTO {
	private String proyecto;
	private Integer numOp;
	private String ref;
	private Integer cantSol;
	private Integer cantFab;
	private String centroTrabajo;
	private Operario operario;
	private Integer idCentroTrabajo;
	private Integer idItemFab;
	private Integer idPerfil;
	private Long idItem;
	private Integer cantReportar;
	
	public String getProyecto() {
		return proyecto;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getRef() {
		return ref;
	}

	public Integer getCantSol() {
		return cantSol;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public void setCantSol(Integer cant) {
		this.cantSol = cant;
	}

	public Integer getCantFab() {
		return cantFab;
	}

	public void setCantFab(Integer cantFab) {
		this.cantFab = cantFab;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public Operario getOperario() {
		return operario;
	}

	public void setOperario(Operario operario) {
		this.operario = operario;
	}

	public ReporteDTO() {
		super();
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Integer getCantReportar() {
		return cantReportar;
	}

	public void setCantReportar(Integer cantReportar) {
		this.cantReportar = cantReportar;
	}

	@Override
	public String toString() {
		return "ReporteDTO [proyecto=" + proyecto + ", numOp=" + numOp + ", ref=" + ref + ", cantSol=" + cantSol
				+ ", cantFab=" + cantFab + ", centroTrabajo=" + centroTrabajo + ", operario=" + operario
				+ ", idCentroTrabajo=" + idCentroTrabajo + ", idItemFab=" + idItemFab + ", idPerfil=" + idPerfil
				+ ", idItem=" + idItem + ", cantReportar=" + cantReportar + "]";
	}

}
