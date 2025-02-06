package com.almatec.controlpiso.ingenieria;

import java.util.Date;

public interface MemoWithOP {

	Long getIdMemo();
	Date getFechaCreacion();
	Integer getIdEstado();
	String getUsuarioCrea();
	String getUsuarioAprueba();
	Integer getIdOpIa();
	String getOp();
	String getCliente();
	String getProyecto();
	String getZona();
}
