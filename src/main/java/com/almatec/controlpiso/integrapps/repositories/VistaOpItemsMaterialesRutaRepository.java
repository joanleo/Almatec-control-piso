package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.integrapps.entities.VistaOpItemsMaterialesRuta;

public interface VistaOpItemsMaterialesRutaRepository extends JpaRepository<VistaOpItemsMaterialesRuta, String>, JpaSpecificationExecutor<VistaOpItemsMaterialesRuta> {

	@Query(value = "SELECT * "
			+ "FROM view_op_items_ruta "
			+ "WHERE ((item_centro_t_id = :idCT) AND (estado_op = 1)) "
			+ "OR ((item_centro_t_id = :idCT) AND (estado_op = 2)) "
			+ "OR ((material_centro_t_id = :idCT2) AND (estado_op = 1)) "
			+ "OR ((material_centro_t_id = :idCT2) AND (estado_op = 2)) "
			+ "ORDER BY id_op_ia ASC", nativeQuery = true)
	List<VistaOpItemsMaterialesRuta> findByItemCentroTIdOrMaterialCentroTId(Integer idCT, Integer idCT2);

	@Query(value = "SELECT * "
			+ "FROM view_op_items_ruta "
			+ "WHERE ((item_op_id = :idItem) AND (item_centro_t_id = :idCT) AND (estado_op = 1)) "
			+ "OR ((item_op_id = :idItem) AND (item_centro_t_id = :idCT) AND (estado_op = 2)) "
			+ "OR ((item_op_id = :idItem) AND (material_centro_t_id = :idCT) AND (estado_op = 1)) "
			+ "OR ((item_op_id = :idItem) AND (material_centro_t_id = :idCT) AND (estado_op = 2)) ", nativeQuery = true)
	List<VistaOpItemsMaterialesRuta> buscarItemCt(Long idItem, Integer idCT);

	Page<VistaOpItemsMaterialesRuta> findAll(Specification<VistaOpItemsMaterialesRuta> itemsPrioridades,
			Pageable pageable);

	List<VistaOpItemsMaterialesRuta> findAll(Specification<VistaOpItemsMaterialesRuta> spec);

	@Query(value = "SELECT * FROM view_op_items_ruta "
		    + "WHERE "
		    + "  (:tipo = 'parte' AND ( "
		    + "      ((item_op_id = :idItemOp) AND (item_centro_t_id = :idCT) AND (estado_op = 1) AND (material_id = :idItem)) "
		    + "      OR ((item_op_id = :idItemOp) AND (item_centro_t_id = :idCT) AND (estado_op = 2) AND (material_id = :idItem)) "
		    + "      OR ((item_op_id = :idItemOp) AND (material_centro_t_id = :idCT) AND (estado_op = 1) AND (material_id = :idItem)) "
		    + "      OR ((item_op_id = :idItemOp) AND (material_centro_t_id = :idCT) AND (estado_op = 2) AND (material_id = :idItem)) "
		    + "  )) "
		    + "  OR "
		    + "  (:tipo <> 'parte'  AND( "
		    + "     ((item_op_id = :idItemOp) AND (item_centro_t_id = :idCT) AND (estado_op = 1)) "
		    + "     OR ((item_op_id = :idItemOp) AND (item_centro_t_id = :idCT) AND (estado_op = 2))  "
		    + "     OR ((item_op_id = :idItemOp) AND (material_centro_t_id = :idCT) AND (estado_op = 1)) "
		    + "     OR ((item_op_id = :idItemOp) AND (material_centro_t_id = :idCT) AND (estado_op = 2)) "
		    + "  ))", nativeQuery = true)
		List<VistaOpItemsMaterialesRuta> buscarItemParteCt(Long idItemOp, Integer idItem, Integer idCT, String tipo);

}
