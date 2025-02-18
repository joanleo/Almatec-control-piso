package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "z_item_materia_prima")
public class ItemMaterial {
    @Id
    @Column(name = "id_item_material")
    private Integer id;

    @Column(name = "id_item")
    private Integer idItem;

    @Column(name = "id_materia_prima")
    private Integer idMateriaPrima;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "es_mp")
    private Boolean esMateriaPrima;

	public ItemMaterial() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public Integer getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public Boolean getActivo() {
		return activo;
	}

	public Boolean getEsMateriaPrima() {
		return esMateriaPrima;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setIdMateriaPrima(Integer idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setEsMateriaPrima(Boolean esMateriaPrima) {
		this.esMateriaPrima = esMateriaPrima;
	}

	@Override
	public String toString() {
		return "ItemMaterial [id=" + id + ", idItem=" + idItem + ", idMateriaPrima=" + idMateriaPrima + ", cantidad="
				+ cantidad + ", activo=" + activo + ", esMateriaPrima=" + esMateriaPrima + "]";
	}

    
}
