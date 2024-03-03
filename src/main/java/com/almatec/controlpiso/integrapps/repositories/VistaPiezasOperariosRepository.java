package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasOperarios;

public interface VistaPiezasOperariosRepository extends JpaRepository<VistaPiezasOperarios, String> {

	@Query(value = "SELECT * "
			+ "FROM view_piezas_operarios_proceso "
			+ "WHERE C_proconfigproceso_id = :#{#operario.idConfigProceso} "
			+ "AND ct = :#{#operario.idCentroTrabajo} "
			+ "AND C_prooperario_id = :#{#operario.idOperario} ", nativeQuery = true)
	List<VistaPiezasOperarios> findPiezasOperariosProceso(OperarioDTO operario);

}
