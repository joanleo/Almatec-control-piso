package com.almatec.controlpiso.erp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.erp.entities.ListaMaterial;

public interface ListaMaterialRepository extends JpaRepository<ListaMaterial, Integer> {

	@Query(value = "SELECT f820_rowid, f820_id_metodo, f820_secuencia, f820_rowid_item_ext_hijo "
			+ "FROM t820_mf_lista_material "
			+ "INNER JOIN t121_mc_items_extensiones "
			+ "ON t820_mf_lista_material.f820_rowid_item_ext = t121_mc_items_extensiones.f121_rowid "
			+ "INNER JOIN t120_mc_items "
			+ "ON t121_mc_items_extensiones.f121_rowid_item = t120_mc_items.f120_rowid "
			+ "WHERE   (t120_mc_items.f120_id = :f820_id)", nativeQuery = true)
	List<ListaMaterial> obtenerListaActual(@Param("f820_id") Integer f820_id);

}
