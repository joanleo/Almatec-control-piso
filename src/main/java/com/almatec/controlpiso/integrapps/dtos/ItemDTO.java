package com.almatec.controlpiso.integrapps.dtos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ItemDTO {
	private Integer id;
	private String descripcion;
	private Integer cant;
	private Integer idCentroTrabajo;
	private String centroTrabajo;
	private Set<ComponenteDTO> componentes = new HashSet<>();
	
	public ItemDTO() {
		super();
	}

	public ItemDTO(Integer itemFabId, String descripcionConjunto, Integer cantOp, Integer idCentroTrabajoConjunto,
			String centroTrabajoConjunto) {
		super();
		this.id = itemFabId;
		this.descripcion = descripcionConjunto;
		this.cant = cantOp;
		this.idCentroTrabajo = idCentroTrabajoConjunto;
		this.centroTrabajo = centroTrabajoConjunto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCant() {
		return cant;
	}

	public void setCant(Integer cant) {
		this.cant = cant;
	}

	public Integer getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public void setIdCentroTrabajo(Integer idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public Set<ComponenteDTO> getComponentes() {
		return componentes;
	}

	public void setComponentes(Set<ComponenteDTO> componentes) {
		this.componentes = componentes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cant, centroTrabajo, componentes, descripcion, id, idCentroTrabajo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDTO other = (ItemDTO) obj;
		return Objects.equals(cant, other.cant) && Objects.equals(centroTrabajo, other.centroTrabajo)
				&& Objects.equals(componentes, other.componentes) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(id, other.id) && Objects.equals(idCentroTrabajo, other.idCentroTrabajo);
	}

	@Override
	public String toString() {
		return "ItemDTO [id=" + id + ", descripcion=" + descripcion + ", cant=" + cant + ", idCentroTrabajo="
				+ idCentroTrabajo + ", centroTrabajo=" + centroTrabajo + ", componentes=" + componentes + "]";
	}

	public void addComponente(ComponenteDTO componente) {
		componentes.add(componente);
	}

}
