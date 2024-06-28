package com.almatec.controlpiso.security.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almatec.controlpiso.security.dtos.RoleDTO;
import com.almatec.controlpiso.security.entities.Modulo;
import com.almatec.controlpiso.security.entities.Permission;
import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.services.ModuloService;
import com.almatec.controlpiso.security.services.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;
	private final ModuloService moduloServise;
	
	public RoleController(RoleService roleService, ModuloService moduloServise) {
		this.roleService = roleService;
		this.moduloServise = moduloServise;
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
		ObjectMapper mapper = new ObjectMapper();
		String modulosJson = mapper.writeValueAsString(modulos);
		modelo.addAttribute("role", role);
		modelo.addAttribute("modulos", modulos);
		modelo.addAttribute("modulosJson", modulosJson);
		return "configuracion/roles/formulario-role";
	}
	
	@PostMapping
	public String guardarRole(@ModelAttribute("role") RoleDTO roleDTO, RedirectAttributes flash) {
		System.out.println("se recibe la solicitud post");
		System.out.println(roleDTO);
		Set<Permission> permissionsSet = convertStringToPermissionsSet(roleDTO.getPermissions());
		Role roleSave = new Role();
		if(roleDTO.getIdRole() != null) {
			roleSave = roleService.obtenerRole(roleDTO.getIdRole());
			roleSave.setPermissions(permissionsSet);
			roleService.guardarRole(roleSave);
		}else {
			Role role = new Role();
			role.setNombre(roleDTO.getNombre());
			role.setPermissions(permissionsSet);			
			roleService.guardarRole(role);
			
			System.out.println(role);
		}
	    
		flash.addFlashAttribute("message", "Role guardado exitosamente");
        return "redirect:/roles";
	}
	
	@GetMapping("/editar/{idRole}")
	public String editarRole(Model modelo, @PathVariable Long idRole) throws JsonProcessingException {
		RoleDTO role = roleService.obtenerRoleDTO(idRole);
		List<Modulo> modulos = moduloServise.findAll();
		ObjectMapper mapper = new ObjectMapper();
		String modulosJson = mapper.writeValueAsString(modulos);
		
		modelo.addAttribute("role", role);
		modelo.addAttribute("modulos", modulos);
		modelo.addAttribute("modulosJson", modulosJson);
		return "configuracion/roles/formulario-role";
	}
	
	@PostMapping("/eliminar")
    public String removeRole(@RequestParam Long idRole) {
        roleService.eliminarRole(idRole);
        return "redirect:/roles";
    }
	
	private Set<Permission> convertStringToPermissionsSet(String permissionsString) {
	    Set<Permission> permissionsSet = new HashSet<>();
	    if (permissionsString != null && !permissionsString.isEmpty()) {
	        String[] permissionsArray = permissionsString.split(",");
	        for (String permissionId : permissionsArray) {
	            if (!permissionId.isEmpty()) {
	                Long id = Long.valueOf(permissionId);
	                Permission permission = roleService.obtenerPermisoPorId(id);
	                permissionsSet.add(permission);
	            }
	        }
	    }
	    return permissionsSet;
	}
}
