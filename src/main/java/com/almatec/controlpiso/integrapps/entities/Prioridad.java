package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.almatec.controlpiso.security.entities.Usuario;

@Entity
@Table(name = "pro_prioridad")
public class Prioridad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prio")
	private Integer id;
	
	@Column(name = "Item_id")
	private Long idItem;
	
	@Column(name = "prioridad")
	private Integer itemPrioridad;
	
	@ManyToOne
	@JoinColumn(name = "id_centro_trabajo")
	private CentroTrabajo centroTrabajo;
	
	@CreationTimestamp
	@Column(name = "fecha_crea")
	private LocalDateTime fechaCrea;
	
	@UpdateTimestamp
	@Column(name = "fecha_edita")
	private LocalDateTime fechaEdita;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_crea")
	private Usuario usuarioCrea;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_edita")
	private Usuario usuarioEdita; 

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

	public CentroTrabajo getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(CentroTrabajo centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public Usuario getUsuarioCrea() {
		return usuarioCrea;
	}

	public Usuario getUsuarioEdita() {
		return usuarioEdita;
	}

	public void setUsuarioCrea(Usuario usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public void setUsuarioEdita(Usuario usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}
	
}
