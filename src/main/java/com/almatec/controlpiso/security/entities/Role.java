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
	
	@JsonManagedReference
	@OneToMany(targetEntity=Usuario.class, mappedBy="rol", fetch = FetchType.EAGER)
    private Set<Usuario> usuarios = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "menusweb_roles", 
      joinColumns = @JoinColumn(name = "idRol"),
      inverseJoinColumns = @JoinColumn(name = "idMenu"))
    private Set<Menu> menus;

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

	/*public Set<Menu> getMenus() {
		return menus;
	}


	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}*/


	@Override
	public String toString() {
		return "Role [idRol=" + idRol + ", nombre=" + nombre + ", isActivo=" + isActivo + ", usuarios=" + usuarios
				+"]";
	}

	
	
}
