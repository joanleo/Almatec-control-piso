package com.almatec.controlpiso.integrapps.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OpCentroTrabajoDTO {
	
	private Integer idOp;
	private String op;
	private String cliente;
	private String proyecto;
	private Date fechaContraActual;
	private String esquemaPintura;
	private String zona;
	private List<ItemOpCtDTO> items = new ArrayList<>();
	
	public OpCentroTrabajoDTO() {
		super();
	}

	public OpCentroTrabajoDTO(Integer idOp2, String op, String cliente2, String proyecto2) {
		this.idOp = idOp2;
		this.op = op;
		this.cliente = cliente2;
		this.proyecto = proyecto2;

	}

	public Integer getIdOp() {
		return idOp;
	}

	public void setIdOp(Integer idOp) {
		this.idOp = idOp;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
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

	public List<ItemOpCtDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemOpCtDTO> items) {
		this.items = items;
	}

	public Date getFechaContraActual() {
		return fechaContraActual;
	}

	public void setFechaContraActual(Date fechaContraActual) {
		this.fechaContraActual = fechaContraActual;
	}

	public String getEsquemaPintura() {
		return esquemaPintura;
	}

	public void setEsquemaPintura(String esquemaPintura) {
		this.esquemaPintura = esquemaPintura;
	}
 
	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
 
	@Override
	public String toString() {
		return "OpCentroTrabajoDTO [idOp=" + idOp + ", op=" + op + ", cliente=" + cliente + ", proyecto=" + proyecto
				+ ", fechaContraActual=" + fechaContraActual + ", esquemaPintura=" + esquemaPintura + ", zona=" + zona
				+ ", items=" + items + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, esquemaPintura, fechaContraActual, idOp, items, op, proyecto, zona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpCentroTrabajoDTO other = (OpCentroTrabajoDTO) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(esquemaPintura, other.esquemaPintura)
				&& Objects.equals(fechaContraActual, other.fechaContraActual) && Objects.equals(idOp, other.idOp)
				&& Objects.equals(items, other.items) && Objects.equals(op, other.op)
				&& Objects.equals(proyecto, other.proyecto) && Objects.equals(zona, other.zona);
	}

	

}
