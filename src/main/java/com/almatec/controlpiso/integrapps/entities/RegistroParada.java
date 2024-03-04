package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pro_regparada")
public class RegistroParada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_regparada_id")
	private Long idRegParada;
	
	@Column(name = "C_proconfigproceso_id")
	private Integer idConfigProceso;
	
	@Column(name = "C_prooperario_id")
	private Integer idOperario;
	
	@Column(name = "C_proparada_id")
	private Long idParada;
	
	@Column(name = "N_sstranscurrido")	
	private Float segundosTrans = 0.0f;
	
	@Column(name = "FC_registro")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "FE_registro")
	private LocalDateTime fechaEdicion;
	
	@Column(name = "E_activo")
	private Boolean isActivo = true;

	public RegistroParada() {
		super();
	}

	public Long getIdRegParada() {
		return idRegParada;
	}

	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}

	public Integer getIdOperario() {
		return idOperario;
	}

	public Long getIdParada() {
		return idParada;
	}

	public Float getSegundosTrans() {
		return segundosTrans;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDateTime getFechaEdicion() {
		return fechaEdicion;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIdRegParada(Long idRegParada) {
		this.idRegParada = idRegParada;
	}

	public void setIdConfigProceso(Integer idConfigProceso) {
		this.idConfigProceso = idConfigProceso;
	}

	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}

	public void setIdParada(Long idParada) {
		this.idParada = idParada;
	}

	public void setSegundosTrans(Float segundosTrans) {
		this.segundosTrans = segundosTrans;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setFechaEdicion(LocalDateTime fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	@Override
	public String toString() {
		return "RegistroParada [idRegParada=" + idRegParada + ", idConfigProceso=" + idConfigProceso + ", idOperario="
				+ idOperario + ", idParada=" + idParada + ", segundosTrans=" + segundosTrans + ", fechaCreacion="
				+ fechaCreacion + ", fechaEdicion=" + fechaEdicion + ", isActivo=" + isActivo + "]";
	}
	
	
	

}
