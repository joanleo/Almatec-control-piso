package com.almatec.controlpiso.security.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "web_modulos")
public class Modulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modulo")
	private Long id;
	
	private String nombre;
	
	@OneToOne(mappedBy = "modulo", cascade = CascadeType.ALL)
    private Permission permission;
	
	@OneToMany(mappedBy = "modulo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OpcionModulo> opciones;

	public Modulo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Set<OpcionModulo> getOpciones() {
		return opciones;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setOpciones(Set<OpcionModulo> opciones) {
		this.opciones = opciones;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Modulo [id=" + id + ", nombre=" + nombre + ", permission=" + permission + ", opciones=" + opciones
				+ "]";
	}	

}
