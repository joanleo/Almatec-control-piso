package com.almatec.controlpiso.integrapps.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.entities.RegistroOperDia;

public interface RegistroOperDiaRepository extends JpaRepository<RegistroOperDia, Integer> {

	@Query(value = "SELECT * "
			+ "FROM pro_regoperxdia "
			+ "WHERE (C_prooperario_id =:#{#operarioDTO.idOperario}) "
			+ "AND CONVERT(date, FC_registro) =:fechaActual "
			+ "AND C_centrotrabajo_id = :#{#operarioDTO.idCentroTrabajo} ", nativeQuery = true)
	RegistroOperDia consultaOperarioCentroTrabajoEnviado(@Param("operarioDTO")OperarioDTO operarioDTO, @Param("fechaActual") LocalDate fechaActual);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO pro_regoperxdia (C_proconfigproceso_id, C_centrotrabajo_id, C_prooperario_id, A_usuariocrea, A_usuarioedita, FE_registro, E_activo) "
			+ "VALUES (:#{#operarioDTO.idConfigProceso}, :#{#operarioDTO.idCentroTrabajo}, :#{#operarioDTO.idOperario}, 'rfl', 'rfl', :fecha, 1) ", nativeQuery = true)
	void agregarOperario(@Param("operarioDTO") OperarioDTO operarioDTO, @Param("fecha") Date fecha);

	@Transactional
	@Modifying
	@Query(value = "UPDATE pro_regoperxdia "
			+ "SET E_activo = :estado "
			+ "WHERE (C_centrotrabajo_id =:#{#operarioDTO.idCentroTrabajo}) "
			+ "AND (C_proconfigproceso_id =:#{#operarioDTO.idConfigProceso}) "
			+ "AND (C_prooperario_id =:#{#operarioDTO.idOperario}) ", nativeQuery = true)
	void actualizaEstadoOperario(@Param("operarioDTO")OperarioDTO operarioDTO, @Param("estado") Boolean estado);

	List<RegistroOperDia> findByIdCentroTAndIdConfigProcesoAndIsActivoTrue(Integer idCT, Integer idConfigP);

	RegistroOperDia findByIdCentroTAndIdConfigProceso(Integer idCentroTrabajo, Integer idConfigProceso);

	@Query(value = "SELECT * "
			+ "FROM pro_regoperxdia "
			+ "WHERE (C_prooperario_id =:#{#operarioDTO.idOperario}) "
			+ "AND CONVERT(date, FC_registro) = CONVERT(date,:fechaActual) "
			+ "AND C_centrotrabajo_id <> :#{#operarioDTO.idCentroTrabajo} "
			+ "AND (E_activo = 1) ", nativeQuery = true)
	List<RegistroOperDia> findOperariosCT(@Param("operarioDTO") OperarioDTO operarioDTO, @Param("fechaActual") Date fechaActual);

	@Query(value = "SELECT * "
			+ "FROM pro_regoperxdia "
			+ "WHERE (C_prooperario_id = :#{#operarioDTO.idOperario}) "
			+ "AND CONVERT(date, FC_registro) = CONVERT(date, :fechaActual) "
			+ "AND C_centrotrabajo_id = :#{#operarioDTO.idCentroTrabajo} ", nativeQuery = true)
	RegistroOperDia findByIdCentroTAndFecha(@Param("operarioDTO") OperarioDTO operarioDTO, @Param("fechaActual") Date fechaActual);

}
