package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.entities.Usuario;

public class UsuarioDTO {

	private Integer id;
	private String nombreUsuario;
	private String nombres;
	private Role rol;
	
	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nombreUsuario = usuario.getNombreUsuario();
		this.nombres = usuario.getNombres();
		this.rol = usuario.getRol();
	}

	public Integer getId() {
		return id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getNombres() {
		return nombres;
	}

	public Role getRol() {
		return rol;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}
}
