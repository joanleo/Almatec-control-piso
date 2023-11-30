package com.almatec.controlpiso.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;

@Controller
@RequestMapping("/centros-trabajo")
public class CentroTrabajoController {
	
	private final CentroTrabajoService centroTrabajoService;
	private final UsuarioService usuarioService;

	public CentroTrabajoController(CentroTrabajoService centroTrabajoService, UsuarioService usuarioService) {
	    this.centroTrabajoService = centroTrabajoService;
	    this.usuarioService = usuarioService;
	}

	
	private Usuario obtenerUsuarioAtenticado() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
	}

	@GetMapping
	public String listarCentrosTrabajo(Model modelo) {
		Usuario usuario = obtenerUsuarioAtenticado();
		List<CentroTrabajo> centrosTrabajo = centroTrabajoService.buscarCentrosTrabajo(usuario.getCia());
		
		modelo.addAttribute("centrosTrabajo", centrosTrabajo);
		return "configuracion/centros-trabajo/listar-centros-trabajo";
	}
	
	@GetMapping("/nuevo")
	public String nuevoCentroTrabajo(Model modelo) {
		Usuario usuario = obtenerUsuarioAtenticado();
		List<CentroOperacion> centrosOperacion = centroTrabajoService.buscarCentrosOperacion(usuario.getCia());
		List<CentroTrabajo> centrosTrabajo = centroTrabajoService.buscarCentrosTrabajo(usuario.getCia());
		CentroTrabajo centroTrabajo = new CentroTrabajo();
		modelo.addAttribute("centrosOperacion", centrosOperacion);
		modelo.addAttribute("centrosTrabajo", centrosTrabajo);
		modelo.addAttribute("centroTrabajo", centroTrabajo);
		return "configuracion/centros-trabajo/formulario-centro-trabajo";
	}
	
	@GetMapping("/actualizar/{id}")
	public String actualizar(Model modelo, @PathVariable Long id) {
		CentroTrabajo centroTrabajo = centroTrabajoService.buscarCentroTrabajo(id);
		Usuario usuario = obtenerUsuarioAtenticado();
		List<CentroOperacion> centrosOperacion = centroTrabajoService.buscarCentrosOperacion(usuario.getCia());
		List<CentroTrabajo> centrosTrabajo = centroTrabajoService.buscarCentrosTrabajo(usuario.getCia());
		modelo.addAttribute("centroTrabajo", centroTrabajo);
		modelo.addAttribute("centrosOperacion", centrosOperacion);
		modelo.addAttribute("centrosTrabajo", centrosTrabajo);
		return "configuracion/centros-trabajo/formulario-centro-trabajo";
	}
	
	public String guardar(@ModelAttribute("centroTrabajo") CentroTrabajo centroTrabajo) {
		centroTrabajoService.guardar(centroTrabajo);
		return "redirect:/centros-trabajo";
	}

}
