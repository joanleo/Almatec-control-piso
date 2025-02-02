package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.VistaKgMesDTO;
import com.almatec.controlpiso.integrapps.dtos.VistaKgPorCtDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasReportadas;
import com.almatec.controlpiso.integrapps.interfaces.VistaKgMes;
import com.almatec.controlpiso.integrapps.interfaces.VistaKgPorCt;
import com.almatec.controlpiso.integrapps.repositories.VistaTiemposOperariosRepository;
import com.almatec.controlpiso.integrapps.services.VistaPiezasReportadasService;

@Service
public class GestionProduccionService {

	@Autowired
	private VistaTiemposOperariosRepository vistaTiemposOperariosRepo;
	
	@Autowired
	private VistaPiezasReportadasService vistaPiezasReportadasService;
	
	public List<VistaKgPorCtDTO> crearInformeGeneral() {
		List<VistaKgPorCt> lista = vistaTiemposOperariosRepo.findVistaKgPorCt();
		return lista
				.stream()
				.map(VistaKgPorCtDTO::new)
				.collect(Collectors.toList());
	}

	public List<VistaKgMesDTO> obtenerDataMes() {
		List<VistaKgMes> lista = vistaTiemposOperariosRepo.findVistaKgMes();
		List<VistaKgMesDTO> result = lista
				.stream()
				.map(VistaKgMesDTO::new)
				.collect(Collectors.toList());
		return result;
	}
	
	public List<VistaPiezasReportadas> obtenerResumenOps() {
		List<Integer> listaIds = List.of(15, 16, 17);
		List<VistaPiezasReportadas> lista = vistaPiezasReportadasService.findByIdOpIntegrappsIn(listaIds);
		//lista.forEach(System.out::println);
		
		return lista;
	}

}
