package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.dtos.ConsultaOpCreadaDTO;
import com.almatec.controlpiso.integrapps.entities.OrdenPv;

public interface OrdenPvRepository extends JpaRepository<OrdenPv, Integer> {

	@Query(value = "SELECT * "
			+"FROM orden_pv_vw "
			+"WHERE CONCAT(Und_Neg, f200_razon_social, f431_id_proyecto, Zona_Desc, estado_doc, Esq_Pintura) "
			+"LIKE %:keyword% "
			+"ORDER BY id_op_ia DESC", nativeQuery = true)
	List<OrdenPv> buscarPorKeyword(String keyword);

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

	List<OrdenPv> findByIdProyecto(String idProyecto);

	@Query(value = "UPDATE  orden_pv "
			+ "SET Row850_id=:#{#creado.f850_rowid} , Row851_id=:#{#creado.f851_rowid} , Num_Op=:#{#creado.f850_consec_docto} , "
			+ "op_UnoEE=:opUnoEE "
			+ "WHERE id_op_ia = :#{#ordenIntegrapps.id}", nativeQuery = true)
	void actualizarOp(@Param("creado") ConsultaOpCreadaDTO creado, @Param("opUnoEE") String opUnoEE, @Param("ordenIntegrapps")OrdenPv ordenIntegrapps);

	@Query(value = "SELECT Num_Op "
			+ "FROM orden_pv_vw "
			+ "WHERE id_op_ia = :idOpIntegrapps ", nativeQuery = true)
	Integer obtenerNumOpPorIdOp(@Param("idOpIntegrapps") Integer idOpIntegrapps);

	@Query(value = "SELECT   TOP (1) orden_pv_vw.id_op_ia, orden_pv_vw.id_op_padre, orden_pv_vw.Row430_id, orden_pv_vw.Row431_id, orden_pv_vw.Id_Emp_Ing, orden_pv_vw.Tipo_OP, orden_pv_vw.Num_Op, orden_pv_vw.op_UnoEE, orden_pv_vw.id_est_doc, "
			+ "                orden_pv_vw.Fec_plan_IngR, orden_pv_vw.Fec_Real_IngR, orden_pv_vw.Codigo_Sgc, orden_pv_vw.Resp_Ingenieria, orden_pv_vw.fecha_ingenieria, orden_pv_vw.Fecha_contractual, orden_pv_vw.F_Con_Actual, orden_pv_vw.Kg_Ttl, "
			+ "                orden_pv_vw.Kg_Reales, orden_pv_vw.ord_und, orden_pv_vw.ord_cant, orden_pv_vw.Observaciones, orden_pv_vw.Anulada, orden_pv_vw.Fec_Desp, orden_pv_vw.Arch_adjunto, orden_pv_vw.Fec_Competada, orden_pv_vw.F_Aper, "
			+ "                orden_pv_vw.BarCodeH, orden_pv_vw.BarCodeM, orden_pv_vw.ord_fecha_planeada, orden_pv_vw.Bodega, orden_pv_vw.Ept_UnoEE, orden_pv_vw.Sci_UnoEE, orden_pv_vw.Und_Neg, orden_pv_vw.Zona_Desc, orden_pv_vw.Fecha_Ent, "
			+ "                orden_pv_vw.Fecha_A_Prod, orden_pv_vw.Color_Bas, orden_pv_vw.Color_Vigas, orden_pv_vw.Color_Pro, orden_pv_vw.f431_id_proyecto, orden_pv_vw.f200_razon_social, orden_pv_vw.estado_doc, orden_pv_vw.Esq_Pintura, "
			+ "                orden_pv_vw.f200_nit, orden_pv_vw.f120_referencia, orden_pv_vw.f120_descripcion, orden_pv_vw.kg_fabricar "
			+ "FROM      orden_pv_vw "
			+ "INNER JOIN      UnoEE_Prueba.dbo.t430_cm_pv_docto ON orden_pv_vw.Row430_id = UnoEE_Prueba.dbo.t430_cm_pv_docto.f430_rowid "
			+ "WHERE   (UnoEE_Prueba.dbo.t430_cm_pv_docto.f430_id_tipo_docto = 'pv') "
			+ "AND (UnoEE_Prueba.dbo.t430_cm_pv_docto.f430_consec_docto = :noPedido) "
			+ "AND (orden_pv_vw.Tipo_OP <> 'IF') "
			+ "ORDER BY orden_pv_vw.id_op_ia DESC ", nativeQuery = true)
	OrdenPv findByNumOp(@Param("noPedido") Integer noPedido);

}
