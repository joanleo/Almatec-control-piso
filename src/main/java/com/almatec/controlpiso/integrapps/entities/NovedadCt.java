package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pro_novedades_ct")
public class NovedadCt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "novedad_id")
	private Integer idNovedad;
	
	@Column(name = "item_id")
	private Long IdItem;
	
	@Column(name = "Item_fab_id")
	private Integer idItmFab;
	
	@Column(name = "item_parte_id")
	private Integer idParte;
	
	@Column(name = "C_centrotrabajo_id")
	private Integer idCentroTrabajo;
	
	@Column(name = "C_prooperario_id")
	private Integer idOperario;
	
	@Column(name = "no_conforme")
	private Integer noConforme;
	
	private Integer desperdicio;
	
	private Integer sobrante;
	
	private String lote;
	
	@Column(name = "ind_registro_siesa")
	private Boolean enviadoErp = false;
	
	@Column(name = "cod_erp_mp")
	private Integer codErpMp;

	public NovedadCt() {
		super();
	}

	public Integer getIdNovedad() {
		return idNovedad;
	}

	public Long getIdItem() {
		return IdItem;
	}

	public Integer getIdItmFab() {
		return idItmFab;
	}


	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public Integer getIdOperario() {
		return idOperario;
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

	public void setIdNovedad(Integer idNovedad) {
		this.idNovedad = idNovedad;
	}

	public void setIdItem(Long idItem) {
		IdItem = idItem;
	}

	public void setIdItmFab(Integer idItmFab) {
		this.idItmFab = idItmFab;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
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

	public Boolean getEnviadoErp() {
		return enviadoErp;
	}

	public void setEnviadoErp(Boolean enviadoErp) {
		this.enviadoErp = enviadoErp;
	}

	@Override
	public String toString() {
		return "NovedadCt [idNovedad=" + idNovedad + ", IdItem=" + IdItem + ", idItmFab=" + idItmFab + ", idParte="
				+ idParte + ", idCentroTrabajo=" + idCentroTrabajo + ", idOperario=" + idOperario + ", noConforme="
				+ noConforme + ", desperdicio=" + desperdicio + ", sobrante=" + sobrante + ", lote=" + lote
				+ ", enviadoErp=" + enviadoErp + ", codErpMp=" + codErpMp + "]";
	}
}
