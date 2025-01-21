package com.almatec.controlpiso.security.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almatec.controlpiso.security.dtos.RoleDTO;
import com.almatec.controlpiso.security.entities.Modulo;
import com.almatec.controlpiso.security.entities.Permission;
import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.services.ModuloService;
import com.almatec.controlpiso.security.services.PermissionService;
import com.almatec.controlpiso.security.services.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;
	private final ModuloService moduloServise;
	private final PermissionService permissionService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public RoleController(RoleService roleService, 
			ModuloService moduloServise,
			PermissionService permissionService) {
		this.roleService = roleService;
		this.moduloServise = moduloServise;
		this.permissionService = permissionService;
	}
	
	@GetMapping
	public String listar(Model modelo) {
		List<Role> roles = roleService.buscarRoles();
		modelo.addAttribute("roles", roles);
		return "configuracion/roles/listar-roles";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model modelo) throws JsonProcessingException {
		RoleDTO role = new RoleDTO();
		List<Modulo> modulos = moduloServise.findAll();
		log.info("Módulo de costos: {}", modulos.stream()
		        .filter(m -> m.getNombre().equals("Costos"))
		        .findFirst()
		        .orElse(null));
		ObjectMapper mapper = new ObjectMapper();
		String modulosJson = mapper.writeValueAsString(modulos);
		modelo.addAttribute("role", role);
		modelo.addAttribute("modulos", modulos);
		modelo.addAttribute("modulosJson", modulosJson);
		modelo.addAttribute("rolePermissionsJson", "[]");
		return "configuracion/roles/formulario-role";
	}
	
	@Transactional
	@PostMapping
	public String guardarRole(@ModelAttribute("role") RoleDTO roleDTO, RedirectAttributes flash) {
		log.info("Iniciando guardado de role: {}", roleDTO.getNombre());
	    long startTime = System.currentTimeMillis();
	    System.out.println(roleDTO);	    	    
	    
		Set<Permission> permissionsSet = convertStringToPermissionsSet(roleDTO.getPermissions());
		System.out.println("Permisos convertidos: " + permissionsSet);
		log.info("Tiempo conversión permisos: {}ms", System.currentTimeMillis() - startTime);
		
		
		Role roleSave = new Role();
		if(roleDTO.getIdRole() != null) {
			long dbFetchStart = System.currentTimeMillis();
			roleSave = roleService.obtenerRole(roleDTO.getIdRole());
			log.info("Tiempo fetch role: {}ms", System.currentTimeMillis() - dbFetchStart);
			if(roleSave != null) {
	            roleSave.setNombre(roleDTO.getNombre());
	            roleSave.setPermissions(permissionsSet);
	        } else {
	            // Si no existe, crear uno nuevo
	            roleSave = new Role();
	            roleSave.setNombre(roleDTO.getNombre());
	            roleSave.setPermissions(permissionsSet);
	        }
		}else {
			roleSave.setNombre(roleDTO.getNombre());
			roleSave.setPermissions(permissionsSet);						
		}
		
		long saveStart = System.currentTimeMillis();
	    roleService.guardarRole(roleSave);
	    log.info("Tiempo guardado role: {}ms", System.currentTimeMillis() - saveStart);
	    
	    log.info("Tiempo total: {}ms", System.currentTimeMillis() - startTime);
	    
		flash.addFlashAttribute("message", "Role guardado exitosamente");
        return "redirect:/roles";
	}
	
	@GetMapping("/editar/{idRole}")
	public String editarRole(Model modelo, @PathVariable Long idRole) throws JsonProcessingException {
		log.info("Iniciando edición de role con ID: {}", idRole);

	    try {
	        RoleDTO role = roleService.obtenerRoleDTOConPermisos(idRole);

	        List<Modulo> modulos = moduloServise.findAll();

	        ObjectMapper mapper = new ObjectMapper();
	        String modulosJson = mapper.writeValueAsString(modulos);
	        
	        // Procesamiento de los permisos
	        List<Map<String, String>> permissionsJson = new ArrayList<>();
	        if (role.getPermissions() != null && !role.getPermissions().isEmpty()) {
	            permissionsJson = Arrays.stream(role.getPermissions().split(","))
	            		.map(permission -> {
	                        String[] parts = permission.split(":"); // Divide id y nombre
	                        Map<String, String> permissionMap = new HashMap<>();
	                        permissionMap.put("id", parts[0]);     // ID del permiso
	                        permissionMap.put("name", parts[1]);   // Nombre del permiso
	                        return permissionMap;
	                    })
	                    .collect(Collectors.toList());
	        }
	        
	        String rolePermissionsJson = mapper.writeValueAsString(permissionsJson);
	        
	        modelo.addAttribute("role", role);
	        modelo.addAttribute("modulos", modulos);
	        modelo.addAttribute("modulosJson", modulosJson);
	        modelo.addAttribute("rolePermissionsJson", rolePermissionsJson);

	        return "configuracion/roles/formulario-role";
	    } catch (Exception e) {
	        log.error("Error al editar role: {}", e.getMessage(), e);
	        modelo.addAttribute("error", "Error al cargar el rol para editar");
	        return "redirect:/roles";
	    }
	}
	
	@PostMapping("/eliminar")
	public String removeRole(@RequestParam Long idRole, RedirectAttributes redirectAttributes) {
	    try {
	        roleService.eliminarRole(idRole);
	        return "redirect:/roles";
	    } catch (ResponseStatusException e) {
	        redirectAttributes.addFlashAttribute("error", e.getReason());
	        return "redirect:/roles";
	    }
	}
	
	private Set<Permission> convertStringToPermissionsSet(String permissionsString) {
		if (permissionsString == null || permissionsString.isEmpty()) {
	        return new HashSet<>();
	    }
		
		List<Long> permissionIds = Arrays.stream(permissionsString.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
		Set<Permission> permissions = new HashSet<>(permissionService.findAllByIdIn(permissionIds));
	    
	    log.info("Permisos convertidos: {}", permissions);
	    return permissions;
	}
}
