package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class ListadematerialesListadematerialesv3 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 820;  //Valor fijo = 820
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 3;  //Versión = 03
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la información del registro
	private Integer f820_id  ;  //Se valida con maestro, es obligatorio si no se especifica la referencia
	private String f820_referencia ;  //Se valida con maestro, es obligatoria si no se especifica el código
	private String f820_id_ext1_detalle;  //Es obligatorio si el ítem maneja extensión 1
	private String f820_id_ext2_detalle;  //Es obligatorio si el ítem maneja extensión 2
	private String f820_id_instalacion ;  //Se valida con el maestro
	private String f820_id_metodo ;  //Se valida con el maestro
	private Integer f820_secuencia ;  //Número de secuencia
	private Integer f820_id_hijo;  //Se valida con maestro, es obligatorio si no se especifica la referencia
	private String f820_referencia_hijo;  //Se valida con maestro, es obligatoria si no se especifica el código
	private String f820_id_ext1_detalle_hijo;  //Es obligatorio si el ítem maneja extensión 1
	private String f820_id_ext2_detalle_hijo;  //Es obligatorio si el ítem maneja extensión 2

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

	public String getF820_id   () {
		return getFormattedValue(f820_id   ,7);
	}

	public void setF820_id   (Integer f820_id  ) {
		this.f820_id   = f820_id  ;
	}

	public String getF820_referencia  () {
		return getFormattedValue(f820_referencia  ,50);
	}

	public void setF820_referencia  (String f820_referencia ) {
		this.f820_referencia  = f820_referencia ;
	}

	public String getF820_id_ext1_detalle () {
		return getFormattedValue(f820_id_ext1_detalle ,20);
	}

	public void setF820_id_ext1_detalle (String f820_id_ext1_detalle) {
		this.f820_id_ext1_detalle = f820_id_ext1_detalle;
	}

	public String getF820_id_ext2_detalle () {
		return getFormattedValue(f820_id_ext2_detalle ,20);
	}

	public void setF820_id_ext2_detalle (String f820_id_ext2_detalle) {
		this.f820_id_ext2_detalle = f820_id_ext2_detalle;
	}

	public String getF820_id_instalacion  () {
		return getFormattedValue(f820_id_instalacion  ,3);
	}

	public void setF820_id_instalacion  (String f820_id_instalacion ) {
		this.f820_id_instalacion  = f820_id_instalacion ;
	}

	public String getF820_id_metodo  () {
		return getFormattedValue(f820_id_metodo  ,4);
	}

	public void setF820_id_metodo  (String f820_id_metodo ) {
		this.f820_id_metodo  = f820_id_metodo ;
	}

	public String getF820_secuencia  () {
		return getFormattedValue(f820_secuencia  ,4);
	}

	public void setF820_secuencia  (Integer f820_secuencia ) {
		this.f820_secuencia  = f820_secuencia ;
	}

	public String getF820_id_hijo () {
		return getFormattedValue(f820_id_hijo ,7);
	}

	public void setF820_id_hijo (Integer f820_id_hijo) {
		this.f820_id_hijo = f820_id_hijo;
	}

	public String getF820_referencia_hijo () {
		return getFormattedValue(f820_referencia_hijo ,50);
	}

	public void setF820_referencia_hijo (String f820_referencia_hijo) {
		this.f820_referencia_hijo = f820_referencia_hijo;
	}

	public String getF820_id_ext1_detalle_hijo () {
		return getFormattedValue(f820_id_ext1_detalle_hijo ,20);
	}

	public void setF820_id_ext1_detalle_hijo (String f820_id_ext1_detalle_hijo) {
		this.f820_id_ext1_detalle_hijo = f820_id_ext1_detalle_hijo;
	}
	
	public String getF820_id_ext2_detalle_hijo () {
		return getFormattedValue(f820_id_ext2_detalle_hijo ,20);
	}
	
	public void setF820_id_ext2_detalle_hijo (String f820_id_ext1_detalle_hijo) {
		this.f820_id_ext2_detalle_hijo = f820_id_ext1_detalle_hijo;
	}

	public ListadematerialesListadematerialesv3 () {
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
		return "ListadematerialesListadematerialesv3[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", f820_id  =" + this.getF820_id   () +", f820_referencia =" + this.getF820_referencia  () +", f820_id_ext1_detalle=" + this.getF820_id_ext1_detalle () +", f820_id_ext2_detalle=" + this.getF820_id_ext2_detalle () +", f820_id_instalacion =" + this.getF820_id_instalacion  () +", f820_id_metodo =" + this.getF820_id_metodo  () +", f820_secuencia =" + this.getF820_secuencia  () +", f820_id_hijo=" + this.getF820_id_hijo () +", f820_referencia_hijo=" + this.getF820_referencia_hijo () +", f820_id_ext1_detalle_hijo=" + this.getF820_id_ext1_detalle_hijo () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF820_id   ()+this.getF820_referencia  ()+this.getF820_id_ext1_detalle ()+this.getF820_id_ext2_detalle ()+this.getF820_id_instalacion  ()+this.getF820_id_metodo  ()+this.getF820_secuencia  ()+this.getF820_id_hijo ()+this.getF820_referencia_hijo ()+this.getF820_id_ext1_detalle_hijo ()+this.getF820_id_ext2_detalle_hijo();
	}
}