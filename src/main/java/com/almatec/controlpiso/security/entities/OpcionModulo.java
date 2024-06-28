package com.almatec.controlpiso.security.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "web_opcion_modulo")
public class OpcionModulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_opcion")
	private Long idOpcion;
	
	private String descripcion;
	
	@OneToOne(mappedBy = "opcionModulo", cascade = CascadeType.ALL)
    private Permission permission;
	
	@ManyToOne
    @JoinColumn(name = "id_modulo")
	@JsonIgnore
    private Modulo modulo;

	public OpcionModulo() {
		super();
	}

	public Long getIdOpcion() {
		return idOpcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setIdOpcion(Long idOpcion) {
		this.idOpcion = idOpcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "OpcionModulo [idOpcion=" + idOpcion + ", descripcion=" + descripcion + "]";
	}
	
	
}
