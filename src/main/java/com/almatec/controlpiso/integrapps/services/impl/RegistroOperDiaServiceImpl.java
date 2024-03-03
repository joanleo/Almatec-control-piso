package com.almatec.controlpiso.integrapps.services.impl;

import java.util.Date;
import java.util.List;

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
	
	private Boolean isOperarioRegistradoOtroCt(OperarioDTO operarioDTO) {
		System.out.println("Validando si esta activo en otro ct");
		Date fechaActual = new Date();
		List<RegistroOperDia> registros = registroOperRepo.findOperariosCT(operarioDTO, fechaActual);
		return registros != null && !registros.isEmpty();
	}

	@Override
	public String agregarRetirarOperario(OperarioDTO operarioDTO) {
		
		Date fechaActual = new Date();
		if(Boolean.TRUE.equals(isOperarioRegistradoOtroCt(operarioDTO))) {
			return "se encuentra registrado en otro centro de trabajo";
		}
		RegistroOperDia registroOperario = registroOperRepo.findByIdCentroTAndFecha(operarioDTO, fechaActual);
		System.out.println(registroOperario);
		if(registroOperario == null) {
			Date fecha = new Date();
			registroOperRepo.agregarOperario(operarioDTO, fecha);
			return "agregado exitosamente";				
		}
		if(registroOperario.getIdCentroT() == operarioDTO.getIdCentroTrabajo() && registroOperario.getIdConfigProceso() == operarioDTO.getIdConfigProceso()) {
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

	

}
