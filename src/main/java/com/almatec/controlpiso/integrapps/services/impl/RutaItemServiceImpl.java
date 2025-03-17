package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.ingenieria.dtos.RutaItemDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.RutaItem;
import com.almatec.controlpiso.integrapps.repositories.CentroTrabajoRepository;
import com.almatec.controlpiso.integrapps.repositories.RutaItemRepository;
import com.almatec.controlpiso.integrapps.services.RutaItemService;

@Service
public class RutaItemServiceImpl implements RutaItemService {
	
	@Autowired
	private RutaItemRepository rutaItemRepo;
	
	@Autowired
	private CentroTrabajoRepository centroTrabajoRepo;

	@Override
	public List<RutaItem> buscarRutaItem(Integer idItem) {
		return rutaItemRepo.findByIdItemAndIsActivoTrue(idItem);
	}

	@Override
	public List<RutaItemDTO> obtenerRutaPorItem(Integer idItem) {
		List<RutaItem> rutasItem = rutaItemRepo.findByIdItemAndIsActivoTrue(idItem);
		
		return rutasItem.stream()
	            .map(ruta -> {
	                CentroTrabajo centro = centroTrabajoRepo
	                    .findById(ruta.getIdCentroTrabajo())
	                    .orElseThrow(() -> new ResourceNotFoundException(
	                        "Centro de trabajo no encontrado: " + ruta.getIdCentroTrabajo()));
	                
	                return convertirARutaItemDTO(ruta, centro);
	            })
	            .collect(Collectors.toList());
	}
	
	private RutaItemDTO convertirARutaItemDTO(RutaItem ruta, CentroTrabajo centro) {
        return new RutaItemDTO(
            ruta.getIdRuta(),
            centro.getNombre(),
            centro.getaCT(),
            ruta.getValor(),
            //ruta.getOrden(),
            ruta.getIsActivo(),
            centro.getIdCentroTrabajoErp(),
            centro.getId(),
            centro.getUm()
        );
    }
	
	

}
