package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.produccion.dtos.OperarioGeneralDTO;


public interface OperarioRepository extends JpaRepository<Operario, Integer> {

	@Query(value = "SELECT pro_operario.C_prooperario_id "
			+ "FROM pro_operario "
			+ "RIGHT JOIN personas ON pro_operario.Per_Id = personas.Per_Id "
			+ "WHERE (personas.Per_Doc_Num = :numCedula) ", nativeQuery = true)
	Integer obtenerIdOperario(@Param("numCedula") String numCedula);

	@Query(value = "SELECT pro_operario.A_Operario_Nombre, personas.Barcode " +
		       "FROM pro_operario " +
		       "INNER JOIN personas ON pro_operario.Per_Id = personas.Per_Id " +
		       "WHERE pro_operario.E_activo = 1 " +
		       "ORDER BY pro_operario.A_Operario_Nombre ASC ", nativeQuery = true)
	List<Object[]> buscarDataBarCodeOperarios();

	@Query("SELECT new com.almatec.controlpiso.produccion.dtos.OperarioGeneralDTO(" +
	       "o.id, p.sexo, p.tipoDoc, p.numDoc, p.nombres, p.apellidos, " +
	       "p.direccion, p.celular, p.email, o.isActivo, o.idCentroT, p.id) " +
	       "FROM Operario o JOIN o.persona p")
	List<OperarioGeneralDTO> buscarOperariosGeneral();

	@Query("SELECT new com.almatec.controlpiso.produccion.dtos.OperarioGeneralDTO(" +
           "o.id, p.sexo, p.tipoDoc, p.numDoc, p.nombres, p.apellidos, " +
           "p.direccion, p.celular, p.email, o.isActivo, o.idCentroT, p.id) " +
           "FROM Operario o JOIN o.persona p")
    Page<OperarioGeneralDTO> buscarOperariosGeneralPaginados(Pageable pageable);

	@Query("SELECT new com.almatec.controlpiso.produccion.dtos.OperarioGeneralDTO(" +
           "o.id, p.sexo, p.tipoDoc, p.numDoc, p.nombres, p.apellidos, " +
           "p.direccion, p.celular, p.email, o.isActivo, o.idCentroT, p.id) " +
           "FROM Operario o JOIN o.persona p " + 
           "WHERE o.id = :id")
	OperarioGeneralDTO buscarOperarioGeneralPorId(@Param("id")Integer id);

}
