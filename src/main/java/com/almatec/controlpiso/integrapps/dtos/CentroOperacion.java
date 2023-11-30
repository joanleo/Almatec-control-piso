package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.interfaces.CentroOperacionInterface;

public class CentroOperacion {

	String id;
	Integer idCia;
	String descripcion;
	public CentroOperacion(CentroOperacionInterface centroOperacionInterface) {
		super();
		this.id = centroOperacionInterface.getf285_id();
		this.idCia = centroOperacionInterface.getf285_id_cia();
		this.descripcion = centroOperacionInterface.getf285_descripcion();
	}
	public CentroOperacion() {
		super();
	}
	public String getId() {
		return id;
	}
	public Integer getIdCia() {
		return idCia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	
	

}
