package com.almatec.controlpiso.integrapps.services;

import java.util.List;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasOperarios;

public interface VistaPiezasOperariosService {

	List<VistaPiezasOperarios> obtenerPiezasOperarioProceso(OperarioDTO operario);

	List<VistaPiezasOperarios> obtenerPiezasCentroTrabajoProceso(Integer idCT, Integer idConfig);

}
