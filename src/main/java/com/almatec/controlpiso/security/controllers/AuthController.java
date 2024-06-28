package com.almatec.controlpiso.security.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {
	
	/*private final CustomUserDetailsService userDetailsService;
	
	public AuthController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }*/

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
		/*String username = principal.getName();
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
            
        } catch (UsernameNotFoundException e) {
            // Manejar la excepción si el usuario no se encuentra
            return "redirect:/login"; // O cualquier otra acción apropiada
        }
        
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        System.out.println(userDetails);
        authorities.forEach(System.out::println);*/
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