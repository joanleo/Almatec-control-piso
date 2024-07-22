package com.almatec.controlpiso.calidad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calidad")
public class calidadController {
	
	@GetMapping("/formulario")
	public String obtenerFormulario() {
		return "calidad/formulario";
	}

}
