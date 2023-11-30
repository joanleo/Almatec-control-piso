package com.almatec.controlpiso.integrapps.entities;

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
	
	@Column(name = "id_flia")
	private Integer idFamilia;
	
	@Column(name ="id_grp_item")
	private Integer idGrupoItem;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "Medida_1")
	private String medida1;
	
	@Column(name = "Medida_2")
	private String medida2;
	
	@Column(name = "Medida_3")
	private String medida3;
	
	@Column(name = "peso_unitario")
	private String peso;

	@Column(name = "unidad")
	private String um;
	
	@Column(name = "cant_req")
	private String cant;
	
	@Column(name = "especial")
	private Boolean isEspecial;
	
	@Column(name ="req_plano")
	private Boolean isReqPlano;
	
	@Column(name ="activo")
	private Boolean isActivo;
	
	@Column(name = "ruta_plano")
	private String rutaPlano;
	
	@Column(name = "agrupa")
	private String agrupador;
	
	@Column(name = "id_estado")
	private Integer idEstado;

	public ItemOp() {
		super();
	}

	public ItemOp(ItemOpInterface itemInterface) {
		this.id = itemInterface.getitem_id();
		this.idPvIntegrapps = itemInterface.getid_op_ia();
		this.idFamilia = itemInterface.getid_flia();
		this.idGrupoItem = itemInterface.getid_grp_item();
		this.descripcion = itemInterface.getdescripcion();
		this.medida1 = itemInterface.getMedida_1();
		this.medida2 = itemInterface.getMedida_2();
		this.medida3 = itemInterface.getMedida_3();
		this.peso = itemInterface.getpeso_unitario();
		this.um = itemInterface.getunidad();
		this.cant = itemInterface.getcant_req();
		this.isEspecial = itemInterface.getespecial();
		this.isReqPlano = itemInterface.getreq_plano();
		this.isActivo = itemInterface.getactivo();
		this.agrupador = itemInterface.getagrupa();
		this.idEstado = itemInterface.getid_estado();
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

	public Integer getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(Integer idFamilia) {
		this.idFamilia = idFamilia;
	}

	public Integer getIdGrupoItem() {
		return idGrupoItem;
	}

	public void setIdGrupoItem(Integer idGrupoItem) {
		this.idGrupoItem = idGrupoItem;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMedida1() {
		return medida1;
	}

	public void setMedida1(String medida1) {
		this.medida1 = medida1;
	}

	public String getMedida2() {
		return medida2;
	}

	public void setMedida2(String medida2) {
		this.medida2 = medida2;
	}

	public String getMedida3() {
		return medida3;
	}

	public void setMedida3(String medida3) {
		this.medida3 = medida3;
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

	public String getCant() {
		return cant;
	}

	public void setCant(String cant) {
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

	public String getAgrupador() {
		return agrupador;
	}

	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	@Override
	public String toString() {
		return "ItemOp [id=" + id + ", idPvIntegrapps=" + idPvIntegrapps + ", idFamilia=" + idFamilia + ", idGrupoItem="
				+ idGrupoItem + ", descripcion=" + descripcion + ", medida1=" + medida1 + ", medida2=" + medida2
				+ ", medida3=" + medida3 + ", precioUnitario=" + peso + ", um=" + um + ", cant=" + cant
				+ ", isEspecial=" + isEspecial + ", isReqPlano=" + isReqPlano + ", isActivo=" + isActivo
				+ ", rutaPlano=" + rutaPlano + ", agrupador=" + agrupador + ", idEstado=" + idEstado + "]";
	}
	
	


}
