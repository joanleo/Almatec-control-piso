package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;
import com.almatec.controlpiso.integrapps.repositories.VistaItemLoteDisponibleRepository;
import com.almatec.controlpiso.integrapps.services.VistaItemLoteDisponibleService;
import com.almatec.controlpiso.integrapps.specifications.VistaItemLoteDisponibleSpecification;

@Service
public class VistaItemLoteDisponibleServiceImpl implements VistaItemLoteDisponibleService {
	
	@Autowired
	private VistaItemLoteDisponibleRepository vistaItemLoteDisponibleRepository;
	
	@Autowired
	private VistaItemLoteDisponibleSpecification filter;

	@Override
	public List<VistaItemLoteDisponible> searchItems(SpecItemLoteDTO filtro, boolean transferencia) {
		return vistaItemLoteDisponibleRepository.findAll(filter.getDisponibilidad(filtro, transferencia));
	}
	
	@Override
    public Page<VistaItemLoteDisponible> searchItems(SpecItemLoteDTO filtro, boolean transferencia, Pageable pageable) {
        return vistaItemLoteDisponibleRepository.findAll(filter.getDisponibilidad(filtro, transferencia), pageable);
    }

}
