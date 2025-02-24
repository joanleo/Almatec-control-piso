package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.Parada;

public interface ParadaRepository extends JpaRepository<Parada, Long> {
	
	boolean existsById(Long paradaId);

	List<Parada> findByIsActivoTrue();

}
