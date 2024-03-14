package com.almatec.controlpiso.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.integrapps.dtos.PedidoSpecDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;
import com.almatec.controlpiso.integrapps.services.VistaPedidosErpService;

@Controller
@RequestMapping("/comercial")
public class ComercialController {
	
	@Autowired
	private VistaPedidosErpService vistaPedidosErpService;
	
	
	@GetMapping("/pedidos")
	public String listarPedidos(Model modelo) {
		
		return "comercial/listar-pedidos";
	}
	
	@ResponseBody
	@PostMapping("/pedidos/filtrar")
	public List<VistaPedidosErp> getPedidos(@RequestBody PedidoSpecDTO busquedaSpec){
		System.out.println(busquedaSpec);
		List<VistaPedidosErp> lista = vistaPedidosErpService.searchOrder(busquedaSpec);
		return lista;
	}
}
