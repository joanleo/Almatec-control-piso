package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	Item findByIdItem(Integer idItemMAteriaPrima);

}
