package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.ReporteCalidad;

public interface ReporteCalidadRepository extends JpaRepository<ReporteCalidad, Long> {

	Page<ReporteCalidad> findByProyectoContainingIgnoreCaseOrZonaContainingIgnoreCase(String search, String search2,
			Pageable pageable);

	Page<ReporteCalidad> findByProyectoContainingIgnoreCaseOrZonaContainingIgnoreCaseOrDescripcionItemContainingIgnoreCase(
			String search, String search2, String search3, Pageable pageable);

}
