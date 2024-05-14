package com.almatec.controlpiso.integrapps.dtos;

public class OpProduccionDTO {
	private String tipoOp;
	private Integer numOp;
	private String un;
	private String zona;
	private String esquemaPintura;
	private String idProyecto;
	private String cliente;
	private String itemRef;
	private String itemDescripcion;
	private Double cant;
	
	public OpProduccionDTO() {
		super();
	}
	public String getTipoOp() {
		return tipoOp;
	}
	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}
	public Integer getNumOp() {
		return numOp;
	}
	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}
	public String getUn() {
		return un;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getEsquemaPintura() {
		return esquemaPintura;
	}
	public void setEsquemaPintura(String esquemaPintura) {
		this.esquemaPintura = esquemaPintura;
	}
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getItemRef() {
		return itemRef;
	}
	public void setItemRef(String itemRef) {
		this.itemRef = itemRef;
	}
	public String getItemDescripcion() {
		return itemDescripcion;
	}
	public void setItemDescripcion(String itemDescripcion) {
		this.itemDescripcion = itemDescripcion;
	}
	public Double getCant() {
		return cant;
	}
	public void setCant(Double cant) {
		this.cant = cant;
	} 
	@Override
	public String toString() {
		return "OpProduccionDTO [tipoOp=" + tipoOp + ", numOp=" + numOp + ", un=" + un + ", zona=" + zona
				+ ", esquemaPintura=" + esquemaPintura + ", idProyecto=" + idProyecto + ", cliente=" + cliente
				+ ", itemRef=" + itemRef + ", itemDescripcion=" + itemDescripcion + ", cant=" + cant + "]";
	}

}
