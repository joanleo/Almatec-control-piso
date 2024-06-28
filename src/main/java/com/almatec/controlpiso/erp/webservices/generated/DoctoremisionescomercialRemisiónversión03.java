package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class DoctoremisionescomercialRemisiónversión03 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 460;  //Valor fijo = 460
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 3;  //Versión = 03
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la información del registro
	private Integer F_CONSEC_AUTO_REG;  //0=Manual, significa que respecta el consecutivo asignado en el plano, 1=Automático, significa que el consecutivo es recalculado con base en la tabla de consecutivos de docto.
	private String F350_ID_CO;  //Valida en maestro, código de centro de operación del documento
	private String F350_ID_TIPO_DOCTO;  //Valida en maestro, código de tipo de documento de remision 
	private Integer F350_CONSEC_DOCTO;  //Numero de documento 
	private String F350_FECHA;  //El formato debe ser AAAAMMDD
	private Integer F350_IND_ESTADO;  //0=Elaboración; 1=Aprobado
	private Integer F350_IND_IMPRESION;  //0=No Impreso; 1=Impreso
	private String F430_ID_TIPO_DOCTO;  //Valida en maestro, código de tipo de documento de pedidos
	private Integer F430_CONSEC_DOCTO;  //Numero de pedido comprometido, esto quiere decir que remisiona los registros que se comprometieron
	private String f462_id_vehiculo;  //Valida en maestro de vehiculos, obligatorio si graban información del transportador
	private String f462_id_tercero_transp;  //Código del transportador, obligatorio si graban información del transportador y existe vehículo
	private String f462_id_sucursal_transp;  //Sucursal del transportador, obligatorio si graban información del transportador y existe vehículo
	private String f462_id_tercero_conductor;  //Código del conductor, obligatorio si graban información del transportador y existe vehículo
	private String f462_nombre_conductor;  //Nombre del conductor, obligatorio si graban información del transportador y existe vehículo
	private String f462_identif_conductor;  //Identificación del conductor, obligatorio si graban información del transportador y existe vehículo
	private String f462_numero_guia;  //Numero de guia, oblogatoria si graban informacion del transportador y existe vehículo
	private Double f462_cajas;  //El formato debe ser (10 enteros + punto + 4 decimales). (0000000000.0000)
	private Double f462_peso;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidade de peso del sistema. (000000000000000.0000)
	private Double f462_volumen;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidade de volumen del sistema. (000000000000000.0000)
	private Double f462_valor_seguros;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la moneda (decimales unidades). (000000000000000.0000)
	private String f462_notas  ;  //Notas del transportador
	private String f460_id_cond_pago;  //Condicion de pago, si es vacio toma la condicion de pago del pedido, si no toma la del plano.

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

	public String getF350_id_co () {
		return getFormattedValue(F350_ID_CO ,3);
	}

	public void setF350_id_co (String F350_ID_CO) {
		this.F350_ID_CO = F350_ID_CO;
	}

	public String getF350_id_tipo_docto () {
		return getFormattedValue(F350_ID_TIPO_DOCTO ,3);
	}

	public void setF350_id_tipo_docto (String F350_ID_TIPO_DOCTO) {
		this.F350_ID_TIPO_DOCTO = F350_ID_TIPO_DOCTO;
	}

	public String getF350_consec_docto () {
		return getFormattedValue(F350_CONSEC_DOCTO ,8);
	}

	public void setF350_consec_docto (Integer F350_CONSEC_DOCTO) {
		this.F350_CONSEC_DOCTO = F350_CONSEC_DOCTO;
	}

	public String getF350_fecha () {
		return getFormattedValue(F350_FECHA ,8);
	}

	public void setF350_fecha (String F350_FECHA) {
		this.F350_FECHA = F350_FECHA;
	}

	public String getF350_ind_estado () {
		return getFormattedValue(F350_IND_ESTADO ,1);
	}

	public void setF350_ind_estado (Integer F350_IND_ESTADO) {
		this.F350_IND_ESTADO = F350_IND_ESTADO;
	}

	public String getF350_ind_impresion () {
		return getFormattedValue(F350_IND_IMPRESION ,1);
	}

	public void setF350_ind_impresion (Integer F350_IND_IMPRESION) {
		this.F350_IND_IMPRESION = F350_IND_IMPRESION;
	}

	public String getF430_id_tipo_docto () {
		return getFormattedValue(F430_ID_TIPO_DOCTO ,3);
	}

	public void setF430_id_tipo_docto (String F430_ID_TIPO_DOCTO) {
		this.F430_ID_TIPO_DOCTO = F430_ID_TIPO_DOCTO;
	}

	public String getF430_consec_docto () {
		return getFormattedValue(F430_CONSEC_DOCTO ,8);
	}

	public void setF430_consec_docto (Integer F430_CONSEC_DOCTO) {
		this.F430_CONSEC_DOCTO = F430_CONSEC_DOCTO;
	}

	public String getF462_id_vehiculo () {
		return getFormattedValue(f462_id_vehiculo ,10);
	}

	public void setF462_id_vehiculo (String f462_id_vehiculo) {
		this.f462_id_vehiculo = f462_id_vehiculo;
	}

	public String getF462_id_tercero_transp () {
		return getFormattedValue(f462_id_tercero_transp ,15);
	}

	public void setF462_id_tercero_transp (String f462_id_tercero_transp) {
		this.f462_id_tercero_transp = f462_id_tercero_transp;
	}

	public String getF462_id_sucursal_transp () {
		return getFormattedValue(f462_id_sucursal_transp ,3);
	}

	public void setF462_id_sucursal_transp (String f462_id_sucursal_transp) {
		this.f462_id_sucursal_transp = f462_id_sucursal_transp;
	}

	public String getF462_id_tercero_conductor () {
		return getFormattedValue(f462_id_tercero_conductor ,15);
	}

	public void setF462_id_tercero_conductor (String f462_id_tercero_conductor) {
		this.f462_id_tercero_conductor = f462_id_tercero_conductor;
	}

	public String getF462_nombre_conductor () {
		return getFormattedValue(f462_nombre_conductor ,50);
	}

	public void setF462_nombre_conductor (String f462_nombre_conductor) {
		this.f462_nombre_conductor = f462_nombre_conductor;
	}

	public String getF462_identif_conductor () {
		return getFormattedValue(f462_identif_conductor ,15);
	}

	public void setF462_identif_conductor (String f462_identif_conductor) {
		this.f462_identif_conductor = f462_identif_conductor;
	}

	public String getF462_numero_guia () {
		return getFormattedValue(f462_numero_guia ,30);
	}

	public void setF462_numero_guia (String f462_numero_guia) {
		this.f462_numero_guia = f462_numero_guia;
	}

	public String getF462_cajas () {
		return getFormattedValue(f462_cajas ,15,4);
	}

	public void setF462_cajas (Double f462_cajas) {
		this.f462_cajas = f462_cajas;
	}

	public String getF462_peso () {
		return getFormattedValue(f462_peso ,20,4);
	}

	public void setF462_peso (Double f462_peso) {
		this.f462_peso = f462_peso;
	}

	public String getF462_volumen () {
		return getFormattedValue(f462_volumen ,20,4);
	}

	public void setF462_volumen (Double f462_volumen) {
		this.f462_volumen = f462_volumen;
	}

	public String getF462_valor_seguros () {
		return getFormattedValue(f462_valor_seguros ,20,4);
	}

	public void setF462_valor_seguros (Double f462_valor_seguros) {
		this.f462_valor_seguros = f462_valor_seguros;
	}

	public String getF462_notas   () {
		return getFormattedValue(f462_notas   ,255);
	}

	public void setF462_notas   (String f462_notas  ) {
		this.f462_notas   = f462_notas  ;
	}

	public String getF460_id_cond_pago () {
		return getFormattedValue(f460_id_cond_pago ,3);
	}

	public void setF460_id_cond_pago (String f460_id_cond_pago) {
		this.f460_id_cond_pago = f460_id_cond_pago;
	}

	public DoctoremisionescomercialRemisiónversión03 () {
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
		return "DoctoremisionescomercialRemisiónversión03[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", F_CONSEC_AUTO_REG=" + this.getF_consec_auto_reg () +", F350_ID_CO=" + this.getF350_id_co () +", F350_ID_TIPO_DOCTO=" + this.getF350_id_tipo_docto () +", F350_CONSEC_DOCTO=" + this.getF350_consec_docto () +", F350_FECHA=" + this.getF350_fecha () +", F350_IND_ESTADO=" + this.getF350_ind_estado () +", F350_IND_IMPRESION=" + this.getF350_ind_impresion () +", F430_ID_TIPO_DOCTO=" + this.getF430_id_tipo_docto () +", F430_CONSEC_DOCTO=" + this.getF430_consec_docto () +", f462_id_vehiculo=" + this.getF462_id_vehiculo () +", f462_id_tercero_transp=" + this.getF462_id_tercero_transp () +", f462_id_sucursal_transp=" + this.getF462_id_sucursal_transp () +", f462_id_tercero_conductor=" + this.getF462_id_tercero_conductor () +", f462_nombre_conductor=" + this.getF462_nombre_conductor () +", f462_identif_conductor=" + this.getF462_identif_conductor () +", f462_numero_guia=" + this.getF462_numero_guia () +", f462_cajas=" + this.getF462_cajas () +", f462_peso=" + this.getF462_peso () +", f462_volumen=" + this.getF462_volumen () +", f462_valor_seguros=" + this.getF462_valor_seguros () +", f462_notas  =" + this.getF462_notas   () +", f460_id_cond_pago=" + this.getF460_id_cond_pago () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_consec_auto_reg ()+this.getF350_id_co ()+this.getF350_id_tipo_docto ()+this.getF350_consec_docto ()+this.getF350_fecha ()+this.getF350_ind_estado ()+this.getF350_ind_impresion ()+this.getF430_id_tipo_docto ()+this.getF430_consec_docto ()+this.getF462_id_vehiculo ()+this.getF462_id_tercero_transp ()+this.getF462_id_sucursal_transp ()+this.getF462_id_tercero_conductor ()+this.getF462_nombre_conductor ()+this.getF462_identif_conductor ()+this.getF462_numero_guia ()+this.getF462_cajas ()+this.getF462_peso ()+this.getF462_volumen ()+this.getF462_valor_seguros ()+this.getF462_notas   ()+this.getF460_id_cond_pago ();
	}
}