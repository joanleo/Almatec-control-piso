package com.almatec.controlpiso.security.controllers;

import java.security.Principal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.almatec.controlpiso.security.services.CustomUserDetailsService;


@Controller
public class AuthController {
	
	private final CustomUserDetailsService userDetailsService;
	
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
		/*if (principal != null) {
            String username = principal.getName();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            System.out.println("Usuario logueado: " + username);
            System.out.println("Roles del usuario:");
            userDetails.getAuthorities().forEach(authority -> 
                System.out.println("- " + authority.getAuthority())
            );
        }*/
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