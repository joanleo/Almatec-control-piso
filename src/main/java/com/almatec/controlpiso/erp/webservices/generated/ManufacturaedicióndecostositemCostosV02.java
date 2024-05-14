package com.almatec.controlpiso.erp.webservices.generated;

import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public class ManufacturaedicióndecostositemCostosV02 implements Conector {
	private Integer F_NUMERO_REG;  //Numero consecutivo
	private Integer F_TIPO_REG= 402;  //Valor fijo = 402
	private Integer F_SUBTIPO_REG= 0;  //Valor fijo = 00
	private Integer F_VERSION_REG= 2;  //Versión = 02
	private Integer F_CIA;  //Valida en maestro, código de la compañía a la cual pertenece la información del registro
	private Integer f402_id_item;  //Codigo, es obligatorio si no va referencia ni codigo de barras
	private String f402_referencia_item;  //Codigo, es obligatorio si no va codigo de item ni codigo de barras
	private String f402_codigo_barras;  //Codigo, es obligatorio si no va codigo de item ni referencia
	private String f402_id_ext1_detalle;  //Es obligatorio si el ítem maneja extensión 1
	private String f402_id_ext2_detalle;  //Es obligatorio si el ítem maneja extensión 2
	private String f402_id_instalacion;  //Valida en maestro, código de la instalación.
	private Integer f402_id_grupo_costo;  //Valida en maestro, código del grupo de costo
	private Integer f402_id_segmento_costo;  //Valida en maestro, código del segmento de costo.
	private Double f402_costo_este_nivel_uni;  //Costo unitario, este nivel. El formato debe ser (Signo + 15 enteros + punto + 4 decimales). El número de decimales se deben reportar teniendo en cuenta el número de decimales configurados en la moneda local  (Decimales unidades). (+000000000000000.0000)
	private Double f402_costo_nivel_previo_uni;
	
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

	public String getF402_id_item () {
		return getFormattedValue(f402_id_item ,7);
	}

	public void setF402_id_item (Integer f402_id_item) {
		this.f402_id_item = f402_id_item;
	}

	public String getF402_referencia_item () {
		return getFormattedValue(f402_referencia_item ,50);
	}

	public void setF402_referencia_item (String f402_referencia_item) {
		this.f402_referencia_item = f402_referencia_item;
	}

	public String getF402_codigo_barras () {
		return getFormattedValue(f402_codigo_barras ,20);
	}

	public void setF402_codigo_barras (String f402_codigo_barras) {
		this.f402_codigo_barras = f402_codigo_barras;
	}

	public String getF402_id_ext1_detalle () {
		return getFormattedValue(f402_id_ext1_detalle ,20);
	}

	public void setF402_id_ext1_detalle (String f402_id_ext1_detalle) {
		this.f402_id_ext1_detalle = f402_id_ext1_detalle;
	}

	public String getF402_id_ext2_detalle () {
		return getFormattedValue(f402_id_ext2_detalle ,20);
	}

	public void setF402_id_ext2_detalle (String f402_id_ext2_detalle) {
		this.f402_id_ext2_detalle = f402_id_ext2_detalle;
	}

	public String getF402_id_instalacion () {
		return getFormattedValue(f402_id_instalacion ,3);
	}

	public void setF402_id_instalacion (String f402_id_instalacion) {
		this.f402_id_instalacion = f402_id_instalacion;
	}

	public String getF402_id_grupo_costo () {
		return getFormattedValue(f402_id_grupo_costo ,2);
	}

	public void setF402_id_grupo_costo (Integer f402_id_grupo_costo) {
		this.f402_id_grupo_costo = f402_id_grupo_costo;
	}

	public String getF402_id_segmento_costo () {
		return getFormattedValue(f402_id_segmento_costo ,3);
	}

	public void setF402_id_segmento_costo (Integer f402_id_segmento_costo) {
		this.f402_id_segmento_costo = f402_id_segmento_costo;
	}

	public String getF402_costo_este_nivel_uni () {
		return getFormattedValue(f402_costo_este_nivel_uni ,21,4);
	}

	public void setF402_costo_este_nivel_uni (Double f402_costo_este_nivel_uni) {
		this.f402_costo_este_nivel_uni = f402_costo_este_nivel_uni;
	}

	public String getF402_costo_nivel_previo_uni() {
		return getFormattedValue(f402_costo_nivel_previo_uni ,21,4);
	}
	public void setF402_costo_nivel_previo_uni(Double f402_costo_nivel_previo_uni) {
		this.f402_costo_nivel_previo_uni = f402_costo_nivel_previo_uni;
	}
	public ManufacturaedicióndecostositemCostosV02 () {
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
		return "ManufacturaedicióndecostositemCostosV02[F_NUMERO_REG=" + this.getF_numero_reg () +", F_TIPO_REG=" + this.getF_tipo_reg () +", F_SUBTIPO_REG=" + this.getF_subtipo_reg () +", F_VERSION_REG=" + this.getF_version_reg () +", F_CIA=" + this.getF_cia () +", f402_id_item=" + this.getF402_id_item () +", f402_referencia_item=" + this.getF402_referencia_item () +", f402_codigo_barras=" + this.getF402_codigo_barras () +", f402_id_ext1_detalle=" + this.getF402_id_ext1_detalle () +", f402_id_ext2_detalle=" + this.getF402_id_ext2_detalle () +", f402_id_instalacion=" + this.getF402_id_instalacion () +", f402_id_grupo_costo=" + this.getF402_id_grupo_costo () +", f402_id_segmento_costo=" + this.getF402_id_segmento_costo () +", f402_costo_este_nivel_uni=" + this.getF402_costo_este_nivel_uni () +"]";
	}
	@Override
	public String getConector() {
		return this.getF_numero_reg ()+this.getF_tipo_reg ()+this.getF_subtipo_reg ()+this.getF_version_reg ()+this.getF_cia ()+this.getF402_id_item ()+this.getF402_referencia_item ()+this.getF402_codigo_barras ()+this.getF402_id_ext1_detalle ()+this.getF402_id_ext2_detalle ()+this.getF402_id_instalacion ()+this.getF402_id_grupo_costo ()+this.getF402_id_segmento_costo ()+this.getF402_costo_este_nivel_uni () +this.getF402_costo_nivel_previo_uni();
	}
}