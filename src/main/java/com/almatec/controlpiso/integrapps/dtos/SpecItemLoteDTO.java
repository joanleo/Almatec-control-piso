package com.almatec.controlpiso.integrapps.dtos;

public class SpecItemLoteDTO {
	
	private Integer codigo;
	private String um;
	private String lote;
	private String descripcion;
	
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

	@Override
	public String toString() {
		return "SpecItemLoteDTO [codErp=" + codigo + ", um=" + um + ", lote=" + lote + ", descripcion=" + descripcion
				+ "]";
	}
	
	
}
