package com.almatec.controlpiso.integrapps.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.RegistroPieza;
import com.almatec.controlpiso.integrapps.repositories.RegistroPiezaRepository;
import com.almatec.controlpiso.integrapps.services.RegistroPiezaService;

@Service
public class RegistroPiezaServiceImpl implements RegistroPiezaService{
	
	@Autowired
	private RegistroPiezaRepository registroPiezaRepo;

	@Override
	public RegistroPieza consultaRegistroPieza(Integer idCT, Integer idProceso, Integer idOperario, Integer idPieza) {
		return registroPiezaRepo.findByIdCTAndIdConfigAndIdOperarioAndIdItem(idCT, idProceso, idOperario, idPieza);
	}

	@Override
	public void actualizarRegistro(RegistroPieza registro) {

		registroPiezaRepo.saveAndFlush(registro);

	}

	@Override
	public RegistroPieza consultaRegistroPieza(Integer idCT, Integer idProceso, Integer idOperario, Integer idItemOp,
			Integer idItem) {
		return registroPiezaRepo.findByIdCTAndIdConfigAndIdOperarioAndIdItemAndIdItemParte(idCT, idProceso, idOperario, idItemOp, idItem);
	}

}
