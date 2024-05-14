package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class ConsumosdesdeCompromisosConsumos implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 450;  //Valor fijo = 450
	private Integer F_SUBTIPO_REG= 3;  //Valor fijo = 03
	private Integer F_VERSION_REG= 1;  //Version = 01
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private Integer F_CONSEC_AUTO_REG;  //0=Manual, significa que respecta el consecutivo asignado en el plano, 1=Automatico, significa que el consecutivo es recalculado con base en la tabla de consecutivos de docto.
	private String f350_id_co ;  //Valida en maestro, código de centro de operación del documento
	private String f350_id_tipo_docto;  //Valida en maestro, código de tipo de documento
	private Integer f350_consec_docto;  //Numero de documento
	private String f350_id_fecha;  //El formato debe ser AAAAMMDD
	private Integer f350_ind_estado;  //0=En elaboración, 1=Aprobado/Contabilizado, 2=Anulado
	private Integer f350_ind_impresión;  //0=No, 1=Si
	private Integer f350_id_clase_docto;  //710 = Consumos de componentes
	private String f350_docto_alterno;  //No se valida
	private String f350_notas;  //Notas del documento
	private String f350_id_motivo;  //Valida en maestro, código de motivo
	private String f350_id_proyecto;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f850_tipo_docto;
	private Integer f850_consec_docto;
	
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

	public String getF350_id_co  () {
		return getFormattedValue(f350_id_co  ,3);
	}

	public void setF350_id_co  (String f350_id_co ) {
		this.f350_id_co  = f350_id_co ;
	}

	public String getF350_id_tipo_docto () {
		return getFormattedValue(f350_id_tipo_docto ,3);
	}

	public void setF350_id_tipo_docto (String f350_id_tipo_docto) {
		this.f350_id_tipo_docto = f350_id_tipo_docto;
	}

	public String getF350_consec_docto () {
		return getFormattedValue(f350_consec_docto ,8);
	}

	public void setF350_consec_docto (Integer f350_consec_docto) {
		this.f350_consec_docto = f350_consec_docto;
	}

	public String getF350_id_fecha () {
		return getFormattedValue(f350_id_fecha ,8);
	}

	public void setF350_id_fecha (String f350_id_fecha) {
		this.f350_id_fecha = f350_id_fecha;
	}

	public String getF350_ind_estado () {
		return getFormattedValue(f350_ind_estado ,1);
	}

	public void setF350_ind_estado (Integer f350_ind_estado) {
		this.f350_ind_estado = f350_ind_estado;
	}

	public String getF350_ind_impresión () {
		return getFormattedValue(f350_ind_impresión ,1);
	}

	public void setF350_ind_impresión (Integer f350_ind_impresión) {
		this.f350_ind_impresión = f350_ind_impresión;
	}

	public String getF350_id_clase_docto () {
		return getFormattedValue(f350_id_clase_docto ,3);
	}

	public void setF350_id_clase_docto (Integer f350_id_clase_docto) {
		this.f350_id_clase_docto = f350_id_clase_docto;
	}

	public String getF350_docto_alterno () {
		return getFormattedValue(f350_docto_alterno ,15);
	}

	public void setF350_docto_alterno (String f350_docto_alterno) {
		this.f350_docto_alterno = f350_docto_alterno;
	}

	public String getF350_notas () {
		return getFormattedValue(f350_notas ,255);
	}

	public void setF350_notas (String f350_notas) {
		this.f350_notas = f350_notas;
	}

	public String getF350_id_motivo () {
		return getFormattedValue(f350_id_motivo ,2);
	}

	public void setF350_id_motivo (String f350_id_motivo) {
		this.f350_id_motivo = f350_id_motivo;
	}

	public String getF350_id_proyecto () {
		return getFormattedValue(f350_id_proyecto ,15);
	}

	public void setF350_id_proyecto (String f350_id_proyecto) {
		this.f350_id_proyecto = f350_id_proyecto;
	}

	public String getF850_tipo_docto() {
		return getFormattedValue(f850_tipo_docto ,3);
	}
	public void setF850_tipo_docto(String f850_tipo_docto) {
		this.f850_tipo_docto = f850_tipo_docto;
	}
	public String getF850_consec_docto() {
		return getFormattedValue(f850_consec_docto ,8);
	}
	public void setF850_consec_docto(Integer f850_consec_docto) {
		this.f850_consec_docto = f850_consec_docto;
	}
	public ConsumosdesdeCompromisosConsumos () {
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
		return "ConsumosdesdeCompromisosConsumos[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", F_CONSEC_AUTO_REG=" + this.getF_consec_auto_reg () +", f350_id_co =" + this.getF350_id_co  () +", f350_id_tipo_docto=" + this.getF350_id_tipo_docto () +", f350_consec_docto=" + this.getF350_consec_docto () +", f350_id_fecha=" + this.getF350_id_fecha () +", f350_ind_estado=" + this.getF350_ind_estado () +", f350_ind_impresión=" + this.getF350_ind_impresión () +", f350_id_clase_docto=" + this.getF350_id_clase_docto () +", f350_docto_alterno=" + this.getF350_docto_alterno () +", f350_notas=" + this.getF350_notas () +", f350_id_motivo=" + this.getF350_id_motivo () +", f350_id_proyecto=" + this.getF350_id_proyecto () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_consec_auto_reg ()+this.getF350_id_co  ()+this.getF350_id_tipo_docto ()+this.getF350_consec_docto ()+this.getF350_id_fecha ()+this.getF350_ind_estado ()+this.getF350_ind_impresión ()+this.getF350_id_clase_docto ()+this.getF350_docto_alterno ()+this.getF350_notas ()+this.getF350_id_motivo ()+this.getF350_id_proyecto ()+this.getF850_tipo_docto() +this.getF850_consec_docto();
	}
}