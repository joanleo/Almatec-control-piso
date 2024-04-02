package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_novedades")
public class VistaNovedades {

	@Id
	@Column(name = "novedad_id")
	private Integer idNovedad;
	
	@Column(name = "item_id")
	private Long idItem;
	
	@Column(name = "Item_fab_id")
	private Integer idItemFab;
	
	@Column(name = "item_perf_id")
	private Integer idPerfil;
	
	@Column(name = "C_centrotrabajo_id")
	private Integer idCentroTrabajo;
	
	@Column(name = "C_prooperario_id")	
	private Integer idOperario;
	
	@Column(name = "fecha_creacion")
	private Date fechaCreachion;
	
	@Column(name = "no_conforme")
	private Integer noConforme;
	
	private Integer desperdicio;
	
	private Integer sobrante;
	
	@Column(name = "ind_registro_siesa")
	private Boolean isRegistered;
	
	@Column(name = "item_desc")
	private String descripcionItem;
	
	@Column(name = "item_perf_desc")
	private String descripcionPerfil;
	
	@Column(name = "Num_Op")
	private Integer numOp;
	
	@Column(name = "codigo_erp")
	private Integer codigoErpItem;
	
	@Column(name = "item_perf_cod")
	private Integer codigoErpPerfil;
	
	@Column(name = "Und_Neg")
	private String proyecto;
	
	@Column(name = "A_Operario_Nombre")
	private String nombreOperario;

	public VistaNovedades() {
		super();
	}

	public Integer getIdNovedad() {
		return idNovedad;
	}

	public Long getIdItem() {
		return idItem;
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

	public Integer getIdOperario() {
		return idOperario;
	}

	public Date getFechaCreachion() {
		return fechaCreachion;
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

	public Boolean getIsRegistered() {
		return isRegistered;
	}

	public String getDescripcionItem() {
		return descripcionItem;
	}

	public String getDescripcionPerfil() {
		return descripcionPerfil;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public Integer getCodigoErpItem() {
		return codigoErpItem;
	}

	public Integer getCodigoErpPerfil() {
		return codigoErpPerfil;
	}

	public String getProyecto() {
		return proyecto;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}

	public void setIdNovedad(Integer idNovedad) {
		this.idNovedad = idNovedad;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
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

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public void setFechaCreachion(Date fechaCreachion) {
		this.fechaCreachion = fechaCreachion;
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

	public void setIsRegistered(Boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}

	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public void setCodigoErpItem(Integer codigoErpItem) {
		this.codigoErpItem = codigoErpItem;
	}

	public void setCodigoErpPerfil(Integer codigoErpPerfil) {
		this.codigoErpPerfil = codigoErpPerfil;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setNombreOperario(String nombreOperario) {
		this.nombreOperario = nombreOperario;
	}

	@Override
	public String toString() {
		return "VistaNovedades [idNovedad=" + idNovedad + ", idItem=" + idItem + ", idItemFab=" + idItemFab
				+ ", idPerfil=" + idPerfil + ", idCentroTrabajo=" + idCentroTrabajo + ", idOperario=" + idOperario
				+ ", fechaCreachion=" + fechaCreachion + ", noConforme=" + noConforme + ", desperdicio=" + desperdicio
				+ ", sobrante=" + sobrante + ", isRegistered=" + isRegistered + ", descripcionItem=" + descripcionItem
				+ ", descripcionPerfil=" + descripcionPerfil + ", numOp=" + numOp + ", codigoErpItem=" + codigoErpItem
				+ ", codigoErpPerfil=" + codigoErpPerfil + ", proyecto=" + proyecto + ", nombreOperario="
				+ nombreOperario + "]";
	}
	

}
