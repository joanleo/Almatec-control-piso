package com.almatec.controlpiso.exceptions;

public class RutaItemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoError;
    private String detallesTecnicos;
    
    public RutaItemException(String mensaje, String codigoError, String detallesTecnicos) {
        super(mensaje);
        this.codigoError = codigoError;
        this.detallesTecnicos = detallesTecnicos;
    }
    
    public RutaItemException(String message) {
        super(message);
    }

    public RutaItemException(String message, Throwable cause) {
        super(message, cause);
    }
    // Getters para acceder a la información adicional del error
    public String getCodigoError() {
        return codigoError;
    }
    
    public String getDetallesTecnicos() {
        return detallesTecnicos;
    }

}
