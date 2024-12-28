package com.almatec.controlpiso.integrapps.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "integraciopnes_erp")
public class EventosErp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "tipo_doc")
	private String tipoDoc;
	
	private Integer consecutivo;
	
	private Boolean estado;

	public EventosErp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public Integer getConsecutivo() {
		return consecutivo;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

}
