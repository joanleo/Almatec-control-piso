package com.almatec.controlpiso.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produccion")
public class ProduccionController {

	@GetMapping("/home")
	public String homeProduction() {
		return "produccion/home-production";
	}
}
