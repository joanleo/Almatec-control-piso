package com.almatec.controlpiso.integrapps.dtos;


/**
 * 
 */
public class ItemComponenteDTO {
	
	private ItemDTO itemDTO;
	private Float cantidad;
	
	/**
	 * @param item
	 * @param cantidad
	 */
	public ItemComponenteDTO(ItemDTO item, Float cantidad) {
		super();
		this.itemDTO = item;
		this.cantidad = cantidad;
	}

	public ItemComponenteDTO() {
		super();
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ItemComponenteDTO [itemDTO=" + itemDTO + ", cantidad=" + cantidad + "]";
	}
	
	

}
