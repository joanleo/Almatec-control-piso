package com.almatec.controlpiso.integrapps.dtos;

public class PiezaOperarioDTO {
	
	private Integer idProceso;
	
	private Integer idOperario;
	
	private Integer idItemOp;
	
	private Integer idItem;
	
	private Boolean isComponente;
	
	private Boolean estaActivo;

	public PiezaOperarioDTO() {
		super();
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public Integer getIdItemOp() {
		return idItemOp;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public Boolean getIsComponente() {
		return isComponente;
	}

	public Boolean getEstaActivo() {
		return estaActivo;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public void setIdItemOp(Integer idItemOp) {
		this.idItemOp = idItemOp;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setIsComponente(Boolean isComponente) {
		this.isComponente = isComponente;
	}

	public void setEstaActivo(Boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

	@Override
	public String toString() {
		return "PiezaOperarioDTO [idProceso=" + idProceso + ", idOperario=" + idOperario + ", idItemOp=" + idItemOp
				+ ", idItem=" + idItem + ", isComponente=" + isComponente + ", estaActivo=" + estaActivo + "]";
	}

	

}

