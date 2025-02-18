package com.almatec.controlpiso.ingenieria.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ComponenteDTO {
    private Integer idItem;
    private String descripcion;
    private String tipo;
    private String plano;
    private BigDecimal cantidad;
    private List<ComponenteDTO> subcomponentes= new ArrayList<>();;
    private List<MateriaPrimaDTO> materiaPrima= new ArrayList<>();;
    
	public ComponenteDTO() {
		super();
	}

	public Integer getIdItem() {
		return idItem;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public String getPlano() {
		return plano;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public List<ComponenteDTO> getSubcomponentes() {
		return subcomponentes;
	}

	public List<MateriaPrimaDTO> getMateriaPrima() {
		return materiaPrima;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public void setSubcomponentes(List<ComponenteDTO> subcomponentes) {
		this.subcomponentes = subcomponentes;
	}

	public void setMateriaPrima(List<MateriaPrimaDTO> materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	@Override
	public String toString() {
		return "ComponenteDTO [idItem=" + idItem + ", descripcion=" + descripcion + ", tipo=" + tipo + ", plano="
				+ plano + ", cantidad=" + cantidad + ", subcomponentes=" + subcomponentes + ", materiaPrima="
				+ materiaPrima + "]";
	}
    
	
    
}
