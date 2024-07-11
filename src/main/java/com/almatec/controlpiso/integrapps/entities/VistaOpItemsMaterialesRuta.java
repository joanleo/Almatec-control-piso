package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_op_items_ruta")
public class VistaOpItemsMaterialesRuta {
	
	@Id
	private String id;
	
	@Column(name = "id_op_ia")
	private Integer idOpIntegrapps;
	
	private String cliente;
	private String un;
	private String op;
	private String esquema_pintura;
	private Long item_op_id;
	private Integer item_id;
	private String item_desc;
	private BigDecimal item_peso;
	private BigDecimal item_long;
	private Integer prioridad;
	private String item_color;
	private BigDecimal cant_req;
	private BigDecimal cant_cumplida;
	
	@Column(name = "item_centro_t_id")
	private Integer itemCentroTId;
	private String item_centro_t_nombre;
	private Integer material_id;
	private String material_desc;
	private BigDecimal material_peso;
	private BigDecimal material_long;
	private BigDecimal material_cant;
	
	@Column(name = "material_centro_t_id")
	private Integer materialCentroTId;
	private String material_centro_t_nombre;
	private Integer materia_prima_material_id;
	private String materia_prima_material_desc;
	private BigDecimal materia_prima_material_cant;
	
	private String zona;
	
	public String getId() {
		return id;
	}
	public Integer getIdOpIntegrapps() {
		return idOpIntegrapps;
	}
	public String getCliente() {
		return cliente;
	}
	public String getUn() {
		return un;
	}
	public String getOp() {
		return op;
	}
	public String getEsquema_pintura() {
		return esquema_pintura;
	}
	public Long getItem_op_id() {
		return item_op_id;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public BigDecimal getItem_peso() {
		return item_peso;
	}
	public Integer getPrioridad() {
		return prioridad;
	}
	public String getItem_color() {
		return item_color;
	}
	public BigDecimal getCant_req() {
		return cant_req;
	}
	public BigDecimal getCant_cumplida() {
		return cant_cumplida;
	}
	public String getItem_centro_t_nombre() {
		return item_centro_t_nombre;
	}
	public Integer getMaterial_id() {
		return material_id;
	}
	public String getMaterial_desc() {
		return material_desc;
	}
	public BigDecimal getMaterial_peso() {
		return material_peso;
	}
	public BigDecimal getMaterial_cant() {
		return material_cant;
	}
	public String getMaterial_centro_t_nombre() {
		return material_centro_t_nombre;
	}
	public Integer getMateria_prima_material_id() {
		return materia_prima_material_id;
	}
	public String getMateria_prima_material_desc() {
		return materia_prima_material_desc;
	}
	public BigDecimal getMateria_prima_material_cant() {
		return materia_prima_material_cant;
	}
	public Integer getItemCentroTId() {
		return itemCentroTId;
	}
	public Integer getMaterialCentroTId() {
		return materialCentroTId;
	}
	public BigDecimal getItem_long() {
		return item_long;
	}
	public BigDecimal getMaterial_long() {
		return material_long;
	}
	public String getZona() {
		return zona;
	}
	@Override
	public String toString() {
		return "VistaOpItemsMaterialesRuta [id=" + id + ", idOpIntegrapps=" + idOpIntegrapps + ", cliente=" + cliente
				+ ", un=" + un + ", op=" + op + ", esquema_pintura=" + esquema_pintura + ", item_op_id=" + item_op_id
				+ ", item_id=" + item_id + ", item_desc=" + item_desc + ", item_peso=" + item_peso + ", item_long="
				+ item_long + ", prioridad=" + prioridad + ", item_color=" + item_color + ", cant_req=" + cant_req
				+ ", cant_cumplida=" + cant_cumplida + ", itemCentroTId=" + itemCentroTId + ", item_centro_t_nombre="
				+ item_centro_t_nombre + ", material_id=" + material_id + ", material_desc=" + material_desc
				+ ", material_peso=" + material_peso + ", material_long=" + material_long + ", material_cant="
				+ material_cant + ", materialCentroTId=" + materialCentroTId + ", material_centro_t_nombre="
				+ material_centro_t_nombre + ", materia_prima_material_id=" + materia_prima_material_id
				+ ", materia_prima_material_desc=" + materia_prima_material_desc + ", materia_prima_material_cant="
				+ materia_prima_material_cant + ", zona=" + zona + "]";
	}
		
}
