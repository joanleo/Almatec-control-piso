package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.integrapps.entities.OrdenPv;

public interface OrdenPvRepository extends JpaRepository<OrdenPv, Integer> {

	@Query(value = "SELECT * "
			+ "FROM orden_pv_vw "
			+ "WHERE CONCAT(Und_Neg, f200_razon_social, f431_id_proyecto, Zona_Desc, estado_doc, Esq_Pintura) "
			+ "LIKE %:keyword% "
			+ "ORDER BY id_op_ia DESC", nativeQuery = true)
	List<OrdenPv> buscarPorKeyword(String keyword);

}
