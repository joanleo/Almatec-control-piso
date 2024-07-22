package com.almatec.controlpiso.calidad.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.almatec.controlpiso.calidad.dtos.ReporteCalidadDTO;
import com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO;
import com.almatec.controlpiso.integrapps.entities.ReporteCalidad;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.ReporteCalidadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/calidad")
public class CalidadController {
	
	private final ListaMService listaMService;
	private final ReporteCalidadService reporteCalidadService;
	
	public CalidadController(ListaMService listaMService,
			ReporteCalidadService reporteCalidadService) {
		super();
		this.listaMService = listaMService;
		this.reporteCalidadService = reporteCalidadService;
	}

	@GetMapping("/formulario/centro-trabajo/{idCT}")
	public String obtenerFormulario(@PathVariable Integer idCT,
			  @RequestParam(required = false) Long idItem,
			  @RequestParam(required = false) Integer idOperario,
			  @RequestParam(required = false) Long id,
			  Model modelo) throws JsonProcessingException {
		
		ReporteCalidadDTO formulario;
		if (id != null) {
	        // Modo edición
	        formulario = reporteCalidadService.obtenerFormularioPorId(id);
	    } else {
	    	formulario = reporteCalidadService.buscarItemReporteCalidadCt(idItem, idCT, idOperario);	    	
	    }
		
		if (formulario.getFechaDoc() == null) {
	        formulario.setFechaDoc(LocalDateTime.now());
	    }
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	    String fechaFormateada = formulario.getFechaDoc().format(formatter);
	    modelo.addAttribute("fechaFormateada", fechaFormateada);

		List<LoteConCodigoDTO> lotes = listaMService.obtenerLotesOpPorItem(idItem);
		ObjectMapper mapper = new ObjectMapper();
	    String lotesJson = mapper.writeValueAsString(lotes);

		modelo.addAttribute("formulario", formulario);
		modelo.addAttribute("lotes", lotes);
		modelo.addAttribute("lotesJson", lotesJson);
		modelo.addAttribute("modoEdicion", id != null);
		
		return "calidad/formulario";
	}
	
	@PostMapping("/formulario/guardar")
	public ResponseEntity<?> guardarFormularioCalidad(@RequestBody ReporteCalidadDTO formCalidad) {
		System.out.println(formCalidad);
		if(formCalidad.getId() != null && StringUtils.hasText(formCalidad.getId().toString())) {
			System.out.println("Modo edicion: " + formCalidad.getId());			
		}else {
			System.out.println("No es modo edicion");
		}
		try {
			ReporteCalidad reporte = reporteCalidadService.guardarReporteCalidad(formCalidad); 
			return ResponseEntity.ok(reporte);
		} catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Argumento inválido - " + e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Problema de acceso a datos - " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado - " + e.getMessage());
        }
	}
	
	@GetMapping("/formularios")
	public String listarFormularios(@RequestParam(defaultValue = "0") int page,
	                                @RequestParam(defaultValue = "10") int size,
	                                @RequestParam(required = false) String search,
	                                Model model) {
	    Page<ReporteCalidadDTO> formularios = reporteCalidadService.listarFormularios(page, size, search);
	    model.addAttribute("formularios", formularios);
	    model.addAttribute("search", search);
	    return "calidad/listar-formularios";
	}

}
