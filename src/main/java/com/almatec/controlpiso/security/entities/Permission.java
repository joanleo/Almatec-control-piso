package com.almatec.controlpiso.security.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "web_permisos")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permiso")
	private Long idPermiso;

	private String name;
	
	@OneToOne
    @JoinColumn(name = "id_modulo")
	@JsonIgnore
    private Modulo modulo;
	
	@OneToOne
    @JoinColumn(name = "opcion_modulo_id", referencedColumnName = "id_opcion")
	@JsonIgnore
    private OpcionModulo opcionModulo;

	@ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Role> roles;

	public Long getIdPermiso() {
		return idPermiso;
	}

	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setIdPermiso(Long id_permiso) {
		this.idPermiso = id_permiso;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public OpcionModulo getOpcionModulo() {
		return opcionModulo;
	}

	public void setOpcionModulo(OpcionModulo opcionModulo) {
		this.opcionModulo = opcionModulo;
	}

	public Permission() {
		super();
	}

	@Override
	public String toString() {
		return "Permission [idPermiso=" + idPermiso + ", name=" + name +  "]";
	}


}
