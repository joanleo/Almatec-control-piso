package com.almatec.controlpiso.security.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String iniciarSecion() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Principal principal) {
		return "home";
	}
}


/*
 * Codigo para encriptar contraseña:
 * String rawPassword = "1234";
 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
 * String encryptedPassword = passwordEncoder.encode(rawPassword);
 * System.out.println("Contraseña encriptada: " + encryptedPassword);
 * 
 * */