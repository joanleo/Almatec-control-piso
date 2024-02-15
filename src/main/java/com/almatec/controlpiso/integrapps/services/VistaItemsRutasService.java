package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;

public interface VistaItemsRutasService {

	List<OpCentroTrabajoDTO> buscarOpCT(Integer idCT);

}
