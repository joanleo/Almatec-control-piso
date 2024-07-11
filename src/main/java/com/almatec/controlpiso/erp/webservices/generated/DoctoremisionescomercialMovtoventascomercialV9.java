package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class DoctoremisionescomercialMovtoventascomercialV9 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 470;  //Valor fijo = 470
	private Integer F_SUBTIPO_REG= 1;  //Valor fijo = 01
	private Integer F_VERSION_REG= 9;  //Version = 09
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private String f470_id_co ;  //Valida en maestro, código de centro de operación del documento
	private String f470_id_tipo_docto;  //Valida en maestro, código de tipo de documento
	private Integer f470_consec_docto;  //Numero de documento
	private Integer f470_nro_registro;  //Numero de registro del movimiento
	private String f_campo;
	private String f470_id_bodega;  //Valida en maestro, código de bodega
	private String f470_id_ubicacion_aux;  //Valida en maestro, obligatorio si la bodega maneja ubiaciones
	private String f470_id_lote;  //Valida en maestro, obligatorio si el item y la bodega manejan lotes
	private Integer f470_id_concepto;  //501=Ventas (clases 512) 502=Devoluciones (clases 516)
	private String f470_id_motivo;  //Valida en maestro, código de motivo
	private Integer f470_ind_obsequio;  //Indicador de obsequio 0=No, 1=Si
	private String f470_id_co_movto;  //Valida en maestro, código de centro de operación del movimiento
	private String f_campo_1;
	private String f470_id_ccosto_movto;  //Obligatorio si la  cuenta contable exige ccosto. Valida en maestro, código de centro de costo del movimiento.
	private String f470_id_proyecto;  //Valida en maestro, código de proyecto del movimiento
	private String f470_id_lista_precio;  //Valida en maestro, código de la lista de precio
	private String f470_id_unidad_precio;  //Valida en maestro, código de unidad de medida en que se encuentra el precio, normalmente es la misma unidad de captura pero cuando hay unidad adicional es importante definir en cual de las dos unidades se encuentra el precio
	private String f470_id_unidad_medida;  //Valida en maestro, código de unidad de medida del movimiento
	private Double f470_cant_base;  //Cantidad en unidad de captura, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medidad. (000000000000000.0000)
	private Double f470_cant_2;  //Cantidad adicional, es obligatorio si el item maneja unidad adicional, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medidad. (000000000000000.0000)
	private Double f470_vlr_bruto;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la moneda (decimales de compra y venta).  Se debe grabar el valor bruto sin impuestos. 
	private Integer f470_ind_naturaleza;  //Se valida con el indicador de naturaleza del motivo. Debe ser 1=Entrada(Entrada); 2=Salida (Devolucion)
	private Integer f470_ind_solo_valor;  //No aplica poner en 0
	private Integer f470_ind_impto_asumido;  //No aplica poner en 0
	private String f470_notas;  //Notas del movimiento
	private String f470_desc_variable;  //Descripcion del movimiento
	private String F_DESC_ITEM;  //Si tiene algún valor  el sistema al importar el registro valida que la descripción del item sea idem al de la base de datos. 
	private String F_ID_UM_INVENTARIO;  //Si tiene algún valor  el sistema al importar el registro valida que la unidad de inventario del item sea idem al de la base de datos. 
	private Integer f470_id_item;  //Codigo, es obligatorio si no va referencia ni codigo de barras
	private String f470_referencia_item;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f470_codigo_barras;  //Codigo, es obligatorio si no va codigo de item ni referencia
	private String f470_id_ext1_detalle;  //Es obligatorio si el ítem maneja extensión 1
	private String f470_id_ext2_detalle;  //Es obligatorio si el ítem maneja extensión 2
	private String f470_id_un_movto;  //Valida en maestro, código de unidad de negocio del movimiento.

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

	public String getF_campo() {
		return getFormattedValue(f470_id_bodega ,55);
	}
	public void setF_campo(String f_campo) {
		this.f_campo = f_campo;
	}
	public String getF470_id_bodega () {
		return getFormattedValue(f470_id_bodega ,5);
	}

	public void setF470_id_bodega (String f470_id_bodega) {
		this.f470_id_bodega = f470_id_bodega;
	}

	public String getF470_id_ubicacion_aux () {
		return getFormattedValue(f470_id_ubicacion_aux ,10);
	}

	public void setF470_id_ubicacion_aux (String f470_id_ubicacion_aux) {
		this.f470_id_ubicacion_aux = f470_id_ubicacion_aux;
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

	public String getF470_ind_obsequio () {
		return getFormattedValue(f470_ind_obsequio ,1);
	}

	public void setF470_ind_obsequio (Integer f470_ind_obsequio) {
		this.f470_ind_obsequio = f470_ind_obsequio;
	}

	public String getF470_id_co_movto () {
		return getFormattedValue(f470_id_co_movto ,3);
	}

	public void setF470_id_co_movto (String f470_id_co_movto) {
		this.f470_id_co_movto = f470_id_co_movto;
	}

	public String getF_campo_1() {
		return getFormattedValue(f470_id_ccosto_movto ,2);
	}
	public void setF_campo_1(String f_campo_1) {
		this.f_campo_1 = f_campo_1;
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

	public String getF470_id_lista_precio () {
		return getFormattedValue(f470_id_lista_precio ,3);
	}

	public void setF470_id_lista_precio (String f470_id_lista_precio) {
		this.f470_id_lista_precio = f470_id_lista_precio;
	}

	public String getF470_id_unidad_precio () {
		return getFormattedValue(f470_id_unidad_precio ,4);
	}

	public void setF470_id_unidad_precio (String f470_id_unidad_precio) {
		this.f470_id_unidad_precio = f470_id_unidad_precio;
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

	public String getF470_vlr_bruto () {
		return getFormattedValue(f470_vlr_bruto ,20,4);
	}

	public void setF470_vlr_bruto (Double f470_vlr_bruto) {
		this.f470_vlr_bruto = f470_vlr_bruto;
	}

	public String getF470_ind_naturaleza () {
		return getFormattedValue(f470_ind_naturaleza ,1);
	}

	public void setF470_ind_naturaleza (Integer f470_ind_naturaleza) {
		this.f470_ind_naturaleza = f470_ind_naturaleza;
	}

	public String getF470_ind_solo_valor () {
		return getFormattedValue(f470_ind_solo_valor ,1);
	}

	public void setF470_ind_solo_valor (Integer f470_ind_solo_valor) {
		this.f470_ind_solo_valor = f470_ind_solo_valor;
	}

	public String getF470_ind_impto_asumido () {
		return getFormattedValue(f470_ind_impto_asumido ,1);
	}

	public void setF470_ind_impto_asumido (Integer f470_ind_impto_asumido) {
		this.f470_ind_impto_asumido = f470_ind_impto_asumido;
	}

	public String getF470_notas () {
		return getFormattedValue(f470_notas ,255);
	}

	public void setF470_notas (String f470_notas) {
		this.f470_notas = f470_notas;
	}

	public String getF470_desc_variable () {
		return getFormattedValue(f470_desc_variable ,2000);
	}

	public void setF470_desc_variable (String f470_desc_variable) {
		this.f470_desc_variable = f470_desc_variable;
	}

	public String getF_desc_item () {
		return getFormattedValue(F_DESC_ITEM ,40);
	}

	public void setF_desc_item (String F_DESC_ITEM) {
		this.F_DESC_ITEM = F_DESC_ITEM;
	}

	public String getF_id_um_inventario () {
		return getFormattedValue(F_ID_UM_INVENTARIO ,4);
	}

	public void setF_id_um_inventario (String F_ID_UM_INVENTARIO) {
		this.F_ID_UM_INVENTARIO = F_ID_UM_INVENTARIO;
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

	public String getF470_id_un_movto () {
		return getFormattedValue(f470_id_un_movto ,20);
	}

	public void setF470_id_un_movto (String f470_id_un_movto) {
		this.f470_id_un_movto = f470_id_un_movto;
	}

	public DoctoremisionescomercialMovtoventascomercialV9 () {
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
		return "DoctoremisionescomercialMovtoventascomercialV9[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", f470_id_co =" + this.getF470_id_co  () +", f470_id_tipo_docto=" + this.getF470_id_tipo_docto () +", f470_consec_docto=" + this.getF470_consec_docto () +", f470_nro_registro=" + this.getF470_nro_registro () +", f470_id_bodega=" + this.getF470_id_bodega () +", f470_id_ubicacion_aux=" + this.getF470_id_ubicacion_aux () +", f470_id_lote=" + this.getF470_id_lote () +", f470_id_concepto=" + this.getF470_id_concepto () +", f470_id_motivo=" + this.getF470_id_motivo () +", f470_ind_obsequio=" + this.getF470_ind_obsequio () +", f470_id_co_movto=" + this.getF470_id_co_movto () +", f470_id_ccosto_movto=" + this.getF470_id_ccosto_movto () +", f470_id_proyecto=" + this.getF470_id_proyecto () +", f470_id_lista_precio=" + this.getF470_id_lista_precio () +", f470_id_unidad_precio=" + this.getF470_id_unidad_precio () +", f470_id_unidad_medida=" + this.getF470_id_unidad_medida () +", f470_cant_base=" + this.getF470_cant_base () +", f470_cant_2=" + this.getF470_cant_2 () +", f470_vlr_bruto=" + this.getF470_vlr_bruto () +", f470_ind_naturaleza=" + this.getF470_ind_naturaleza () +", f470_ind_solo_valor=" + this.getF470_ind_solo_valor () +", f470_ind_impto_asumido=" + this.getF470_ind_impto_asumido () +", f470_notas=" + this.getF470_notas () +", f470_desc_variable=" + this.getF470_desc_variable () +", F_DESC_ITEM=" + this.getF_desc_item () +", F_ID_UM_INVENTARIO=" + this.getF_id_um_inventario () +", f470_id_item=" + this.getF470_id_item () +", f470_referencia_item=" + this.getF470_referencia_item () +", f470_codigo_barras=" + this.getF470_codigo_barras () +", f470_id_ext1_detalle=" + this.getF470_id_ext1_detalle () +", f470_id_ext2_detalle=" + this.getF470_id_ext2_detalle () +", f470_id_un_movto=" + this.getF470_id_un_movto () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF470_id_co  ()+this.getF470_id_tipo_docto ()+this.getF470_consec_docto ()+this.getF470_nro_registro ()+this.getF_campo()+ this.getF470_id_bodega ()+this.getF470_id_ubicacion_aux ()+this.getF470_id_lote ()+this.getF470_id_concepto ()+this.getF470_id_motivo ()+this.getF470_ind_obsequio ()+this.getF470_id_co_movto ()+this.getF_campo_1()+ this.getF470_id_ccosto_movto ()+this.getF470_id_proyecto ()+this.getF470_id_lista_precio ()+this.getF470_id_unidad_precio ()+this.getF470_id_unidad_medida ()+this.getF470_cant_base ()+this.getF470_cant_2 ()+this.getF470_vlr_bruto ()+this.getF470_ind_naturaleza ()+this.getF470_ind_solo_valor ()+this.getF470_ind_impto_asumido ()+this.getF470_notas ()+this.getF470_desc_variable ()+this.getF_desc_item ()+this.getF_id_um_inventario ()+this.getF470_id_item ()+this.getF470_referencia_item ()+this.getF470_codigo_barras ()+this.getF470_id_ext1_detalle ()+this.getF470_id_ext2_detalle ()+this.getF470_id_un_movto ();
	}
}