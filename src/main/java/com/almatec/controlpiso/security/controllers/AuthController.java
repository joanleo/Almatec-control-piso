package com.almatec.controlpiso.security.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;


@Controller
public class AuthController {
	
	private final UsuarioService userService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public AuthController(UsuarioService userService) {
        this.userService = userService;
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
	public String home(Principal principal, HttpServletRequest request) {
		if (principal != null) {
			String clientIp = getClientIpAddress(request);
            String username = principal.getName();
            Usuario user = userService.ObtenerUsuarioPorNombreUsuario(username);
            
            logger.info("Usuario {} inició sesión correctamente desde IP {}. Rol: {}.", username, clientIp, user.getRole().getNombre());
		}
		return "home";
	}

	private String getClientIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}