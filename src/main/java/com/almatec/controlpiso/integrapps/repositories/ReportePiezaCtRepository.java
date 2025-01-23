package com.almatec.controlpiso.integrapps.repositories;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt;
import com.almatec.controlpiso.produccion.dtos.ReportePiezaCtDTO;

public interface ReportePiezaCtRepository extends JpaRepository<ReportePiezaCt, Integer> {

	@Query(value = "SELECT COALESCE(SUM(cant), 0) "
		    + "FROM pro_reporte_pieza_ct "
		    + "WHERE Item_fab_id = :idItemFab "
		    + "AND C_centrotrabajo_id = :idCT "
		    + "AND item_id = :idItem", nativeQuery = true)
	Integer buscarCantidadesFabricadasConjunto(@Param("idItem") Long idItem, @Param("idItemFab")Integer idItemFab, 
											@Param("idCT")Integer idCT);

	@Query(value = "SELECT COALESCE(SUM(cant), 0) "
		    + "FROM pro_reporte_pieza_ct "
		    + "WHERE item_parte_id = :idPerfil "
		    + "AND C_centrotrabajo_id = :idCT "
		    + "AND item_id = :idItem", nativeQuery = true)
	Integer buscarCantidadesFabricadasPerfil(@Param("idItem") Long idItem, @Param("idPerfil")Integer idPerfil, 
			@Param("idCT")Integer idCT);
	
	@Query("SELECT new com.almatec.controlpiso.produccion.dtos.ReportePiezaCtDTO( "
			+ "r.id, "
			+ "o.nombre, "
			+ "ct.nombre, " +
				"CASE " +
			       "    WHEN r.idItemFab > 0 THEN ic.descripcion " +
			       "    WHEN r.idParte > 0 THEN ip.descripcion " +
			       "    ELSE 'Sin descripción' " +
			       "END, "
			+ "r.cant, "
			+ "r.fechaCreacion, "
			+ "r.lote, "
			+ "r.estado, "
			+ "r.ultimoIntento, "
			+ "r.mensajeError) " +
	           "FROM ReportePiezaCt r " +
	           "LEFT JOIN Operario o ON r.idOperario = o.id " +
	           "LEFT JOIN CentroTrabajo ct ON r.idCentroT = ct.id " +
	           "LEFT JOIN Item ic ON r.idItemFab = ic.idItem " +
	           "LEFT JOIN Item ip ON r.idParte = ip.idItem " +
	           "WHERE (:fechaInicio IS NULL OR r.fechaCreacion >= :fechaInicio) " +
	           "AND (:fechaFin IS NULL OR r.fechaCreacion <= :fechaFin) " +
	           "AND (:idOperario IS NULL OR r.idOperario = :idOperario) " +
	           "AND (:idCentroT IS NULL OR r.idCentroT = :idCentroT)")
	    Page<ReportePiezaCtDTO> findReportesPiezaWithFilters(
	            @Param("fechaInicio") LocalDateTime fechaInicio,
	            @Param("fechaFin") LocalDateTime fechaFin,
	            @Param("idOperario") Integer idOperario,
	            @Param("idCentroT") Integer idCentroT,
	            Pageable pageable);

}
