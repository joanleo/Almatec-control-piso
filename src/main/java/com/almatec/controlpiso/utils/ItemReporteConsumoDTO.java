package com.almatec.controlpiso.utils;

public class ItemReporteConsumoDTO {
	
	private Integer id;
	private Integer cant;
	public Integer getId() {
		return id;
	}
	public Integer getCant() {
		return cant;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCant(Integer cant) {
		this.cant = cant;
	}
	public ItemReporteConsumoDTO() {
		super();
	}
	public ItemReporteConsumoDTO(Integer id, Integer cant) {
		super();
		this.id = id;
		this.cant = cant;
	}
	@Override
	public String toString() {
		return "ItemReporteConsumoDTO [id=" + id + ", cant=" + cant + "]";
	}
	
	

}
