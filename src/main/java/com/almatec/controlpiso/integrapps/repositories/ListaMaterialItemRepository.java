package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.ListaMaterialItem;

public interface ListaMaterialItemRepository extends JpaRepository<ListaMaterialItem, Integer> {

	List<ListaMaterialItem> findByIdItem(Integer id);

}
