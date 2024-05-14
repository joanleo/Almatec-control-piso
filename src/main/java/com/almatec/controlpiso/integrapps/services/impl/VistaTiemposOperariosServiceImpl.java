package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.TiemposOperariosDTO;
import com.almatec.controlpiso.integrapps.interfaces.TiemposOperarioInterface;
import com.almatec.controlpiso.integrapps.repositories.VistaTiemposOperariosRepository;
import com.almatec.controlpiso.integrapps.services.VistaTiemposOperariosService;

@Service
public class VistaTiemposOperariosServiceImpl implements VistaTiemposOperariosService {

	@Autowired
	private VistaTiemposOperariosRepository vistaTiemposOperariosRepository;
	
	@Override
	public List<TiemposOperariosDTO> obtenerTiemposOperarios(Integer idProceso) {
		List<TiemposOperarioInterface> tiemposOperInterface = vistaTiemposOperariosRepository.findByIdConfigProceso(idProceso);
		List<TiemposOperariosDTO> tiemposOperDTO = new ArrayList<>();
		tiemposOperInterface.forEach(item->{
			TiemposOperariosDTO tiempoOper = new TiemposOperariosDTO(item);
			tiemposOperDTO.add(tiempoOper);
		});
		return tiemposOperDTO;
	}

}
