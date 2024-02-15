package com.almatec.controlpiso.security.services;

import java.util.List;

import javax.transaction.Transactional;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.security.entities.Usuario;

public interface UsuarioService {

	Usuario ObtenerUsuarioPorNombreUsuario(String name);

	List<Usuario> buscarUsuarios();

	ErrorMensaje guardarUsuario(Usuario usuario);

	Usuario buscarUsuarioPorId(Integer id);

	@Transactional
	void inActivarUsuario(Integer id);

}
