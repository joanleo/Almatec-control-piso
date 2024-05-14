package com.almatec.controlpiso.integrapps.interfaces;

import java.math.BigDecimal;

public interface ItemInterface {

	Integer getItem_fab_Id(); 
	String getitem_tipo(); 
	Integer getcodigo_erp();
	String getitem_desc(); 
	String getitem_grp_flia(); 
	String getitem_grp_pin(); 
	BigDecimal getitem_peso_b(); 
	BigDecimal getitem_peso_n(); 
	String getitem_plano(); 
	String getimp_etiqueta(); 
	BigDecimal getitem_estado(); 
	String getitem_ue(); 
	BigDecimal getitem_long();
}
