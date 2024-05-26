package com.almatec.controlpiso.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.integrapps.dtos.ConsultaOpId;
import com.almatec.controlpiso.integrapps.dtos.DataItemImprimirDTO;
import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;
import com.almatec.controlpiso.integrapps.dtos.OpProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioRegistradoDTO;
import com.almatec.controlpiso.integrapps.dtos.ProyectoProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.SolicitudMariaPrimaDTO;
import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.dtos.UsuarioDTO;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.OrdenPv;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;
import com.almatec.controlpiso.integrapps.entities.VistaNovedades;
import com.almatec.controlpiso.integrapps.services.DetalleSolicitudMateriaPrimaService;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.integrapps.services.RegistroOperDiaService;
import com.almatec.controlpiso.integrapps.services.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.integrapps.services.VistaItemLoteDisponibleService;
import com.almatec.controlpiso.integrapps.services.VistaNovedadesService;
import com.almatec.controlpiso.integrapps.services.impl.GestionProduccionService;
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
	private GestionProduccionService gestionProduccionService;
	
	

	@GetMapping("/home")
	public String homeProduction() {
		return "produccion/dashboard";
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
	
	@ResponseBody
	@PostMapping("/materia-prima/solicitud")
	public ResponseEntity<Map<String, String>> crearsolicitudMateriaPrima(@RequestBody SolicitudMariaPrimaDTO solicitudMP) {
		try {
			SolicitudMateriaPrima solicitud = solicitudMateriaPrimaService.crearSolicitud(solicitudMP.getSolicitud());
			detalleSolicitudMateriaPrimaService.crearDetalleSolicitud(solicitud.getId(), solicitudMP.getDetalles());
	        Map<String, String> respuesta = new HashMap<>();
	        respuesta.put("mensaje", "Solicitud creada correctamente");
	        respuesta.put("status", "ok");
	        return ResponseEntity.ok(respuesta);
	    } catch (Exception e) {
	        Map<String, String> respuesta = new HashMap<>();
	        respuesta.put("mensaje", "Error al crear la solicitud");
	        respuesta.put("status", "error");
	        return ResponseEntity.badRequest().body(respuesta);
	    }
	}
	
	@ResponseBody
	@GetMapping("/listas-materiales/ordenes-produccion/{idOP}")
	public List<ListaMDTO> obtenerListaMaterialesPorIdOp(@PathVariable Integer idOP){
		return listaMaterialService.obtenerListaMDTOPorIdOp(idOP);
	}
	
	@ResponseBody
	@PostMapping("/items/disponibilidad")
	public List<VistaItemLoteDisponible> obtenerItemsDispon(@RequestBody SpecItemLoteDTO filtro){
		return vistaItemLoteDisponibleService.searchItems(filtro);
	}
	
	@GetMapping("/operarios-registrados")
	public String obtenerOperariosRegistrados(Model modelo) {
		List<OperarioRegistradoDTO> operarios = registroOperDiaService.obtenerOperariosRegistrados();
		modelo.addAttribute("operarios", operarios);
		return "produccion/operarios-registrados";
	}
	
	@GetMapping("/impresion-etiquetas")
	public String mostrarOpImpresion(Model modelo) {
		List<OrdenPv> ops = ordenPvService.obtenerOpActivas(); 		
		modelo.addAttribute("ops", ops);
		return "produccion/impresion-etiquetas";
	}
	
	@ResponseBody
	@GetMapping("/op/{idOp}/items")
	public List<ItemOp> getItemsOpByIdOp(@PathVariable Integer idOp){
		return  itemOpService.obtenerItemsOpProduccion(idOp);
	}
	
	@ResponseBody
	@PostMapping("/imprimir-etiquetas")
	public void registrarDataItemImprimir(@RequestBody List<DataItemImprimirDTO> data) {
		itemOpService.imprimirEtiquetas(data);
	}
	
	public void crearInformeGeneral() {
		gestionProduccionService.crearInformeGeneral();
	}
}
