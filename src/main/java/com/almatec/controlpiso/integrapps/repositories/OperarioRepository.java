package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.Operario;


public interface OperarioRepository extends JpaRepository<Operario, Integer> {

	@Query(value = "SELECT pro_operario.C_prooperario_id "
			+ "FROM pro_operario "
			+ "RIGHT JOIN personas ON pro_operario.Per_Id = personas.Per_Id "
			+ "WHERE (personas.Barcode = :numCedula) ", nativeQuery = true)
	Integer obtenerIdOperario(@Param("numCedula") String numCedula);

}
