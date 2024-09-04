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
	
	@Column(name = "f850_id_tipo_docto")
	private String tipoOp;

	@Column(name = "NumOp")
	private Integer numOp;

	@Column(name = "fecha_entrega")
	private Date fechaEntrega;

	@Column(name = "f120_id_unidad_inventario")
	private String unidadMedidaInventario;

	@Column(name = "f120_id_unidad_adicional")
	private String unidadMedidaAdicional;
	
	@Column(name = "cliente")
	private String cliente;
	
	@Column(name = "Codigo")
	private Integer codigo;



	public VistaItemPedidoErp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Integer getRowIdOp() {
		return rowIdOp;
	}

	public String getTipoPedido() {
		return tipoPedido;
	}

	public Integer getNoPedido() {
		return noPedido;
	}

	public String getReferencia() {
		return referencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getUm() {
		return um;
	}

	public String getBodega() {
		return bodega;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public Double getPeso() {
		return peso;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public String getUnidadMedidaInventario() {
		return unidadMedidaInventario;
	}

	public String getUnidadMedidaAdicional() {
		return unidadMedidaAdicional;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public String getCliente() {
		return cliente;
	}

	public Integer getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return "VistaItemPedidoErp [id=" + id + ", rowIdOp=" + rowIdOp + ", tipoPedido=" + tipoPedido + ", noPedido="
				+ noPedido + ", referencia=" + referencia + ", descripcion=" + descripcion + ", um=" + um + ", bodega="
				+ bodega + ", cantidad=" + cantidad + ", peso=" + peso + ", tipoOp=" + tipoOp + ", numOp=" + numOp
				+ ", fechaEntrega=" + fechaEntrega + ", unidadMedidaInventario=" + unidadMedidaInventario
				+ ", unidadMedidaAdicional=" + unidadMedidaAdicional + ", cliente=" + cliente + ", codigo=" + codigo
				+ "]";
	}
}
