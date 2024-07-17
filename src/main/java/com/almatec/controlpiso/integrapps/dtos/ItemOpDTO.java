package com.almatec.controlpiso.integrapps.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ItemOpDTO {
	
	private Long idItemOp;
	private Double cant;
	private Double cantCumplida;
	private Integer prioridad;
	private ItemDTO itemDTO;
	private String color;
	private List<ItemComponenteDTO> componentes = new ArrayList<>();

	
	public ItemOpDTO() {
		super();
	}


	public Long getIdItemOp() {
		return idItemOp;
	}


	public Double getCant() {
		return cant;
	}


	public Double getCantCumplida() {
		return cantCumplida;
	}


	/*public Integer getPrioridad() {
		return prioridad;
	}*/


	public List<ItemComponenteDTO> getComponentes() {
		return componentes;
	}


	public void setIdItemOp(Long idItemOp) {
		this.idItemOp = idItemOp;
	}


	public void setCant(Double cant) {
		this.cant = cant;
	}


	public void setCantCumplida(Double cantCumplida) {
		this.cantCumplida = cantCumplida;
	}


	/*public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}*/


	public void setComponentes(List<ItemComponenteDTO> componentes) {
		this.componentes = componentes;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public ItemDTO getItemDTO() {
		return itemDTO;
	}


	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}


	@Override
	public String toString() {
		return "ItemOpDTO [idItemOp=" + idItemOp + ", cant=" + cant + ", cantCumplida=" + cantCumplida +  ", itemDTO=" + itemDTO + ", color=" + color + ", componentes=" + componentes + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(componentes, itemDTO);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOpDTO other = (ItemOpDTO) obj;
		return Objects.equals(componentes, other.componentes) && Objects.equals(itemDTO, other.itemDTO);
	}
}