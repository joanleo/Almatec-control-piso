package com.almatec.controlpiso.produccion.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.exceptions.ServiceException;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.integrapps.services.OperarioService;
import com.almatec.controlpiso.produccion.dtos.OperarioGeneralDTO;
import com.almatec.controlpiso.utils.OperarioPDFService;
import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/operarios")
public class OperarioController {

	@Autowired
	private OperarioService operarioService;
	
	@Autowired
	private CentroTrabajoService centroTrabajoService;
	
	@Autowired
    private OperarioPDFService operarioPDFService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@GetMapping("/listar")
	public String listarOperarios() {		
		return "produccion/operarios/listar-operarios.html";
	}
	
	@GetMapping
	public ResponseEntity<?> obtenerOperarios(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "") String search){
		try {
			Page<OperarioGeneralDTO> operariosPage = operarioService.obtenerOperariosGeneralPaginados(page, size, sortBy, sortDir, search);
            Map<String, Object> response = new HashMap<>();
            response.put("operarios", operariosPage.getContent());
            response.put("currentPage", operariosPage.getNumber());
            response.put("totalItems", operariosPage.getTotalElements());
            response.put("totalPages", operariosPage.getTotalPages());
            return ResponseEntity.ok(operariosPage);
        } catch (ServiceException e) {
        	logger.error("Error al obtener operarios generales", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(true, "Error al obtener la lista de operarios"));
        }
	}
	
	@GetMapping("/nuevo")
	public String crearOperario(Model model) {		
		List<CentroTrabajo> centrosTrabajo = centroTrabajoService.buscarCentrosTrabajo(22);
		
		Map<Integer, String> opcionesSexo = new HashMap<>();
	    opcionesSexo.put(1, "Masculino");
	    opcionesSexo.put(2, "Femenino");
	    opcionesSexo.put(3, "Otro");
	    
	    model.addAttribute("operario", new OperarioGeneralDTO());
	    model.addAttribute("centrosTrabajo", centrosTrabajo);
	    model.addAttribute("opcionesSexo", opcionesSexo);
	    
		return "produccion/operarios/formulario-operario.html";
	}
	
	@GetMapping("/editar/{id}")
	public String editarOperario(Model model, @PathVariable Integer id ) {
		OperarioGeneralDTO operario;
		try {
			operario = operarioService.obtenerOperarioGeneralPorId(id);
			List<CentroTrabajo> centrosTrabajo = centroTrabajoService.buscarCentrosTrabajo(22);
			
			Map<Integer, String> opcionesSexo = new HashMap<>();
		    opcionesSexo.put(1, "Masculino");
		    opcionesSexo.put(2, "Femenino");
		    opcionesSexo.put(3, "Otro");
		    
		    model.addAttribute("operario", operario);
		    model.addAttribute("centrosTrabajo", centrosTrabajo);
		    model.addAttribute("opcionesSexo", opcionesSexo);
			
		    return "produccion/operarios/formulario-operario.html";
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@PostMapping("/guardar")
	public String guardarOperario(@ModelAttribute OperarioGeneralDTO operarioDTO,
			RedirectAttributes  flash) {
		try {
			ResponseMessage mensaje = operarioService.guardarOperario(operarioDTO);
			flash.addFlashAttribute("message", mensaje.getMensaje());
            return "redirect:/operarios/listar";
		} catch (ServiceException e) {
			flash.addFlashAttribute("error", e.getMessage());
            return "redirect:/operarios/nuevo";
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> toggleEstadoOperario(@PathVariable Integer id) {
	    try {
	        ResponseMessage mensaje = operarioService.toggleEstadoOperario(id);
	        return ResponseEntity.ok(mensaje);
	    } catch (ServiceException e) {
	        logger.error("Error al cambiar el estado del operario", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ResponseMessage(true, "Error al cambiar el estado del operario: " + e.getMessage()));
	    }
	}
	
	@GetMapping("/{numCedula}")
	public ResponseEntity<?> obtenerOperario(@PathVariable Integer numCedula) {
		try {
			return ResponseEntity.ok(operarioService.obtenerOperario(numCedula));			
		}catch (ResourceNotFoundException error) {
			ResponseMessage errorMessage = new ResponseMessage(true, error.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		}
	}
	
	@GetMapping("/generar-codigos-barra")
	public void generarBarCodeOperarios(HttpServletResponse response) throws DocumentException, IOException, WriterException {
		operarioPDFService.generarBarCodeOperarios(response);
	}
	
	
}
