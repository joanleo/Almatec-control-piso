package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;

import com.almatec.controlpiso.integrapps.entities.Operario;

public class ReporteDTO {
	private String proyecto;
	private Integer numOp;
	private String ref;
	private BigDecimal cantSol;
	private Integer cantFab;
	private String centroTrabajo;
	private Operario operario;
	private Integer idCentroTrabajo;
	private Integer idItemFab;
	private Integer idParte;
	private Long idItem;
	private Integer cantReportar;
	private String lote;
	private String color;
	
	public String getProyecto() {
		return proyecto;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getRef() {
		return ref;
	}

	public BigDecimal getCantSol() {
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

	public void setCantSol(BigDecimal cant) {
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

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
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

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Integer getIdParte() {
		return idParte;
	}

	public void setIdParte(Integer idParte) {
		this.idParte = idParte;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ReporteDTO [proyecto=" + proyecto + ", numOp=" + numOp + ", ref=" + ref + ", cantSol=" + cantSol
				+ ", cantFab=" + cantFab + ", centroTrabajo=" + centroTrabajo + ", operario=" + operario
				+ ", idCentroTrabajo=" + idCentroTrabajo + ", idItemFab=" + idItemFab + ", idParte=" + idParte
				+ ", idItem=" + idItem + ", cantReportar=" + cantReportar + ", lote=" + lote + ", color=" + color + "]";
	}

}
