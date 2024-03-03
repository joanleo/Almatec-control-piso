package com.almatec.controlpiso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.integrapps.dtos.OpProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.ProyectoProduccionDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;

@Controller
@RequestMapping("/produccion")
public class ProduccionController {
	
	@Autowired
	private OrdenPvService ordenPvService;
	
	@Autowired
	private ItemOpService itemOpService;

	@GetMapping("/home")
	public String homeProduction() {
		return "produccion/dashboard";
	}
	
	@GetMapping("/proyectos")
	public String listarProyectos(Model modelo, @Param("keyword") String keyword) {
		List<ProyectoProduccionDTO> proyectos = null;
		if(keyword != null) {
			proyectos = ordenPvService.buscarProyectosOrdenPv(keyword);
		}else {
			proyectos = ordenPvService.buscarProyectosOrdenPv();			
		}
		modelo.addAttribute("proyectos", proyectos);
		return "produccion/proyectos";
	}
	
	@GetMapping("/proyectos/{idProyecto}")
	public String verOpsProyecto(Model modelo, @PathVariable String idProyecto) {
		List<OpProduccionDTO> ordenes = ordenPvService.buscarOrdenesPorIdProyecto(idProyecto);
		modelo.addAttribute("ordenes", ordenes);
		return "produccion/ordenes-produccion-proyecto";
	}
	
	@GetMapping("/proyectos/{idProyecto}/op/{numOp}")
	public String verOp(Model modelo, @PathVariable String idProyecto,
						@PathVariable Integer numOp ) {
		List<ItemOp> items = itemOpService.obtenerItemsOpProduccion(numOp);
		for(ItemOp i: items) {
			System.out.println(i);
		}
		
		modelo.addAttribute("items", items);
		return "produccion/detalle-op";
	}
}
