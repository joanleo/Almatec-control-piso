package com.almatec.controlpiso.comunicador.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensajes_tipo")
public class TipoNotificacion {
	
	@Id
	@Column(name = "msg_tipo_id")
	private Integer id;
	
	@Column(name = "msg_tipo_desc")
	private String tipo;
	
	@Column(name = "msg_emails")
	private String emailList;
	
	@Column(name = "msg_tipo_activo")
	private Boolean estado = true;

	public TipoNotificacion() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public String getEmailList() {
		return emailList;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	

}
