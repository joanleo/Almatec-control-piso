package com.almatec.controlpiso.integrapps.dtos;

public class SpecItemLoteDTO {
	
	private Integer codigo;
	private String um;
	private String lote;
	private String descripcion;
	private String bodega;
	private Integer numOp;
	
	public SpecItemLoteDTO() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getUm() {
		return um;
	}

	public String getLote() {
		return lote;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setCodigo(Integer codErp) {
		this.codigo = codErp;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	@Override
	public String toString() {
		return "SpecItemLoteDTO [codigo=" + codigo + ", um=" + um + ", lote=" + lote + ", descripcion=" + descripcion
				+ ", bodega=" + bodega + ", numOp=" + numOp + "]";
	}
	
}
