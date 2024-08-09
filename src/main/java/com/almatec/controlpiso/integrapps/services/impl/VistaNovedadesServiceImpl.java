package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.VistaNovedades;
import com.almatec.controlpiso.integrapps.repositories.VistaNovedadesRepository;
import com.almatec.controlpiso.integrapps.services.VistaNovedadesService;

@Service
public class VistaNovedadesServiceImpl implements VistaNovedadesService{

	@Autowired
	private VistaNovedadesRepository vistaNovedadesRepository;
	
	@Override
	public List<VistaNovedades> obtenerNovedades() {
		return vistaNovedadesRepository.findByEnviadoErpFalse();
	}

}
