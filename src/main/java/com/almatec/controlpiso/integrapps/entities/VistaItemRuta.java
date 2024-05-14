package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_item_ruta")
public class VistaItemRuta {
	
	@Id
	@Column(name = "Item_fab_Id")
	private Integer idItem;
	
	@Column(name = "Item_Id_papa")
	private Integer idItemPapa;
	
	@Column(name = "item_desc")
	private String descripcion;
	
	@Column(name = "C_centrotrabajo_id")
	private Integer idCentroTrabajo;
	
	@Column(name = "A_nombre")
	private String nombreCentroTrabajo;
	
	@Column(name = "codigo_erp")
	private Integer codErp;

	public VistaItemRuta() {
		super();
	}

	public Integer getIdItem() {
		return idItem;
	}

	public Integer getIdItemPapa() {
		return idItemPapa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public String getNombreCentroTrabajo() {
		return nombreCentroTrabajo;
	}

	public Integer getCodErp() {
		return codErp;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setIdItemPapa(Integer idItemPapa) {
		this.idItemPapa = idItemPapa;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public void setNombreCentroTrabajo(String nombreCentroTrabajo) {
		this.nombreCentroTrabajo = nombreCentroTrabajo;
	}

	public void setCodErp(Integer codErp) {
		this.codErp = codErp;
	}

	@Override
	public String toString() {
		return "VistaItemItemRuta [idItem=" + idItem + ", idItemPapa=" + idItemPapa + ", descripcion=" + descripcion
				+ ", idCentroTrabajo=" + idCentroTrabajo + ", nombreCentroTrabajo=" + nombreCentroTrabajo + ", codErp="
				+ codErp + "]";
	}
	
	
	


}
