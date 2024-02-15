package com.almatec.controlpiso.security.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.security.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByNombreUsuario(String username);

	List<Usuario> findByIsActivoTrue();

	@Modifying
	@Transactional
	@Query(value = "UPDATE usuarios "
			+ "SET usu_activo = 0 "
			+ "WHERE usu_id = :id", nativeQuery = true)
	int inActivarUsuario(@Param("id") Integer id);

}
  