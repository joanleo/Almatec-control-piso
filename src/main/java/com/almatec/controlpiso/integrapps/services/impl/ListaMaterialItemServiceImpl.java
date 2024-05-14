package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.ListaMaterialItem;
import com.almatec.controlpiso.integrapps.repositories.ListaMaterialItemRepository;
import com.almatec.controlpiso.integrapps.services.ListaMaterialItemService;

@Service
public class ListaMaterialItemServiceImpl implements ListaMaterialItemService {
	
	@Autowired
	private ListaMaterialItemRepository listaMaterialItemRepo;

	@Override
	public List<ListaMaterialItem> buscarListaMaterialItem(Integer id) {
		return listaMaterialItemRepo.findByIdItem(id);
	}

}
