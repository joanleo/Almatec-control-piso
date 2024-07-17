package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_piezas_operarios_proceso")
public class VistaPiezasOperarios {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "id_reg_pieza")
	private Integer idRegPieza;

	@Column(name = "id_config_proceso")
	private Integer idProceso;

	@Column(name = "id_centro_trabajo")
	private Integer ct;

	@Column(name = "id_operario")
	private Integer idOperario;

	@Column(name = "is_pieza_activa")
	private Boolean isActivo;

	private String cliente;

	@Column(name = "c_o")
	private String co;

	@Column(name = "id_item_op")
	private Long IdItem;

	@Column(name = "id_item")
	private Integer idItemFab;

	@Column(name = "descripcion")
	private String descripcionItem;

	@Column(name = "cant_req")
	private Integer cantReq;

	@Column(name = "peso_item")
	private BigDecimal peso;

	@Column(name = "codigo_erp")
	private Integer codErp;

	@Column(name = "long_item")
	private BigDecimal longitud;

	private Integer prioridad;

	@Column(name = "id_op_ia")
	private Integer idOp;

	@Column(name = "tipo_op_erp")
	private String tipoOp;

	@Column(name = "num_op_erp")
	private Integer numOp;

	@Column(name = "esquema_pintura")
	private String esquemaPintura;

	@Column(name = "N_sstranscurrido")
	private Integer tiempoTrancurrido;

	@Column(name = "N_ssreproceso")
	private Integer tiempoReproceso;

	@Column(name = "nombre_operario")
	private String nombreOperario;

	@Column(name = "color")
	private String color;

	@Column(name = "id_item_parte")
	private Integer idParte;

	@Column(name = "cant_cumplida")
	private Integer cantCumplida;

	@Column(name = "ruta_plano")
	private String plano;
	
	@Column(name = "cant_fabricada")
	private Integer cantFabricada;

	public VistaPiezasOperarios() {
		super();
	}

	public String getId() {
		return id;
	}

	public Integer getIdRegPieza() {
		return idRegPieza;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public Integer getCt() {
		return ct;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public String getCliente() {
		return cliente;
	}

	public String getProyecto() {
		return co;
	}

	public Long getIdItem() {
		return IdItem;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public String getDescripcionItem() {
		return descripcionItem;
	}

	public Integer getCantReq() {
		return cantReq;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public Integer getCodErp() {
		return codErp;
	}

	/*public BigDecimal getLongitud() {
		return longitud;
	}*/

	public Integer getPrioridad() {
		return prioridad;
	}

	public Integer getIdOp() {
		return idOp;
	}

	public String getTipoOp() {
		return tipoOp;
	}

	public Integer getNumOp() {
		return numOp;
	}

	public String getEsquemaPintura() {
		return esquemaPintura;
	}

	public Integer getTiempoTrancurrido() {
		return tiempoTrancurrido;
	}

	public Integer getTiempoReproceso() {
		return tiempoReproceso;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}

	public String getColor() {
		return color;
	}

	public Integer getIdParte() {
		return idParte;
	}

	public Integer getCantCumplida() {
		return cantCumplida;
	}

	public String getPlano() {
		return plano;
	}

	public String getCo() {
		return co;
	}

	public Integer getCantFabricada() {
		return cantFabricada;
	}

	@Override
	public String toString() {
		return "VistaPiezasOperarios [id=" + id + ", idRegPieza=" + idRegPieza + ", idProceso=" + idProceso + ", ct="
				+ ct + ", idOperario=" + idOperario + ", isActivo=" + isActivo + ", cliente=" + cliente + ", co=" + co
				+ ", IdItem=" + IdItem + ", idItemFab=" + idItemFab + ", descripcionItem=" + descripcionItem
				+ ", cantReq=" + cantReq + ", peso=" + peso + ", codErp=" + codErp + ", longitud=" + longitud
				+ ", prioridad=" + prioridad + ", idOp=" + idOp + ", tipoOp=" + tipoOp + ", numOp=" + numOp
				+ ", esquemaPintura=" + esquemaPintura + ", tiempoTrancurrido=" + tiempoTrancurrido
				+ ", tiempoReproceso=" + tiempoReproceso + ", nombreOperario=" + nombreOperario + ", color=" + color
				+ ", idParte=" + idParte + ", cantCumplida=" + cantCumplida + ", plano=" + plano + ", cantFabricada="
				+ cantFabricada + "]";
	}

}
