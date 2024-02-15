package com.almatec.controlpiso.integrapps.dtos;

public class ErrorMensaje {
	
	private Boolean error;	
	private String mensaje;
	
	public ErrorMensaje() {
		super();
	}
		
	public ErrorMensaje(Boolean error, String mensaje) {
		super();
		this.error = error;
		this.mensaje = mensaje;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}	

}

