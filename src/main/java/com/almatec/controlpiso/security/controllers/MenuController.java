package com.almatec.controlpiso.security.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.security.entities.Menu;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.MenuService;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Controller
@RequestMapping("/menus")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@Autowired UtilitiesApp util;
	
	@GetMapping
	public String listar(Model modelo) {
		Usuario usuario = util.obtenerUsuarioAtenticado();
		//System.out.println(usuario.getRol().getMenus());
		List<Menu> menus = menuService.buscarMenus();
		modelo.addAttribute("menus", menus);
		return "configuracion/menus/listar-menus";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		Menu menu = new Menu();
		List<Menu> menus = menuService.buscarMenus();
		modelo.addAttribute("menu", menu);
		modelo.addAttribute("menus", menus);
		return "configuracion/menus/formulario-menu";
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("menu") Menu menu) {
		menu.setFechaEdita(new Date());
		menuService.guardar(menu);
		return "redirect:/menus";
	}
	
}
