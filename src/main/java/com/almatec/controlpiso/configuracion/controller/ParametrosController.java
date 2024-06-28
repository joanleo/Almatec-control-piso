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
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.integrapps.dtos.ParametroDTO;
import com.almatec.controlpiso.integrapps.services.ParametroService;


@Controller
@RequestMapping("/parametros")
public class ParametrosController {
	
	@Autowired
	private ParametroService parametroService;
	
	@GetMapping("/listar")
	public String listarParametros() {
		return "parametros/listar-parametros";
	}
	
	@ResponseBody
	@GetMapping
	public List<ParametroDTO> obtenerParametros(){
		return parametroService.obtenerParametrosDTO();
	}
	
	@ResponseBody
	@PostMapping
	public ResponseEntity<?> guardarParametros(@RequestBody List<ParametroDTO> parametrosDTO){
		Map<String, Object> response = new HashMap<>();
		try {
			parametroService.guardarParametros(parametrosDTO);
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
