package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;
import com.almatec.controlpiso.integrapps.repositories.VistaTiemposOperariosRepository;
import com.almatec.controlpiso.integrapps.services.VistaTiemposOperariosService;

@Service
public class VistaTiemposOperariosServiceImpl implements VistaTiemposOperariosService {

	@Autowired
	private VistaTiemposOperariosRepository vistaTiemposOperariosRepository;
	
	@Override
	public List<VistaTiemposOperarios> obtenerTiemposOperarios(Integer idProceso) {
		return vistaTiemposOperariosRepository.findByIdConfigProceso(idProceso);
	}

}
