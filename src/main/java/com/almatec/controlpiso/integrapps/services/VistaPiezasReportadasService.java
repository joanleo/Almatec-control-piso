package com.almatec.controlpiso.integrapps.services;

import java.util.Set;


import com.almatec.controlpiso.programacion.dtos.OrdenProduccionResumen;

public interface VistaPiezasReportadasService {

	Set<OrdenProduccionResumen> buscarOps(Integer idCT);

}
