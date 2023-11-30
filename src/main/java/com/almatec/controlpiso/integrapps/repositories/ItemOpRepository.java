package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;


public interface ItemOpRepository extends JpaRepository<ItemOp, Long> {

	@Query(value = "SELECT items_op.item_id, items_op.id_op_ia, items_op.id_flia, "
			+ "items_op.id_grp_item, items_op.descripcion, items_op.Medida_1, "
			+ "items_op.Medida_2, items_op.Medida_3, items_op.peso_unitario, "
			+ "items_op.unidad, items_op.cant_req, items_op.especial, items_op.req_plano, "
			+ "items_op.activo, items_op.ruta_plano, items_op.agrupa, items_op.id_estado "
			+ "FROM     items_op "
			+ "INNER JOIN orden_pv "
			+ "ON items_op.id_op_ia = orden_pv.id_op_ia "
			+ "WHERE  (orden_pv.Num_Op = :idOp) "
			+ "AND items_op.agrupa = 'C1' "
			+ "AND items_op.agrupa IS NOT NULL "
			+ "AND (items_op.agrupa <> '')", nativeQuery = true)
	List<ItemOpInterface> obtenerItemsOpC1(@Param("idOp") String idOp);

	@Query(value = "SELECT * "
			+ "FROM items_op "
			+ "WHERE items_op.id_grp_item = :idGrupo",nativeQuery = true)
	List<ItemOp> obtenerItemsOpC2(@Param("idGrupo") String idGrupo);

}
