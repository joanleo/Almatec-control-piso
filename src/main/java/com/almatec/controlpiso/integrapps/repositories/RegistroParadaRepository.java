package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.RegistroParada;

public interface RegistroParadaRepository extends JpaRepository<RegistroParada, Integer> {

	RegistroParada findByIdConfigProcesoAndIdOperarioAndIdParada(Integer idConfigProceso, Integer idOperario,
			Long idParada);

}
