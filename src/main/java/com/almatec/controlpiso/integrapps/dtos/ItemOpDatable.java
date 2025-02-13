package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;

public class ItemOpDatable {

	private Long itemId;
	private String marca;
	private String descripcion;
	private Integer cant;
	private Double peso;
	private Integer cantPentiente;
	private Double pesoPendiente;
	private String color;
	private String plano;
	
	
	public ItemOpDatable() {
		super();
	}
	
	public ItemOpDatable(ItemOpInterface itemInterface) {
		this.itemId = itemInterface.getitem_id();
		this.marca = itemInterface.getmarca();
		this.descripcion = itemInterface.getdescripcion();
		this.cant = itemInterface.getcant_req().intValue();
		this.peso = itemInterface.getpeso_unitario().doubleValue();
		this.cantPentiente = cant - itemInterface.getcant_cumplida().intValue();
		BigDecimal pesoPendienteTemp = itemInterface.getpeso_unitario().multiply(BigDecimal.valueOf(cant))
		        .subtract(itemInterface.getpeso_unitario().multiply(BigDecimal.valueOf(itemInterface.getcant_cumplida())));
		this.pesoPendiente = pesoPendienteTemp.setScale(3, RoundingMode.HALF_UP).doubleValue();
		this.color = itemInterface.getpintura();
		this.plano = itemInterface.getruta_plano();
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
	public Integer getCant() {
		return cant;
	}
	public Double getPeso() {
		return peso;
	}
	public Integer getCantPentiente() {
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
	public void setCant(Integer cant) {
		this.cant = cant;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public void setCantPentiente(Integer cantPentiente) {
		this.cantPentiente = cantPentiente;
	}
	public void setPesoPendiente(Double pesoPendiente) {
		this.pesoPendiente = pesoPendiente;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	@Override
	public String toString() {
		return "ItemOpDatable [itemId=" + itemId + ", marca=" + marca + ", descripcion=" + descripcion + ", cant="
				+ cant + ", peso=" + peso + ", cantPentiente=" + cantPentiente + ", pesoPendiente=" + pesoPendiente
				+ ", color=" + color + ", plano=" + plano + "]";
	}
}
