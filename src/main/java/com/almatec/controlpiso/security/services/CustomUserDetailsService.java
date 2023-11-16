package com.almatec.controlpiso.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.repositories.UsuarioRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByNombreUsuario(username)
				.orElseThrow(()-> new UsernameNotFoundException("Nombre de usuario, no encontrado: " + username));
		return User.withUsername(usuario.getNombreUsuario())
				.password(usuario.getContrasena())
				.roles("USER")
				.build();
	}

}
