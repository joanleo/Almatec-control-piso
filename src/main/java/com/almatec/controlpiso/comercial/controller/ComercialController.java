package com.almatec.controlpiso.comercial.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.almatec.controlpiso.comunicador.services.MensajeServices;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.erp.webservices.services.OrdenProduccionPapaService;
import com.almatec.controlpiso.integrapps.dtos.PedidoSpecDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;
import com.almatec.controlpiso.integrapps.services.VistaItemPedidoErpService;
import com.almatec.controlpiso.integrapps.services.VistaPedidosErpService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Controller
@RequestMapping("/comercial")
public class ComercialController {
	
	private final VistaPedidosErpService vistaPedidosErpService;
    private final VistaItemPedidoErpService vistaItemPedidosErp;
    private final ConfigurationService configService;
    private final XmlService xmlService;
    private final UtilitiesApp util;
	private final MensajeServices mensajeService;
	private final OrdenProduccionPapaService ordenProduccionPapaService;

	private Logger logger = LoggerFactory.getLogger(getClass());

    static final String RESPUESTA_OK = "Operacion realizada exitosamente";

    public ComercialController(VistaPedidosErpService vistaPedidosErpService,
                               VistaItemPedidoErpService vistaItemPedidosErp,
                               ConfigurationService configService,
                               XmlService xmlService,                               
                               UtilitiesApp util, 
                               MensajeServices mensajeService, 
                               OrdenProduccionPapaService ordenProduccionPapaService) {
        this.vistaPedidosErpService = vistaPedidosErpService;
        this.vistaItemPedidosErp = vistaItemPedidosErp;
        this.configService = configService;
        this.xmlService = xmlService;
        this.util = util;
        this.mensajeService = mensajeService;
        this.ordenProduccionPapaService = ordenProduccionPapaService;
    }
	
	
	@GetMapping
	public String listarPedidosVentaErp(Model modelo,
            @RequestParam(required = false) String keyword,
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
		
		Usuario usuario = util.obtenerUsuarioAtenticado();
		
		try {		
			//Se realiza la aprobacion por parte de comercial
			logger.info("Creando Orden de produccion Papa.");
			List<Conector> opPapa = ordenProduccionPapaService.crearConectoresOpPapa(items);
			String consecutivo = "IF_PV-" + noPedido;
			util.guardarRegistroXml(xmlService.crearPlanoXml(opPapa), consecutivo);
			util.crearArchivoPlano(opPapa, consecutivo, configService.getCIA());
			String response = xmlService.postImportarXML(opPapa);
			if (RESPUESTA_OK.equals(response)) {
				VistaPedidosErp pedido = vistaPedidosErpService.obtenerPorNoPedido(noPedido);
				Map<String, String> datos = new HashMap<>();
				datos.put("pedido", "PV-" + noPedido);
				datos.put("cliente", items.get(0).getCliente());
				datos.put("proyecto", pedido.getCentroOperaciones());
				datos.put("aprobador", usuario.getNombres());
				datos.put("ordenProduccion", pedido.getTipoOp() + "-" + pedido.getNumOp());
				datos.put("fechaAprobacion", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				mensajeService.enviarEmailAprobacionPedidoVenta(datos);
				flash.addFlashAttribute("message", "Orden creada exitosamente"); 
			} else {
				flash.addFlashAttribute("message", response);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			flash.addAttribute("error", "Error en la generacion del archivo plano de la orden de produccion");
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
            @RequestParam(defaultValue = "fecha") String sort,
            @RequestParam(defaultValue = "desc") String order) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        Page<VistaPedidosErp> pedidos = vistaPedidosErpService.searchOrder(busquedaSpec, pageable); 
        return ResponseEntity.ok(pedidos);
    }
}
