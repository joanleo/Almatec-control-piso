package com.almatec.controlpiso.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.RoleService;
import com.almatec.controlpiso.security.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	private final RoleService rolService;

	public UsuarioController(UsuarioService usuarioService, RoleService rolService) {
		super();
		this.usuarioService = usuarioService;
		this.rolService = rolService;
	}
	
	@GetMapping
	public String listar(Model modelo) {
		List<Usuario> usuarios = usuarioService.buscarUsuarios();
		modelo.addAttribute("usuarios", usuarios);
		return "configuracion/usuarios/listar-usuarios";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		Usuario usuario = new Usuario();
		List<Role> roles = rolService.buscarRoles();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("roles", roles);
		return "configuracion/usuarios/formulario-usuario";
	}
	
	public String actualizar(Model modelo) {
		
		return "configuracion/usuarios/formulario-usuario";
	}

	
}
