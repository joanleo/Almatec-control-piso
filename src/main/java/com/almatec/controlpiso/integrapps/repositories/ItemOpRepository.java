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

	@Query(value =
			"DECLARE @schema NVARCHAR(100) = :schema\\; " +
			"DECLARE @sql NVARCHAR(MAX) = ' " +
			"SELECT DISTINCT i.id_op_ia, op.Num_Op, items.f120_descripcion AS Descripcion " +
			"FROM items_op i " +
			"INNER JOIN orden_pv op ON i.id_op_ia = op.id_op_ia " +
			"LEFT JOIN ' + @schema + '.t850_mf_op_docto doc " +
			"    ON op.Row850_id = doc.f850_rowid " +
			"INNER JOIN ' + @schema + '.t851_mf_op_docto_item doc_item " +
			"    ON doc.f850_rowid = doc_item.f851_rowid_op_docto " +
			"INNER JOIN ' + @schema + '.t121_mc_items_extensiones ext " +
			"    ON doc_item.f851_rowid_item_ext_padre = ext.f121_rowid " +
			"INNER JOIN ' + @schema + '.t120_mc_items items " +
			"    ON ext.f121_rowid_item = items.f120_rowid " +
			"WHERE op.Tipo_OP = ''OP'' " +
			"    AND op.Num_Op <> 0 " +
			"    AND (doc.f850_ind_estado = 1 " +
			"    OR doc.f850_ind_estado = 2) " +
			"ORDER BY op.Num_Op DESC'; " +
			"EXEC sp_executesql @sql;",
			nativeQuery = true)
			List<ConsultaOpIdInterface> obtenerNumsOps(@Param("schema") String schema);

	@Query(value = "SELECT Json "
			+ "FROM Apis_Json "
			+ "WHERE Id_op_ia = :idOPI "
			+ "AND Tipo = :tipo "
			+ "AND Estado = 0 ", nativeQuery = true)
	String obtenerJsonPorIdOPIntegrappsYTipo(@Param("idOPI")Integer idOPI, @Param("tipo")String tipo);


	List<ItemOp> findByIsActivoTrueAndIdItemFabIsNot(int i);

	@Query(value = "SELECT  valor_aplicar "
			+ "FROM   Items_rutas "
			+ "WHERE   (activo = 1)"
			+ "AND (Item_fab_Id = :idItemFab) "
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
			+ "WHERE im.activo = 1 "
			+ "AND items_op.item_id = :idItem "
			+ "AND (items_fabrica.item_tipo <> 'CONJUNTO' OR im.id_materia_prima = :idFab) ", nativeQuery = true)
	List<ItemListaMateriaInterface> obtenerListaMaterialesItemPorIdItem(@Param("idItem") Integer idItem, @Param("idFab") Integer idFab);

	@Query(value = "SELECT DISTINCT io.id_op_ia AS IdOpIa, v.Tipo_OP AS TipoOp, v.Num_Op AS NumOp,"
			+ "v.f200_razon_social AS Cliente,  RTRIM(v.f285_id) + '-' + RTRIM(v.f285_descripcion) AS Proyecto "
			+ "FROM      items_op AS io "
			+ "INNER JOIN view_orden_pv AS v ON io.id_op_ia = v.id_op_ia "
			+ "WHERE   (v.id_estado_op = 1 AND io.cant_cumplida <= io.cant_req) "
			+ "OR      (v.id_estado_op = 2 AND io.cant_cumplida <= io.cant_req) "
			+ "ORDER BY v.Num_Op DESC", nativeQuery = true)
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
			+ "AND (v.id_estado_op = 1) "
			+ "OR  (v.id_estado_op = 2) "
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

	@Query(value =
			"DECLARE @schema NVARCHAR(100) = :schema\\; " +
			"DECLARE @idOpIntegrapps INT = :idOpIntegrapps\\; " +
			"DECLARE @sql NVARCHAR(MAX) = ' " +
			"SELECT CAST(ct.f806_id AS INT) " +
			"FROM ' + @schema + '.t808_mf_rutas ruta " +
			"INNER JOIN ' + @schema + '.t809_mf_rutas_operacion ruta_op " +
			"    ON ruta.f808_rowid = ruta_op.f809_rowid_rutas " +
			"INNER JOIN ' + @schema + '.t806_mf_centros_trabajo ct " +
			"    ON ruta_op.f809_rowid_ctrabajo = ct.f806_rowid " +
			"INNER JOIN ' + @schema + '.t851_mf_op_docto_item doc_item " +
			"    ON ruta.f808_rowid = doc_item.f851_rowid_ruta " +
			"INNER JOIN orden_pv op " +
			"    ON doc_item.f851_rowid = op.Row851_id " +
			"WHERE op.id_op_ia = @idOpIntegrapps'; " +
			"EXEC sp_executesql @sql, N'@idOpIntegrapps INT', @idOpIntegrapps",
			nativeQuery = true)
			List<Integer> buscarCentrosTrabajoRutaPorIdOpIA(@Param("schema") String schema, @Param("idOpIntegrapps") Integer idOpIntegrapps);

	@Query(value = "SELECT id_op_ia "
			+ "FROM items_op "
			+ "WHERE items_op.item_id = :idItem ", nativeQuery = true)
	Integer buscarIdOpIntegrappsPorIdItem(@Param("idItem")Long idItem);

	@Query(value = "SELECT   C_centrotrabajo_id "
			+ "FROM    Items_rutas "
			+ "WHERE   (Item_fab_Id = :idItemFab) AND (activo = 1)", nativeQuery = true)
	List<Integer> buscarRutaItemPorIdItemFab(@Param("idItemFab")Integer idItemFab);


}
