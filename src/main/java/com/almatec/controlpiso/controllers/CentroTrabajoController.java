package com.almatec.controlpiso.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almatec.controlpiso.config.AppConfig;
import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.NovedadDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.PiezaOperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.integrapps.services.NovedadCtService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;
import com.almatec.controlpiso.integrapps.services.VistaItemsRutasService;
import com.almatec.controlpiso.integrapps.services.VistaPiezasOperariosService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.utils.ExportOpCentroTrabajoToPdf;
import com.almatec.controlpiso.utils.ExportOptoPdf;
import com.almatec.controlpiso.utils.UtilitiesApp;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/centros-trabajo")
public class CentroTrabajoController {
	
	@Autowired
	private CentroTrabajoService centroTrabajoService;
	
	@Autowired
	private UtilitiesApp util;
	
	@Autowired
	private AppConfig appConfig;
	
	@Autowired
	private VistaItemsRutasService vistaItemsRutasService;
	
	@Autowired
	private VistaPiezasOperariosService vistaPiezasOperariosService;
	
	@Autowired
	private ReportePiezaCtService reportePiezaCtService;
	
	@Autowired
	private NovedadCtService novedadCtService;

	
	@GetMapping("/listar")
	@ResponseBody
	public List<CentroTrabajo> listarCentrosTrabajo(Model modelo) {
		Usuario usuario = util.obtenerUsuarioAtenticado();
		return centroTrabajoService.buscarCentrosTrabajo(usuario.getCia());
	}
	
	@PostMapping("/{idCT}/agregar-retirar-operario")
	@ResponseBody
	public ResponseEntity<String> agregarRetirarOperario(@RequestBody OperarioDTO operarioDTO) {
		return ResponseEntity.ok(centroTrabajoService.agregarRetirarOperario(operarioDTO));
	}
	
	@GetMapping("/{idCT}/{idConfigP}/operarios")
	@ResponseBody
	public List<Operario> obtenerOperariosCT(@PathVariable Integer idCT,
											   @PathVariable Integer idConfigP ){
		return centroTrabajoService.buscarOperariosCtDia(idCT, idConfigP);
	}
	
	@GetMapping("/{idCT}/ordenes-produccion")
	@ResponseBody
	public Set<OpCentroTrabajoDTO> obtenerOpCentroTrabajo(@PathVariable Integer idCT){
		return centroTrabajoService.buscarOpCT(idCT);
	}
	
	@ResponseBody
	@GetMapping("/{idProceso}/tiempos_operarios")
	public List<VistaTiemposOperarios> obtenerTiempoOperarios(@PathVariable Integer idProceso){
		return centroTrabajoService.obtenerTiemposOperarios(idProceso);
	}
	
	@ResponseBody
	@PostMapping("/{idCT}/asignar-pieza")
	public ResponseEntity<?> asignarActualizarPiezaOperario(@PathVariable Integer idCT,
															@RequestBody List<PiezaOperarioDTO> piezas){
		try {
	        centroTrabajoService.asignarActualizarPiezaOperario(idCT, piezas);
	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
	    }
	}
	
	@ResponseBody
	@PostMapping("/{idCT}/paradas")
	public ResponseEntity<?> registrarActualizarParada(@PathVariable Integer idCT){
		return null;
	}
	
	@ResponseBody
	@PostMapping("/{idCT}/piezas-operario-proceso")
	public Set<OpCentroTrabajoDTO> piezasCentroTrabajo(@RequestBody OperarioDTO operario){
		System.out.println("Se reciben datos: " + operario);
		Set<OpCentroTrabajoDTO> piezas = vistaPiezasOperariosService.obtenerPiezasOperarioProceso(operario);
		return piezas;
	}
	
	@GetMapping("/{idCT}/reporte")
	public String reportePiezasCT(@PathVariable Integer idCT,
								  @RequestParam Long idItem,
								  @RequestParam Integer idOperario,
								  Model modelo) {
		ReporteDTO reporte = centroTrabajoService.buscarItemCt(idItem, idCT, idOperario);
		System.out.println("Operario enviado al formulario: " + reporte.getOperario());
		modelo.addAttribute("reporte", reporte);
		return "produccion/formulario-reporte-piezas-ct";
	}
	
	@PostMapping("/{idCT}/reporte")
	public String guardarReportePiezas(@ModelAttribute("reporte") ReporteDTO reporte,
										RedirectAttributes flash) {
		System.out.println("Guadando reporte: " + reporte);
		System.out.println("Operario recibido: " + reporte.getOperario());
		ErrorMensaje mensaje = reportePiezaCtService.guardarReporte(reporte);
		if(mensaje.getError()) {
			flash.addFlashAttribute("error", mensaje.getMensaje());
			return "redirect:/produccion/home";			
		}
			flash.addFlashAttribute("message", mensaje.getMensaje());
			return "redirect:/produccion/home";
	}
	
	@GetMapping("/{idCT}/novedades")
	public String reporteNovedadesCT(@PathVariable Integer idCT,
								  @RequestParam Long idItem,
								  @RequestParam Integer idOperario,
								  Model modelo) {
		ReporteDTO reporte = centroTrabajoService.buscarItemCt(idItem, idCT, idOperario);
		Integer ultimoConsecutivo = novedadCtService.obtenerUltimoConsecutivo();
		NovedadDTO novedad = new NovedadDTO(reporte); 
		System.out.println("Operario enviado al formulario novedades: " + reporte.getOperario());
		modelo.addAttribute("novedad", novedad);
		modelo.addAttribute("consecutivo", ultimoConsecutivo);
		return "produccion/formulario-novedades";
	}
	
	@PostMapping("/{idCT}/novedades")
	public String guardarNovedades(@ModelAttribute("novedad") NovedadDTO novedad,
										RedirectAttributes flash) {
		System.out.println("Guadando Novedad: " + novedad);
		System.out.println("Operario recibido: " + novedad.getOperario());
		ErrorMensaje mensaje = novedadCtService.guardarNovedad(novedad);
		if(mensaje.getError()) {
			flash.addFlashAttribute("error", mensaje.getMensaje());
			return "redirect:/produccion/home";			
		}
			flash.addFlashAttribute("message", mensaje.getMensaje());
			return "redirect:/produccion/home";
	}
	

	@GetMapping	public String CentrosTrabajo(Model modelo) {
		Usuario usuario = util.obtenerUsuarioAtenticado();
		List<CentroTrabajo> centrosTrabajo = centroTrabajoService.buscarCentrosTrabajo(usuario.getCia());
		
		modelo.addAttribute("centrosTrabajo", centrosTrabajo);
		return "configuracion/centros-trabajo/listar-centros-trabajo";
	}
	
	@GetMapping("/nuevo")
	public String nuevoCentroTrabajo(Model modelo) {
		Usuario usuario = util.obtenerUsuarioAtenticado();
		List<CentroOperacion> centrosOperacion = centroTrabajoService.buscarCentrosOperacion(usuario.getCia());
		List<CentroTrabajo> centrosTrabajo = centroTrabajoService.buscarCentrosTrabajo(usuario.getCia());
		CentroTrabajo centroTrabajo = new CentroTrabajo();
		modelo.addAttribute("centrosOperacion", centrosOperacion);
		modelo.addAttribute("centrosTrabajo", centrosTrabajo);
		modelo.addAttribute("centroTrabajo", centroTrabajo);
		return "configuracion/centros-trabajo/formulario-centro-trabajo";
	}
	
	@GetMapping("/actualizar/{id}")
	public String actualizar(Model modelo, @PathVariable Integer id) {
		CentroTrabajo centroTrabajo = centroTrabajoService.buscarCentroTrabajo(id);
		Usuario usuario = util.obtenerUsuarioAtenticado();
		List<CentroOperacion> centrosOperacion = centroTrabajoService.buscarCentrosOperacion(usuario.getCia());
		List<CentroTrabajo> centrosTrabajo = centroTrabajoService.buscarCentrosTrabajo(usuario.getCia());
		modelo.addAttribute("centroTrabajo", centroTrabajo);
		modelo.addAttribute("centrosOperacion", centrosOperacion);
		modelo.addAttribute("centrosTrabajo", centrosTrabajo);
		return "configuracion/centros-trabajo/formulario-centro-trabajo";
	}
	
	public String guardar(@ModelAttribute("centroTrabajo") CentroTrabajo centroTrabajo) {
		centroTrabajoService.guardar(centroTrabajo);
		return "redirect:/centros-trabajo";
	}
	
	
	
	
	
	@GetMapping("/descargar-pdf/{nombreDelArchivo}")
    public ResponseEntity<Resource> descargarPDF(@PathVariable String nombreDelArchivo) throws IOException {
		System.out.println("obtener planos");
		System.out.println(appConfig.getPdfFolderPath());
		System.out.println(nombreDelArchivo);
        Path path = Paths.get(appConfig.getPdfFolderPath(), nombreDelArchivo);
        System.out.println(path);
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .body(resource);
    }
	
	@GetMapping("/op/{idOp}/descarga")
	public void exportToPdfOp(HttpServletResponse response,
			@PathVariable Integer idOp) throws DocumentException, IOException{
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Op_" + idOp + "_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		List<OpCentroTrabajoDTO> itemsOp = vistaItemsRutasService.buscarOp(idOp);
		
		ExportOptoPdf exportar = new ExportOptoPdf(itemsOp);
		exportar.export(response);
	}
	
	@GetMapping("/reporte-piezas")
	public String reportePieza(Model modelo) {
		return "produccion/formulario-reporte-piezas-ct";
	}
	
	@GetMapping("/reporte-novedades")
	public String reporteNovedades(Model modelo) {
		return "produccion/formulario-novedades";
	}
	
	@GetMapping("/carga")
	public String cargaCt(Model modelo) {		
		List<CentroTrabajo> centros = centroTrabajoService.buscarCentrosTrabajo(22);
		modelo.addAttribute("centros", centros);
		return "produccion/carga-centros-trabajo";
	}
	
	@PostMapping("/{idCT}/descargar")
	public void opsCentroTrabajo(HttpServletResponse response,
							@PathVariable Integer idCT,
							@RequestBody List<Integer> opsSeleccionadas) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Ops_" + idCT + "_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		System.out.println("Descargando listado ops para centro de trabajo: " + idCT);
		System.out.println("Ordenes de produccion seleccionadas:");
		for(Integer id: opsSeleccionadas) {
			System.out.println(id);
		}
		Set<OpCentroTrabajoDTO> setOpsCt = centroTrabajoService.buscarOpCT(idCT);
		List<OpCentroTrabajoDTO> opsCt = new ArrayList<>(setOpsCt);
		
		CentroTrabajo centroT =  centroTrabajoService.buscarCentroTrabajo(idCT);
		
		ExportOpCentroTrabajoToPdf documento = new ExportOpCentroTrabajoToPdf(opsCt, opsSeleccionadas, centroT);
		documento.export(response);
	}

}
