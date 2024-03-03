package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.entities.Operario;

public class NovedadDTO {

	private Long IdItem;	
	private Integer idItemFab;	
	private Integer idPerfil;	
	private Integer idCentroTrabajo;	
	private Operario operario;	
	private Integer noConforme;	
	private Integer desperdicio;	
	private Integer sobrante;
	private String centroTrabajo;
	private Integer numOp;
	private String proyecto;
	private String ref;
	
	public NovedadDTO() {
		super();
	}

	public NovedadDTO(ReporteDTO reporte) {
		this.IdItem = reporte.getIdItem();
		this.idItemFab = reporte.getIdItemFab();
		this.idPerfil = reporte.getIdPerfil();
		this.idCentroTrabajo = reporte.getIdCentroTrabajo();
		this.operario = reporte.getOperario();
		this.centroTrabajo = reporte.getCentroTrabajo();
		this.numOp = reporte.getNumOp();
		this.proyecto = reporte.getProyecto();
		this.ref = reporte.getRef();
	}

	public Long getIdItem() {
		return IdItem;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public Operario getOperario() {
		return operario;
	}

	public Integer getNoConforme() {
		return noConforme;
	}

	public Integer getDesperdicio() {
		return desperdicio;
	}

	public Integer getSobrante() {
		return sobrante;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getProyecto() {
		return proyecto;
	}

	public String getRef() {
		return ref;
	}

	public void setIdItem(Long idItem) {
		IdItem = idItem;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public void setOperario(Operario operario) {
		this.operario = operario;
	}

	public void setNoConforme(Integer noConforme) {
		this.noConforme = noConforme;
	}

	public void setDesperdicio(Integer desperdicio) {
		this.desperdicio = desperdicio;
	}

	public void setSobrante(Integer sobrante) {
		this.sobrante = sobrante;
	}

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public void setNumOp(Integer numOP) {
		this.numOp = numOP;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	
	
}
