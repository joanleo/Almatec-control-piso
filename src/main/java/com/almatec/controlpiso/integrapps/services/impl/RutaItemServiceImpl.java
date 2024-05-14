package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.RutaItem;
import com.almatec.controlpiso.integrapps.repositories.RutaItemRepository;
import com.almatec.controlpiso.integrapps.services.RutaItemService;

@Service
public class RutaItemServiceImpl implements RutaItemService {
	
	@Autowired
	private RutaItemRepository rutaItemRepo;

	@Override
	public List<RutaItem> buscarRutaItem(Integer idItem) {
		return rutaItemRepo.findByIdItem(idItem);
	}

}
