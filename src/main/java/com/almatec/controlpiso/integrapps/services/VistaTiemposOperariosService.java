package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;

public interface VistaTiemposOperariosService {

	List<VistaTiemposOperarios> obtenerTiemposOperarios(Integer idProceso);

}
