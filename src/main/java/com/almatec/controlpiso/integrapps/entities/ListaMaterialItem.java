package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "z_item_materia_prima")
public class ListaMaterialItem {
	
	@Id
	@Column(name = "id_item_material")
	private Integer IdListaMateriaItem;
	
	@Column(name = "id_item")
	private Integer idItem;
	
	@Column(name = "id_materia_prima")
	private Integer idItemMAteriaPrima;
	
	private Float cantidad;
	
	@Column(name = "activo")
	private Boolean isActivo;

	public ListaMaterialItem() {
		super();
	}

	public Integer getIdListaMateriaItem() {
		return IdListaMateriaItem;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public Integer getIdItemMAteriaPrima() {
		return idItemMAteriaPrima;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIdListaMateriaItem(Integer idListaMateriaItem) {
		IdListaMateriaItem = idListaMateriaItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setIdItemMAteriaPrima(Integer idItemMAteriaPrima) {
		this.idItemMAteriaPrima = idItemMAteriaPrima;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	@Override
	public String toString() {
		return "ListaMaterialItem [IdListaMateriaItem=" + IdListaMateriaItem + ", idItem=" + idItem
				+ ", idItemMAteriaPrima=" + idItemMAteriaPrima + ", cantidad=" + cantidad + ", isActivo=" + isActivo
				+ "]";
	}
	
	


}
