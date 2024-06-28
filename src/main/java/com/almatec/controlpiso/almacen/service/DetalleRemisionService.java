package com.almatec.controlpiso.almacen.service;

import java.util.List;

import com.almatec.controlpiso.almacen.interfaces.DetalleRemisionInterface;

public interface DetalleRemisionService {

	List<DetalleRemisionInterface> obtenerDetallesPorIdRemision(Long idRemision);

}
