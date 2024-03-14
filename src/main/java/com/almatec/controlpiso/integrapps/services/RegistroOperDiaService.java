package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.entities.RegistroOperDia;

public interface RegistroOperDiaService {

	String agregarRetirarOperario(OperarioDTO operarioDTO);

	List<RegistroOperDia> findByIdCentroTAndIdConfigProceso(Integer idCT, Integer idConfigP);

	void actualizaParada(RegistroOperDia registroOperario, int idParada);

	RegistroOperDia obtenerRegistroOperario(Integer idCT, Integer idProceso, Integer idOperario);


}
