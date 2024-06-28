package com.almatec.controlpiso.programacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;
import com.almatec.controlpiso.integrapps.services.VistaItemsOpsProgramacionService;

@Controller
@RequestMapping("/programacion")
public class ProgramacionController {
	
	@Autowired
	private VistaItemsOpsProgramacionService vistaItemsOpsProgramacionService;

	@GetMapping("/asignar-prioridades")
	public String asignarPrioridad(Model modelo) {
		List<VistaItemsOpsProgramacion> itemsOps = vistaItemsOpsProgramacionService.obtenerItemsOps();
		modelo.addAttribute("items", itemsOps);
		return "programacion/asignacion-prioridad";
	}
	
	@PostMapping("/item/{idItem}/actualizar-prioridad/{prioridad}")
	public ResponseEntity<String> actualizaPrioridad(@PathVariable Long idItem, 
	                                                @PathVariable Integer prioridad) {
	    vistaItemsOpsProgramacionService.actualizaPrioridad(idItem, prioridad);

	    return new ResponseEntity<>("Actualización exitosa", HttpStatus.OK);
	}
}
