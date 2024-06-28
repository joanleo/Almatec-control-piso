package com.almatec.controlpiso.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.services.ItemOpService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/items-op")
public class ItemOpController {

	private final ItemOpService itemOpService;
	
	@GetMapping("/id-op-ia/{idOpIa}")
	public List<ItemOp> getItemOpPorIdIntegrapps(@PathVariable Integer idOpIa) {
		return itemOpService.buscarItemsOp(idOpIa);
	}
	
	
	
	
	public ItemOpController(ItemOpService itemOpService) {
		this.itemOpService = itemOpService;
	}
}
