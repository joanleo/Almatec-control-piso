package com.almatec.controlpiso.erp.dto;

public class ListaMaterialesDTO {	
	private Integer f820_id;
	private Integer f820_id_hijo;
	private Double f820_cant_base;
	private Double f820_cant_requerida;
	private String f820_id_bodega;
	private Integer f820_secuencia ;	
	
	public ListaMaterialesDTO() {
		super();
	}
	
	public Integer getF820_id() {
		return f820_id;
	}
	public Integer getF820_id_hijo() {
		return f820_id_hijo;
	}
	public Double getF820_cant_base() {
		return f820_cant_base;
	}
	public Double getF820_cant_requerida() {
		return f820_cant_requerida;
	}
	public String getF820_id_bodega() {
		return f820_id_bodega;
	}
	public void setF820_id(Integer f820_id) {
		this.f820_id = f820_id;
	}
	public void setF820_id_hijo(Integer f820_id_hijo) {
		this.f820_id_hijo = f820_id_hijo;
	}
	public void setF820_cant_base(Double f820_cant_base) {
		this.f820_cant_base = f820_cant_base;
	}
	public void setF820_cant_requerida(Double f820_cant_requerida) {
		this.f820_cant_requerida = f820_cant_requerida;
	}
	public void setF820_id_bodega(String f820_id_bodega) {
		this.f820_id_bodega = f820_id_bodega;
	}

	public Integer getF820_secuencia() {
		return f820_secuencia;
	}

	public void setF820_secuencia(Integer f820_secuencia) {
		this.f820_secuencia = f820_secuencia;
	}

	@Override
	public String toString() {
		return "ListaMaterialesDTO [f820_id=" + f820_id + ", f820_id_hijo=" + f820_id_hijo + ", f820_cant_base="
				+ f820_cant_base + ", f820_cant_requerida=" + f820_cant_requerida + ", f820_id_bodega=" + f820_id_bodega
				+ ", f820_secuencia=" + f820_secuencia + "]";
	}
	
}
