package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.dtos.ConsultaOpCreadaDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.interfaces.OrdenPvEstadoData;

public interface OrdenPvRepository extends JpaRepository<VistaOrdenPv, Integer> {

	@Query(value = "SELECT * "
			+"FROM orden_pv_vw "
			+"WHERE CONCAT(Und_Neg, f200_razon_social, f431_id_proyecto, Zona_Desc, estado_doc, Esq_Pintura) "
			+"LIKE %:keyword% "
			+"ORDER BY id_op_ia DESC", nativeQuery = true)
	List<VistaOrdenPv> buscarPorKeyword(String keyword);

	@Query(value = "SELECT f431_id_proyecto,f200_razon_social "
			+"FROM orden_pv_vw "
			+"GROUP BY f431_id_proyecto,f200_razon_social ", nativeQuery = true)
	List<Object[]> findProyects();

	@Query(value = "SELECT f431_id_proyecto,f200_razon_social, Und_Neg "
			+"FROM orden_pv_vw "
			+"WHERE CONCAT(f431_id_proyecto,f200_razon_social, Und_Neg) "
			+"LIKE %:keyword% "
			+"GROUP BY f431_id_proyecto,f200_razon_social, Und_Neg ", nativeQuery = true)
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
			+ "FROM orden_pv_vw "
			+ "WHERE id_op_ia = :idOpIntegrapps ", nativeQuery = true)
	Integer obtenerNumOpPorIdOp(@Param("idOpIntegrapps") Integer idOpIntegrapps);

	@Query(value = "SELECT  pv_cliente_razon_social AS Cliente, pv_cliente_co_id AS IdCo, pv_cliente_co_descripcion AS CoDescripcion "
			+ "FROM      view_pedidos_estado_erp "
			+ "WHERE   (pv_num = :noPedido) "
			+ "AND pv_tipo = 'PV'", nativeQuery = true)
	OrdenPvEstadoData findByNumOp(@Param("noPedido") Integer noPedido);

	List<VistaOrdenPv> findByTipoOpAndIdEstadoDoc(String string, int i);


}
