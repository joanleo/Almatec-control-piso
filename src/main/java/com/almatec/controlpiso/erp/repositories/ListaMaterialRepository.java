package com.almatec.controlpiso.erp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.erp.entities.ListaMaterial;
import com.almatec.controlpiso.erp.interfaces.RutaInterface;

public interface ListaMaterialRepository extends JpaRepository<ListaMaterial, Integer> {

	@Query(value = "SELECT f820_rowid, f820_id_metodo, f820_secuencia, t120_mc_items_1.f120_id AS id_hijo "
			+ "FROM t820_mf_lista_material "
			+ "INNER JOIN t121_mc_items_extensiones "
			+ "ON t820_mf_lista_material.f820_rowid_item_ext = t121_mc_items_extensiones.f121_rowid "
			+ "INNER JOIN t120_mc_items "
			+ "ON t121_mc_items_extensiones.f121_rowid_item = t120_mc_items.f120_rowid "
			+ "INNER JOIN t121_mc_items_extensiones AS t121_mc_items_extensiones_1 "
			+ "ON t820_mf_lista_material.f820_rowid_item_ext_hijo = t121_mc_items_extensiones_1.f121_rowid "
			+ "INNER JOIN t120_mc_items AS t120_mc_items_1 "
			+ "ON t121_mc_items_extensiones_1.f121_rowid_item = t120_mc_items_1.f120_rowid "
			+ "WHERE   (t120_mc_items.f120_id = :f820_id)", nativeQuery = true)
	List<ListaMaterial> obtenerListaActual(@Param("f820_id") Integer f820_id);

	@Query(value = "SELECT   t808_mf_rutas.f808_id, t808_mf_rutas.f808_id_instalacion, t809_mf_rutas_operacion.f809_id_metodo, "
			+ "t809_mf_rutas_operacion.f809_numero_operacion "
			+ "FROM t809_mf_rutas_operacion "
			+ "INNER JOIN t808_mf_rutas "
			+ "ON t809_mf_rutas_operacion.f809_rowid_rutas = t808_mf_rutas.f808_rowid "
			+ "WHERE   (t808_mf_rutas.f808_id = :f808_id) AND (t809_mf_rutas_operacion.f809_id_cia = 22)", nativeQuery = true)
	List<RutaInterface> obtenerRutas(String f808_id);


}
