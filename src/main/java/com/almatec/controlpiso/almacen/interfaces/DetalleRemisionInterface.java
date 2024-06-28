package com.almatec.controlpiso.almacen.interfaces;

import java.util.Date;

public interface DetalleRemisionInterface {
	
	Long getIdDetalleRemision();
	Integer getCant();
	Date getFechaCreacion();
	Long getIdRemision();
	Long getIdItemOp();
	String getDescripcion();
	Integer getCantSol();
	Integer getCantCumplida();
	Integer getCantDespachada();
	Double getPeso();
	String getMarca();
	String getUndEmpaque();
}
