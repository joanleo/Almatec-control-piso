package com.almatec.controlpiso.integrapps.services;


import com.almatec.controlpiso.integrapps.entities.RegistroPieza;

public interface RegistroPiezaService {

	RegistroPieza consultaRegistroPieza(Integer idCT, Integer idProceso, Integer idOperario, Integer idPieza);

	void actualizarRegistro(RegistroPieza registro);

}
