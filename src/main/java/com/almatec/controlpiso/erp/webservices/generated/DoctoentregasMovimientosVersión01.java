package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class DoctoentregasMovimientosVersión01 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 470;  //Valor fijo = 470
	private Integer F_SUBTIPO_REG= 2;  //Valor fijo = 02
	private Integer F_VERSION_REG= 1;  //Version = 01
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private String f470_id_co ;  //Valida en maestro, código de centro de operación del documento
	private String f470_id_tipo_docto;  //Valida en maestro, código de tipo de documento
	private Integer f470_consec_docto;  //Numero de documento
	private Integer f470_nro_registro;  //Numero de registro del movimiento
	private String f850_id_tipo_docto;  //Valida en maestro, código de tipo de documento de la Orden de producción
	private Integer f850_consec_docto;  //Numero de documento de la Orden de producción, la OP no debe ser de Posdeducción
	private Integer f470_id_item;  //Codigo, es obligatorio si no va referencia ni codigo de barras
	private String f470_referencia_item;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f470_codigo_barras;  //Codigo, es obligatorio si no va codigo de item ni referencia
	private String f470_id_ext1_detalle;  //Es obligatorio si el ítem maneja extensión 1
	private String f470_id_ext2_detalle;  //Es obligatorio si el ítem maneja extensión 2
	private Integer f470_id_item_otros;  //Codigo, es obligatorio si van a reportar coproductos o subproductos y si no va referencia ni codigo de barras
	private String f470_referencia_item_otros;  //Codigo, es obligatorio si van a reportar coproductos o subproductos y si no va codigo de item ni codigo de barras
	private String f470_codigo_barras_otros;  //Codigo, es obligatorio si van a reportar coproductos o subproductos y si no va codigo de item ni referencia
	private String f470_id_ext1_detalle_otros;  //Es obligatorio si van a reportar coproductos o subproductos y si el ítem maneja extensión 1
	private String f470_id_ext2_detalle_otros;  //Es obligatorio si van a reportar coproductos o subproductos y si el ítem maneja extensión 2
	private String f470_id_bodega;  //Valida en maestro, código de bodega. Si es transferencia debe ser igual a la Bodega salida del documento
	private String f470_id_ubicación_aux;  //Valida en maestro, obligatorio si la bodega maneja ubiaciones
	private String f470_id_lote;  //Valida en maestro, obligatorio si el item y la bodega manejan lotes
	private Integer f470_id_concepto;  //701=Control de piso
	private String f470_id_motivo_entrega;  //Valida en maestro, código de motivo
	private String f470_id_motivo_rechazo;  //Valida en maestro, código de motivo, obligatorio si graba rechazos en los movimientos
	private String f470_id_co_movto;  //Valida en maestro, código de centro de operación del movimiento
	private String f470_id_un_movto;  //Valida en maestro, código de unidad de negocio del movimiento.
	private String f470_id_ccosto_movto;  //Obligatorio si la  cuenta contable exige ccosto. Valida en maestro, código de centro de costo del movimiento.
	private String f470_id_proyecto;  //Valida en maestro, código de proyecto del movimiento
	private Double f470_cant_base_entrega;  //Cantidad en unidad de la orden, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medida. (000000000000000.0000)
	private Double f470_cant_2_entrega;  //Cantidad adicional, es obligatorio si el item maneja unidad adicional, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medida.
	private Double f470_cant_base_rechazo;  //Cantidad en unidad de la orden, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medida. (000000000000000.0000)
	private Double f470_cant_2_rechazo;  //Cantidad adicional, es obligatorio si el item maneja unidad adicional y reportaron cantidad base rechazo, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medida.
	private String f470_notas;  //Notas del movimiento
	private String f470_desc_varible;  //Descripcion del movimiento
	private String F_DESC_ITEM;  //Si tiene algún valor  el sistema al importar el registro valida que la descripción del item sea idem al de la base de datos. 
	private String F_ID_UM_INVENTARIO;
	
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

	public String getF470_id_item () {
		return getFormattedValue(f470_id_item ,7);
	}

	public void setF470_id_item (Integer f470_id_item) {
		this.f470_id_item = f470_id_item;
	}

	public String getF470_referencia_item () {
		return getFormattedValue(f470_referencia_item ,50);
	}

	public void setF470_referencia_item (String f470_referencia_item) {
		this.f470_referencia_item = f470_referencia_item;
	}

	public String getF470_codigo_barras () {
		return getFormattedValue(f470_codigo_barras ,20);
	}

	public void setF470_codigo_barras (String f470_codigo_barras) {
		this.f470_codigo_barras = f470_codigo_barras;
	}

	public String getF470_id_ext1_detalle () {
		return getFormattedValue(f470_id_ext1_detalle ,20);
	}

	public void setF470_id_ext1_detalle (String f470_id_ext1_detalle) {
		this.f470_id_ext1_detalle = f470_id_ext1_detalle;
	}

	public String getF470_id_ext2_detalle () {
		return getFormattedValue(f470_id_ext2_detalle ,20);
	}

	public void setF470_id_ext2_detalle (String f470_id_ext2_detalle) {
		this.f470_id_ext2_detalle = f470_id_ext2_detalle;
	}

	public String getF470_id_item_otros () {
		return getFormattedValue(f470_id_item_otros ,7);
	}

	public void setF470_id_item_otros (Integer f470_id_item_otros) {
		this.f470_id_item_otros = f470_id_item_otros;
	}

	public String getF470_referencia_item_otros () {
		return getFormattedValue(f470_referencia_item_otros ,50);
	}

	public void setF470_referencia_item_otros (String f470_referencia_item_otros) {
		this.f470_referencia_item_otros = f470_referencia_item_otros;
	}

	public String getF470_codigo_barras_otros () {
		return getFormattedValue(f470_codigo_barras_otros ,20);
	}

	public void setF470_codigo_barras_otros (String f470_codigo_barras_otros) {
		this.f470_codigo_barras_otros = f470_codigo_barras_otros;
	}

	public String getF470_id_ext1_detalle_otros () {
		return getFormattedValue(f470_id_ext1_detalle_otros ,20);
	}

	public void setF470_id_ext1_detalle_otros (String f470_id_ext1_detalle_otros) {
		this.f470_id_ext1_detalle_otros = f470_id_ext1_detalle_otros;
	}

	public String getF470_id_ext2_detalle_otros () {
		return getFormattedValue(f470_id_ext2_detalle_otros ,20);
	}

	public void setF470_id_ext2_detalle_otros (String f470_id_ext2_detalle_otros) {
		this.f470_id_ext2_detalle_otros = f470_id_ext2_detalle_otros;
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

	public String getF470_id_motivo_entrega () {
		return getFormattedValue(f470_id_motivo_entrega ,2);
	}

	public void setF470_id_motivo_entrega (String f470_id_motivo_entrega) {
		this.f470_id_motivo_entrega = f470_id_motivo_entrega;
	}

	public String getF470_id_motivo_rechazo () {
		return getFormattedValue(f470_id_motivo_rechazo ,2);
	}

	public void setF470_id_motivo_rechazo (String f470_id_motivo_rechazo) {
		this.f470_id_motivo_rechazo = f470_id_motivo_rechazo;
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

	public String getF470_cant_base_entrega () {
		return getFormattedValue(f470_cant_base_entrega ,20,4);
	}

	public void setF470_cant_base_entrega (Double f470_cant_base_entrega) {
		this.f470_cant_base_entrega = f470_cant_base_entrega;
	}

	public String getF470_cant_2_entrega () {
		return getFormattedValue(f470_cant_2_entrega ,20,4);
	}

	public void setF470_cant_2_entrega (Double f470_cant_2_entrega) {
		this.f470_cant_2_entrega = f470_cant_2_entrega;
	}

	public String getF470_cant_base_rechazo () {
		return getFormattedValue(f470_cant_base_rechazo ,20,4);
	}

	public void setF470_cant_base_rechazo (Double f470_cant_base_rechazo) {
		this.f470_cant_base_rechazo = f470_cant_base_rechazo;
	}

	public String getF470_cant_2_rechazo () {
		return getFormattedValue(f470_cant_2_rechazo ,20,4);
	}

	public void setF470_cant_2_rechazo (Double f470_cant_2_rechazo) {
		this.f470_cant_2_rechazo = f470_cant_2_rechazo;
	}

	public String getF470_notas () {
		return getFormattedValue(f470_notas ,255);
	}

	public void setF470_notas (String f470_notas) {
		this.f470_notas = f470_notas;
	}

	public String getF470_desc_varible () {
		return getFormattedValue(f470_desc_varible ,2000);
	}

	public void setF470_desc_varible (String f470_desc_varible) {
		this.f470_desc_varible = f470_desc_varible;
	}

	public String getF_desc_item () {
		return getFormattedValue(F_DESC_ITEM ,40);
	}

	public void setF_desc_item (String F_DESC_ITEM) {
		this.F_DESC_ITEM = F_DESC_ITEM;
	}

	public String getF_ID_UM_INVENTARIO() {
		return getFormattedValue(F_ID_UM_INVENTARIO ,4);
	}
	public void setF_ID_UM_INVENTARIO(String f_ID_UM_INVENTARIO) {
		F_ID_UM_INVENTARIO = f_ID_UM_INVENTARIO;
	}
	
	public DoctoentregasMovimientosVersión01 () {
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
		return "DoctoentregasMovimientosVersión01[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", f470_id_co =" + this.getF470_id_co  () +", f470_id_tipo_docto=" + this.getF470_id_tipo_docto () +", f470_consec_docto=" + this.getF470_consec_docto () +", f470_nro_registro=" + this.getF470_nro_registro () +", f850_id_tipo_docto=" + this.getF850_id_tipo_docto () +", f850_consec_docto=" + this.getF850_consec_docto () +", f470_id_item=" + this.getF470_id_item () +", f470_referencia_item=" + this.getF470_referencia_item () +", f470_codigo_barras=" + this.getF470_codigo_barras () +", f470_id_ext1_detalle=" + this.getF470_id_ext1_detalle () +", f470_id_ext2_detalle=" + this.getF470_id_ext2_detalle () +", f470_id_item_otros=" + this.getF470_id_item_otros () +", f470_referencia_item_otros=" + this.getF470_referencia_item_otros () +", f470_codigo_barras_otros=" + this.getF470_codigo_barras_otros () +", f470_id_ext1_detalle_otros=" + this.getF470_id_ext1_detalle_otros () +", f470_id_ext2_detalle_otros=" + this.getF470_id_ext2_detalle_otros () +", f470_id_bodega=" + this.getF470_id_bodega () +", f470_id_ubicación_aux=" + this.getF470_id_ubicación_aux () +", f470_id_lote=" + this.getF470_id_lote () +", f470_id_concepto=" + this.getF470_id_concepto () +", f470_id_motivo_entrega=" + this.getF470_id_motivo_entrega () +", f470_id_motivo_rechazo=" + this.getF470_id_motivo_rechazo () +", f470_id_co_movto=" + this.getF470_id_co_movto () +", f470_id_un_movto=" + this.getF470_id_un_movto () +", f470_id_ccosto_movto=" + this.getF470_id_ccosto_movto () +", f470_id_proyecto=" + this.getF470_id_proyecto () +", f470_cant_base_entrega=" + this.getF470_cant_base_entrega () +", f470_cant_2_entrega=" + this.getF470_cant_2_entrega () +", f470_cant_base_rechazo=" + this.getF470_cant_base_rechazo () +", f470_cant_2_rechazo=" + this.getF470_cant_2_rechazo () +", f470_notas=" + this.getF470_notas () +", f470_desc_varible=" + this.getF470_desc_varible () +", F_DESC_ITEM=" + this.getF_desc_item () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF470_id_co  ()+this.getF470_id_tipo_docto ()+this.getF470_consec_docto ()+this.getF470_nro_registro ()+this.getF850_id_tipo_docto ()+this.getF850_consec_docto ()+this.getF470_id_item ()+this.getF470_referencia_item ()+this.getF470_codigo_barras ()+this.getF470_id_ext1_detalle ()+this.getF470_id_ext2_detalle ()+this.getF470_id_item_otros ()+this.getF470_referencia_item_otros ()+this.getF470_codigo_barras_otros ()+this.getF470_id_ext1_detalle_otros ()+this.getF470_id_ext2_detalle_otros ()+this.getF470_id_bodega ()+this.getF470_id_ubicación_aux ()+this.getF470_id_lote ()+this.getF470_id_concepto ()+this.getF470_id_motivo_entrega ()+this.getF470_id_motivo_rechazo ()+this.getF470_id_co_movto ()+this.getF470_id_un_movto ()+this.getF470_id_ccosto_movto ()+this.getF470_id_proyecto ()+this.getF470_cant_base_entrega ()+this.getF470_cant_2_entrega ()+this.getF470_cant_base_rechazo ()+this.getF470_cant_2_rechazo ()+this.getF470_notas ()+this.getF470_desc_varible ()+this.getF_desc_item ()+ this.getF_ID_UM_INVENTARIO();
	}
}