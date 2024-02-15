package com.almatec.controlpiso.integrapps.entities;

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
	private Integer idPvIntegrapps;
	
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
	private String peso;

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

	public ItemOp() {
		super();
	}

	public ItemOp(ItemOpInterface itemInterface) {
		this.id = itemInterface.getitem_id();
		this.idPvIntegrapps = itemInterface.getid_op_ia();
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
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIdPvIntegrapps() {
		return idPvIntegrapps;
	}

	public void setIdPvIntegrapps(Integer idPvIntegrapps) {
		this.idPvIntegrapps = idPvIntegrapps;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String pesoUnitario) {
		this.peso = pesoUnitario;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public Double getCant() {
		return cant;
	}

	public void setCant(Double cant) {
		this.cant = cant;
	}

	public Boolean getIsEspecial() {
		return isEspecial;
	}

	public void setIsEspecial(Boolean isEspecial) {
		this.isEspecial = isEspecial;
	}

	public Boolean getIsReqPlano() {
		return isReqPlano;
	}

	public void setIsReqPlano(Boolean isReqPlano) {
		this.isReqPlano = isReqPlano;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public String getRutaPlano() {
		return rutaPlano;
	}

	public void setRutaPlano(String rutaPlano) {
		this.rutaPlano = rutaPlano;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdItemFab() {
		return idItemFab;
	}

	public void setIdItemFab(Integer idItemFab) {
		this.idItemFab = idItemFab;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCodigoErp() {
		return codigoErp;
	}

	public void setCodigoErp(String codigoErp) {
		this.codigoErp = codigoErp;
	}

	@Override
	public String toString() {
		return "ItemOp [id=" + id + ", idPvIntegrapps=" + idPvIntegrapps + ", idItemFab=" + idItemFab + ", grupo="
				+ grupo + ", codigoErp=" + codigoErp + ", descripcion=" + descripcion + ", marca=" + marca + ", peso="
				+ peso + ", um=" + um + ", cant=" + cant + ", isEspecial=" + isEspecial + ", isReqPlano=" + isReqPlano
				+ ", isActivo=" + isActivo + ", rutaPlano=" + rutaPlano + ", fechaCreacion=" + fechaCreacion
				+ ", idEstado=" + idEstado + "]";
	}


}
