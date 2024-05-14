package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class ItemsParametrosVersion5 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 132;  //Valor fijo = 132
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 5;  //Versión = 05
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la información del registro
	private Integer F_ACTUALIZA_REG;  //0=No, 1=Si
	private String F_CAMPO;
	private String f132_id_instalacion ;  //Se valida con el maestro 
	private String f132_abc_rotacion_veces ;  //A, B, C
	private String f132_abc_rotacion_costo ;  //A, B, C
	private String f132_categoria_ciclo_conteo ;  //No se valida
	private String f132_id_um_venta_suge ;  //Se valida con el maestro, Si el ítem no es vendible deben llenar con la unidad de inventario
	private Integer f132_periodo_cubrimiento ;  //De 0 a 999 días
	private Integer f132_tiempo_reposicion ;  //De 0 a 999 días
	private Integer f132_tiempo_seguridad ;  //De 0 a 999 días
	private String f132_mf_id_planificador ;  //Se valida con el maestro
	private String f132_mf_id_comprador ;  //Se valida con el maestro
	private Integer f132_mf_dias_horiz_planea ;  //De 0 a 999 días
	private Double f132_mf_stock_segur_estatico ;  //10 Enteros 4 decimales, debe ir el punto decimal 0000000000.0000
	private Integer f132_mf_dias_horiz_stock_min ;  //De 0 a 999 días
	private Integer f132_mf_dias_stock_min ;  //De 0 a 999 días
	private Integer f132_mf_tiempo_repo_fijo ;  //De 0 a 999 días
	private Double f132_mf_tasa_produccion_dia ;  //10 Enteros 4 decimales, debe ir el punto decimal 0000000000.0000
	private Integer f132_mf_ind_mps ;  //Maneja plan maestro de producción 0=No; 1=Si
	private Integer f132_mf_dias_corte_demanda ;  //De 0 a 999 días
	private Double f132_mf_porc_rendimiento ;  //3 Enteros 3 decimales, debe ir el punto decimal 000.000
	private Integer f132_mf_ind_demanda_1 ;  //Tipos de demanda 0=no aplica,1=Mayor entre pronosticos y pedidos, 2=Suma de pronosticos y pedidos, 3=Solo pronosticos,4=Solo pedidos,9=Directo bajo pedido
	private Integer f132_mf_ind_demanda_2 ;  //Si maneja plan maestro de produccióny dias de corte de demanda debe definir un tipo de demanda de lo contrario va 0. Tipos de demanda 1=Mayor entre pronosticos y pedidos, 2=Suma de pronosticos y pedidos, 3=Solo pronosticos,4=Solo pedidos
	private Integer f132_mf_ind_tipo_orden ;  //0=Orden de compra,1=Orden de produccion
	private Integer f132_mf_ind_politica_orden ;  //1=Discreta,2=Discreta por encima de lote,3=Multiplos de lote,4=Incremental por encima de lote
	private Double f132_mf_tamano_lote ;  //10 Enteros 4 decimales, debe ir el punto decimal 0000000000.0000. Obligatorio si Politica de orden es 2, 3 o 4
	private Double f132_mf_cant_incremental_lote ;  //10 Enteros 4 decimales, debe ir el punto decimal 0000000000.0000, Obligatorio si Politica de orden es 4
	private Double f132_mf_porc_minimo_orden_plan ;  //3 Enteros 3 decimales, debe ir el punto decimal 000.000, Se captura si Politica de orden es 2, 3 o 4 
	private Integer f132_mf_dias_periodos_fijo ;  //De 0 a 999 días
	private String f132_mf_id_formulador ;  //Se valida con maestro
	private String f132_mf_revision_formula ;  //No se valida
	private String f132_mf_id_ruta ;  //Se valida con maestro
	private String f132_mf_id_bodega_asigna ;  //Se valida con maestro
	private Integer f132_mf_ind_asigna_instalacion ;  //0=No, 1=Si
	private Integer f132_mf_ind_generar_orden_prod ;  //0=No, 1=Si
	private Integer f132_mf_ind_item_critico;  //0=No, 1=Si
	private String f132_mf_id_tercero_prov_1 ;  //Se valida con maestro
	private String f132_mf_id_sucursal_prov_1 ;  //Se valida con maestro
	private String f132_mf_id_tercero_prov_2 ;  //Se valida con maestro, solo va si tiene proveedor 1
	private String f132_mf_id_sucursal_prov_2 ;  //Se valida con maestro
	private Double f132_porc_min_margen;  //4 Enteros 2 decimales, debe ir el punto decimal 0000.00
	private Double f132_porc_max_margen;  //4 Enteros 2 decimales, debe ir el punto decimal 0000.00, sebe ser mayor o igual al porcentaje minimo
	private Integer f120_id  ;  //Se valida con maestro, es obligatorio si no se especifica la referencia
	private String f120_referencia ;  //Se valida con maestro, es obligatoria si no se especifica el código
	private String f121_id_ext1_detalle;  //Es obligatorio si el ítem maneja extensión 1
	private String f121_id_ext2_detalle;  //Es obligatorio si el ítem maneja extensión 2
	private Double f132_mf_tasa_produccion_hora ;  //10 Enteros 6 decimales, debe ir el punto decimal 0000000000.000000
	private Double f132_porcentaje_exceso_compra;  //4 Enteros 2 decimales, debe ir el punto decimal 0000.00
	private Integer f132_mf_ind_genera_ord_pln;

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

	public String getF_CAMPO() {
		return getFormattedValue(F_CAMPO  ,35);
	}
	public void setF_CAMPO(String f_CAMPO) {
		F_CAMPO = f_CAMPO;
	}
	public String getF132_id_instalacion  () {
		return getFormattedValue(f132_id_instalacion  ,3);
	}

	public void setF132_id_instalacion  (String f132_id_instalacion ) {
		this.f132_id_instalacion  = f132_id_instalacion ;
	}

	public String getF132_abc_rotacion_veces  () {
		return getFormattedValue(f132_abc_rotacion_veces  ,1);
	}

	public void setF132_abc_rotacion_veces  (String f132_abc_rotacion_veces ) {
		this.f132_abc_rotacion_veces  = f132_abc_rotacion_veces ;
	}

	public String getF132_abc_rotacion_costo  () {
		return getFormattedValue(f132_abc_rotacion_costo  ,1);
	}

	public void setF132_abc_rotacion_costo  (String f132_abc_rotacion_costo ) {
		this.f132_abc_rotacion_costo  = f132_abc_rotacion_costo ;
	}

	public String getF132_categoria_ciclo_conteo  () {
		return getFormattedValue(f132_categoria_ciclo_conteo  ,3);
	}

	public void setF132_categoria_ciclo_conteo  (String f132_categoria_ciclo_conteo ) {
		this.f132_categoria_ciclo_conteo  = f132_categoria_ciclo_conteo ;
	}

	public String getF132_id_um_venta_suge  () {
		return getFormattedValue(f132_id_um_venta_suge  ,4);
	}

	public void setF132_id_um_venta_suge  (String f132_id_um_venta_suge ) {
		this.f132_id_um_venta_suge  = f132_id_um_venta_suge ;
	}

	public String getF132_periodo_cubrimiento  () {
		return getFormattedValue(f132_periodo_cubrimiento  ,3);
	}

	public void setF132_periodo_cubrimiento  (Integer f132_periodo_cubrimiento ) {
		this.f132_periodo_cubrimiento  = f132_periodo_cubrimiento ;
	}

	public String getF132_tiempo_reposicion  () {
		return getFormattedValue(f132_tiempo_reposicion  ,3);
	}

	public void setF132_tiempo_reposicion  (Integer f132_tiempo_reposicion ) {
		this.f132_tiempo_reposicion  = f132_tiempo_reposicion ;
	}

	public String getF132_tiempo_seguridad  () {
		return getFormattedValue(f132_tiempo_seguridad  ,3);
	}

	public void setF132_tiempo_seguridad  (Integer f132_tiempo_seguridad ) {
		this.f132_tiempo_seguridad  = f132_tiempo_seguridad ;
	}

	public String getF132_mf_id_planificador  () {
		return getFormattedValue(f132_mf_id_planificador  ,4);
	}

	public void setF132_mf_id_planificador  (String f132_mf_id_planificador ) {
		this.f132_mf_id_planificador  = f132_mf_id_planificador ;
	}

	public String getF132_mf_id_comprador  () {
		return getFormattedValue(f132_mf_id_comprador  ,4);
	}

	public void setF132_mf_id_comprador  (String f132_mf_id_comprador ) {
		this.f132_mf_id_comprador  = f132_mf_id_comprador ;
	}

	public String getF132_mf_dias_horiz_planea  () {
		return getFormattedValue(f132_mf_dias_horiz_planea  ,3);
	}

	public void setF132_mf_dias_horiz_planea  (Integer f132_mf_dias_horiz_planea ) {
		this.f132_mf_dias_horiz_planea  = f132_mf_dias_horiz_planea ;
	}

	public String getF132_mf_stock_segur_estatico  () {
		return getFormattedValue(f132_mf_stock_segur_estatico  ,15,4);
	}

	public void setF132_mf_stock_segur_estatico  (Double f132_mf_stock_segur_estatico ) {
		this.f132_mf_stock_segur_estatico  = f132_mf_stock_segur_estatico ;
	}

	public String getF132_mf_dias_horiz_stock_min  () {
		return getFormattedValue(f132_mf_dias_horiz_stock_min  ,3);
	}

	public void setF132_mf_dias_horiz_stock_min  (Integer f132_mf_dias_horiz_stock_min ) {
		this.f132_mf_dias_horiz_stock_min  = f132_mf_dias_horiz_stock_min ;
	}

	public String getF132_mf_dias_stock_min  () {
		return getFormattedValue(f132_mf_dias_stock_min  ,3);
	}

	public void setF132_mf_dias_stock_min  (Integer f132_mf_dias_stock_min ) {
		this.f132_mf_dias_stock_min  = f132_mf_dias_stock_min ;
	}

	public String getF132_mf_tiempo_repo_fijo  () {
		return getFormattedValue(f132_mf_tiempo_repo_fijo  ,3);
	}

	public void setF132_mf_tiempo_repo_fijo  (Integer f132_mf_tiempo_repo_fijo ) {
		this.f132_mf_tiempo_repo_fijo  = f132_mf_tiempo_repo_fijo ;
	}

	public String getF132_mf_tasa_produccion_dia  () {
		return getFormattedValue(f132_mf_tasa_produccion_dia  ,15,4);
	}

	public void setF132_mf_tasa_produccion_dia  (Double f132_mf_tasa_produccion_dia ) {
		this.f132_mf_tasa_produccion_dia  = f132_mf_tasa_produccion_dia ;
	}

	public String getF132_mf_ind_mps  () {
		return getFormattedValue(f132_mf_ind_mps  ,1);
	}

	public void setF132_mf_ind_mps  (Integer f132_mf_ind_mps ) {
		this.f132_mf_ind_mps  = f132_mf_ind_mps ;
	}

	public String getF132_mf_dias_corte_demanda  () {
		return getFormattedValue(f132_mf_dias_corte_demanda  ,3);
	}

	public void setF132_mf_dias_corte_demanda  (Integer f132_mf_dias_corte_demanda ) {
		this.f132_mf_dias_corte_demanda  = f132_mf_dias_corte_demanda ;
	}

	public String getF132_mf_porc_rendimiento  () {
		return getFormattedValue(f132_mf_porc_rendimiento  ,7,3);
	}

	public void setF132_mf_porc_rendimiento  (Double f132_mf_porc_rendimiento ) {
		this.f132_mf_porc_rendimiento  = f132_mf_porc_rendimiento ;
	}

	public String getF132_mf_ind_demanda_1  () {
		return getFormattedValue(f132_mf_ind_demanda_1  ,1);
	}

	public void setF132_mf_ind_demanda_1  (Integer f132_mf_ind_demanda_1 ) {
		this.f132_mf_ind_demanda_1  = f132_mf_ind_demanda_1 ;
	}

	public String getF132_mf_ind_demanda_2  () {
		return getFormattedValue(f132_mf_ind_demanda_2  ,1);
	}

	public void setF132_mf_ind_demanda_2  (Integer f132_mf_ind_demanda_2 ) {
		this.f132_mf_ind_demanda_2  = f132_mf_ind_demanda_2 ;
	}

	public String getF132_mf_ind_tipo_orden  () {
		return getFormattedValue(f132_mf_ind_tipo_orden  ,1);
	}

	public void setF132_mf_ind_tipo_orden  (Integer f132_mf_ind_tipo_orden ) {
		this.f132_mf_ind_tipo_orden  = f132_mf_ind_tipo_orden ;
	}

	public String getF132_mf_ind_politica_orden  () {
		return getFormattedValue(f132_mf_ind_politica_orden  ,1);
	}

	public void setF132_mf_ind_politica_orden  (Integer f132_mf_ind_politica_orden ) {
		this.f132_mf_ind_politica_orden  = f132_mf_ind_politica_orden ;
	}

	public String getF132_mf_tamano_lote  () {
		return getFormattedValue(f132_mf_tamano_lote  ,15,4);
	}

	public void setF132_mf_tamano_lote  (Double f132_mf_tamano_lote ) {
		this.f132_mf_tamano_lote  = f132_mf_tamano_lote ;
	}

	public String getF132_mf_cant_incremental_lote  () {
		return getFormattedValue(f132_mf_cant_incremental_lote  ,15,4);
	}

	public void setF132_mf_cant_incremental_lote  (Double f132_mf_cant_incremental_lote ) {
		this.f132_mf_cant_incremental_lote  = f132_mf_cant_incremental_lote ;
	}

	public String getF132_mf_porc_minimo_orden_plan  () {
		return getFormattedValue(f132_mf_porc_minimo_orden_plan  ,7,3);
	}

	public void setF132_mf_porc_minimo_orden_plan  (Double f132_mf_porc_minimo_orden_plan ) {
		this.f132_mf_porc_minimo_orden_plan  = f132_mf_porc_minimo_orden_plan ;
	}

	public String getF132_mf_dias_periodos_fijo  () {
		return getFormattedValue(f132_mf_dias_periodos_fijo  ,3);
	}

	public void setF132_mf_dias_periodos_fijo  (Integer f132_mf_dias_periodos_fijo ) {
		this.f132_mf_dias_periodos_fijo  = f132_mf_dias_periodos_fijo ;
	}

	public String getF132_mf_id_formulador  () {
		return getFormattedValue(f132_mf_id_formulador  ,4);
	}

	public void setF132_mf_id_formulador  (String f132_mf_id_formulador ) {
		this.f132_mf_id_formulador  = f132_mf_id_formulador ;
	}

	public String getF132_mf_revision_formula  () {
		return getFormattedValue(f132_mf_revision_formula  ,20);
	}

	public void setF132_mf_revision_formula  (String f132_mf_revision_formula ) {
		this.f132_mf_revision_formula  = f132_mf_revision_formula ;
	}

	public String getF132_mf_id_ruta  () {
		return getFormattedValue(f132_mf_id_ruta  ,20);
	}

	public void setF132_mf_id_ruta  (String f132_mf_id_ruta ) {
		this.f132_mf_id_ruta  = f132_mf_id_ruta ;
	}

	public String getF132_mf_id_bodega_asigna  () {
		return getFormattedValue(f132_mf_id_bodega_asigna  ,5);
	}

	public void setF132_mf_id_bodega_asigna  (String f132_mf_id_bodega_asigna ) {
		this.f132_mf_id_bodega_asigna  = f132_mf_id_bodega_asigna ;
	}

	public String getF132_mf_ind_asigna_instalacion  () {
		return getFormattedValue(f132_mf_ind_asigna_instalacion  ,1);
	}

	public void setF132_mf_ind_asigna_instalacion  (Integer f132_mf_ind_asigna_instalacion ) {
		this.f132_mf_ind_asigna_instalacion  = f132_mf_ind_asigna_instalacion ;
	}

	public String getF132_mf_ind_generar_orden_prod  () {
		return getFormattedValue(f132_mf_ind_generar_orden_prod  ,1);
	}

	public void setF132_mf_ind_generar_orden_prod  (Integer f132_mf_ind_generar_orden_prod ) {
		this.f132_mf_ind_generar_orden_prod  = f132_mf_ind_generar_orden_prod ;
	}

	public String getF132_mf_ind_item_critico () {
		return getFormattedValue(f132_mf_ind_item_critico ,1);
	}

	public void setF132_mf_ind_item_critico (Integer f132_mf_ind_item_critico) {
		this.f132_mf_ind_item_critico = f132_mf_ind_item_critico;
	}

	public String getF132_mf_id_tercero_prov_1  () {
		return getFormattedValue(f132_mf_id_tercero_prov_1  ,15);
	}

	public void setF132_mf_id_tercero_prov_1  (String f132_mf_id_tercero_prov_1 ) {
		this.f132_mf_id_tercero_prov_1  = f132_mf_id_tercero_prov_1 ;
	}

	public String getF132_mf_id_sucursal_prov_1  () {
		return getFormattedValue(f132_mf_id_sucursal_prov_1  ,3);
	}

	public void setF132_mf_id_sucursal_prov_1  (String f132_mf_id_sucursal_prov_1 ) {
		this.f132_mf_id_sucursal_prov_1  = f132_mf_id_sucursal_prov_1 ;
	}

	public String getF132_mf_id_tercero_prov_2  () {
		return getFormattedValue(f132_mf_id_tercero_prov_2  ,15);
	}

	public void setF132_mf_id_tercero_prov_2  (String f132_mf_id_tercero_prov_2 ) {
		this.f132_mf_id_tercero_prov_2  = f132_mf_id_tercero_prov_2 ;
	}

	public String getF132_mf_id_sucursal_prov_2  () {
		return getFormattedValue(f132_mf_id_sucursal_prov_2  ,3);
	}

	public void setF132_mf_id_sucursal_prov_2  (String f132_mf_id_sucursal_prov_2 ) {
		this.f132_mf_id_sucursal_prov_2  = f132_mf_id_sucursal_prov_2 ;
	}

	public String getF132_porc_min_margen () {
		return getFormattedValue(f132_porc_min_margen ,7,2);
	}

	public void setF132_porc_min_margen (Double f132_porc_min_margen) {
		this.f132_porc_min_margen = f132_porc_min_margen;
	}

	public String getF132_porc_max_margen () {
		return getFormattedValue(f132_porc_max_margen ,7,2);
	}

	public void setF132_porc_max_margen (Double f132_porc_max_margen) {
		this.f132_porc_max_margen = f132_porc_max_margen;
	}

	public String getF120_id   () {
		return getFormattedValue(f120_id   ,7);
	}

	public void setF120_id   (Integer f120_id  ) {
		this.f120_id   = f120_id  ;
	}

	public String getF120_referencia  () {
		return getFormattedValue(f120_referencia  ,50);
	}

	public void setF120_referencia  (String f120_referencia ) {
		this.f120_referencia  = f120_referencia ;
	}

	public String getF121_id_ext1_detalle () {
		return getFormattedValue(f121_id_ext1_detalle ,20);
	}

	public void setF121_id_ext1_detalle (String f121_id_ext1_detalle) {
		this.f121_id_ext1_detalle = f121_id_ext1_detalle;
	}

	public String getF121_id_ext2_detalle () {
		return getFormattedValue(f121_id_ext2_detalle ,20);
	}

	public void setF121_id_ext2_detalle (String f121_id_ext2_detalle) {
		this.f121_id_ext2_detalle = f121_id_ext2_detalle;
	}

	public String getF132_mf_tasa_produccion_hora  () {
		return getFormattedValue(f132_mf_tasa_produccion_hora  ,17,6);
	}

	public void setF132_mf_tasa_produccion_hora  (Double f132_mf_tasa_produccion_hora ) {
		this.f132_mf_tasa_produccion_hora  = f132_mf_tasa_produccion_hora ;
	}

	public String getF132_porcentaje_exceso_compra () {
		return getFormattedValue(f132_porcentaje_exceso_compra ,7,2);
	}

	public void setF132_porcentaje_exceso_compra (Double f132_porcentaje_exceso_compra) {
		this.f132_porcentaje_exceso_compra = f132_porcentaje_exceso_compra;
	}

	public Integer getF132_mf_ind_genera_ord_pln() {
		return f132_mf_ind_genera_ord_pln;
	}
	public void setF132_mf_ind_genera_ord_pln(Integer f132_mf_ind_genera_ord_pln) {
		this.f132_mf_ind_genera_ord_pln = f132_mf_ind_genera_ord_pln;
	}
	public ItemsParametrosVersion5 () {
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
		return "ItemsParametrosVersion5[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", F_ACTUALIZA_REG=" + this.getF_actualiza_reg () +", f132_id_instalacion =" + this.getF132_id_instalacion  () +", f132_abc_rotacion_veces =" + this.getF132_abc_rotacion_veces  () +", f132_abc_rotacion_costo =" + this.getF132_abc_rotacion_costo  () +", f132_categoria_ciclo_conteo =" + this.getF132_categoria_ciclo_conteo  () +", f132_id_um_venta_suge =" + this.getF132_id_um_venta_suge  () +", f132_periodo_cubrimiento =" + this.getF132_periodo_cubrimiento  () +", f132_tiempo_reposicion =" + this.getF132_tiempo_reposicion  () +", f132_tiempo_seguridad =" + this.getF132_tiempo_seguridad  () +", f132_mf_id_planificador =" + this.getF132_mf_id_planificador  () +", f132_mf_id_comprador =" + this.getF132_mf_id_comprador  () +", f132_mf_dias_horiz_planea =" + this.getF132_mf_dias_horiz_planea  () +", f132_mf_stock_segur_estatico =" + this.getF132_mf_stock_segur_estatico  () +", f132_mf_dias_horiz_stock_min =" + this.getF132_mf_dias_horiz_stock_min  () +", f132_mf_dias_stock_min =" + this.getF132_mf_dias_stock_min  () +", f132_mf_tiempo_repo_fijo =" + this.getF132_mf_tiempo_repo_fijo  () +", f132_mf_tasa_produccion_dia =" + this.getF132_mf_tasa_produccion_dia  () +", f132_mf_ind_mps =" + this.getF132_mf_ind_mps  () +", f132_mf_dias_corte_demanda =" + this.getF132_mf_dias_corte_demanda  () +", f132_mf_porc_rendimiento =" + this.getF132_mf_porc_rendimiento  () +", f132_mf_ind_demanda_1 =" + this.getF132_mf_ind_demanda_1  () +", f132_mf_ind_demanda_2 =" + this.getF132_mf_ind_demanda_2  () +", f132_mf_ind_tipo_orden =" + this.getF132_mf_ind_tipo_orden  () +", f132_mf_ind_politica_orden =" + this.getF132_mf_ind_politica_orden  () +", f132_mf_tamano_lote =" + this.getF132_mf_tamano_lote  () +", f132_mf_cant_incremental_lote =" + this.getF132_mf_cant_incremental_lote  () +", f132_mf_porc_minimo_orden_plan =" + this.getF132_mf_porc_minimo_orden_plan  () +", f132_mf_dias_periodos_fijo =" + this.getF132_mf_dias_periodos_fijo  () +", f132_mf_id_formulador =" + this.getF132_mf_id_formulador  () +", f132_mf_revision_formula =" + this.getF132_mf_revision_formula  () +", f132_mf_id_ruta =" + this.getF132_mf_id_ruta  () +", f132_mf_id_bodega_asigna =" + this.getF132_mf_id_bodega_asigna  () +", f132_mf_ind_asigna_instalacion =" + this.getF132_mf_ind_asigna_instalacion  () +", f132_mf_ind_generar_orden_prod =" + this.getF132_mf_ind_generar_orden_prod  () +", f132_mf_ind_item_critico=" + this.getF132_mf_ind_item_critico () +", f132_mf_id_tercero_prov_1 =" + this.getF132_mf_id_tercero_prov_1  () +", f132_mf_id_sucursal_prov_1 =" + this.getF132_mf_id_sucursal_prov_1  () +", f132_mf_id_tercero_prov_2 =" + this.getF132_mf_id_tercero_prov_2  () +", f132_mf_id_sucursal_prov_2 =" + this.getF132_mf_id_sucursal_prov_2  () +", f132_porc_min_margen=" + this.getF132_porc_min_margen () +", f132_porc_max_margen=" + this.getF132_porc_max_margen () +", f120_id  =" + this.getF120_id   () +", f120_referencia =" + this.getF120_referencia  () +", f121_id_ext1_detalle=" + this.getF121_id_ext1_detalle () +", f121_id_ext2_detalle=" + this.getF121_id_ext2_detalle () +", f132_mf_tasa_produccion_hora =" + this.getF132_mf_tasa_produccion_hora  () +", f132_porcentaje_exceso_compra=" + this.getF132_porcentaje_exceso_compra () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_actualiza_reg ()+ this.getF_CAMPO()+this.getF132_id_instalacion  ()+this.getF132_abc_rotacion_veces  ()+this.getF132_abc_rotacion_costo  ()+this.getF132_categoria_ciclo_conteo  ()+this.getF132_id_um_venta_suge  ()+this.getF132_periodo_cubrimiento  ()+this.getF132_tiempo_reposicion  ()+this.getF132_tiempo_seguridad  ()+this.getF132_mf_id_planificador  ()+this.getF132_mf_id_comprador  ()+this.getF132_mf_dias_horiz_planea  ()+this.getF132_mf_stock_segur_estatico  ()+this.getF132_mf_dias_horiz_stock_min  ()+this.getF132_mf_dias_stock_min  ()+this.getF132_mf_tiempo_repo_fijo  ()+this.getF132_mf_tasa_produccion_dia  ()+this.getF132_mf_ind_mps  ()+this.getF132_mf_dias_corte_demanda  ()+this.getF132_mf_porc_rendimiento  ()+this.getF132_mf_ind_demanda_1  ()+this.getF132_mf_ind_demanda_2  ()+this.getF132_mf_ind_tipo_orden  ()+this.getF132_mf_ind_politica_orden  ()+this.getF132_mf_tamano_lote  ()+this.getF132_mf_cant_incremental_lote  ()+this.getF132_mf_porc_minimo_orden_plan  ()+this.getF132_mf_dias_periodos_fijo  ()+this.getF132_mf_id_formulador  ()+this.getF132_mf_revision_formula  ()+this.getF132_mf_id_ruta  ()+this.getF132_mf_id_bodega_asigna  ()+this.getF132_mf_ind_asigna_instalacion  ()+this.getF132_mf_ind_generar_orden_prod  ()+this.getF132_mf_ind_item_critico ()+this.getF132_mf_id_tercero_prov_1  ()+this.getF132_mf_id_sucursal_prov_1  ()+this.getF132_mf_id_tercero_prov_2  ()+this.getF132_mf_id_sucursal_prov_2  ()+this.getF132_porc_min_margen ()+this.getF132_porc_max_margen ()+this.getF120_id   ()+this.getF120_referencia  ()+this.getF121_id_ext1_detalle ()+this.getF121_id_ext2_detalle ()+this.getF132_mf_tasa_produccion_hora  ()+this.getF132_porcentaje_exceso_compra ()+ this.getF132_mf_ind_genera_ord_pln();
	}
}