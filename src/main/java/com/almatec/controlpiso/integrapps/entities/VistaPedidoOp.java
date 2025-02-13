package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "view_pv_op")
public class VistaPedidoOp {
    
    @Id
    @Column(name = "id_op_ia")
    private Integer idOpIa;
    
    @Column(name = "pv_row_id")
    private Integer pvRowId;
    
    @Column(name = "pv_tipo")
    private String pvTipo;
    
    @Column(name = "pv_num")
    private Integer pvNum;
    
    @Column(name = "op_padre_tipo")
    private String opPadreTipo;
    
    @Column(name = "op_padre_num")
    private Integer opPadreNum;
    
    @Column(name = "op_hijo_tipo")
    private String opHijoTipo;
    
    @Column(name = "op_hijo_num")
    private Integer opHijoNum;
    
    @Column(name = "op_hijo_total_kg_sol_calc")
    private Double opHijoTotalKgSol;
    
    @Column(name = "op_hijo_kg_cumplidos")
    private Double opHijoKgCumplidos;
    
    @Column(name = "op_hijo_total_kg_planeado")
    private Double opHijoKgFabricar;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_ingenieria")
    private Date fechaIngenieria;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_produccion")
    private Date fechaProduccion;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_entrega")
    private Date fechaEntrega;
    
    @Column(name = "op_hijo_estado")
    private String opHijoEstado;
    
    private String zona;

	public VistaPedidoOp() {
		super();
	}

	public Integer getIdOpIa() {
		return idOpIa;
	}

	public Integer getPvRowId() {
		return pvRowId;
	}

	public String getPvTipo() {
		return pvTipo;
	}

	public Integer getPvNum() {
		return pvNum;
	}

	public String getOpPadreTipo() {
		return opPadreTipo;
	}

	public Integer getOpPadreNum() {
		return opPadreNum;
	}

	public String getOpHijoTipo() {
		return opHijoTipo;
	}

	public Integer getOpHijoNum() {
		return opHijoNum;
	}

	public Double getOpHijoTotalKgSol() {
		return opHijoTotalKgSol;
	}

	public Double getOpHijoKgCumplidos() {
		return opHijoKgCumplidos;
	}

	public Double getOpHijoKgFabricar() {
		return opHijoKgFabricar;
	}

	public Date getFechaIngenieria() {
		return fechaIngenieria;
	}

	public Date getFechaProduccion() {
		return fechaProduccion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public String getOpHijoEstado() {
		return opHijoEstado;
	}

	public String getZona() {
		return zona;
	}

	@Override
	public String toString() {
		return "VistaPedidoOp [idOpIa=" + idOpIa + ", pvRowId=" + pvRowId + ", pvTipo=" + pvTipo + ", pvNum=" + pvNum
				+ ", opPadreTipo=" + opPadreTipo + ", opPadreNum=" + opPadreNum + ", opHijoTipo=" + opHijoTipo
				+ ", opHijoNum=" + opHijoNum + ", opHijoTotalKgSol=" + opHijoTotalKgSol + ", opHijoKgCumplidos="
				+ opHijoKgCumplidos + ", opHijoKgFabricar=" + opHijoKgFabricar + ", fechaIngenieria=" + fechaIngenieria
				+ ", fechaProduccion=" + fechaProduccion + ", fechaEntrega=" + fechaEntrega + ", opHijoEstado="
				+ opHijoEstado + ", zona=" + zona + "]";
	}
}
