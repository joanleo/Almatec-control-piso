package com.almatec.controlpiso.integrapps.interfaces;

import java.math.BigDecimal;
import java.util.Date;

public interface ItemOpInterface {
	
	Long getitem_id();
	Integer getid_op_ia();
	Integer getItem_fab_Id();
	String getgrupo();
	String getdescripcion();
	String getmarca();
	String getcodigo_erp();
	BigDecimal getpeso_unitario();
	String getunidad();
	Double getcant_req();
	Boolean getespecial();
	Boolean getreq_plano();
	Boolean getactivo();
	String getruta_plano();
	Date getfecha_crea();
	Integer getid_estado();
	Double getcant_cumplida();
	String getpintura();

}
