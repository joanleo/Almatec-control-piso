package com.almatec.controlpiso.security.services;

import java.util.List;

import javax.transaction.Transactional;

import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.security.entities.Usuario;

public interface UsuarioService {

	Usuario ObtenerUsuarioPorNombreUsuario(String name);

	List<Usuario> buscarUsuarios();

	ResponseMessage guardarUsuario(Usuario usuario);

	Usuario buscarUsuarioPorId(Integer id);

	@Transactional
	void inActivarUsuario(Integer id);

}
