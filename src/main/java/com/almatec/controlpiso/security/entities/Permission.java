package com.almatec.controlpiso.security.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permisos")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permiso")
	private Long id_permiso;

	private String name;

	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles;

	public Long getId_permiso() {
		return id_permiso;
	}

	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setId_permiso(Long id_permiso) {
		this.id_permiso = id_permiso;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Permission [id_permiso=" + id_permiso + ", name=" + name + ", roles=" + roles + "]";
	}

	public Permission() {
		super();
	}

}
