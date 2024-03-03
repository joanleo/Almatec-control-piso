package com.almatec.controlpiso.integrapps.services;

import java.util.List;
import java.util.Set;

import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;

public interface VistaItemsRutasService {

	Set<OpCentroTrabajoDTO> buscarOpCT(Integer idCT);

	List<OpCentroTrabajoDTO> buscarOp(Integer idOp);

	Set<OpCentroTrabajoDTO> buscarItemCt(Long idItem, Integer idCT);

}
