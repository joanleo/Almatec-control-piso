package com.almatec.controlpiso.comunicador.dtos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.almatec.controlpiso.comunicador.entities.TipoNotificacion;


public class TipoNotificacionDTO {
	
	private Integer id;	
	private String tipo;	
	private List<String> emails = new ArrayList<>();	
	private Boolean estado = true;
	
	public TipoNotificacionDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public List<String> getEmails() {
		return emails;
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

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
	public static TipoNotificacionDTO fromEntity(TipoNotificacion entity){
		
		TipoNotificacionDTO dto = new TipoNotificacionDTO();
		dto.setId(entity.getId());
		dto.setTipo(entity.getTipo());
		dto.setEmails(new ArrayList<>(Arrays.asList(entity.getEmailList().split(","))));
		dto.setEstado(entity.getEstado());
		
		return dto;
	}
	
	public TipoNotificacion toEntity() {
		TipoNotificacion entity = new TipoNotificacion();
        entity.setId(this.id);
        entity.setTipo(this.tipo);
        entity.setEmailList(String.join(",", this.emails));
        return entity;
    }
	

}
