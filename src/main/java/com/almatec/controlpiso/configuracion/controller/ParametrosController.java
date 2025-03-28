package com.almatec.controlpiso.configuracion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.integrapps.dtos.ParametroDTO;
import com.almatec.controlpiso.integrapps.services.ParametroService;


@Controller
@RequestMapping("/parametros")
public class ParametrosController {
	
	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	@GetMapping("/listar")
	public String listarParametros() {
		return "parametros/listar-parametros";
	}
	
	@GetMapping
	public ResponseEntity<List<ParametroDTO>> obtenerParametros(){
		return ResponseEntity.ok(parametroService.obtenerParametrosDTO());
	}
	
	@PostMapping
	public ResponseEntity<?> guardarParametros(@RequestBody List<ParametroDTO> parametrosDTO){
		Map<String, Object> response = new HashMap<>();
		try {
			parametroService.guardarParametros(parametrosDTO);
			configurationService.recargarParametros();
			response.put("status", "success");
	        response.put("message", "Cambios guardados exitosamente");
	        return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.put("status", "error");
	        response.put("message", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
