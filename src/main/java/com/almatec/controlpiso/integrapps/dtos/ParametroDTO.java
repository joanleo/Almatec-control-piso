package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.entities.Parametro;

public class ParametroDTO {
	
	private Integer id;
	private String nombre;
	private String valor;

	public ParametroDTO(Parametro item) {
	
		this.id = item.getId();
		this.nombre = item.getNombre();
		this.valor = item.getValor();
	}

	public ParametroDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ParametroDTO [id=" + id + ", nombre=" + nombre + ", valor=" + valor + "]";
	}

}
