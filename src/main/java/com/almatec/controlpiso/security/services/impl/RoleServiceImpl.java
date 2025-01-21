package com.almatec.controlpiso.security.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
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
	@Transactional
	public void guardarRole(Role role) {
		try {
			log.debug("Guardando role: {}", role.getNombre());
			rolRepo.save(role);
		} catch (Exception e) {
			log.error("Error al guardar role: {}", e.getMessage(), e);
            throw new RuntimeException("Error al guardar role", e);
		}
		
	}

	public void eliminarRole(Long idRole) {
	    try {
	        Role roleElimina = rolRepo.findById(idRole)
	                .orElseThrow();
	        rolRepo.delete(roleElimina);
	    } catch (DataIntegrityViolationException e) {
	        throw new ResponseStatusException(HttpStatus.CONFLICT, "No se puede eliminar el rol porque está en uso.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al eliminar el rol.");
	    }
	}
 
	@Override
	public RoleDTO obtenerRoleDTO(Long idRole) {
		try {
			Role roleEdita = rolRepo.findById(idRole)
					.orElseThrow(() -> new RuntimeException("Role no encontrado"));
			RoleDTO dto = new RoleDTO();
	        dto.setIdRole(roleEdita.getIdRole());
	        dto.setNombre(roleEdita.getNombre());
	        dto.setPermissions(roleEdita.getPermissions().stream()
	            .map(p -> p.getIdPermiso().toString())
	            .collect(Collectors.joining(",")));
	        
	        return dto;
		} catch (Exception e) {
			e.printStackTrace();  
			return null;
		}
	}

	@Override
	public Role obtenerRole(Long idRole) {
		try {
			Role role =rolRepo.findByIdRole(idRole).orElseThrow(()-> new RuntimeException("No se encontro el role con id: " + idRole));
			System.out.println(role);
			return role;
		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
	}

	@Override
	public RoleDTO obtenerRoleDTOConPermisos(Long idRole) {
	    Role role = rolRepo.findByIdWithPermissions(idRole)
	        .orElseThrow(() -> new RuntimeException("Role no encontrado"));

	    RoleDTO roleDTO = new RoleDTO();
	    roleDTO.setIdRole(role.getIdRole());
	    roleDTO.setNombre(role.getNombre());
	    roleDTO.setPermissions(
	        role.getPermissions().stream()
	        	.map(permission -> String.format("%d:%s", permission.getIdPermiso(), permission.getName()))
	            .collect(Collectors.joining(","))
	    );

	    return roleDTO;
	}

}
