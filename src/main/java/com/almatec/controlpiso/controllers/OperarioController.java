package com.almatec.controlpiso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.services.OperarioService;

@Controller
@RequestMapping("/operarios")
public class OperarioController {

	@Autowired
	private OperarioService operarioService;
	
	@ResponseBody
	@GetMapping("/{numCedula}")
	public ResponseEntity<?> obtenerOperario(@PathVariable Integer numCedula) {
		try {
			return ResponseEntity.ok(operarioService.obtenerOperario(numCedula));			
		}catch (ResourceNotFoundException error) {
			ErrorMensaje errorMessage = new ErrorMensaje(true, error.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		}
	}
}
