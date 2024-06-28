package com.almatec.controlpiso.almacen.interfaces;

import java.util.Date;

public interface EncabezadoRemision {

	Long getIdRemision();
	Date getFechaCreacion();
	String getOp();
	String getCliente();
	String getProyecto();
	String getUsuarioCrea();
	String getContacto();
	String getDireccion();
	String getCelular();
	String getCiudad();
	
}
