package com.almatec.controlpiso.security.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.security.entities.Modulo;
import com.almatec.controlpiso.security.repositories.ModuloRepository;
import com.almatec.controlpiso.security.services.ModuloService;

@Service
public class ModuloServiceImpl implements ModuloService {
	
	private final ModuloRepository moduloRepo;
	

	public ModuloServiceImpl(ModuloRepository moduloRepo) {
		this.moduloRepo = moduloRepo;
	}


	@Override
	public List<Modulo> findAll() {
		return moduloRepo.findAll();
	}

}
