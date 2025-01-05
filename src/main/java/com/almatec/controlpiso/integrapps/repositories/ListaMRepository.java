package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO;
import com.almatec.controlpiso.integrapps.entities.ListaM;
import com.almatec.controlpiso.integrapps.interfaces.ListaMInterface;

public interface ListaMRepository extends JpaRepository<ListaM, Integer> {

	@Query(value = "SELECT  Lista_MP_Ops.Id_Mp_Op, Lista_MP_Ops.id_op_ia, Lista_MP_Ops.Cod_Erp, Lista_MP_Ops.Und_Erp, Lista_MP_Ops.Cant_Req_Ini, "
			+ "Lista_MP_Ops.Cant_Req_Act, Lista_MP_Ops.Cant_Entrega, Lista_MP_Ops.Cant_Existen, Lista_MP_Ops.Peso_Unt, Lista_MP_Ops.Id_Usu_Sol, "
			+ "Lista_MP_Ops.Tipo, Lista_MP_Ops.Fecha, Lista_MP_Ops.Estado, Lista_MP_Ops.Env_LM, UnoEE.dbo.t120_mc_items.f120_id_cia, "
			+ "UnoEE.dbo.t120_mc_items.f120_descripcion "
			+ "FROM  Lista_MP_Ops "
			+ "LEFT JOIN UnoEE.dbo.t120_mc_items "
			+ "ON Lista_MP_Ops.Cod_Erp = UnoEE.dbo.t120_mc_items.f120_id AND UnoEE.dbo.t120_mc_items.f120_id_cia = 22"
			+ "INNER JOIN orden_pv "
			+ "ON Lista_MP_Ops.id_op_ia = orden_pv.id_op_ia "
			+ "INNER JOIN UnoEE.dbo.t850_mf_op_docto "
			+ "ON orden_pv.Row850_id = UnoEE.dbo.t850_mf_op_docto.f850_rowid "
			+ "WHERE (Lista_MP_Ops.id_op_ia = :idOP AND Lista_MP_Ops.Cod_Erp <> 0 AND Lista_MP_Ops.Cant_Req_Ini > 0) "
			+ "AND ((UnoEE.dbo.t850_mf_op_docto.f850_ind_estado = 1) "
			+ "OR (UnoEE.dbo.t850_mf_op_docto.f850_ind_estado = 2))", nativeQuery = true)
	List<ListaMInterface> ObtenerListaInterdacePorIdOp(@Param("idOP") Integer idOP);

	List<ListaM> findByIdOpIntegrapps(Integer idOp);

	@Query("SELECT new com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO(d.codigoErp, d.loteErp) " +
           "FROM SolicitudMateriaPrima s " +
           "JOIN DetalleSolicitudMateriaPrima d ON s.id = d.idSolicitud " +
           "WHERE s.idOp = :idOp " +
           "AND d.idEstadoItem = 1 " +
           "GROUP BY d.loteErp, d.codigoErp")
    List<LoteConCodigoDTO> obtenerLotesOpPorItem(@Param("idOp") Integer idOp);


}
