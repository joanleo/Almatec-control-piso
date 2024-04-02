package com.almatec.controlpiso.erp.dto;

public class RutaDTO {
	
	private String f808_id;
	private Integer f809_numero_operacion;
	private String f809_descripcion;
	private String f809_id_ctrabajo;
	private Double f809_cantidad_base;
	private Double f809_horas_maquina;
	
	public RutaDTO() {
		super();
	}

	public String getF808_id() {
		return f808_id;
	}

	public Integer getF809_numero_operacion() {
		return f809_numero_operacion;
	}

	public String getF809_descripcion() {
		return f809_descripcion;
	}

	public String getF809_id_ctrabajo() {
		return f809_id_ctrabajo;
	}

	public Double getF809_cantidad_base() {
		return f809_cantidad_base;
	}

	public Double getF809_horas_maquina() {
		return f809_horas_maquina;
	}

	public void setF808_id(String f808_id) {
		this.f808_id = f808_id;
	}

	public void setF809_numero_operacion(Integer f809_numero_operacion) {
		this.f809_numero_operacion = f809_numero_operacion;
	}

	public void setF809_descripcion(String f809_descripcion) {
		this.f809_descripcion = f809_descripcion;
	}

	public void setF809_id_ctrabajo(String f809_id_ctrabajo) {
		this.f809_id_ctrabajo = f809_id_ctrabajo;
	}

	public void setF809_cantidad_base(Double f809_cantidad_base) {
		this.f809_cantidad_base = f809_cantidad_base;
	}

	public void setF809_horas_maquina(Double f809_horas_maquina) {
		this.f809_horas_maquina = f809_horas_maquina;
	}

	@Override
	public String toString() {
		return "RutaDTO [f808_id=" + f808_id +", f809_numero_operacion=" + f809_numero_operacion + ", f809_descripcion=" + f809_descripcion + ", f809_id_ctrabajo=" + f809_id_ctrabajo + ", f809_cantidad_base="
				+ f809_cantidad_base + ", f809_horas_maquina=" + f809_horas_maquina + "]";
	}
	
	
}
