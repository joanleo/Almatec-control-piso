package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.dtos.ItemOpCTPrioridadDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;

public interface VistaItemsOpsProgramacionRepository extends JpaRepository<VistaItemsOpsProgramacion, String> {

	@Query(value = "SELECT * "
			+ "FROM view_items_op_programacion "
			+ "WHERE Item_fab_Id <> 0 ", nativeQuery = true)
	List<VistaItemsOpsProgramacion> findAllItemsToMake();

	@Query(value = "SELECT * "
			+ "FROM view_items_op_programacion "
			+ "WHERE Item_fab_Id <> 0 ", nativeQuery = true)
	Page<VistaItemsOpsProgramacion> buscarTodosItemsActivosPrioridad(Pageable pageable);

	Page<VistaItemsOpsProgramacion> findAll(Specification<VistaItemsOpsProgramacion> itemsPrioridades,
			Pageable pageable); 

	/*@Query("SELECT NEW com.almatec.controlpiso.integrapps.dtos.ItemOpCTPrioridadDTO( "
			+ "io.id, io.descripcion, io.cantReq, io.cantCumplida, io.peso, if.longitud, "
			+ "op.numOp, op.id, op.cliente, op.esquemaPintura, op.zona, op.centroOperaciones, "
			+ "CASE WHEN p IS NOT NULL THEN p.prioridad ELSE 0 END) " +
		       "FROM ItemOp io " +
		       "JOIN VistaOrdenPv op ON io.idOpIntegrapps = op.id " +
		       "JOIN Item if ON io.idItemFab = if.idItem " +
		       "JOIN RutaItem ir ON if.idItem = ir.idItem " +
		       "JOIN CentroTrabajo ct ON ir.IdCentroTrabajo = ct.id " +
		       "LEFT JOIN Prioridad p ON p.idItem = io.id AND p.idItemFab = if.id AND p.idCentroTrabajo = ct.id " +
		       "WHERE ct.id = :idCT ")
	List<ItemOpCTPrioridadDTO> findOpsItemsPorCentroTrabajo(@Param("idCT") Integer idCT);
*/
}
