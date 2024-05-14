package com.almatec.controlpiso.erp.interfaces;

import java.time.LocalDateTime;

public interface DetalleTransferenciaInterface {
	
	String getf350_id_tipo_docto();
	Integer getf350_consec_docto();
	String getf350_usuario_creacion();
	LocalDateTime getf350_fecha_ts_creacion();

}
