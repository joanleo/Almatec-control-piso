package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class DoctoTEPMovimientosVersion01 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 880;  //Valor fijo = 880
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 1;  //Version = 01
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private String f880_id_co ;  //Valida en maestro, código de centro de operación del documento
	private String f880_id_tipo_docto;  //Valida en maestro, código de tipo de documento
	private Integer f880_consec_docto;  //Numero de documento
	private Integer f880_nro_registro;  //Numero de registro del movimiento
	private String f880_id_tipo_docto_op;  //Valida en maestro, código de tipo de documento de la Orden de producción
	private Integer f880_consec_docto_op;  //Numero de documento de la Orden de producción
	private Integer f880_id_item;  //Codigo, es obligatorio si no va referencia ni codigo de barras, Obligatorio si no es consolidado
	private String f880_referencia_item;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f880_codigo_barras;  //Codigo, es obligatorio si no va codigo de item ni referencia
	private String f880_id_ext1_detalle;  //Es obligatorio si el ítem maneja extensión 1
	private String f880_id_ext2_detalle;  //Es obligatorio si el ítem maneja extensión 2
	private Integer f880_numero_operacion  ;  //Número de operación, debe estar relacionada a la OP
	private String f880_rowid_ctrabajo  ;  //Se valida con el maestro, obligatorio si es por empleado
	private Integer f880_ind_tipo_hora  ;  //0. Horas ejecución 1. Horas máquina 2. Horas alistamiento 3. Tiempo de paro 4. Horas indirectas 9. Ajuste cantidad
	private String f880_id_labor  ;  //Se valida con el maestro, obligatorio si tipo de hora es 0, 2 y 4, y el sistema maneja labores y la clase de docto es 730
	private String f880_id_maquina  ;  //Se valida con el maestro, obligatorio si es tipo de hora es 1
	private Integer f880_ind_operacion_completa  ;  //0. Operación sin completar 1. Operación completa
	private Double f880_cant_completa_base  ;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad del ítem OP (000000000000000.0000)
	private Double f880_cant_retrabajo_base  ;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad del ítem OP (000000000000000.0000) Debe ser menor o igual a cantidad completa
	private Double f880_cant_rechazo_base  ;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad del ítem OP (000000000000000.0000) 
	private String f880_id_motivo_tep_rechazos  ;  //Se valida con el maestro de motivos de TEP, obligatorio si reportan cantidad rechazo
	private Double f880_hora_inicial  ;  //El formato debe ser (2 enteros + punto + 2 decimales) (00.00) Obligatorio dependiendo del parámetro del sistema
	private Double f880_hora_final  ;  //El formato debe ser (2 enteros + punto + 2 decimales) (00.00) Obligatorio dependiendo del parámetro del sistema, debe ser menor a la inicial
	private Double f880_horas  ;  //El formato debe ser (2 enteros + punto + 2 decimales) (00.00) Obligatorio dependiendo del parámetro del sistema
	private String f880_id_motivo_tep_paro  ;  //Se valida con el maestro de motivos de TEP, obligatorio si tipo de hora es 3
	private String f880_notas;
	
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

	public String getF880_id_co  () {
		return getFormattedValue(f880_id_co  ,3);
	}

	public void setF880_id_co  (String f880_id_co ) {
		this.f880_id_co  = f880_id_co ;
	}

	public String getF880_id_tipo_docto () {
		return getFormattedValue(f880_id_tipo_docto ,3);
	}

	public void setF880_id_tipo_docto (String f880_id_tipo_docto) {
		this.f880_id_tipo_docto = f880_id_tipo_docto;
	}

	public String getF880_consec_docto () {
		return getFormattedValue(f880_consec_docto ,8);
	}

	public void setF880_consec_docto (Integer f880_consec_docto) {
		this.f880_consec_docto = f880_consec_docto;
	}

	public String getF880_nro_registro () {
		return getFormattedValue(f880_nro_registro ,10);
	}

	public void setF880_nro_registro (Integer f880_nro_registro) {
		this.f880_nro_registro = f880_nro_registro;
	}

	public String getF880_id_tipo_docto_op () {
		return getFormattedValue(f880_id_tipo_docto_op ,3);
	}

	public void setF880_id_tipo_docto_op (String f880_id_tipo_docto) {
		this.f880_id_tipo_docto_op = f880_id_tipo_docto;
	}

	public String getF880_consec_docto_op () {
		return getFormattedValue(f880_consec_docto_op ,8);
	}

	public void setF880_consec_docto_op (Integer f880_consec_docto) {
		this.f880_consec_docto_op = f880_consec_docto;
	}

	public String getF880_id_item () {
		return getFormattedValue(f880_id_item ,7);
	}

	public void setF880_id_item (Integer f880_id_item) {
		this.f880_id_item = f880_id_item;
	}

	public String getF880_referencia_item () {
		return getFormattedValue(f880_referencia_item ,50);
	}

	public void setF880_referencia_item (String f880_referencia_item) {
		this.f880_referencia_item = f880_referencia_item;
	}

	public String getF880_codigo_barras () {
		return getFormattedValue(f880_codigo_barras ,20);
	}

	public void setF880_codigo_barras (String f880_codigo_barras) {
		this.f880_codigo_barras = f880_codigo_barras;
	}

	public String getF880_id_ext1_detalle () {
		return getFormattedValue(f880_id_ext1_detalle ,20);
	}

	public void setF880_id_ext1_detalle (String f880_id_ext1_detalle) {
		this.f880_id_ext1_detalle = f880_id_ext1_detalle;
	}

	public String getF880_id_ext2_detalle () {
		return getFormattedValue(f880_id_ext2_detalle ,20);
	}

	public void setF880_id_ext2_detalle (String f880_id_ext2_detalle) {
		this.f880_id_ext2_detalle = f880_id_ext2_detalle;
	}

	public String getF880_numero_operacion   () {
		return getFormattedValue(f880_numero_operacion   ,4);
	}

	public void setF880_numero_operacion   (Integer f880_numero_operacion  ) {
		this.f880_numero_operacion   = f880_numero_operacion  ;
	}

	public String getF880_rowid_ctrabajo   () {
		return getFormattedValue(f880_rowid_ctrabajo   ,15);
	}

	public void setF880_rowid_ctrabajo   (String f880_rowid_ctrabajo  ) {
		this.f880_rowid_ctrabajo   = f880_rowid_ctrabajo  ;
	}

	public String getF880_ind_tipo_hora   () {
		return getFormattedValue(f880_ind_tipo_hora   ,1);
	}

	public void setF880_ind_tipo_hora   (Integer f880_ind_tipo_hora  ) {
		this.f880_ind_tipo_hora   = f880_ind_tipo_hora  ;
	}

	public String getF880_id_labor   () {
		return getFormattedValue(f880_id_labor   ,15);
	}

	public void setF880_id_labor   (String f880_id_labor  ) {
		this.f880_id_labor   = f880_id_labor  ;
	}

	public String getF880_id_maquina   () {
		return getFormattedValue(f880_id_maquina   ,15);
	}

	public void setF880_id_maquina   (String f880_id_maquina  ) {
		this.f880_id_maquina   = f880_id_maquina  ;
	}

	public String getF880_ind_operacion_completa   () {
		return getFormattedValue(f880_ind_operacion_completa   ,1);
	}

	public void setF880_ind_operacion_completa   (Integer f880_ind_operacion_completa  ) {
		this.f880_ind_operacion_completa   = f880_ind_operacion_completa  ;
	}

	public String getF880_cant_completa_base   () {
		return getFormattedValue(f880_cant_completa_base   ,20,4);
	}

	public void setF880_cant_completa_base   (Double f880_cant_completa_base  ) {
		this.f880_cant_completa_base   = f880_cant_completa_base  ;
	}

	public String getF880_cant_retrabajo_base   () {
		return getFormattedValue(f880_cant_retrabajo_base   ,20,4);
	}

	public void setF880_cant_retrabajo_base   (Double f880_cant_retrabajo_base  ) {
		this.f880_cant_retrabajo_base   = f880_cant_retrabajo_base  ;
	}

	public String getF880_cant_rechazo_base   () {
		return getFormattedValue(f880_cant_rechazo_base   ,20,4);
	}

	public void setF880_cant_rechazo_base   (Double f880_cant_rechazo_base  ) {
		this.f880_cant_rechazo_base   = f880_cant_rechazo_base  ;
	}

	public String getF880_id_motivo_tep_rechazos   () {
		return getFormattedValue(f880_id_motivo_tep_rechazos   ,2);
	}

	public void setF880_id_motivo_tep_rechazos   (String f880_id_motivo_tep_rechazos  ) {
		this.f880_id_motivo_tep_rechazos   = f880_id_motivo_tep_rechazos  ;
	}

	public String getF880_hora_inicial   () {
		return getFormattedValue(f880_hora_inicial   ,5,2);
	}

	public void setF880_hora_inicial   (Double f880_hora_inicial  ) {
		this.f880_hora_inicial   = f880_hora_inicial  ;
	}

	public String getF880_hora_final   () {
		return getFormattedValue(f880_hora_final   ,5,2);
	}

	public void setF880_hora_final   (Double f880_hora_final  ) {
		this.f880_hora_final   = f880_hora_final  ;
	}

	public String getF880_horas   () {
		return getFormattedValue(f880_horas   ,5,2);
	}

	public void setF880_horas   (Double f880_horas  ) {
		this.f880_horas   = f880_horas  ;
	}

	public String getF880_id_motivo_tep_paro   () {
		return getFormattedValue(f880_id_motivo_tep_paro   ,2);
	}

	public void setF880_id_motivo_tep_paro   (String f880_id_motivo_tep_paro  ) {
		this.f880_id_motivo_tep_paro   = f880_id_motivo_tep_paro  ;
	}

	public String getF880_notas() {
		return getFormattedValue(f880_notas   ,255);
	}
	public void setF880_notas(String f880_notas) {
		this.f880_notas = f880_notas;
	}
	public DoctoTEPMovimientosVersion01 () {
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
		return "DoctoTEPMovimientosVersión01[F_NUMERO_REG=" + this.getF_numero_reg() +", F_TIPO_REG=" + this.getF_tipo_reg() +", F_SUBTIPO_REG=" + this.getF_subtipo_reg() +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", f880_id_co =" + this.getF880_id_co() +", f880_id_tipo_docto=" + this.getF880_id_tipo_docto() +", f880_consec_docto=" + this.getF880_consec_docto() +", f880_nro_registro=" + this.getF880_nro_registro() +", f880_id_tipo_docto=" + this.getF880_id_tipo_docto() +", f880_consec_docto_op=" + this.getF880_consec_docto_op() +", f880_id_item=" + this.getF880_id_item () +", f880_referencia_item=" + this.getF880_referencia_item () +", f880_codigo_barras=" + this.getF880_codigo_barras () +", f880_id_ext1_detalle=" + this.getF880_id_ext1_detalle () +", f880_id_ext2_detalle=" + this.getF880_id_ext2_detalle () +", f880_numero_operacion  =" + this.getF880_numero_operacion   () +", f880_rowid_ctrabajo  =" + this.getF880_rowid_ctrabajo   () +", f880_ind_tipo_hora  =" + this.getF880_ind_tipo_hora   () +", f880_id_labor  =" + this.getF880_id_labor   () +", f880_id_maquina  =" + this.getF880_id_maquina   () +", f880_ind_operacion_completa  =" + this.getF880_ind_operacion_completa   () +", f880_cant_completa_base  =" + this.getF880_cant_completa_base   () +", f880_cant_retrabajo_base  =" + this.getF880_cant_retrabajo_base   () +", f880_cant_rechazo_base  =" + this.getF880_cant_rechazo_base   () +", f880_id_motivo_tep_rechazos  =" + this.getF880_id_motivo_tep_rechazos() +", f880_hora_inicial  =" + this.getF880_hora_inicial   () +", f880_hora_final  =" + this.getF880_hora_final() +", f880_horas  =" + this.getF880_horas() +", f880_id_motivo_tep_paro  =" + this.getF880_id_motivo_tep_paro() +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF880_id_co  ()+this.getF880_id_tipo_docto ()+this.getF880_consec_docto ()+this.getF880_nro_registro ()+this.getF880_id_tipo_docto_op ()+this.getF880_consec_docto_op ()+this.getF880_id_item ()+this.getF880_referencia_item ()+this.getF880_codigo_barras ()+this.getF880_id_ext1_detalle ()+this.getF880_id_ext2_detalle ()+this.getF880_numero_operacion   ()+this.getF880_rowid_ctrabajo   ()+this.getF880_ind_tipo_hora   ()+this.getF880_id_labor   ()+this.getF880_id_maquina   ()+this.getF880_ind_operacion_completa   ()+this.getF880_cant_completa_base   ()+this.getF880_cant_retrabajo_base   ()+this.getF880_cant_rechazo_base   ()+this.getF880_id_motivo_tep_rechazos   ()+this.getF880_hora_inicial   ()+this.getF880_hora_final   ()+this.getF880_horas   ()+this.getF880_id_motivo_tep_paro   ()+ this.getF880_notas();
	}
}