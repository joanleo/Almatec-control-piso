package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class RutasRutasV01 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 808;  //Valor fijo = 808
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 1;  //Versión = 01
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la información del registro
	private Integer F_ACTUALIZA_REG;  //0=No, 1=Si
	private String f808_id;  //Código de la ruta
	private String f808_id_instalacion;  //Valida en maestro, código de la instalación.
	private String f808_descripcion;  //Descripción ruta
	private Integer f808_ind_estado;  //0 = Inactivo, 1 = Activo
	private Integer f808_ind_controla_sec_piso;  //0 = No controla, 1 = Controla
	private String f808_notas; 

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

	public String getF_actualiza_reg () {
		return getFormattedValue(F_ACTUALIZA_REG ,1);
	}

	public void setF_actualiza_reg (Integer F_ACTUALIZA_REG) {
		this.F_ACTUALIZA_REG = F_ACTUALIZA_REG;
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

	public String getF808_descripcion () {
		return getFormattedValue(f808_descripcion ,40);
	}

	public void setF808_descripcion (String f808_descripcion) {
		this.f808_descripcion = f808_descripcion;
	}

	public String getF808_ind_estado () {
		return getFormattedValue(f808_ind_estado ,1);
	}

	public void setF808_ind_estado (Integer f808_ind_estado) {
		this.f808_ind_estado = f808_ind_estado;
	}

	public String getF808_ind_controla_sec_piso () {
		return getFormattedValue(f808_ind_controla_sec_piso ,1);
	}

	public void setF808_ind_controla_sec_piso (Integer f808_ind_controla_sec_piso) {
		this.f808_ind_controla_sec_piso = f808_ind_controla_sec_piso;
	}

	public String getF808_notas() {
		return getFormattedValue(f808_notas ,255);
	}
	public void setF808_notas(String f808_notas) {
		this.f808_notas = f808_notas;
	}
	public RutasRutasV01 () {
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
		return "RutasRutasV01[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", F_ACTUALIZA_REG=" + this.getF_actualiza_reg () +", f808_id=" + this.getF808_id () +", f808_id_instalacion=" + this.getF808_id_instalacion () +", f808_descripcion=" + this.getF808_descripcion () +", f808_ind_estado=" + this.getF808_ind_estado () +", f808_ind_controla_sec_piso=" + this.getF808_ind_controla_sec_piso () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_actualiza_reg ()+this.getF808_id ()+this.getF808_id_instalacion ()+this.getF808_descripcion ()+this.getF808_ind_estado ()+this.getF808_ind_controla_sec_piso ()+ this.getF808_notas();
	}
}