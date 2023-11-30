package com.almatec.controlpiso.security.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Roles_Integrapps")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Rol")
	private Integer idRol;
	
	@Column(name = "Rol_Nombre")
	private String nombre;
	
	@Column(name = "Rol_Activo")
	private Boolean isActivo;
	
	@OneToMany(targetEntity=Usuario.class, mappedBy="rol",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Usuario> usuarios = new HashSet<>();

	public Role() {
		super();
	}


	public Integer getIdRol() {
		return idRol;
	}


	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Role [id=" + idRol + ", nombre=" + nombre + ", isActivo=" + isActivo + "]";
	}
	
	
}
