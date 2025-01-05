package com.almatec.controlpiso.costos.controllers;

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

import com.almatec.controlpiso.costos.services.CostosService;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpId;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.services.ItemOpService;

@Controller
@RequestMapping("/costos")
public class CostosController {
	
	@Autowired
	private ItemOpService itemOpService;
	
	@Autowired
	private CostosService costoService;
	
	
	@GetMapping("/ordenes-produccion")
    public String mostrarFormulario(Model model) {
		
		List<ConsultaOpId> numsOps = itemOpService.obtenerNumOps();
        model.addAttribute("numsOps", numsOps);
        return "costos/formulario-ajuste-cantidades-ejecutar-op";
    }
	
	@PostMapping("/ordenes-produccion/{idOp}/ajustar-cantidades-ejecutar")
	public ResponseEntity<?> actualizarCantidadesOp(@PathVariable Integer idOp) {		
		ResponseMessage mensaje =costoService.actualizarCantidadesEjecutarOp(idOp);
		
		if(Boolean.TRUE.equals(mensaje.getError())) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        } else {
            return ResponseEntity.ok(mensaje);
        }
	}

}
