package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "centro_trabajo_tep_reportado")
public class CentroTrabajoTepReportado {
		    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "orden_produccion", nullable = false)
	private String ordenProduccion;
	
	@Column(name = "centro_trabajo_id", nullable = false)
	private Integer centroTrabajoId;
	
	// Constraint único para evitar duplicados
	@Column(name = "unique_constraint", unique = true)
	private String uniqueConstraint;
	
	
	@PrePersist
	public void prePersist() {
	    // Crear constraint único combinando orden y centro
	    this.uniqueConstraint = this.ordenProduccion + "_" + this.centroTrabajoId;
	}


	public Long getId() {
		return id;
	}


	public String getOrdenProduccion() {
		return ordenProduccion;
	}


	public Integer getCentroTrabajoId() {
		return centroTrabajoId;
	}


	public String getUniqueConstraint() {
		return uniqueConstraint;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setOrdenProduccion(String ordenProduccion) {
		this.ordenProduccion = ordenProduccion;
	}


	public void setCentroTrabajoId(Integer centroTrabajoId) {
		this.centroTrabajoId = centroTrabajoId;
	}


	public void setUniqueConstraint(String uniqueConstraint) {
		this.uniqueConstraint = uniqueConstraint;
	}

}
