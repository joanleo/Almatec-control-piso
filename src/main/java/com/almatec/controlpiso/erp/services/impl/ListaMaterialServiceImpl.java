package com.almatec.controlpiso.erp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.entities.ListaMaterial;
import com.almatec.controlpiso.erp.interfaces.RutaInterface;
import com.almatec.controlpiso.erp.repositories.ListaMaterialRepository;
import com.almatec.controlpiso.erp.services.ListaMaterialService;

@Service
public class ListaMaterialServiceImpl implements ListaMaterialService {

	@Autowired
	private ListaMaterialRepository listaMaterialRepo;

	@Override
	public List<ListaMaterial> obtenerListaActual(Integer f820_id) {
		List<ListaMaterial> lista = listaMaterialRepo.obtenerListaActual(f820_id);
		return lista;
	}

	@Override
	public List<RutaInterface> obtenerRutasActual(String f808_id) {
		List<RutaInterface> rutas = listaMaterialRepo.obtenerRutas(f808_id);
		return rutas;
	}
}
