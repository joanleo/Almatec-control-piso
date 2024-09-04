package com.almatec.controlpiso.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.erp.webservices.services.OrdenProduccionHijoService;
import com.almatec.controlpiso.erp.webservices.services.TransferenciaService;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;


@Controller
@RequestMapping("/api")
public class ApiController {
	
	private final OrdenProduccionHijoService ordenProduccionHijoService;	
	private final TransferenciaService transferenciaService;
	private final UsuarioService usuarioService;
	
	public ApiController(OrdenProduccionHijoService ordenProduccionHijoService,
			TransferenciaService transferenciaService,
			UsuarioService usuarioService) {
		super();
		this.ordenProduccionHijoService = ordenProduccionHijoService;
		this.transferenciaService = transferenciaService;
		this.usuarioService = usuarioService;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/entregas/crear-op/{idOPI}")
	public ResponseEntity<?> crearOPEntrega(@PathVariable Integer idOPI){
		logger.info("Se recibe solicitud de creacion de orden de produccion con base en id op integrapps no: {}", idOPI);
		try {
			String response = ordenProduccionHijoService.crearOrdenProduccion(idOPI);
			return ResponseEntity.ok(response);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la creación de la ruta"); 
	    }
	}
	
	@PostMapping("/transferencia/{idSol}")
	public ResponseEntity<?> crearTransferenciaPorIdSol(@PathVariable Integer idSol) throws IOException{
		logger.info("Se recibe solicitud de Transferncia por id: {}", idSol);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioP = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
		ResponseMessage mensaje = transferenciaService.crearTransferencia(idSol, usuarioP);
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
