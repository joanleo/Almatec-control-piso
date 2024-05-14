package com.almatec.controlpiso.integrapps.dtos;

public class ConsultaOpId {
	
	private Integer idOpIa;
    private Integer numOp;
	public ConsultaOpId(Integer idOpIa, Integer numOp) {
		super();
		this.idOpIa = idOpIa;
		this.numOp = numOp;
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

}
