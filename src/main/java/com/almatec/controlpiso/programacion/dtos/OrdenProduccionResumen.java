package com.almatec.controlpiso.programacion.dtos;

import java.util.ArrayList;
import java.util.List;


public class OrdenProduccionResumen {
	
	private Integer idOpIntegrapps;	
	private String cliente;	
	private String op;	
	private String esquemaPintura;	
	private String un;
	private String zona;
	private List<ItemDTO> items = new ArrayList<>();
	
	public OrdenProduccionResumen() {
		super();
	}

	public Integer getIdOpIntegrapps() {
		return idOpIntegrapps;
	}

	public String getCliente() {
		return cliente;
	}

	public String getOp() {
		return op;
	}

	public String getEsquemaPintura() {
		return esquemaPintura;
	}

	public String getUn() {
		return un;
	}

	public String getZona() {
		return zona;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setIdOpIntegrapps(Integer idOpIntegrapps) {
		this.idOpIntegrapps = idOpIntegrapps;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public void setEsquemaPintura(String esquemaPintura) {
		this.esquemaPintura = esquemaPintura;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrdenProduccionResumen [idOpIntegrapps=" + idOpIntegrapps + ", cliente=" + cliente + ", op=" + op
				+ ", esquemaPintura=" + esquemaPintura + ", un=" + un + ", zona=" + zona + ", items=" + items + "]";
	}
	
	

}
