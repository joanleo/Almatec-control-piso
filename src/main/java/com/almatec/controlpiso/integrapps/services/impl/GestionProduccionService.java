package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.VistaKgMesDTO;
import com.almatec.controlpiso.integrapps.dtos.VistaKgPorCtDTO;
import com.almatec.controlpiso.integrapps.interfaces.VistaKgMes;
import com.almatec.controlpiso.integrapps.interfaces.VistaKgPorCt;
import com.almatec.controlpiso.integrapps.repositories.VistaTiemposOperariosRepository;

@Service
public class GestionProduccionService {

	@Autowired
	private VistaTiemposOperariosRepository vistaTiemposOperariosRepo;
	
	public List<VistaKgPorCtDTO> crearInformeGeneral() {
		List<VistaKgPorCt> lista = vistaTiemposOperariosRepo.findVistaKgPorCt();
		List<VistaKgPorCtDTO> result = lista
				.stream()
				.map(VistaKgPorCtDTO::new)
				.collect(Collectors.toList());
		result.forEach(System.out::println);
		return result;
	}

	public List<VistaKgMesDTO> obtenerDataMes() {
		List<VistaKgMes> lista = vistaTiemposOperariosRepo.findVistaKgMes();
		List<VistaKgMesDTO> result = lista
				.stream()
				.map(VistaKgMesDTO::new)
				.collect(Collectors.toList());
		result.forEach(System.out::println);
		return result;
	}

}
