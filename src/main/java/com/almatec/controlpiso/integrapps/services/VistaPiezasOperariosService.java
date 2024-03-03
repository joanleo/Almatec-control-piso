package com.almatec.controlpiso.integrapps.services;

import java.util.Set;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;

public interface VistaPiezasOperariosService {

	Set<OpCentroTrabajoDTO> obtenerPiezasOperarioProceso(OperarioDTO operario);

}
