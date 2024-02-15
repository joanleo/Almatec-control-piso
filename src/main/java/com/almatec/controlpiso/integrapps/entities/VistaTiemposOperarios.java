package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Immutable
@Entity
@Table(name = "tiempo_operarios_proceso")
public class VistaTiemposOperarios {
	
	@Id
	@Column(name = "C_proconfigproceso_id")
	private Integer idConfigProceso;
	
	@Column(name = "F_turnoini")
	private LocalDateTime fechaInicioTurno;
	
	@Column(name = "F_turnofin")
	private LocalDateTime fechaFinTurno;
	
	@Column(name = "N_ssimproductivo")	
	private Float improductivo;
	
	@Column(name = "C_Pro_Item_Id")
	private Integer idItem;
	
	@Column(name = "estado_turno")  
	private Boolean isActiveTurno;
	
	@Column(name = "TmpActivo_A") 
	private Float productivo;
	
	//TmpActivo
	@Column(name = "C_prooperario_id")
	private Integer idOperario;
	//E_activo
	@Column(name = "A_Operario_Nombre")
	private String nombreOperario;
	//F_configuracion
	//C_proparada_id
	@Column(name = "parada")
	private String status;
	//A_turno
	private String turno;
	//C_ciaorg_id
	//centro_trabajo
	public VistaTiemposOperarios() {
		super();
	}
	public Integer getIdConfigProceso() {
		return idConfigProceso;
	}
	public void setIdConfigProceso(Integer idConfigProceso) {
		this.idConfigProceso = idConfigProceso;
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
	public Boolean getIsActiveTurno() {
		return isActiveTurno;
	}
	public void setIsActiveTurno(Boolean isActiveTurno) {
		this.isActiveTurno = isActiveTurno;
	}
	public Float getProductivo() {
		return productivo;
	}
	public void setProductivo(Float productivo) {
		this.productivo = productivo;
	}
	public Integer getIdOperario() {
		return idOperario;
	}
	public void setIdOperario(Integer idOperario) {
		this.idOperario = idOperario;
	}
	public String getNombreOperario() {
		return nombreOperario;
	}
	public void setNombreOperario(String nombreOperario) {
		this.nombreOperario = nombreOperario;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	

}
