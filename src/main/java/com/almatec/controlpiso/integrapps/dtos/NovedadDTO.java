package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.entities.Operario;

public class NovedadDTO {

	private Long IdItem;	
	private Integer idItemFab;	
	private Integer idParte;	
	private Integer idCentroTrabajo;	
	private Operario operario;	
	private Integer noConforme;	
	private Integer desperdicio;	
	private Double sobrante;
	private String centroTrabajo;
	private Integer numOp;
	private String proyecto;
	private String ref;
	private String lote;
	private Integer codErpMp;
	private Integer piezasAdicionales;
	
	public NovedadDTO() {
		super();
	}

	public NovedadDTO(ReporteDTO reporte) {
		this.IdItem = reporte.getIdItem();
		this.idItemFab = reporte.getIdItemFab();
		this.idParte = reporte.getIdParte();
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

	public Double getSobrante() {
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

	public void setSobrante(Double sobrante) {
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

	public Integer getIdParte() {
		return idParte;
	}

	public void setIdParte(Integer idParte) {
		this.idParte = idParte;
	}

	public String getLote() {
		return lote;
	}

	public Integer getCodErpMp() {
		return codErpMp;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public void setCodErpMp(Integer codErpMp) {
		this.codErpMp = codErpMp;
	}

	public Integer getPiezasAdicionales() {
		return piezasAdicionales;
	}

	public void setPiezasAdicionales(Integer piezasAdicionales) {
		this.piezasAdicionales = piezasAdicionales;
	}
	
	
}
