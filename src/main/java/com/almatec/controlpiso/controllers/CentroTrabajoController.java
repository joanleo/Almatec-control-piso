package com.almatec.controlpiso.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.almatec.controlpiso.config.AppConfig;
import com.almatec.controlpiso.integrapps.dtos.CentroOperacion;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.dtos.PiezaOperarioDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Controller
@RequestMapping("/centros-trabajo")
public class CentroTrabajoController {
	
	@Autowired
	private CentroTrabajoService centroTrabajoService;
	
	@Autowired
	private UtilitiesApp util;
	
	@Autowired
	private AppConfig appConfig;
	
	
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
	public List<OpCentroTrabajoDTO> obtenerOpCentroTrabajo(@PathVariable Integer idCT){
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
		return centroTrabajoService.asignarActualizarPiezaOperario(idCT, piezas);
	}
	
	@ResponseBody
	@PostMapping("/{idCT}/paradas")
	public ResponseEntity<?> registrarActualizarParada(){
		return null;
	}
	
	/*@GetMapping("/{idCT}/piezas")
	public ResponseEntity<T> piezasCentroTrabajo(){
		
	}*/

	@GetMapping
	public String CentrosTrabajo(Model modelo) {
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

}
