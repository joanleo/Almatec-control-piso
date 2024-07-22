package com.almatec.controlpiso.programacion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.almatec.controlpiso.integrapps.dtos.ResponseDTO;
import com.almatec.controlpiso.integrapps.entities.Prioridad;
import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;
import com.almatec.controlpiso.integrapps.entities.VistaOpItemsMaterialesRuta;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.integrapps.services.PrioridadSevice;
import com.almatec.controlpiso.integrapps.services.VistaItemsOpsProgramacionService;
import com.almatec.controlpiso.integrapps.services.VistaOpItemsMaterialesRutaService;
import com.almatec.controlpiso.programacion.dtos.PrioridadFilterDTO;
import com.almatec.controlpiso.programacion.dtos.PrioridadItemsDTO;

@Controller
@RequestMapping("/programacion")
public class ProgramacionController {
	
	@Autowired
	private VistaItemsOpsProgramacionService vistaItemsOpsProgramacionService;
	
	@Autowired
	private VistaOpItemsMaterialesRutaService vistaOpItemsMaterialesRutaService;
	
	@Autowired
	private PrioridadSevice prioridadService;
	
	@Autowired
	private OrdenPvService ordenPvService;
	
	@GetMapping
	public String getItemsPrioridad(Model modelo,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(name = "sortField", defaultValue = "idOpIntegrapps") String sortField,
            @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
            @RequestParam(required = false) Integer centroTrabajoId,
            PrioridadFilterDTO filtro,
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {

		if (filtro == null) {
            filtro = new PrioridadFilterDTO();
        }
		modelo.addAttribute("filtro", filtro);

		Page<VistaOpItemsMaterialesRuta> itemsPage = Page.empty();		
		
		if (centroTrabajoId != null) {
            filtro.setCentroTrabajoId(centroTrabajoId);
            itemsPage = vistaOpItemsMaterialesRutaService.obtenerItemsOpsPaginados(page, size, sortDir, sortField, filtro);
        }
        
		modelo.addAttribute("itemsPage", itemsPage);
		modelo.addAttribute("sortField", sortField);
	    modelo.addAttribute("sortDir", sortDir);
	    
	    if ("XMLHttpRequest".equals(requestedWith)) {
	        return "programacion/listar-prioridades :: resultsTable";
	    }
		
		return "programacion/listar-prioridades";
	}
	
	@PostMapping("/guardar-prioridad-multiple")
	public ResponseEntity<?> guardarActuallizarPrioridades(@Valid @RequestBody PrioridadItemsDTO itemsPrioridad){
		
		prioridadService.guardarActualizarPrioridades(itemsPrioridad);
		
		try {
            List<Prioridad> prioridadesActualizadas = prioridadService.guardarActualizarPrioridades(itemsPrioridad);
            return ResponseEntity.ok(new ResponseDTO("Prioridades actualizadas con éxito", prioridadesActualizadas));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Error al procesar la solicitud: " + e.getMessage(), null));
        }
		
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
	
	@GetMapping("/grafico-gantt")
	public String mostrarGraficoGantt(Model modelo) {		
		
		return "programacion/grafico-gantt";
	}
	
	@GetMapping("/ordenes")
	public ResponseEntity<List<VistaOrdenPv>> obtenerOrdenes() {
		List<VistaOrdenPv> ordenes = ordenPvService.obtenerOrdenes();
		return ResponseEntity.ok(ordenes);
	}
	
}
