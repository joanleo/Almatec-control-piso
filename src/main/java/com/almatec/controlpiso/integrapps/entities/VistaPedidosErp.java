package com.almatec.controlpiso.integrapps.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "Pedidos_Erp_Estado")
public class VistaPedidosErp {
	
	@Id
	@Column(name = "f430_rowid")
    private Long id;

	@Column(name = "Nit_Cliente")
	private String nit;
	
	@Column(name = "Cliente")
	private String razonSocial;
	
	@Column(name = "id_Sucursal")
	private String sucursal;
	
	@Column(name = "Tipo_Pv")
	private String tipo;
	
	@Column(name = "No_Pv")
	private Integer noPv;
	
	@Column(name = "Pedido")
	private String pedidoNo;
	
	@Column(name = "Vlr_Neto")
	private String valor;
	
	@Column(name = "Id_estado")
	private Integer estado;
	
	private Date fecha;
	
	@Column(name = "f431_id_proyecto")
	private String proyecto;

	public VistaPedidosErp() {
		super();
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

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNoPv() {
		return noPv;
	}

	public void setNoPv(Integer noPv) {
		this.noPv = noPv;
	}

	public String getPedidoNo() {
		return pedidoNo;
	}

	public void setPedidoNo(String pedidoNo) {
		this.pedidoNo = pedidoNo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	@Override
	public String toString() {
		return "VistaPedidosErp [nit=" + nit + ", razonSocial=" + razonSocial + ", sucursal=" + sucursal + ", tipo="
				+ tipo + ", noPv=" + noPv + ", pedidoNo=" + pedidoNo + ", valor=" + valor + ", estado=" + estado
				+ ", fecha=" + fecha + ", proyecto=" + proyecto + "]";
	}

	

}
