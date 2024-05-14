package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class OrdenesdeproduccionCompromisosCompromisosV4 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 405;  //Valor fijo = 405
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 4;  //Version = 04
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private String f850_id_co ;  //Valida en maestro, código de centro de operación del documento
	private String f850_id_tipo_docto;  //Valida en maestro, código de tipo de documento
	private Integer f850_consec_docto;  //Numero de documento
	private Integer f850_nro_registro;  //Numero de registro del movimiento
	private Integer f851_id_item_padre;  //Codigo, es obligatorio si no va referencia ni codigo de barras.  No obligatorio si la orden de producción es consolidada.
	private String f851_referencia_item_padre;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras.No obligatorio si la orden de producción es consolidada.
	private String f851_codigo_barras_item_padre;  //Codigo, es obligatorio si no va codigo de item ni referencia. No obligatorio si la orden de producción es consolidada.
	private String f851_id_ext1_detalle_item_padre;  //Es obligatorio si el ítem maneja extensión 1. No obligatorio si la orden de producción es consolidada.
	private String f851_id_ext2_detalle_item_padre;  //Es obligatorio si el ítem maneja extensión 2.No obligatorio si la orden de producción es consolidada.
	private Integer f860_id_item_comp;  //Codigo, es obligatorio si no va referencia ni codigo de barras
	private String f860_referencia_item_comp;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f860_codigo_barras_item_comp;  //Codigo, es obligatorio si no va codigo de item ni referencia
	private String f860_id_ext1_detalle_item_comp;  //Es obligatorio si el ítem maneja extensión 1
	private String f860_id_ext2_detalle_item_comp;  //Es obligatorio si el ítem maneja extensión 2
	private String f860_id_bodega;  //Valida en maestro, código de bodega
	private String f860_id_ubicación_aux;  //Valida en maestro, obligatorio si la bodega maneja ubiaciones. No es necesario cuando el item maneja seriales.
	private String f860_id_lote;  //Valida en maestro, obligatorio si el item y la bodega manejan lotes. No es necesario si el item maneja seriales.
	private String f860_id_unidad_medida;  //Valida en maestro, código de unidad de medida del movimiento
	private Double f860_cant_base;  //Cantidad en unidad de captura, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medidad. (000000000000000.0000)
	private Double f860_cant_2;  //Cantidad adicional, es obligatorio si el item maneja unidad adicional, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medidad. (000000000000000.0000)

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

	public String getF850_id_co  () {
		return getFormattedValue(f850_id_co  ,3);
	}

	public void setF850_id_co  (String f850_id_co ) {
		this.f850_id_co  = f850_id_co ;
	}

	public String getF850_id_tipo_docto () {
		return getFormattedValue(f850_id_tipo_docto ,3);
	}

	public void setF850_id_tipo_docto (String f850_id_tipo_docto) {
		this.f850_id_tipo_docto = f850_id_tipo_docto;
	}

	public String getF850_consec_docto () {
		return getFormattedValue(f850_consec_docto ,8);
	}

	public void setF850_consec_docto (Integer f850_consec_docto) {
		this.f850_consec_docto = f850_consec_docto;
	}

	public String getF850_nro_registro () {
		return getFormattedValue(f850_nro_registro ,10);
	}

	public void setF850_nro_registro (Integer f850_nro_registro) {
		this.f850_nro_registro = f850_nro_registro;
	}

	public String getF851_id_item_padre () {
		return getFormattedValue(f851_id_item_padre ,7);
	}

	public void setF851_id_item_padre (Integer f851_id_item_padre) {
		this.f851_id_item_padre = f851_id_item_padre;
	}

	public String getF851_referencia_item_padre () {
		return getFormattedValue(f851_referencia_item_padre ,50);
	}

	public void setF851_referencia_item_padre (String f851_referencia_item_padre) {
		this.f851_referencia_item_padre = f851_referencia_item_padre;
	}

	public String getF851_codigo_barras_item_padre () {
		return getFormattedValue(f851_codigo_barras_item_padre ,20);
	}

	public void setF851_codigo_barras_item_padre (String f851_codigo_barras_item_padre) {
		this.f851_codigo_barras_item_padre = f851_codigo_barras_item_padre;
	}

	public String getF851_id_ext1_detalle_item_padre () {
		return getFormattedValue(f851_id_ext1_detalle_item_padre ,20);
	}

	public void setF851_id_ext1_detalle_item_padre (String f851_id_ext1_detalle_item_padre) {
		this.f851_id_ext1_detalle_item_padre = f851_id_ext1_detalle_item_padre;
	}

	public String getF851_id_ext2_detalle_item_padre () {
		return getFormattedValue(f851_id_ext2_detalle_item_padre ,20);
	}

	public void setF851_id_ext2_detalle_item_padre (String f851_id_ext2_detalle_item_padre) {
		this.f851_id_ext2_detalle_item_padre = f851_id_ext2_detalle_item_padre;
	}

	public String getF860_id_item_comp () {
		return getFormattedValue(f860_id_item_comp ,7);
	}

	public void setF860_id_item_comp (Integer f860_id_item_comp) {
		this.f860_id_item_comp = f860_id_item_comp;
	}

	public String getF860_referencia_item_comp () {
		return getFormattedValue(f860_referencia_item_comp ,50);
	}

	public void setF860_referencia_item_comp (String f860_referencia_item_comp) {
		this.f860_referencia_item_comp = f860_referencia_item_comp;
	}

	public String getF860_codigo_barras_item_comp () {
		return getFormattedValue(f860_codigo_barras_item_comp ,20);
	}

	public void setF860_codigo_barras_item_comp (String f860_codigo_barras_item_comp) {
		this.f860_codigo_barras_item_comp = f860_codigo_barras_item_comp;
	}

	public String getF860_id_ext1_detalle_item_comp () {
		return getFormattedValue(f860_id_ext1_detalle_item_comp ,20);
	}

	public void setF860_id_ext1_detalle_item_comp (String f860_id_ext1_detalle_item_comp) {
		this.f860_id_ext1_detalle_item_comp = f860_id_ext1_detalle_item_comp;
	}

	public String getF860_id_ext2_detalle_item_comp () {
		return getFormattedValue(f860_id_ext2_detalle_item_comp ,20);
	}

	public void setF860_id_ext2_detalle_item_comp (String f860_id_ext2_detalle_item_comp) {
		this.f860_id_ext2_detalle_item_comp = f860_id_ext2_detalle_item_comp;
	}

	public String getF860_id_bodega () {
		return getFormattedValue(f860_id_bodega ,5);
	}

	public void setF860_id_bodega (String f860_id_bodega) {
		this.f860_id_bodega = f860_id_bodega;
	}

	public String getF860_id_ubicación_aux () {
		return getFormattedValue(f860_id_ubicación_aux ,10);
	}

	public void setF860_id_ubicación_aux (String f860_id_ubicación_aux) {
		this.f860_id_ubicación_aux = f860_id_ubicación_aux;
	}

	public String getF860_id_lote () {
		return getFormattedValue(f860_id_lote ,15);
	}

	public void setF860_id_lote (String f860_id_lote) {
		this.f860_id_lote = f860_id_lote;
	}

	public String getF860_id_unidad_medida () {
		return getFormattedValue(f860_id_unidad_medida ,4);
	}

	public void setF860_id_unidad_medida (String f860_id_unidad_medida) {
		this.f860_id_unidad_medida = f860_id_unidad_medida;
	}

	public String getF860_cant_base () {
		return getFormattedValue(f860_cant_base ,20,4);
	}

	public void setF860_cant_base (Double f860_cant_base) {
		this.f860_cant_base = f860_cant_base;
	}

	public String getF860_cant_2 () {
		return getFormattedValue(f860_cant_2 ,20,4);
	}

	public void setF860_cant_2 (Double f860_cant_2) {
		this.f860_cant_2 = f860_cant_2;
	}


	public OrdenesdeproduccionCompromisosCompromisosV4 () {
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
		return "OrdenesdeproduccionCompromisosCompromisosV4[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", f850_id_co =" + this.getF850_id_co  () +", f850_id_tipo_docto=" + this.getF850_id_tipo_docto () +", f850_consec_docto=" + this.getF850_consec_docto () +", f850_nro_registro=" + this.getF850_nro_registro () +", f851_id_item_padre=" + this.getF851_id_item_padre () +", f851_referencia_item_padre=" + this.getF851_referencia_item_padre () +", f851_codigo_barras_item_padre=" + this.getF851_codigo_barras_item_padre () +", f851_id_ext1_detalle_item_padre=" + this.getF851_id_ext1_detalle_item_padre () +", f851_id_ext2_detalle_item_padre=" + this.getF851_id_ext2_detalle_item_padre () +", f860_id_item_comp=" + this.getF860_id_item_comp () +", f860_referencia_item_comp=" + this.getF860_referencia_item_comp () +", f860_codigo_barras_item_comp=" + this.getF860_codigo_barras_item_comp () +", f860_id_ext1_detalle_item_comp=" + this.getF860_id_ext1_detalle_item_comp () +", f860_id_ext2_detalle_item_comp=" + this.getF860_id_ext2_detalle_item_comp () +", f860_id_bodega=" + this.getF860_id_bodega () +", f860_id_ubicación_aux=" + this.getF860_id_ubicación_aux () +", f860_id_lote=" + this.getF860_id_lote () +", f860_id_unidad_medida=" + this.getF860_id_unidad_medida () +", f860_cant_base=" + this.getF860_cant_base () +", f860_cant_2=" + this.getF860_cant_2 () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF850_id_co  ()+this.getF850_id_tipo_docto ()+this.getF850_consec_docto ()+this.getF850_nro_registro ()+this.getF851_id_item_padre ()+this.getF851_referencia_item_padre ()+this.getF851_codigo_barras_item_padre ()+this.getF851_id_ext1_detalle_item_padre ()+this.getF851_id_ext2_detalle_item_padre ()+this.getF860_id_item_comp ()+this.getF860_referencia_item_comp ()+this.getF860_codigo_barras_item_comp ()+this.getF860_id_ext1_detalle_item_comp ()+this.getF860_id_ext2_detalle_item_comp ()+this.getF860_id_bodega ()+this.getF860_id_ubicación_aux ()+this.getF860_id_lote ()+this.getF860_id_unidad_medida ()+this.getF860_cant_base ()+this.getF860_cant_2 ();
	}
}