package com.almatec.controlpiso.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
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
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Usuario usuario, RedirectAttributes flash) {
		ErrorMensaje error = usuarioService.guardarUsuario(usuario);
		
		if(Boolean.TRUE.equals(error.getError())) {
    		flash.addFlashAttribute("error", error.getMensaje());
    		return "redirect:/usuarios/nuevo";
    	}
    	flash.addFlashAttribute("message", "Usuario guardado exitosamente.");
        return "redirect:/usuarios";
	}
	
	@GetMapping("/editar/{id}")
	public String actualizar(Model modelo,
							@PathVariable Integer id) {
		Usuario usuario = usuarioService.buscarUsuarioPorId(id);
		List<Role> roles = rolService.buscarRoles();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("roles", roles);
		return "configuracion/usuarios/formulario-usuario";
	}
	
	@DeleteMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id,
							RedirectAttributes flash) {
		System.out.println("Solicitud eliminacion usuario");
		try {
			usuarioService.inActivarUsuario(id);
			flash.addFlashAttribute("message", "usuario eliminado exitosamente");
		}catch (Exception e) {
			flash.addFlashAttribute("error", "Error al tratar de elimiar el usuario");
		}
		return "redirect:/usuarios";
	}

	
}