package com.almatec.controlpiso.produccion.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.almacen.service.DetalleSolicitudMateriaPrimaService;
import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.comunicador.services.MensajeServices;
import com.almatec.controlpiso.exceptions.RutaItemException;
import com.almatec.controlpiso.exceptions.ServiceException;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpId;
import com.almatec.controlpiso.integrapps.dtos.DataItemImprimirDTO;
import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;
import com.almatec.controlpiso.integrapps.dtos.OpProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioRegistradoDTO;
import com.almatec.controlpiso.integrapps.dtos.ProyectoProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.dtos.SolicitudMariaPrimaDTO;
import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.dtos.UsuarioDTO;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.NovedadCt;
import com.almatec.controlpiso.integrapps.entities.ReportePiezaCt;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;
import com.almatec.controlpiso.integrapps.entities.VistaNovedades;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.NovedadCtService;
import com.almatec.controlpiso.integrapps.services.OperarioService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.integrapps.services.RegistroOperDiaService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;
import com.almatec.controlpiso.integrapps.services.VistaItemLoteDisponibleService;
import com.almatec.controlpiso.integrapps.services.VistaNovedadesService;
import com.almatec.controlpiso.produccion.dtos.ReportePiezaCtDTO;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;

@Controller
@RequestMapping("/produccion")
public class ProduccionController {
	
	@Autowired
	private OrdenPvService ordenPvService;
	
	@Autowired
	private ItemOpService itemOpService;
	
	@Autowired
	private VistaNovedadesService vistaNovedadesService;
	
	@Autowired
	private VistaItemLoteDisponibleService vistaItemLoteDisponibleService;
	
	@Autowired
	private RegistroOperDiaService registroOperDiaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	
	@Autowired
	private DetalleSolicitudMateriaPrimaService detalleSolicitudMateriaPrimaService;
	
	@Autowired
	private ListaMService listaMaterialService;
	
	@Autowired
	private NovedadCtService novedadService;
	
	@Autowired
	private MensajeServices mensajeService;
	
	@Autowired
	private ReportePiezaCtService reportePiezaCtService;
	
	@Autowired
	private OperarioService operarioService;
	
	@Autowired
	private CentroTrabajoService centroTrabajoService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/home")
	public String homeProduction(@RequestParam(required = false) String alert,
			@RequestParam(required = false) String mensaje,
			Model model) {
		model.addAttribute("alert", alert);
		model.addAttribute("mensaje", mensaje);
		return "produccion/planta";
	}
	
	@GetMapping("/proyectos")
	public String listarProyectos(Model modelo, @Param("keyword") String keyword) {
		List<ProyectoProduccionDTO> proyectos = null;
		if(keyword != null) {
			proyectos = ordenPvService.buscarProyectosOrdenPv(keyword);
		}else {
			proyectos = ordenPvService.buscarProyectosOrdenPv();			
		}
		modelo.addAttribute("proyectos", proyectos);
		return "produccion/proyectos";
	}
	
	@GetMapping("/proyectos/{idProyecto}")
	public String verOpsProyecto(Model modelo, @PathVariable String idProyecto) {
		List<OpProduccionDTO> ordenes = ordenPvService.buscarOrdenesPorIdProyecto(idProyecto);
		modelo.addAttribute("ordenes", ordenes);
		return "produccion/ordenes-produccion-proyecto";
	}
	
	@GetMapping("/proyectos/{idProyecto}/op/{numOp}")
	public String verOp(Model modelo, @PathVariable String idProyecto,
						@PathVariable Integer numOp ) {
		List<ItemOp> items = itemOpService.obtenerItemsOpProduccion(numOp);
		
		modelo.addAttribute("items", items);
		return "produccion/detalle-op";
	}
	
	@GetMapping("/novedades")
	public String listarNovedades(Model modelo) {
		List<VistaNovedades> novedades = vistaNovedadesService.obtenerNovedades();
		modelo.addAttribute("novedades", novedades);
		return "produccion/listar-novedades";
	}
	
	@PostMapping("/novedades/{idNovedad}/actualiza")
	public ResponseEntity<?> actualizaEstadoNovedad(@PathVariable Integer idNovedad) {
		try {
			NovedadCt novedad = novedadService.actualizaEstado(idNovedad);
			return ResponseEntity.ok(novedad);			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/materia-prima/solicitud")
	public String solicitudMateriaPrima(Model modelo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioP = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
		UsuarioDTO usuario = new UsuarioDTO(usuarioP);
		Integer consecutivo = solicitudMateriaPrimaService.obtenerConsecutivo();
		List<ConsultaOpId> numsOps = itemOpService.obtenerNumOps();
		SolicitudMateriaPrima solicitud = new SolicitudMateriaPrima();
		List<DetalleSolicitudMateriaPrima> detalles = new ArrayList<>();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("consecutivo", consecutivo + 1);
		modelo.addAttribute("numsOps", numsOps);
		modelo.addAttribute("solicitud", solicitud);
		modelo.addAttribute("detalles", detalles);
		return "produccion/formulario-solicitud-materia-prima";
	}
	
	@PostMapping("/materia-prima/solicitud")
	public ResponseEntity<Map<String, String>> crearsolicitudMateriaPrima(@RequestBody SolicitudMariaPrimaDTO solicitudMP) {
		try {
			SolicitudMateriaPrima solicitud = solicitudMateriaPrimaService.crearSolicitud(solicitudMP.getSolicitud());
			List<DetalleSolicitudMateriaPrima> detalleSolicitud = detalleSolicitudMateriaPrimaService.crearDetalleSolicitud(solicitud.getId(), solicitudMP.getDetalles());
	        Usuario usuarioP = usuarioService.buscarUsuarioPorId(solicitud.getIdUsuarioSol());
	        mensajeService.enviarEmailSolicitudMateriaPrima(solicitud, detalleSolicitud, usuarioP.getNombres());
	        
	        String mensaje = String.format(
	                "Solicitud de transferencia %s-%s creada exitosamente. " +
	                "Se ha enviado una notificación por correo electrónico con los detalles. " +
	                "El proceso continuará con la aprobación correspondiente.",
	                solicitud.getTipoDoc(),
	                solicitud.getId()
	            );
	        
	        Map<String, String> respuesta = new HashMap<>();
	        respuesta.put("mensaje", mensaje);
	        respuesta.put("status", "ok");
	        return ResponseEntity.ok(respuesta);
	    } catch (Exception e) {
	        Map<String, String> respuesta = new HashMap<>();
	        respuesta.put("mensaje", "No se pudo procesar la solicitud de transferencia. Por favor, intente nuevamente o contacte al administrador del sistema.");
	        respuesta.put("status", "error");
	        return ResponseEntity.badRequest().body(respuesta);
	    }
	}
	
	@GetMapping("/listas-materiales/ordenes-produccion/{idOP}")
	public ResponseEntity<List<ListaMDTO>> obtenerListaMaterialesPorIdOp(@PathVariable Integer idOP){
		return ResponseEntity.ok(listaMaterialService.obtenerListaMDTOPorIdOp(idOP));
	}
	
	@PostMapping("/items/disponibilidad")
	public ResponseEntity<List<VistaItemLoteDisponible>> obtenerItemsDispon(@RequestBody SpecItemLoteDTO filtro){
		return ResponseEntity.ok(vistaItemLoteDisponibleService.searchItems(filtro, true));
	}
	
	@GetMapping("/operarios-registrados")
	public String obtenerOperariosRegistrados(Model modelo) {
		List<OperarioRegistradoDTO> operarios = registroOperDiaService.obtenerOperariosRegistrados();
		modelo.addAttribute("operarios", operarios);
		return "produccion/operarios-registrados";
	}
	
	@GetMapping("/impresion-etiquetas")
	public String mostrarOpImpresion(Model modelo) {
		List<VistaOrdenPv> ops = ordenPvService.obtenerOpActivas(); 		
		modelo.addAttribute("ops", ops);
		return "produccion/impresion-etiquetas";
	}
	
	@PostMapping("/imprimir-etiquetas")
	public void registrarDataItemImprimir(@RequestBody List<DataItemImprimirDTO> data) {
		itemOpService.imprimirEtiquetas(data);
	}

	@GetMapping("/op/{idOp}/items")
	public ResponseEntity<List<ItemOp>> getItemsOpByIdOp(@PathVariable Integer idOp){
		return  ResponseEntity.ok(itemOpService.obtenerItemsOpProduccion(idOp));
	}	
	
	
	@GetMapping("/historial-reportes")
    public String mostrarReportes(
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
            @RequestParam(required = false) Integer idOperario,
            @RequestParam(required = false) Integer idCentroT,
            @RequestParam(required = false) String op,        // Nuevo
            @RequestParam(required = false) String co,        // Nuevo
            @RequestParam(required = false) String zona,
            @RequestParam(required = false) ReportePiezaCt.Estado estado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fechaCreacion") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection,
            Model model) throws ServiceException {
		
		LocalDateTime fechaInicioDateTime = fechaInicio != null ? fechaInicio.atStartOfDay() : null;
	    LocalDateTime fechaFinDateTime = fechaFin != null ? fechaFin.atTime(23, 59, 59) : null;
            
        Page<ReportePiezaCtDTO> reportes = reportePiezaCtService.getReportesPiezaWithFilters(
        		fechaInicioDateTime, fechaFinDateTime, idOperario, idCentroT,
        		op, co, zona, estado, page, size, sortBy, sortDirection);
            
        // Add data for filter dropdowns
        model.addAttribute("operarios", operarioService.obtenerOperariosGeneral());
        model.addAttribute("centrosTrabajo", centroTrabajoService.buscarCentrosTrabajo(22));
        
        // Add current filter values
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);
        model.addAttribute("idOperario", idOperario);
        model.addAttribute("idCentroT", idCentroT);
        model.addAttribute("op", op);
        model.addAttribute("co", co);
        model.addAttribute("zona", zona);
        model.addAttribute("estado", estado);
        
     // Calculate page range for pagination
        int totalPages = reportes.getTotalPages();
        int start = Math.max(0, page - 2);
        int end = Math.min(totalPages - 1, page + 2);
        
        List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
        
        // Add pagination data
        model.addAttribute("reportes", reportes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reportes.getTotalPages());
        model.addAttribute("totalItems", reportes.getTotalElements());
        model.addAttribute("pageNumbers", pageNumbers);
        
        return "produccion/historial-reportes";
    }
	
	@ResponseBody
	@PostMapping("/reenviar-reporte/{idReporte}")
	public ResponseEntity<?> reenviarReporte(@PathVariable Integer idReporte) {
	    try {
	        reportePiezaCtService.reprocesarReporte(idReporte);
	        return ResponseEntity.ok()
	            .body(Map.of(
	                "mensaje", "Reporte reenviado exitosamente",
	                "tipo", "success"
	            ));
	    }catch (RutaItemException e) {
	    	log.error("Error de ruta al reenviar reporte {}: {}", idReporte, e.getMessage(), e);
	        // Nuevo manejo específico para RutaItemException
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body(Map.of(
	                "mensaje", e.getMessage(),
	                "codigo", e.getCodigoError(),  // Asumiendo que existe un getter getCodigo()
	                "detalle", e.getDetallesTecnicos(), // Asumiendo que existe un getter getDetalle()
	                "tipo", "danger"
	            ));
	    } catch (ServiceException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body(Map.of(
	                "mensaje", e.getMessage(),
	                "tipo", "warning"
	            ));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(Map.of(
	                "mensaje", "Error interno al reenviar el reporte: " + e.getMessage(),
	                "tipo", "danger"
	            ));
	    }
	}
	
	@PostMapping("/anular-reporte/{idReporte}")
	public ResponseEntity<ResponseMessage> anularReporte(@PathVariable Integer idReporte) {
	    try {
	        ResponseMessage response = reportePiezaCtService.anularReporte(idReporte);
	        if (response.getError()) {
	            return ResponseEntity.badRequest().body(response);
	        }
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        ResponseMessage errorResponse = new ResponseMessage(true, "Error al anular reporte: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}
	
}
