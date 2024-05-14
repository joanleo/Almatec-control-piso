package com.almatec.controlpiso.integrapps.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OpDTO {

	private Integer idOp;
	private String tipoOp;
	private Integer numOp;
	private String cliente;
	private String proyecto;
	private Date fechaContraActual;
	private String esquemaPintura;
	private List<ItemOpDTO> itemsOps = new ArrayList<>();
	
	public OpDTO() {
		super();
	}

	public Integer getIdOp() {
		return idOp;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getCliente() {
		return cliente;
	}

	public String getProyecto() {
		return proyecto;
	}

	public Date getFechaContraActual() {
		return fechaContraActual;
	}

	public String getEsquemaPintura() {
		return esquemaPintura;
	}

	public List<ItemOpDTO> getItemsOps() {
		return itemsOps;
	}

	public void setIdOp(Integer idOp) {
		this.idOp = idOp;
	}

	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setFechaContraActual(Date fechaContraActual) {
		this.fechaContraActual = fechaContraActual;
	}

	public void setEsquemaPintura(String esquemaPintura) {
		this.esquemaPintura = esquemaPintura;
	}

	public void setItemsOps(List<ItemOpDTO> items) {
		this.itemsOps = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, esquemaPintura, fechaContraActual, idOp, itemsOps, numOp, proyecto, tipoOp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpDTO other = (OpDTO) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(esquemaPintura, other.esquemaPintura)
				&& Objects.equals(fechaContraActual, other.fechaContraActual) && Objects.equals(idOp, other.idOp)
				&& Objects.equals(itemsOps, other.itemsOps) && Objects.equals(numOp, other.numOp)
				&& Objects.equals(proyecto, other.proyecto) && Objects.equals(tipoOp, other.tipoOp);
	}

	@Override
	public String toString() {
		return "OpDTO [idOp=" + idOp + ", tipoOp=" + tipoOp + ", numOp=" + numOp + ", cliente=" + cliente
				+ ", proyecto=" + proyecto + ", fechaContraActual=" + fechaContraActual + ", esquemaPintura="
				+ esquemaPintura + ", items=" + itemsOps + "]";
	}
}
