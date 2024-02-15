package com.almatec.controlpiso.security.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "menusweb")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private String url;
	
	private String nombre;
	
	private Boolean isActivo=true;
	
	@Column(name = "fechaEdita", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
	private Date fechaEdita;
	
	@ManyToOne
    @JoinColumn(name = "idPadre")
    private Menu padre;
	
	@OneToMany(mappedBy = "padre")
    private List<Menu> submenus;
	
	@ManyToMany(mappedBy = "menus")
    private Set<Role> roles;

	public Menu() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Date getFechaEdita() {
		return fechaEdita;
	}

	public void setFechaEdita(Date fechaEdita) {
		this.fechaEdita = fechaEdita;
	}

	public Menu getPadre() {
		return padre;
	}

	public void setPadre(Menu padre) {
		this.padre = padre;
	}

	public List<Menu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<Menu> submenus) {
		this.submenus = submenus;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", url=" + url + ", nombre=" + nombre + ", isActivo=" + isActivo +  ", padre=" + padre.nombre +  "]";
	}
	
}
