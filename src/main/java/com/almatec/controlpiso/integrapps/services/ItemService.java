package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.almatec.controlpiso.ingenieria.dtos.ItemDetalladoDTO;
import com.almatec.controlpiso.integrapps.entities.Item;

public interface ItemService {

	Item buscarItemFabrica(Integer idItemFab);

	//Item buscarItemFabricaPorCodErp(Integer idItemMAteriaPrima);

	Item buscarItemFabricaPorIdItem(Integer idItemMAteriaPrima);

	Item guardarItem(Item itemReporte);

	List<Item> buscarItems(String query, String tipo);

	Page<Item> buscarItems(String query, String tipo, Pageable pageable);

	ItemDetalladoDTO buscarItemConDetalles(Integer id);

}
