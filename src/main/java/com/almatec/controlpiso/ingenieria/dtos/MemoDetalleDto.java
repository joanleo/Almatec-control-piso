package com.almatec.controlpiso.ingenieria.dtos;

public class MemoDetalleDto {

	private Long idItemOp;
	private Integer cantidad;
	private String operacion;
	
	public MemoDetalleDto() {
		super();
	}

	public Long getIdItemOp() {
		return idItemOp;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setIdItemOp(Long idItemOp) {
		this.idItemOp = idItemOp;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}	
	
}
