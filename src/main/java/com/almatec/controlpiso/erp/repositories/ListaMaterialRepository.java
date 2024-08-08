package com.almatec.controlpiso.erp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.erp.entities.ListaMaterial;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.interfaces.DataCostoStandarInterface;
import com.almatec.controlpiso.erp.interfaces.DataTEP;
import com.almatec.controlpiso.erp.interfaces.DetalleTransferenciaInterface;
import com.almatec.controlpiso.erp.interfaces.RutaInterface;
import com.almatec.controlpiso.erp.interfaces.TarifaCostosSegmentoItem;
import com.almatec.controlpiso.erp.webservices.interfaces.ConsultaItemOpCreado;

public interface ListaMaterialRepository extends JpaRepository<ListaMaterial, Integer> {

	@Query(value = "SELECT f820_rowid, f820_id_metodo, f820_secuencia, t120_mc_items_1.f120_id AS id_hijo "
			+ "FROM t820_mf_lista_material "
			+ "INNER JOIN t121_mc_items_extensiones "
			+ "ON t820_mf_lista_material.f820_rowid_item_ext = t121_mc_items_extensiones.f121_rowid "
			+ "INNER JOIN t120_mc_items "
			+ "ON t121_mc_items_extensiones.f121_rowid_item = t120_mc_items.f120_rowid "
			+ "INNER JOIN t121_mc_items_extensiones AS t121_mc_items_extensiones_1 "
			+ "ON t820_mf_lista_material.f820_rowid_item_ext_hijo = t121_mc_items_extensiones_1.f121_rowid "
			+ "INNER JOIN t120_mc_items AS t120_mc_items_1 "
			+ "ON t121_mc_items_extensiones_1.f121_rowid_item = t120_mc_items_1.f120_rowid "
			+ "WHERE   (t120_mc_items.f120_id = :f820_id)", nativeQuery = true)
	List<ListaMaterial> obtenerListaActual(@Param("f820_id") Integer f820_id);

	@Query(value = "SELECT   t808_mf_rutas.f808_id, t808_mf_rutas.f808_id_instalacion, t809_mf_rutas_operacion.f809_id_metodo, "
			+ "t809_mf_rutas_operacion.f809_numero_operacion "
			+ "FROM t809_mf_rutas_operacion "
			+ "INNER JOIN t808_mf_rutas "
			+ "ON t809_mf_rutas_operacion.f809_rowid_rutas = t808_mf_rutas.f808_rowid "
			+ "WHERE   (t808_mf_rutas.f808_id = :f808_id) AND (t809_mf_rutas_operacion.f809_id_cia = 22)", nativeQuery = true)
	List<RutaInterface> obtenerRutas(String f808_id);

	@Query(value = "SELECT TOP(1) f120_id "
			+ "FROM t120_mc_items "
			+ "ORDER BY f120_id DESC", nativeQuery = true)
	Integer obtenerUltimoIdRefItem();

	@Query(value = "SELECT t850_mf_op_docto.f850_rowid, t850_mf_op_docto.f850_id_tipo_docto, t850_mf_op_docto.f850_consec_docto, "
			+ "t851_mf_op_docto_item.f851_rowid, t120_mc_items.f120_id "
			+ "FROM      t850_mf_op_docto "
			+ "INNER JOIN t851_mf_op_docto_item "
			+ "ON t850_mf_op_docto.f850_rowid = t851_mf_op_docto_item.f851_rowid_op_docto "
			+ "INNER JOIN t121_mc_items_extensiones "
			+ "ON t851_mf_op_docto_item.f851_rowid_item_ext_padre = t121_mc_items_extensiones.f121_rowid "
			+ "INNER JOIN t120_mc_items "
			+ "ON t121_mc_items_extensiones.f121_rowid_item = t120_mc_items.f120_rowid "
			+ "WHERE   (t850_mf_op_docto.f850_id_cia = 22) AND (t120_mc_items.f120_id = :idItem) ", nativeQuery = true)
	ConsultaItemOpCreado obtenerRowIdOpItemOp(@Param("idItem") Integer idItem);

	@Query(value = "SELECT TOP(1) t350_co_docto_contable.f350_id_tipo_docto, t350_co_docto_contable.f350_consec_docto, t350_co_docto_contable.f350_usuario_creacion,"
			+ " t350_co_docto_contable.f350_fecha_ts_creacion "
			+ "FROM  t450_cm_docto_invent "
			+ "INNER JOIN t350_co_docto_contable ON t450_cm_docto_invent.f450_rowid_docto = t350_co_docto_contable.f350_rowid "
			+ "WHERE   (t450_cm_docto_invent.f450_id_cia = 22)"
			+ "AND f350_notas = :idSolIntegrapps ", nativeQuery = true)
	DetalleTransferenciaInterface obtenerDetalleTransferencia(@Param("idSolIntegrapps") String idSolIntegrapps);

	@Query(value= "SELECT   t850_mf_op_docto.f850_id_tipo_docto, "
			+ "t850_mf_op_docto.f850_consec_docto, "
			+ "t850_mf_op_docto.f850_rowid, "
			+ "t851_mf_op_docto_item.f851_rowid, "
			+ "t120_mc_items.f120_id,"
			+ "f120_id_unidad_inventario "
			+ "FROM t850_mf_op_docto "
			+ "INNER JOIN  t851_mf_op_docto_item ON t850_mf_op_docto.f850_rowid = t851_mf_op_docto_item.f851_rowid_op_docto "
			+ "INNER JOIN  t121_mc_items_extensiones ON t851_mf_op_docto_item.f851_rowid_item_ext_padre = t121_mc_items_extensiones.f121_rowid "
			+ "INNER JOIN  t120_mc_items ON t121_mc_items_extensiones.f121_rowid_item = t120_mc_items.f120_rowid "
			+ "INNER JOIN  Integrapps.dbo.orden_pv ON t850_mf_op_docto.f850_rowid = Integrapps.dbo.orden_pv.Row850_id "
			+ "WHERE (Integrapps.dbo.orden_pv.id_op_ia = :idOp )", nativeQuery = true)
	DataConsumoInterface obtenerDataParaConsumo(@Param("idOp") Integer idOp);

	@Query(value = "SELECT TOP(1) t350_co_docto_contable.f350_notas "
			+ "FROM      t350_co_docto_contable "
			+ "INNER JOIN t450_cm_docto_invent ON t350_co_docto_contable.f350_rowid = t450_cm_docto_invent.f450_rowid_docto "			
			+ "WHERE  (t350_co_docto_contable.f350_notas = :idOp) "
			+ "ORDER BY t450_cm_docto_invent.f450_rowid_docto DESC ", nativeQuery = true)
	String obtenerConsecutivoNotasTransferencia(@Param("idOp") String idOp);
	
	@Query(value = "SELECT TOP(1) t808_mf_rutas.f808_id, t808_mf_rutas.f808_id_instalacion, t808_mf_rutas.f808_descripcion, t808_mf_rutas.f808_ind_estado, "
			+ "t809_mf_rutas_operacion.f809_numero_operacion, t809_mf_rutas_operacion.f809_descripcion, t809_mf_rutas_operacion.f809_cantidad_base, "
			+ "t809_mf_rutas_operacion.f809_horas_maquina, t807_mf_maquinas.f807_id, t806_mf_centros_trabajo.f806_id "
			+ "FROM      t808_mf_rutas "
			+ "INNER JOIN t809_mf_rutas_operacion ON t808_mf_rutas.f808_rowid = t809_mf_rutas_operacion.f809_rowid_rutas "
			+ "INNER JOIN t806_mf_centros_trabajo ON t809_mf_rutas_operacion.f809_rowid_ctrabajo = t806_mf_centros_trabajo.f806_rowid "
			+ "INNER JOIN t807_mf_maquinas ON t806_mf_centros_trabajo.f806_rowid = t807_mf_maquinas.f807_rowid_ctrabajo "
			+ "WHERE (t808_mf_rutas.f808_id_cia = 22) "
			+ "AND (t808_mf_rutas.f808_id = :idRuta ) "
			+ "AND (t806_mf_centros_trabajo.f806_id = :idCentroTrabajo)", nativeQuery = true)
	DataTEP obtenerDataTEP(@Param("idRuta") String idRuta, @Param("idCentroTrabajo") String idCentroTrabajo);

	@Query(value = "SELECT   TOP (1) t120_mc_items.f120_id "
			+ "FROM      t850_mf_op_docto "
			+ "INNER JOIN t851_mf_op_docto_item "
			+ "ON t850_mf_op_docto.f850_rowid = t851_mf_op_docto_item.f851_rowid_op_docto "
			+ "INNER JOIN t121_mc_items_extensiones "
			+ "ON t851_mf_op_docto_item.f851_rowid_item_ext_padre = t121_mc_items_extensiones.f121_rowid "
			+ "INNER JOIN t120_mc_items ON t121_mc_items_extensiones.f121_rowid_item = t120_mc_items.f120_rowid "
			+ "WHERE   (t850_mf_op_docto.f850_consec_docto = :numOp)", nativeQuery = true)
	Integer obtenerItemOp(@Param("numOp") Integer numOp);

	@Query(value = "SELECT t.IdItem, SUM(t.CostoStandar) AS TotalCostoStandar, SUM(t.costo_previo) AS TotalCostoPrevio "
			+ "FROM ("
			+ "    SELECT 	t402_cm_costos.f402_id_grupo_costo AS IdGrupoCosto, "
			+ "				t120_mc_items.f120_id AS IdItem, "
			+ "        		t402_cm_costos.f402_costo_este_nivel_uni AS CostoStandar, "
			+ "				t402_cm_costos.f402_costo_nivel_previo_uni  AS costo_previo "
			+ "    FROM "
			+ "        t402_cm_costos "
			+ "    INNER JOIN t121_mc_items_extensiones "
			+ "	   ON t402_cm_costos.f402_rowid_item_ext = t121_mc_items_extensiones.f121_rowid "
			+ "    INNER JOIN t120_mc_items "
			+ "	   ON t121_mc_items_extensiones.f121_rowid_item = t120_mc_items.f120_rowid "
			+ "    WHERE t120_mc_items.f120_id = :idItem "
			+ "    AND t402_cm_costos.f402_id_cia = 22 "
			+ "    AND t402_cm_costos.f402_id_grupo_costo = 2 "
			+ "    GROUP BY 	t402_cm_costos.f402_id_grupo_costo, "
			+ "					t402_cm_costos.f402_rowid_item_ext, "
			+ "					t120_mc_items.f120_id, "
			+ "	   				t402_cm_costos.f402_costo_nivel_previo_uni, "
			+ "					t402_cm_costos.f402_costo_este_nivel_uni ) t "
			+ "	   GROUP BY t.IdItem ", nativeQuery = true)
	DataCostoStandarInterface obtenerCostoStandar(@Param("idItem")Integer idItem);

	@Query(value = "SELECT t808_mf_rutas.f808_id_cia AS Cia, t808_mf_rutas.f808_id AS Ref, t804_mf_segmentos_costos.f804_id AS Segmento, SUM(t809_mf_rutas_operacion.f809_horas_maquina * t817_mf_tarifas.f817_tarifa) AS CostoTarifa\r\n"
			+ "FROM      t808_mf_rutas "
			+ "INNER JOIN t809_mf_rutas_operacion "
			+ "		ON t808_mf_rutas.f808_rowid = t809_mf_rutas_operacion.f809_rowid_rutas "
			+ "INNER JOIN t806_mf_centros_trabajo "
			+ "		ON t809_mf_rutas_operacion.f809_rowid_ctrabajo = t806_mf_centros_trabajo.f806_rowid "
			+ "INNER JOIN t817_mf_tarifas "
			+ "		ON t806_mf_centros_trabajo.f806_rowid = t817_mf_tarifas.f817_rowid_ctrabajo "
			+ "INNER JOIN t804_mf_segmentos_costos "
			+ "		ON t817_mf_tarifas.f817_id_cia = t804_mf_segmentos_costos.f804_id_cia AND t817_mf_tarifas.f817_id_segmento_costo = t804_mf_segmentos_costos.f804_id "
			+ "WHERE   (t808_mf_rutas.f808_id_cia = 22) "
			+ "AND (t808_mf_rutas.f808_id = :ref) "
			+ "AND (t817_mf_tarifas.f817_ind_tipo_tarifa = 2) "
			+ "GROUP BY t808_mf_rutas.f808_id_cia, "
			+ "			t808_mf_rutas.f808_id, "
			+ "			t804_mf_segmentos_costos.f804_id", nativeQuery = true)
	List<TarifaCostosSegmentoItem> encontrarCostosSegmentosItemPorRef(@Param("ref")String ref);


}
/*
 * 
 * @Query(value = "SELECT   t402_cm_costos.f402_id_grupo_costo AS IdGrupoCosto, t120_mc_items.f120_id AS IdItem, "
		+ "SUM(t402_cm_costos.f402_costo_este_nivel_uni) AS CostoStandar "
		+ "FROM      t402_cm_costos "
		+ "INNER JOIN t121_mc_items_extensiones "
		+ "ON t402_cm_costos.f402_rowid_item_ext = t121_mc_items_extensiones.f121_rowid "
		+ "INNER JOIN t120_mc_items ON t121_mc_items_extensiones.f121_rowid_item = t120_mc_items.f120_rowid "
		+ "WHERE   (t120_mc_items.f120_id = :idItem) AND (t402_cm_costos.f402_id_cia = 22) "
		+ "AND (t402_cm_costos.f402_id_grupo_costo = 2) "   obtenerCostoStandar*/ 
