package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class RutasoperacionesOperacionesV02 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 809;  //Valor fijo = 809
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 2;  //Versión = 02
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la información del registro
	private String f808_id;  //Código de la ruta
	private String f808_id_instalacion;  //Valida en maestro, código de la instalación.
	private String f809_id_metodo;  //Código del metodo
	private Integer f809_numero_operacion;

	public String getF_numero_reg () {
		return getFormattedValue(F_NUMERO_REG ,7);
	}
	@Override

	public void setF_numero_reg (Integer F_NUMERO_REG) {
		this.F_NUMERO_REG = F_NUMERO_REG;
	}

	public String getF_tipo_reg () {
		return getFormattedValue(F_TIPO_REG ,4);
	}

	public void setF_tipo_reg (Integer F_TIPO_REG) {
		this.F_TIPO_REG = F_TIPO_REG;
	}

	public String getF_subtipo_reg () {
		return getFormattedValue(F_SUBTIPO_REG ,2);
	}

	public void setF_subtipo_reg (Integer F_SUBTIPO_REG) {
		this.F_SUBTIPO_REG = F_SUBTIPO_REG;
	}

	public String getF_version_reg () {
		return getFormattedValue(F_VERSION_REG ,2);
	}

	public void setF_version_reg (Integer F_VERSION_REG) {
		this.F_VERSION_REG = F_VERSION_REG;
	}

	public String getF_cia () {
		return getFormattedValue(F_CIA ,3);
	}

	public void setF_cia (Integer F_CIA) {
		this.F_CIA = F_CIA;
	}

	public String getF808_id () {
		return getFormattedValue(f808_id ,20);
	}

	public void setF808_id (String f808_id) {
		this.f808_id = f808_id;
	}

	public String getF808_id_instalacion () {
		return getFormattedValue(f808_id_instalacion ,3);
	}

	public void setF808_id_instalacion (String f808_id_instalacion) {
		this.f808_id_instalacion = f808_id_instalacion;
	}

	public String getF809_id_metodo () {
		return getFormattedValue(f809_id_metodo ,4);
	}

	public void setF809_id_metodo (String f809_id_metodo) {
		this.f809_id_metodo = f809_id_metodo;
	}

	public String getF809_numero_operacion() {
		return getFormattedValue(f809_numero_operacion ,4);
	}
	public void setF809_numero_operacion(Integer f809_numero_operacion) {
		this.f809_numero_operacion = f809_numero_operacion;
	}
	public RutasoperacionesOperacionesV02 () {
	// Constructor vacio para inicializar
	}
	@Override

	public String getFormattedValue(String name, int size) {
		if(name == null) {
			return String.format("%" + size + "s", " ");
		}
			return String.format("%-" + size +"s", name);
	}
	@Override

	public String getFormattedValue(Integer name, int size) {
		if(name == null) {
			return String.format("%0" + size + "d", 0);
		}
			return String.format("%0" + size +"d", name);
	}
	@Override

	public String getFormattedValue(Double name, int size, int decimal) {
		if(name == null) {
			return String.format("%0" + size + "."+decimal+"f", 0.0).replace(",",".");
		}
		return String.format("%0" + size + "."+decimal+ "f", name).replace(",",".");
	}
	@Override
	public String toString() {
		return "RutasoperacionesOperacionesV02[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", f808_id=" + this.getF808_id () +", f808_id_instalacion=" + this.getF808_id_instalacion () +", f809_id_metodo=" + this.getF809_id_metodo () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF808_id ()+this.getF808_id_instalacion ()+this.getF809_id_metodo ()+this.getF809_numero_operacion();
	}
}