package com.almatec.controlpiso.security.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.almatec.controlpiso.security.services.CustomUserDetailsService;


@Controller
public class AuthController {
	
	private final CustomUserDetailsService userDetailsService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public AuthController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

	@GetMapping("/login")
	public String iniciarSecion() {
		/*String rawPassword = "1234";
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 String encryptedPassword = passwordEncoder.encode(rawPassword);
		 System.out.println("Contraseña encriptada: " + encryptedPassword);*/
		return "login";
	}
	
	@GetMapping("/")
	public String home(Principal principal) {
		if (principal != null) {
            String username = principal.getName();
            //UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            logger.info("Usuario {} logueado.", username);
		}
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