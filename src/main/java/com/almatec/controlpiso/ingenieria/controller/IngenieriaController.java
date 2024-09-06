package com.almatec.controlpiso.ingenieria.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.almatec.controlpiso.integrapps.dtos.ItemOpDatable;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.paging.PageArray;
import com.almatec.controlpiso.integrapps.paging.PagingRequest;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;

	
@Controller
@RequestMapping("/ingenieria")
public class IngenieriaController {

	private final ItemOpService itemOpService;
	private final OrdenPvService ordenPvService;

	public IngenieriaController(ItemOpService itemOpService, OrdenPvService ordenPvService) {
		super();
		this.itemOpService = itemOpService;
		this.ordenPvService = ordenPvService;
	}

	@GetMapping("/status/proyectos")
	public String estadoProyectos(Model modelo, @Param("keyword") String keyword,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Page<VistaOrdenPv> proyectosPage = keyword == null
				? ordenPvService.buscarProyectosPaginados(PageRequest.of(page, size))
				: ordenPvService.buscarProyectosPaginados(keyword, PageRequest.of(page, size));

		modelo.addAttribute("proyectos", proyectosPage.getContent());
		modelo.addAttribute("currentPage", page);
		modelo.addAttribute("totalPages", proyectosPage.getTotalPages());
		modelo.addAttribute("totalItems", proyectosPage.getTotalElements());
		modelo.addAttribute("keyword", keyword);
		return "ingenieria/status-proyectos.html";
	}

	@PostMapping("/op/{numOp}/detalle")
	public ResponseEntity<PageArray> detalleOp(@RequestBody PagingRequest pagingRequest, @PathVariable Integer numOp) {
		PageArray page = itemOpService.obtenerItemsOpArray(pagingRequest, numOp);

		return ResponseEntity.ok(page);
	}

	@GetMapping("/op/{idOp}/detalle")
	public ResponseEntity<List<ItemOpDatable>> detalleOp(@PathVariable Integer idOp) {
		List<ItemOpDatable> itemsOp = itemOpService.obtenerItemsOpDataTable(idOp);
		return ResponseEntity.ok(itemsOp);
	}

}
