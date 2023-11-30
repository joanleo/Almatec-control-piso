package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;
import com.almatec.controlpiso.integrapps.repositories.ItemOpRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;

@Service
public class ItemOpServiceImpl implements ItemOpService {
	
	@Autowired
	private ItemOpRepository itemOpRepo;

	@Override
	public List<ItemOp> obtenerItemsOpC1(String idOp) {
		List<ItemOpInterface> listaItemsOpInterface = itemOpRepo.obtenerItemsOpC1(idOp);
		List<ItemOp> itemsOp = new ArrayList<>();
		for(ItemOpInterface itemInterface: listaItemsOpInterface) {
			ItemOp item = new ItemOp(itemInterface);
			itemsOp.add(item);
		}
		return itemsOp;
	}

	@Override
	public List<ItemOp> obtenerItemsOpC2(String idGrupo) {
		System.out.println("Padre: " + idGrupo);
		return itemOpRepo.obtenerItemsOpC2(idGrupo);
	}

	@Override
	public ItemOp obtenerItemsOp(String idGrupo) {
		return itemOpRepo.findById(Long.valueOf(idGrupo))
				.orElseThrow(()-> new ResourceNotFoundException("Item no encontrado"));
	}

}
