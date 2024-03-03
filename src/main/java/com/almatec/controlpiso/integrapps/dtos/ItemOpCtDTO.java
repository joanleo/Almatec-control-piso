package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.almatec.controlpiso.integrapps.interfaces.CommonDTO;

public class ItemOpCtDTO implements CommonDTO {
	private Long idItem;
	private Integer idItemFab;
	private String descripcion;
	private Integer cant;
	private Integer idCentroTrabajo;
	private String centroTrabajo;
	private BigDecimal peso;
	private String pintura;
	private Set<ComponenteDTO> componentes = new HashSet<>();
	private Integer prioridad;
	
	public ItemOpCtDTO() {
		super();
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public void setIdItemFab(Integer id) {
		this.idItemFab = id;
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

	@Override
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

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getPintura() {
		return pintura;
	}

	public void setPintura(String pintura) {
		this.pintura = pintura;
	}	

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public void addComponente(ComponenteDTO componente) {
		componentes.add(componente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cant, centroTrabajo, componentes, descripcion, idCentroTrabajo, idItem, idItemFab, peso,
				pintura, prioridad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOpCtDTO other = (ItemOpCtDTO) obj;
		return Objects.equals(cant, other.cant) && Objects.equals(centroTrabajo, other.centroTrabajo)
				&& Objects.equals(componentes, other.componentes) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(idCentroTrabajo, other.idCentroTrabajo) && Objects.equals(idItem, other.idItem)
				&& Objects.equals(idItemFab, other.idItemFab) && Objects.equals(peso, other.peso)
				&& Objects.equals(pintura, other.pintura) && Objects.equals(prioridad, other.prioridad);
	}

	@Override
	public String toString() {
		return "ItemOpCtDTO [idItem=" + idItem + ", idItemFab=" + idItemFab + ", descripcion=" + descripcion + ", cant="
				+ cant + ", idCentroTrabajo=" + idCentroTrabajo + ", centroTrabajo=" + centroTrabajo + ", peso=" + peso
				+ ", pintura=" + pintura + ", componentes=" + componentes + ", prioridad=" + prioridad + "]";
	}

	
}
