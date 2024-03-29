package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_items_lote_dispo")
public class VistaItemLoteDisponible {

	@Id
	@Column(name = "identificador_unico")
	private String idUnico;
	
	@Column(name = "f120_id")
	private Integer idItem;
	
	@Column(name = "f120_descripcion")
	private String descripcionItem;
	
	@Column(name = "f_id_unidad_inventario")
	private String um;
	
	@Column(name = "f_cant_existencia_1")
	private BigDecimal existencia;
	
	@Column(name = "f_cant_comprometida_1")
	private BigDecimal comprometida;
	
	@Column(name = "f_peso")
	private BigDecimal peso;
	
	@Column(name = "f_cant_disponible_1")
	private BigDecimal disponible;
	
	@Column(name = "f150_descripcion")
	private String bodega;
	
	@Column(name = "f_id_lote")
	private String lote;

	public VistaItemLoteDisponible() {
		super();
	}

	public String getIdUnico() {
		return idUnico;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public String getDescripcionItem() {
		return descripcionItem;
	}

	public String getUm() {
		return um;
	}

	public BigDecimal getExistencia() {
		return existencia;
	}

	public BigDecimal getComprometida() {
		return comprometida;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public BigDecimal getDisponible() {
		return disponible;
	}

	public String getBodega() {
		return bodega;
	}

	public String getLote() {
		return lote;
	}

	@Override
	public String toString() {
		return "VistaItemLoteDisponible [idUnico=" + idUnico + ", idItem=" + idItem + ", descripcionItem="
				+ descripcionItem + ", um=" + um + ", existencia=" + existencia + ", comprometida=" + comprometida
				+ ", peso=" + peso + ", disponible=" + disponible + ", bodega=" + bodega + ", lote=" + lote + "]";
	}
	
	

}
