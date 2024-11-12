package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.ConfigProcesoDTO;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.entities.ConfigProceso;

public interface ConfigProcesoService {

	List<ConfigProceso> buscarProcesosConfig();

	ConfigProcesoDTO configProceso(Integer idCentroTrabajo, Long idTurno);

	ResponseMessage finalizarTurno(Integer idConfigProceso);

	List<ConfigProceso> obtenerConfigProcesosDia();

}
