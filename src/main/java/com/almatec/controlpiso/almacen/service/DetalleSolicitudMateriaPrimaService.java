package com.almatec.controlpiso.almacen.service;

import java.util.List;

import com.almatec.controlpiso.almacen.dto.DetalleSolicitudDTO;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;

public interface DetalleSolicitudMateriaPrimaService {

	List<DetalleSolicitudMateriaPrima> crearDetalleSolicitud(Integer numDoc, List<DetalleSolicitudMateriaPrima> detalles);

	List<DetalleSolicitudDTO> obtenerDetalleDTOPorIdSolic(Integer idSolic);

	List<DetalleSolicitudMateriaPrima> obtenerDetallePorIdSol(Integer idSol);

	List<DetalleSolicitudMateriaPrima> actualizarDetalleSol(List<DetalleSolicitudMateriaPrima> detalleSol);

}
