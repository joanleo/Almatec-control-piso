package com.almatec.controlpiso.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.almatec.controlpiso.integrapps.dtos.InfoParadaDTO;
import com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO;
import com.almatec.controlpiso.integrapps.dtos.NovedadDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.PiezaOperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.RegistroParadaDTO;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.dtos.TiemposOperariosDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasOperarios;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.NovedadCtService;
import com.almatec.controlpiso.integrapps.services.RegistroParadaService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;
import com.almatec.controlpiso.integrapps.services.VistaItemsRutasService;
import com.almatec.controlpiso.integrapps.services.VistaPiezasOperariosService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.utils.ExportOpCentroTrabajoToPdf;
import com.almatec.controlpiso.utils.ExportOptoPdf;
import com.almatec.controlpiso.utils.UtilitiesApp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;

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
	
	@Autowired
	private RegistroParadaService registroParadaService;
	
	@Autowired
	private ListaMService listaMService;

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@GetMapping("/listar")
	@ResponseBody
	public List<CentroTrabajo> listarCentrosTrabajo(Model modelo) {
		Usuario usuario = util.obtenerUsuarioAtenticado();
		return centroTrabajoService.buscarCentrosTrabajo(usuario.getCia());
	}
	
	@GetMapping("/{idCT}/ordenes-produccion")
	@ResponseBody
	public Set<OpCentroTrabajoDTO> obtenerOpCentroTrabajo(@PathVariable Integer idCT){
		return centroTrabajoService.buscarOpCT(idCT);
	}
	
	@ResponseBody
	@GetMapping("/{idCT}/{idConfig}/piezas-operarios-ct-proceso")
	public List<VistaPiezasOperarios> piezasOperariosCentroTrabajo(
			@PathVariable Integer idCT,
			@PathVariable Integer idConfig){
		return vistaPiezasOperariosService.obtenerPiezasCentroTrabajoProceso(idCT, idConfig);
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
	@GetMapping("/{idProceso}/tiempos_operarios")
	public List<TiemposOperariosDTO> obtenerTiempoOperarios(@PathVariable Integer idProceso){
		return centroTrabajoService.obtenerTiemposOperarios(idProceso);
	}
	
	
	@ResponseBody
	@PostMapping("/{idCT}/agregar-retirar-operario")
	public ResponseEntity<String> agregarRetirarOperario(@RequestBody OperarioDTO operarioDTO) {
		return ResponseEntity.ok(centroTrabajoService.agregarRetirarOperario(operarioDTO));
	}
	
	@GetMapping("/{idCT}/{idConfigP}/operarios")
	@ResponseBody
	public List<Operario> obtenerOperariosCT(@PathVariable Integer idCT,
											   @PathVariable Integer idConfigP ){
		return centroTrabajoService.buscarOperariosCtDia(idCT, idConfigP);
	}
	
		
	
	@ResponseBody
	@PostMapping("/{idCT}/paradas")
	public ResponseEntity<?> registrarActualizarParada(@PathVariable Integer idCT, @RequestBody RegistroParadaDTO registroParada){
		ErrorMensaje mensaje = registroParadaService.registrarActualizarParada(registroParada, idCT);
		if(Boolean.TRUE.equals(mensaje.getError())) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        } else {
            return ResponseEntity.ok(mensaje);
        }
	}
	
	@ResponseBody
	@PostMapping("/{idCT}/piezas-operario-proceso")
	public List<VistaPiezasOperarios> piezasCentroTrabajo(@RequestBody OperarioDTO operario){
		List<VistaPiezasOperarios> piezas = vistaPiezasOperariosService.obtenerPiezasOperarioProceso(operario);
		return piezas;
	}
	
	
	
	@GetMapping("/{idCT}/reporte")
	public String reportePiezasCT(@PathVariable Integer idCT,
								  @RequestParam Long idItem,
								  @RequestParam Integer idOperario,
								  Model modelo) {
		
		ReporteDTO reporte = centroTrabajoService.buscarItemCt(idItem, idCT, idOperario);
		List<LoteConCodigoDTO> lotes = listaMService.obtenerLotesOpPorItem(idItem);
				
		modelo.addAttribute("reporte", reporte);
		modelo.addAttribute("lotes", lotes);
		
		return "produccion/formulario-reporte-piezas-ct";
	}
	
	@PostMapping("/{idCT}/reporte")
	public String guardarReportePiezas(@ModelAttribute("reporte") ReporteDTO reporte,
										RedirectAttributes flash) {

		ErrorMensaje mensaje = reportePiezaCtService.guardarReporte(reporte);
		if(Boolean.TRUE.equals(mensaje.getError())) {
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
								  Model modelo) throws JsonProcessingException {
		ReporteDTO reporte = centroTrabajoService.buscarItemCt(idItem, idCT, idOperario);
		NovedadDTO novedad = new NovedadDTO(reporte);
		Integer ultimoConsecutivo = novedadCtService.obtenerUltimoConsecutivo();
		List<LoteConCodigoDTO> lotes = listaMService.obtenerLotesOpPorItem(idItem);
		ObjectMapper mapper = new ObjectMapper();
	    String lotesJson = mapper.writeValueAsString(lotes);
	    	    
		modelo.addAttribute("novedad", novedad);
		modelo.addAttribute("consecutivo", ultimoConsecutivo);
		modelo.addAttribute("lotes", lotes);
		modelo.addAttribute("lotesJson", lotesJson);
		return "produccion/formulario-novedades";
	}
	
	@PostMapping("/{idCT}/novedades")
	public String guardarNovedades(@ModelAttribute("novedad") NovedadDTO novedad,
										RedirectAttributes flash) {
		ErrorMensaje mensaje = novedadCtService.guardarNovedad(novedad);
		if(Boolean.TRUE.equals(mensaje.getError())) {
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

        Path path = Paths.get(appConfig.getPdfFolderPath(), nombreDelArchivo);
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
		logger.info("Se genera pdf con la informacion de la OP-{}",idOp);
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
		CentroTrabajo centroT =  centroTrabajoService.buscarCentroTrabajoPorIdCtErp(idCT);
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=carga_trabajo_" + centroT.getNombre() + "_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		Set<OpCentroTrabajoDTO> opsCt = centroTrabajoService.buscarOpCT(idCT);
		
		Rectangle letter = PageSize.LETTER;
		float halfLetterHeight = letter.getHeight() / 2;
		Rectangle halfLetter = new Rectangle(letter.getWidth(), halfLetterHeight);
		ExportOpCentroTrabajoToPdf documento = new ExportOpCentroTrabajoToPdf(opsCt, opsSeleccionadas, centroT, letter);
		documento.export(response);
	}
	
	@GetMapping("/{idCT}/proceso/{idConfigProceso}/paradas")
	public ResponseEntity<List<InfoParadaDTO>> obtenerInfoParadasCT(@PathVariable Integer idConfigProceso){
		List<InfoParadaDTO> paradas =  registroParadaService.obtenerInfoParadasCT(idConfigProceso);
		return ResponseEntity.ok(paradas);
	} 

}
