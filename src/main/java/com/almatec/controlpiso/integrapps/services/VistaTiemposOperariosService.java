package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.TiemposOperariosDTO;
import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;

public interface VistaTiemposOperariosService {

	List<TiemposOperariosDTO> obtenerTiemposOperarios(Integer idProceso);

}
