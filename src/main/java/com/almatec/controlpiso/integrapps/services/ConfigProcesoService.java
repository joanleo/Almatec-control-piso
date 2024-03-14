package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.ConfigProcesoDTO;
import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.entities.ConfigProceso;

public interface ConfigProcesoService {

	List<ConfigProceso> buscarProcesosConfig();

	ConfigProcesoDTO configProceso(Integer idCentroTrabajo, Long idTurno);

	ErrorMensaje finalizarTurno(Integer idConfigProceso);

}
