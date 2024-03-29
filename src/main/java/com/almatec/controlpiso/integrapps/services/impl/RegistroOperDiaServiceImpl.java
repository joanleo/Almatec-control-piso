package com.almatec.controlpiso.integrapps.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.entities.RegistroOperDia;
import com.almatec.controlpiso.integrapps.repositories.RegistroOperDiaRepository;
import com.almatec.controlpiso.integrapps.services.RegistroOperDiaService;

@Transactional
@Service
public class RegistroOperDiaServiceImpl implements RegistroOperDiaService {
	
	@Autowired
	private RegistroOperDiaRepository registroOperRepo;
	
	private String isOperarioRegistradoOtroCt(OperarioDTO operarioDTO) {
		System.out.println("Validando si esta activo en otro ct");
		Date fechaActual = new Date();
		List<String> registros = registroOperRepo.findOperariosCT(operarioDTO, fechaActual);
		for(String registro: registros) {
			System.out.println(registro);
		}
		return registros.get(0);
	}

	@Override
	public String agregarRetirarOperario(OperarioDTO operarioDTO) {
		
		Date fechaActual = new Date();
		String otroCt = isOperarioRegistradoOtroCt(operarioDTO);
		if(otroCt != null) {
			return "ya se encuentra registrado en el centro de trabajo " + otroCt;
		}
		RegistroOperDia registroOperario = registroOperRepo.findByIdCentroTAndFecha(operarioDTO, fechaActual);
		System.out.println(operarioDTO);
		System.out.println(registroOperario);
		if(registroOperario == null) {
			Date fecha = new Date();
			registroOperRepo.agregarOperario(operarioDTO, fecha);
			return "agregado exitosamente";				
		}
		if(Objects.equals(registroOperario.getIdCentroT(), operarioDTO.getIdCentroTrabajo()) 
				&& Objects.equals(registroOperario.getIdConfigProceso(), operarioDTO.getIdConfigProceso())) {
			Boolean estado = (registroOperario.getIsActivo());
			System.out.println("Estado operario registro NEGADO: "+ !estado);
			System.out.println("Actualizando estado del operario...");
			registroOperRepo.actualizaEstadoOperario(operarioDTO, !estado);
			System.out.println("Estado del operario actualizado correctamente.");
			return estado ? "retirado exitosamente" : "agregado exitosamente";
		}
		throw new ResourceNotFoundException("No se encontro el Operario con id " + operarioDTO.getIdOperario() + " en el centro de trabajo " + operarioDTO.getIdCentroTrabajo() );
	}

	@Override
	public List<RegistroOperDia> findByIdCentroTAndIdConfigProceso(Integer idCT, Integer idConfigP) {
		return registroOperRepo.findByIdCentroTAndIdConfigProcesoAndIsActivoTrue(idCT, idConfigP);
	}

	@Override
	public RegistroOperDia obtenerRegistroOperario(Integer idCT, Integer idProceso, Integer idOperario) {
		return registroOperRepo.findByIdCentroTAndIdConfigProcesoAndIdOperario(idCT, idProceso, idOperario);
	}

	@Override
	public void actualizaParada(RegistroOperDia registroOperario, int idParada) {
		registroOperRepo.actualizaParada(registroOperario, idParada);
		
	}

	

}
