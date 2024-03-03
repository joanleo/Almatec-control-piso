package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = " pro_prioridad")
public class Prioridad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prio")
	private Integer id;
	
	@Column(name = "Item_id")
	private Long idItem;
	
	@Column(name = "prioridad")
	private Integer itemPrioridad;

	public Prioridad() {
		super();
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Integer getItemPrioridad() {
		return itemPrioridad;
	}

	public void setItemPrioridad(Integer itemPrioridad) {
		this.itemPrioridad = itemPrioridad;
	}
	
}
