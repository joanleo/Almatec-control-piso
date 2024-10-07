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
	@Column(name = "item_desc")
	private String itemDescripcion;
	private BigDecimal item_peso;
	private BigDecimal item_long;
	private Integer prioridad;
	private String item_color;
	@Column(name = "cant_req")
	private Integer cantReq;
	private Integer cant_cumplida;
	
	@Column(name = "item_centro_t_id")
	private Integer itemCentroTId;
	private String item_centro_t_nombre;
	private Integer material_id;
	private String material_desc;
	private BigDecimal material_peso;
	private BigDecimal material_long;
	private Integer material_cant;
	
	@Column(name = "material_centro_t_id")
	private Integer materialCentroTId;
	private String material_centro_t_nombre;
	private Integer materia_prima_material_id;
	private String materia_prima_material_desc;
	private BigDecimal materia_prima_material_cant;
	
	private String zona;
	
	private String grupo;
	
	private String marca;
	
	public VistaOpItemsMaterialesRuta() {
		super();
	}
	
	public VistaOpItemsMaterialesRuta(Integer idOpIntegrapps, Long item_op_id, Integer item_id, Integer itemCentroTId,
            String cliente, String un, String op, String esquema_pintura,
            String itemDescripcion, BigDecimal item_peso, BigDecimal item_long,
            Integer prioridad, String item_color, Integer cantReq,
            Integer cant_cumplida, String item_centro_t_nombre,
            String zona, String grupo, String marca) {
		
		this.idOpIntegrapps = idOpIntegrapps;
		this.item_op_id = item_op_id;
		this.item_id = item_id;
		this.itemCentroTId = itemCentroTId;
		this.cliente = cliente;
		this.un = un;
		this.op = op;
		this.esquema_pintura = esquema_pintura;
		this.itemDescripcion = itemDescripcion;
		this.item_peso = item_peso;
		this.item_long = item_long;
		this.prioridad = prioridad;
		this.item_color = item_color;
		this.cantReq = cantReq;
		this.cant_cumplida = cant_cumplida;
		this.item_centro_t_nombre = item_centro_t_nombre;
		this.zona = zona;
		this.grupo = grupo;
		this.marca = marca;
	}

	
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
	public BigDecimal getItem_peso() {
		return item_peso;
	}
	public Integer getPrioridad() {
		return prioridad;
	}
	public String getItem_color() {
		return item_color;
	}
	public Integer getCant_cumplida() {
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
	public Integer getMaterial_cant() {
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
	public Integer getCantReq() {
		return cantReq;
	}
	public void setCantReq(Integer cantReq) {
		this.cantReq = cantReq;
	}
	public void setCant_cumplida(Integer cant_cumplida) {
		this.cant_cumplida = cant_cumplida;
	}
	public String getItemDescripcion() {
		return itemDescripcion;
	}
	public void setItemDescripcion(String itemDescripcion) {
		this.itemDescripcion = itemDescripcion;
	}
	public String getGrupo() {
		return grupo;
	}
	public String getMarca() {
		return marca;
	}
	@Override
	public String toString() {
		return "VistaOpItemsMaterialesRuta [id=" + id + ", idOpIntegrapps=" + idOpIntegrapps + ", cliente=" + cliente
				+ ", un=" + un + ", op=" + op + ", esquema_pintura=" + esquema_pintura + ", item_op_id=" + item_op_id
				+ ", item_id=" + item_id + ", itemDescripcion=" + itemDescripcion + ", item_peso=" + item_peso
				+ ", item_long=" + item_long + ", prioridad=" + prioridad + ", item_color=" + item_color + ", cantReq="
				+ cantReq + ", cant_cumplida=" + cant_cumplida + ", itemCentroTId=" + itemCentroTId
				+ ", item_centro_t_nombre=" + item_centro_t_nombre + ", material_id=" + material_id + ", material_desc="
				+ material_desc + ", material_peso=" + material_peso + ", material_long=" + material_long
				+ ", material_cant=" + material_cant + ", materialCentroTId=" + materialCentroTId
				+ ", material_centro_t_nombre=" + material_centro_t_nombre + ", materia_prima_material_id="
				+ materia_prima_material_id + ", materia_prima_material_desc=" + materia_prima_material_desc
				+ ", materia_prima_material_cant=" + materia_prima_material_cant + ", zona=" + zona + ", grupo=" + grupo
				+ ", marca=" + marca + "]";
	}
			
}
