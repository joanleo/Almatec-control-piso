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
@Table(name = "web_roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private Long idRole;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "is_active")
	private Boolean isActivo = true;
	
	@JsonManagedReference
	@OneToMany(targetEntity=Usuario.class, mappedBy="role", fetch = FetchType.EAGER)
    private Set<Usuario> usuarios = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "web_role_permissions", 
      joinColumns = @JoinColumn(name = "id_role"), 
      inverseJoinColumns = @JoinColumn(name = "id_permission"))
    private Set<Permission> permissions;

	public Role() {
		super();
	}


	public Long getIdRole() {
		return idRole;
	}


	public void setIdRole(Long idRol) {
		this.idRole = idRol;
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
		return "Role [idRole=" + idRole + ", nombre=" + nombre + ", isActivo=" + isActivo + ", permissions=" + permissions + "]";
	}
	
	
}
