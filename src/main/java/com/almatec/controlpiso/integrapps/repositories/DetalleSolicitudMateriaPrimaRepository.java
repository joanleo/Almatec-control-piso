package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.interfaces.DetalleSolicDesItemInterface;

public interface DetalleSolicitudMateriaPrimaRepository extends JpaRepository<DetalleSolicitudMateriaPrima, Integer> {

	@Query(value =
			"DECLARE @schema NVARCHAR(100) = :schema\\; " +
			"DECLARE @idSolic INT = :idSolic\\; " +
			"DECLARE @sql NVARCHAR(MAX) = ' " +
			"SELECT det.id_sol_mp_det, det.id_sol_mp, det.Cod_Erp, det.Und_Erp, det.Cant_Sol, det.Cant_Entrega, det.Lotes_Erp, det.Estado_Item, " + 
			"det.Bodega_Entrega, det.Id_Usu_Sol, det.Id_Usu_Erp, det.fecha, items.f120_descripcion " +
			"FROM Mp_Sol_Det det " +
			"INNER JOIN ' + @schema + '.t120_mc_items items " +
			"    ON det.Cod_Erp = items.f120_id " +
			"WHERE items.f120_id_cia = 22 " +
			"    AND det.id_sol_mp = @idSolic'; " +
			"EXEC sp_executesql @sql, N'@idSolic INT', @idSolic",
			nativeQuery = true)
			List<DetalleSolicDesItemInterface> obtenerInterfacePorIdSolicitud(@Param("schema") String schema,
			@Param("idSolic") Integer idSolic);

	List<DetalleSolicitudMateriaPrima> findByIdSolicitud(Integer idSol);

}
