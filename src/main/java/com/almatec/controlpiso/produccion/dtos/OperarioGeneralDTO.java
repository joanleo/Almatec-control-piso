package com.almatec.controlpiso.produccion.dtos;

import java.util.List;
import java.util.Set;

public class OperarioGeneralDTO {

	private Integer id;
	private Integer sexo;
	private Integer tipoDoc;
	private String numDoc;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String celular;
	private String email;
	private Boolean isActivo;
	private Long idCentroT;
	private Integer idPersona;
	private List<Integer> centroTrabajoIds;
	
	public OperarioGeneralDTO() {
		super();
	}

	public OperarioGeneralDTO(Integer id, Integer sexo, Integer tipoDoc, String numDoc, String nombres,
			String apellidos, String direccion, String celular, String email, Boolean isActivo, Long idCentroT, Integer idPersona) {
		super();
		this.id = id;
		this.sexo = sexo;
		this.tipoDoc = tipoDoc;
		this.numDoc = numDoc;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.celular = celular;
		this.email = email;
		this.isActivo = isActivo;
		this.idCentroT = idCentroT;
		this.idPersona = idPersona;
	}

	public Integer getId() {
		return id;
	}

	public Integer getSexo() {
		return sexo;
	}

	public Integer getTipoDoc() {
		return tipoDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public String getNombres() {
		return nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getCelular() {
		return celular;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public void setTipoDoc(Integer tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Long getIdCentroT() {
		return idCentroT;
	}

	public void setIdCentroT(Long idCentroT) {
		this.idCentroT = idCentroT;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public List<Integer> getCentroTrabajoIds() {
		return centroTrabajoIds;
	}

	public void setCentroTrabajoIds(List<Integer> centroTrabajoIds) {
		this.centroTrabajoIds = centroTrabajoIds;
	}

	@Override
	public String toString() {
		return "OperarioGeneralDTO [id=" + id + ", sexo=" + sexo + ", tipoDoc=" + tipoDoc + ", numDoc=" + numDoc
				+ ", nombres=" + nombres + ", apellidos=" + apellidos + ", direccion=" + direccion + ", celular="
				+ celular + ", email=" + email + ", isActivo=" + isActivo + ", idCentroT=" + idCentroT + ", idPersona="
				+ idPersona + ", centroTrabajoIds=" + centroTrabajoIds + "]";
	}
	
}
