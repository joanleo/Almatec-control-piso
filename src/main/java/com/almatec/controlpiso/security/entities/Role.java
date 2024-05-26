package com.almatec.controlpiso.security.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "roles_web")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private Integer idRol;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "is_active")
	private Boolean isActivo;
	
	@JsonManagedReference
	@OneToMany(targetEntity=Usuario.class, mappedBy="role", fetch = FetchType.EAGER)
    private Set<Usuario> usuarios = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "role_permissions", 
      joinColumns = @JoinColumn(name = "role_id"), 
      inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

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


	public Set<Permission> getPermissions() {
		return permissions;
	}


	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}


	@Override
	public String toString() {
		return "Role [idRol=" + idRol + ", nombre=" + nombre + ", isActivo=" + isActivo + ", usuarios=" + usuarios
				+ ", permissions=" + permissions + "]";
	}
	
	
}
