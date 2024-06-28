package com.almatec.controlpiso.security.dtos;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RoleDTO {
	private Long idRole;
    private String nombre;
    private String permissions;
    
	public RoleDTO() {
		super();
	}

	public Long getIdRole() {
		return idRole;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setIdRole(Long idRol) {
		this.idRole = idRol;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPermissions(String permissions) {
        this.permissions = Arrays.stream(permissions.split(","))
                                 .distinct()
                                 .collect(Collectors.joining(","));
    }

	@Override
	public String toString() {
		return "RoleDTO [idRole=" + idRole + ", nombre=" + nombre + ", permissions=" + permissions + "]";
	}
    
    
}
