package com.almatec.controlpiso.security.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.security.dtos.RoleDTO;
import com.almatec.controlpiso.security.entities.Permission;
import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.repositories.RoleRepository;
import com.almatec.controlpiso.security.services.PermissionService;
import com.almatec.controlpiso.security.services.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
	
	private final RoleRepository rolRepo;
	private final PermissionService permissionService;
	
	public RoleServiceImpl(RoleRepository rolRepo, PermissionService permissionService) {
		this.rolRepo = rolRepo;
		this.permissionService = permissionService;
	}

	@Override
	public List<Role> buscarRoles() {
		return rolRepo.findByIsActivoTrue();
	}

	@Override
	public Permission obtenerPermisoPorId(Long id) {
		return permissionService.findPermissionById(id);
	}

	@Override
	public void guardarRole(Role role) {
		try {
			rolRepo.save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarRole(Long idRole) {
		try {
			Role roleElimina = rolRepo.findById(idRole)
					.orElseThrow();
			rolRepo.delete(roleElimina);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
 
	@Override
	public RoleDTO obtenerRoleDTO(Long idRole) {
		try {
			Role roleEdita = rolRepo.findById(idRole)
					.orElseThrow();
			System.out.println("Role a editar:");
			System.out.println(roleEdita);
			ModelMapper mapper = new ModelMapper();
			return mapper.map(roleEdita, RoleDTO.class);
		} catch (Exception e) {
			e.printStackTrace();  
		}
		return null;
	}

	@Override
	public Role obtenerRole(Long idRole) {
		return rolRepo.findById(idRole).orElse(null);
	}

}
