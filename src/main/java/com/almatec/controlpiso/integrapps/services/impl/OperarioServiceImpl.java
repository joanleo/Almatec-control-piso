package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.repositories.OperarioRepository;
import com.almatec.controlpiso.integrapps.services.OperarioService;
import com.almatec.controlpiso.utils.dto.OperarioBarCodeDTO;

@Service
public class OperarioServiceImpl  implements OperarioService{
	
	@Autowired
	private OperarioRepository operarioRepo;

	@Override
	public Operario obtenerOperario(Integer numCedula) {
		Integer idOperario = operarioRepo.obtenerIdOperario(String.valueOf(numCedula));
		if (idOperario == null) {
            throw new ResourceNotFoundException("No se encontró operario con la cédula: " + numCedula);
        }
		return operarioRepo.findById(idOperario)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro operario con la cedula: " + numCedula));
	}

	@Override
	public Operario buscarOperarioPorId(Integer idOperario) {
		return operarioRepo.findById(idOperario)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro operario con el id: " + idOperario));
	}

	@Override
	public List<OperarioBarCodeDTO> obtenerDataBarCodeOperarios() {
		
		List<Object[]> results = operarioRepo.buscarDataBarCodeOperarios();
        
        return results.stream()
            .map(result -> new OperarioBarCodeDTO(
                (String) result[0],  // A_Operario_Nombre
                (String) result[1]   // Barcode
            ))
            .collect(Collectors.toList());

	}
}
