package com.almatec.controlpiso.security.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.repositories.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByNombreUsuario(username)
				.orElseThrow(()-> new UsernameNotFoundException("Nombre de usuario, no encontrado: " + username));
		return User.withUsername(usuario.getNombreUsuario())
				.password(usuario.getContrasena())
				.authorities(getAuthorities(usuario.getRole()))
				.build();
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return role.getPermissions().stream()
        		.map(permission -> new SimpleGrantedAuthority(permission.getName()))
        		.collect(Collectors.toList());
    }

}
