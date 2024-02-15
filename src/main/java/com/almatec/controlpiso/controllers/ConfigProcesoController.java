package com.almatec.controlpiso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.integrapps.dtos.ConfigProcesoDTO;
import com.almatec.controlpiso.integrapps.entities.ConfigProceso;
import com.almatec.controlpiso.integrapps.services.ConfigProcesoService;

@Controller
@RequestMapping("/config-procesos")
public class ConfigProcesoController {

	@Autowired
	private ConfigProcesoService configProcesoService;
	
	public String listar(Model modelo) {
		List<ConfigProceso> procesosConfig = configProcesoService.buscarProcesosConfig();
		modelo.addAttribute("procesosConfig", procesosConfig);
		return "produccion/config-procesos/listar.html";
	}
	
	@ResponseBody
	@GetMapping("/centro-trabajo/{idCentroTrabajo}/turno/{idTurno}")
	public ConfigProcesoDTO configNuevoProceso(Model modelo, 
									@PathVariable Integer idCentroTrabajo, 
									@PathVariable Long idTurno) throws Exception {
		System.out.println("Configurando CT");
		return configProcesoService.configProceso(idCentroTrabajo, idTurno);
	}
	
}
