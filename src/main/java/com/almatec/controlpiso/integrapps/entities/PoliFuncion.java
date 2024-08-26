package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "poli_funcion")
public class PoliFuncion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Emp_Poli")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "C_prooperario_id")
    @JsonIgnore
    private Operario operario;

    @ManyToOne
    @JoinColumn(name = "C_centrotrabajo_id")
    private CentroTrabajo centroTrabajo;

	public PoliFuncion() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public Operario getOperario() {
		return operario;
	}

	public CentroTrabajo getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOperario(Operario operario) {
		this.operario = operario;
	}

	public void setCentroTrabajo(CentroTrabajo centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

    
}
