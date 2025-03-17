package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.RutaItem;

public interface RutaItemRepository extends JpaRepository<RutaItem, Integer> {

	List<RutaItem> findByIdItemAndIsActivoTrue(Integer idItem);

}
