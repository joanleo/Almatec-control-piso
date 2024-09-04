package com.almatec.controlpiso.integrapps.dtos;

public class ResponseMessage {
	
	private Boolean error = false;	
	private String mensaje;
	
	public ResponseMessage() {
		super();
	}
		
	public ResponseMessage(Boolean error, String mensaje) {
		super();
		this.error = error;
		this.mensaje = mensaje;
	}

	public ResponseMessage(String mensaje) {
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

