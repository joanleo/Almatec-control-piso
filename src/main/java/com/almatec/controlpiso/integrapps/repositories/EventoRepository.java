package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
