package com.almatec.controlpiso.security.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.services.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping
	public String listar(Model modelo) {
		List<Role> roles = roleService.buscarRoles();
		modelo.addAttribute("roles", roles);
		return "configuracion/roles/listar-roles";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		Role role = new Role();
		modelo.addAttribute("role", role);
		return "configuracion/roles/formulario-role";
	}
}
