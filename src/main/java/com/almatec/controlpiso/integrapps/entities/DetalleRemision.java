package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "remision_detalle")
public class DetalleRemision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_remision")
	private Long idDetalleRemision;
	
	@ManyToOne
	@JoinColumn(name = "id_remision")
	@JsonBackReference
	@JsonIgnore
	private Remision remision;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item_op")
	private ItemOp itemOp;
	
	@Column(name = "cant")
	private Integer cantidad;
	
	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;
	
	
	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.fechaCreacion = now;
	}

	public DetalleRemision() {
		super();
	}

	public Long getIdDetalleRemision() {
		return idDetalleRemision;
	}

	public Remision getRemision() {
		return remision;
	}

	public ItemOp getItemOp() {
		return itemOp;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setIdDetalleRemision(Long idDetalleRemision) {
		this.idDetalleRemision = idDetalleRemision;
	}

	public void setRemision(Remision remision) {
		this.remision = remision;
	}

	public void setItemOp(ItemOp itemOp) {
		this.itemOp = itemOp;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "DetalleRemision [idDetalleRemision=" + idDetalleRemision + ", itemOp=" + itemOp + ", cantidad="
				+ cantidad + ", fechaCreacion=" + fechaCreacion + "]";
	}
	
	
}
