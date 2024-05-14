package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class ItemsVersión05 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 120;  //Valor fijo = 120
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 5;  //Versión = 05
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la información del registro
	private Integer F_ACTUALIZA_REG;  //0=No, 1=Si; 0 si el item es nuevo y 1 si el item ya existe y desea modificarlo, en este caso el código es obligatorio.
	private Integer f120_id  ;  //Codigo obligatorio si es modificación, si es nuevo  el código es obligatorio si el sistema no genera consecutivos automaticos, si el sistema genera consecutivos automáticos debe ser 000000.
	private String f120_referencia ;  //Referencia principal
	private String f120_descripcion ;  //Descripción del item
	private String f120_descripcion_corta;  //Descripción corta
	private String f120_id_grupo_impositivo ;  //Se valida con maestro
	private String f120_id_tipo_inv_serv;  //Se valida con maestro
	private String f120_id_grupo_dscto;  //Se valida con maestro
	private Integer f120_id_segmento_costo;  //Se valida con maestro, obligatorio si es servicio
	private Integer f120_ind_tipo_item ;  //1=Inventario; 2=Servicio; 3=Kit; 4=Fantasma
	private Integer f120_ind_compra ;  //0=No, 1=Si
	private Integer f120_ind_venta ;  //0=No, 1=Si
	private Integer f120_ind_manufactura ;  //0=No, 1=Si
	private Integer f120_ind_lote ;  //0=No, 1=Si; 2=Si y perecedero
	private Integer f120_ind_lote_asignacion ;  //0=No maneja lote, 1=Manual; 2=Automático
	private Integer f120_vida_util ;  //Puede ser mayor que 0 si es perecedero, el formato debe ser (4 entereos 0000)
	private String f120_id_tercero_prov ;  //Se valida con maestro
	private String f120_id_sucursal_prov;  //Se valida con maestro
	private String f120_id_tercero_cli ;  //Se valida con maestro
	private String f120_id_sucursal_cli ;  //Se valida con maestro
	private String f120_id_unidad_inventario ;  //Se valida con maestro
	private Double f120_factor_peso_inventario;  //Solo se requiere si  el sistema comercial tiene definida para los items la unidad de peso, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000) Si es igual a la unidad de inventario el factor debe ser 1.
	private Double f120_factor_volumen_inventario;  //Solo se requiere si  el sistema comercial tiene definida para los items la unidad de volumen, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000) Si es igual a la unidad de inventario el factor debe ser 1.
	private String f120_id_unidad_adicional ;  //Nombre de la persona de contacto
	private Double f120_factor_adicional;  //Solo se requiere si  el item maneja unidad adicional, el factor es respecto a la unidad de inventario, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000)
	private Double f120_factor_peso_adicional;  //Solo se requiere si  el sistema comercial tiene definida para los items la unidad de peso, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000) Si es igual a la unidad  adicional el factor debe ser 1.
	private Double f120_factor_volumen_adicional;  //Solo se requiere si  el sistema comercial tiene definida para los items la unidad de volumen, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000) Si es igual a la unidad adicional el factor debe ser 1.
	private String f120_id_unidad_orden;  //Se valida con maestro, puede ser igual a la unidad de inventario
	private Double f120_factor_orden;  //Solo se requiere si el item maneja una unidad de orden diferente a la unidad de inventario, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000) Si es igual a la unidad de inventario el factor debe ser 1.
	private Double f120_factor_peso_orden;  //Solo se requiere si  el sistema comercial tiene definida para los items la unidad de peso, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000) Si es igual a la unidad de orden el factor debe ser 1.
	private Double f120_factor_volumen_orden;  //Solo se requiere si  el sistema comercial tiene definida para los items la unidad de volumen, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000) Si es igual a la unidad de orden el factor debe ser 1.
	private String f120_id_unidad_empaque ;  //Se valida con maestro
	private Double f120_factor_empaque;  //Solo se requiere si  el item maneja unidad de empaque, el factor es respecto a la unidad de inventario, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000)
	private Double f120_factor_peso_empaque;  //Solo se requiere si  el sistema comercial tiene definida para los items la unidad de peso, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000) Si es igual a la unidad empaque el factor debe ser 1.
	private Double f120_factor_volumen_empaque;  //Solo se requiere si  el sistema comercial tiene definida para los items la unidad de volumen, el formato debe ser (6 enteros + punto + 4 decimales) (000000.0000) Si es igual a la unidad empaque el factor debe ser 1.
	private Integer f120_ind_lista_precios_ext;  //0=No; 1=Si
	private Integer f121_ind_estado ;  //0=Inactivo; 1=Activo; 2=Bloqueado
	private String f121_fecha_inactivacion ;  //Obligatorio si está inactivo, formato AAAAMMDD
	private String f121_fecha_creacion ;  //Obligatorio si es nuevo, formato AAAAMMDD
	private String f120_notas;  //Notas de ítem
	private Integer f120_ind_serial;  //0=No, 1=Si
	private String f120_id_cfg_serial;  //Obligatorio si f120_ind_serial = 1, se valida con el maestro de configuración de seriales t172
	private Integer f120_ind_paquete;  //0=No; 1=Si
	private Integer f120_ind_exento;

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

	public String getF120_descripcion  () {
		return getFormattedValue(f120_descripcion  ,40);
	}

	public void setF120_descripcion  (String f120_descripcion ) {
		this.f120_descripcion  = f120_descripcion ;
	}

	public String getF120_descripcion_corta () {
		return getFormattedValue(f120_descripcion_corta ,20);
	}

	public void setF120_descripcion_corta (String f120_descripcion_corta) {
		this.f120_descripcion_corta = f120_descripcion_corta;
	}

	public String getF120_id_grupo_impositivo  () {
		return getFormattedValue(f120_id_grupo_impositivo  ,4);
	}

	public void setF120_id_grupo_impositivo  (String f120_id_grupo_impositivo ) {
		this.f120_id_grupo_impositivo  = f120_id_grupo_impositivo ;
	}

	public String getF120_id_tipo_inv_serv () {
		return getFormattedValue(f120_id_tipo_inv_serv ,10);
	}

	public void setF120_id_tipo_inv_serv (String f120_id_tipo_inv_serv) {
		this.f120_id_tipo_inv_serv = f120_id_tipo_inv_serv;
	}

	public String getF120_id_grupo_dscto () {
		return getFormattedValue(f120_id_grupo_dscto ,4);
	}

	public void setF120_id_grupo_dscto (String f120_id_grupo_dscto) {
		this.f120_id_grupo_dscto = f120_id_grupo_dscto;
	}

	public String getF120_id_segmento_costo () {
		return getFormattedValue(f120_id_segmento_costo ,3);
	}

	public void setF120_id_segmento_costo (Integer f120_id_segmento_costo) {
		this.f120_id_segmento_costo = f120_id_segmento_costo;
	}

	public String getF120_ind_tipo_item  () {
		return getFormattedValue(f120_ind_tipo_item  ,1);
	}

	public void setF120_ind_tipo_item  (Integer f120_ind_tipo_item ) {
		this.f120_ind_tipo_item  = f120_ind_tipo_item ;
	}

	public String getF120_ind_compra  () {
		return getFormattedValue(f120_ind_compra  ,1);
	}

	public void setF120_ind_compra  (Integer f120_ind_compra ) {
		this.f120_ind_compra  = f120_ind_compra ;
	}

	public String getF120_ind_venta  () {
		return getFormattedValue(f120_ind_venta  ,1);
	}

	public void setF120_ind_venta  (Integer f120_ind_venta ) {
		this.f120_ind_venta  = f120_ind_venta ;
	}

	public String getF120_ind_manufactura  () {
		return getFormattedValue(f120_ind_manufactura  ,1);
	}

	public void setF120_ind_manufactura  (Integer f120_ind_manufactura ) {
		this.f120_ind_manufactura  = f120_ind_manufactura ;
	}

	public String getF120_ind_lote  () {
		return getFormattedValue(f120_ind_lote  ,1);
	}

	public void setF120_ind_lote  (Integer f120_ind_lote ) {
		this.f120_ind_lote  = f120_ind_lote ;
	}

	public String getF120_ind_lote_asignacion  () {
		return getFormattedValue(f120_ind_lote_asignacion  ,1);
	}

	public void setF120_ind_lote_asignacion  (Integer f120_ind_lote_asignacion ) {
		this.f120_ind_lote_asignacion  = f120_ind_lote_asignacion ;
	}

	public String getF120_vida_util  () {
		return getFormattedValue(f120_vida_util  ,4);
	}

	public void setF120_vida_util  (Integer f120_vida_util ) {
		this.f120_vida_util  = f120_vida_util ;
	}

	public String getF120_id_tercero_prov  () {
		return getFormattedValue(f120_id_tercero_prov  ,15);
	}

	public void setF120_id_tercero_prov  (String f120_id_tercero_prov ) {
		this.f120_id_tercero_prov  = f120_id_tercero_prov ;
	}

	public String getF120_id_sucursal_prov () {
		return getFormattedValue(f120_id_sucursal_prov ,3);
	}

	public void setF120_id_sucursal_prov (String f120_id_sucursal_prov) {
		this.f120_id_sucursal_prov = f120_id_sucursal_prov;
	}

	public String getF120_id_tercero_cli  () {
		return getFormattedValue(f120_id_tercero_cli  ,15);
	}

	public void setF120_id_tercero_cli  (String f120_id_tercero_cli ) {
		this.f120_id_tercero_cli  = f120_id_tercero_cli ;
	}

	public String getF120_id_sucursal_cli  () {
		return getFormattedValue(f120_id_sucursal_cli  ,3);
	}

	public void setF120_id_sucursal_cli  (String f120_id_sucursal_cli ) {
		this.f120_id_sucursal_cli  = f120_id_sucursal_cli ;
	}

	public String getF120_id_unidad_inventario  () {
		return getFormattedValue(f120_id_unidad_inventario  ,4);
	}

	public void setF120_id_unidad_inventario  (String f120_id_unidad_inventario ) {
		this.f120_id_unidad_inventario  = f120_id_unidad_inventario ;
	}

	public String getF120_factor_peso_inventario () {
		return getFormattedValue(f120_factor_peso_inventario ,11,4);
	}

	public void setF120_factor_peso_inventario (Double f120_factor_peso_inventario) {
		this.f120_factor_peso_inventario = f120_factor_peso_inventario;
	}

	public String getF120_factor_volumen_inventario () {
		return getFormattedValue(f120_factor_volumen_inventario ,11,4);
	}

	public void setF120_factor_volumen_inventario (Double f120_factor_volumen_inventario) {
		this.f120_factor_volumen_inventario = f120_factor_volumen_inventario;
	}

	public String getF120_id_unidad_adicional  () {
		return getFormattedValue(f120_id_unidad_adicional  ,4);
	}

	public void setF120_id_unidad_adicional  (String f120_id_unidad_adicional ) {
		this.f120_id_unidad_adicional  = f120_id_unidad_adicional ;
	}

	public String getF120_factor_adicional () {
		return getFormattedValue(f120_factor_adicional ,11,4);
	}

	public void setF120_factor_adicional (Double f120_factor_adicional) {
		this.f120_factor_adicional = f120_factor_adicional;
	}

	public String getF120_factor_peso_adicional () {
		return getFormattedValue(f120_factor_peso_adicional ,11,4);
	}

	public void setF120_factor_peso_adicional (Double f120_factor_peso_adicional) {
		this.f120_factor_peso_adicional = f120_factor_peso_adicional;
	}

	public String getF120_factor_volumen_adicional () {
		return getFormattedValue(f120_factor_volumen_adicional ,11,4);
	}

	public void setF120_factor_volumen_adicional (Double f120_factor_volumen_adicional) {
		this.f120_factor_volumen_adicional = f120_factor_volumen_adicional;
	}

	public String getF120_id_unidad_orden () {
		return getFormattedValue(f120_id_unidad_orden ,4);
	}

	public void setF120_id_unidad_orden (String f120_id_unidad_orden) {
		this.f120_id_unidad_orden = f120_id_unidad_orden;
	}

	public String getF120_factor_orden () {
		return getFormattedValue(f120_factor_orden ,11,4);
	}

	public void setF120_factor_orden (Double f120_factor_orden) {
		this.f120_factor_orden = f120_factor_orden;
	}

	public String getF120_factor_peso_orden () {
		return getFormattedValue(f120_factor_peso_orden ,11,4);
	}

	public void setF120_factor_peso_orden (Double f120_factor_peso_orden) {
		this.f120_factor_peso_orden = f120_factor_peso_orden;
	}

	public String getF120_factor_volumen_orden () {
		return getFormattedValue(f120_factor_volumen_orden ,11,4);
	}

	public void setF120_factor_volumen_orden (Double f120_factor_volumen_orden) {
		this.f120_factor_volumen_orden = f120_factor_volumen_orden;
	}

	public String getF120_id_unidad_empaque  () {
		return getFormattedValue(f120_id_unidad_empaque  ,4);
	}

	public void setF120_id_unidad_empaque  (String f120_id_unidad_empaque ) {
		this.f120_id_unidad_empaque  = f120_id_unidad_empaque ;
	}

	public String getF120_factor_empaque () {
		return getFormattedValue(f120_factor_empaque ,11,4);
	}

	public void setF120_factor_empaque (Double f120_factor_empaque) {
		this.f120_factor_empaque = f120_factor_empaque;
	}

	public String getF120_factor_peso_empaque () {
		return getFormattedValue(f120_factor_peso_empaque ,11,4);
	}

	public void setF120_factor_peso_empaque (Double f120_factor_peso_empaque) {
		this.f120_factor_peso_empaque = f120_factor_peso_empaque;
	}

	public String getF120_factor_volumen_empaque () {
		return getFormattedValue(f120_factor_volumen_empaque ,11,4);
	}

	public void setF120_factor_volumen_empaque (Double f120_factor_volumen_empaque) {
		this.f120_factor_volumen_empaque = f120_factor_volumen_empaque;
	}

	public String getF120_ind_lista_precios_ext () {
		return getFormattedValue(f120_ind_lista_precios_ext ,1);
	}

	public void setF120_ind_lista_precios_ext (Integer f120_ind_lista_precios_ext) {
		this.f120_ind_lista_precios_ext = f120_ind_lista_precios_ext;
	}

	public String getF121_ind_estado  () {
		return getFormattedValue(f121_ind_estado  ,1);
	}

	public void setF121_ind_estado  (Integer f121_ind_estado ) {
		this.f121_ind_estado  = f121_ind_estado ;
	}

	public String getF121_fecha_inactivacion  () {
		return getFormattedValue(f121_fecha_inactivacion  ,8);
	}

	public void setF121_fecha_inactivacion  (String f121_fecha_inactivacion ) {
		this.f121_fecha_inactivacion  = f121_fecha_inactivacion ;
	}

	public String getF121_fecha_creacion  () {
		return getFormattedValue(f121_fecha_creacion  ,8);
	}

	public void setF121_fecha_creacion  (String f121_fecha_creacion ) {
		this.f121_fecha_creacion  = f121_fecha_creacion ;
	}

	public String getF120_notas () {
		return getFormattedValue(f120_notas ,255);
	}

	public void setF120_notas (String f120_notas) {
		this.f120_notas = f120_notas;
	}

	public String getF120_ind_serial () {
		return getFormattedValue(f120_ind_serial ,1);
	}

	public void setF120_ind_serial (Integer f120_ind_serial) {
		this.f120_ind_serial = f120_ind_serial;
	}

	public String getF120_id_cfg_serial () {
		return getFormattedValue(f120_id_cfg_serial ,10);
	}

	public void setF120_id_cfg_serial (String f120_id_cfg_serial) {
		this.f120_id_cfg_serial = f120_id_cfg_serial;
	}

	public String getF120_ind_paquete () {
		return getFormattedValue(f120_ind_paquete ,1);
	}

	public void setF120_ind_paquete (Integer f120_ind_paquete) {
		this.f120_ind_paquete = f120_ind_paquete;
	}

	public String getF120_ind_exento() {
		return getFormattedValue(f120_ind_exento ,1);
	}
	public void setF120_ind_exento(Integer f120_ind_exento) {
		this.f120_ind_exento = f120_ind_exento;
	}
	public ItemsVersión05 () {
	// Constructor vacio para inicializar
	}
	@Override

	public String getFormattedValue(String name, int size) {
		if(name == null) {
			return String.format("%" + size + "s", " ");
		}
		if (name.length() > size) {
	        return name.substring(0, size);
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
		return "ItemsVersión05[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", F_ACTUALIZA_REG=" + this.getF_actualiza_reg () +", f120_id  =" + this.getF120_id   () +", f120_referencia =" + this.getF120_referencia  () +", f120_descripcion =" + this.getF120_descripcion  () +", f120_descripcion_corta=" + this.getF120_descripcion_corta () +", f120_id_grupo_impositivo =" + this.getF120_id_grupo_impositivo  () +", f120_id_tipo_inv_serv=" + this.getF120_id_tipo_inv_serv () +", f120_id_grupo_dscto=" + this.getF120_id_grupo_dscto () +", f120_id_segmento_costo=" + this.getF120_id_segmento_costo () +", f120_ind_tipo_item =" + this.getF120_ind_tipo_item  () +", f120_ind_compra =" + this.getF120_ind_compra  () +", f120_ind_venta =" + this.getF120_ind_venta  () +", f120_ind_manufactura =" + this.getF120_ind_manufactura  () +", f120_ind_lote =" + this.getF120_ind_lote  () +", f120_ind_lote_asignacion =" + this.getF120_ind_lote_asignacion  () +", f120_vida_util =" + this.getF120_vida_util  () +", f120_id_tercero_prov =" + this.getF120_id_tercero_prov  () +", f120_id_sucursal_prov=" + this.getF120_id_sucursal_prov () +", f120_id_tercero_cli =" + this.getF120_id_tercero_cli  () +", f120_id_sucursal_cli =" + this.getF120_id_sucursal_cli  () +", f120_id_unidad_inventario =" + this.getF120_id_unidad_inventario  () +", f120_factor_peso_inventario=" + this.getF120_factor_peso_inventario () +", f120_factor_volumen_inventario=" + this.getF120_factor_volumen_inventario () +", f120_id_unidad_adicional =" + this.getF120_id_unidad_adicional  () +", f120_factor_adicional=" + this.getF120_factor_adicional () +", f120_factor_peso_adicional=" + this.getF120_factor_peso_adicional () +", f120_factor_volumen_adicional=" + this.getF120_factor_volumen_adicional () +", f120_id_unidad_orden=" + this.getF120_id_unidad_orden () +", f120_factor_orden=" + this.getF120_factor_orden () +", f120_factor_peso_orden=" + this.getF120_factor_peso_orden () +", f120_factor_volumen_orden=" + this.getF120_factor_volumen_orden () +", f120_id_unidad_empaque =" + this.getF120_id_unidad_empaque  () +", f120_factor_empaque=" + this.getF120_factor_empaque () +", f120_factor_peso_empaque=" + this.getF120_factor_peso_empaque () +", f120_factor_volumen_empaque=" + this.getF120_factor_volumen_empaque () +", f120_ind_lista_precios_ext=" + this.getF120_ind_lista_precios_ext () +", f121_ind_estado =" + this.getF121_ind_estado  () +", f121_fecha_inactivacion =" + this.getF121_fecha_inactivacion  () +", f121_fecha_creacion =" + this.getF121_fecha_creacion  () +", f120_notas=" + this.getF120_notas () +", f120_ind_serial=" + this.getF120_ind_serial () +", f120_id_cfg_serial=" + this.getF120_id_cfg_serial () +", f120_ind_paquete=" + this.getF120_ind_paquete () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF_actualiza_reg ()+this.getF120_id   ()+this.getF120_referencia  ()+this.getF120_descripcion  ()+this.getF120_descripcion_corta ()+this.getF120_id_grupo_impositivo  ()+this.getF120_id_tipo_inv_serv ()+this.getF120_id_grupo_dscto ()+this.getF120_id_segmento_costo ()+this.getF120_ind_tipo_item  ()+this.getF120_ind_compra  ()+this.getF120_ind_venta  ()+this.getF120_ind_manufactura  ()+this.getF120_ind_lote  ()+this.getF120_ind_lote_asignacion  ()+this.getF120_vida_util  ()+this.getF120_id_tercero_prov  ()+this.getF120_id_sucursal_prov ()+this.getF120_id_tercero_cli  ()+this.getF120_id_sucursal_cli  ()+this.getF120_id_unidad_inventario  ()+this.getF120_factor_peso_inventario ()+this.getF120_factor_volumen_inventario ()+this.getF120_id_unidad_adicional  ()+this.getF120_factor_adicional ()+this.getF120_factor_peso_adicional ()+this.getF120_factor_volumen_adicional ()+this.getF120_id_unidad_orden ()+this.getF120_factor_orden ()+this.getF120_factor_peso_orden ()+this.getF120_factor_volumen_orden ()+this.getF120_id_unidad_empaque  ()+this.getF120_factor_empaque ()+this.getF120_factor_peso_empaque ()+this.getF120_factor_volumen_empaque ()+this.getF120_ind_lista_precios_ext ()+this.getF121_ind_estado  ()+this.getF121_fecha_inactivacion  ()+this.getF121_fecha_creacion  ()+this.getF120_notas ()+this.getF120_ind_serial ()+this.getF120_id_cfg_serial ()+this.getF120_ind_paquete ()+this.getF120_ind_exento();
	}
}