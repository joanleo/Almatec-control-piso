package com.almatec.controlpiso.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public String obtenerFechaFormateada() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
        return formatoFecha.format(new Date());
    }
	
	public String obtenerFechaFormateada(Date fecha) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
		return formatoFecha.format(fecha);
	}
}
