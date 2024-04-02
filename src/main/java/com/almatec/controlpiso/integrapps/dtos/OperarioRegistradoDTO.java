package com.almatec.controlpiso.integrapps.dtos;

import com.almatec.controlpiso.integrapps.interfaces.OperarioRegistrado;

public class OperarioRegistradoDTO {

	private String operarioNombre;
	private String ctNombre;
	private String turnoDescripcion;
	private Boolean estado;
	private String paradaNombre;
	
	public OperarioRegistradoDTO() {
		super();
	}
	
	public OperarioRegistradoDTO(OperarioRegistrado operario) {
		this.operarioNombre = operario.getoperario_nombre();
		this.ctNombre = operario.getct_nombre();
		this.turnoDescripcion = operario.getturno_descripcion();
		this.estado = operario.getestado();
		this.paradaNombre = operario.getparada_nombre();
	}

	public String getOperarioNombre() {
		return operarioNombre;
	}

	public String getCtNombre() {
		return ctNombre;
	}

	public String getTurnoDescripcion() {
		return turnoDescripcion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public String getParadaNombre() {
		return paradaNombre;
	}

	public void setOperarioNombre(String operarioNombre) {
		this.operarioNombre = operarioNombre;
	}

	public void setCtNombre(String ctNombre) {
		this.ctNombre = ctNombre;
	}

	public void setTurnoDescripcion(String turnoDescripcion) {
		this.turnoDescripcion = turnoDescripcion;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public void setParadaNombre(String paradaNombre) {
		this.paradaNombre = paradaNombre;
	}

	@Override
	public String toString() {
		return "OperarioRegistradoDTO [operarioNombre=" + operarioNombre + ", ctNombre=" + ctNombre
				+ ", turnoDescripcion=" + turnoDescripcion + ", estado=" + estado + ", paradaNombre=" + paradaNombre
				+ "]";
	}
	
	
	
}
