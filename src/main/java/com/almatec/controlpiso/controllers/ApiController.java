package com.almatec.controlpiso.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.erp.dto.ListaMaterialesDTO;
import com.almatec.controlpiso.erp.webservices.XmlService;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private XmlService xmlService;
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> crearListaMateriales(@RequestBody List<ListaMaterialesDTO> listaMateriales){
		System.out.println(listaMateriales);
		try {
			String response = xmlService.crearListaMAteriales(listaMateriales);
			return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la creación de la lista de materiales"); // En caso de error, puedes devolver un mensaje de error personalizado
	    }
	}

}
