package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ParametroDTO;
import com.almatec.controlpiso.integrapps.entities.Parametro;
import com.almatec.controlpiso.integrapps.repositories.ParametroRepository;
import com.almatec.controlpiso.integrapps.services.ParametroService;

@Transactional
@Service
public class ParametroServiceImpl implements ParametroService {
	
	@Autowired
	private ParametroRepository parametroRepo;

	@Override
	public List<Parametro> obtenerParametros() {
		return parametroRepo.findAll();
	}

	@Override
	public List<ParametroDTO> obtenerParametrosDTO() {
		List<Parametro> parametros = parametroRepo.findAll();
		List<ParametroDTO> parametrosDTO = new ArrayList<>();
		parametros.forEach(item->{
			ParametroDTO parametroDTO = new ParametroDTO(item);
			parametrosDTO.add(parametroDTO);
		});
		return parametrosDTO;
	}

	@Override
	public void guardarParametros(List<ParametroDTO> parametrosDTO) {
		List<Parametro> parametros = new ArrayList<>();
		parametrosDTO.forEach(item->{
			Parametro parametro = new Parametro();
			parametro.setId(item.getId());
			parametro.setNombre(item.getNombre());
			parametro.setValor(item.getValor());
			parametros.add(parametro);
		});
		
		parametroRepo.saveAll(parametros);
	}

	@Override
	public Integer buscarCantidadesFabricadasPerfil(Long id, Integer getid_parte, Integer idCentroTrabajo) {
		return parametroRepo.buscarCantidadesFabricadasPerfil(id, getid_parte);
	}

	@Override
	public Integer buscarCantidadesFabricadasConjunto(Long idItem, Integer idItemFab, Integer idCentroTrabajo) {
		return parametroRepo.buscarCantidadesFabricadasConjunto(idItem, idItemFab, idCentroTrabajo);
	}

}
