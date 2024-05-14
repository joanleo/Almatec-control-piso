package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.integrapps.entities.VistaOpItemsMaterialesRuta;

public interface VistaOpItemsMaterialesRutaRepository extends JpaRepository<VistaOpItemsMaterialesRuta, String> {

	List<VistaOpItemsMaterialesRuta> findByItemCentroTIdOrMaterialCentroTId(Integer idCT, Integer idCT2);

	@Query(value = "SELECT * "
			+ "FROM view_op_items_ruta "
			+ "WHERE ((item_op_id = :idItem) AND (item_centro_t_id = :idCT)) "
			+ "OR ((item_op_id = :idItem) AND(material_centro_t_id = :idCT)) ", nativeQuery = true)
	List<VistaOpItemsMaterialesRuta> buscarItemCt(Long idItem, Integer idCT);

}
