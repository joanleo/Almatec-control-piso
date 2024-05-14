package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.ListaM;
import com.almatec.controlpiso.integrapps.interfaces.ListaMInterface;

public interface ListaMRepository extends JpaRepository<ListaM, Integer> {

	@Query(value = "SELECT  Lista_MP_Ops.Id_Mp_Op, Lista_MP_Ops.id_op_ia, Lista_MP_Ops.Cod_Erp, Lista_MP_Ops.Und_Erp, Lista_MP_Ops.Cant_Req_Ini, "
			+ "Lista_MP_Ops.Cant_Req_Act, Lista_MP_Ops.Cant_Entrega, Lista_MP_Ops.Cant_Existen, Lista_MP_Ops.Peso_Unt, Lista_MP_Ops.Id_Usu_Sol, "
			+ "Lista_MP_Ops.Tipo, Lista_MP_Ops.Fecha, Lista_MP_Ops.Estado, Lista_MP_Ops.Env_LM, UnoEE_Prueba.dbo.t120_mc_items.f120_id_cia, "
			+ "UnoEE_Prueba.dbo.t120_mc_items.f120_descripcion "
			+ "FROM  Lista_MP_Ops "
			+ "INNER JOIN UnoEE_Prueba.dbo.t120_mc_items "
			+ "ON Lista_MP_Ops.Cod_Erp = UnoEE_Prueba.dbo.t120_mc_items.f120_id "
			+ "WHERE   (UnoEE_Prueba.dbo.t120_mc_items.f120_id_cia = 22) "
			+ "AND Lista_MP_Ops.id_op_ia = :idOP ", nativeQuery = true)
	List<ListaMInterface> ObtenerListaInterdacePorIdOp(@Param("idOP") Integer idOP);

	List<ListaM> findByIdOpIntegrapps(Integer idOp);

	@Query(value = "SELECT   Mp_Sol_Det.Lotes_Erp "
			+ "FROM  Mp_Sol "
			+ "INNER JOIN Mp_Sol_Det ON Mp_Sol.id_sol_mp = Mp_Sol_Det.id_sol_mp "
			+ "WHERE   (Mp_Sol.id_op_ia = :idOp) "
			+ "AND (Mp_Sol_Det.Estado_Item = 1) "
			+ "GROUP BY Mp_Sol_Det.Lotes_Erp ", nativeQuery = true)
	List<String> obtenerLotesOpPorItem(@Param("idOp") Integer idOp);


}
