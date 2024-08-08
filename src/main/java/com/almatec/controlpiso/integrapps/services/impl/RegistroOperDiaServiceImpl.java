package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioRegistradoDTO;
import com.almatec.controlpiso.integrapps.entities.RegistroOperDia;
import com.almatec.controlpiso.integrapps.interfaces.OperarioRegistrado;
import com.almatec.controlpiso.integrapps.repositories.RegistroOperDiaRepository;
import com.almatec.controlpiso.integrapps.services.RegistroOperDiaService;

@Transactional
@Service
public class RegistroOperDiaServiceImpl implements RegistroOperDiaService {
	
	@Autowired
	private RegistroOperDiaRepository registroOperRepo;
	
	private String isOperarioRegistradoOtroCt(OperarioDTO operarioDTO) {
		Date fechaActual = new Date();
		List<String> registros = registroOperRepo.findOperariosCT(operarioDTO, fechaActual);		
		return registros.isEmpty() ? null : registros.get(0);
	}

	@Override
	public String agregarRetirarOperario(OperarioDTO operarioDTO) {
		
		Date fechaActual = new Date();
		String otroCt = isOperarioRegistradoOtroCt(operarioDTO);
		if(otroCt != null) {
			return "ya se encuentra registrado en el centro de trabajo " + otroCt;
		}
		RegistroOperDia registroOperario = registroOperRepo.findByIdCentroTAndFecha(operarioDTO, fechaActual);
		if(registroOperario == null) {
			Date fecha = new Date();
			registroOperRepo.agregarOperario(operarioDTO, fecha);
			return "agregado exitosamente";				
		}
		if(Objects.equals(registroOperario.getIdCentroT(), operarioDTO.getIdCentroTrabajo()) 
				&& Objects.equals(registroOperario.getIdConfigProceso(), operarioDTO.getIdConfigProceso())) {
			Boolean estado = (registroOperario.getIsActivo());
			registroOperRepo.actualizaEstadoOperario(operarioDTO, !estado);
			return Boolean.TRUE.equals(estado) ? "retirado exitosamente" : "agregado exitosamente";
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

	@Override
	public List<OperarioRegistradoDTO> obtenerOperariosRegistrados() {
		Date fecha = new Date();
		List<OperarioRegistrado> listOperariosInterface = registroOperRepo.findByFechaCreacion(fecha);
		List<OperarioRegistradoDTO> operarios = new ArrayList<>();
		for(OperarioRegistrado oper: listOperariosInterface) {
			OperarioRegistradoDTO operario = new OperarioRegistradoDTO(oper);
			operarios.add(operario);
		}
		
		return operarios;
	}

	

}
