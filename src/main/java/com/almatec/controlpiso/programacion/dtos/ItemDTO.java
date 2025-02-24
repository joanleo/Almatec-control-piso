package com.almatec.controlpiso.programacion.dtos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class ItemDTO {
	
	private Long itemOpId;	
	private Integer itemId;	
	private String itemDescripcion;	
	private BigDecimal itemPeso;	
	private BigDecimal itemLongitud;	
	private String itemColor;	
	private Integer itemCentroTId;	
	private String itemCentroTNombre;	
	private String grupo;	
	private String marca;	
	private BigDecimal cantReq;	
	private BigDecimal cantCumplida;	
	private Integer cantReportadaPieza;	
	private BigDecimal cantPendientePieza;
	private Set<ComponenteDTO> componentes = new HashSet<>();
	private Integer prioridad;
	private Integer materiaPrimaId;
	private String materiaPrimaDescripcion;
	private BigDecimal materiaPrimaCant;
	private BigDecimal itemArea;
	
	public ItemDTO() {
		super();
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

	public void setItemOpId(Long itemOpId) {
		this.itemOpId = itemOpId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setItemDescripcion(String itemDescripcion) {
		this.itemDescripcion = itemDescripcion;
	}

	public void setItemPeso(BigDecimal itemPeso) {
		this.itemPeso = itemPeso;
	}

	public void setItemLongitud(BigDecimal itemLongitud) {
		this.itemLongitud = itemLongitud;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}

	public void setCantReq(BigDecimal cantReq) {
		this.cantReq = cantReq;
	}

	public void setCantCumplida(BigDecimal cantCumplida) {
		this.cantCumplida = cantCumplida;
	}

	public void setItemCentroTId(Integer itemCentroTId) {
		this.itemCentroTId = itemCentroTId;
	}

	public void setItemCentroTNombre(String itemCentroTNombre) {
		this.itemCentroTNombre = itemCentroTNombre;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setCantReportadaPieza(Integer cantReportadaPieza) {
		this.cantReportadaPieza = cantReportadaPieza;
	}

	public void setCantPendientePieza(BigDecimal cantPendientePieza) {
		this.cantPendientePieza = cantPendientePieza;
	}

	public Set<ComponenteDTO> getComponentes() {
		return componentes;
	}

	public void setComponentes(Set<ComponenteDTO> componentes) {
		this.componentes = componentes;
	}
	
	public void addComponente(ComponenteDTO componente) {
		componentes.add(componente);
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

	public void setMateriaPrimaId(Integer materiaPrimaId) {
		this.materiaPrimaId = materiaPrimaId;
	}

	public void setMateriaPrimaDescripcion(String materiaPrimaDescripcion) {
		this.materiaPrimaDescripcion = materiaPrimaDescripcion;
	}

	public void setMateriaPrimaCant(BigDecimal materiaPrimaCant) {
		this.materiaPrimaCant = materiaPrimaCant;
	}

	public BigDecimal getItemArea() {
		return itemArea;
	}

	public void setItemArea(BigDecimal itemArea) {
		this.itemArea = itemArea;
	}

	@Override
	public String toString() {
		return "ItemDTO [itemOpId=" + itemOpId + ", itemId=" + itemId + ", itemDescripcion=" + itemDescripcion
				+ ", itemPeso=" + itemPeso + ", itemLongitud=" + itemLongitud + ", itemColor=" + itemColor
				+ ", itemCentroTId=" + itemCentroTId + ", itemCentroTNombre=" + itemCentroTNombre + ", grupo=" + grupo
				+ ", marca=" + marca + ", cantReq=" + cantReq + ", cantCumplida=" + cantCumplida
				+ ", cantReportadaPieza=" + cantReportadaPieza + ", cantPendientePieza=" + cantPendientePieza
				+ ", componentes=" + componentes + ", prioridad=" + prioridad + ", materiaPrimaId=" + materiaPrimaId
				+ ", materiaPrimaDescripcion=" + materiaPrimaDescripcion + ", materiaPrimaCant=" + materiaPrimaCant
				+ ", itemArea=" + itemArea + "]";
	}

}
