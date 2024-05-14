package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tiempo_operarios_proceso")
public class VistaTiemposOperarios {
	
	@EmbeddedId
	private VistaTiemposOperariosId id;
	
	@Column(name = "F_turnoini")
	private LocalDateTime fechaInicioTurno;
	
	@Column(name = "F_turnofin")
	private LocalDateTime fechaFinTurno;
	
	@Column(name = "N_ssimproductivo")	
	private Float improductivo;
	
	@Column(name = "C_Pro_Item_Id")
	private Integer idItem;
	
	@Column(name = "TmpActivo_A") 
	private Float productivo;
	
	@Column(name = "E_activo")
	private Boolean isActivo;
	
	@Column(name = "A_Operario_Nombre")
	private String nombreOperario;

	private String turno;

	public VistaTiemposOperarios() {
		super();
	}

	public LocalDateTime getFechaInicioTurno() {
		return fechaInicioTurno;
	}
	public void setFechaInicioTurno(LocalDateTime fechaInicioTurno) {
		this.fechaInicioTurno = fechaInicioTurno;
	}
	public LocalDateTime getFechaFinTurno() {
		return fechaFinTurno;
	}
	public void setFechaFinTurno(LocalDateTime fechaFinTurno) {
		this.fechaFinTurno = fechaFinTurno;
	}
	public Float getImproductivo() {
		return improductivo;
	}
	public void setImproductivo(Float improductivo) {
		this.improductivo = improductivo;
	}
	public Integer getIdItem() {
		return idItem;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	public Float getProductivo() {
		return productivo;
	}
	public void setProductivo(Float productivo) {
		this.productivo = productivo;
	}

	public String getNombreOperario() {
		return nombreOperario;
	}
	public void setNombreOperario(String nombreOperario) {
		this.nombreOperario = nombreOperario;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}

	public VistaTiemposOperariosId getId() {
		return id;
	}	

	public void setId(VistaTiemposOperariosId id) {
		this.id = id;
	}
	
	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	@Override
	public String toString() {
		return "VistaTiemposOperarios [id=" + id + ", fechaInicioTurno=" + fechaInicioTurno + ", fechaFinTurno="
				+ fechaFinTurno + ", improductivo=" + improductivo + ", idItem=" + idItem + ", productivo=" + productivo 
				+ ", isActivo=" + isActivo + ", nombreOperario="
				+ nombreOperario + ", turno=" + turno + "]";
	}

	

}
