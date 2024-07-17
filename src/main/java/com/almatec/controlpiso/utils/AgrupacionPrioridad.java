package com.almatec.controlpiso.utils;

import java.util.Objects;

public class AgrupacionPrioridad {

	private String op;
    private Integer itemId;
	public AgrupacionPrioridad(String op, Integer itemId) {
		super();
		this.op = op;
		this.itemId = itemId;
	}
	public String getOp() {
		return op;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(itemId, op);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgrupacionPrioridad other = (AgrupacionPrioridad) obj;
		return Objects.equals(itemId, other.itemId) && Objects.equals(op, other.op);
	}
}
