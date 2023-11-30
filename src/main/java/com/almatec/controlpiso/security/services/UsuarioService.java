package com.almatec.controlpiso.security.services;

import java.util.List;

import com.almatec.controlpiso.security.entities.Usuario;

public interface UsuarioService {

	Usuario ObtenerUsuarioPorNombreUsuario(String name);

	List<Usuario> buscarUsuarios();

}
