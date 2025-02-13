package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "view_pedidos_kg_cumplidos")
public class VistaPedidosKgCumplidos {
    
    @Id
    @Column(name = "pv_rowid")
    private Integer rowid;
    @Column(name = "pv_cia")
    private String cia;
    @Column(name = "pv_co")
    private String coId;
    @Column(name = "pv_tipo")
    private String tipo;
    @Column(name = "pv_num")
    private String noPv;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pv_fecha")
    private Date fecha;
    @Column(name = "pv_cliente_nit")
    private String nit;
    @Column(name = "pv_cliente_razon_social")
    private String razonSocial;
    @Column(name = "pv_vendedor_nombre")
    private String vendedor;
    @Column(name = "pv_op_padre_tipo")
    private String tipoOp;
    @Column(name = "pv_op_padre_num")
    private String numOp;
    @Column(name = "pv_cliente_co_id")
    private String pvClienteCoId;
    @Column(name = "pv_cliente_co_descripcion")
    private String co;
    @Column(name = "pv_tipo_num")
    private String pvTipoNum;
    @Column(name = "pv_item_cantidad")
    private BigDecimal cantPedida;
    @Column(name = "pv_item_und_inventario")
    private String unidadMedidaInventario;
    @Column(name = "pv_item_und_adicional")
    private String unidadMedidaAdicional;
    @Column(name = "pv_valor_neto")
    private BigDecimal pvValorNeto;
    @Column(name = "pv_id_estado")
    private Integer pvIdEstado;
    @Column(name = "pv_item_descripcion")
    private String descripcion;
    @Column(name = "pv_cliente_sucursal")
    private String sucursal;
    @Column(name = "pv_cliente_cupo")
    private BigDecimal pvClienteCupo;
    @Column(name = "pv_estado")
    private String estado;
    @Column(name = "f120_ind_tipo_item")
    private String f120IndTipoItem;
    @Column(name = "pv_valor_bruto")
    private BigDecimal valorBruto;
    @Column(name = "id_pedido_completo")
    private String idPedidoCompleto;
    @Column(name = "pv_op_padre_row_id")
    private Long pvOpPadreRowId;
    @Column(name = "total_kg_cumplidos")
    private BigDecimal totalKgCumplidos;
    
	public VistaPedidosKgCumplidos() {
		super();
	}

	public Integer getRowid() {
		return rowid;
	}

	public String getCia() {
		return cia;
	}

	public String getCoId() {
		return coId;
	}

	public String getTipo() {
		return tipo;
	}

	public String getNoPv() {
		return noPv;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getNit() {
		return nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public String getVendedor() {
		return vendedor;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public String getNumOp() {
		return numOp;
	}

	public String getPvClienteCoId() {
		return pvClienteCoId;
	}

	public String getCo() {
		return co;
	}

	public String getPvTipoNum() {
		return pvTipoNum;
	}

	public BigDecimal getCantPedida() {
		return cantPedida;
	}

	public String getUnidadMedidaInventario() {
		return unidadMedidaInventario;
	}

	public String getUnidadMedidaAdicional() {
		return unidadMedidaAdicional;
	}

	public BigDecimal getPvValorNeto() {
		return pvValorNeto;
	}

	public Integer getPvIdEstado() {
		return pvIdEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getSucursal() {
		return sucursal;
	}

	public BigDecimal getPvClienteCupo() {
		return pvClienteCupo;
	}

	public String getEstado() {
		return estado;
	}

	public String getF120IndTipoItem() {
		return f120IndTipoItem;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public String getIdPedidoCompleto() {
		return idPedidoCompleto;
	}

	public Long getPvOpPadreRowId() {
		return pvOpPadreRowId;
	}

	public BigDecimal getTotalKgCumplidos() {
		return totalKgCumplidos;
	}

	@Override
	public String toString() {
		return "VistaPedidosKgCumplidos [rowid=" + rowid + ", cia=" + cia + ", coId=" + coId + ", tipo=" + tipo
				+ ", noPv=" + noPv + ", fecha=" + fecha + ", nit=" + nit + ", razonSocial=" + razonSocial
				+ ", vendedor=" + vendedor + ", tipoOp=" + tipoOp + ", numOp=" + numOp + ", pvClienteCoId="
				+ pvClienteCoId + ", co=" + co + ", pvTipoNum=" + pvTipoNum + ", cantPedida=" + cantPedida
				+ ", unidadMedidaInventario=" + unidadMedidaInventario + ", unidadMedidaAdicional="
				+ unidadMedidaAdicional + ", pvValorNeto=" + pvValorNeto + ", pvIdEstado=" + pvIdEstado
				+ ", descripcion=" + descripcion + ", sucursal=" + sucursal + ", pvClienteCupo=" + pvClienteCupo
				+ ", pvEstado=" + estado + ", f120IndTipoItem=" + f120IndTipoItem + ", valorBruto=" + valorBruto
				+ ", idPedidoCompleto=" + idPedidoCompleto + ", pvOpPadreRowId=" + pvOpPadreRowId
				+ ", totalKgCumplidos=" + totalKgCumplidos + "]";
	}
    
}