package com.almatec.controlpiso.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosOpErp;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.VistaItemPedidoErpService;
import com.almatec.controlpiso.integrapps.services.VistaPedidosErpService;
import com.almatec.controlpiso.integrapps.services.VistaPedidosOpErpService;

@Controller
@RequestMapping("/ingenieria")
public class IngenieriaController {
	
	@Autowired
	private VistaPedidosErpService vistaPedidosErpService;
	
	@Autowired
	private VistaItemPedidoErpService vistaItemPedidosErp;
	
	@Autowired
	private VistaPedidosOpErpService vistaPedidosOpErpService;
	
	@Autowired
	private ItemOpService itemOpService;
	
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
		return "ingenieria/listar-pedidos";
	}
	
	@GetMapping("/pedidos/{idPedido}")
	public String verDetallePedido(@PathVariable String idPedido, Model modelo) {
		List<VistaItemPedidoErp> itemsPedido = vistaItemPedidosErp.buscarItemsPedido(idPedido);
		String noPedido = itemsPedido.get(0).getNoPedido();
		modelo.addAttribute("items", itemsPedido);
		modelo.addAttribute("noPedido", noPedido);
		return "ingenieria/pedido";
	}
	
	@PostMapping("/crear-op")
	public String crearOrdenProduccion(@RequestParam String referencia, 
									   @RequestParam String noPedido, 
									   RedirectAttributes flash ) {
		List<VistaItemPedidoErp> items = vistaItemPedidosErp.buscarItemPedidoByReferencia(noPedido, referencia);
		try {
			xmlService.crearOrdenProduccion(items);
		} catch (IOException e) {
			e.printStackTrace();
		}
		flash.addFlashAttribute("message", "Orden de produccion creada exitosamente.");
		return "redirect:/ingenieria/pedidos/"+noPedido;
	}
	
	@GetMapping("/listar-ops")
	public String listarOps(Model modelo, @Param("keyword") String keyword) {
		List<VistaPedidosOpErp> pedidosOp = null;
		if(keyword == null) {
			pedidosOp = vistaPedidosOpErpService.buscarOps();
		}else {
			pedidosOp = vistaPedidosOpErpService.buscarOps(keyword);			
		}
		
		modelo.addAttribute("pedidosOp", pedidosOp); 
		return "ingenieria/listar-ops";
	}
	
	@GetMapping("/op/{idOp}")
	public String verDetalleOp(@PathVariable String idOp, Model modelo) {
		List<ItemOp> listaItemOp = itemOpService.obtenerItemsOp(idOp);
		modelo.addAttribute("listaItemOp", listaItemOp);
		modelo.addAttribute("idOp", idOp);
		return "ingenieria/op";
	}
}
