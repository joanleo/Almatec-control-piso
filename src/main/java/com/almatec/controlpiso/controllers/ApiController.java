package com.almatec.controlpiso.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.erp.dto.ListaMaterialesDTO;
import com.almatec.controlpiso.erp.dto.RutaDTO;
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.services.DetalleSolicitudMateriaPrimaService;
import com.almatec.controlpiso.integrapps.services.SolicitudMateriaPrimaService;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private XmlService xmlService;
	
	@Autowired
	private SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	
	@Autowired
	private DetalleSolicitudMateriaPrimaService detalleSolicitudMateriaPrimaService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ResponseBody
	@PostMapping("/lista-materiales")
	public ResponseEntity<?> crearListaMateriales(@RequestBody List<ListaMaterialesDTO> listaMateriales){
		logger.info("Se recibe solicitud de creacion de lista de materiales con la siguiente info: {}", listaMateriales);
		xmlService.asignarParametros();
		String response = xmlService.crearListaMAteriales(listaMateriales);			
		return ResponseEntity.ok(response);
	}
	
	@ResponseBody
	@PostMapping("/lista-materiales/{id}")
	public ResponseEntity<?> crearListaMateriales(@PathVariable Integer id){
		logger.info("Se recibe solicitud de creacion de lista de materiales con el id: {}", id);
		try {
			xmlService.asignarParametros();
			String response = xmlService.crearListaMaterialesPorIdTabla(id);
			return ResponseEntity.ok(response);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la creación de la lista de materiales"); 
	    }
	}
	
	@ResponseBody
	@PostMapping("/rutas")
	public ResponseEntity<?> crearRutas(@RequestBody List<RutaDTO> ruta){
		
		System.out.println(ruta);
		logger.info("Se recibe solicitud de creacion de ruta", ruta);
		try {
			xmlService.asignarParametros();
			String response = xmlService.crearRuta(ruta);
			return ResponseEntity.ok(response);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la creación de la ruta"); 
	    }
	}
	
	/*@ResponseBody
	@PostMapping("/rutas/{id}")
	public ResponseEntity<?> crearRutasId(@PathVariable Integer id) throws IOException{
		logger.info("Se recibe solicitud de creacion de ruta por id: {}", id);
		xmlService.asignarParametros();
		//String response = xmlService.crearEntrega();
		return ResponseEntity.ok(response);
	}*/
	
	@ResponseBody
	@PostMapping("/entregas/crear-op/{idOPI}")
	public ResponseEntity<?> crearOPEntrega(@PathVariable Integer idOPI){
		logger.info("Se recibe solicitud de creacion de orden de produccion con base en id op integrapps no: {}", idOPI);
		try {
			xmlService.asignarParametros();
			String response = xmlService.crearOPEntrega(idOPI);
			return ResponseEntity.ok(response);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la creación de la ruta"); 
	    }
	}
	
	@ResponseBody
	@PostMapping("/transferencia/{idSol}")
	public ResponseEntity<?> crearTransferenciaPorIdSol(@PathVariable Integer idSol) throws IOException{
		logger.info("Se recibe solicitud de Transferncia por id: {}", idSol);
		SolicitudMateriaPrima solicitud = solicitudMateriaPrimaService.obtenerSolicitudPorId(idSol);
		List<DetalleSolicitudMateriaPrima> detalleSol = detalleSolicitudMateriaPrimaService.obtenerDetallePorIdSol(idSol);
		xmlService.asignarParametros();
		ErrorMensaje mensaje = xmlService.crearTransferencia(solicitud, detalleSol, idSol);
		//ErrorMensaje mensaje = xmlService.crearTransferenciaYCompromisoDesdeOP(solicitud, detalleSol, idSol);
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
