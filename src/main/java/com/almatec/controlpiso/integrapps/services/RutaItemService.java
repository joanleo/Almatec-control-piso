package com.almatec.controlpiso.integrapps.services;

import java.util.List;


import com.almatec.controlpiso.ingenieria.dtos.RutaItemDTO;
import com.almatec.controlpiso.integrapps.entities.RutaItem;

public interface RutaItemService {

	List<RutaItem> buscarRutaItem(Integer idItem);

	List<RutaItemDTO> obtenerRutaPorItem(Integer idItem);

}
