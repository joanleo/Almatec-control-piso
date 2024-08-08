package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}
