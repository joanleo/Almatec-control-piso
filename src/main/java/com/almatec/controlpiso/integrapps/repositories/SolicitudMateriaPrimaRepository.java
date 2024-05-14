package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.interfaces.SolicitudUsuarioInterface;

public interface SolicitudMateriaPrimaRepository extends JpaRepository<SolicitudMateriaPrima, Integer> {

	@Query(value = "SELECT TOP(1) id_sol_mp "
			+ "FROM Mp_Sol "
			+ "ORDER BY id_sol_mp DESC", nativeQuery = true)
	Integer obtenerConsecutivo();

	@Query(value = "SELECT Mp_Sol.id_sol_mp, Mp_Sol.cia, Mp_Sol.Bodega_Erp, Mp_Sol.Tipo_doc, Mp_Sol.Num_doc, "
			+ "Mp_Sol.Fecha_Doc, Mp_Sol.Estado_Doc, Mp_Sol.id_op_ia, Mp_Sol.Tipo_Op, Mp_Sol.Num_Op, Mp_Sol.Id_Usu_Sol, Mp_Sol.Id_Usu_Erp, "
			+ "Mp_Sol.Tipo_doc_Erp, Mp_Sol.Num_Doc_Erp, Mp_Sol.Fecha_Doc_Erp, Mp_Sol.Barcode, usuarios.usu_nombre "
			+ "FROM Mp_Sol "
			+ "INNER JOIN "
			+ "usuarios ON Mp_Sol.Id_Usu_Sol = usuarios.usu_id "
			+ "WHERE Mp_Sol.Estado_Doc = :idEstado", nativeQuery = true)
	List<SolicitudUsuarioInterface> findByIdEstado(@Param("idEstado") int i);

	@Query(value = "SELECT   TOP (1) Cod_Erp "
			+ "FROM      Mp_Sol_Det "
			+ "WHERE   (Lotes_Erp = :lote)", nativeQuery = true)
	Integer obtenerCodErpPorLote(@Param("lote") String lote);

	@Query(value = "SELECT f808_id "
			+ "FROM  pro_centrostrabajo "
			+ "WHERE   (C_centrotrabajo_id = :idCentroTrabajo)", nativeQuery = true)
	String obtenerIdctErp(@Param("idCentroTrabajo") Integer idCentroTrabajo);

}
