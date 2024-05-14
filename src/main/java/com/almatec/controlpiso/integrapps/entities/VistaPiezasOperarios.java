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
	
	/*@Column(name = "C_ciaorg_id")
	private Integer idCia;*/
	
	@Column(name = "id_centro_trabajo")
	private Integer ct;
	
	@Column(name = "id_operario")
	private Integer idOperario;
	
	@Column(name = "is_pieza_activa")
	private Boolean isActivo;
	
	private String cliente;
	
	private String proyecto;
	
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
	private Integer cantCumlida;
	
	@Column(name = "unidad_negocio")
	private String und_negocio;
	
	@Column(name = "ruta_plano")
	private String plano;

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
		return proyecto;
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

	public BigDecimal getLongitud() {
		return longitud;
	}

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

	public Integer getCantCumlida() {
		return cantCumlida;
	}

	public String getUnd_negocio() {
		return und_negocio;
	}

	public String getPlano() {
		return plano;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdRegPieza(Integer idRegPieza) {
		this.idRegPieza = idRegPieza;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public void setCt(Integer ct) {
		this.ct = ct;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setIdItem(Long idItem) {
		IdItem = idItem;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}

	public void setCantReq(Integer cantReq) {
		this.cantReq = cantReq;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public void setCodErp(Integer codErp) {
		this.codErp = codErp;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public void setIdOp(Integer idOp) {
		this.idOp = idOp;
	}

	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}

	public void setNumOp(Integer numOp) {
		this.numOp = numOp;
	}

	public void setEsquemaPintura(String esquemaPintura) {
		this.esquemaPintura = esquemaPintura;
	}

	public void setTiempoTrancurrido(Integer tiempoTrancurrido) {
		this.tiempoTrancurrido = tiempoTrancurrido;
	}

	public void setTiempoReproceso(Integer tiempoReproceso) {
		this.tiempoReproceso = tiempoReproceso;
	}

	public void setNombreOperario(String nombreOperario) {
		this.nombreOperario = nombreOperario;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setIdParte(Integer idParte) {
		this.idParte = idParte;
	}

	public void setCantCumlida(Integer cantCumlida) {
		this.cantCumlida = cantCumlida;
	}

	public void setUnd_negocio(String und_negocio) {
		this.und_negocio = und_negocio;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	@Override
	public String toString() {
		return "VistaPiezasOperarios [id=" + id + ", idRegPieza=" + idRegPieza + ", idProceso=" + idProceso + ", ct="
				+ ct + ", idOperario=" + idOperario + ", isActivo=" + isActivo + ", cliente=" + cliente + ", proyecto="
				+ proyecto + ", IdItem=" + IdItem + ", idItemFab=" + idItemFab + ", descripcionItem=" + descripcionItem
				+ ", cantReq=" + cantReq + ", peso=" + peso + ", codErp=" + codErp + ", longitud=" + longitud
				+ ", prioridad=" + prioridad + ", idOp=" + idOp + ", tipoOp=" + tipoOp + ", numOp=" + numOp
				+ ", esquemaPintura=" + esquemaPintura + ", tiempoTrancurrido=" + tiempoTrancurrido
				+ ", tiempoReproceso=" + tiempoReproceso + ", nombreOperario=" + nombreOperario + ", color=" + color
				+ ", idParte=" + idParte + ", cantCumlida=" + cantCumlida + ", und_negocio=" + und_negocio + ", plano="
				+ plano + "]";
	}

	
}
