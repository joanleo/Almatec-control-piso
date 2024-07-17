package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.almatec.controlpiso.integrapps.interfaces.CommonDTO;

public class ItemOpCtDTO implements CommonDTO {
	private Long item_op_id;
	private Integer item_id;
	private String item_desc;
	private BigDecimal cant_req;
	private Integer item_centro_t_id;
	private String item_centro_t_nombre;
	private BigDecimal item_peso;
	private String item_color;
	private BigDecimal longitud;
	private Set<ComponenteDTO> componentes = new HashSet<>();
	private Integer prioridad;
	private Integer cant_cumplida;
	private String marca;

	public ItemOpCtDTO() {
		super();
	}

	public Long getItem_op_id() {
		return item_op_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public BigDecimal getCant_req() {
		return cant_req;
	}

	public Integer getItem_centro_t_id() {
		return item_centro_t_id;
	}

	public String getItem_centro_t_nombre() {
		return item_centro_t_nombre;
	}

	public BigDecimal getItem_peso() {
		return item_peso;
	}

	public String getItem_color() {
		return item_color;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setItem_op_id(Long item_op_id) {
		this.item_op_id = item_op_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

	public void setCant_req(BigDecimal cant_req) {
		this.cant_req = cant_req;
	}

	public void setItem_centro_t_id(Integer item_centro_t_id) {
		this.item_centro_t_id = item_centro_t_id;
	}

	public void setItem_centro_t_nombre(String item_centro_t_nombre) {
		this.item_centro_t_nombre = item_centro_t_nombre;
	}

	public void setItem_peso(BigDecimal item_peso) {
		this.item_peso = item_peso;
	}

	public void setItem_color(String item_color) {
		this.item_color = item_color;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Set<ComponenteDTO> getComponentes() {
		return componentes;
	}

	public void setComponentes(Set<ComponenteDTO> componentes) {
		this.componentes = componentes;
	}

	public void addComponente(ComponenteDTO componente) {
		componentes.add(componente);
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public Integer getCant_cumplida() {
		return cant_cumplida;
	}

	public void setCant_cumplida(Integer cant_cumplida) {
		this.cant_cumplida = cant_cumplida;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "ItemOpCtDTO [item_op_id=" + item_op_id + ", item_id=" + item_id + ", item_desc=" + item_desc
				+ ", cant_req=" + cant_req + ", item_centro_t_id=" + item_centro_t_id + ", item_centro_t_nombre="
				+ item_centro_t_nombre + ", item_peso=" + item_peso + ", item_color=" + item_color + ", longitud="
				+ longitud + ", componentes=" + componentes + ", prioridad=" + prioridad + ", cant_cumplida="
				+ cant_cumplida + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cant_req, componentes, item_centro_t_id, item_centro_t_nombre, item_color, item_desc,
				item_id, item_op_id, item_peso, prioridad);
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
		return Objects.equals(cant_req, other.cant_req) && Objects.equals(componentes, other.componentes)
				&& Objects.equals(item_centro_t_id, other.item_centro_t_id)
				&& Objects.equals(item_centro_t_nombre, other.item_centro_t_nombre)
				&& Objects.equals(item_color, other.item_color) && Objects.equals(item_desc, other.item_desc)
				&& Objects.equals(item_id, other.item_id) && Objects.equals(item_op_id, other.item_op_id)
				&& Objects.equals(item_peso, other.item_peso) && Objects.equals(prioridad, other.prioridad);
	}

	@Override
	public String getCentroTrabajo() {
		return null;
	}

	@Override
	public String getDescripcion() {
		return null;
	}

	@Override
	public Integer getCant() {
		return null;
	}

}
