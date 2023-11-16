package com.almatec.controlpiso.erp.webservices.interfaces;

public interface Conector {

	public String getFormattedValue(String name, int size);
	public String getFormattedValue(Integer name, int size);
	public String getFormattedValue(Double name, int size, int decimal);
	public void setF_numero_reg(Integer F_NUMERO_REG);
	public String getConector();
}
