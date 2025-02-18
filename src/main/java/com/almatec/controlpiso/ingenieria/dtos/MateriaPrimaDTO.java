package com.almatec.controlpiso.ingenieria.dtos;

import java.math.BigDecimal;

public class MateriaPrimaDTO {
    private Integer codErp;
    private String descripcion;
    private BigDecimal cantidad;
    private String unidad;
    
	public MateriaPrimaDTO() {
		super();
	}


	public String getDescripcion() {
		return descripcion;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Integer getCodErp() {
		return codErp;
	}


	public void setCodErp(Integer codErp) {
		this.codErp = codErp;
	}


	@Override
	public String toString() {
		return "MateriaPrimaDTO [codErp=" + codErp + ", descripcion=" + descripcion + ", cantidad=" + cantidad
				+ ", unidad=" + unidad + "]";
	}
    
    
}
