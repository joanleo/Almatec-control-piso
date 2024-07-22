package com.almatec.controlpiso.comercial.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private final VistaPedidosErpService vistaPedidosErpService;
    private final VistaItemPedidoErpService vistaItemPedidosErp;
    private final XmlService xmlService;

    public ComercialController(VistaPedidosErpService vistaPedidosErpService,
                               VistaItemPedidoErpService vistaItemPedidosErp,
                               XmlService xmlService) {
        this.vistaPedidosErpService = vistaPedidosErpService;
        this.vistaItemPedidosErp = vistaItemPedidosErp;
        this.xmlService = xmlService;
    }
	
	
	@GetMapping
	public String listarPedidosVentaErp(Model modelo,
            @RequestParam(value = "keyword", required = false) String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
		
		Page<VistaPedidosErp> page = (keyword == null || keyword.isEmpty())  ?
		vistaPedidosErpService.buscarPedidosErp(pageable) :
		vistaPedidosErpService.buscarPedidosErp(keyword, pageable);
		
		modelo.addAttribute("pedidosPage", page);
		modelo.addAttribute("keyword", keyword);
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
	
	@PostMapping("/pedidos/filtrar")
	public ResponseEntity<Page<VistaPedidosErp>> getPedidos(
            @RequestBody PedidoSpecDTO busquedaSpec,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "co") String sort,
            @RequestParam(defaultValue = "asc") String order) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        Page<VistaPedidosErp> pedidos = vistaPedidosErpService.searchOrder(busquedaSpec, pageable); 
        return ResponseEntity.ok(pedidos);
    }
}
