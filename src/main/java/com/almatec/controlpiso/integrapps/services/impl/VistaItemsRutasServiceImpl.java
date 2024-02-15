package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.VistaItemsRutas;
import com.almatec.controlpiso.integrapps.repositories.CentroTrabajoRepository;
import com.almatec.controlpiso.integrapps.repositories.VistaItemsRutasRepository;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.integrapps.services.VistaItemsRutasService;
import com.almatec.controlpiso.utils.EstructuraDatos;

@Service
public class VistaItemsRutasServiceImpl implements VistaItemsRutasService {
	
	@Autowired
	private VistaItemsRutasRepository vistaItemsRutasRepo;

	@Override
	public List<OpCentroTrabajoDTO> buscarOpCT(Integer idCT) {
		System.out.println("Centro de trabajo: " + idCT);
		List<VistaItemsRutas> listaRutas = vistaItemsRutasRepo.findByIdCentroTrabajoConjuntoOrIdCentroTrabajoPerfil(idCT, idCT);
				
		List<OpCentroTrabajoDTO> ordenesProduccion = EstructuraDatos.crearEstructura(listaRutas);
		for(OpCentroTrabajoDTO op:ordenesProduccion) {
			System.out.println(op);
		}
		
		if(Objects.equals(listaRutas.get(0).getIdCentroTrabajoPerfil(), idCT)) {
			System.out.println("En este centro de trabajo se muestra un componente");
		}

		return ordenesProduccion;
	}

}
