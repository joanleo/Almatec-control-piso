package com.almatec.controlpiso.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.erp.dto.ListaMaterialesDTO;
import com.almatec.controlpiso.erp.dto.RutaDTO;
import com.almatec.controlpiso.erp.webservices.XmlService;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private XmlService xmlService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ResponseBody
	@PostMapping("/lista-materiales")
	public ResponseEntity<?> crearListaMateriales(@RequestBody List<ListaMaterialesDTO> listaMateriales){
		System.out.println(listaMateriales);
		logger.info("Se recibe solicitud de creacion de lista de materiales con la siguiente info: {}", listaMateriales);
		try {
			String response = xmlService.crearListaMAteriales(listaMateriales);
			return ResponseEntity.ok(response);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la creación de la lista de materiales"); 
	    }
	}
	
	@ResponseBody
	@PostMapping("/lista-materiales/{id}")
	public ResponseEntity<?> crearListaMateriales(@PathVariable Integer id){
		logger.info("Se recibe solicitud de creacion de lista de materiales con el id: {}", id);
		try {
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
			String response = xmlService.crearRuta(ruta);
			return ResponseEntity.ok(response);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la creación de la ruta"); 
	    }
	}

}
