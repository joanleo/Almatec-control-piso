package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.dtos.ConsultaOpCreadaDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.interfaces.OrdenPvEstadoData;

public interface OrdenPvRepository extends JpaRepository<VistaOrdenPv, Integer> {

	@Query(value = "SELECT * "
			+"FROM view_orden_pv "
			+"WHERE CONCAT(Und_Neg, f200_razon_social, f285_descripcion, Zona_Desc, estado_op, Esq_Pintura) "
			+"LIKE %:keyword% "
			+"AND Tipo_OP='OP' "
			+"AND id_est_doc=1 "
			+"ORDER BY id_op_ia DESC", nativeQuery = true)
	List<VistaOrdenPv> buscarPorKeyword(String keyword);

	@Query(value = "SELECT f285_descripcion,f200_razon_social "
			+"FROM view_orden_pv "
			+"GROUP BY f285_descripcion,f200_razon_social ", nativeQuery = true)
	List<Object[]> findProyects();

	@Query(value = "SELECT f285_descripcion,f200_razon_social, Und_Neg "
			+"FROM view_orden_pv "
			+"WHERE CONCAT(f285_descripcion,f200_razon_social, Und_Neg) "
			+"LIKE %:keyword% "
			+"GROUP BY f285_descripcion,f200_razon_social, Und_Neg ", nativeQuery = true)
	List<Object[]> findProyects(@Param("keyword") String keyword);

	List<VistaOrdenPv> findByIdProyecto(String idProyecto);

	@Transactional
	@Modifying
	@Query(value = "UPDATE  orden_pv "
			+ "SET Row850_id=:#{#creado.f850_rowid} , Row851_id=:#{#creado.f851_rowid} , Num_Op=:#{#creado.f850_consec_docto} , "
			+ "op_UnoEE=:opUnoEE "
			+ "WHERE id_op_ia = :#{#ordenIntegrapps.id}", nativeQuery = true)
	void actualizarOp(@Param("creado") ConsultaOpCreadaDTO creado, @Param("opUnoEE") String opUnoEE, @Param("ordenIntegrapps")VistaOrdenPv ordenIntegrapps);

	@Query(value = "SELECT Num_Op "
			+ "FROM view_orden_pv "
			+ "WHERE id_op_ia = :idOpIntegrapps ", nativeQuery = true)
	Integer obtenerNumOpPorIdOp(@Param("idOpIntegrapps") Integer idOpIntegrapps);

	@Query(value = "SELECT  pv_cliente_razon_social AS Cliente, pv_cliente_co_id AS IdCo, pv_cliente_co_descripcion AS CoDescripcion "
			+ "FROM      view_pedidos_estado_erp "
			+ "WHERE   (pv_num = :noPedido) "
			+ "AND pv_tipo = 'PV'", nativeQuery = true)
	OrdenPvEstadoData findByNumOp(@Param("noPedido") Integer noPedido);

	List<VistaOrdenPv> findByTipoOpAndIdEstadoDoc(String string, int i);

	List<VistaOrdenPv> findByTipoOpAndIdEstadoDocOrderByNumOpDesc(String string, int i);

	Page<VistaOrdenPv> findByTipoOpAndIdEstadoDocOrderByNumOpDesc(String tipoOp, int idEstadoDoc, Pageable pageable);

	@Query("SELECT o FROM VistaOrdenPv o WHERE " +
	           "(LOWER(o.cliente) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(o.centroOperaciones) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(o.estadoOp) LIKE LOWER(CONCAT('%', :keyword, '%')))  AND "
	           + "o.tipoOp = 'OP' AND o.idEstadoDoc = 1 "
	           + "ORDER BY o.numOp DESC ")
	Page<VistaOrdenPv> buscarPorKeywordPaginado(String keyword, Pageable pageable);

	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(value = "UPDATE orden_pv "
			+ "SET centros_trabajo_tep_reportado = :centrosTep "
			+ "WHERE id_op_ia = :idOpIntegrapps", nativeQuery = true)
	int actualizarCentrosTep(@Param("idOpIntegrapps")Integer idOpIntegrapps, @Param("centrosTep")String centrosTep);

	@Query(value = "SELECT * "
			+ "FROM view_orden_pv "
			+ "WHERE id_op_ia = :idOpIntegrapps ", nativeQuery = true)
	VistaOrdenPv buscarPorId(@Param("idOpIntegrapps") Integer idOpIntegrapps);
	
	@Query(value =
			"DECLARE @schema NVARCHAR(100) = :schema\\; " +
			"DECLARE @idOPI INT = :idOPI\\; " +
			"DECLARE @sql NVARCHAR(MAX) = ' " +
			"SELECT TOP (1) items.f120_id " +
			"FROM ' + @schema + '.t851_mf_op_docto_item doc_item " +
			"INNER JOIN ' + @schema + '.t121_mc_items_extensiones ext " +
			"    ON doc_item.f851_rowid_item_ext_padre = ext.f121_rowid " +
			"INNER JOIN ' + @schema + '.t120_mc_items items " +
			"    ON ext.f121_rowid_item = items.f120_rowid " +
			"INNER JOIN Integrapps.dbo.orden_pv op " +
			"    ON doc_item.f851_rowid = op.Row851_id " +
			"WHERE op.id_op_ia = @idOPI'; " +
			"EXEC sp_executesql @sql, N'@idOPI INT', @idOPI",
			nativeQuery = true)
	Integer obteneridItemOpPorIdOpIA(@Param("schema") String schema, @Param("idOPI") Integer idOPI);

	@Query(value = "SELECT Num_Op "
			+ "FROM view_orden_pv "
			+ "WHERE id_op_ia = :idOpIntegrapps ", nativeQuery = true)
	Integer obtenerNumOpPorIdOpIA(@Param("idOpIntegrapps")Integer idOpIntegrapps);


}
