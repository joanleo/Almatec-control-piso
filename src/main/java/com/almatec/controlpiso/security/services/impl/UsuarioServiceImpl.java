package com.almatec.controlpiso.security.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
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
		return usuarioRepo.findByIsActivoTrue();
	}

	@Override
	public ResponseMessage guardarUsuario(Usuario usuario) {
		Usuario usuarioNuevo = null;
		if(usuario.getId() != null) {
			usuarioNuevo = usuarioRepo.findById(usuario.getId())
					.orElseThrow(()-> new ResourceNotFoundException("No se encontro el usuario con id: " + usuario.getId()));
		}else {
			usuarioNuevo = new Usuario();
			if(usuario.getContrasena() == null || "".equals(usuario.getContrasena())) {
				return new ResponseMessage(true, "Debe proporcionar una contraseña ");
			}
		}
		
		usuarioNuevo.setCia(22);
		usuarioNuevo.setNombreUsuario(usuario.getNombreUsuario());
		if (!"".equals(usuario.getContrasena()) && !usuario.getContrasena().equals(usuarioNuevo.getContrasenaErp())) {
		    usuarioNuevo.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
		    usuarioNuevo.setContrasenaErp(usuario.getContrasena());
		}
		usuarioNuevo.setNombres(usuario.getNombres());
		usuarioNuevo.setCedula(usuario.getCedula());
		usuarioNuevo.setCorreo(usuario.getCorreo());
		usuarioNuevo.setRole(usuario.getRole());
		
		try {
			usuarioRepo.save(usuarioNuevo);
			return new ResponseMessage(false, "");
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseMessage(true, "El nombre de usuario " + usuario.getNombreUsuario() + " ya existe.");
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
		
		try {
			usuarioRepo.inActivarUsuario(usuario.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
