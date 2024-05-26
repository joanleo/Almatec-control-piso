package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;

public class ItemOpDatable {

	private Long itemId;
	private String marca;
	private String descripcion;
	private Double cant;
	private Double peso;
	private Double cantPentiente;
	private Double pesoPendiente;
	private String color;
	
	
	public ItemOpDatable() {
		super();
	}
	
	public ItemOpDatable(ItemOpInterface itemInterface) {
		itemId = itemInterface.getitem_id();
		marca = itemInterface.getmarca();
		descripcion = itemInterface.getdescripcion();
		cant = itemInterface.getcant_req();
		peso = itemInterface.getpeso_unitario().doubleValue();
		cantPentiente = cant - itemInterface.getcant_cumplida();
		BigDecimal pesoPendienteTemp = itemInterface.getpeso_unitario().multiply(BigDecimal.valueOf(cant))
		        .subtract(itemInterface.getpeso_unitario().multiply(BigDecimal.valueOf(itemInterface.getcant_cumplida())));
		pesoPendiente = pesoPendienteTemp.setScale(3, RoundingMode.HALF_UP).doubleValue();
		color = itemInterface.getpintura();
	}
	
	

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getMarca() {
		return marca;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Double getCant() {
		return cant;
	}
	public Double getPeso() {
		return peso;
	}
	public Double getCantPentiente() {
		return cantPentiente;
	}
	public Double getPesoPendiente() {
		return pesoPendiente;
	}
	public String getColor() {
		return color;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setCant(Double cant) {
		this.cant = cant;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public void setCantPentiente(Double cantPentiente) {
		this.cantPentiente = cantPentiente;
	}
	public void setPesoPendiente(Double pesoPendiente) {
		this.pesoPendiente = pesoPendiente;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ItemOpDatable [itemId=" + itemId + ", marca=" + marca + ", descripcion=" + descripcion + ", cant="
				+ cant + ", peso=" + peso + ", cantPentiente=" + cantPentiente + ", pesoPendiente=" + pesoPendiente
				+ ", color=" + color + "]";
	}
	
	
	
}
