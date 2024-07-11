package com.almatec.controlpiso.almacen.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.integrapps.dtos.UsuarioDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.OpConItemPendientePorRemision;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/almacen")
public class AlmacenController {
	
	@Autowired
	private SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	
	@Autowired
	private DetalleSolicitudMateriaPrimaService detalleSolicitudMateriaPrimaService;
	
	@Autowired
	private AlmacenService almacenService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private XmlService xmlServices;
	
	
	@GetMapping("/solicitudes")
	public String listarSolicitudesM(Model modelo) {
		List<SolicitudDTO> solicitudes = solicitudMateriaPrimaService.obtenerSolicitudes();		
		modelo.addAttribute("solicitudes", solicitudes);
		return "almacen/solicitudes-materia-prima.html";
	}
	
	@ResponseBody
	@GetMapping("/detalle/solicitud/{idSolic}")
	public List<DetalleSolicitudDTO> obtenerDetalleSolicitud(@PathVariable Integer idSolic) {
		return detalleSolicitudMateriaPrimaService.obtenerDetalleDTOPorIdSolic(idSolic);
	}
	
	@GetMapping("/remisiones")
	public String ListarRemisiones(Model modelo,
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
		Page<EncabezadoRemision> remisionesPage = almacenService.obtenerRemisionesPaginadas(page, size);
		modelo.addAttribute("remisionesPage", remisionesPage);
		return "almacen/listar-remisiones";
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
	
	@ResponseBody
	@GetMapping("/remisiones/{idOpIa}")
	public List<ItemOp> obtenerItemsARemisionar(@PathVariable Integer idOpIa){		
		return almacenService.obtenerItemsARemisionarPorIdOpIa(idOpIa);
	}
	
	@PostMapping("/remisiones")
	public ResponseEntity<?> guardarRemision(@RequestBody RemisionDTO remisionDTO){
		try {
			RemisionDTO remisionSaved = almacenService.guardarRemision(remisionDTO);
			return ResponseEntity.ok(remisionSaved);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ResponseBody
	@GetMapping("/remisiones/{idRemision}/detalle-remision")
	public List<DetalleRemisionInterface> obtenerDetalleRemision(@PathVariable Long idRemision){		
		return almacenService.obtenerDetalleRemision(idRemision);
	}
	
	@PostMapping("/remisiones/{idRemision}/pdf/generar")
	public void generarPdfRemision(HttpServletResponse response, 
			@PathVariable Long idRemision,
			@RequestBody DataTransportadoraDTO dataTransportadora) throws Exception {
		
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=RM_" + idRemision + "_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		EncabezadoRemision encabezadoRemision = almacenService.obtenerEncabezadoRemisionPorId(idRemision);
		List<DetalleRemisionInterface> detallesRemision = almacenService.obtenerDetalleRemision(idRemision);

		RemisionPdfService remision = new RemisionPdfService(encabezadoRemision, detallesRemision, dataTransportadora);
		//xmlServices.asignarParametros();
		//xmlServices.crearRemision(encabezadoRemision.getIdOpIa(), detallesRemision);
		remision.createPdf(response);
	}

}
