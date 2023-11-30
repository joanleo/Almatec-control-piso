package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.dtos.Compania;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.interfaces.CentroOperacionInterface;
import com.almatec.controlpiso.integrapps.interfaces.CompaniaErp;
import com.almatec.controlpiso.integrapps.repositories.CentroTrabajoRepository;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;

@Service
public class CentroTrabajoServiceImpl implements CentroTrabajoService {
	
	@Autowired
	private CentroTrabajoRepository centroTrabajoRepo;

	@Override
	public List<Compania> buscarCompanias() {
		List<CompaniaErp> companiasErp = centroTrabajoRepo.buscarCompanias();
		List<Compania> companias = new ArrayList<>();
		for(CompaniaErp companiaErp: companiasErp) {
			Compania compania= new Compania(companiaErp);
			companias.add(compania);
		}
		return companias;
	}

	@Override
	public List<CentroTrabajo> buscarCentrosTrabajo(Integer cia) {
		return centroTrabajoRepo.findByIdCia(cia);
	}

	@Override
	public CentroTrabajo buscarCentroTrabajo(Long id) {
		return centroTrabajoRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro el vendedor"));
	}

	@Override
	public List<CentroOperacion> buscarCentrosOperacion(Integer cia) {
		List<CentroOperacionInterface> centrosOperacionInterface = centroTrabajoRepo.buscarCentrosOperacion(cia);
		List<CentroOperacion> centrosOperacion = new ArrayList<>();
		for(CentroOperacionInterface centroOperacionInterface: centrosOperacionInterface) {
			CentroOperacion centroOperacion = new CentroOperacion(centroOperacionInterface);
			centrosOperacion.add(centroOperacion);
		}
		return centrosOperacion;
	}

	@Transactional
	@Override
	public void guardar(CentroTrabajo centroTrabajo) {
		centroTrabajoRepo.save(centroTrabajo);
		
	}

}
