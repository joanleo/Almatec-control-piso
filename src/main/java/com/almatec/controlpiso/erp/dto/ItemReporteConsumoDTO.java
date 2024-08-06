package com.almatec.controlpiso.utils;

public class ItemReporteConsumoDTO {
	
	private Integer id;
	private Double cant;
	public Integer getId() {
		return id;
	}
	public Double getCant() {
		return cant;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCant(Double cant) {
		this.cant = cant;
	}
	public ItemReporteConsumoDTO() {
		super();
	}
	public ItemReporteConsumoDTO(Integer id, Double cant) {
		super();
		this.id = id;
		this.cant = cant;
	}
	@Override
	public String toString() {
		return "ItemReporteConsumoDTO [id=" + id + ", cant=" + cant + "]";
	}
	
	

}
