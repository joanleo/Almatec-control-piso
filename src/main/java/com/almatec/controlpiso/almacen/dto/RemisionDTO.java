package com.almatec.controlpiso.almacen.dto;

import java.util.List;

public class RemisionDTO {

	private Long idRemision;
	private Integer idOpIa;
	private Integer idUsuario;
	private String observaciones;
	private List<DetalleRemisionDTO> detalles;
	
	public RemisionDTO() {
		super();
	}

	public Long getIdRemision() {
		return idRemision;
	}

	public void setIdRemision(Long idRemision) {
		this.idRemision = idRemision;
	}

	public Integer getIdOpIa() {
		return idOpIa;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public List<DetalleRemisionDTO> getDetalles() {
		return detalles;
	}

	public void setIdOpIa(Integer idOpIa) {
		this.idOpIa = idOpIa;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setDetalles(List<DetalleRemisionDTO> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "RemisionDTO [idRemision=" + idRemision + ", idOpIa=" + idOpIa + ", idUsuario=" + idUsuario
				+ ", observaciones=" + observaciones + ", detalles=" + detalles + "]";
	}	
	
}
