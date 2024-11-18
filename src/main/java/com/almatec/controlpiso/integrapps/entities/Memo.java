package com.almatec.controlpiso.integrapps.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.almatec.controlpiso.security.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "memos")
public class Memo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_memo")
	private Long id;
	
	@Column(name = "id_op_ia")
	private Integer idOpIntegrapps;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_crea")
	private Usuario usuarioCrea;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_aprueba")
	private Usuario usuarioAprueba;
	
	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "fecha_aprobacion")
	private LocalDateTime fechaAprobacion;
	
	@Column(name = "fecha_actualizacion")
	private LocalDateTime fechaActualizacion;
	
	@Column(name = "id_estado")
	private Integer idEstado = 0;
	
	@JsonIgnoreProperties("memo")
	@OneToMany(mappedBy = "memo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MemoDetalle> detalles;
	
	
	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.fechaCreacion = now;
		this.fechaActualizacion = now;
	}
	
	@PreUpdate
	protected void onUpDate() {
		this.fechaActualizacion = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public Integer getIdOpIntegrapps() {
		return idOpIntegrapps;
	}

	public Usuario getUsuarioCrea() {
		return usuarioCrea;
	}

	public Usuario getUsuarioAprueba() {
		return usuarioAprueba;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDateTime getFechaAprobacion() {
		return fechaAprobacion;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public List<MemoDetalle> getDetalles() {
		return detalles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdOpIntegrapps(Integer idOpIntegrapps) {
		this.idOpIntegrapps = idOpIntegrapps;
	}

	public void setUsuarioCrea(Usuario usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public void setUsuarioAprueba(Usuario usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setFechaAprobacion(LocalDateTime fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public void setDetalles(List<MemoDetalle> detalle) {
		this.detalles = detalle;
	}

	public Memo() {
		super();
	}
}
