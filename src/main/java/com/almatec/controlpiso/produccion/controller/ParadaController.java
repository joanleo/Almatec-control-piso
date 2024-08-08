package com.almatec.controlpiso.produccion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.integrapps.entities.Parada;
import com.almatec.controlpiso.integrapps.services.ParadaService;

@Controller
@RequestMapping("/paradas")
public class ParadaController {

	@Autowired
	private ParadaService paradaService;
	
	@ResponseBody
	@GetMapping
	public List<Parada> obtenerParadas(){
		return paradaService.obtenerParadas();
	}
}
