package com.almatec.controlpiso.security.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.repositories.UsuarioRepository;
import com.almatec.controlpiso.security.services.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Usuario ObtenerUsuarioPorNombreUsuario(String name) {
		return usuarioRepo.findByNombreUsuario(name)
				.orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));
		
	}

	@Override
	public List<Usuario> buscarUsuarios() {
		List<Usuario>  usuarios = usuarioRepo.findByIsActivoTrue();
		return usuarios;
	}

	@Override
	public ErrorMensaje guardarUsuario(Usuario usuario) {
		Usuario usuarioNuevo = null;
		if(usuario.getId() != null) {
			usuarioNuevo = usuarioRepo.findById(usuario.getId())
					.orElseThrow(()-> new ResourceNotFoundException("No se encontro el usuario con id: " + usuario.getId()));
		}else {
			usuarioNuevo = new Usuario();
		}
		
		usuarioNuevo.setCia(22);
		usuarioNuevo.setNombreUsuario(usuario.getNombreUsuario());
		usuarioNuevo.setContrasenaErp(usuario.getContrasena());
		usuarioNuevo.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
		usuarioNuevo.setNombres(usuario.getNombres());
		usuarioNuevo.setCedula(usuario.getCedula());
		usuarioNuevo.setCorreo(usuario.getCorreo());
		usuarioNuevo.setRol(usuario.getRol());
		
		try {
			usuarioRepo.save(usuarioNuevo);
			return new ErrorMensaje(false, "");
		} catch (DataIntegrityViolationException e) {
			return new ErrorMensaje(true, "El nombre de usuario " + usuario.getNombreUsuario() + " ya existe.");
		}
	}

	@Override
	public Usuario buscarUsuarioPorId(Integer id) {
		return usuarioRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro el usuario con id: " + id));
	}

	@Override
	public void inActivarUsuario(Integer id) {
		Usuario usuario = usuarioRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro el usuario con id: " + id));
		System.out.println("Se eliminara el usuario: " + usuario.getNombres());
		
		try {
			usuarioRepo.inActivarUsuario(usuario.getId());
			System.out.println("Servicio inactiva usuario");
			
		} catch (Exception e) {
			System.out.println("Error al cambiar en la bd: " + e);
		}
		
	}

}
