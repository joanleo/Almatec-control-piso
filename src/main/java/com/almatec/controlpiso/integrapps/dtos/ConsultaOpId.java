package com.almatec.controlpiso.integrapps.dtos;

public class ConsultaOpId {
	
	private Integer idOpIa;
    private Integer numOp;
    private String descripcion;
	public ConsultaOpId(Integer idOpIa, Integer numOp, String descriocion) {
		super();
		this.idOpIa = idOpIa;
		this.numOp = numOp;
		this.descripcion = descriocion;
	}
	public ConsultaOpId() {
		super();
	}
	public Integer getIdOpIa() {
		return idOpIa;
	}
	public Integer getNumOp() {
		return numOp;
	}
	public void setIdOpIa(Integer idOpIa) {
		this.idOpIa = idOpIa;
	}
	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}   

}
