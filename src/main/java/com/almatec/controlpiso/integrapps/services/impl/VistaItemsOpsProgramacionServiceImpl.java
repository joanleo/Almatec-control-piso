package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.Prioridad;
import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;
import com.almatec.controlpiso.integrapps.repositories.PrioridadRepository;
import com.almatec.controlpiso.integrapps.repositories.VistaItemsOpsProgramacionRepository;
import com.almatec.controlpiso.integrapps.services.VistaItemsOpsProgramacionService;

@Transactional
@Service
public class VistaItemsOpsProgramacionServiceImpl implements VistaItemsOpsProgramacionService {
	
	@Autowired
	private VistaItemsOpsProgramacionRepository vistaItemsOpsProgramacionRepo;
	
	@Autowired
	private PrioridadRepository prioridadRepo;

	@Override
	public List<VistaItemsOpsProgramacion> obtenerItemsOps() {
		List<VistaItemsOpsProgramacion> itemsOp = vistaItemsOpsProgramacionRepo.findAllItemsToMake();
		return itemsOp;
	}

	@Override
	public void actualizaPrioridad(Long idItem, Integer prioridad) {
		Prioridad prioridadFound = prioridadRepo.findByIdItem(idItem);
		if(prioridadFound != null) {
			System.out.println("encontro el item");
			prioridadFound.setItemPrioridad(prioridad);
			prioridadRepo.updatePrioridad(prioridadFound);
			System.out.println("Actualizo el item");
			return;
		}
		Prioridad nuevo = new Prioridad();
		nuevo.setIdItem(idItem);
		nuevo.setItemPrioridad(prioridad);
		prioridadRepo.crearPrioridad(nuevo);
		
	}

}