package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt;

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

}
