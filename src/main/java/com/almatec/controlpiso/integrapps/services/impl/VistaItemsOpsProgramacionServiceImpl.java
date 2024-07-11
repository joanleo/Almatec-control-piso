package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.Prioridad;
import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;
import com.almatec.controlpiso.integrapps.repositories.PrioridadRepository;
import com.almatec.controlpiso.integrapps.repositories.VistaItemsOpsProgramacionRepository;
import com.almatec.controlpiso.integrapps.services.VistaItemsOpsProgramacionService;
import com.almatec.controlpiso.integrapps.specifications.PrioridadSpecification;
import com.almatec.controlpiso.programacion.dtos.PrioridadFilterDTO;

@Transactional
@Service
public class VistaItemsOpsProgramacionServiceImpl implements VistaItemsOpsProgramacionService {
	
	@Autowired
	private VistaItemsOpsProgramacionRepository vistaItemsOpsProgramacionRepo;
	
	@Autowired
	private PrioridadRepository prioridadRepo;
	
	@Autowired
	private PrioridadSpecification filtroPrioridad;

	@Override
	public List<VistaItemsOpsProgramacion> obtenerItemsOps() {
		return vistaItemsOpsProgramacionRepo.findAllItemsToMake();
	}

	@Override
	public void actualizaPrioridad(Long idItem, Integer prioridad) {
		Prioridad prioridadFound = prioridadRepo.findByIdItem(idItem);
		if(prioridadFound != null) {
			prioridadFound.setItemPrioridad(prioridad);
			prioridadRepo.updatePrioridad(prioridadFound);
			return;
		}
		Prioridad nuevo = new Prioridad();
		nuevo.setIdItem(idItem);
		nuevo.setItemPrioridad(prioridad);
		prioridadRepo.crearPrioridad(nuevo);
		
	}

	@Override
	public Page<VistaItemsOpsProgramacion> obtenerItemsOpsPaginados(int page, int size, String sortDir, String sortField, PrioridadFilterDTO filter) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortField));
		Page<VistaItemsOpsProgramacion> prioridades = null;
		try {
				prioridades = vistaItemsOpsProgramacionRepo.findAll(filtroPrioridad.getItemsPrioridades(filter), pageable);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prioridades;
	}

}
