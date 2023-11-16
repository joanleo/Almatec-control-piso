package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "pedidos_ops")
@Immutable
public class VistaPedidosOpErp {
	
	@Id
	@Column(name = "f850_rowid")
	private Integer id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "fecha_op")
	private Date fechaOp;
	
	@Column(name = "tipo_op")
	private String tipoOp;
	
	@Column(name = "num_op")
	private String numOp;
	
	@Column(name = "id_estado_op")
	private String idEstadoOp;
	
	@Column(name = "tipo_pv")
	private String tipoPv;
	
	@Column(name = "num_pv")
	private String numPv;
	
	@Column(name = "id_estado_pv")
	private String idEstadoPv;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "fecha_pv")
	private Date fechaPv;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "fec_ent_pv")
	private Date fechaEntregaPv;
	
	@Column(name = "estado_pv")
	private String estadoPv;

	@Column(name = "estado_op")
	private String estadoOp;
	
	@Column(name = "nit")
	private String nit;
	
	@Column(name = "razon_social")
	private String razonSocial;

	public VistaPedidosOpErp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaOp() {
		return fechaOp;
	}

	public void setFechaOp(Date fechaOp) {
		this.fechaOp = fechaOp;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}

	public String getNumOp() {
		return numOp;
	}

	public void setNumOp(String numOp) {
		this.numOp = numOp;
	}

	public String getIdEstadoOp() {
		return idEstadoOp;
	}

	public void setIdEstadoOp(String idEstadoOp) {
		this.idEstadoOp = idEstadoOp;
	}

	public String getTipoPv() {
		return tipoPv;
	}

	public void setTipoPv(String tipoPv) {
		this.tipoPv = tipoPv;
	}

	public String getNumPv() {
		return numPv;
	}

	public void setNumPv(String numPv) {
		this.numPv = numPv;
	}

	public String getIdEstadoPv() {
		return idEstadoPv;
	}

	public void setIdEstadoPv(String idEstadoPv) {
		this.idEstadoPv = idEstadoPv;
	}

	public Date getFechaPv() {
		return fechaPv;
	}

	public void setFechaPv(Date fechaPv) {
		this.fechaPv = fechaPv;
	}

	public Date getFechaEntregaPv() {
		return fechaEntregaPv;
	}

	public void setFechaEntregaPv(Date fechaEntregaPv) {
		this.fechaEntregaPv = fechaEntregaPv;
	}

	public String getEstadoPv() {
		return estadoPv;
	}

	public void setEstadoPv(String estadoPv) {
		this.estadoPv = estadoPv;
	}

	public String getEstadoOp() {
		return estadoOp;
	}

	public void setEstadoOp(String estadoOp) {
		this.estadoOp = estadoOp;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Override
	public String toString() {
		return "VistaPedidosOpErp [id=" + id + ", fechaOp=" + fechaOp + ", tipoOp=" + tipoOp + ", numOp=" + numOp
				+ ", idEstadoOp=" + idEstadoOp + ", tipoPv=" + tipoPv + ", numPv=" + numPv + ", idEstadoPv="
				+ idEstadoPv + ", fechaPv=" + fechaPv + ", fechaEntregaPv=" + fechaEntregaPv + ", estadoPv=" + estadoPv
				+ ", estadoOp=" + estadoOp + ", nit=" + nit + ", razonSocial=" + razonSocial + "]";
	}


}
