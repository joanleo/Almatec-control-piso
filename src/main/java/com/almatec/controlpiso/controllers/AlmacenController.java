package com.almatec.controlpiso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.integrapps.dtos.DetalleSolicitudDTO;
import com.almatec.controlpiso.integrapps.dtos.SolicitudDTO;
import com.almatec.controlpiso.integrapps.services.DetalleSolicitudMateriaPrimaService;
import com.almatec.controlpiso.integrapps.services.SolicitudMateriaPrimaService;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/almacen")
public class AlmacenController {
	
	@Autowired
	private SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	
	@Autowired
	private DetalleSolicitudMateriaPrimaService detalleSolicitudMateriaPrimaService;
	
	@GetMapping("/solicitudes")
	public String listarSolicitudesM(Model modelo) {
		List<SolicitudDTO> solicitudes = solicitudMateriaPrimaService.obtenerSolicitudes();		
		modelo.addAttribute("solicitudes", solicitudes);
		return "almacen/solicitudes-materia-prima.html";
	}
	
	@ResponseBody
	@GetMapping("/detalle/solicitud/{idSolic}")
	public List<DetalleSolicitudDTO> obtenerDetalleSolicitud(@PathVariable Integer idSolic) {
		return detalleSolicitudMateriaPrimaService.obtenerDetalleDTOPorIdSolic(idSolic);
	}
	

}
