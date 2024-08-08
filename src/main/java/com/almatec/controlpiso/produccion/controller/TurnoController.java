package com.almatec.controlpiso.produccion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.integrapps.entities.Turno;
import com.almatec.controlpiso.integrapps.repositories.TurnoRepository;

@Controller
@RequestMapping("/turnos")
public class TurnoController {

	@Autowired
	private TurnoRepository turnoRepo;
	
	@GetMapping
	@ResponseBody
	public List<Turno> listar() {
		return turnoRepo.findAll(); 
	}
}
