package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;

public class LoteConCodigoDTO {

	private Integer codErp;
    private String loteErp;
    private BigDecimal disponible;
    
	public LoteConCodigoDTO() {
		super();
	}

	public LoteConCodigoDTO(Integer codErp, String loteErp) {
		super();
		this.codErp = codErp;
		this.loteErp = loteErp;
	}

	public Integer getCodErp() {
		return codErp;
	}

	public String getLoteErp() {
		return loteErp;
	}

	public void setCodErp(Integer codErp) {
		this.codErp = codErp;
	}

	public void setLoteErp(String loteErp) {
		this.loteErp = loteErp;
	}

	public BigDecimal getDisponible() {
		return disponible;
	}

	public void setDisponible(BigDecimal disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		return "LoteConCodigoDTO [codErp=" + codErp + ", loteErp=" + loteErp + ", disponible=" + disponible + "]";
	}
}
