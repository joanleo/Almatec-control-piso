package com.almatec.controlpiso.calidad.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.almatec.controlpiso.calidad.dtos.ReporteCalidadDTO;
import com.almatec.controlpiso.calidad.service.ExcelExportService;
import com.almatec.controlpiso.calidad.service.PdfExportService;
import com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO;
import com.almatec.controlpiso.integrapps.entities.Operario;
import com.almatec.controlpiso.integrapps.entities.ReporteCalidad;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.OperarioService;
import com.almatec.controlpiso.integrapps.services.ReporteCalidadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/calidad")
public class CalidadController {
	
	private final ListaMService listaMService;
	private final ReporteCalidadService reporteCalidadService;
	private final OperarioService operarioService;
	private final PdfExportService pdfExportService;
	private final ExcelExportService excelExportService;
	
	public CalidadController(ListaMService listaMService,
			ReporteCalidadService reporteCalidadService,
			OperarioService operarioService,
			PdfExportService pdfExportService,
			ExcelExportService excelExportService) {
		super();
		this.listaMService = listaMService;
		this.reporteCalidadService = reporteCalidadService;
		this.operarioService = operarioService;
		this.pdfExportService = pdfExportService;
		this.excelExportService = excelExportService;
	}

	@GetMapping("/formulario/centro-trabajo/{idCT}")
	public String obtenerFormulario(@PathVariable Integer idCT,
			  @RequestParam(required = false) Long idItem,
			  @RequestParam(required = false) Integer idOperario,
			  @RequestParam(required = false) Long id,
			  Model modelo) throws JsonProcessingException {
		
		ReporteCalidadDTO formulario;
		Integer idOper=0;
		String nombreOperario="";
		if (id != null) {
	        // Modo edición
	        formulario = reporteCalidadService.obtenerFormularioPorId(id);
	        idOper = formulario.getIdOperario();
	    	nombreOperario = formulario.getNombreOperario();
	    } else {
	    	formulario = reporteCalidadService.buscarItemReporteCalidadCt(idItem, idCT, idOperario);
	    	Operario operario = operarioService.buscarOperarioPorId(idOperario);
	    	idOper = operario.getId();
	    	nombreOperario = operario.getNombre();
	    }
		
		if (formulario.getFechaDoc() == null) {
	        formulario.setFechaDoc(LocalDateTime.now());
	    }
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	    String fechaFormateada = formulario.getFechaDoc().format(formatter);
	    modelo.addAttribute("fechaFormateada", fechaFormateada);

		List<LoteConCodigoDTO> lotes = listaMService.obtenerLotesOpPorItem(idItem);
		ObjectMapper mapper = new ObjectMapper();
	    String lotesJson = mapper.writeValueAsString(lotes);

	    modelo.addAttribute("idOperario", idOper);
	    modelo.addAttribute("nombreOperario", nombreOperario);
		modelo.addAttribute("formulario", formulario);
		modelo.addAttribute("lotes", lotes);
		modelo.addAttribute("lotesJson", lotesJson);
		modelo.addAttribute("modoEdicion", id != null);
		
		return "calidad/formulario";
	}
	
	@PostMapping("/formulario/guardar")
	public ResponseEntity<?> guardarFormularioCalidad(@RequestBody ReporteCalidadDTO formCalidad) {
		try {
			ReporteCalidad reporte = reporteCalidadService.guardarReporteCalidad(formCalidad); 
			return ResponseEntity.ok(reporte);
		} catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Argumento inválido - " + e.getMessage());
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Problema de acceso a datos - " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado - " + e.getMessage());
        }
	}
	
	@GetMapping("/formularios")
	public String listarFormularios(@RequestParam(defaultValue = "0") int page,
	                                @RequestParam(defaultValue = "10") int size,
	                                @RequestParam(required = false) String search,
	                                Model model) {
	    Page<ReporteCalidadDTO> formularios = reporteCalidadService.listarFormularios(page, size, search);
	    model.addAttribute("formularios", formularios);
	    model.addAttribute("search", search);
	    return "calidad/listar-formularios";
	}
	
	@PostMapping("/exportar-pdf")
    public ResponseEntity<ByteArrayResource> exportarPdf(@RequestBody List<Long> ids) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (Long id : ids) {
            ReporteCalidadDTO formulario = reporteCalidadService.obtenerFormularioPorId(id);
            ByteArrayOutputStream pdfOutputStream = pdfExportService.generarPdfFormulario(formulario);
            
            ZipEntry entry = new ZipEntry("formulario_" + id + ".pdf");
            zos.putNextEntry(entry);
            zos.write(pdfOutputStream.toByteArray());
            zos.closeEntry();
        }
        
        zos.close();
        
        ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());
        
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=formularios_calidad.zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(baos.size())
                .body(resource);
    }
	
	@PostMapping("/exportar-excel")
    public ResponseEntity<byte[]> downloadGeneralExcelReport(@RequestBody List<Long> ids) throws Exception {
        
        List<ReporteCalidadDTO> reportes = new ArrayList<>();
        for (Long id : ids) {
            ReporteCalidadDTO formulario = reporteCalidadService.obtenerFormularioPorId(id);
            reportes.add(formulario);
        }

        byte[] excelBytes = excelExportService.generateExcel(reportes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=reporte_calidad.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelBytes);
    }

}
