package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.almacen.interfaces.DetalleRemisionInterface;
import com.almatec.controlpiso.integrapps.entities.DetalleRemision;

public interface DetalleRemisionRepository extends JpaRepository<DetalleRemision, Long> {

	@Query(value = "SELECT   detalle_remision.id_detalle_remision AS IdDetalleRemision, detalle_remision.cant AS Cant, detalle_remision.fecha_creacion AS FechaCreacion, "
			+ "detalle_remision.id_remision AS IdRemision, detalle_remision.id_item_op AS IdItemOp, items_op.descripcion AS Descripcion, items_op.cant_req AS CantSol, "
			+ "items_op.cant_cumplida AS CantCumplida, items_op.cant_despacha AS CantDespachada, items_op.peso_unitario AS Peso, items_op.marca AS Marca,"
			+ "items_fabrica.item_ue AS UndEmpaque "
			+ "FROM  detalle_remision "
			+ "INNER JOIN items_op "
			+ "ON detalle_remision.id_item_op = items_op.item_id "
			+ "LEFT JOIN items_fabrica "
			+ "ON items_op.Item_fab_Id = items_fabrica.Item_fab_Id "
			+ "WHERE   (detalle_remision.id_remision = :idRemision)", nativeQuery = true)
	List<DetalleRemisionInterface> buscarDetallesPorIdRemision(@Param("idRemision") Long idRemision);

}
