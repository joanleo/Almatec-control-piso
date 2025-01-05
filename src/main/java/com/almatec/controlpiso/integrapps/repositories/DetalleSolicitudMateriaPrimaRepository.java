package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.interfaces.DetalleSolicDesItemInterface;

public interface DetalleSolicitudMateriaPrimaRepository extends JpaRepository<DetalleSolicitudMateriaPrima, Integer> {

	@Query(value = "SELECT Mp_Sol_Det.id_sol_mp_det, Mp_Sol_Det.id_sol_mp, Mp_Sol_Det.Cod_Erp, Mp_Sol_Det.Und_Erp, "
			+ "Mp_Sol_Det.Cant_Sol, Mp_Sol_Det.Cant_Entrega, Mp_Sol_Det.Lotes_Erp, Mp_Sol_Det.Estado_Item, "
			+ "Mp_Sol_Det.Bodega_Entrega, Mp_Sol_Det.Id_Usu_Sol, Mp_Sol_Det.Id_Usu_Erp, Mp_Sol_Det.fecha, "
			+ "UnoEE.dbo.t120_mc_items.f120_descripcion "
			+ "FROM Mp_Sol_Det "
			+ "INNER JOIN "
			+ "UnoEE.dbo.t120_mc_items ON Mp_Sol_Det.Cod_Erp = UnoEE.dbo.t120_mc_items.f120_id "
			+ "WHERE(UnoEE.dbo.t120_mc_items.f120_id_cia = 22) "
			+ "AND Mp_Sol_Det.id_sol_mp = :idSolic ", nativeQuery = true)
	List<DetalleSolicDesItemInterface> obtenerInterfacePorIdSolicitud(@Param("idSolic") Integer idSolic);

	List<DetalleSolicitudMateriaPrima> findByIdSolicitud(Integer idSol);

}
