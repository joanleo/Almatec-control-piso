package com.almatec.controlpiso.integrapps.interfaces;

import java.time.LocalDateTime;
import java.util.Date;

public interface SolicitudUsuarioInterface {
	
	Integer getid_sol_mp();
	Integer getcia();
	String getBodega_Erp();
	String getTipo_doc();
	Integer getNum_doc();
	Date getFecha_Doc();
	Integer getEstado_Doc();
	Integer getid_op_ia();
	String getTipo_Op();
	Integer getNum_Op();
	Integer getId_Usu_Sol();
	Integer getId_Usu_Aprueba();
	String getTipo_doc_Erp();
	Integer getNum_Doc_Erp();
	LocalDateTime getFecha_Doc_Erp();
	String getBarcode();
	String getusu_nombre();


}
