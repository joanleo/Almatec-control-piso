package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.VistaItemsRutas;

public interface VistaItemsRutasRepository extends JpaRepository<VistaItemsRutas, Long> {

	@Query(value = "SELECT * "
			+ "FROM view_items_rutas "
			+ "WHERE C_centrotrabajo_id = :idCTConjunto", nativeQuery = true)
	List<VistaItemsRutas> findByIdCentroTrabajoConjunto(@Param("idCTConjunto")Integer idCTConjunto);

	@Query(value = "SELECT * "
			+ "FROM view_items_rutas "
			+ "WHERE idCentroTrabajoPerfil = :idCT", nativeQuery = true)
	List<VistaItemsRutas> findByIdCentroTrabajoPerfil(@Param("idCT") Integer idCT);

	@Query(value = "SELECT * "
			+ "FROM view_items_rutas "
			+ "WHERE C_centrotrabajo_id = :idCT "
			+ "OR idCentroTrabajoPerfil = :idCT2 ", nativeQuery = true)
	List<VistaItemsRutas> findByIdCentroTrabajoConjuntoOrIdCentroTrabajoPerfil(@Param("idCT") Integer idCT, @Param("idCT2") Integer idCT2);

	@Query(value = "SELECT * "
			+ "FROM view_items_rutas "
			+ "WHERE idOp = :idOp ", nativeQuery = true)
	List<VistaItemsRutas> findByIdOp( @Param("idOp") Integer idOp);

	@Query(value = "SELECT * "
			+ "FROM view_items_rutas "
			+ "WHERE ((item_id = :idItem) AND (C_centrotrabajo_id = :idCT)) "
			+ "OR ((item_id = :idItem) AND(idCentroTrabajoPerfil = :idCT)) ", nativeQuery = true)
	List<VistaItemsRutas> buscarItemCt(@Param("idItem") Long idItem, @Param("idCT")Integer idCT);

}
