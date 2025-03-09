package com.almatec.controlpiso.ingenieria.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemDetalladoDTO {
    private Integer idItem;
    private String descripcion;
    private String tipo;
    private String plano;
    private BigDecimal pesoBruto;
    private BigDecimal pesoNeto;
    private List<ComponenteDTO> componentes= new ArrayList<>();
    private List<MateriaPrimaDTO> materiaPrima= new ArrayList<>();
    
	public ItemDetalladoDTO() {
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

	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}

	public BigDecimal getPesoNeto() {
		return pesoNeto;
	}

	public List<ComponenteDTO> getComponentes() {
		return componentes;
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

	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public void setPesoNeto(BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}

	public void setComponentes(List<ComponenteDTO> componentes) {
		this.componentes = componentes;
	}

	public void setMateriaPrima(List<MateriaPrimaDTO> materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	@Override
	public String toString() {
		return "ItemDetalladoDTO [idItem=" + idItem + ", descripcion=" + descripcion + ", tipo=" + tipo + ", plano="
				+ plano + ", pesoBruto=" + pesoBruto + ", pesoNeto=" + pesoNeto + ", componentes=" + componentes
				+ ", materiaPrima=" + materiaPrima + "]";
	}  
}