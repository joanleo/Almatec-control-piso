package com.almatec.controlpiso.ingenieria.dtos;

public class MemoDetalleDTO {

	private Long idItemOp;
	private Integer cantidad;
	private String operacion;
	private String descripcion;
	private String observacion;
	
	public MemoDetalleDTO() {
		super();
	}

	public MemoDetalleDTO(Long idItemOp, Integer cantidad, String operacion, 
			String descripcion, String observacion) {
		super();
		this.idItemOp = idItemOp;
		this.cantidad = cantidad;
		this.operacion = operacion;
		this.descripcion = descripcion;
		this.observacion = observacion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}	
	
}
