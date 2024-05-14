package com.almatec.controlpiso.integrapps.dtos;

import java.util.List;
import java.util.Objects;

import com.almatec.controlpiso.integrapps.entities.Item;
import com.almatec.controlpiso.integrapps.entities.RutaItem;

public class ItemDTO {
	
	private Item item;
	private List<RutaItem> ruta;
	
	public ItemDTO() {
		super();
	}

	public ItemDTO(Item item, List<RutaItem> ruta) {
		super();
		this.item = item;
		this.ruta = ruta;
	}

	public Item getItem() {
		return item;
	}

	public List<RutaItem> getRuta() {
		return ruta;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setRuta(List<RutaItem> ruta) {
		this.ruta = ruta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(item, ruta);
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
		return Objects.equals(item, other.item) && Objects.equals(ruta, other.ruta);
	}

	@Override
	public String toString() {
		return "ItemDTO [item=" + item + ", ruta=" + ruta + "]";
	}
	
	

}
