package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.integrapps.entities.NovedadCt;

public interface NovedadCtRepository extends JpaRepository<NovedadCt, Integer> {

	@Query(value = "SELECT MAX(novedad_id) FROM  pro_novedades_ct", nativeQuery = true)
	Integer obtenerUltimoconsecutivo();

}
