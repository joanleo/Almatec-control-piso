package com.almatec.controlpiso.integrapps.dtos;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;

public class SolicitudMariaPrimaDTO {
	
	private SolicitudMateriaPrima solicitud;
	private List<DetalleSolicitudMateriaPrima> detalles;
	private String nombreUsuario;
	
	public SolicitudMariaPrimaDTO() {
		super();
	}

	public SolicitudMateriaPrima getSolicitud() {
		return solicitud;
	}

	public List<DetalleSolicitudMateriaPrima> getDetalles() {
		return detalles;
	}

	public void setSolicitud(SolicitudMateriaPrima solicitud) {
		this.solicitud = solicitud;
	}

	public void setDetalles(List<DetalleSolicitudMateriaPrima> detalles) {
		this.detalles = detalles;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@SuppressWarnings("unused")
	private void addDetalle(DetalleSolicitudMateriaPrima detalle) {
		this.detalles.add(detalle);
	}

	@Override
	public String toString() {
		return "SolicitudMariaPrimaDTO [solicitud=" + solicitud + ", detalles=" + detalles + "]";
	}

}
