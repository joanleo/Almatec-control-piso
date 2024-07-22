package com.almatec.controlpiso.utils;

import java.math.BigDecimal;
import java.util.Objects;

public class AgrupacionPrioridad {

	private String op;
    private Integer itemId;
    private BigDecimal cantReq;
    
	public AgrupacionPrioridad(String op, Integer itemId, BigDecimal cantReq) {
		super();
		this.op = op;
		this.itemId = itemId;
		this.cantReq = cantReq;
	}

	public String getOp() {
		return op;
	}

	public Integer getItemId() {
		return itemId;
	}

	public BigDecimal getCantReq() {
		return cantReq;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setCantReq(BigDecimal cantReq) {
		this.cantReq = cantReq;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantReq, itemId, op);
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
		return Objects.equals(cantReq, other.cantReq) && Objects.equals(itemId, other.itemId)
				&& Objects.equals(op, other.op);
	}

	@Override
	public String toString() {
		return "AgrupacionPrioridad [op=" + op + ", itemId=" + itemId + ", cantReq=" + cantReq + "]";
	}
	
}
