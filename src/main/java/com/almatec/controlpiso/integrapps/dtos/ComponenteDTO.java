package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;
import java.util.Objects;

public class ComponenteDTO {
	
	private Integer codErp;
	private Integer idPerfil;
	private String descripcionPerfil;
	private String cantListaMateriales;
	private Integer idCentroTrabajoPerfil;
	private String centroTrabajoPerfil;
	private BigDecimal longitud;
	
	public ComponenteDTO() {
		super();
	}

	public ComponenteDTO(Integer codErp, Integer idPerfil, String descripcionPerfil, String cantListaMateriales,
			Integer idCentroTrabajoPerfil, String centroTrabajoPerfil) {
		super();
		this.codErp = codErp;
		this.idPerfil = idPerfil;
		this.descripcionPerfil = descripcionPerfil;
		this.cantListaMateriales = cantListaMateriales;
		this.idCentroTrabajoPerfil = idCentroTrabajoPerfil;
		this.centroTrabajoPerfil = centroTrabajoPerfil;
	}

	public Integer getCodErp() {
		return codErp;
	}

	public void setCodErp(Integer codErp) {
		this.codErp = codErp;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescripcionPerfil() {
		return descripcionPerfil;
	}

	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}

	public String getCantListaMateriales() {
		return cantListaMateriales;
	}

	public void setCantListaMateriales(String cantListaMateriales) {
		this.cantListaMateriales = cantListaMateriales;
	}

	public Integer getIdCentroTrabajoPerfil() {
		return idCentroTrabajoPerfil;
	}

	public void setIdCentroTrabajoPerfil(Integer idCentroTrabajoPerfil) {
		this.idCentroTrabajoPerfil = idCentroTrabajoPerfil;
	}

	public String getCentroTrabajoPerfil() {
		return centroTrabajoPerfil;
	}

	public void setCentroTrabajoPerfil(String centroTrabajoPerfil) {
		this.centroTrabajoPerfil = centroTrabajoPerfil;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantListaMateriales, centroTrabajoPerfil, codErp, descripcionPerfil, idCentroTrabajoPerfil,
				idPerfil, longitud);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComponenteDTO other = (ComponenteDTO) obj;
		return Objects.equals(cantListaMateriales, other.cantListaMateriales)
				&& Objects.equals(centroTrabajoPerfil, other.centroTrabajoPerfil)
				&& Objects.equals(codErp, other.codErp) && Objects.equals(descripcionPerfil, other.descripcionPerfil)
				&& Objects.equals(idCentroTrabajoPerfil, other.idCentroTrabajoPerfil)
				&& Objects.equals(idPerfil, other.idPerfil) && Objects.equals(longitud, other.longitud);
	}

	@Override
	public String toString() {
		return "ComponenteDTO [codErp=" + codErp + ", idPerfil=" + idPerfil + ", descripcionPerfil=" + descripcionPerfil
				+ ", cantListaMateriales=" + cantListaMateriales + ", idCentroTrabajoPerfil=" + idCentroTrabajoPerfil
				+ ", centroTrabajoPerfil=" + centroTrabajoPerfil + ", longitud=" + longitud + "]";
	}	

}
