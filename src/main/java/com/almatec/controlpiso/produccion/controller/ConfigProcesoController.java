package com.almatec.controlpiso.produccion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.integrapps.dtos.ConfigProcesoDTO;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.entities.ConfigProceso;
import com.almatec.controlpiso.integrapps.services.ConfigProcesoService;

@Controller
@RequestMapping("/config-procesos")
public class ConfigProcesoController {

	@Autowired
	private ConfigProcesoService configProcesoService;
	
	@GetMapping("/hoy")
	public ResponseEntity<List<ConfigProceso>> listar() {
		System.out.println("solicitud para obtener configs");
		return ResponseEntity.ok(configProcesoService.obtenerConfigProcesosDia());
	}
	
	@GetMapping("/centro-trabajo/{idCentroTrabajo}/turno/{idTurno}")
	public ResponseEntity<ConfigProcesoDTO> configNuevoProceso(Model modelo, 
									@PathVariable Integer idCentroTrabajo, 
									@PathVariable Long idTurno) throws Exception {
		return ResponseEntity.ok(configProcesoService.configProceso(idCentroTrabajo, idTurno));
	}
	
	@PostMapping("{idConfigProceso}/finalizar-turno")
	public ResponseEntity<?> finalizarTurno(@PathVariable Integer idConfigProceso) {
		ResponseMessage mensaje = configProcesoService.finalizarTurno(idConfigProceso);
		Map<String, Object> response = new HashMap<>();
		if(Boolean.FALSE.equals(mensaje.getError())) {
			response.put("status", "success");
	        response.put("message", mensaje.getMensaje());
	        return ResponseEntity.ok(response);
		}
		response.put("status", "error");
        response.put("message", mensaje.getMensaje());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
}
