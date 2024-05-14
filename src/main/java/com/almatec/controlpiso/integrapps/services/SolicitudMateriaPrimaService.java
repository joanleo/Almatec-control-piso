package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.SolicitudDTO;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;

public interface SolicitudMateriaPrimaService {

	Integer obtenerConsecutivo();

	SolicitudMateriaPrima crearSolicitud(SolicitudMateriaPrima solicitud);

	void actualizaSolicitud(SolicitudMateriaPrima solicitud);

	List<SolicitudDTO> obtenerSolicitudes();

	SolicitudMateriaPrima obtenerSolicitudPorId(Integer idSol);

	Integer obtenerCodErpPorLote(String lote);

	String obtenerIdctErp(Integer idCentroTrabajo);

}
