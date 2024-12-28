package com.almatec.controlpiso.integrapps.services;

import java.util.List;

public interface VistaOrdenPvService {

	List<Integer> obtenerCentrosTrabajoTep(Integer integer);

	void actualizarCentrosTep(Integer idOpIntegrapps, List<Integer> centrosTep);

}
