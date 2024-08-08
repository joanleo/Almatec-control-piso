package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.dtos.ItemOpCTPrioridadDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ConsultaOpIdInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemListaMateriaInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;
import com.almatec.controlpiso.integrapps.interfaces.OpConItemPendientePorRemision;


public interface ItemOpRepository extends JpaRepository<ItemOp, Long> {

	@Query(value = "SELECT items_op.item_id, items_op.id_op_ia, items_op.Item_fab_Id, "
			+ "items_op.descripcion, items_op.grupo, items_op.marca, items_op.codigo_erp, "
			+ "items_op.peso_unitario, items_op.unidad, items_op.cant_req, items_op.cant_cumplida, "
			+ "items_op.activo, items_op.ruta_plano, items_op.fecha_crea, items_op.id_estado, items_op.pintura,"
			+ "items_op.grp_pintura "
			+ "FROM     items_op "
			+ "LEFT JOIN items_fabrica "
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

	@Query(value = "SELECT DISTINCT items_op.id_op_ia,orden_pv.Num_Op, t120_mc_items.f120_descripcion AS Descripcion "
			+ "FROM  items_op "
			+ "INNER JOIN orden_pv "
			+ "ON items_op.id_op_ia = orden_pv.id_op_ia "
			+ "LEFT JOIN UnoEE_Prueba.dbo.t850_mf_op_docto "
			+ "ON orden_pv.Row850_id = UnoEE_Prueba.dbo.t850_mf_op_docto.f850_rowid "
			+ "INNER JOIN UnoEE_Prueba.dbo.t851_mf_op_docto_item "
			+ "ON UnoEE_Prueba.dbo.t850_mf_op_docto.f850_rowid = UnoEE_Prueba.dbo.t851_mf_op_docto_item.f851_rowid_op_docto "
			+ "INNER JOIN UnoEE_Prueba.dbo.t121_mc_items_extensiones ON UnoEE_Prueba.dbo.t851_mf_op_docto_item.f851_rowid_item_ext_padre = UnoEE_Prueba.dbo.t121_mc_items_extensiones.f121_rowid "
			+ "INNER JOIN UnoEE_Prueba.dbo.t120_mc_items ON t121_mc_items_extensiones.f121_rowid_item = UnoEE_Prueba.dbo.t120_mc_items.f120_rowid "
			+ "WHERE  orden_pv.Tipo_OP = 'OP' "
			+ "AND orden_pv.Num_Op <> 0 "
			+ "AND ((UnoEE_Prueba.dbo.t850_mf_op_docto.f850_ind_estado = 1) "
			+ "OR (UnoEE_Prueba.dbo.t850_mf_op_docto.f850_ind_estado = 2)) ", nativeQuery = true)
	List<ConsultaOpIdInterface> obtenerNumsOps();

	@Query(value = "SELECT Json "
			+ "FROM Apis_Json "
			+ "WHERE Id_op_ia = :idOPI "
			+ "AND Tipo = :tipo "
			+ "AND Estado = 0 ", nativeQuery = true)
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
			+ "WHERE items_op.item_id = :idItem "
			+ "AND items_fabrica.Item_fab_Id = :idFab ", nativeQuery = true)
	List<ItemListaMateriaInterface> obtenerListaMaterialesItemPorIdItem(@Param("idItem") Integer idItem, @Param("idFab") Integer idFab);

	@Query(value = "SELECT DISTINCT io.id_op_ia AS IdOpIa, v.Tipo_OP AS TipoOp, v.Num_Op AS NumOp,"
			+ "v.f200_razon_social AS Cliente,  RTRIM(v.f285_id) + '-' + RTRIM(v.f285_descripcion) AS Proyecto "
			+ "FROM      items_op AS io "
			+ "INNER JOIN view_orden_pv AS v ON io.id_op_ia = v.id_op_ia "
			+ "WHERE   (v.id_est_doc = 1 AND io.cant_cumplida <= io.cant_req) "
			+ "OR      (v.id_est_doc = 2 AND io.cant_cumplida <= io.cant_req)", nativeQuery = true)
	List<OpConItemPendientePorRemision> buscarOpActivasConItemsPendientesPorEntregar();

	@Query(value = "SELECT   io.item_id, io.id_op_ia, io.Item_fab_Id, io.grupo, io.marca, io.codigo_erp, io.descripcion, io.peso_unitario, "
			+ "io.unidad, io.cant_req, io.cant_cumplida, io.cant_existencias, io.cant_despacha, io.pintura, io.grp_pintura, io.peso_pintura, "
			+ "io.cod_pintura, io.activo, io.ruta_plano, io.id_estado, io.fecha_crea, io.especial, io.req_plano, io.cant_imp_eti, io.ct_comsumo,"
			+ "io.centros_tep "
			+ "FROM      items_op AS io "
			+ "INNER JOIN view_orden_pv AS v "
			+ "ON io.id_op_ia = v.id_op_ia "
			+ "WHERE  (io.id_op_ia = :idOpIa) "
			+ "AND ((Item_fab_Id <> 0 AND cant_cumplida > 0) OR codigo_erp <> 0)"
			+ "AND (v.id_est_doc = 1) "
			+ "OR  (v.id_est_doc = 2) "
			+ "ORDER BY io.Item_fab_Id", nativeQuery = true)
	List<ItemOp> buscarItemsARemisionarPorIdOpIa(@Param("idOpIa") Integer idOpIa);

	@Query("SELECT NEW com.almatec.controlpiso.integrapps.dtos.ItemOpCTPrioridadDTO( "
			+ "io.id, io.descripcion, io.cant, io.cantCumplida, io.peso, item.longitud, "
			+ "op.numOp, op.id, op.cliente, op.esquemaPintura, op.zona, op.centroOperaciones, "
			+ "CASE WHEN p IS NOT NULL THEN p.itemPrioridad ELSE 0 END) " +
		       "FROM ItemOp io " +
		       "JOIN VistaOrdenPv op ON io.idOpIntegrapps = op.id " +
		       "JOIN Item item ON io.idItemFab = item.idItem " +
		       "JOIN RutaItem ir ON item.idItem = ir.idItem " +
		       "JOIN CentroTrabajo ct ON ir.IdCentroTrabajo = ct.id " +
		       "LEFT JOIN Prioridad p ON p.idItem = io.id AND p.centroTrabajo = ct.id " +
		       "WHERE ct.id = :idCT ")
	List<ItemOpCTPrioridadDTO> findOpsItemsPorCentroTrabajo(@Param("idCT") Integer idCT);

	@Query(value = "SELECT  CAST(UnoEE_Prueba.dbo.t806_mf_centros_trabajo.f806_id AS INT) " 
			+ "FROM      UnoEE_Prueba.dbo.t808_mf_rutas "
			+ "INNER JOIN UnoEE_Prueba.dbo.t809_mf_rutas_operacion "
			+ "ON UnoEE_Prueba.dbo.t808_mf_rutas.f808_rowid = UnoEE_Prueba.dbo.t809_mf_rutas_operacion.f809_rowid_rutas "
			+ "INNER JOIN UnoEE_Prueba.dbo.t806_mf_centros_trabajo "
			+ "ON UnoEE_Prueba.dbo.t809_mf_rutas_operacion.f809_rowid_ctrabajo = UnoEE_Prueba.dbo.t806_mf_centros_trabajo.f806_rowid "
			+ "INNER JOIN UnoEE_Prueba.dbo.t851_mf_op_docto_item "
			+ "ON UnoEE_Prueba.dbo.t808_mf_rutas.f808_rowid = UnoEE_Prueba.dbo.t851_mf_op_docto_item.f851_rowid_ruta "
			+ "INNER JOIN orden_pv "
			+ "ON UnoEE_Prueba.dbo.t851_mf_op_docto_item.f851_rowid = orden_pv.Row851_id "
			+ "WHERE   (orden_pv.id_op_ia = :idOpIntegrapps) ", nativeQuery = true)
	List<Integer> buscarCentrosTrabajoPorIdOpIA(@Param("idOpIntegrapps")Integer idOpIntegrapps);


}
