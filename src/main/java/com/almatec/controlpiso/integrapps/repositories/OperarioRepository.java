package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.utils.dto.OperarioBarCodeDTO;


public interface OperarioRepository extends JpaRepository<Operario, Integer> {

	@Query(value = "SELECT pro_operario.C_prooperario_id "
			+ "FROM pro_operario "
			+ "RIGHT JOIN personas ON pro_operario.Per_Id = personas.Per_Id "
			+ "WHERE (personas.Per_Doc_Num = :numCedula) ", nativeQuery = true)
	Integer obtenerIdOperario(@Param("numCedula") String numCedula);

	@Query(value = "SELECT pro_operario.A_Operario_Nombre, personas.Barcode " +
		       "FROM pro_operario " +
		       "INNER JOIN personas ON pro_operario.Per_Id = personas.Per_Id " +
		       "WHERE pro_operario.E_activo = 1", nativeQuery = true)
	List<Object[]> buscarDataBarCodeOperarios();

}
