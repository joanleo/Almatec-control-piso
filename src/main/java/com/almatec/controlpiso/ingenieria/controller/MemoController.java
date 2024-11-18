package com.almatec.controlpiso.ingenieria.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.almatec.controlpiso.ingenieria.MemoWithOP;
import com.almatec.controlpiso.ingenieria.dtos.MemoDTO;
import com.almatec.controlpiso.ingenieria.dtos.MemoDetalleDTO;
import com.almatec.controlpiso.ingenieria.services.MemoService;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpId;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.utils.UtilitiesApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/ingenieria/memos")
public class MemoController {
	
	private final OrdenPvService ordenPvService;
	private final ItemOpService itemOpService;
	private final MemoService memoService;
	private final UtilitiesApp util;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public MemoController(
			OrdenPvService ordenPvService, 
			ItemOpService itemOpService,
			MemoService memoService,
			UtilitiesApp util) {
		super();
		this.ordenPvService = ordenPvService;
		this.itemOpService = itemOpService;
		this.memoService = memoService;
		this.util = util;
	}
	
	@GetMapping
    public String getMemos(Model model, 
                           @RequestParam(defaultValue = "0") int page, 
                           @RequestParam(defaultValue = "5") int size,
                           @RequestParam(required = false) String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MemoWithOP> memos = memoService.obtenerMemosPaginados(keyword, pageable);
        
        model.addAttribute("memos", memos);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", memos.getTotalPages());
        return "ingenieria/listar-memos";
    }

	@GetMapping("/aprobar")
	public String mostrarAprobarMemos(Model modelo) {
		List<MemoWithOP> memos = memoService.obtenerMemosSinAprobar();
		modelo.addAttribute("memos", memos);
		return "ingenieria/aprobar-memos";
	}
	
	@GetMapping("/nuevo")
	public String nuevoMemo(Model modelo) {
		
		Usuario usuario = util.obtenerUsuarioAtenticado();
		List<ConsultaOpId> numsOps = itemOpService.obtenerNumOps();
		
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("numsOps", numsOps);
		return "ingenieria/formulario-memo";
	}
	
	@PostMapping
	public ResponseEntity<?> guardarMemos(@RequestBody MemoDTO memoDTO) {
		Usuario usuario = util.obtenerUsuarioAtenticado();
		VistaOrdenPv orden = ordenPvService.obtenerOrdenPorId(memoDTO.getIdOpIntegrapps());
		try {
			logger.info("Creando memorando para la OP-{}. Usuario {}, rol:{}.", orden.getNumOp() ,usuario.getNombres(), usuario.getRole().getNombre());
			MemoDTO memoSaved = memoService.guardarMemo(memoDTO);
			logger.info("Memorando M-{} creado. Usuario {}, rol:{}.",memoSaved.getId(), usuario.getNombres(), usuario.getRole().getNombre());
			return ResponseEntity.ok(memoSaved);
		} catch (Exception e) {
			logger.error("Error al tratar de crear el memorando para la OP-{}. Usuario {}, rol:{}.", orden.getNumOp() ,usuario.getNombres(), usuario.getRole().getNombre());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{idMemo}/detalle-memo")
	public ResponseEntity<List<MemoDetalleDTO>> obtenerDetalleMemo(@PathVariable Long idMemo){		
		return ResponseEntity.ok(memoService.obtenerDetalleMemo(idMemo));
	}
	
	@PostMapping("/{idMemo}/aprobar-memo")
	public ResponseEntity<MemoDTO> aprobarMemo(@PathVariable Long idMemo){
		Usuario usuario = util.obtenerUsuarioAtenticado();
		return ResponseEntity.ok(memoService.aprobarMemo(idMemo, usuario));
	}
	

}
