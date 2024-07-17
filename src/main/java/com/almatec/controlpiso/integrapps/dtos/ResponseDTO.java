package com.almatec.controlpiso.integrapps.dtos;

public class ResponseDTO {
	
	private String mensaje;
	private Object data;
	
	public ResponseDTO() {
		super();
	}

	public ResponseDTO(String mensaje, Object data) {
		super();
		this.mensaje = mensaje;
		this.data = data;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Object getData() {
		return data;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
