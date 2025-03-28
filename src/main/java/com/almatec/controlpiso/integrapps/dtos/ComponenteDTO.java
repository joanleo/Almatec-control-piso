package com.almatec.controlpiso.integrapps.dtos;

import java.math.BigDecimal;
import java.util.Objects;

import com.almatec.controlpiso.integrapps.interfaces.CommonDTO;

public class ComponenteDTO implements CommonDTO {
	
	private Integer material_id;
	private String material_desc;
	private Integer material_cant;
	private Integer material_centro_t_id;
	private String material_centro_t_nombre;
	private BigDecimal longitud;
	private BigDecimal material_peso;
	
	public ComponenteDTO() {
		super();
	}

	public ComponenteDTO(Integer material_id, String material_desc, Integer material_cant,
			Integer material_centro_t_id, String material_centro_t_nombre) {
		super();
		this.material_id = material_id;
		this.material_desc = material_desc;
		this.material_cant = material_cant;
		this.material_centro_t_id = material_centro_t_id;
		this.material_centro_t_nombre = material_centro_t_nombre;
	}

	public Integer getMaterial_id() {
		return material_id;
	}

	public String getMaterial_desc() {
		return material_desc;
	}

	public Integer getMaterial_cant() {
		return material_cant;
	}

	public Integer getMaterial_centro_t_id() {
		return material_centro_t_id;
	}

	public String getMaterial_centro_t_nombre() {
		return material_centro_t_nombre;
	}

	public BigDecimal getMaterial_peso() {
		return material_peso;
	}

	public void setMaterial_id(Integer material_id) {
		this.material_id = material_id;
	}

	public void setMaterial_desc(String material_desc) {
		this.material_desc = material_desc;
	}

	public void setMaterial_cant(Integer material_cant) {
		this.material_cant = material_cant;
	}

	public void setMaterial_centro_t_id(Integer material_centro_t_id) {
		this.material_centro_t_id = material_centro_t_id;
	}

	public void setMaterial_centro_t_nombre(String material_centro_t_nombre) {
		this.material_centro_t_nombre = material_centro_t_nombre;
	}

	public void setMaterial_peso(BigDecimal material_peso) {
		this.material_peso = material_peso;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal material_long) {
		this.longitud = material_long;
	}

	@Override
	public String toString() {
		return "ComponenteDTO [material_id=" + material_id + ", material_desc=" + material_desc + ", material_cant="
				+ material_cant + ", material_centro_t_id=" + material_centro_t_id + ", material_centro_t_nombre="
				+ material_centro_t_nombre + ", material_long=" + longitud + ", material_peso=" + material_peso
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(material_cant, material_centro_t_id, material_centro_t_nombre, material_desc, material_id,
				longitud, material_peso);
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
		return Objects.equals(material_cant, other.material_cant)
				&& Objects.equals(material_centro_t_id, other.material_centro_t_id)
				&& Objects.equals(material_centro_t_nombre, other.material_centro_t_nombre)
				&& Objects.equals(material_desc, other.material_desc) && Objects.equals(material_id, other.material_id)
				&& Objects.equals(longitud, other.longitud)
				&& Objects.equals(material_peso, other.material_peso);
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
