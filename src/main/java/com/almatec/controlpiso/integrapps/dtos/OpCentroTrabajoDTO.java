package com.almatec.controlpiso.integrapps.dtos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OpCentroTrabajoDTO {
	
	private Integer idOp;
	private String tipoOp;
	private Integer numOp;
	private String cliente;
	private String proyecto;
	private Set<ItemDTO> items = new HashSet<>();
	
	public OpCentroTrabajoDTO() {
		super();
	}

	public OpCentroTrabajoDTO(Integer idOp2, String tipoOp2, Integer numOp2, String cliente2, String proyecto2) {
		this.idOp = idOp2;
		this.tipoOp = tipoOp2;
		this.numOp = numOp2;
		this.cliente = cliente2;
		this.proyecto = proyecto2;

	}

	public Integer getIdOp() {
		return idOp;
	}

	public void setIdOp(Integer idOp) {
		this.idOp = idOp;
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

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public Set<ItemDTO> getItems() {
		return items;
	}

	public void setItems(Set<ItemDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OpCentroTrabajoDTO [idOp=" + idOp + ", tipoOp=" + tipoOp + ", numOp=" + numOp + ", cliente=" + cliente
				+ ", proyecto=" + proyecto + ", items=" + items + "]";
	}

}