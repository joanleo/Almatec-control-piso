package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;
import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperariosId;
import com.almatec.controlpiso.integrapps.interfaces.TiemposOperarioInterface;
import com.almatec.controlpiso.integrapps.interfaces.VistaKgMes;
import com.almatec.controlpiso.integrapps.interfaces.VistaKgPorCt;

/*
 * Este repositorio se usara para obtener datos de vistas y no crear entidades para cada una
 */
public interface VistaTiemposOperariosRepository extends JpaRepository<VistaTiemposOperarios, VistaTiemposOperariosId> {

	@Query(value = "SELECT   C_proconfigproceso_id, C_prooperario_id, A_Operario_Nombre, E_activo, F_turnoini, F_turnofin, A_turno, turno, "
			+ "SUM(TmpActivo) AS TmpActivo, SUM(N_ssimproductivo) AS N_ssimproductivo "
			+ "FROM view_tiempo_operarios_proceso "
			+ "GROUP BY C_proconfigproceso_id, C_prooperario_id, A_Operario_Nombre, E_activo, F_turnoini, F_turnofin, A_turno, turno "
			+ "HAVING   (C_proconfigproceso_id = :idProceso) ", nativeQuery = true)
	List<TiemposOperarioInterface> findByIdConfigProceso(@Param("idProceso") Integer idProceso);

	@Query(value = "SELECT fecha, centro_trabajo, kg_cumplidos, ml_cumplidos "
			+ "FROM view_kg_ct ", nativeQuery = true)
	List<VistaKgPorCt> findVistaKgPorCt();

	@Query(value = "SELECT  tipo_op AS TipoOp, num_op AS NumOp, pv_cliente_co_id_descripcion AS CentroOperaciones, "
			+ "desc_item_op AS ItemOp, total_kg_sol AS TotalKgSol, total_kg_mes AS TotalKgMes, year_month AS AnoMes, cliente AS Cliente "
			+ "FROM view_kg_cumplidos_mes", nativeQuery = true)
	List<VistaKgMes> findVistaKgMes();

}
