package com.almatec.controlpiso.utils;

import java.util.Objects;

public class ClaveAgrupacion {
	
	private String cliente;
    private String proyecto;
    private Integer idOp;
    
	public ClaveAgrupacion() {
		super();
	}
	public ClaveAgrupacion(String cliente, String proyecto, Integer idOp) {
		this.cliente = cliente;
        this.proyecto = proyecto;
        this.idOp = idOp;
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
	public Integer getIdOp() {
		return idOp;
	}
	public void setIdOp(Integer idOp) {
		this.idOp = idOp;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cliente, idOp, proyecto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClaveAgrupacion other = (ClaveAgrupacion) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(idOp, other.idOp)
				&& Objects.equals(proyecto, other.proyecto);
	}
	@Override
	public String toString() {
		return "ClaveAgrupacion [cliente=" + cliente + ", proyecto=" + proyecto + ", idOp=" + idOp + "]";
	}
	
}
