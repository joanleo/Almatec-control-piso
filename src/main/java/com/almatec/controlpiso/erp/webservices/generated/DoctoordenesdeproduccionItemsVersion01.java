package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class DoctoordenesdeproduccionItemsVersion01 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 851;  //Valor fijo = 851
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 1;  //Version = 01
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private String f851_id_co ;  //Valida en maestro, código de centro de operación del documento
	private String f851_id_tipo_docto;  //Valida en maestro, código de tipo de documento
	private Integer f851_consec_docto;  //Numero de documento
	private Integer f851_nro_registro;  //Numero de registro del movimiento
	private Integer f851_id_item;  //Codigo, es obligatorio si no va referencia ni codigo de barras
	private String f851_referencia_item;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f851_codigo_barras;  //Codigo, es obligatorio si no va codigo de item ni referencia
	private String f851_id_ext1_detalle;  //Es obligatorio si el ítem maneja extensión 1
	private String f851_id_ext2_detalle;  //Es obligatorio si el ítem maneja extensión 2
	private String f851_id_unidad_medida;  //Valida en maestro, unidad de medida del movimiento.
	private Double f851_porc_rendimiento;  //Porcentaje de rendimiento, el formato debe ser (3 enteros + punto + 4 decimales). (000.0000)
	private Double f851_cant_planeada_base;  //Cantidad planeada, el formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medida. (000000000000000.0000)
	private String f851_fecha_inicio;  //El formato debe ser AAAAMMDD
	private String f851_fecha_terminacion;  //El formato debe ser AAAAMMDD
	private String f851_id_metodo_lista;  //Valida en maestro, método de lista.
	private String f851_id_bodega_componentes;  //Valida en maestro, código de bodega. 
	private String f851_id_metodo_ruta;  //Valida en maestro, método de ruta.
	private String f851_id_lote;  //Valida en maestro, código del lote. Obligatorio si el item y la bodega manejan lotes.
	private String f851_notas;  //Notas del movimiento
	private String f851_id_bodega;
	
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

	public String getF851_id_co  () {
		return getFormattedValue(f851_id_co  ,3);
	}

	public void setF851_id_co  (String f851_id_co ) {
		this.f851_id_co  = f851_id_co ;
	}

	public String getF851_id_tipo_docto () {
		return getFormattedValue(f851_id_tipo_docto ,3);
	}

	public void setF851_id_tipo_docto (String f851_id_tipo_docto) {
		this.f851_id_tipo_docto = f851_id_tipo_docto;
	}

	public String getF851_consec_docto () {
		return getFormattedValue(f851_consec_docto ,8);
	}

	public void setF851_consec_docto (Integer f851_consec_docto) {
		this.f851_consec_docto = f851_consec_docto;
	}

	public String getF851_nro_registro () {
		return getFormattedValue(f851_nro_registro ,10);
	}

	public void setF851_nro_registro (Integer f851_nro_registro) {
		this.f851_nro_registro = f851_nro_registro;
	}

	public String getF851_id_item () {
		return getFormattedValue(f851_id_item ,7);
	}

	public void setF851_id_item (Integer f851_id_item) {
		this.f851_id_item = f851_id_item;
	}

	public String getF851_referencia_item () {
		return getFormattedValue(f851_referencia_item ,50);
	}

	public void setF851_referencia_item (String f851_referencia_item) {
		this.f851_referencia_item = f851_referencia_item;
	}

	public String getF851_codigo_barras () {
		return getFormattedValue(f851_codigo_barras ,20);
	}

	public void setF851_codigo_barras (String f851_codigo_barras) {
		this.f851_codigo_barras = f851_codigo_barras;
	}

	public String getF851_id_ext1_detalle () {
		return getFormattedValue(f851_id_ext1_detalle ,20);
	}

	public void setF851_id_ext1_detalle (String f851_id_ext1_detalle) {
		this.f851_id_ext1_detalle = f851_id_ext1_detalle;
	}

	public String getF851_id_ext2_detalle () {
		return getFormattedValue(f851_id_ext2_detalle ,20);
	}

	public void setF851_id_ext2_detalle (String f851_id_ext2_detalle) {
		this.f851_id_ext2_detalle = f851_id_ext2_detalle;
	}

	public String getF851_id_unidad_medida () {
		return getFormattedValue(f851_id_unidad_medida ,4);
	}

	public void setF851_id_unidad_medida (String f851_id_unidad_medida) {
		this.f851_id_unidad_medida = f851_id_unidad_medida;
	}

	public String getF851_porc_rendimiento () {
		return getFormattedValue(f851_porc_rendimiento ,8,4);
	}

	public void setF851_porc_rendimiento (Double f851_porc_rendimiento) {
		this.f851_porc_rendimiento = f851_porc_rendimiento;
	}

	public String getF851_cant_planeada_base () {
		return getFormattedValue(f851_cant_planeada_base ,20,4);
	}

	public void setF851_cant_planeada_base (Double f851_cant_planeada_base) {
		this.f851_cant_planeada_base = f851_cant_planeada_base;
	}

	public String getF851_fecha_inicio () {
		return getFormattedValue(f851_fecha_inicio ,8);
	}

	public void setF851_fecha_inicio (String f851_fecha_inicio) {
		this.f851_fecha_inicio = f851_fecha_inicio;
	}

	public String getF851_fecha_terminacion () {
		return getFormattedValue(f851_fecha_terminacion ,8);
	}

	public void setF851_fecha_terminacion (String f851_fecha_terminacion) {
		this.f851_fecha_terminacion = f851_fecha_terminacion;
	}

	public String getF851_id_metodo_lista () {
		return getFormattedValue(f851_id_metodo_lista ,4);
	}

	public void setF851_id_metodo_lista (String f851_id_metodo_lista) {
		this.f851_id_metodo_lista = f851_id_metodo_lista;
	}

	public String getF851_id_bodega_componentes () {
		return getFormattedValue(f851_id_bodega_componentes ,5);
	}

	public void setF851_id_bodega_componentes (String f851_id_bodega_componentes) {
		this.f851_id_bodega_componentes = f851_id_bodega_componentes;
	}

	public String getF851_id_metodo_ruta () {
		return getFormattedValue(f851_id_metodo_ruta ,4);
	}

	public void setF851_id_metodo_ruta (String f851_id_metodo_ruta) {
		this.f851_id_metodo_ruta = f851_id_metodo_ruta;
	}

	public String getF851_id_lote () {
		return getFormattedValue(f851_id_lote ,15);
	}

	public void setF851_id_lote (String f851_id_lote) {
		this.f851_id_lote = f851_id_lote;
	}

	public String getF851_notas () {
		return getFormattedValue(f851_notas ,2000);
	}

	public void setF851_notas (String f851_notas) {
		this.f851_notas = f851_notas;
	}

	public String getF851_id_bodega() {
		return getFormattedValue(f851_id_bodega, 5);
	}
	public void setF851_id_bodega(String f851_id_bodega) {
		this.f851_id_bodega = f851_id_bodega;
	}
	public DoctoordenesdeproduccionItemsVersion01 () {
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
		return "DoctoordenesdeproduccionItemsVersión01[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", f851_id_co =" + this.getF851_id_co  () +", f851_id_tipo_docto=" + this.getF851_id_tipo_docto () +", f851_consec_docto=" + this.getF851_consec_docto () +", f851_nro_registro=" + this.getF851_nro_registro () +", f851_id_item=" + this.getF851_id_item () +", f851_referencia_item=" + this.getF851_referencia_item () +", f851_codigo_barras=" + this.getF851_codigo_barras () +", f851_id_ext1_detalle=" + this.getF851_id_ext1_detalle () +", f851_id_ext2_detalle=" + this.getF851_id_ext2_detalle () +", f851_id_unidad_medida=" + this.getF851_id_unidad_medida () +", f851_porc_rendimiento=" + this.getF851_porc_rendimiento () +", f851_cant_planeada_base=" + this.getF851_cant_planeada_base () +", f851_fecha_inicio=" + this.getF851_fecha_inicio () +", f851_fecha_terminacion=" + this.getF851_fecha_terminacion () +", f851_id_metodo_lista=" + this.getF851_id_metodo_lista () +", f851_id_bodega_componentes=" + this.getF851_id_bodega_componentes () +", f851_id_metodo_ruta=" + this.getF851_id_metodo_ruta () +", f851_id_lote=" + this.getF851_id_lote () +", f851_notas=" + this.getF851_notas () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF851_id_co  ()+this.getF851_id_tipo_docto ()+this.getF851_consec_docto ()+this.getF851_nro_registro ()+this.getF851_id_item ()+this.getF851_referencia_item ()+this.getF851_codigo_barras ()+this.getF851_id_ext1_detalle ()+this.getF851_id_ext2_detalle ()+this.getF851_id_unidad_medida ()+this.getF851_porc_rendimiento ()+this.getF851_cant_planeada_base ()+this.getF851_fecha_inicio ()+this.getF851_fecha_terminacion ()+this.getF851_id_metodo_lista ()+this.getF851_id_bodega_componentes ()+this.getF851_id_metodo_ruta ()+this.getF851_id_lote ()+this.getF851_notas ()+this.getF851_id_bodega();
	}
}