package com.almatec.controlpiso.security.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.repositories.UsuarioRepository;
import com.almatec.controlpiso.security.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public Usuario ObtenerUsuarioPorNombreUsuario(String name) {
		return usuarioRepo.findByNombreUsuario(name)
				.orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));
		
	}

	@Override
	public List<Usuario> buscarUsuarios() {
		List<Usuario>  usuarios = usuarioRepo.findByIsActivoTrue();
		for(Usuario usuario:usuarios) {
			System.out.println(usuario);
		}
		return usuarios;
	}

}
