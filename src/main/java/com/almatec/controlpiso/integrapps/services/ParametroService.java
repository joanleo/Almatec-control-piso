package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.ParametroDTO;
import com.almatec.controlpiso.integrapps.entities.Parametro;

public interface ParametroService {

	List<Parametro> obtenerParametros();

	List<ParametroDTO> obtenerParametrosDTO();

	void guardarParametros(List<ParametroDTO> parametrosDTO);

	Integer buscarCantidadesFabricadasPerfil(Long id, Integer getid_parte, Integer idCentroTrabajo);

	Integer buscarCantidadesFabricadasConjunto(Long id, Integer idItemFab, Integer idCentroTrabajo);

}
