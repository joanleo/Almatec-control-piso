package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.Parametro;

public interface ParametroRepository extends JpaRepository<Parametro, Integer> {

	@Query(value = "SELECT SUM(cant) "
			+ "FROM pro_reporte_pieza_ct "
			+ "WHERE item_parte_id = :idPerfil "
			+ "AND item_id = :idItem "
			+ "AND is_consume = 1 "
			+ "GROUP BY item_id", nativeQuery = true)
	Integer buscarCantidadesFabricadasPerfil(@Param("idItem") Long idItem, @Param("idPerfil")Integer idPerfil);

	@Query(value = "SELECT SUM(cant) "
			+ "FROM pro_reporte_pieza_ct "
			+ "WHERE Item_fab_id = :idItemFab "
			+ "AND C_centrotrabajo_id = :idCT "
			+ "AND item_id = :idItem "
			+ "GROUP BY item_id", nativeQuery = true)
	Integer buscarCantidadesFabricadasConjunto(@Param("idItem") Long idItem, @Param("idItemFab")Integer idItemFab, 
											@Param("idCT")Integer idCT);

}
