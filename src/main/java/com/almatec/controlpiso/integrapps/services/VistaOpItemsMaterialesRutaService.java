package com.almatec.controlpiso.integrapps.services;

import java.util.Set;

import org.springframework.data.domain.Page;

import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOpItemsMaterialesRuta;
import com.almatec.controlpiso.programacion.dtos.PrioridadFilterDTO;
import com.almatec.controlpiso.programacion.dtos.VistaOpItemsMaterialesRutaDTO;

public interface VistaOpItemsMaterialesRutaService {

	Set<OpCentroTrabajoDTO> buscarOpCt(Integer idCT);

	Set<OpCentroTrabajoDTO> buscarItemCt(Long idItem, Integer idCT);

	Page<VistaOpItemsMaterialesRuta> obtenerItemsOpsPaginados(int page, int size, String sortDir, String sortField,
			PrioridadFilterDTO filtro);

}
