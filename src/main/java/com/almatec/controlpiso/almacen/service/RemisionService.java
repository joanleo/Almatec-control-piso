package com.almatec.controlpiso.almacen.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.almatec.controlpiso.almacen.interfaces.EncabezadoRemision;
import com.almatec.controlpiso.integrapps.entities.Remision;

public interface RemisionService {

	Remision guardarRemision(Remision remision);

	List<EncabezadoRemision> obtenerTodasLasRemisiones();

	EncabezadoRemision obtenerEncabezadoRemisionPorId(Long idRemision);

	Page<EncabezadoRemision> obtenerRemisionesPaginadas(int page, int size);

}
