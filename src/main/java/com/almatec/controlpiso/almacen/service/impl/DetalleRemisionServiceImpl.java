package com.almatec.controlpiso.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.interfaces.DetalleRemisionInterface;
import com.almatec.controlpiso.almacen.service.DetalleRemisionService;
import com.almatec.controlpiso.integrapps.repositories.DetalleRemisionRepository;

@Service
public class DetalleRemisionServiceImpl implements DetalleRemisionService {
	
	@Autowired
	private DetalleRemisionRepository detalleRemisionRepo;

	@Override
	public List<DetalleRemisionInterface> obtenerDetallesPorIdRemision(Long idRemision) {
		try {
			return detalleRemisionRepo.buscarDetallesPorIdRemision(idRemision);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

}
