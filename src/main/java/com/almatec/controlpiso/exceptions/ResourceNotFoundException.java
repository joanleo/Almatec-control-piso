package com.almatec.controlpiso.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4097420474135939343L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}