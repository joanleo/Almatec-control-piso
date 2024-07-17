package com.almatec.controlpiso.integrapps.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Prioridad;

public interface PrioridadRepository extends JpaRepository<Prioridad, Integer> {

	Prioridad findByIdItem(Long idItem);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE  pro_prioridad "
			+ "SET prioridad = :#{#prioridadFound.itemPrioridad} "
			+ "WHERE Item_id = :#{#prioridadFound.idItem} ", nativeQuery = true)
	void updatePrioridad(@Param("prioridadFound") Prioridad prioridadFound);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO pro_prioridad (Item_id, prioridad) "
			+ "VALUES (:#{#nuevo.idItem}, :#{#nuevo.itemPrioridad} ) ", nativeQuery = true)
	void crearPrioridad(@Param("nuevo") Prioridad nuevo);

	Optional<Prioridad> findByIdItemAndCentroTrabajo(long longValue, CentroTrabajo centroTrabajo);

}

