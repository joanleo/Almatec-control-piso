package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pro_turnos")
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_proturnos_id")
	private Long id;
	
	@Column(name = "C_ciaorg_id")
	private Integer cia;
	
	@Column(name = "A_co")
	private String co;
	
	@Column(name = "A_turno")
	private String turno;
	
	@Column(name = "A_descripcion")
	private String descripcion;
	
	@Column(name = "N_orden")
	private Integer orden;
	
	@Column(name = "A_turnoini")
	private String inicio;
	
	@Column(name = "A_turnofin")
	private String fin;
	
	@Column(name = "E_activo")
	private Boolean isActivo;
	
	@Column(name = "A_usuariocrea")
	private String usuarioCrea;
	
	@Column(name = "A_usuarioedita")
	private String usuarioEdita;
	
	@Column(name = "FC_registro")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "FE_registro")
	private LocalDateTime fechaEdicion;

	public Turno() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
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

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(LocalDateTime fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	@Override
	public String toString() {
		return "Turno [id=" + id + ", cia=" + cia + ", co=" + co + ", turno=" + turno + ", descripcion=" + descripcion
				+ ", orden=" + orden + ", inicio=" + inicio + ", fin=" + fin + ", isActivo=" + isActivo
				+ ", usuarioCrea=" + usuarioCrea + ", usuarioEdita=" + usuarioEdita + ", fechaCreacion=" + fechaCreacion
				+ ", fechaEdicion=" + fechaEdicion + "]";
	}


}
