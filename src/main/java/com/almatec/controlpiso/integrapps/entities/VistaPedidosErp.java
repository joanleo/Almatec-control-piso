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
	@Column(name = "pv_rowid")
	private Long id;

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

	@Column(name = "pv_cliente_co_id_descripcion")
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

	@Column(name = "pv_kg_cumplidos")
	private Double kgCumplidos;
	
	@Column(name = "pv_cliente_co_descripcion")
	private String centroOperaciones; 
	
	@Column(name = "pv_valor_bruto")
	private String valorBruto;

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

	public Double getKgCumplidos() {
		return kgCumplidos;
	}

	public String getCentroOperaciones() {
		return centroOperaciones;
	}

	public String getValorBruto() {
		return valorBruto;
	}

	@Override
	public String toString() {
		return "VistaPedidosErp [id=" + id + ", nit=" + nit + ", razonSocial=" + razonSocial + ", sucursal=" + sucursal
				+ ", tipo=" + tipo + ", noPv=" + noPv + ", pedidoNo=" + pedidoNo + ", valor=" + valor + ", co=" + co
				+ ", fecha=" + fecha + ", vendedor=" + vendedor + ", idEstado=" + idEstado + ", estado=" + estado
				+ ", descripcion=" + descripcion + ", cantPedida=" + cantPedida + ", unidadMedidaInventario="
				+ unidadMedidaInventario + ", unidadMedidaAdicional=" + unidadMedidaAdicional + ", tipoOp=" + tipoOp
				+ ", numOp=" + numOp + ", kgCumplidos=" + kgCumplidos + ", centroOperaciones=" + centroOperaciones
				+ ", valorBruto=" + valorBruto + "]";
	}

}
