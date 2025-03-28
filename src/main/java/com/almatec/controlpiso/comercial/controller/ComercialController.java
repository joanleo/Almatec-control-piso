package com.almatec.controlpiso.comercial.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import com.almatec.controlpiso.integrapps.entities.VistaPedidoOp;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosKgCumplidos;
import com.almatec.controlpiso.integrapps.services.VistaItemPedidoErpService;
import com.almatec.controlpiso.integrapps.services.VistaPedidoOpService;
import com.almatec.controlpiso.integrapps.services.VistaPedidosErpService;
import com.almatec.controlpiso.integrapps.services.VistaPedidosKgCumplidosService;
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
	private final VistaPedidosKgCumplidosService vistaPedidosKgCumplidosService;
	private final VistaPedidoOpService vistaPedidoOpService;

	private Logger logger = LoggerFactory.getLogger(getClass());

    static final String RESPUESTA_OK = "Operacion realizada exitosamente";

    public ComercialController(VistaPedidosErpService vistaPedidosErpService,
                               VistaItemPedidoErpService vistaItemPedidosErp,
                               ConfigurationService configService,
                               XmlService xmlService,                               
                               UtilitiesApp util, 
                               MensajeServices mensajeService, 
                               OrdenProduccionPapaService ordenProduccionPapaService,
                               VistaPedidosKgCumplidosService vistaPedidosKgCumplidosService,
                               VistaPedidoOpService vistaPedidoOpService) {
        this.vistaPedidosErpService = vistaPedidosErpService;
        this.vistaItemPedidosErp = vistaItemPedidosErp;
        this.configService = configService;
        this.xmlService = xmlService;
        this.util = util;
        this.mensajeService = mensajeService;
        this.ordenProduccionPapaService = ordenProduccionPapaService;
        this.vistaPedidosKgCumplidosService = vistaPedidosKgCumplidosService;
        this.vistaPedidoOpService = vistaPedidoOpService;
    }
	
	
    @GetMapping("/pedidos/listar")
    public String listarPedidosVentaErp(Model modelo,
                                      @RequestParam(required = false) String keyword,
                                      @PageableDefault(size = 10) Pageable pageable) {

        Page<VistaPedidosErp> page = (keyword == null || keyword.isEmpty()) ?
                vistaPedidosErpService.buscarPedidosErp(pageable) :
                vistaPedidosErpService.buscarPedidosErp(keyword, pageable);

        // Cálculo de páginas para mostrar (máximo 5)
        int currentPage = page.getNumber();
        int totalPages = page.getTotalPages();
        
        int startPage = Math.max(0, currentPage - 2);
        int endPage = Math.min(totalPages - 1, startPage + 4);
        startPage = Math.max(0, endPage - 4);
        
        List<Integer> pageNumbers = IntStream.rangeClosed(startPage, endPage)
                                            .boxed()
                                            .collect(Collectors.toList());
        
        modelo.addAttribute("pedidosPage", page);
        modelo.addAttribute("keyword", keyword);
        modelo.addAttribute("pageNumbers", pageNumbers);
        
        return "comercial/listar-pedidos";
    }
	
	@GetMapping("/pedidos/estado/{idPedido}")
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
			logger.info("Creando orden de produccion IF para el PV-{}. Usuario: {}, Rol: {}.", noPedido, usuario.getNombres(), usuario.getRole().getNombre());
			List<Conector> opPapa = ordenProduccionPapaService.crearConectoresOpPapa(items);
			String consecutivo = "IF_PV-" + noPedido;
			util.guardarRegistroXml(xmlService.crearPlanoXml(opPapa), consecutivo);
			util.crearArchivoPlano(opPapa, consecutivo, configService.getCIA());
			String response = xmlService.postImportarXML(opPapa);
			logger.info("Respuesta del webservice: {}", response);
			if (RESPUESTA_OK.equals(response)) {
				VistaPedidosErp pedido = vistaPedidosErpService.obtenerPorNoPedido(noPedido);
				Map<String, String> datos = new HashMap<>();
				datos.put("pedido", "PV-" + noPedido);
				datos.put("cliente", items.get(0).getCliente());
				datos.put("proyecto", pedido.getCo());
				datos.put("aprobador", usuario.getNombres());
				datos.put("ordenProduccion", pedido.getTipoOp() + "-" + pedido.getNumOp());
				datos.put("fechaAprobacion", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				logger.info("Orden de produccion {}-{} creada. Usuario: {}, Rol: {}.", pedido.getTipoOp(), pedido.getNumOp(), usuario.getNombres(), usuario.getRole().getNombre());
				mensajeService.enviarEmailAprobacionPedidoVenta(datos);
				flash.addFlashAttribute("message", "Orden creada exitosamente"); 
			} else {
				logger.error("Error al tratar de crear la orden de produccion IF para el PV-{}. Usuario: {}, Rol: {}.", noPedido, usuario.getNombres(), usuario.getRole().getNombre());
				flash.addFlashAttribute("message", response);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error en la generacion del archivo plano/xml para orden de produccion del PV-" + noPedido);
			flash.addAttribute("error", "Error en la generacion del archivo plano/xml para orden de produccion del PV-" + noPedido);
		}
		return "redirect:/comercial/pedidos/listar";				
	}
	
	
	@GetMapping("/pedidos")
	public String listarPedidos(Model modelo) {		
		return "comercial/pedidos-estado";
	}
	
	@PostMapping("/pedidos/filtrar")
	public ResponseEntity<Page<VistaPedidosKgCumplidos>> getPedidos(
            @RequestBody PedidoSpecDTO busquedaSpec,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "fecha") String sort,
            @RequestParam(defaultValue = "desc") String order) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        Page<VistaPedidosKgCumplidos> pedidos = vistaPedidosKgCumplidosService.searchOrder(busquedaSpec, pageable); 
        return ResponseEntity.ok(pedidos);
    }
	
	@GetMapping("/pedidos/{rowIdPV}/ordenes-produccion")
	public ResponseEntity<List<VistaPedidoOp>> getOrdenesProduccion(@PathVariable Integer rowIdPV) {
		logger.info("Recibida solicitud de órdenes de producción para rowId: {}", rowIdPV);
	    try {
	        List<VistaPedidoOp> ordenesProduccion = vistaPedidoOpService.findByPedido(rowIdPV);
	        logger.info("Encontradas {} órdenes de producción", ordenesProduccion.size());
	        return ResponseEntity.ok(ordenesProduccion);
	    } catch (Exception e) {
	        logger.error("Error al obtener órdenes de producción para rowId {}: {}", rowIdPV, e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(Collections.emptyList());
	    }
	}
}
