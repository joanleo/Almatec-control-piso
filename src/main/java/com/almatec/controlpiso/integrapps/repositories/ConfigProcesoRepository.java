package com.almatec.controlpiso.integrapps.repositories;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.ConfigProceso;

public interface ConfigProcesoRepository extends JpaRepository<ConfigProceso, Integer> {

	@Query(value = "SELECT * "
			+ "FROM pro_configproceso "
			+ "WHERE C_centrotrabajo_id = :idCentroTrabajo "
			+ "AND C_proturnos_id = :idTurno "
			+ "AND CONVERT(date, FC_registro) = :fecha", nativeQuery = true)
	ConfigProceso obtenerConfiguracionCentroTrabajoTurno(@Param("idCentroTrabajo") Integer idCentroTrabajo, 
			                                             @Param("idTurno") Long idTurno, 
			                                             @Param("fecha") LocalDate fecha);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO pro_configproceso (C_ciaorg_id, C_centrotrabajo_id, C_proturnos_id, A_usuariocrea, A_usuarioedita) "
				+ "VALUES ('22', :idCentroTrabajo, :idTurno, 'integrapps', 'integrapps')", nativeQuery = true)
	void guardarConfig(@Param("idCentroTrabajo") Integer idCentroTrabajo, 
					   @Param("idTurno") Long idTurno);

}