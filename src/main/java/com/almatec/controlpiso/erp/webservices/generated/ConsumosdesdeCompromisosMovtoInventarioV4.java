package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class ConsumosdesdeCompromisosMovtoInventarioV4 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 470;  //Valor fijo = 470
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 4;  //Version = 04
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private String f470_id_co ;  //Valida en maestro, código de centro de operación del documento
	private String f470_id_tipo_docto;  //Valida en maestro, código de tipo de documento
	private Integer f470_consec_docto;  //Numero de documento
	private Integer f470_nro_registro;  //Numero de registro del movimiento
	private Integer f470_id_item_padre;  //Codigo, es obligatorio si no va referencia ni codigo de barras
	private String f470_referencia_item_padre;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f470_codigo_barras_padre;  //Codigo, es obligatorio si no va codigo de item ni referencia
	private String f470_id_ext1_detalle_padre;  //Es obligatorio si el ítem maneja extensión 1
	private String f470_id_ext2_detalle_padre;  //Es obligatorio si el ítem maneja extensión 2
	private Integer f470_numero_operacion;  //Número de la operación, valida en maestro.
	private Integer f470_id_item_comp;  //Codigo, es obligatorio si no va referencia ni codigo de barras
	private String f470_referencia_item_comp;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f470_codigo_barras_comp;  //Codigo, es obligatorio si no va codigo de item ni referencia
	private String f470_id_ext1_detalle_comp;  //Es obligatorio si el ítem maneja extensión 1
	private String f470_id_ext2_detalle_comp;  //Es obligatorio si el ítem maneja extensión 2
	private String f470_id_bodega;  //Valida en maestro, código de bodega
	private String f470_id_ubicación_aux;  //Valida en maestro, obligatorio si la bodega maneja ubiaciones
	private String f470_id_lote;  //Valida en maestro, obligatorio si el item y la bodega manejan lotes
	private Integer f470_id_concepto;  //701=Control de piso
	private String f470_id_motivo;  //Valida en maestro, código de motivo
	private String f470_id_co_movto;  //Valida en maestro, código de centro de operación del movimiento
	private String f470_id_un_movto;  //Valida en maestro, código de unidad de negocio del movimiento.
	private String f470_id_ccosto_movto;  //Obligatorio si la  cuenta contable exige ccosto. Valida en maestro, código de centro de costo del movimiento.
	private String f470_id_proyecto;  //Valida en maestro, código de proyecto del movimiento
	private String f470_id_unidad_medida;  //Valida en maestro, código de unidad de medida del movimiento
	private Double f470_cant_base;  //Cantidad en unidad de captura, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medidad. (000000000000000.0000)
	private Double f470_cant_2;  //Cantidad adicional, es obligatorio si el item maneja unidad adicional, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medidad. (000000000000000.0000)
	private String f470_notas;  //Notas del movimiento
	private String f470_desc_varible;

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

	public String getF470_id_co  () {
		return getFormattedValue(f470_id_co  ,3);
	}

	public void setF470_id_co  (String f470_id_co ) {
		this.f470_id_co  = f470_id_co ;
	}

	public String getF470_id_tipo_docto () {
		return getFormattedValue(f470_id_tipo_docto ,3);
	}

	public void setF470_id_tipo_docto (String f470_id_tipo_docto) {
		this.f470_id_tipo_docto = f470_id_tipo_docto;
	}

	public String getF470_consec_docto () {
		return getFormattedValue(f470_consec_docto ,8);
	}

	public void setF470_consec_docto (Integer f470_consec_docto) {
		this.f470_consec_docto = f470_consec_docto;
	}

	public String getF470_nro_registro () {
		return getFormattedValue(f470_nro_registro ,10);
	}

	public void setF470_nro_registro (Integer f470_nro_registro) {
		this.f470_nro_registro = f470_nro_registro;
	}

	public String getF470_id_item_padre () {
		return getFormattedValue(f470_id_item_padre ,7);
	}

	public void setF470_id_item_padre (Integer f470_id_item_padre) {
		this.f470_id_item_padre = f470_id_item_padre;
	}

	public String getF470_referencia_item_padre () {
		return getFormattedValue(f470_referencia_item_padre ,50);
	}

	public void setF470_referencia_item_padre (String f470_referencia_item_padre) {
		this.f470_referencia_item_padre = f470_referencia_item_padre;
	}

	public String getF470_codigo_barras_padre () {
		return getFormattedValue(f470_codigo_barras_padre ,20);
	}

	public void setF470_codigo_barras_padre (String f470_codigo_barras_padre) {
		this.f470_codigo_barras_padre = f470_codigo_barras_padre;
	}

	public String getF470_id_ext1_detalle_padre () {
		return getFormattedValue(f470_id_ext1_detalle_padre ,20);
	}

	public void setF470_id_ext1_detalle_padre (String f470_id_ext1_detalle_padre) {
		this.f470_id_ext1_detalle_padre = f470_id_ext1_detalle_padre;
	}

	public String getF470_id_ext2_detalle_padre () {
		return getFormattedValue(f470_id_ext2_detalle_padre ,20);
	}

	public void setF470_id_ext2_detalle_padre (String f470_id_ext2_detalle_padre) {
		this.f470_id_ext2_detalle_padre = f470_id_ext2_detalle_padre;
	}

	public String getF470_numero_operacion () {
		return getFormattedValue(f470_numero_operacion ,10);
	}

	public void setF470_numero_operacion (Integer f470_numero_operacion) {
		this.f470_numero_operacion = f470_numero_operacion;
	}

	public String getF470_id_item_comp () {
		return getFormattedValue(f470_id_item_comp ,7);
	}

	public void setF470_id_item_comp (Integer f470_id_item_comp) {
		this.f470_id_item_comp = f470_id_item_comp;
	}

	public String getF470_referencia_item_comp () {
		return getFormattedValue(f470_referencia_item_comp ,50);
	}

	public void setF470_referencia_item_comp (String f470_referencia_item_comp) {
		this.f470_referencia_item_comp = f470_referencia_item_comp;
	}

	public String getF470_codigo_barras_comp () {
		return getFormattedValue(f470_codigo_barras_comp ,20);
	}

	public void setF470_codigo_barras_comp (String f470_codigo_barras_comp) {
		this.f470_codigo_barras_comp = f470_codigo_barras_comp;
	}

	public String getF470_id_ext1_detalle_comp () {
		return getFormattedValue(f470_id_ext1_detalle_comp ,20);
	}

	public void setF470_id_ext1_detalle_comp (String f470_id_ext1_detalle_comp) {
		this.f470_id_ext1_detalle_comp = f470_id_ext1_detalle_comp;
	}

	public String getF470_id_ext2_detalle_comp () {
		return getFormattedValue(f470_id_ext2_detalle_comp ,20);
	}

	public void setF470_id_ext2_detalle_comp (String f470_id_ext2_detalle_comp) {
		this.f470_id_ext2_detalle_comp = f470_id_ext2_detalle_comp;
	}

	public String getF470_id_bodega () {
		return getFormattedValue(f470_id_bodega ,5);
	}

	public void setF470_id_bodega (String f470_id_bodega) {
		this.f470_id_bodega = f470_id_bodega;
	}

	public String getF470_id_ubicación_aux () {
		return getFormattedValue(f470_id_ubicación_aux ,10);
	}

	public void setF470_id_ubicación_aux (String f470_id_ubicación_aux) {
		this.f470_id_ubicación_aux = f470_id_ubicación_aux;
	}

	public String getF470_id_lote () {
		return getFormattedValue(f470_id_lote ,15);
	}

	public void setF470_id_lote (String f470_id_lote) {
		this.f470_id_lote = f470_id_lote;
	}

	public String getF470_id_concepto () {
		return getFormattedValue(f470_id_concepto ,3);
	}

	public void setF470_id_concepto (Integer f470_id_concepto) {
		this.f470_id_concepto = f470_id_concepto;
	}

	public String getF470_id_motivo () {
		return getFormattedValue(f470_id_motivo ,2);
	}

	public void setF470_id_motivo (String f470_id_motivo) {
		this.f470_id_motivo = f470_id_motivo;
	}

	public String getF470_id_co_movto () {
		return getFormattedValue(f470_id_co_movto ,3);
	}

	public void setF470_id_co_movto (String f470_id_co_movto) {
		this.f470_id_co_movto = f470_id_co_movto;
	}

	public String getF470_id_un_movto () {
		return getFormattedValue(f470_id_un_movto ,20);
	}

	public void setF470_id_un_movto (String f470_id_un_movto) {
		this.f470_id_un_movto = f470_id_un_movto;
	}

	public String getF470_id_ccosto_movto () {
		return getFormattedValue(f470_id_ccosto_movto ,15);
	}

	public void setF470_id_ccosto_movto (String f470_id_ccosto_movto) {
		this.f470_id_ccosto_movto = f470_id_ccosto_movto;
	}

	public String getF470_id_proyecto () {
		return getFormattedValue(f470_id_proyecto ,15);
	}

	public void setF470_id_proyecto (String f470_id_proyecto) {
		this.f470_id_proyecto = f470_id_proyecto;
	}

	public String getF470_id_unidad_medida () {
		return getFormattedValue(f470_id_unidad_medida ,4);
	}

	public void setF470_id_unidad_medida (String f470_id_unidad_medida) {
		this.f470_id_unidad_medida = f470_id_unidad_medida;
	}

	public String getF470_cant_base () {
		return getFormattedValue(f470_cant_base ,20,4);
	}

	public void setF470_cant_base (Double f470_cant_base) {
		this.f470_cant_base = f470_cant_base;
	}

	public String getF470_cant_2 () {
		return getFormattedValue(f470_cant_2 ,20,4);
	}

	public void setF470_cant_2 (Double f470_cant_2) {
		this.f470_cant_2 = f470_cant_2;
	}

	public String getF470_notas () {
		return getFormattedValue(f470_notas ,255);
	}

	public void setF470_notas (String f470_notas) {
		this.f470_notas = f470_notas;
	}

	public String getF470_desc_varible() {
		return getFormattedValue(f470_desc_varible ,2000);
	}
	public void setF470_desc_varible(String f470_desc_varible) {
		this.f470_desc_varible = f470_desc_varible;
	}
	public ConsumosdesdeCompromisosMovtoInventarioV4 () {
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
		return "ConsumosdesdeCompromisosMovtoInventarioV4[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", f470_id_co =" + this.getF470_id_co  () +", f470_id_tipo_docto=" + this.getF470_id_tipo_docto () +", f470_consec_docto=" + this.getF470_consec_docto () +", f470_nro_registro=" + this.getF470_nro_registro () +", f470_id_item_padre=" + this.getF470_id_item_padre () +", f470_referencia_item_padre=" + this.getF470_referencia_item_padre () +", f470_codigo_barras_padre=" + this.getF470_codigo_barras_padre () +", f470_id_ext1_detalle_padre=" + this.getF470_id_ext1_detalle_padre () +", f470_id_ext2_detalle_padre=" + this.getF470_id_ext2_detalle_padre () +", f470_numero_operacion=" + this.getF470_numero_operacion () +", f470_id_item_comp=" + this.getF470_id_item_comp () +", f470_referencia_item_comp=" + this.getF470_referencia_item_comp () +", f470_codigo_barras_comp=" + this.getF470_codigo_barras_comp () +", f470_id_ext1_detalle_comp=" + this.getF470_id_ext1_detalle_comp () +", f470_id_ext2_detalle_comp=" + this.getF470_id_ext2_detalle_comp () +", f470_id_bodega=" + this.getF470_id_bodega () +", f470_id_ubicación_aux=" + this.getF470_id_ubicación_aux () +", f470_id_lote=" + this.getF470_id_lote () +", f470_id_concepto=" + this.getF470_id_concepto () +", f470_id_motivo=" + this.getF470_id_motivo () +", f470_id_co_movto=" + this.getF470_id_co_movto () +", f470_id_un_movto=" + this.getF470_id_un_movto () +", f470_id_ccosto_movto=" + this.getF470_id_ccosto_movto () +", f470_id_proyecto=" + this.getF470_id_proyecto () +", f470_id_unidad_medida=" + this.getF470_id_unidad_medida () +", f470_cant_base=" + this.getF470_cant_base () +", f470_cant_2=" + this.getF470_cant_2 () +", f470_notas=" + this.getF470_notas () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF470_id_co  ()+this.getF470_id_tipo_docto ()+this.getF470_consec_docto ()+this.getF470_nro_registro ()+this.getF470_id_item_padre ()+this.getF470_referencia_item_padre ()+this.getF470_codigo_barras_padre ()+this.getF470_id_ext1_detalle_padre ()+this.getF470_id_ext2_detalle_padre ()+this.getF470_numero_operacion ()+this.getF470_id_item_comp ()+this.getF470_referencia_item_comp ()+this.getF470_codigo_barras_comp ()+this.getF470_id_ext1_detalle_comp ()+this.getF470_id_ext2_detalle_comp ()+this.getF470_id_bodega ()+this.getF470_id_ubicación_aux ()+this.getF470_id_lote ()+this.getF470_id_concepto ()+this.getF470_id_motivo ()+this.getF470_id_co_movto ()+this.getF470_id_un_movto ()+this.getF470_id_ccosto_movto ()+this.getF470_id_proyecto ()+this.getF470_id_unidad_medida ()+this.getF470_cant_base ()+this.getF470_cant_2 ()+this.getF470_notas ()+this.getF470_desc_varible();
	}
}