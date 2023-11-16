package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;
import com.almatec.controlpiso.integrapps.repositories.ItemOpRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;

@Service
public class ItemOpServiceImpl implements ItemOpService {
	
	@Autowired
	private ItemOpRepository itemOpRepo;

	@Override
	public List<ItemOp> obtenerItemsOp(String idOp) {
		List<ItemOpInterface> listaItemsOpInterface = itemOpRepo.obtenerItemsOp(idOp);
		List<ItemOp> ItemsOp = new ArrayList<>();
		for(ItemOpInterface itemInterface: listaItemsOpInterface) {
			ItemOp item = new ItemOp(itemInterface);
			ItemsOp.add(item);
		}
		return ItemsOp;
	}

}
