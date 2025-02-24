package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vista_resumen_piezas_reportadas_v3")
public class VistaPiezasReportadas {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "id_op_ia")
	private Integer idOpIntegrapps;
	
	@Column(name = "cliente")
	private String cliente;
	
	@Column(name = "op")
	private String op;
	
	@Column(name = "esquema_pintura")
	private String esquemaPintura;
	
	@Column(name = "item_op_id")
	private Long itemOpId;
	
	@Column(name = "item_id")
	private Integer itemId;
	
	@Column(name = "item_desc")
	private String itemDescripcion;
	
	@Column(name = "item_peso")
	private BigDecimal itemPeso;
	
	@Column(name = "item_long")
	private BigDecimal itemLongitud;
	
	@Column(name = "prioridad")
	private Integer prioridad;
	
	@Column(name = "item_color")
	private String itemColor;
	
	@Column(name = "cant_req")
	private BigDecimal cantReq;
	
	@Column(name = "cant_cumplida")
	private BigDecimal cantCumplida;
	
	@Column(name = "item_centro_t_id")
	private Integer itemCentroTId;
	
	@Column(name = "item_centro_t_nombre")
	private String itemCentroTNombre;
	
	@Column(name = "material_id")
	private Integer materialId;
	
	@Column(name = "material_desc")
	private String materialDescripcion;
	
	@Column(name = "material_peso")	
	private BigDecimal materialPeso;
	
	@Column(name = "material_long")
	private BigDecimal materialLongitud;
	
	@Column(name = "material_cant" )
	private BigDecimal materialCant;
	
	@Column(name = "material_centro_t_id")
	private Integer materialCentroTId;
	
	@Column(name = "material_centro_t_nombre")
	private String materialCentroTNombre;
	
	@Column(name = "materia_prima_material_id")
	private Integer materiaPrimaId;
	
	@Column(name = "materia_prima_material_desc")
	private String materiaPrimaDescripcion;
	
	@Column(name = "materia_prima_material_cant")
	private BigDecimal materiaPrimaCant;
	
	@Column(name = "estado_op")
	private Integer estadoOp;
	
	@Column(name = "un")
	private String un;
	
	@Column(name = "zona")
	private String zona;
	
	@Column(name = "grupo")
	private String grupo;
	
	@Column(name = "marca")
	private String marca;
	
	@Column(name = "cantidad_reportada_pieza")
	private Integer cantReportadaPieza;
	
	@Column(name = "cantidad_pendiente_pieza")
	private BigDecimal cantPendientePieza;
	
	@Column(name = "cantidad_reportada_material")
	private Integer cantReportadaMaterial;
	
	@Column(name = "cantidad_pendiente_material")
	private BigDecimal cantPendienteMaterial;
	
	@Column(name = "item_area")
	private BigDecimal itemArea;

	public VistaPiezasReportadas() {
		super();
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

	public String getOp() {
		return op;
	}

	public String getEsquemaPintura() {
		return esquemaPintura;
	}

	public Long getItemOpId() {
		return itemOpId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public String getItemDescripcion() {
		return itemDescripcion;
	}

	public BigDecimal getItemPeso() {
		return itemPeso;
	}

	public BigDecimal getItemLongitud() {
		return itemLongitud;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public String getItemColor() {
		return itemColor;
	}

	public BigDecimal getCantReq() {
		return cantReq;
	}

	public BigDecimal getCantCumplida() {
		return cantCumplida;
	}

	public Integer getItemCentroTId() {
		return itemCentroTId;
	}

	public String getItemCentroTNombre() {
		return itemCentroTNombre;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public String getMaterialDescripcion() {
		return materialDescripcion;
	}

	public BigDecimal getMaterialPeso() {
		return materialPeso;
	}

	public BigDecimal getMaterialLongitud() {
		return materialLongitud;
	}

	public BigDecimal getMaterialCant() {
		return materialCant;
	}

	public Integer getMaterialCentroTId() {
		return materialCentroTId;
	}

	public String getMaterialCentroTNombre() {
		return materialCentroTNombre;
	}

	public Integer getMateriaPrimaId() {
		return materiaPrimaId;
	}

	public String getMateriaPrimaDescripcion() {
		return materiaPrimaDescripcion;
	}

	public BigDecimal getMateriaPrimaCant() {
		return materiaPrimaCant;
	}

	public Integer getEstadoOp() {
		return estadoOp;
	}

	public String getUn() {
		return un;
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

	public Integer getCantReportadaPieza() {
		return cantReportadaPieza;
	}

	public BigDecimal getCantPendientePieza() {
		return cantPendientePieza;
	}

	public Integer getCantReportadaMaterial() {
		return cantReportadaMaterial;
	}

	public BigDecimal getCantPendienteMaterial() {
		return cantPendienteMaterial;
	}

	public BigDecimal getItemArea() {
		return itemArea;
	}

	@Override
	public String toString() {
		return "VistaPiezasReportadas [id=" + id + ", idOpIntegrapps=" + idOpIntegrapps + ", cliente=" + cliente
				+ ", op=" + op + ", esquemaPintura=" + esquemaPintura + ", itemOpId=" + itemOpId + ", itemId=" + itemId
				+ ", itemDescripcion=" + itemDescripcion + ", itemPeso=" + itemPeso + ", itemLongitud=" + itemLongitud
				+ ", prioridad=" + prioridad + ", itemColor=" + itemColor + ", cantReq=" + cantReq + ", cantCumplida="
				+ cantCumplida + ", itemCentroTId=" + itemCentroTId + ", itemCentroTNombre=" + itemCentroTNombre
				+ ", materialId=" + materialId + ", materialDescripcion=" + materialDescripcion + ", materialPeso="
				+ materialPeso + ", materialLongitud=" + materialLongitud + ", materialCant=" + materialCant
				+ ", materialCentroTId=" + materialCentroTId + ", materialCentroTNombre=" + materialCentroTNombre
				+ ", materiaPrimaId=" + materiaPrimaId + ", materiaPrimaDescripcion=" + materiaPrimaDescripcion
				+ ", materiaPrimaCant=" + materiaPrimaCant + ", estadoOp=" + estadoOp + ", un=" + un + ", zona=" + zona
				+ ", grupo=" + grupo + ", marca=" + marca + ", cantReportadaPieza=" + cantReportadaPieza
				+ ", cantPendientePieza=" + cantPendientePieza + ", cantReportadaMaterial=" + cantReportadaMaterial
				+ ", cantPendienteMaterial=" + cantPendienteMaterial + ", itemArea=" + itemArea + "]";
	}

}
