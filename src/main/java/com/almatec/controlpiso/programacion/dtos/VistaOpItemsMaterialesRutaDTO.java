package com.almatec.controlpiso.programacion.dtos;

import java.math.BigDecimal;

public class VistaOpItemsMaterialesRutaDTO {
	
	private Integer idOpIntegrapps;
    private String op;
    private Long item_op_id;
    private Integer item_id;
    private Integer itemCentroTId;
    private String cliente;
    private String un;
    private String esquema_pintura;
    private String itemDescripcion;
    private BigDecimal item_peso;
    private BigDecimal item_long;
    private String item_color;
    private BigDecimal cantReq;
    private BigDecimal cant_cumplida;
    private String item_centro_t_nombre;
    private String zona;
    private String grupo;
    private String marca;
    private Integer prioridad;
    
	public VistaOpItemsMaterialesRutaDTO() {
		super();
	}

	public VistaOpItemsMaterialesRutaDTO(Integer idOpIntegrapps, String op, Long item_op_id, Integer item_id,
			Integer itemCentroTId, String cliente, String un, String esquema_pintura, String itemDescripcion,
			BigDecimal item_peso, BigDecimal item_long, String item_color, BigDecimal cantReq, BigDecimal cant_cumplida,
			String item_centro_t_nombre, String zona, String grupo, String marca) {
		super();
		this.idOpIntegrapps = idOpIntegrapps;
		this.op = op;
		this.item_op_id = item_op_id;
		this.item_id = item_id;
		this.itemCentroTId = itemCentroTId;
		this.cliente = cliente;
		this.un = un;
		this.esquema_pintura = esquema_pintura;
		this.itemDescripcion = itemDescripcion;
		this.item_peso = item_peso;
		this.item_long = item_long;
		this.item_color = item_color;
		this.cantReq = cantReq;
		this.cant_cumplida = cant_cumplida;
		this.item_centro_t_nombre = item_centro_t_nombre;
		this.zona = zona;
		this.grupo = grupo;
		this.marca = marca;
	}

	public Integer getIdOpIntegrapps() {
		return idOpIntegrapps;
	}

	public String getOp() {
		return op;
	}

	public Long getItem_op_id() {
		return item_op_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public Integer getItemCentroTId() {
		return itemCentroTId;
	}

	public String getCliente() {
		return cliente;
	}

	public String getUn() {
		return un;
	}

	public String getEsquema_pintura() {
		return esquema_pintura;
	}

	public String getItemDescripcion() {
		return itemDescripcion;
	}

	public BigDecimal getItem_peso() {
		return item_peso;
	}

	public BigDecimal getItem_long() {
		return item_long;
	}

	public String getItem_color() {
		return item_color;
	}

	public BigDecimal getCantReq() {
		return cantReq;
	}

	public BigDecimal getCant_cumplida() {
		return cant_cumplida;
	}

	public String getItem_centro_t_nombre() {
		return item_centro_t_nombre;
	}

	public String getZona() {
		return zona;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getMarca() {
		return marca;
	}

	public void setIdOpIntegrapps(Integer idOpIntegrapps) {
		this.idOpIntegrapps = idOpIntegrapps;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public void setItem_op_id(Long item_op_id) {
		this.item_op_id = item_op_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public void setItemCentroTId(Integer itemCentroTId) {
		this.itemCentroTId = itemCentroTId;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setUn(String un) {
		this.un = un;
	}

	public void setEsquema_pintura(String esquema_pintura) {
		this.esquema_pintura = esquema_pintura;
	}

	public void setItemDescripcion(String itemDescripcion) {
		this.itemDescripcion = itemDescripcion;
	}

	public void setItem_peso(BigDecimal item_peso) {
		this.item_peso = item_peso;
	}

	public void setItem_long(BigDecimal item_long) {
		this.item_long = item_long;
	}

	public void setItem_color(String item_color) {
		this.item_color = item_color;
	}

	public void setCantReq(BigDecimal cantReq) {
		this.cantReq = cantReq;
	}

	public void setCant_cumplida(BigDecimal cant_cumplida) {
		this.cant_cumplida = cant_cumplida;
	}

	public void setItem_centro_t_nombre(String item_centro_t_nombre) {
		this.item_centro_t_nombre = item_centro_t_nombre;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "VistaOpItemsMaterialesRutaDTO [idOpIntegrapps=" + idOpIntegrapps + ", op=" + op + ", item_op_id="
				+ item_op_id + ", item_id=" + item_id + ", itemCentroTId=" + itemCentroTId + ", cliente=" + cliente
				+ ", un=" + un + ", esquema_pintura=" + esquema_pintura + ", itemDescripcion=" + itemDescripcion
				+ ", item_peso=" + item_peso + ", item_long=" + item_long + ", item_color=" + item_color + ", cantReq="
				+ cantReq + ", cant_cumplida=" + cant_cumplida + ", item_centro_t_nombre=" + item_centro_t_nombre
				+ ", zona=" + zona + ", grupo=" + grupo + ", marca=" + marca + "]";
	}

	

}
