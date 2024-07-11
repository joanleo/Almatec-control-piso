package com.almatec.controlpiso.programacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;
import com.almatec.controlpiso.integrapps.services.VistaItemsOpsProgramacionService;
import com.almatec.controlpiso.programacion.dtos.PrioridadFilterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/programacion")
public class ProgramacionController {
	
	@Autowired
	private VistaItemsOpsProgramacionService vistaItemsOpsProgramacionService;
	
	@GetMapping
	public String getItemsPrioridad(Model modelo,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(name = "sortField", defaultValue = "idOp") String sortField,
            @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
            PrioridadFilterDTO filtro,
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) throws JsonProcessingException {
		
		
		Page<VistaItemsOpsProgramacion> itemsOps = vistaItemsOpsProgramacionService.obtenerItemsOpsPaginados(page, size, sortDir, sortField, filtro);
		modelo.addAttribute("itemsPage", itemsOps);
		modelo.addAttribute("sortField", sortField);
	    modelo.addAttribute("sortDir", sortDir);
	    modelo.addAttribute("filtro", filtro);
	    ObjectMapper objectMapper = new ObjectMapper();
	    modelo.addAttribute("itemsJson", objectMapper.writeValueAsString(itemsOps.getContent()));
	    
	    if ("XMLHttpRequest".equals(requestedWith)) {
	        return "programacion/listar-prioridades :: resultsTable";
	    }
		
		return "programacion/listar-prioridades";
	}

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
