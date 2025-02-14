package com.almatec.controlpiso.comunicador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.comunicador.entities.TipoNotificacion;

public interface TipoNotificacionRepository extends JpaRepository<TipoNotificacion, Integer> {

}
