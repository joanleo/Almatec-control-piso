package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pro_operario")
public class Operario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_prooperario_id")
	private Integer id;
	
	@Column(name = "C_ciaorg_id")
	private Integer cia;
	
	@Column(name = "A_co")
	private String co;
	
	@Column(name = "Per_Id")
	private Integer idPersona;
	
	@Column(name = "C_Usuario_id")
	private Long idUsuario;
	
	@Column(name = "A_Usuariocrea")
	private String usuarioCrea;
	
	@Column(name = "FC_Registro")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "C_centrotrabajo_id")
	private Long IdCentroT;
	
	@Column(name = "F_configuracion")
	private LocalDateTime fechaConfig;
	
	@Column(name = "C_proturnos_id")
	private Long idTurno;
	
	@Column(name = "E_activo")
	private Boolean isActivo;
	
	@Column(name = "C_estadoproceso_id")
	private Integer idEstadoProceso;
	
	@Column(name = "A_Usuarioedita")
	private String usuarioEdita;
	
	@Column(name = "FE_Registro")
	private LocalDateTime fechaEdicion;
	
	@Column(name = "A_Operario_Nombre")
	private String nombre;

	public Operario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCia() {
		return cia;
	}

	public void setCia(Integer cia) {
		this.cia = cia;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getIdCentroT() {
		return IdCentroT;
	}

	public void setIdCentroT(Long idCentroT) {
		IdCentroT = idCentroT;
	}

	public LocalDateTime getFechaConfig() {
		return fechaConfig;
	}

	public void setFechaConfig(LocalDateTime fechaConfig) {
		this.fechaConfig = fechaConfig;
	}

	public Long getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Integer getIdEstadoProceso() {
		return idEstadoProceso;
	}

	public void setIdEstadoProceso(Integer idEstadoProceso) {
		this.idEstadoProceso = idEstadoProceso;
	}

	public String getUsuarioEdita() {
		return usuarioEdita;
	}

	public void setUsuarioEdita(String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	public LocalDateTime getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(LocalDateTime fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Operario [id=" + id + ", cia=" + cia + ", co=" + co + ", idPersona=" + idPersona + ", idUsuario="
				+ idUsuario + ", usuarioCrea=" + usuarioCrea + ", fechaCreacion=" + fechaCreacion + ", IdCentroT="
				+ IdCentroT + ", fechaConfig=" + fechaConfig + ", idTurno=" + idTurno + ", isActivo=" + isActivo
				+ ", idEstadoProceso=" + idEstadoProceso + ", usuarioEdita=" + usuarioEdita + ", fechaEdicion="
				+ fechaEdicion + ", nombre=" + nombre + "]";
	}

}
