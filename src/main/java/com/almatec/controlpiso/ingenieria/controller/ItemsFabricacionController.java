package com.almatec.controlpiso.ingenieria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.almatec.controlpiso.ingenieria.dtos.ItemDetalladoDTO;
import com.almatec.controlpiso.ingenieria.dtos.RutaItemDTO;
import com.almatec.controlpiso.integrapps.entities.Item;
import com.almatec.controlpiso.integrapps.services.ItemService;
import com.almatec.controlpiso.integrapps.services.RutaItemService;

@Controller
@RequestMapping("/ingenieria/items")
public class ItemsFabricacionController {
	
	@Autowired
    private ItemService itemService;
	
	@Autowired
	private RutaItemService rutaItemService;
    
    @GetMapping
    public String verDetalleItem(Model model) {      
        return "ingenieria/items/items";
    }
    
    @GetMapping("/search")
    public ResponseEntity<Page<Item>> searchItems(@RequestParam String query,
    		@RequestParam(required = false) String tipo,
    		@RequestParam(defaultValue = "0") int page,
    	    @RequestParam(defaultValue = "10") int size) {
    	
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Item> items = itemService.buscarItems(query, tipo, pageable);
        return ResponseEntity.ok(items);
    }
    
    @GetMapping("/{id}")
    public String verDetalleItem(@PathVariable Integer id, Model model) {
    	ItemDetalladoDTO item = itemService.buscarItemConDetalles(id);
        model.addAttribute("item", item);
        model.addAttribute("nivel", 0);
        return "ingenieria/items/detalle";
    }
    
    @GetMapping("/rutas")
    public String verRutaItem(Model model) {      
        return "ingenieria/items/rutas";
    }

    @GetMapping("/{idItem}/ruta")
    public ResponseEntity<List<RutaItemDTO>> obtenerRutaItem(@PathVariable Integer idItem) {
        return ResponseEntity.ok(rutaItemService.obtenerRutaPorItem(idItem));
    }
}
