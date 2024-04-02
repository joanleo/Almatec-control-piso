package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class RutasoperacionesOperacionesV01 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 809;  //Valor fijo = 809
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 1;  //Versión = 01
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la información del registro
	private Integer F_ACTUALIZA_REG;  //0=No, 1=Si
	private String f808_id;  //Código de la ruta
	private String f808_id_instalacion;  //Valida en maestro, código de la instalación.
	private String f809_id_metodo;  //Código del metodo
	private Integer f809_numero_operacion;  //Número de la operación
	private Integer f809_ind_estado;  //0 = Inactivo, 1 = Activo
	private String f809_descripcion;  //Descripción de la operación
	private Integer f809_ind_operacion_externa;  //0 = Inactivo, 1 = Activo
	private Integer f809_id_item;  //Codigo, es obligatorio si no va referencia ni codigo de barras, debe ser un ítem de servicio
	private String f809_referencia_item;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras, debe ser un ítem de servicio
	private String f809_codigo_barras;  //Codigo, es obligatorio si no va codigo de item ni referencia, debe ser un ítem de servicio
	private Integer f809_dias_entrega_externa;  //Número de días entrega externa
	private Double f809_tarifa_externa;  //Tarifa externa, El formato debe ser 15 entero, 4 decimales. 000000000000000.0000
	private String f809_id_ctrabajo;  //Valida en maestro, código del centro de trabajo.
	private Double f809_cantidad_base;  //Cantidad base,  el formato debe ser 15 enteros + punto + 4 decimales (000000000000000.0000)
	private Double f809_unidades_equivalentes;  //Unidades equivalentes, el formato debe ser 7 enteros + punto + 4 decimales (0000000.0000)
	private Double f809_horas_cola;  //Número de horas en cola, el formato debe ser 7 enteros + punto + 4 decimales (0000000.0000)
	private Double f809_horas_alistamiento;  //Número de horas alistamiento, el formato debe ser 7 enteros + punto + 4 decimales (0000000.0000)
	private Double f809_horas_ejecucion;  //Número de horas ejecución, el formato debe ser 7 enteros + punto + 4 decimales (0000000.0000)
	private Double f809_horas_maquina;  //Número de horas maquina, el formato debe ser 7 enteros + punto + 4 decimales (0000000.0000)
	private Double f809_horas_traslado;  //Número de horas traslado, el formato debe ser 7 enteros + punto + 4 decimales (0000000.0000)
	private String f809_num_operarios_ejecucion;  //Número de operarios en ejecución, el formato debe ser 000
	private String f809_num_operarios_alistamiento;  //Número de operarios en alistamiento, el formato debe ser 000
	private String f809_fecha_activacion;  //El formato debe ser AAAAMMDD
	private String f809_fecha_inactivacion;  //El formato debe ser AAAAMMDD, si se va a inactivar enviar vacio.
	private String f809_notas;  //Notas de la operación
	private String f809_id_descripcion_tecnica;

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

	public String getF_actualiza_reg () {
		return getFormattedValue(F_ACTUALIZA_REG ,1);
	}

	public void setF_actualiza_reg (Integer F_ACTUALIZA_REG) {
		this.F_ACTUALIZA_REG = F_ACTUALIZA_REG;
	}

	public String getF808_id () {
		return getFormattedValue(f808_id ,20);
	}

	public void setF808_id (String f808_id) {
		this.f808_id = f808_id;
	}

	public String getF808_id_instalacion () {
		return getFormattedValue(f808_id_instalacion ,3);
	}

	public void setF808_id_instalacion (String f808_id_instalacion) {
		this.f808_id_instalacion = f808_id_instalacion;
	}

	public String getF809_id_metodo () {
		return getFormattedValue(f809_id_metodo ,4);
	}

	public void setF809_id_metodo (String f809_id_metodo) {
		this.f809_id_metodo = f809_id_metodo;
	}

	public String getF809_numero_operacion () {
		return getFormattedValue(f809_numero_operacion ,4);
	}

	public void setF809_numero_operacion (Integer f809_numero_operacion) {
		this.f809_numero_operacion = f809_numero_operacion;
	}

	public String getF809_ind_estado () {
		return getFormattedValue(f809_ind_estado ,1);
	}

	public void setF809_ind_estado (Integer f809_ind_estado) {
		this.f809_ind_estado = f809_ind_estado;
	}

	public String getF809_descripcion () {
		return getFormattedValue(f809_descripcion ,40);
	}

	public void setF809_descripcion (String f809_descripcion) {
		this.f809_descripcion = f809_descripcion;
	}

	public String getF809_ind_operacion_externa () {
		return getFormattedValue(f809_ind_operacion_externa ,1);
	}

	public void setF809_ind_operacion_externa (Integer f809_ind_operacion_externa) {
		this.f809_ind_operacion_externa = f809_ind_operacion_externa;
	}

	public String getF809_id_item () {
		return getFormattedValue(f809_id_item ,7);
	}

	public void setF809_id_item (Integer f809_id_item) {
		this.f809_id_item = f809_id_item;
	}

	public String getF809_referencia_item () {
		return getFormattedValue(f809_referencia_item ,50);
	}

	public void setF809_referencia_item (String f809_referencia_item) {
		this.f809_referencia_item = f809_referencia_item;
	}

	public String getF809_codigo_barras () {
		return getFormattedValue(f809_codigo_barras ,20);
	}

	public void setF809_codigo_barras (String f809_codigo_barras) {
		this.f809_codigo_barras = f809_codigo_barras;
	}

	public String getF809_dias_entrega_externa () {
		return getFormattedValue(f809_dias_entrega_externa ,4);
	}

	public void setF809_dias_entrega_externa (Integer f809_dias_entrega_externa) {
		this.f809_dias_entrega_externa = f809_dias_entrega_externa;
	}

	public String getF809_tarifa_externa () {
		return getFormattedValue(f809_tarifa_externa ,20,0);
	}

	public void setF809_tarifa_externa (Double f809_tarifa_externa) {
		this.f809_tarifa_externa = f809_tarifa_externa;
	}

	public String getF809_id_ctrabajo () {
		return getFormattedValue(f809_id_ctrabajo ,15);
	}

	public void setF809_id_ctrabajo (String f809_id_ctrabajo) {
		this.f809_id_ctrabajo = f809_id_ctrabajo;
	}

	public String getF809_cantidad_base () {
		return getFormattedValue(f809_cantidad_base ,20,0);
	}

	public void setF809_cantidad_base (Double f809_cantidad_base) {
		this.f809_cantidad_base = f809_cantidad_base;
	}

	public String getF809_unidades_equivalentes () {
		return getFormattedValue(f809_unidades_equivalentes ,12,0);
	}

	public void setF809_unidades_equivalentes (Double f809_unidades_equivalentes) {
		this.f809_unidades_equivalentes = f809_unidades_equivalentes;
	}

	public String getF809_horas_cola () {
		return getFormattedValue(f809_horas_cola ,12,0);
	}

	public void setF809_horas_cola (Double f809_horas_cola) {
		this.f809_horas_cola = f809_horas_cola;
	}

	public String getF809_horas_alistamiento () {
		return getFormattedValue(f809_horas_alistamiento ,12,0);
	}

	public void setF809_horas_alistamiento (Double f809_horas_alistamiento) {
		this.f809_horas_alistamiento = f809_horas_alistamiento;
	}

	public String getF809_horas_ejecucion () {
		return getFormattedValue(f809_horas_ejecucion ,12,0);
	}

	public void setF809_horas_ejecucion (Double f809_horas_ejecucion) {
		this.f809_horas_ejecucion = f809_horas_ejecucion;
	}

	public String getF809_horas_maquina () {
		return getFormattedValue(f809_horas_maquina ,12,0);
	}

	public void setF809_horas_maquina (Double f809_horas_maquina) {
		this.f809_horas_maquina = f809_horas_maquina;
	}

	public String getF809_horas_traslado () {
		return getFormattedValue(f809_horas_traslado ,12,0);
	}

	public void setF809_horas_traslado (Double f809_horas_traslado) {
		this.f809_horas_traslado = f809_horas_traslado;
	}

	public String getF809_num_operarios_ejecucion () {
		return this.f809_num_operarios_ejecucion;
	}

	public void setF809_num_operarios_ejecucion (String f809_num_operarios_ejecucion) {
		this.f809_num_operarios_ejecucion = f809_num_operarios_ejecucion;
	}

	public String getF809_num_operarios_alistamiento () {
		return this.f809_num_operarios_alistamiento;
	}

	public void setF809_num_operarios_alistamiento (String f809_num_operarios_alistamiento) {
		this.f809_num_operarios_alistamiento = f809_num_operarios_alistamiento;
	}

	public String getF809_fecha_activacion () {
		return getFormattedValue(f809_fecha_activacion ,8);
	}

	public void setF809_fecha_activacion (String f809_fecha_activacion) {
		this.f809_fecha_activacion = f809_fecha_activacion;
	}

	public String getF809_fecha_inactivacion () {
		return getFormattedValue(f809_fecha_inactivacion ,8);
	}

	public void setF809_fecha_inactivacion (String f809_fecha_inactivacion) {
		this.f809_fecha_inactivacion = f809_fecha_inactivacion;
	}

	public String getF809_notas () {
		return getFormattedValue(f809_notas ,255);
	}

	public void setF809_notas (String f809_notas) {
		this.f809_notas = f809_notas;
	}

	public String getF809_id_descripcion_tecnica() {
		return getFormattedValue(f809_id_descripcion_tecnica ,3);
	}
	public void setF809_id_descripcion_tecnica(String f809_id_descripcion_tecnica) {
		this.f809_id_descripcion_tecnica = f809_id_descripcion_tecnica;
	}
	public RutasoperacionesOperacionesV01 () {
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
		return "RutasoperacionesOperacionesV01[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", F_ACTUALIZA_REG=" + this.getF_actualiza_reg () +", f808_id=" + this.getF808_id () +", f808_id_instalacion=" + this.getF808_id_instalacion () +", f809_id_metodo=" + this.getF809_id_metodo () +", f809_numero_operacion=" + this.getF809_numero_operacion () +", f809_ind_estado=" + this.getF809_ind_estado () +", f809_descripcion=" + this.getF809_descripcion () +", f809_ind_operacion_externa=" + this.getF809_ind_operacion_externa () +", f809_id_item=" + this.getF809_id_item () +", f809_referencia_item=" + this.getF809_referencia_item () +", f809_codigo_barras=" + this.getF809_codigo_barras () +", f809_dias_entrega_externa=" + this.getF809_dias_entrega_externa () +", f809_tarifa_externa=" + this.getF809_tarifa_externa () +", f809_id_ctrabajo=" + this.getF809_id_ctrabajo () +", f809_cantidad_base=" + this.getF809_cantidad_base () +", f809_unidades_equivalentes=" + this.getF809_unidades_equivalentes () +", f809_horas_cola=" + this.getF809_horas_cola () +", f809_horas_alistamiento=" + this.getF809_horas_alistamiento () +", f809_horas_ejecucion=" + this.getF809_horas_ejecucion () +", f809_horas_maquina=" + this.getF809_horas_maquina () +", f809_horas_traslado=" + this.getF809_horas_traslado () +", f809_num_operarios_ejecucion=" + this.getF809_num_operarios_ejecucion () +", f809_num_operarios_alistamiento=" + this.getF809_num_operarios_alistamiento () +", f809_fecha_activacion=" + this.getF809_fecha_activacion () +", f809_fecha_inactivacion=" + this.getF809_fecha_inactivacion () +", f809_notas=" + this.getF809_notas () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_actualiza_reg ()+this.getF808_id ()+this.getF808_id_instalacion ()+this.getF809_id_metodo ()+this.getF809_numero_operacion ()+this.getF809_ind_estado ()+this.getF809_descripcion ()+this.getF809_ind_operacion_externa ()+this.getF809_id_item ()+this.getF809_referencia_item ()+this.getF809_codigo_barras ()+this.getF809_dias_entrega_externa ()+this.getF809_tarifa_externa ()+this.getF809_id_ctrabajo ()+this.getF809_cantidad_base ()+this.getF809_unidades_equivalentes ()+this.getF809_horas_cola ()+this.getF809_horas_alistamiento ()+this.getF809_horas_ejecucion ()+this.getF809_horas_maquina ()+this.getF809_horas_traslado ()+this.getF809_num_operarios_ejecucion ()+this.getF809_num_operarios_alistamiento ()+this.getF809_fecha_activacion ()+this.getF809_fecha_inactivacion ()+this.getF809_notas ()+this.getF809_id_descripcion_tecnica();
	}
}