package com.almatec.controlpiso.security.services;

import java.util.List;

import com.almatec.controlpiso.security.dtos.RoleDTO;
import com.almatec.controlpiso.security.entities.Permission;
import com.almatec.controlpiso.security.entities.Role;

public interface RoleService {

	List<Role> buscarRoles();

	Permission obtenerPermisoPorId(Long id);

	void guardarRole(Role role);

	void eliminarRole(Long roleId);

	RoleDTO obtenerRoleDTO(Long idRole);

	Role obtenerRole(Long idRol);

}
