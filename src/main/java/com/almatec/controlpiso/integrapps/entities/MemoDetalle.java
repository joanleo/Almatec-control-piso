package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "memo_detalle")
public class MemoDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_memo_detalle")
	private Long id;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "id_memo")
    private Memo memo;
	
	@ManyToOne
	@JoinColumn(name = "id_item_op")
	private ItemOp itemOp;
	
	private Integer cantidad;
	
	private String operacion;
	
	private String observacion;
	
	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "fecha_actualizacion")
	private LocalDateTime fechaActualizacion;
	
	
	
	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.fechaCreacion = now;
	}
	
	@PreUpdate
	protected void onUpDate() {
		this.fechaActualizacion = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public Memo getMemo() {
		return memo;
	}

	public ItemOp getItemOp() {
		return itemOp;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public String getOperacion() {
		return operacion;
	}

	public String getObservacion() {
		return observacion;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMemo(Memo memo) {
		this.memo = memo;
	}

	public void setItemOp(ItemOp itemOp) {
		this.itemOp = itemOp;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public void setObservacion(String descripcion) {
		this.observacion = descripcion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public MemoDetalle() {
		super();
	}

	@Override
	public String toString() {
		return "MemoDetalle [id=" + id + ", itemOp=" + itemOp + ", cantidad=" + cantidad + ", operacion=" + operacion
				+ ", observacion=" + observacion + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
				+ fechaActualizacion + "]";
	}
	
	
}
