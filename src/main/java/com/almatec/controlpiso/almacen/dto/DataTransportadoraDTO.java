package com.almatec.controlpiso.almacen.dto;

public class DataTransportadoraDTO {
	
	private String nombre;
	private String vehiculo;
	private String conductor;
	private String placa;
	private String cedula;
	private String pase;
	private String celular;
	private Double pesoEntrada;
	private Double pesoSalida;
	private String horaEntrada;
	private String horaSalida;
	
	public DataTransportadoraDTO() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public String getConductor() {
		return conductor;
	}

	public String getPlaca() {
		return placa;
	}

	public String getCedula() {
		return cedula;
	}

	public String getPase() {
		return pase;
	}

	public String getCelular() {
		return celular;
	}

	public Double getPesoEntrada() {
		return pesoEntrada;
	}

	public Double getPesoSalida() {
		return pesoSalida;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setPase(String pase) {
		this.pase = pase;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setPesoEntrada(Double pesoEntrada) {
		this.pesoEntrada = pesoEntrada;
	}

	public void setPesoSalida(Double pesoSalida) {
		this.pesoSalida = pesoSalida;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	
	

}
