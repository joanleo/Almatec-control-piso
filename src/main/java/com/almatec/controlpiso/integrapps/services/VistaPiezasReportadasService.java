package com.almatec.controlpiso.integrapps.services;

import java.util.List;
import java.util.Set;

import com.almatec.controlpiso.integrapps.entities.VistaPiezasReportadas;
import com.almatec.controlpiso.programacion.dtos.OrdenProduccionResumen;

public interface VistaPiezasReportadasService {

	Set<OrdenProduccionResumen> buscarOps(Integer idCT);

	List<VistaPiezasReportadas> findByIdOpIntegrappsIn(List<Integer> listaIds);

}
