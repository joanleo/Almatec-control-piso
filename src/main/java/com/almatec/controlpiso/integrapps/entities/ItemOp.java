package com.almatec.controlpiso.integrapps.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;

@Entity
@Table(name = "items_op")
public class ItemOp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;
	
	@Column(name = "id_op_ia")
	private Integer idOpIntegrapps;
	
	@Column(name = "Item_fab_Id")
	private Integer idItemFab;
	
	@Column(name ="grupo")
	private String grupo;
	
	@Column(name = "codigo_erp")
	private String codigoErp;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "marca")
	private String marca;
	
	@Column(name = "peso_unitario")
	private BigDecimal peso;

	@Column(name = "unidad")
	private String um;
	
	@Column(name = "cant_req", columnDefinition = "double precision")
	private Double cant;
	
	@Column(name = "especial")
	private Boolean isEspecial;
	
	@Column(name ="req_plano")
	private Boolean isReqPlano;
	
	@Column(name ="activo")
	private Boolean isActivo;
	
	@Column(name = "ruta_plano")
	private String rutaPlano;
	
	@Column(name = "fecha_crea")
	private Date fechaCreacion;
	
	@Column(name = "id_estado")
	private Integer idEstado;
	
	@Column(name = "cant_cumplida")
	private Double cantCumplida;
	
	@Column(name = "pintura")
	private String color;
	
	@Column(name = "grp_pintura")
	private String grupoColor;
	
	@Column(name = "cant_imp_eti")
	private Integer canEtiqueta;
	
	@Column(name = "cod_pintura")
	private Integer codigoPintura;
	
	@Column(name = "peso_pintura")
	private Double pesoPintura;
	
	@Column(name = "ct_comsumo")
	private Integer centroTConsumo;
	
	@Column(name = "cant_despacha")
	private Integer cantDespachada;

	public ItemOp() {
		super();
	}

	public ItemOp(ItemOpInterface itemInterface) {
		this.id = itemInterface.getitem_id();
		this.idOpIntegrapps = itemInterface.getid_op_ia();
		this.idItemFab = itemInterface.getItem_fab_Id();
		this.grupo = itemInterface.getgrupo();
		this.descripcion = itemInterface.getdescripcion();
		this.codigoErp = itemInterface.getcodigo_erp();
		this.marca = itemInterface.getmarca();
		this.peso = itemInterface.getpeso_unitario();
		this.um = itemInterface.getunidad();
		this.cant = itemInterface.getcant_req();
		this.isEspecial = itemInterface.getespecial();
		this.isReqPlano = itemInterface.getreq_plano();
		this.isActivo = itemInterface.getactivo();
		this.idEstado = itemInterface.getid_estado();
		this.rutaPlano = itemInterface.getruta_plano();
		this.fechaCreacion = itemInterface.getfecha_crea();
		this.cantCumplida = itemInterface.getcant_cumplida();
		this.color = itemInterface.getpintura();
		this.grupoColor = itemInterface.getgrp_pintura();
	}

	public Long getId() {
		return id;
	}

	public Integer getIdOpIntegrapps() {
		return idOpIntegrapps;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getCodigoErp() {
		return codigoErp;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public String getUm() {
		return um;
	}

	public Double getCant() {
		return cant;
	}

	public Boolean getIsEspecial() {
		return isEspecial;
	}

	public Boolean getIsReqPlano() {
		return isReqPlano;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public String getRutaPlano() {
		return rutaPlano;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public Double getCantCumplida() {
		return cantCumplida;
	}

	public String getColor() {
		return color;
	}

	public String getGrupoColor() {
		return grupoColor;
	}

	public Integer getCanEtiqueta() {
		return canEtiqueta;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdOpIntegrapps(Integer idPvIntegrapps) {
		this.idOpIntegrapps = idPvIntegrapps;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void setCodigoErp(String codigoErp) {
		this.codigoErp = codigoErp;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public void setCant(Double cant) {
		this.cant = cant;
	}

	public void setIsEspecial(Boolean isEspecial) {
		this.isEspecial = isEspecial;
	}

	public void setIsReqPlano(Boolean isReqPlano) {
		this.isReqPlano = isReqPlano;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public void setRutaPlano(String rutaPlano) {
		this.rutaPlano = rutaPlano;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public void setCantCumplida(Double cantCumplida) {
		this.cantCumplida = cantCumplida;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setGrupoColor(String grupoColor) {
		this.grupoColor = grupoColor;
	}

	public void setCanEtiqueta(Integer canEtiqueta) {
		this.canEtiqueta = canEtiqueta;
	}

	public Integer getCodigoPintura() {
		return codigoPintura;
	}

	public Double getPesoPintura() {
		return pesoPintura;
	}

	public void setCodigoPintura(Integer codigoPintura) {
		this.codigoPintura = codigoPintura;
	}

	public void setPesoPintura(Double pesoPintura) {
		this.pesoPintura = pesoPintura;
	}

	public Integer getCentroTConsumo() {
		return centroTConsumo;
	}

	public void setCentroTConsumo(Integer centroTCnsumo) {
		this.centroTConsumo = centroTCnsumo;
	}

	public Integer getCantDespachada() {
		return cantDespachada;
	}

	public void setCantDespachada(Integer cantDespachada) {
		this.cantDespachada = cantDespachada;
	}

	@Override
	public String toString() {
		return "ItemOp [id=" + id + ", idOpIntegrapps=" + idOpIntegrapps + ", idItemFab=" + idItemFab + ", grupo="
				+ grupo + ", codigoErp=" + codigoErp + ", descripcion=" + descripcion + ", marca=" + marca + ", peso="
				+ peso + ", um=" + um + ", cant=" + cant + ", isEspecial=" + isEspecial + ", isReqPlano=" + isReqPlano
				+ ", isActivo=" + isActivo + ", rutaPlano=" + rutaPlano + ", fechaCreacion=" + fechaCreacion
				+ ", idEstado=" + idEstado + ", cantCumplida=" + cantCumplida + ", color=" + color + ", grupoColor="
				+ grupoColor + ", canEtiqueta=" + canEtiqueta + ", codigoPintura=" + codigoPintura + ", pesoPintura="
				+ pesoPintura + ", centroTConsumo=" + centroTConsumo + ", cantDespachada=" + cantDespachada + "]";
	}	

}
