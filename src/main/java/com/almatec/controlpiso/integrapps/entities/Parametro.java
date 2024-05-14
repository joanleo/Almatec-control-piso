package com.almatec.controlpiso.integrapps.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametros")
public class Parametro {
	@Id
	@Column(name = "id_parametro")
	private Integer id;
	private String nombre;
	private String valor;
	
	public Parametro() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Parametro [nombre=" + nombre + ", valor=" + valor + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametro other = (Parametro) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(valor, other.valor);
	}

}
