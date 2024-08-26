package com.almatec.controlpiso.programacion.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.almatec.controlpiso.integrapps.dtos.ConsultaOpId;
import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;
import com.almatec.controlpiso.integrapps.dtos.ResponseDTO;
import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.entities.Prioridad;
import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;
import com.almatec.controlpiso.integrapps.entities.VistaOpItemsMaterialesRuta;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.integrapps.services.PrioridadSevice;
import com.almatec.controlpiso.integrapps.services.VistaItemsOpsProgramacionService;
import com.almatec.controlpiso.integrapps.services.VistaOpItemsMaterialesRutaService;
import com.almatec.controlpiso.integrapps.services.VistaPiezasReportadasService;
import com.almatec.controlpiso.programacion.dtos.OrdenProduccionResumen;
import com.almatec.controlpiso.programacion.dtos.PrioridadFilterDTO;
import com.almatec.controlpiso.programacion.dtos.PrioridadItemsDTO;
import com.almatec.controlpiso.programacion.service.ExportExcelLm;

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
	
	@Autowired
	private ItemOpService itemOpService;
	
	@Autowired
	private ListaMService listaMaterialService;
	
	@Autowired
	private ExportExcelLm excelService;
	
	@Autowired
	private VistaPiezasReportadasService vistaPiezasReportadasService;
	
	@GetMapping
	public String getItemsPrioridad(Model modelo,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idOpIntegrapps") String sortField,
            @RequestParam(defaultValue = "desc") String sortDir,
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
	
	@GetMapping("/consulta-materia-prima")
	public String visualizacionListaMaterialesOps(Model model) {
		List<ConsultaOpId> numsOps = itemOpService.obtenerNumOps();
		
		model.addAttribute("numsOps", numsOps);
		model.addAttribute("specItemLoteDTO", new SpecItemLoteDTO());
        return "programacion/consulta-materia-prima";
	}
	
	@GetMapping("/descargar-lista-materiales")
	public ResponseEntity<Resource> descargarListaMateriales(@RequestParam Integer idOP,
			@RequestParam String descripcion) throws IOException {
	    // Obtener la lista de materiales
	    List<ListaMDTO> listaMateriales = listaMaterialService.obtenerListaMDTOPorIdOp(idOP);
	    
	    // Generar el archivo Excel
	    ByteArrayOutputStream excelOutputStream = excelService.generarExcelListaMateriales(listaMateriales);
	    
	    // Crear el recurso a partir del ByteArrayOutputStream
	    ByteArrayResource resource = new ByteArrayResource(excelOutputStream.toByteArray());
	    
	    // Configurar la respuesta HTTP
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + descripcion + ".xlsx")
	        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
	        .body(resource);
	}
	
	@GetMapping("/resumen-fabricado")
	public String informeFabricadoPrioridad() {
		
		return "programacion/resumen-fabricado";
	}
	
	@GetMapping("/centro-trabajo/{idCT}/resumen-ops")
	public ResponseEntity<Set<OrdenProduccionResumen>> obtenerResumenOpsCT(@PathVariable Integer idCT){		
		return ResponseEntity.ok(vistaPiezasReportadasService.buscarOps(idCT));
	}
}
