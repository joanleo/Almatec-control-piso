package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.interfaces.CentroOperacionInterface;
import com.almatec.controlpiso.integrapps.interfaces.CompaniaErp;
import com.almatec.controlpiso.integrapps.interfaces.DatosOpItem;

public interface CentroTrabajoRepository extends JpaRepository<CentroTrabajo, Integer> {

	@Query(value = "SELECT  f010_id, f010_razon_social "
			+ "FROM  Companias_ERP", nativeQuery = true)
	List<CompaniaErp> buscarCompanias();

	@Query(value = "SELECT * "
			+ "FROM pro_centrostrabajo "
			+ "WHERE E_activo = 1", nativeQuery = true)
	List<CentroTrabajo> buscarCentrosTrabajo();

	@Query(value = "SELECT f285_id, f285_id_cia, f285_descripcion "
			+ "FROM co_erp "
			+ "WHERE f285_id_cia = :cia ", nativeQuery = true)
	List<CentroOperacionInterface> buscarCentrosOperacion(@Param("cia") Integer cia);

	List<CentroTrabajo> findByIdCiaAndIsShowTrue(Integer cia);

	@Query(value = "SELECT orden_pv.Row850_id, orden_pv.Row851_id "
			+ "FROM orden_pv "
			+ "INNER JOIN items_op ON orden_pv.id_op_ia = items_op.id_op_ia ", nativeQuery = true)
	DatosOpItem obtenerDatosOpItem(Long idItem);

	CentroTrabajo findByIdCentroTrabajoErp(Integer idCT);

	
}
