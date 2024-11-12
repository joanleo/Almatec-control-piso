package com.almatec.controlpiso.almacen.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.almatec.controlpiso.almacen.dto.SolicitudDTO;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;

public interface SolicitudMateriaPrimaService {

	Integer obtenerConsecutivo();

	SolicitudMateriaPrima crearSolicitud(SolicitudMateriaPrima solicitud);

	void actualizaSolicitud(SolicitudMateriaPrima solicitud);

	List<SolicitudDTO> obtenerSolicitudesPendientes();

	SolicitudMateriaPrima obtenerSolicitudPorId(Integer idSol);

	Integer obtenerCodErpPorLote(String lote);

	String obtenerIdctErp(Integer idCentroTrabajo);

	void rechazarSolicitud(Integer idSol);
	
	List<SolicitudDTO> obtenerSolicitudes();

	Page<SolicitudDTO> obtenerSolicitudesPendientesPaginadas(int page, int size);

	Page<SolicitudDTO> obtenerTodasSolicitudesPaginadas(int page, int size);

}
