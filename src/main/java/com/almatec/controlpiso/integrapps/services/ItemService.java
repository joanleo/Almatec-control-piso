package com.almatec.controlpiso.integrapps.services;

import com.almatec.controlpiso.integrapps.entities.Item;

public interface ItemService {

	Item buscarItemFabrica(Integer idItemFab);

	//Item buscarItemFabricaPorCodErp(Integer idItemMAteriaPrima);

	Item buscarItemFabricaPorIdItem(Integer idItemMAteriaPrima);

	Item guardarItem(Item itemReporte);

}
