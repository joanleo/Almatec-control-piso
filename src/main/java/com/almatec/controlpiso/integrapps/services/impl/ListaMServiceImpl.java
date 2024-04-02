package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.ListaM;
import com.almatec.controlpiso.integrapps.repositories.ListaMRepository;
import com.almatec.controlpiso.integrapps.services.ListaMService;

@Service
public class ListaMServiceImpl implements ListaMService {
	
	@Autowired
	private ListaMRepository listaMaterialRepo;

	@Override
	public List<ListaM> obtenerListaMaterialesPorIdOp(Integer idOP) {
		return listaMaterialRepo.findByIdOpIntegrapps(idOP);
		}

}
