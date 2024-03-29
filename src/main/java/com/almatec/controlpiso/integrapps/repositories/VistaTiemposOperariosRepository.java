package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;

public interface VistaTiemposOperariosRepository extends JpaRepository<VistaTiemposOperarios, Integer> {

	@Query(value = "SELECT * "
			+ "FROM view_tiempo_operarios_proceso "
			+ "WHERE C_proconfigproceso_id = :idProceso ", nativeQuery = true)
	List<VistaTiemposOperarios> findByIdConfigProceso(@Param("idProceso") Integer idProceso);

}
