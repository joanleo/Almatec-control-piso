package com.almatec.controlpiso.erp.services;

import java.util.List;

import com.almatec.controlpiso.erp.entities.ListaMaterial;
import com.almatec.controlpiso.erp.interfaces.RutaInterface;

public interface ListaMaterialService {
	
	List<ListaMaterial> obtenerListaActual(Integer f820_id);

	List<RutaInterface> obtenerRutasActual(String f808_id);

}
