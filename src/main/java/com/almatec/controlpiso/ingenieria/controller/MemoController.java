package com.almatec.controlpiso.ingenieria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.ingenieria.MemoWithOP;
import com.almatec.controlpiso.ingenieria.dtos.MemoDTO;
import com.almatec.controlpiso.ingenieria.services.MemoService;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpId;
import com.almatec.controlpiso.integrapps.dtos.UsuarioDTO;
import com.almatec.controlpiso.integrapps.entities.MemoDetalle;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/ingenieria")
public class MemoController {
	
	private final UsuarioService usuarioService;
	private final ItemOpService itemOpService;
	private final MemoService memoService;
	
	public MemoController(UsuarioService usuarioService, ItemOpService itemOpService,
			MemoService memoService) {
		super();
		this.usuarioService = usuarioService;
		this.itemOpService = itemOpService;
		this.memoService = memoService;
	}

	@GetMapping("/memos/listar")
	public String getMemos(Model modelo) {
		List<MemoWithOP> memos = memoService.obtenerMemosSinAprobar();
		modelo.addAttribute("memos", memos);
		return "ingenieria/listar-memos";
	}
	
	@GetMapping("/memos/nuevo")
	public String nuevoMemo(Model modelo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioP = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
		UsuarioDTO usuario = new UsuarioDTO(usuarioP);
		List<ConsultaOpId> numsOps = itemOpService.obtenerNumOps();
		
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("numsOps", numsOps);
		return "ingenieria/formulario-memo";
	}
	
	@PostMapping("/memos")
	public ResponseEntity<?> guardarMemos(@RequestBody MemoDTO memoDTO) {
		try {
			MemoDTO memoSaved = memoService.guardarMemo(memoDTO);
			return ResponseEntity.ok(memoSaved);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ResponseBody
	@GetMapping("/memos/{idMemo}/detalle-memo")
	public List<MemoDetalle> obtenerDetalleMemo(@PathVariable Long idMemo){		
		return memoService.obtenerDetalleMemo(idMemo);
	}
	
	@ResponseBody
	@PostMapping("/memos/{idMemo}/aprobar-memo")
	public MemoDTO aprobarMemo(@PathVariable Long idMemo){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
		return memoService.aprobarMemo(idMemo, usuario);
	}
	

}
