package com.almatec.controlpiso.utils.dto;

public class OperarioBarCodeDTO {
	
	private String nombres;
	private String barcode;
	
	public OperarioBarCodeDTO() {
		super();
	}
	public OperarioBarCodeDTO(String nombres, String barcode) {
		super();
		this.nombres = nombres;
		this.barcode = barcode;
	}
	public String getNombres() {
		return nombres;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
