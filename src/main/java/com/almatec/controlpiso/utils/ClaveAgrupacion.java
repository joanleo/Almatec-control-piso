package com.almatec.controlpiso.utils;

import java.util.Objects;

public class ClaveAgrupacion {
	
	private String cliente;
    private String proyecto;
    
	public ClaveAgrupacion() {
		super();
	}
	public ClaveAgrupacion(String cliente, String proyecto) {
		this.cliente = cliente;
        this.proyecto = proyecto;
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
	@Override
	public int hashCode() {
		return Objects.hash(cliente, proyecto);
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
		return Objects.equals(cliente, other.cliente) && Objects.equals(proyecto, other.proyecto);
	}
	@Override
	public String toString() {
		return "ClaveAgrupacion [cliente=" + cliente + ", proyecto=" + proyecto + "]";
	}
}
