package com.almatec.controlpiso.integrapps.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.entities.Item;
import com.almatec.controlpiso.integrapps.repositories.ItemRepository;
import com.almatec.controlpiso.integrapps.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepo;

	@Override
	public Item buscarItemFabrica(Integer idItemFab) {
		
		return itemRepo.findById(idItemFab)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro el item con id: " + idItemFab));
	}

	@Override
	public Item buscarItemFabricaPorIdItem(Integer idItemMAteriaPrima) {
		return itemRepo.findByIdItem(idItemMAteriaPrima);
	}

}
