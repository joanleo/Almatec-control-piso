package com.almatec.controlpiso.integrapps.services;

import java.util.Set;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.PiezaCTProcesoDTO;

public interface VistaPiezasOperariosService {

	Set<OpCentroTrabajoDTO> obtenerPiezasOperarioProceso(OperarioDTO operario);

	Set<PiezaCTProcesoDTO> obtenerPiezasCentroTrabajoProceso(Integer idCT, Integer idConfig);

}
