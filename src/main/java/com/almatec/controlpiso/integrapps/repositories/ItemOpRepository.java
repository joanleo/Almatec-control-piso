package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;


public interface ItemOpRepository extends JpaRepository<ItemOp, Long> {

	@Query(value = "SELECT items_op.item_id, items_op.id_op_ia, items_op.Item_fab_Id, "
			+ "items_op.descripcion, items_op.grupo, items_op.marca, items_op.codigo_erp, "
			+ "items_op.peso_unitario, items_op.unidad, items_op.cant_req, "
			+ "items_op.activo, items_op.ruta_plano, items_op.fecha_crea, items_op.id_estado,"
			+ "orden_pv.Color_Bas, orden_pv.Color_Vigas, orden_pv.Color_Pro "
			+ "FROM     items_op "
			+ "LEFT JOIN orden_pv "
			+ "ON items_op.id_op_ia = orden_pv.id_op_ia "
			+ "WHERE  (orden_pv.Num_Op = :idOp) ", nativeQuery = true)
	List<ItemOpInterface> obtenerItemsOp(@Param("idOp") Integer idOp);

	@Query(value = "SELECT * "
			+ "FROM items_op "
			+ "WHERE items_op.id_grp_item = :idGrupo",nativeQuery = true)
	List<ItemOp> obtenerItemsOpC2(@Param("idGrupo") String idGrupo);

	List<ItemOp> findByIdPvIntegrapps(Integer numOp);

}
