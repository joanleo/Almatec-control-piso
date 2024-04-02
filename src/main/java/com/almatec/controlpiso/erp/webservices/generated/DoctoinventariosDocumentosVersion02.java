package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class DoctoinventariosDocumentosVersion02 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 450;  //Valor fijo = 450
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 2;  //Version = 02
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private Integer F_CONSEC_AUTO_REG;  //0=Manual, significa que respecta el consecutivo asignado en el plano, 1=Automatico, significa que el consecutivo es recalculado con base en la tabla de consecutivos de docto. 2 = Transacción granulada.
	private String f350_id_co ;  //Valida en maestro, código de centro de operación del documento
	private String f350_id_tipo_docto;  //Valida en maestro, código de tipo de documento
	private Integer f350_consec_docto;  //Numero de documento
	private String f350_fecha;  //El formato debe ser AAAAMMDD
	private String f350_id_tercero;  //Valida en maestro de terceros.
	private Integer f350_id_clase_docto;  //61=Entrada; 62=Salida; 63=Ajustes; 67=Transferencias; 70=Procesos;  69=Saldos Iniciales; 65=Transferencia salido transito; 66=Transferencia entrada transito; 73=Transferencia devolucion transito
	private Integer f350_ind_estado;  //0=En elaboración, 1=Aprobado/Contabilizado, 2=Anulado
	private Integer f350_ind_impresión;  //0=No, 1=Si
	private String f350_notas;  //Notas del documento
	private Integer f450_id_concepto;  //601=Entrada; 602=Salida; 603=Ajustes; 607=Transferencias; 610=Procesos; 699=Saldos Iniciales; 605=Transferencia en transito
	private String f450_id_bodega_salida;  //Valida con maestro y es obligatoria para transferencias
	private String f450_id_bodega_entrada;  //Valida con maestro y es obligatoria para transferencias
	private String f450_docto_alterno;  //No se valida
	private String f350_id_co_base;  //Valida en maestro, código de centro de operación del documento
	private String f350_id_tipo_docto_base;  //Valida en maestro, código de tipo de documento
	private Integer f350_consec_docto_base;  //Numero de documento de tansito, obligatorio para Entradas Transito y Devoluciones Transito
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
	private String f462_notas;
	
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

	public String getF350_fecha () {
		return getFormattedValue(f350_fecha ,8);
	}

	public void setF350_fecha (String f350_fecha) {
		this.f350_fecha = f350_fecha;
	}

	public String getF350_id_tercero () {
		return getFormattedValue(f350_id_tercero ,15);
	}

	public void setF350_id_tercero (String f350_id_tercero) {
		this.f350_id_tercero = f350_id_tercero;
	}

	public String getF350_id_clase_docto () {
		return getFormattedValue(f350_id_clase_docto ,3);
	}

	public void setF350_id_clase_docto (Integer f350_id_clase_docto) {
		this.f350_id_clase_docto = f350_id_clase_docto;
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

	public String getF350_notas () {
		return getFormattedValue(f350_notas ,255);
	}

	public void setF350_notas (String f350_notas) {
		this.f350_notas = f350_notas;
	}

	public String getF450_id_concepto () {
		return getFormattedValue(f450_id_concepto ,3);
	}

	public void setF450_id_concepto (Integer f450_id_concepto) {
		this.f450_id_concepto = f450_id_concepto;
	}

	public String getF450_id_bodega_salida () {
		return getFormattedValue(f450_id_bodega_salida ,5);
	}

	public void setF450_id_bodega_salida (String f450_id_bodega_salida) {
		this.f450_id_bodega_salida = f450_id_bodega_salida;
	}

	public String getF450_id_bodega_entrada () {
		return getFormattedValue(f450_id_bodega_entrada ,5);
	}

	public void setF450_id_bodega_entrada (String f450_id_bodega_entrada) {
		this.f450_id_bodega_entrada = f450_id_bodega_entrada;
	}

	public String getF450_docto_alterno () {
		return getFormattedValue(f450_docto_alterno ,15);
	}

	public void setF450_docto_alterno (String f450_docto_alterno) {
		this.f450_docto_alterno = f450_docto_alterno;
	}

	public String getF350_id_co_base () {
		return getFormattedValue(f350_id_co_base ,3);
	}

	public void setF350_id_co_base (String f350_id_co_base) {
		this.f350_id_co_base = f350_id_co_base;
	}

	public String getF350_id_tipo_docto_base () {
		return getFormattedValue(f350_id_tipo_docto_base ,3);
	}

	public void setF350_id_tipo_docto_base (String f350_id_tipo_docto_base) {
		this.f350_id_tipo_docto_base = f350_id_tipo_docto_base;
	}

	public String getF350_consec_docto_base () {
		return getFormattedValue(f350_consec_docto_base ,8);
	}

	public void setF350_consec_docto_base (Integer f350_consec_docto_base) {
		this.f350_consec_docto_base = f350_consec_docto_base;
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
	
	public String getF462_notas() {
		return getFormattedValue(f462_notas ,255);
	}
	public void setF462_notas(String f462_notas) {
		this.f462_notas = f462_notas;
	}

	public DoctoinventariosDocumentosVersion02 () {
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
		return "DoctoinventariosDocumentosVersión02[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", F_CONSEC_AUTO_REG=" + this.getF_consec_auto_reg () +", f350_id_co =" + this.getF350_id_co  () +", f350_id_tipo_docto=" + this.getF350_id_tipo_docto () +", f350_consec_docto=" + this.getF350_consec_docto () +", f350_fecha=" + this.getF350_fecha () +", f350_id_tercero=" + this.getF350_id_tercero () +", f350_id_clase_docto=" + this.getF350_id_clase_docto () +", f350_ind_estado=" + this.getF350_ind_estado () +", f350_ind_impresión=" + this.getF350_ind_impresión () +", f350_notas=" + this.getF350_notas () +", f450_id_concepto=" + this.getF450_id_concepto () +", f450_id_bodega_salida=" + this.getF450_id_bodega_salida () +", f450_id_bodega_entrada=" + this.getF450_id_bodega_entrada () +", f450_docto_alterno=" + this.getF450_docto_alterno () +", f350_id_co_base=" + this.getF350_id_co_base () +", f350_id_tipo_docto_base=" + this.getF350_id_tipo_docto_base () +", f350_consec_docto_base=" + this.getF350_consec_docto_base () +", f462_id_vehiculo=" + this.getF462_id_vehiculo () +", f462_id_tercero_transp=" + this.getF462_id_tercero_transp () +", f462_id_sucursal_transp=" + this.getF462_id_sucursal_transp () +", f462_id_tercero_conductor=" + this.getF462_id_tercero_conductor () +", f462_nombre_conductor=" + this.getF462_nombre_conductor () +", f462_identif_conductor=" + this.getF462_identif_conductor () +", f462_numero_guia=" + this.getF462_numero_guia () +", f462_cajas=" + this.getF462_cajas () +", f462_peso=" + this.getF462_peso () +", f462_volumen=" + this.getF462_volumen () +", f462_valor_seguros=" + this.getF462_valor_seguros () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_consec_auto_reg ()+this.getF350_id_co  ()+this.getF350_id_tipo_docto ()+this.getF350_consec_docto ()+this.getF350_fecha ()+this.getF350_id_tercero ()+this.getF350_id_clase_docto ()+this.getF350_ind_estado ()+this.getF350_ind_impresión ()+this.getF350_notas ()+this.getF450_id_concepto ()+this.getF450_id_bodega_salida ()+this.getF450_id_bodega_entrada ()+this.getF450_docto_alterno ()+this.getF350_id_co_base ()+this.getF350_id_tipo_docto_base ()+this.getF350_consec_docto_base ()+this.getF462_id_vehiculo ()+this.getF462_id_tercero_transp ()+this.getF462_id_sucursal_transp ()+this.getF462_id_tercero_conductor ()+this.getF462_nombre_conductor ()+this.getF462_identif_conductor ()+this.getF462_numero_guia ()+this.getF462_cajas ()+this.getF462_peso ()+this.getF462_volumen ()+this.getF462_valor_seguros ()+this.getF462_notas();
	}
}