package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

@Entity
@Table(name = "Pedidos_Items_Erp")
@Immutable
public class VistaItemPedidoErp {
	
	@Id
	@Column(name = "f431_rowid")
	private Integer id;
	
	@Column(name = "Tipo_Pv")
	private String tipoPedido;
	
	@Column(name = "No_Pv")
	private String noPedido;
	
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


	public VistaItemPedidoErp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public String getNoPedido() {
		return noPedido;
	}

	public void setNoPedido(String noPedido) {
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

	@Override
	public String toString() {
		return "VistaItemPedidoErp [id=" + id + ", tipoPedido=" + tipoPedido + ", noPedido=" + noPedido
				+ ", referencia=" + referencia + ", descripcion=" + descripcion + ", um=" + um + ", bodega=" + bodega
				+ ", cantidad=" + cantidad + ", peso=" + peso + ", numOp=" + numOp + "]";
	}


	
}
