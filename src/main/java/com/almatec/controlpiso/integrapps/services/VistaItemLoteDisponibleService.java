package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;

public interface VistaItemLoteDisponibleService {

	List<VistaItemLoteDisponible> searchItems(SpecItemLoteDTO filtro, boolean transferencia);

	Page<VistaItemLoteDisponible> searchItems(SpecItemLoteDTO filtro, boolean transferencia, Pageable pageable);

}
