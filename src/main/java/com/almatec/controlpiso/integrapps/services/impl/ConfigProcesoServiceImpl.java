package com.almatec.controlpiso.integrapps.services.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ConfigProcesoDTO;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.entities.ConfigProceso;
import com.almatec.controlpiso.integrapps.repositories.ConfigProcesoRepository;
import com.almatec.controlpiso.integrapps.services.ConfigProcesoService;

@Transactional
@Service
public class ConfigProcesoServiceImpl implements ConfigProcesoService {
	
	@Autowired
	private ConfigProcesoRepository configProcesoRepo;

	@Override
	public List<ConfigProceso> buscarProcesosConfig() {
		return configProcesoRepo.findAll();
	}

	@Transactional
	@Override
	public ConfigProcesoDTO configProceso(Integer idCentroTrabajo, Long idTurno){
		LocalDate fecha = LocalDate.now();

		ConfigProceso config = configProcesoRepo.obtenerConfiguracionCentroTrabajoTurno(idCentroTrabajo, idTurno, fecha);
		if(config == null) {
			configProcesoRepo.guardarConfig(idCentroTrabajo, idTurno);
			config = configProcesoRepo.obtenerConfiguracionCentroTrabajoTurno(idCentroTrabajo, idTurno, fecha);
			return new ConfigProcesoDTO(config);
		}
		ConfigProcesoDTO envio = new ConfigProcesoDTO(config);
		return envio;
		
	}

	@Override
	public ResponseMessage finalizarTurno(Integer idConfigProceso) {

		try {
			configProcesoRepo.updateIsActivo(idConfigProceso, 0);
			return new ResponseMessage("Turno finalizado");
		}catch (Exception e) {
			return new ResponseMessage(true, "Ocurrio un error al tratar de finalizar el turno, " + e);
		}
	}

	@Override
	public List<ConfigProceso> obtenerConfigProcesosDia() {
		LocalDate fecha = LocalDate.now();
		List<ConfigProceso> configs = configProcesoRepo.findByFechaConfiguracion(fecha);
		return configs;
	}

}
