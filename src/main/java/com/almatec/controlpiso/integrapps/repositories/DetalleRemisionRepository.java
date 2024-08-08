package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.almacen.interfaces.DetalleRemisionInterface;
import com.almatec.controlpiso.integrapps.entities.DetalleRemision;

public interface DetalleRemisionRepository extends JpaRepository<DetalleRemision, Long> {

	@Query(value = "SELECT   remision_detalle.id_detalle_remision AS IdDetalleRemision, remision_detalle.cant AS Cant, remision_detalle.fecha_creacion AS FechaCreacion, "
			+ "remision_detalle.id_remision AS IdRemision, remision_detalle.id_item_op AS IdItemOp, items_op.descripcion AS Descripcion, items_op.cant_req AS CantSol, "
			+ "items_op.cant_cumplida AS CantCumplida, items_op.cant_despacha AS CantDespachada, items_op.peso_unitario AS Peso, items_op.marca AS Marca,"
			+ "items_fabrica.item_ue AS UndEmpaque "
			+ "FROM  remision_detalle "
			+ "INNER JOIN items_op "
			+ "ON remision_detalle.id_item_op = items_op.item_id "
			+ "LEFT JOIN items_fabrica "
			+ "ON items_op.Item_fab_Id = items_fabrica.Item_fab_Id "
			+ "WHERE   (remision_detalle.id_remision = :idRemision)", nativeQuery = true)
	List<DetalleRemisionInterface> buscarDetallesPorIdRemision(@Param("idRemision") Long idRemision);

}
