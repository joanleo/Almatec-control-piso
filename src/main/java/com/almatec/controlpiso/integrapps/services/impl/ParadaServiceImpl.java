package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.Parada;
import com.almatec.controlpiso.integrapps.repositories.ParadaRepository;
import com.almatec.controlpiso.integrapps.services.ParadaService;

@Service
public class ParadaServiceImpl implements ParadaService {
	
	@Autowired
	private ParadaRepository paradaRepo;

	@Override
	public List<Parada> obtenerParadas() {
		return paradaRepo.findAll();
	}

}
