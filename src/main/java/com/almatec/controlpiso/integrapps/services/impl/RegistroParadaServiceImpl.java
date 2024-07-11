package com.almatec.controlpiso.integrapps.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.InfoParadaDTO;
import com.almatec.controlpiso.integrapps.dtos.RegistroParadaDTO;
import com.almatec.controlpiso.integrapps.entities.RegistroOperDia;
import com.almatec.controlpiso.integrapps.entities.RegistroParada;
import com.almatec.controlpiso.integrapps.interfaces.InfoParada;
import com.almatec.controlpiso.integrapps.repositories.RegistroOperDiaRepository;
import com.almatec.controlpiso.integrapps.repositories.RegistroParadaRepository;
import com.almatec.controlpiso.integrapps.services.RegistroParadaService;

@Transactional
@Service
public class RegistroParadaServiceImpl implements RegistroParadaService {
	
	@Autowired
	private RegistroParadaRepository registroParadaRepository;
	
	@Autowired
	private RegistroOperDiaRepository registroOperDiaRepo;

	@Override
	public ErrorMensaje registrarActualizarParada(RegistroParadaDTO registroParadaDTO, Integer idCT) {
		RegistroParada registroParada = registroParadaRepository.findByIdConfigProcesoAndIdOperarioAndIdParada(
				registroParadaDTO.getIdConfigProceso(), registroParadaDTO.getIdOperario(), registroParadaDTO.getIdParada());
		LocalDateTime fecha = LocalDateTime.now();
 
		RegistroOperDia registroOperario = registroOperDiaRepo.findByIdCentroTAndIdConfigProcesoAndIdOperario(idCT, registroParadaDTO.getIdConfigProceso(), registroParadaDTO.getIdOperario());
		registroOperario.setFechaEdicion(fecha);
		if(registroParada == null) {
			registroParada = new RegistroParada();
			registroParada.setIdConfigProceso(registroParadaDTO.getIdConfigProceso());
			registroParada.setIdOperario(registroParadaDTO.getIdOperario());
			registroParada.setIdParada(registroParadaDTO.getIdParada());
			registroParada.setFechaCreacion(fecha);
			registroOperario.setIdParada(registroParadaDTO.getIdParada().intValue());
			try {
				registroParadaRepository.save(registroParada);
				registroOperDiaRepo.save(registroOperario);
				return new ErrorMensaje("Parada registrada exitosamente");
			} catch (Exception e) {
				e.printStackTrace();;
				return new ErrorMensaje(true, "Ocurrio un error al tratar de registrar la parada");
			}
		}else if(registroParada != null && !registroParada.getIsActivo()) {
			registroParada.setIsActivo(true);
			registroParada.setFechaEdicion(fecha);
			registroOperario.setIdParada(registroParadaDTO.getIdParada().intValue());
			try {
				registroParadaRepository.save(registroParada);
				registroOperDiaRepo.save(registroOperario);
				return new ErrorMensaje("Parada registrada exitosamente");
			} catch (Exception e) {
				e.printStackTrace();
				return new ErrorMensaje(true, "Ocurrio un error al tratar de registrar la parada");
			}
		}
		registroParada.setIsActivo(false);
		registroParada.setFechaEdicion(fecha);
		registroOperario.setIdParada(0);
		try {
			registroParadaRepository.save(registroParada);
			registroOperDiaRepo.save(registroOperario);
			return new ErrorMensaje("Parada finalizada exitosamente");
		} catch (Exception e) {
			return new ErrorMensaje(true, "Ocurrio un error al tratar de finalizar la parada");
		}
	}

	@Override
	public List<InfoParadaDTO> obtenerInfoParadasCT(Integer idConfigProceso) {
		List<InfoParada> listaInterface = registroParadaRepository.obtenerInfoParadasCT(idConfigProceso);
		List<InfoParadaDTO> lista = new ArrayList<>();
		for(InfoParada registro: listaInterface) {
			InfoParadaDTO parada = new InfoParadaDTO(registro);
			lista.add(parada);
		}
		return lista;
	}

}
