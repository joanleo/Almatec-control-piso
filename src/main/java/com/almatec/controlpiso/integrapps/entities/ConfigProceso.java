package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pro_configproceso")
public class ConfigProceso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_proconfigproceso_id")
	private Integer id;
	
	@Column(name = "C_ciaorg_id")
	private Integer idCia;
	
	@ManyToOne
	@JoinColumn(name = "C_centrotrabajo_id")
	private CentroTrabajo idCentroTrabajo;
	
	@Column(name = "C_proturnos_id")
	private Integer idTurno;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "FC_registro")
	private Date fechaRegistro;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "FE_registro")
	private Date fechaEdicionRegistro;

	@Column(name = "A_usuariocrea")
	private String usuarioCrea;
	
	@Column(name = "A_usuarioedita")
	private String usuarioEdita;
	
	@Column(name = "C_estadoproceso_id")
	private Integer idEstadoProceso;
	
	@Column(name = "E_activo")
	private Boolean isActivo;
	
	@Column(name = "F_configuracion")
	private Date fechaConfiguracion;
	
	@Column(name = "C_productivo")
	private Long productivo;
	
	@Column(name = "C_improductivo")
	private Long improductivo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "F_ultimo_registro")
	private Date fechaEUltimoRegistro;
	
	@Column(name = "C_proparada_id")
	private Long idParada;
	
	@Column(name = "E_notificado")
	private Boolean isNotificado;
	
	@Column(name = "E_servicio")
	private Boolean isServicio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCia() {
		return idCia;
	}

	public void setIdCia(Integer idCia) {
		this.idCia = idCia;
	}

	public CentroTrabajo getIdCentroTrabajo() {
		return idCentroTrabajo;
	}

	public void setIdCentroTrabajo(CentroTrabajo idCentroTrabajo) {
		this.idCentroTrabajo = idCentroTrabajo;
	}

	public Integer getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Integer idTurno) {
		this.idTurno = idTurno;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaEdicionRegistro() {
		return fechaEdicionRegistro;
	}

	public void setFechaEdicionRegistro(Date fechaEdicionRegistro) {
		this.fechaEdicionRegistro = fechaEdicionRegistro;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public String getUsuarioEdita() {
		return usuarioEdita;
	}

	public void setUsuarioEdita(String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	public Integer getIdEstadoProceso() {
		return idEstadoProceso;
	}

	public void setIdEstadoProceso(Integer idEstadoProceso) {
		this.idEstadoProceso = idEstadoProceso;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Date getFechaConfiguracion() {
		return fechaConfiguracion;
	}

	public void setFechaConfiguracion(Date fechaConfiguracion) {
		this.fechaConfiguracion = fechaConfiguracion;
	}

	public Long getProductivo() {
		return productivo;
	}

	public void setProductivo(Long productivo) {
		this.productivo = productivo;
	}

	public Long getImproductivo() {
		return improductivo;
	}

	public void setImproductivo(Long improductivo) {
		this.improductivo = improductivo;
	}

	public Date getFechaEUltimoRegistro() {
		return fechaEUltimoRegistro;
	}

	public void setFechaEUltimoRegistro(Date fechaEUltimoRegistro) {
		this.fechaEUltimoRegistro = fechaEUltimoRegistro;
	}

	public Long getIdParada() {
		return idParada;
	}

	public void setIdParada(Long idParada) {
		this.idParada = idParada;
	}

	public Boolean getIsNotificado() {
		return isNotificado;
	}

	public void setIsNotificado(Boolean isNotificado) {
		this.isNotificado = isNotificado;
	}

	public Boolean getIsServicio() {
		return isServicio;
	}

	public void setIsServicio(Boolean isServicio) {
		this.isServicio = isServicio;
	}

	@Override
	public String toString() {
		return "ConfigProceso [id=" + id + ", idCia=" + idCia + ", idCentroTrabajo=" + idCentroTrabajo + ", idTurno="
				+ idTurno + ", fechaRegistro=" + fechaRegistro + ", fechaEdicionRegistro=" + fechaEdicionRegistro
				+ ", usuarioCrea=" + usuarioCrea + ", usuarioEdita=" + usuarioEdita + ", idEstadoProceso="
				+ idEstadoProceso + ", isActivo=" + isActivo + ", fechaConfiguracion=" + fechaConfiguracion
				+ ", productivo=" + productivo + ", improductivo=" + improductivo + ", fechaEUltimoRegistro="
				+ fechaEUltimoRegistro + ", idParada=" + idParada + ", isNotificado=" + isNotificado + ", isServicio="
				+ isServicio + "]";
	}
	
	

}
