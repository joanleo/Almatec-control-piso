package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.ItemMaterial;

public interface ItemMaterialRepository extends JpaRepository<ItemMaterial, Integer> {

	@Query("SELECT im FROM ItemMaterial im WHERE im.idItem = :idItem AND im.activo = true ")
    List<ItemMaterial> findComponentes(@Param("idItem") Integer idItem);

}
