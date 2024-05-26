package com.almatec.controlpiso.integrapps.dtos;

public class LoteConCodigoDTO {

	private Integer codErp;
    private String loteErp;
    
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
}
