package com.almatec.controlpiso.integrapps.entities;

import java.util.Date;
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
import javax.persistence.Table;

import com.almatec.controlpiso.security.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "remision")
public class Remision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_remision")
	private Long idRemision;
	
	@Column(name = "id_op_ia")
	private Integer idOpIa;
	
	private String observaciones;
	
	private String notas;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_crea")
	@JsonManagedReference
	private Usuario usuarioCreaRemision;
	
	private Date fechaCreacion;
	
	@OneToMany(mappedBy = "remision", cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	private List<DetalleRemision> detalles;
	
	@PrePersist
	protected void onCreate() {
		this.fechaCreacion = new Date();
	}

	public Remision() {
		super();
	}

	public Long getIdRemision() {
		return idRemision;
	}

	public Integer getIdOpIa() {
		return idOpIa;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public String getNotas() {
		return notas;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setIdRemision(Long idRemision) {
		this.idRemision = idRemision;
	}

	public void setIdOpIa(Integer idOpIa) {
		this.idOpIa = idOpIa;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<DetalleRemision> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleRemision> detalles) {
		this.detalles = detalles;
	}

	public Usuario getUsuarioCreaRemision() {
		return usuarioCreaRemision;
	}

	public void setUsuarioCreaRemision(Usuario usuarioCreaRemision) {
		this.usuarioCreaRemision = usuarioCreaRemision;
	}

	@Override
	public String toString() {
		return "Remision [idRemision=" + idRemision + ", idOpIa=" + idOpIa + ", observaciones=" + observaciones
				+ ", notas=" + notas + ", usuarioCreaRemision=" + usuarioCreaRemision + ", fechaCreacion="
				+ fechaCreacion +  "]";
	}
	
	
	
}
