package com.almatec.controlpiso.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;

@Service
public class UtilitiesApp {
	
	@Autowired
	private UsuarioService usuarioService;

	public Usuario obtenerUsuarioAtenticado() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
	}
}
