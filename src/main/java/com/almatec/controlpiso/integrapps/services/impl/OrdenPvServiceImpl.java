package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.OrdenPv;
import com.almatec.controlpiso.integrapps.repositories.OrdenPvRepository;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;

@Service
public class OrdenPvServiceImpl implements OrdenPvService {
	
	@Autowired
	private OrdenPvRepository ordenPvRepo;

	@Override
	public List<OrdenPv> buscarProyectos() {
		List<OrdenPv> proyectos = ordenPvRepo.findAll();
		return proyectos;
	}

	@Override
	public List<OrdenPv> buscarProyectos(String keyword) {
		List<OrdenPv> proyectos = ordenPvRepo.buscarPorKeyword(keyword);
		return proyectos;
	}

}
