package com.almatec.controlpiso.comunicador.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.comunicador.entities.Mensaje;


public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

	@Query(value = "SELECT  msg_emails "
			+ "FROM   mensajes_tipo "
			+ "WHERE   (msg_tipo_desc = :tipoMensaje) "
			+ "AND (msg_tipo_activo = 1)", nativeQuery = true)
	String obtenerDestinatarios(@Param("tipoMensaje") String tipoMensaje);

}
