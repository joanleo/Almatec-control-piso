package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ConsultaOpIdInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemListaMateriaInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;


public interface ItemOpRepository extends JpaRepository<ItemOp, Long> {

	@Query(value = "SELECT items_op.item_id, items_op.id_op_ia, items_op.Item_fab_Id, "
			+ "items_op.descripcion, items_op.grupo, items_op.marca, items_op.codigo_erp, "
			+ "items_op.peso_unitario, items_op.unidad, items_op.cant_req, items_op.cant_cumplida, "
			+ "items_op.activo, items_op.ruta_plano, items_op.fecha_crea, items_op.id_estado, items_op.pintura,"
			+ "items_op.grp_pintura "
			+ "FROM     items_op "
			+ "JOIN items_fabrica "
			+ "ON items_op.Item_fab_Id = items_fabrica.Item_fab_Id "
			+ "JOIN orden_pv "
			+ "ON items_op.id_op_ia = orden_pv.id_op_ia "
			+ "WHERE  (orden_pv.Num_Op = :idOp) ", nativeQuery = true)
	List<ItemOpInterface> obtenerItemsOp(@Param("idOp") Integer idOp);

	@Query(value = "SELECT * "
			+ "FROM items_op "
			+ "WHERE items_op.id_grp_item = :idGrupo",nativeQuery = true)
	List<ItemOp> obtenerItemsOpC2(@Param("idGrupo") String idGrupo);

	List<ItemOp> findByIdOpIntegrapps(Integer numOp);

	@Query(value = "SELECT Json "
			+ "FROM Apis_Json "
			+ "WHERE Id_Integrador = :id "
			+ "AND Estado = 0", nativeQuery = true)
	String obtenerJsonPorId(@Param("id")Integer id);

	@Query(value = "SELECT DISTINCT items_op.id_op_ia,orden_pv.Num_Op "
			+ "FROM      items_op "
			+ "INNER JOIN orden_pv ON items_op.id_op_ia = orden_pv.id_op_ia "
			+ "AND orden_pv.Num_Op <> 0 ", nativeQuery = true)
	List<ConsultaOpIdInterface> obtenerNumsOps();

	@Query(value = "SELECT Json "
			+ "FROM Apis_Json "
			+ "WHERE Id_op_ia = :idOPI "
			+ "AND Tipo = :tipo "
			+ "AND Estado = 0", nativeQuery = true)
	String obtenerJsonPorIdOPIntegrappsYTipo(@Param("idOPI")Integer idOPI, @Param("tipo")String tipo);


	List<ItemOp> findByIsActivoTrueAndIdItemFabIsNot(int i);

	@Query(value = "SELECT  valor_aplicar "
			+ "FROM   Items_rutas "
			+ "WHERE   (Item_fab_Id = :idItemFab) "
			+ "AND (C_centrotrabajo_id = :idCentroTrabajo)", nativeQuery = true)
	Double obtenerValorAplicarTepItemCentroTrabajo(@Param("idItemFab") Integer idItemFab, @Param("idCentroTrabajo") Integer idCentroTrabajo);

	@Query(value = "SELECT   Item_fab_Id, item_tipo, codigo_erp, item_desc, item_grp_flia, "
			+ "item_grp_pin, item_peso_b, item_peso_n, item_plano, imp_etiqueta, item_estado, item_ue, item_long "
			+ "FROM      items_fabrica "
			+ "WHERE   (Item_fab_Id = :idItem)", nativeQuery = true)
	ItemInterface obtenerItemFabricaPorId(@Param("idItem") Integer idItem);

	@Query(value = "SELECT   items_fabrica.item_tipo AS item_op_tipo, items_op.item_id AS item_op_id, im.id_item AS id_item_fab, "
			+ "CASE WHEN items_fabrica.item_tipo = 'CONJUNTO' THEN im.id_materia_prima END AS id_parte, "
			+ "CASE WHEN items_fabrica.item_tipo = 'CONJUNTO' THEN im.cantidad END AS cant_parte, "
			+ "CASE WHEN items_fabrica.item_tipo = 'CONJUNTO' THEN im_1.id_materia_prima "
			+ "     WHEN items_fabrica.item_tipo <> 'CONJUNTO' THEN im.id_materia_prima END AS id_materia_prima, "
			+ "CASE WHEN items_fabrica.item_tipo = 'CONJUNTO' THEN im_1.cantidad "
			+ "     WHEN items_fabrica.item_tipo <> 'CONJUNTO' THEN im.cantidad END AS can_materia_prima "
			+ "FROM      z_item_materia_prima AS im "
			+ "INNER JOIN items_fabrica ON im.id_item = items_fabrica.Item_fab_Id "
			+ "LEFT OUTER JOIN items_op ON items_fabrica.Item_fab_Id = items_op.Item_fab_Id "
			+ "LEFT OUTER JOIN z_item_materia_prima AS im_1 ON im.id_materia_prima = im_1.id_item "
			+ "WHERE items_fabrica.Item_fab_Id = :idItem ", nativeQuery = true)
	List<ItemListaMateriaInterface> obtenerListaMaterialesItemPorIdItem(@Param("idItem") Integer idItem);

}
