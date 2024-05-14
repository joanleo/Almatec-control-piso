package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;

public class SolicitudDTO {
	
	private SolicitudMateriaPrima solicitud;
	private String usuarioSol;
	
	public SolicitudDTO() {
		super();
	}

	public SolicitudDTO(SolicitudMateriaPrima sol, String usuarioSol) {
		this.solicitud = sol;
		this.usuarioSol = usuarioSol;
	}

	public SolicitudMateriaPrima getSolicitud() {
		return solicitud;
	}

	public String getUsuarioSol() {
		return usuarioSol;
	}

	public void setSolicitud(SolicitudMateriaPrima solicitud) {
		this.solicitud = solicitud;
	}

	public void setUsuarioSol(String usuarioSol) {
		this.usuarioSol = usuarioSol;
	}

	@Override
	public String toString() {
		return "SolicitudDTO [solicitud=" + solicitud + ", usuarioSol=" + usuarioSol + "]";
	}
	
	
	
	
}
