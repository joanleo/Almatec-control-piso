package com.almatec.controlpiso.integrapps.dtos;

public class DataItemImprimirDTO {

	private Integer idItemFab;
	private Integer idItemOp;
	private Integer idOp;
	private String tipoEtiqueta;
	private Integer cantEtiquetas;
	
	public DataItemImprimirDTO() {
		super();
	}

	public Integer getIdOp() {
		return idOp;
	}

	public String getTipoEtiqueta() {
		return tipoEtiqueta;
	}

	public Integer getCantEtiquetas() {
		return cantEtiquetas;
	}

	public void setIdOp(Integer idOp) {
		this.idOp = idOp;
	}

	public void setTipoEtiqueta(String tipoEtiqueta) {
		this.tipoEtiqueta = tipoEtiqueta;
	}

	public void setCantEtiquetas(Integer cantEtiquetas) {
		this.cantEtiquetas = cantEtiquetas;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public Integer getIdItemOp() {
		return idItemOp;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public void setIdItemOp(Integer idItemOp) {
		this.idItemOp = idItemOp;
	}

	@Override
	public String toString() {
		return "DataItemImprimirDTO [idItemFab=" + idItemFab + ", idItemOp=" + idItemOp + ", idOp=" + idOp
				+ ", tipoEtiqueta=" + tipoEtiqueta + ", cantEtiquetas=" + cantEtiquetas + "]";
	}

	
}
