package com.almatec.controlpiso.integrapps.services;

import java.util.Set;

import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;

public interface VistaOpItemsMaterialesRutaService {

	Set<OpCentroTrabajoDTO> buscarOpCt(Integer idCT);

	Set<OpCentroTrabajoDTO> buscarItemCt(Long idItem, Integer idCT);

}
