package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import javax.validation.Valid;

import com.almatec.controlpiso.integrapps.entities.Parada;
import com.almatec.controlpiso.produccion.dtos.ParadaDTO;

public interface ParadaService {

	List<Parada> obtenerParadas();

	Parada crear(@Valid ParadaDTO paradaDTO, String usuarioCrea);

	Parada actualizar(Long id, @Valid ParadaDTO paradaDTO, String usuarioEdita);

	void eliminar(Long id);

	Parada obtenerPorId(Long id);

}
