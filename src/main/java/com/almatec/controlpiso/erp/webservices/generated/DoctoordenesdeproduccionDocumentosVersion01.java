package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class DoctoordenesdeproduccionDocumentosVersion01 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 850;  //Valor fijo = 850
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 1;  //Version = 01
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private Integer F_CONSEC_AUTO_REG;  //0=Manual, significa que respecta el consecutivo asignado en el plano, 1=Automatico, significa que el consecutivo es recalculado con base en la tabla de consecutivos de docto.
	private String f850_id_co ;  //Valida en maestro, código de centro de operación del documento
	private String f850_id_tipo_docto;  //Valida en maestro, código de tipo de documento
	private Integer f850_consec_docto;  //Numero de documento
	private String f850_fecha;  //El formato debe ser AAAAMMDD
	private Integer f850_ind_estado;  //0=En elaboración, 1=Aprobado, 9=Anulado
	private Integer f850_ind_impresión;  //0=No, 1=Si
	private Integer f850_id_clase_docto;  //701=Orden de producción directa
	private String f850_tercero_planificador;  //Valida en maestro, terero planificador.
	private String f850_id_tipo_docto_op_padre;  //Valida en maestro, código de tipo de documento
	private Integer f850_consec_docto_op_padre;  //Numero de documento
	private String f850_id_instalacion;  //Valida en maestro, código de la instalación.
	private String f850_clase_op;  //Valida en maestro, código de la clase de orden de producción.
	private String f850_referencia_1;  //Referencia 1 del documento
	private String f850_referencia_2;  //Referencia 2 del documento
	private String f850_referencia_3;  //Referencia 3 del documento
	private String f850_notas;  //Notas del documento
	private String f850_id_co_pv;  //Valida en maestro, código de centro de operación del documento de pedido. Obligatorio si la clase genera bajo pedido
	private String f850_id_tipo_docto_pv;  //Valida en maestro, código de tipo de documento de pedido. Obligatorio si la clase genera bajo pedido
	private Integer f850_consec_docto_pv;  //Numero de documento de pedido. Obligatorio si la clase genera bajo pedido

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

	public String getF_consec_auto_reg () {
		return getFormattedValue(F_CONSEC_AUTO_REG ,1);
	}

	public void setF_consec_auto_reg (Integer F_CONSEC_AUTO_REG) {
		this.F_CONSEC_AUTO_REG = F_CONSEC_AUTO_REG;
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

	public String getF850_fecha () {
		return getFormattedValue(f850_fecha ,8);
	}

	public void setF850_fecha (String f850_fecha) {
		this.f850_fecha = f850_fecha;
	}

	public String getF850_ind_estado () {
		return getFormattedValue(f850_ind_estado ,1);
	}

	public void setF850_ind_estado (Integer f850_ind_estado) {
		this.f850_ind_estado = f850_ind_estado;
	}

	public String getF850_ind_impresión () {
		return getFormattedValue(f850_ind_impresión ,1);
	}

	public void setF850_ind_impresión (Integer f850_ind_impresión) {
		this.f850_ind_impresión = f850_ind_impresión;
	}

	public String getF850_id_clase_docto () {
		return getFormattedValue(f850_id_clase_docto ,3);
	}

	public void setF850_id_clase_docto (Integer f850_id_clase_docto) {
		this.f850_id_clase_docto = f850_id_clase_docto;
	}

	public String getF850_tercero_planificador () {
		return getFormattedValue(f850_tercero_planificador ,15);
	}

	public void setF850_tercero_planificador (String f850_tercero_planificador) {
		this.f850_tercero_planificador = f850_tercero_planificador;
	}

	public String getF850_id_tipo_docto_op_padre () {
		return getFormattedValue(f850_id_tipo_docto_op_padre ,3);
	}

	public void setF850_id_tipo_docto_op_padre (String f850_id_tipo_docto_op_padre) {
		this.f850_id_tipo_docto_op_padre = f850_id_tipo_docto_op_padre;
	}

	public String getF850_consec_docto_op_padre () {
		return getFormattedValue(f850_consec_docto_op_padre ,8);
	}

	public void setF850_consec_docto_op_padre (Integer f850_consec_docto_op_padre) {
		this.f850_consec_docto_op_padre = f850_consec_docto_op_padre;
	}

	public String getF850_id_instalacion () {
		return getFormattedValue(f850_id_instalacion ,3);
	}

	public void setF850_id_instalacion (String f850_id_instalacion) {
		this.f850_id_instalacion = f850_id_instalacion;
	}

	public String getF850_clase_op () {
		return getFormattedValue(f850_clase_op ,3);
	}

	public void setF850_clase_op (String f850_clase_op) {
		this.f850_clase_op = f850_clase_op;
	}

	public String getF850_referencia_1 () {
		return getFormattedValue(f850_referencia_1 ,30);
	}

	public void setF850_referencia_1 (String f850_referencia_1) {
		this.f850_referencia_1 = f850_referencia_1;
	}

	public String getF850_referencia_2 () {
		return getFormattedValue(f850_referencia_2 ,30);
	}

	public void setF850_referencia_2 (String f850_referencia_2) {
		this.f850_referencia_2 = f850_referencia_2;
	}

	public String getF850_referencia_3 () {
		return getFormattedValue(f850_referencia_3 ,30);
	}

	public void setF850_referencia_3 (String f850_referencia_3) {
		this.f850_referencia_3 = f850_referencia_3;
	}

	public String getF850_notas () {
		return getFormattedValue(f850_notas ,2000);
	}

	public void setF850_notas (String f850_notas) {
		this.f850_notas = f850_notas;
	}

	public String getF850_id_co_pv () {
		return getFormattedValue(f850_id_co_pv ,3);
	}

	public void setF850_id_co_pv (String f850_id_co_pv) {
		this.f850_id_co_pv = f850_id_co_pv;
	}

	public String getF850_id_tipo_docto_pv () {
		return getFormattedValue(f850_id_tipo_docto_pv ,3);
	}

	public void setF850_id_tipo_docto_pv (String f850_id_tipo_docto_pv) {
		this.f850_id_tipo_docto_pv = f850_id_tipo_docto_pv;
	}

	public String getF850_consec_docto_pv () {
		return getFormattedValue(f850_consec_docto_pv ,8);
	}

	public void setF850_consec_docto_pv (Integer f850_consec_docto_pv) {
		this.f850_consec_docto_pv = f850_consec_docto_pv;
	}

	public DoctoordenesdeproduccionDocumentosVersion01 () {
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
		return "DoctoordenesdeproduccionDocumentosVersión01[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", F_CONSEC_AUTO_REG=" + this.getF_consec_auto_reg () +", f850_id_co =" + this.getF850_id_co  () +", f850_id_tipo_docto=" + this.getF850_id_tipo_docto () +", f850_consec_docto=" + this.getF850_consec_docto () +", f850_fecha=" + this.getF850_fecha () +", f850_ind_estado=" + this.getF850_ind_estado () +", f850_ind_impresión=" + this.getF850_ind_impresión () +", f850_id_clase_docto=" + this.getF850_id_clase_docto () +", f850_tercero_planificador=" + this.getF850_tercero_planificador () +", f850_id_tipo_docto_op_padre=" + this.getF850_id_tipo_docto_op_padre () +", f850_consec_docto_op_padre=" + this.getF850_consec_docto_op_padre () +", f850_id_instalacion=" + this.getF850_id_instalacion () +", f850_clase_op=" + this.getF850_clase_op () +", f850_referencia_1=" + this.getF850_referencia_1 () +", f850_referencia_2=" + this.getF850_referencia_2 () +", f850_referencia_3=" + this.getF850_referencia_3 () +", f850_notas=" + this.getF850_notas () +", f850_id_co_pv=" + this.getF850_id_co_pv () +", f850_id_tipo_docto_pv=" + this.getF850_id_tipo_docto_pv () +", f850_consec_docto_pv=" + this.getF850_consec_docto_pv () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_consec_auto_reg ()+this.getF850_id_co  ()+this.getF850_id_tipo_docto ()+this.getF850_consec_docto ()+this.getF850_fecha ()+this.getF850_ind_estado ()+this.getF850_ind_impresión ()+this.getF850_id_clase_docto ()+this.getF850_tercero_planificador ()+this.getF850_id_tipo_docto_op_padre ()+this.getF850_consec_docto_op_padre ()+this.getF850_id_instalacion ()+this.getF850_clase_op ()+this.getF850_referencia_1 ()+this.getF850_referencia_2 ()+this.getF850_referencia_3 ()+this.getF850_notas ()+this.getF850_id_co_pv ()+this.getF850_id_tipo_docto_pv ()+this.getF850_consec_docto_pv ();
	}
}