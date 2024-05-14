package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pedidos_Items_Erp")
public class VistaItemPedidoErp {
	
	@Id
	@Column(name = "f431_rowid")
	private Integer id;
	
	@Column(name = "f430_rowid")
	private Integer rowIdOp;
	
	@Column(name = "Tipo_Pv")
	private String tipoPedido;
	
	@Column(name = "No_Pv")
	private Integer noPedido;
	
	@Column(name = "Referencia")
	private String referencia;
	
	@Column(name = "Descripcion")
	private String descripcion;
	
	@Column(name = "Und")
	private String um;
	
	@Column(name = "Bodega")
	private String bodega;
	
	@Column(name = "Cant_Pedida")
	private Double cantidad;
	
	@Column(name = "Peso_Ttl")
	private Double peso;
	
	@Column(name = "NumOp")
	private Integer numOp;
	
	@Column(name = "fecha_entrega")
	private Date fechaEntrega;


	public VistaItemPedidoErp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRowIdOp() {
		return rowIdOp;
	}

	public void setRowIdOp(Integer rowIdOp) {
		this.rowIdOp = rowIdOp;
	}

	public String getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public Integer getNoPedido() {
		return noPedido;
	}

	public void setNoPedido(Integer noPedido) {
		this.noPedido = noPedido;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	@Override
	public String toString() {
		return "VistaItemPedidoErp [id=" + id + ", rowIdOp=" + rowIdOp + ", tipoPedido=" + tipoPedido + ", noPedido="
				+ noPedido + ", referencia=" + referencia + ", descripcion=" + descripcion + ", um=" + um + ", bodega="
				+ bodega + ", cantidad=" + cantidad + ", peso=" + peso + ", numOp=" + numOp + ", fechaEntrega="
				+ fechaEntrega + "]";
	}


	
}
