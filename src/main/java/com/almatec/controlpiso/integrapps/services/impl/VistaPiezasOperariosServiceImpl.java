package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasOperarios;
import com.almatec.controlpiso.integrapps.repositories.VistaPiezasOperariosRepository;
import com.almatec.controlpiso.integrapps.services.VistaPiezasOperariosService;

@Service
public class VistaPiezasOperariosServiceImpl implements VistaPiezasOperariosService {
	
	@Autowired
	private VistaPiezasOperariosRepository vistaPiezasOperariosRepository;

	@Override
	public List<VistaPiezasOperarios> obtenerPiezasOperarioProceso(OperarioDTO operario) {
		List<VistaPiezasOperarios> lista = vistaPiezasOperariosRepository.findPiezasOperariosProceso(operario);
		
		return lista;
		
	}


    
    @Override
    public List<VistaPiezasOperarios> obtenerPiezasCentroTrabajoProceso(Integer idCT, Integer idConfig) {
		List<VistaPiezasOperarios> lista = vistaPiezasOperariosRepository.findPiezasOperariosProceso(idCT, idConfig);

		return lista;
    }

}
