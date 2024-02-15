package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.VistaItemsRutas;

public interface VistaItemsRutasRepository extends JpaRepository<VistaItemsRutas, Long> {

	
	List<VistaItemsRutas> findByIdCentroTrabajoConjunto(Integer idCTConjunto);

	@Query(value = "SELECT * "
			+ "FROM view_items_rutas "
			+ "WHERE idCentroTrabajoPerfil = :idCT", nativeQuery = true)
	List<VistaItemsRutas> findByIdCentroTrabajoPerfil(@Param("idCT") Integer idCT);

	List<VistaItemsRutas> findByIdCentroTrabajoConjuntoOrIdCentroTrabajoPerfil(Integer idCT, Integer idCT2);

}
