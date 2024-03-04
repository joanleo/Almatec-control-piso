package com.almatec.controlpiso.integrapps.services.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.RegistroParadaDTO;
import com.almatec.controlpiso.integrapps.entities.RegistroParada;
import com.almatec.controlpiso.integrapps.repositories.RegistroParadaRepository;
import com.almatec.controlpiso.integrapps.services.RegistroParadaService;

@Transactional
@Service
public class RegistroParadaServiceImpl implements RegistroParadaService {
	
	@Autowired
	private RegistroParadaRepository registroParadaRepository;

	@Override
	public ErrorMensaje registrarActualizarParada(RegistroParadaDTO registroParadaDTO) {
		System.out.println("Service: Buscando registro parada");
		RegistroParada registroParada = registroParadaRepository.findByIdConfigProcesoAndIdOperarioAndIdParada(
				registroParadaDTO.getIdConfigProceso(), registroParadaDTO.getIdOperario(), registroParadaDTO.getIdParada());
		LocalDateTime fecha = LocalDateTime.now();
 
		if(registroParada == null) {
			System.out.println("No existe parada");
			registroParada = new RegistroParada();
			registroParada.setIdConfigProceso(registroParadaDTO.getIdConfigProceso());
			registroParada.setIdOperario(registroParadaDTO.getIdOperario());
			registroParada.setIdParada(registroParadaDTO.getIdParada());
			registroParada.setFechaCreacion(fecha);
			try {
				System.out.println("Guardando parada");
				registroParadaRepository.save(registroParada);
				System.out.println("Parada registrada exitosamente");
				return new ErrorMensaje("Parada registrada exitosamente");
			} catch (Exception e) {
				System.out.println(e);
				return new ErrorMensaje(true, "Ocurrio un error al tratar de registrar la parada");
			}
		}
		registroParada.setIsActivo(false);
		registroParada.setFechaEdicion(fecha);
		try {
			registroParadaRepository.save(registroParada);
			return new ErrorMensaje("Parada finalizada exitosamente");
		} catch (Exception e) {
			return new ErrorMensaje(true, "Ocurrio un error al tratar de finalizar la parada");
		}
	}

}
