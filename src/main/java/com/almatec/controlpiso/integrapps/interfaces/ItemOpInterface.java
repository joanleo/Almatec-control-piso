package com.almatec.controlpiso.integrapps.interfaces;

public interface ItemOpInterface {
	
	Long getitem_id();
	Integer getid_op_ia();
	Integer getid_flia();
	Integer getid_grp_item();
	String getdescripcion();
	String getMedida_1();
	String getMedida_2();
	String getMedida_3();
	String getpeso_unitario();
	String getunidad();
	String getcant_req();
	Boolean getespecial();
	Boolean getreq_plano();
	Boolean getactivo();
	String getruta_plano();
	String getagrupa();
	Integer getid_estado();

}
