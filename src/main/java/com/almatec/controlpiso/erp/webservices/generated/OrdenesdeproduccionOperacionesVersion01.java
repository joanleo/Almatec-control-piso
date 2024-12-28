package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class OrdenesdeproduccionOperacionesVersion01 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 865;  //Valor fijo = 865
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 1;  //Version = 01
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la informacion del registro
	private Integer F_ACTUALIZA_REG;  //0=Adiciona,1=Modifica, 3=Elimina
	private String f850_id_co_op;  //Valida en maestro, código de centro de operación del documento
	private String f850_id_tipo_docto_op;  //Valida en maestro, código de tipo de documento
	private Integer f850_consec_docto_op;  //Numero de documento
	private Integer f865_id_item_op;  //Codigo, es obligatorio si no va referencia ni codigo de barras
	private String f865_referencia_item_op;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f865_codigo_barras_item_op;  //Codigo, es obligatorio si no va codigo de item ni referencia
	private String f865_id_ext1_detalle_item_op;  //Es obligatorio si el ítem maneja extensión 1
	private String f865_id_ext2_detalle_item_op;  //Es obligatorio si el ítem maneja extensión 2
	private Integer f865_numero_operacion;  //Número de la operación
	private String f865_id_centro_trabajo;  //Valida en maestro id centro de trabajo. Es utilizado para operaciones internas.
	private Double f865_cant_ejecutar_base;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medidad. (000000000000000.0000)
	private Double f865_cant_base;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medidad. (000000000000000.0000).  Es utilizado para operaciones internas.
	private String f865_descripcion_operacion;  //Descripción de la operación
	private Integer f865_ind_operacion_externa;  //0=No ; 1=Si
	private String f865_fecha_inicio;  //El formato debe ser AAAAMMDD
	private String f865_fecha_terminacion;  //El formato debe ser AAAAMMDD
	private Integer f865_rt_numero_operarios;  //Numero de operarios ejecucion
	private Integer f865_rt_numero_operarios_alis;  //Numero de operarios alistamiento
	private Double f865_rt_unidades_equivalentes;  //El formato debe ser (15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la unidad de medidad. (000000000000000.0000). Es utilizado para operaciones internas.
	private Integer f865_rt_horas_cola;  //Horas cola
	private Integer f865_rt_horas_alistamiento;  //Horas alistamiento
	private Integer f865_rt_horas_ejecucion;  //Horas ejecucion
	private Integer f865_rt_horas_maquina;  //Horas maquina
	private Integer f865_rt_horas_traslado;  //Horas traslado
	private String f865_rt_id_descripcion_tecnica;  //Valida las descripciones tecnicas
	private String f865_notas;  //Notas de la operaciòn
	private String f865_id_grupo_ent_muest;  //Codigo grupo entidad muestreo
	private String f865_id_tabla_muestra;  //Codigo de la tabla muestreo
	private Integer f865_id_item_externa;  //Codigo, es obligatorio si no va referencia ni codigo de barras, debe ser un ítem de servicio. Es utilizado cuando el indicador de operación externa está habilitado
	private String f865_referencia_item_externa;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras, debe ser un ítem de servicio. Es utilizado cuando el indicador de operación externa está habilitado
	private String f865_codigo_barras_externa;  //Codigo, es obligatorio si no va codigo de item ni referencia, debe ser un ítem de servicio. Es utilizado cuando el indicador de operación externa está habilitado
	private Integer f865_dias_entrega_externa;  //Número de días entrega externa. Es utilizado cuando el indicador de operación externa está habilitado
	private Integer f865_tarifa_externa;  //Tarifa externa, El formato debe ser 000000000000000.0000. Es utilizado cuando el indicador de operación externa está habilitado

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

	public String getF850_id_co_op () {
		return getFormattedValue(f850_id_co_op ,3);
	}

	public void setF850_id_co_op (String f850_id_co_op) {
		this.f850_id_co_op = f850_id_co_op;
	}

	public String getF850_id_tipo_docto_op () {
		return getFormattedValue(f850_id_tipo_docto_op ,3);
	}

	public void setF850_id_tipo_docto_op (String f850_id_tipo_docto_op) {
		this.f850_id_tipo_docto_op = f850_id_tipo_docto_op;
	}

	public String getF850_consec_docto_op () {
		return getFormattedValue(f850_consec_docto_op ,8);
	}

	public void setF850_consec_docto_op (Integer f850_consec_docto_op) {
		this.f850_consec_docto_op = f850_consec_docto_op;
	}

	public String getF865_id_item_op () {
		return getFormattedValue(f865_id_item_op ,7);
	}

	public void setF865_id_item_op (Integer f865_id_item_op) {
		this.f865_id_item_op = f865_id_item_op;
	}

	public String getF865_referencia_item_op () {
		return getFormattedValue(f865_referencia_item_op ,50);
	}

	public void setF865_referencia_item_op (String f865_referencia_item_op) {
		this.f865_referencia_item_op = f865_referencia_item_op;
	}

	public String getF865_codigo_barras_item_op () {
		return getFormattedValue(f865_codigo_barras_item_op ,20);
	}

	public void setF865_codigo_barras_item_op (String f865_codigo_barras_item_op) {
		this.f865_codigo_barras_item_op = f865_codigo_barras_item_op;
	}

	public String getF865_id_ext1_detalle_item_op () {
		return getFormattedValue(f865_id_ext1_detalle_item_op ,20);
	}

	public void setF865_id_ext1_detalle_item_op (String f865_id_ext1_detalle_item_op) {
		this.f865_id_ext1_detalle_item_op = f865_id_ext1_detalle_item_op;
	}

	public String getF865_id_ext2_detalle_item_op () {
		return getFormattedValue(f865_id_ext2_detalle_item_op ,20);
	}

	public void setF865_id_ext2_detalle_item_op (String f865_id_ext2_detalle_item_op) {
		this.f865_id_ext2_detalle_item_op = f865_id_ext2_detalle_item_op;
	}

	public String getF865_numero_operacion () {
		return getFormattedValue(f865_numero_operacion ,10);
	}

	public void setF865_numero_operacion (Integer f865_numero_operacion) {
		this.f865_numero_operacion = f865_numero_operacion;
	}

	public String getF865_id_centro_trabajo () {
		return getFormattedValue(f865_id_centro_trabajo ,15);
	}

	public void setF865_id_centro_trabajo (String f865_id_centro_trabajo) {
		this.f865_id_centro_trabajo = f865_id_centro_trabajo;
	}

	public String getF865_cant_ejecutar_base () {
		return getFormattedValue(f865_cant_ejecutar_base ,20,4);
	}

	public void setF865_cant_ejecutar_base (Double f865_cant_ejecutar_base) {
		this.f865_cant_ejecutar_base = f865_cant_ejecutar_base;
	}

	public String getF865_cant_base () {
		return getFormattedValue(f865_cant_base ,20,4);
	}

	public void setF865_cant_base (Double f865_cant_base) {
		this.f865_cant_base = f865_cant_base;
	}

	public String getF865_descripcion_operacion () {
		return getFormattedValue(f865_descripcion_operacion ,40);
	}

	public void setF865_descripcion_operacion (String f865_descripcion_operacion) {
		this.f865_descripcion_operacion = f865_descripcion_operacion;
	}

	public String getF865_ind_operacion_externa () {
		return getFormattedValue(f865_ind_operacion_externa ,1);
	}

	public void setF865_ind_operacion_externa (Integer f865_ind_operacion_externa) {
		this.f865_ind_operacion_externa = f865_ind_operacion_externa;
	}

	public String getF865_fecha_inicio () {
		return getFormattedValue(f865_fecha_inicio ,8);
	}

	public void setF865_fecha_inicio (String f865_fecha_inicio) {
		this.f865_fecha_inicio = f865_fecha_inicio;
	}

	public String getF865_fecha_terminacion () {
		return getFormattedValue(f865_fecha_terminacion ,8);
	}

	public void setF865_fecha_terminacion (String f865_fecha_terminacion) {
		this.f865_fecha_terminacion = f865_fecha_terminacion;
	}

	public String getF865_rt_numero_operarios () {
		return getFormattedValue(f865_rt_numero_operarios ,10);
	}

	public void setF865_rt_numero_operarios (Integer f865_rt_numero_operarios) {
		this.f865_rt_numero_operarios = f865_rt_numero_operarios;
	}

	public String getF865_rt_numero_operarios_alis () {
		return getFormattedValue(f865_rt_numero_operarios_alis ,10);
	}

	public void setF865_rt_numero_operarios_alis (Integer f865_rt_numero_operarios_alis) {
		this.f865_rt_numero_operarios_alis = f865_rt_numero_operarios_alis;
	}

	public String getF865_rt_unidades_equivalentes () {
		return getFormattedValue(f865_rt_unidades_equivalentes ,20,4);
	}

	public void setF865_rt_unidades_equivalentes (Double f865_rt_unidades_equivalentes) {
		this.f865_rt_unidades_equivalentes = f865_rt_unidades_equivalentes;
	}

	public String getF865_rt_horas_cola () {
		return getFormattedValue(f865_rt_horas_cola ,20);
	}

	public void setF865_rt_horas_cola (Integer f865_rt_horas_cola) {
		this.f865_rt_horas_cola = f865_rt_horas_cola;
	}

	public String getF865_rt_horas_alistamiento () {
		return getFormattedValue(f865_rt_horas_alistamiento ,20);
	}

	public void setF865_rt_horas_alistamiento (Integer f865_rt_horas_alistamiento) {
		this.f865_rt_horas_alistamiento = f865_rt_horas_alistamiento;
	}

	public String getF865_rt_horas_ejecucion () {
		return getFormattedValue(f865_rt_horas_ejecucion ,20);
	}

	public void setF865_rt_horas_ejecucion (Integer f865_rt_horas_ejecucion) {
		this.f865_rt_horas_ejecucion = f865_rt_horas_ejecucion;
	}

	public String getF865_rt_horas_maquina () {
		return getFormattedValue(f865_rt_horas_maquina ,20);
	}

	public void setF865_rt_horas_maquina (Integer f865_rt_horas_maquina) {
		this.f865_rt_horas_maquina = f865_rt_horas_maquina;
	}

	public String getF865_rt_horas_traslado () {
		return getFormattedValue(f865_rt_horas_traslado ,20);
	}

	public void setF865_rt_horas_traslado (Integer f865_rt_horas_traslado) {
		this.f865_rt_horas_traslado = f865_rt_horas_traslado;
	}

	public String getF865_rt_id_descripcion_tecnica () {
		return getFormattedValue(f865_rt_id_descripcion_tecnica ,3);
	}

	public void setF865_rt_id_descripcion_tecnica (String f865_rt_id_descripcion_tecnica) {
		this.f865_rt_id_descripcion_tecnica = f865_rt_id_descripcion_tecnica;
	}

	public String getF865_notas () {
		return getFormattedValue(f865_notas ,255);
	}

	public void setF865_notas (String f865_notas) {
		this.f865_notas = f865_notas;
	}

	public String getF865_id_grupo_ent_muest () {
		return getFormattedValue(f865_id_grupo_ent_muest ,30);
	}

	public void setF865_id_grupo_ent_muest (String f865_id_grupo_ent_muest) {
		this.f865_id_grupo_ent_muest = f865_id_grupo_ent_muest;
	}

	public String getF865_id_tabla_muestra () {
		return getFormattedValue(f865_id_tabla_muestra ,3);
	}

	public void setF865_id_tabla_muestra (String f865_id_tabla_muestra) {
		this.f865_id_tabla_muestra = f865_id_tabla_muestra;
	}

	public String getF865_id_item_externa () {
		return getFormattedValue(f865_id_item_externa ,7);
	}

	public void setF865_id_item_externa (Integer f865_id_item_externa) {
		this.f865_id_item_externa = f865_id_item_externa;
	}

	public String getF865_referencia_item_externa () {
		return getFormattedValue(f865_referencia_item_externa ,50);
	}

	public void setF865_referencia_item_externa (String f865_referencia_item_externa) {
		this.f865_referencia_item_externa = f865_referencia_item_externa;
	}

	public String getF865_codigo_barras_externa () {
		return getFormattedValue(f865_codigo_barras_externa ,20);
	}

	public void setF865_codigo_barras_externa (String f865_codigo_barras_externa) {
		this.f865_codigo_barras_externa = f865_codigo_barras_externa;
	}

	public String getF865_dias_entrega_externa () {
		return getFormattedValue(f865_dias_entrega_externa ,4);
	}

	public void setF865_dias_entrega_externa (Integer f865_dias_entrega_externa) {
		this.f865_dias_entrega_externa = f865_dias_entrega_externa;
	}

	public String getF865_tarifa_externa () {
		return getFormattedValue(f865_tarifa_externa ,20);
	}

	public void setF865_tarifa_externa (Integer f865_tarifa_externa) {
		this.f865_tarifa_externa = f865_tarifa_externa;
	}

	public OrdenesdeproduccionOperacionesVersion01 () {
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
		return "OrdenesdeproducciónOperacionesVersión01[F_NUMERO_REG= " + this.getF_numero_reg() +", F_TIPO_REG= " + this.getF_tipo_reg() +", F_SUBTIPO_REG= " + this.getF_subtipo_reg() +", F_VERSION_REG= " + this.getF_version_reg() +", F_CIA= " + this.getF_cia() +", F_ACTUALIZA_REG= " + this.getF_actualiza_reg() +", f850_id_co_op= " + this.getF850_id_co_op() +", f850_id_tipo_docto_op= " + this.getF850_id_tipo_docto_op() +", f850_consec_docto_op= " + this.getF850_consec_docto_op() +", f865_id_item_op= " + this.getF865_id_item_op() +", f865_referencia_item_op= " + this.getF865_referencia_item_op() +", f865_codigo_barras_item_op= " + this.getF865_codigo_barras_item_op() +", f865_id_ext1_detalle_item_op= " + this.getF865_id_ext1_detalle_item_op() +", f865_id_ext2_detalle_item_op= " + this.getF865_id_ext2_detalle_item_op() +", f865_numero_operacion= " + this.getF865_numero_operacion() +", f865_id_centro _trabajo= " + this.getF865_id_centro_trabajo() +", f865_cant_ejecutar_base= " + this.getF865_cant_ejecutar_base() +", f865_cant_base= " + this.getF865_cant_base() +", f865_descripcion_operacion= " + this.getF865_descripcion_operacion() +", f865_ind_operacion_externa= " + this.getF865_ind_operacion_externa() +", f865_fecha_inicio= " + this.getF865_fecha_inicio() +", f865_fecha_terminacion= " + this.getF865_fecha_terminacion() +", f865_rt_numero_operarios= " + this.getF865_rt_numero_operarios() +", f865_rt_numero_operarios_alis= " + this.getF865_rt_numero_operarios_alis() +", f865_rt_unidades_equivalentes= " + this.getF865_rt_unidades_equivalentes() +", f865_rt_horas_cola= " + this.getF865_rt_horas_cola() +", f865_rt_horas_alistamiento= " + this.getF865_rt_horas_alistamiento() +", f865_rt_horas_ejecucion= " + this.getF865_rt_horas_ejecucion() +", f865_rt_horas_maquina= " + this.getF865_rt_horas_maquina() +", f865_rt_horas_traslado= " + this.getF865_rt_horas_traslado() +", f865_rt_id_descripcion_tecnica= " + this.getF865_rt_id_descripcion_tecnica() +", f865_notas= " + this.getF865_notas() +", f865_id_grupo_ent_muest= " + this.getF865_id_grupo_ent_muest() +", f865_id_tabla_muestra= " + this.getF865_id_tabla_muestra() +", f865_id_item_externa= " + this.getF865_id_item_externa() +", f865_referencia_item_externa= " + this.getF865_referencia_item_externa() +", f865_codigo_barras_externa= " + this.getF865_codigo_barras_externa() +", f865_dias_entrega_externa= " + this.getF865_dias_entrega_externa() +", f865_tarifa_externa= " + this.getF865_tarifa_externa() +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_actualiza_reg ()+this.getF850_id_co_op ()+this.getF850_id_tipo_docto_op ()+this.getF850_consec_docto_op ()+this.getF865_id_item_op ()+this.getF865_referencia_item_op ()+this.getF865_codigo_barras_item_op ()+this.getF865_id_ext1_detalle_item_op ()+this.getF865_id_ext2_detalle_item_op ()+this.getF865_numero_operacion ()+this.getF865_id_centro_trabajo ()+this.getF865_cant_ejecutar_base ()+this.getF865_cant_base ()+this.getF865_descripcion_operacion ()+this.getF865_ind_operacion_externa ()+this.getF865_fecha_inicio ()+this.getF865_fecha_terminacion ()+this.getF865_rt_numero_operarios ()+this.getF865_rt_numero_operarios_alis ()+this.getF865_rt_unidades_equivalentes ()+this.getF865_rt_horas_cola ()+this.getF865_rt_horas_alistamiento ()+this.getF865_rt_horas_ejecucion ()+this.getF865_rt_horas_maquina ()+this.getF865_rt_horas_traslado ()+this.getF865_rt_id_descripcion_tecnica ()+this.getF865_notas ()+this.getF865_id_grupo_ent_muest ()+this.getF865_id_tabla_muestra ()+this.getF865_id_item_externa ()+this.getF865_referencia_item_externa ()+this.getF865_codigo_barras_externa ()+this.getF865_dias_entrega_externa ()+this.getF865_tarifa_externa ();
	}
}