package com.almatec.controlpiso.integrapps.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
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
	
	@Column(name = "f054_descripcion")
	private String estado;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	@Column(name = "f285_descripcion")
	private String proyecto;
	
	@Column(name = "Vendedor")
	private String vendedor;
	
	@Column(name = "Id_estado")
	private Integer idEstado;
	
	@Column(name = "f120_descripcion")
	private String descripcion;
	
	@Column(name = "f431_cant1_pedida")
	private Integer cantPedida;

	public VistaPedidosErp() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNit() {
		return nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public String getSucursal() {
		return sucursal;
	}

	public String getTipo() {
		return tipo;
	}

	public Integer getNoPv() {
		return noPv;
	}

	public String getPedidoNo() {
		return pedidoNo;
	}

	public String getValor() {
		return valor;
	}

	public String getEstado() {
		return estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getProyecto() {
		return proyecto;
	}

	public String getVendedor() {
		return vendedor;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getCantPedida() {
		return cantPedida;
	}

	@Override
	public String toString() {
		return "VistaPedidosErp [id=" + id + ", nit=" + nit + ", razonSocial=" + razonSocial + ", sucursal=" + sucursal
				+ ", tipo=" + tipo + ", noPv=" + noPv + ", pedidoNo=" + pedidoNo + ", valor=" + valor + ", estado="
				+ estado + ", fecha=" + fecha + ", proyecto=" + proyecto + ", vendedor=" + vendedor + ", idEstado="
				+ idEstado + ", descripcion=" + descripcion + ", cantPedida=" + cantPedida + "]";
	}

	
}
