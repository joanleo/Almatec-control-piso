package com.almatec.controlpiso.integrapps.paging;

import java.util.Objects;

public class Order {

    private Integer column;
    private Direction dir;
    
	public Order() {
		super();
	}

	public Order(Integer column, Direction dir) {
		super();
		this.column = column;
		this.dir = dir;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, dir);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(column, other.column) && dir == other.dir;
	}

}
