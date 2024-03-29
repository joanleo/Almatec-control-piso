package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;

public interface VistaItemLoteDisponibleService {

	List<VistaItemLoteDisponible> searchItems(SpecItemLoteDTO filtro);

}
