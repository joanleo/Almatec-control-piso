package com.almatec.controlpiso.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.security.entities.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {
	
	@Query("SELECT DISTINCT m FROM Modulo m " +
	           "LEFT JOIN FETCH m.permission p " +
	           "LEFT JOIN FETCH m.opciones o " +
	           "LEFT JOIN FETCH o.permission op " +
	           "ORDER BY m.id")
    List<Modulo> findAllWithPermissionsAndOptions();
}