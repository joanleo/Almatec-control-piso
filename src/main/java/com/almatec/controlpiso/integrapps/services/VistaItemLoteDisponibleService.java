package com.almatec.controlpiso.integrapps.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;
import com.almatec.controlpiso.produccion.dtos.LoteInfoDTO;

public interface VistaItemLoteDisponibleService {

	List<VistaItemLoteDisponible> searchItems(SpecItemLoteDTO filtro, boolean transferencia);

	Page<VistaItemLoteDisponible> searchItems(SpecItemLoteDTO filtro, boolean transferencia, Pageable pageable);

	Map<String, LoteInfoDTO> buscarDisponibilidadBatch(Set<String> lotesErp, String string);

}
