package com.almatec.controlpiso.produccion.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ParadaDTO {
    private Long id;
    
    @Size(max = 250, message = "nombre no puede exceder 250 caracteres")
    private String nombre;
    
    @NotNull(message = "descripcion no puede ser nulo")
    @Size(max = 15, message = "descripcion no puede exceder 15 caracteres")
    private String descripcion;
       
    @NotNull(message = "activo no puede ser nulo")
    private Boolean isActivo;
    
    @Size(max = 15, message = "codBarHum no puede exceder 15 caracteres")
    private String codBarrasM;
    

	public ParadaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Boolean getIsActivo() {
		return isActivo;
	}

	public String getCodBarrasM() {
		return codBarrasM;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIsActivo(Boolean isActivo) {
		this.isActivo = isActivo;
	}

	public void setCodBarrasM(String codBarrasM) {
		this.codBarrasM = codBarrasM;
	}


    
}
