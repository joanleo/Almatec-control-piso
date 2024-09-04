package com.almatec.controlpiso.almacen.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.almatec.controlpiso.almacen.dto.DataTransportadoraDTO;
import com.almatec.controlpiso.almacen.dto.DetalleSolicitudDTO;
import com.almatec.controlpiso.almacen.dto.RemisionDTO;
import com.almatec.controlpiso.almacen.dto.SolicitudDTO;
import com.almatec.controlpiso.almacen.interfaces.DetalleRemisionInterface;
import com.almatec.controlpiso.almacen.interfaces.EncabezadoRemision;
import com.almatec.controlpiso.almacen.service.DetalleSolicitudMateriaPrimaService;
import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.almacen.service.impl.AlmacenService;
import com.almatec.controlpiso.almacen.util.RemisionPdfService;
import com.almatec.controlpiso.comunicador.services.MensajeServices;
import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.dtos.UsuarioDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.Remision;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;
import com.almatec.controlpiso.integrapps.interfaces.OpConItemPendientePorRemision;
import com.almatec.controlpiso.integrapps.services.VistaItemLoteDisponibleService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;
import com.lowagie.text.DocumentException;


@Controller
@RequestMapping("/almacen")
public class AlmacenController {
	
	private final SolicitudMateriaPrimaService solicitudMateriaPrimaService;	
	private final DetalleSolicitudMateriaPrimaService detalleSolicitudMateriaPrimaService;
	private final AlmacenService almacenService;
	private final UsuarioService usuarioService;
	private final VistaItemLoteDisponibleService vistaItemLoteDisponibleService;
	private final MensajeServices mensajeServices;
	
	public AlmacenController(SolicitudMateriaPrimaService solicitudMateriaPrimaService,
			DetalleSolicitudMateriaPrimaService detalleSolicitudMateriaPrimaService, AlmacenService almacenService,
			UsuarioService usuarioService, VistaItemLoteDisponibleService vistaItemLoteDisponibleService,
			MensajeServices mensajeServices) {
		super();
		this.solicitudMateriaPrimaService = solicitudMateriaPrimaService;
		this.detalleSolicitudMateriaPrimaService = detalleSolicitudMateriaPrimaService;
		this.almacenService = almacenService;
		this.usuarioService = usuarioService;
		this.vistaItemLoteDisponibleService = vistaItemLoteDisponibleService;
		this.mensajeServices = mensajeServices;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/solicitudes")
	public String listarSolicitudesM(Model modelo) {
		List<SolicitudDTO> solicitudes = solicitudMateriaPrimaService.obtenerSolicitudes();		
		modelo.addAttribute("solicitudes", solicitudes);
		return "almacen/solicitudes-materia-prima.html";
	}
	
	@GetMapping("/detalle/solicitud/{idSolic}")
	public ResponseEntity<List<DetalleSolicitudDTO>> obtenerDetalleSolicitud(@PathVariable Integer idSolic) {
		List<DetalleSolicitudDTO> detalleSolicitud = detalleSolicitudMateriaPrimaService.obtenerDetalleDTOPorIdSolic(idSolic);
		return ResponseEntity.ok(detalleSolicitud);
	}
	
	@GetMapping("/remisiones")
	public String ListarRemisiones(Model modelo,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
		Page<EncabezadoRemision> remisionesPage = almacenService.obtenerRemisionesPaginadas(page, size);
		modelo.addAttribute("remisionesPage", remisionesPage);
		return "almacen/listar-remisiones";
	}
	
	@GetMapping("/remisiones/buscar")
    public ResponseEntity<Page<EncabezadoRemision>> buscarRemisiones(
            @RequestParam String termino,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<EncabezadoRemision> resultados = almacenService.buscarRemisiones(termino, page, size);
        return ResponseEntity.ok(resultados);
    }
	
	@GetMapping("/remisiones/nueva")
	public String crearRemision(Model modelo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioP = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
		UsuarioDTO usuario = new UsuarioDTO(usuarioP);
		List<OpConItemPendientePorRemision> ops = almacenService.obtenerOpActivasConItemsPendientesPorEntregar();
		
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("ops", ops);
		return "almacen/formulario-remision";
	}
	
	@GetMapping("/remisiones/{idOpIa}")
	public ResponseEntity<List<ItemOp>> obtenerItemsARemisionar(@PathVariable Integer idOpIa){		
		List<ItemOp> itemRemisionar =almacenService.obtenerItemsARemisionarPorIdOpIa(idOpIa);
		return ResponseEntity.ok(itemRemisionar);
	}
	
	@PostMapping("/remisiones")
	public ResponseEntity<?> guardarRemision(@RequestBody RemisionDTO remisionDTO){
		RemisionDTO remisionSaved;
		try {
            remisionSaved = almacenService.guardarRemision(remisionDTO);            
            
        } catch (IllegalArgumentException e) {
            logger.error("Error al guardar la remisión: Argumento inválido", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Argumento inválido - " + e.getMessage());
        } catch (DataAccessException e) {
            logger.error("Error al guardar la remisión: Error de acceso a datos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Problema de acceso a datos - " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error inesperado al guardar la remisión", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado - " + e.getMessage());
        }
		
		try {
	        // Intentar obtener la remisión completa y enviar el email
			Remision remisionCompleta = almacenService.obtenerRemisionCompleta(remisionSaved.getIdRemision());
            mensajeServices.enviarEmailGeneracionRemision(remisionCompleta);
	    } catch (Exception e) {
	        // Log del error al enviar el email, pero no afecta la respuesta HTTP
	        logger.error("Error al enviar el email de notificación de remisión", e);
	    }

	    // Devolver la remisión guardada, incluso si hubo un error al enviar el email
	    return ResponseEntity.ok(remisionSaved);
	}
	
	@GetMapping("/remisiones/{idRemision}/detalle-remision")
	public ResponseEntity<List<DetalleRemisionInterface>> obtenerDetalleRemision(@PathVariable Long idRemision){		
		List<DetalleRemisionInterface> detalleRemision = almacenService.obtenerDetalleRemision(idRemision);		
		return ResponseEntity.ok(detalleRemision);
	}
	
	@PostMapping("/remisiones/{idRemision}/pdf/generar")
	public void generarPdfRemision(HttpServletResponse response, 
			@PathVariable Long idRemision,
			@RequestBody DataTransportadoraDTO dataTransportadora) {
		
		try {
	        setResponseHeaders(response, idRemision);

	        EncabezadoRemision encabezadoRemision = almacenService.obtenerEncabezadoRemisionPorId(idRemision);
	        List<DetalleRemisionInterface> detallesRemision = almacenService.obtenerDetalleRemision(idRemision);

	        RemisionPdfService remision = new RemisionPdfService(encabezadoRemision, detallesRemision, dataTransportadora);
	        //xmlServices.asignarParametros();
	        //xmlServices.crearRemision(encabezadoRemision.getIdOpIa(), detallesRemision);
	        remision.createPdf(response);
	    } catch (DocumentException e) {
	        handleDocumentException(response, e);
	    } catch (IOException e) {
	        handleIOException(response, e);
	    } catch (Exception e) {
	        handleGenericException(response, e);
	    }
	}
	
	@GetMapping("/consulta-materia-prima")
	public String consultaMateriaPrima(Model model) {
		model.addAttribute("specItemLoteDTO", new SpecItemLoteDTO());
        return "almacen/consulta-materia-prima";
	}
	
	@GetMapping("/buscar-materia-prima")
    public ResponseEntity<Page<VistaItemLoteDisponible>> buscarMateriaPrima(
            @ModelAttribute SpecItemLoteDTO filtro,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<VistaItemLoteDisponible> resultados = vistaItemLoteDisponibleService.searchItems(filtro, false, pageable);
        
        return ResponseEntity.ok(resultados);
    }

	private void setResponseHeaders(HttpServletResponse response, Long idRemision) {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=RM_" + idRemision + "_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);		
	}
	
	private void handleDocumentException(HttpServletResponse response, DocumentException e) {
        logger.error("Error al generar el documento PDF", e);
        try {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al generar el documento PDF: " + e.getMessage());
        } catch (IOException ioException) {
            logger.error("Error al enviar el error HTTP en la respuesta", ioException);
        }
    }

    private void handleIOException(HttpServletResponse response, IOException e) {
        logger.error("Error de entrada/salida al generar el PDF", e);
        try {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error de entrada/salida al generar el PDF: " + e.getMessage());
        } catch (IOException ioException) {
            logger.error("Error al enviar el error HTTP en la respuesta", ioException);
        }
    }

    private void handleGenericException(HttpServletResponse response, Exception e) {
        logger.error("Error al generar el PDF de la remisión", e);
        try {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al generar el PDF de la remisión: " + e.getMessage());
        } catch (IOException ioException) {
            logger.error("Error al enviar el error HTTP en la respuesta", ioException);
        }
    }

}
