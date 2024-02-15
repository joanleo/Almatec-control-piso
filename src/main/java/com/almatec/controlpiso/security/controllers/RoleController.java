package com.almatec.controlpiso.security.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.security.entities.Menu;
import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.services.MenuService;
import com.almatec.controlpiso.security.services.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;
	private final MenuService menuService;
	
	public RoleController(RoleService roleService, MenuService menuService) {
		this.roleService = roleService;
		this.menuService = menuService;
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
		List<Menu> menus = menuService.buscarMenus();
		modelo.addAttribute("role", role);
		modelo.addAttribute("menus", menus);
		return "configuracion/roles/formulario-role";
	}
}
