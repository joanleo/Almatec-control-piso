package com.almatec.controlpiso.integrapps.services;

import org.springframework.data.domain.Page;

import com.almatec.controlpiso.calidad.dtos.ReporteCalidadDTO;
import com.almatec.controlpiso.integrapps.entities.ReporteCalidad;

public interface ReporteCalidadService {

	ReporteCalidad guardarReporteCalidad(ReporteCalidadDTO formCalidad);

	ReporteCalidadDTO buscarItemReporteCalidadCt(Long idItem, Integer idCT, Integer idOperario);

	Page<ReporteCalidadDTO> listarFormularios(int page, int size, String search);

	ReporteCalidadDTO obtenerFormularioPorId(Long id);

}