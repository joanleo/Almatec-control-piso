package com.almatec.controlpiso.produccion.dtos;

import java.math.BigDecimal;

public class LoteInfoDTO {
    private BigDecimal disponible;
    private String descripcion;

    public LoteInfoDTO(BigDecimal disponible, String descripcion) {
        this.disponible = disponible;
        this.descripcion = descripcion;
    }

	public BigDecimal getDisponible() {
		return disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDisponible(BigDecimal disponible) {
		this.disponible = disponible;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
}