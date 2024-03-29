package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class ListadematerialesListadematerialesv4 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 820;  //Valor fijo = 820
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 4;  //Versión = 04
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la información del registro
	private Integer F_ACTUALIZA_REG;  //0=No, 1=Si
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
	private Double f820_cant_base ;  //Cantidad hasta, el formato debe ser (14 enteros + punto + 4 decimales).   (00000000000000.0000)
	private Double f820_cant_requerida ;  //Cantidad hasta, el formato debe ser (9 enteros + punto + 6 decimales).   (000000000.000000)
	private String f820_fecha_activacion ;  //Obligatorio, formato AAAAMMDD
	private String f820_fecha_inactivacion ;  //Vacio si no desea manejar fecha de inactivación, formato AAAAMMDD
	private Double f820_porcentaje_desperdicio ;  //Porcentaje de desperdicio  de 0 a 100, el formato debe ser (3 enteros + 4 decimales) (000.0000)
	private Integer f820_ruta_oper ;  //Debe ser una operación asociada a la ruta 
	private Integer f820_codigo_uso ;  //0=Normal; 1=Coproducto cant. variable; 2=Coproducto cant. fija; 3=Subproducto cant. varible; 4=Subproducto cant. fija
	private Double f820_porcentaje_costo_producto ;  //Porcentaje de costo  de 0 a 100, el formato debe ser (3 enteros + 4 decimales) (000.0000), obligatorio si código de uso es de coproducto 1 o 2
	private String f820_notas ;  //Notas
	private String f820_id_bodega;  //Se valida con el maestro
	private Integer f820_ind_no_costea;

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

	public void setF820_id_ext2_detalle_hijo (String f820_id_ext2_detalle_hijo) {
		this.f820_id_ext2_detalle_hijo = f820_id_ext2_detalle_hijo;
	}

	public String getF820_cant_base  () {
		return getFormattedValue(f820_cant_base  ,19,4);
	}

	public void setF820_cant_base  (Double f820_cant_base ) {
		this.f820_cant_base  = f820_cant_base ;
	}

	public String getF820_cant_requerida  () {
		return getFormattedValue(f820_cant_requerida  ,16,6);
	}

	public void setF820_cant_requerida  (Double f820_cant_requerida ) {
		this.f820_cant_requerida  = f820_cant_requerida ;
	}

	public String getF820_fecha_activacion  () {
		return getFormattedValue(f820_fecha_activacion  ,8);
	}

	public void setF820_fecha_activacion  (String f820_fecha_activacion ) {
		this.f820_fecha_activacion  = f820_fecha_activacion ;
	}

	public String getF820_fecha_inactivacion  () {
		return getFormattedValue(f820_fecha_inactivacion  ,8);
	}

	public void setF820_fecha_inactivacion  (String f820_fecha_inactivacion ) {
		this.f820_fecha_inactivacion  = f820_fecha_inactivacion ;
	}

	public String getF820_porcentaje_desperdicio  () {
		return getFormattedValue(f820_porcentaje_desperdicio  ,8,4);
	}

	public void setF820_porcentaje_desperdicio  (Double f820_porcentaje_desperdicio ) {
		this.f820_porcentaje_desperdicio  = f820_porcentaje_desperdicio ;
	}

	public String getF820_ruta_oper  () {
		return getFormattedValue(f820_ruta_oper  ,4);
	}

	public void setF820_ruta_oper  (Integer f820_ruta_oper ) {
		this.f820_ruta_oper  = f820_ruta_oper ;
	}

	public String getF820_codigo_uso  () {
		return getFormattedValue(f820_codigo_uso  ,1);
	}

	public void setF820_codigo_uso  (Integer f820_codigo_uso ) {
		this.f820_codigo_uso  = f820_codigo_uso ;
	}

	public String getF820_porcentaje_costo_producto  () {
		return getFormattedValue(f820_porcentaje_costo_producto  ,8,4);
	}

	public void setF820_porcentaje_costo_producto  (Double f820_porcentaje_costo_producto ) {
		this.f820_porcentaje_costo_producto  = f820_porcentaje_costo_producto ;
	}

	public String getF820_notas  () {
		return getFormattedValue(f820_notas  ,255);
	}

	public void setF820_notas  (String f820_notas ) {
		this.f820_notas  = f820_notas ;
	}

	public String getF820_id_bodega () {
		return getFormattedValue(f820_id_bodega ,5);
	}

	public void setF820_id_bodega (String f820_id_bodega) {
		this.f820_id_bodega = f820_id_bodega;
	}

	public Integer getF820_ind_no_costea() {
		return f820_ind_no_costea;
	}
	public void setF820_ind_no_costea(Integer f820_ind_no_costea) {
		this.f820_ind_no_costea = f820_ind_no_costea;
	}
	public ListadematerialesListadematerialesv4 () {
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
		return "ListadematerialesListadematerialesv4[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", F_ACTUALIZA_REG=" + this.getF_actualiza_reg () +", f820_id  =" + this.getF820_id   () +", f820_referencia =" + this.getF820_referencia  () +", f820_id_ext1_detalle=" + this.getF820_id_ext1_detalle () +", f820_id_ext2_detalle=" + this.getF820_id_ext2_detalle () +", f820_id_instalacion =" + this.getF820_id_instalacion  () +", f820_id_metodo =" + this.getF820_id_metodo  () +", f820_secuencia =" + this.getF820_secuencia  () +", f820_id_hijo=" + this.getF820_id_hijo () +", f820_referencia_hijo=" + this.getF820_referencia_hijo () +", f820_id_ext1_detalle_hijo=" + this.getF820_id_ext1_detalle_hijo () +", f820_id_ext2_detalle_hijo=" + this.getF820_id_ext2_detalle_hijo () +", f820_cant_base =" + this.getF820_cant_base  () +", f820_cant_requerida =" + this.getF820_cant_requerida  () +", f820_fecha_activacion =" + this.getF820_fecha_activacion  () +", f820_fecha_inactivacion =" + this.getF820_fecha_inactivacion  () +", f820_porcentaje_desperdicio =" + this.getF820_porcentaje_desperdicio  () +", f820_ruta_oper =" + this.getF820_ruta_oper  () +", f820_codigo_uso =" + this.getF820_codigo_uso  () +", f820_porcentaje_costo_producto =" + this.getF820_porcentaje_costo_producto  () +", f820_notas =" + this.getF820_notas  () +", f820_id_bodega=" + this.getF820_id_bodega () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_actualiza_reg ()+this.getF820_id   ()+this.getF820_referencia  ()+this.getF820_id_ext1_detalle ()+this.getF820_id_ext2_detalle ()+this.getF820_id_instalacion  ()+this.getF820_id_metodo  ()+this.getF820_secuencia  ()+this.getF820_id_hijo ()+this.getF820_referencia_hijo ()+this.getF820_id_ext1_detalle_hijo ()+this.getF820_id_ext2_detalle_hijo ()+this.getF820_cant_base  ()+this.getF820_cant_requerida  ()+this.getF820_fecha_activacion  ()+this.getF820_fecha_inactivacion  ()+this.getF820_porcentaje_desperdicio  ()+this.getF820_ruta_oper  ()+this.getF820_codigo_uso  ()+this.getF820_porcentaje_costo_producto  ()+this.getF820_notas  ()+this.getF820_id_bodega ();
	}
}