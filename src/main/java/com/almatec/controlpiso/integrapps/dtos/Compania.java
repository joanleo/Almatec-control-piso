package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.interfaces.CompaniaErp;

public class Compania {
	
	private Integer id;
	private String nombre;
	
	public Compania() {
		super();
	}
	
	public Compania(CompaniaErp companiaErp) {
		this.id = companiaErp.getf010_id();
		this.nombre = companiaErp.getf010_razon_social();
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	

}
