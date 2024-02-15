package com.almatec.controlpiso.security.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "usu_login" }) })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id")
	private Integer id;
	
	@Column(name = "usu_login")
	private String nombreUsuario;
	
	@Column(name = "usu_password")
	private String contrasena;
	
	@Column(name = "per_mail")
	private String correo;
	
	@Column(name = "usu_nombre")
	private String nombres;
	
	@Column(name = "comp_id")
	private Integer cia;
	
	@Column(name = "usu_activo")
	private Boolean isActivo=true;	
	
	@Column(name = "usu_pass_erp")
	private String contrasenaErp;
	
	@JsonBackReference
	@ManyToOne()
    @JoinColumn(name="role_id", referencedColumnName = "Id_Rol")
    private Role rol;
	
	@Column(name = "usu_docid")
	private String cedula;

	public Usuario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombre) {
		this.nombres = nombre;
	}

	public Integer getCia() {
		return cia;
	}

	public void setCia(Integer cia) {
		this.cia = cia;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}


	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	public String getContrasenaErp() {
		return contrasenaErp;
	}

	public void setContrasenaErp(String contrasenaErp) {
		this.contrasenaErp = contrasenaErp;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", contrasena=" + contrasena + ", correo="
				+ correo + ", nombres=" + nombres + ", cia=" + cia + ", isActivo=" + isActivo + ", contrasenaErp="
				+ contrasenaErp + ", rol=" + rol.getNombre() + ", cedula=" + cedula + "]";
	}
	
}
