package com.almatec.controlpiso.integrapps.interfaces;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ListaMInterface {
	
	Integer getId_Mp_Op();
	Integer getid_op_ia();
	Integer getCod_Erp();
	String getUnd_Erp();
	BigDecimal getCant_Req_Ini();
	BigDecimal getCant_Req_Act();
	BigDecimal getCant_Entrega();
	BigDecimal getCant_Existen();
	BigDecimal getPeso_Unt();
	Integer getId_Usu_Sol();
	String getTipo();
	LocalDateTime getFecha();
	Integer getEstado();
	Boolean getEnv_LM();
	String getf120_descripcion();


}
