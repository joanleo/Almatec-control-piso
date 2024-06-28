package com.almatec.controlpiso.comercial.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.integrapps.dtos.PedidoSpecDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;
import com.almatec.controlpiso.integrapps.services.VistaItemPedidoErpService;
import com.almatec.controlpiso.integrapps.services.VistaPedidosErpService;

@Controller
@RequestMapping("/comercial")
public class ComercialController {
	
	@Autowired
	private VistaPedidosErpService vistaPedidosErpService;
	
	@Autowired
	private VistaItemPedidoErpService vistaItemPedidosErp;
	
	@Autowired
	private XmlService xmlService;
	
	
	@GetMapping
	public String listarPedidosVentaErp(Model modelo, @Param("keyword") String keyword) {
		List<VistaPedidosErp>  pedidos = null;
		if(keyword == null) {
			pedidos = vistaPedidosErpService.buscarPedidosErp();
		}else {
			pedidos = vistaPedidosErpService.buscarPedidosErp(keyword);			
		}
		modelo.addAttribute("pedidos", pedidos);
		return "comercial/listar-pedidos";
	}
	
	@GetMapping("/pedidos/{idPedido}")
	public String verDetallePedido(@PathVariable Integer idPedido, Model modelo) {
		List<VistaItemPedidoErp> itemsPedido = vistaItemPedidosErp.buscarItemsPedido(idPedido);
		String noPedido = itemsPedido.get(0).getTipoPedido() +"-" + itemsPedido.get(0).getNoPedido();
		modelo.addAttribute("items", itemsPedido);
		modelo.addAttribute("noPedido", noPedido);
		return "comercial/pedido";
	}
	
	@PostMapping("/crear-op/{noPedido}/{ref}")
	public String crearOrdenProduccion(@PathVariable Integer noPedido,
				@PathVariable String ref, RedirectAttributes flash) {
		List<VistaItemPedidoErp> items = vistaItemPedidosErp.findByNoPedidopAndReferencia(noPedido, ref);
		
		try {		
			xmlService.asignarParametros();
			String response = xmlService.crearOrdenProduccionPapa(items, noPedido);
			flash.addFlashAttribute("message", response);
		} catch (IOException e) {
			e.printStackTrace();
			flash.addAttribute("error", "Error en la creación de la orden de produccion");
		}
		return "redirect:/comercial";				
	}
	
	
	@GetMapping("/pedidos")
	public String listarPedidos(Model modelo) {		
		return "comercial/pedidos-estado";
	}
	
	@ResponseBody
	@PostMapping("/pedidos/filtrar")
	public List<VistaPedidosErp> getPedidos(@RequestBody PedidoSpecDTO busquedaSpec){
		System.out.println(busquedaSpec);
		List<VistaPedidosErp> lista = vistaPedidosErpService.searchOrder(busquedaSpec);
		return lista;
	}
}
