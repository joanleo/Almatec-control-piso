package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pro_reporte_pieza_ct")
public class ReportePiezaCt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reporte_id")
	private Integer id;
	
	@Column(name = "Item_fab_id")
	private Integer idItemFab;
	
	@Column(name = "item_parte_id")
	private Integer idParte;
	
	@Column(name = "C_centrotrabajo_id")
	private Integer idCentroT;
	
	@Column(name = "C_prooperario_id")
	private Integer idOperario;
	
	private Integer cant;
	
	@Column(name = "doc_erp")
	private Integer docErp=0;
	
	@Column(name = "fecha_erp")
	private LocalDateTime fechaErp; 
	
	@Column(name = "fecha_ceacion")
	private LocalDateTime fechaCreacion;
	
	private Integer estado=0;
	
	@Column(name = "item_id")
	private Long itemId;
	
	private String lote;
	
	@Column(name = "is_consume")
	private Boolean isConsume=false;
	
	@Column(name = "is_tep")
	private Boolean isTep=false;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public Integer getIdCentroT() {
		return idCentroT;
	}

	public void setIdCentroT(Integer idCentroT) {
		this.idCentroT = idCentroT;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public Integer getCant() {
		return cant;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}

	public Integer getDocErp() {
		return docErp;
	}

	public void setDocErp(Integer docErp) {
		this.docErp = docErp;
	}

	public LocalDateTime getFechaErp() {
		return fechaErp;
	}

	public void setFechaErp(LocalDateTime fechaErp) {
		this.fechaErp = fechaErp;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public Boolean getIsConsume() {
		return isConsume;
	}

	public void setIsConsume(Boolean isConsume) {
		this.isConsume = isConsume;
	}

	public Boolean getIsTep() {
		return isTep;
	}

	public void setIsTep(Boolean isTep) {
		this.isTep = isTep;
	}

	public ReportePiezaCt() {
		super();
	}

	@Override
	public String toString() {
		return "ReportePiezaCt [id=" + id + ", idItemFab=" + idItemFab + ", idParte=" + idParte + ", idCentroT="
				+ idCentroT + ", idOperario=" + idOperario + ", cant=" + cant + ", docErp=" + docErp + ", fechaErp="
				+ fechaErp + ", fechaCreacion=" + fechaCreacion + ", estado=" + estado + ", itemId=" + itemId
				+ ", lote=" + lote + ", isConsume=" + isConsume + ", isTep=" + isTep + "]";
	}
}
