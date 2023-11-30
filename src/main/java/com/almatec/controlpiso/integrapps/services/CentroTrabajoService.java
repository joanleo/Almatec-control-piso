package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.dtos.Compania;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;

public interface CentroTrabajoService {

	List<Compania> buscarCompanias();

	List<CentroTrabajo> buscarCentrosTrabajo(Integer integer);

	CentroTrabajo buscarCentroTrabajo(Long id);

	List<CentroOperacion> buscarCentrosOperacion(Integer cia);

	void guardar(CentroTrabajo centroTrabajo);

}
