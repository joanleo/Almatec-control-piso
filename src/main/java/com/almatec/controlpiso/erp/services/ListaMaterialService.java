package com.almatec.controlpiso.erp.services;

import java.util.List;

import com.almatec.controlpiso.erp.entities.ListaMaterial;

public interface ListaMaterialService {
	
	List<ListaMaterial> obtenerListaActual(Integer f820_id);

}
