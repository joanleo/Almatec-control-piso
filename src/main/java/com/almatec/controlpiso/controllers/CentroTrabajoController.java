package com.almatec.controlpiso.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.almatec.controlpiso.config.AppConfig;
import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
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
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.NovedadCtService;
import com.almatec.controlpiso.integrapps.services.RegistroParadaService;
import com.almatec.controlpiso.integrapps.services.ReportePiezaCtService;
import com.almatec.controlpiso.integrapps.services.VistaPiezasOperariosService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.utils.CentrosTrabajoPDFService;
import com.almatec.controlpiso.utils.ExportOpCentroTrabajoToPdf;
import com.almatec.controlpiso.utils.UtilitiesApp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;

@Controller
@RequestMapping("/centros-trabajo")
public class CentroTrabajoController {
	
	private final CentroTrabajoService centroTrabajoService;
	private final UtilitiesApp util;
	private final AppConfig appConfig;
	private final VistaPiezasOperariosService vistaPiezasOperariosService;
	private final ReportePiezaCtService reportePiezaCtService;
	private final NovedadCtService novedadCtService;
	private final RegistroParadaService registroParadaService;
	private final ListaMService listaMService;
	private final CentrosTrabajoPDFService centrosTrabajoPDFService;
	private final ItemOpService itemService;
	

	
	public CentroTrabajoController(
			CentroTrabajoService centroTrabajoService, 
			UtilitiesApp util, 
			AppConfig appConfig,
			VistaPiezasOperariosService vistaPiezasOperariosService,
			ReportePiezaCtService reportePiezaCtService, 
			NovedadCtService novedadCtService,
			RegistroParadaService registroParadaService, 
			ListaMService listaMService,
			CentrosTrabajoPDFService centrosTrabajoPDFService,
			ItemOpService itemService) {
		super();
		this.centroTrabajoService = centroTrabajoService;
		this.util = util;
		this.appConfig = appConfig;

		this.vistaPiezasOperariosService = vistaPiezasOperariosService;
		this.reportePiezaCtService = reportePiezaCtService;
		this.novedadCtService = novedadCtService;
		this.registroParadaService = registroParadaService;
		this.listaMService = listaMService;
		this.centrosTrabajoPDFService = centrosTrabajoPDFService;
		this.itemService = itemService;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<CentroTrabajo>> listarCentrosTrabajo(Model modelo) {
		Usuario usuario = util.obtenerUsuarioAtenticado();
		return ResponseEntity.ok(centroTrabajoService.buscarCentrosTrabajo(usuario.getCia()));
	}
	
	@GetMapping("/{idCT}/ordenes-produccion")
	public ResponseEntity<Set<OpCentroTrabajoDTO>> obtenerOpCentroTrabajo(@PathVariable Integer idCT){
		return ResponseEntity.ok(centroTrabajoService.buscarOpCT(idCT));
	}
	
	@GetMapping("/{idCT}/{idConfig}/piezas-operarios-ct-proceso")
	public ResponseEntity<List<VistaPiezasOperarios>> piezasOperariosCentroTrabajo(
			@PathVariable Integer idCT,
			@PathVariable Integer idConfig){
		return ResponseEntity.ok(vistaPiezasOperariosService.obtenerPiezasCentroTrabajoProceso(idCT, idConfig));
	}
	
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
	
	@GetMapping("/{idProceso}/tiempos_operarios")
	public ResponseEntity<List<TiemposOperariosDTO>> obtenerTiempoOperarios(@PathVariable Integer idProceso){
		return ResponseEntity.ok(centroTrabajoService.obtenerTiemposOperarios(idProceso));
	}
	
	
	@PostMapping("/agregar-retirar-operario")
	public ResponseEntity<String> agregarRetirarOperario(@RequestBody OperarioDTO operarioDTO) {
		return ResponseEntity.ok(centroTrabajoService.agregarRetirarOperario(operarioDTO));
	}
	
	@GetMapping("/{idCT}/{idConfigP}/operarios")
	public ResponseEntity<List<Operario>> obtenerOperariosCT(@PathVariable Integer idCT,
											   @PathVariable Integer idConfigP ){
		return ResponseEntity.ok(centroTrabajoService.buscarOperariosCtDia(idCT, idConfigP));
	}
		
	@PostMapping("/{idCT}/paradas")
	public ResponseEntity<?> registrarActualizarParada(@PathVariable Integer idCT, @RequestBody RegistroParadaDTO registroParada){
		ResponseMessage mensaje = registroParadaService.registrarActualizarParada(registroParada, idCT);
		if(Boolean.TRUE.equals(mensaje.getError())) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        } else {
            return ResponseEntity.ok(mensaje);
        }
	}
	
	@PostMapping("/piezas-operario-proceso")
	public ResponseEntity<List<VistaPiezasOperarios>> piezasCentroTrabajo(@RequestBody OperarioDTO operario){
		List<VistaPiezasOperarios> piezas = vistaPiezasOperariosService.obtenerPiezasOperarioProceso(operario);
		return ResponseEntity.ok(piezas);
	}
	
	
	
	@GetMapping("/{idCT}/reporte")
	public String reportePiezasCT(@PathVariable Integer idCT,
								  @RequestParam Long idItemOp,
								  @RequestParam Integer idOperario,
								  @RequestParam Integer idItem,
								  @RequestParam String tipo,
								  @RequestParam(required = false) Integer idConfigProceso,
								  Model modelo) {
		
		StopWatch stopWatch = new StopWatch();
        stopWatch.start();
		ReporteDTO reporte = centroTrabajoService.buscarItemCtReporte(idItemOp, idCT, idOperario, idItem, tipo);
		reporte.setPesoPintura(reporte.getPesoPintura().divide(new BigDecimal(reporte.getCantSol()), 2, RoundingMode.HALF_UP));
		stopWatch.stop();
		logger.info("Tiempo de ejecución Total: {} ms", stopWatch.getTotalTimeMillis());
		stopWatch.start();
		List<LoteConCodigoDTO> lotes = listaMService.obtenerLotesOpPorItem(idItemOp);
		stopWatch.stop();
		logger.info("Tiempo de ejecución obtencion de lotes: {} ms", stopWatch.getTotalTimeMillis());
		modelo.addAttribute("reporte", reporte);
		modelo.addAttribute("lotes", lotes);
		
		return "produccion/formulario-reporte-piezas-ct";
	}
	
	@PostMapping("/reporte")
	public String guardarReportePiezas(@ModelAttribute ReporteDTO reporte,
										RedirectAttributes flash) {

		ResponseMessage mensaje = reportePiezaCtService.guardarReporte(reporte);
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
	
	@PostMapping("/novedades")
	public String guardarNovedades(@ModelAttribute NovedadDTO novedad,
										RedirectAttributes flash) {
		ResponseMessage mensaje = novedadCtService.guardarNovedad(novedad);
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
	
	public String guardar(@ModelAttribute CentroTrabajo centroTrabajo) {
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
		//float halfLetterHeight = letter.getHeight() / 2;
		//Rectangle halfLetter = new Rectangle(letter.getWidth(), halfLetterHeight);
		ExportOpCentroTrabajoToPdf documento = new ExportOpCentroTrabajoToPdf(opsCt, opsSeleccionadas, centroT, letter);
		documento.export(response);
	}
	
	@GetMapping("/proceso/{idConfigProceso}/paradas")
	public ResponseEntity<List<InfoParadaDTO>> obtenerInfoParadasCT(@PathVariable Integer idConfigProceso){
		List<InfoParadaDTO> paradas =  registroParadaService.obtenerInfoParadasCT(idConfigProceso);
		return ResponseEntity.ok(paradas);
	}
	
	@GetMapping("/generar-codigos-barra")
	public void generarBarCodeOperarios(HttpServletResponse response) throws DocumentException, IOException {
		centrosTrabajoPDFService.generarBarCodeCentrosTrabajo(response);
	}
	
	@GetMapping("/{idCT}/piezas-fabricadas/{idItemOp}/tipo/{tipo}/{idItemFab}")
	public ResponseEntity<Integer> obtenerCantPiezasFabricadas(@PathVariable Integer idCT,
	        @PathVariable Long idItemOp,
	        @PathVariable String tipo,
	        @PathVariable Integer idItemFab) {
		Integer cantidadFabricada;
	    if(tipo.equals("parte")) {
	        cantidadFabricada = reportePiezaCtService.buscarCantidadesFabricadasPerfil(idItemOp, idItemFab, idCT);
	    } else {
	        cantidadFabricada = reportePiezaCtService.buscarCantidadesFabricadasConjunto(idItemOp, idItemFab, idCT);
	    }
	    
	    // Manejo de null
	    if(cantidadFabricada == null) {
	        cantidadFabricada = 0;
	    }
	    //Integer cantidadFabricada = vistaPiezasOperariosService.obtenerCantPiezasFabricadas(idCT, idItemOp);
	    return ResponseEntity.ok(cantidadFabricada);
	}

}
