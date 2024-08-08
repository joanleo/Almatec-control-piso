package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.services.VistaItemsRutasService;

@Service
public class VistaItemsRutasServiceImpl implements VistaItemsRutasService {

	@Override
	public Set<OpCentroTrabajoDTO> buscarOpCT(Integer idCT) {
		return null;
	}

	@Override
	public List<OpCentroTrabajoDTO> buscarOp(Integer idOp) {
		return null;
	}

	@Override
	public Set<OpCentroTrabajoDTO> buscarItemCt(Long idItem, Integer idCT) {
		return null;
	}
	

}
