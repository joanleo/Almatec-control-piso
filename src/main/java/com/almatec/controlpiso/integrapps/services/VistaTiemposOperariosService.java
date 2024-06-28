package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.TiemposOperariosDTO;

public interface VistaTiemposOperariosService {

	List<TiemposOperariosDTO> obtenerTiemposOperarios(Integer idProceso);

}
