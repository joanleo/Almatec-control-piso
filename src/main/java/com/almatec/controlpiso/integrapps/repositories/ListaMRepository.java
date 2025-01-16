package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO;
import com.almatec.controlpiso.integrapps.entities.ListaM;
import com.almatec.controlpiso.integrapps.interfaces.ListaMInterface;

public interface ListaMRepository extends JpaRepository<ListaM, Integer> {

	@Query(value =
			"DECLARE @schema NVARCHAR(100) = :schema\\; " +
			"DECLARE @idOP INT = :idOP\\; " +
			"DECLARE @sql NVARCHAR(MAX) = ' " +
			"SELECT mp.Id_Mp_Op, mp.id_op_ia, mp.Cod_Erp, mp.Und_Erp, mp.Cant_Req_Ini, mp.Cant_Req_Act, mp.Cant_Entrega, " +
			"mp.Cant_Existen, mp.Peso_Unt, mp.Id_Usu_Sol, mp.Tipo, mp.Fecha, mp.Estado, mp.Env_LM, items.f120_id_cia, items.f120_descripcion " +
			"FROM Lista_MP_Ops mp " +
			"LEFT JOIN ' + @schema + '.t120_mc_items items " +
			"    ON mp.Cod_Erp = items.f120_id " +
			"    AND items.f120_id_cia = 22 " +
			"INNER JOIN orden_pv op " +
			"    ON mp.id_op_ia = op.id_op_ia " +
			"INNER JOIN ' + @schema + '.t850_mf_op_docto doc " +
			"    ON op.Row850_id = doc.f850_rowid " +
			"WHERE mp.id_op_ia = @idOP " +
			"    AND mp.Cod_Erp <> 0 " +
			"    AND mp.Cant_Req_Ini > 0 " +
			"    AND (doc.f850_ind_estado = 1 " +
			"    OR doc.f850_ind_estado = 2)'; " +
			"EXEC sp_executesql @sql, N'@idOP INT', @idOP",
			nativeQuery = true)
			List<ListaMInterface> ObtenerListaInterdacePorIdOp(@Param("schema") String schema, @Param("idOP") Integer idOP);

	List<ListaM> findByIdOpIntegrapps(Integer idOp);

	@Query("SELECT new com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO(d.codigoErp, d.loteErp) " +
           "FROM SolicitudMateriaPrima s " +
           "JOIN DetalleSolicitudMateriaPrima d ON s.id = d.idSolicitud " +
           "WHERE s.idOp = :idOp " +
           "AND d.idEstadoItem = 1 " +
           "GROUP BY d.loteErp, d.codigoErp")
    List<LoteConCodigoDTO> obtenerLotesOpPorItem(@Param("idOp") Integer idOp);

	@Query(value = "SELECT  TOP (1) id_materia_prima "
			+ "FROM      z_item_materia_prima "
			+ "WHERE   (id_item = :idItem)", nativeQuery = true)
	Integer obtenerCodMateriaPrimaItemReporte(@Param("idItem") Integer idItem);

	@Query("SELECT new com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO(d.codigoErp, d.loteErp) " +
	           "FROM SolicitudMateriaPrima s " +
	           "JOIN DetalleSolicitudMateriaPrima d ON s.id = d.idSolicitud " +
	           "WHERE s.idOp = :idOp " +
	           "AND d.idEstadoItem = 1 " +
	           "AND d.codigoErp = :codErpMp " +
	           "GROUP BY d.loteErp, d.codigoErp")
	List<LoteConCodigoDTO> obtenerLotesOpPorItemYCodErp(@Param("idOp") Integer idOp, @Param("codErpMp") Integer codErpMp);


}
