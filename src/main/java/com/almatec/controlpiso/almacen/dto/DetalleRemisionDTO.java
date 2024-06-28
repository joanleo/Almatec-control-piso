package com.almatec.controlpiso.almacen.dto;

public class DetalleRemisionDTO {

	private Long itemOp;
	private Integer cantidad;
	
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

	@Override
	public String toString() {
		return "DetalleRemisionDTO [itemOp=" + itemOp + ", cantidad=" + cantidad + "]";
	}	
	
}
