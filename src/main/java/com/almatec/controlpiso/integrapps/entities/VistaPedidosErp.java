package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "view_pedidos_estado_erp")
public class VistaPedidosErp {

	@Id
	@Column(name = "id_pedido_completo")
	private String id;
	
	@Column(name = "pv_rowid")
	private Long rowId;

	@Column(name = "pv_cliente_nit")
	private String nit;

	@Column(name = "pv_cliente_razon_social")
	private String razonSocial;

	@Column(name = "pv_cliente_sucursal")
	private String sucursal;

	@Column(name = "pv_tipo")
	private String tipo;

	@Column(name = "pv_num")
	private Integer noPv;

	@Column(name = "pv_tipo_num")
	private String pedidoNo;

	@Column(name = "pv_valor_neto")
	private String valor;

	@Column(name = "pv_cliente_co_descripcion")
	private String co;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "pv_fecha")
	private Date fecha;

	@Column(name = "pv_vendedor_nombre")
	private String vendedor;

	@Column(name = "pv_id_estado")
	private Integer idEstado;

	@Column(name = "pv_estado")
	private String estado;

	@Column(name = "pv_item_descripcion")
	private String descripcion;

	@Column(name = "pv_item_cantidad")
	private Integer cantPedida;

	@Column(name = "pv_item_und_inventario")
	private String unidadMedidaInventario;

	@Column(name = "pv_item_und_adicional")
	private String unidadMedidaAdicional;

	@Column(name = "pv_op_padre_tipo")
	private String tipoOp;

	@Column(name = "pv_op_padre_num")
	private Integer numOp;

	
	@Column(name = "pv_valor_bruto")
	private String valorBruto;
	
	/*@Column(name = "pv_op_hija_tipo")
	private String tipoOpHija;

	@Column(name = "pv_kg_cumplidos")
	private Double kgCumplidos;

	@Column(name = "pv_op_hijo_num")
	private Integer numOpHija;
	
	@Column(name = "op_zona")
	private String zona_op;*/

	public VistaPedidosErp() {
		super();
	}


	public String getId() {
		return id;
	}


	public Long getRowId() {
		return rowId;
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

	public String getCo() {
		return co;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getVendedor() {
		return vendedor;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getCantPedida() {
		return cantPedida;
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

	public Integer getNumOp() {
		return numOp;
	}


	public String getValorBruto() {
		return valorBruto;
	}

	/*public String getTipoOpHija() {
		return tipoOpHija;
	}
	public Double getKgCumplidos() {
		return kgCumplidos;
	}

	public Integer getNumOpHija() {
		return numOpHija;
	}

	public String getZona_op() {
		return zona_op;
	}*/

	@Override
	public String toString() {
		return "VistaPedidosErp [id=" + id + ", rowId=" + rowId + ", nit=" + nit + ", razonSocial=" + razonSocial
				+ ", sucursal=" + sucursal + ", tipo=" + tipo + ", noPv=" + noPv + ", pedidoNo=" + pedidoNo + ", valor="
				+ valor + ", co=" + co + ", fecha=" + fecha + ", vendedor=" + vendedor + ", idEstado=" + idEstado
				+ ", estado=" + estado + ", descripcion=" + descripcion + ", cantPedida=" + cantPedida
				+ ", unidadMedidaInventario=" + unidadMedidaInventario + ", unidadMedidaAdicional="
				+ unidadMedidaAdicional + ", tipoOp=" + tipoOp + ", numOp=" + numOp + ", valorBruto=" + valorBruto + "]";
	}

}
