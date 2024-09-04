package com.almatec.controlpiso.almacen.dto;

import com.almatec.controlpiso.integrapps.entities.ItemOp;

public class DetalleRemisionDTO {

	private Long itemOp;
	private Integer cantidad;
	private ItemOp item;
	
	public DetalleRemisionDTO() {
		super();
	}

	public Long getItemOp() {
		return itemOp;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setItemOp(Long itemOp) {
		this.itemOp = itemOp;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public ItemOp getItem() {
		return item;
	}

	public void setItem(ItemOp item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "DetalleRemisionDTO [itemOp=" + itemOp + ", cantidad=" + cantidad + "]";
	}	
	
}
