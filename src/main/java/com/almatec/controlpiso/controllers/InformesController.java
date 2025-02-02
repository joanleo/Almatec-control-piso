package com.almatec.controlpiso.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.integrapps.dtos.VistaKgMesDTO;
import com.almatec.controlpiso.integrapps.dtos.VistaKgPorCtDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasReportadas;
import com.almatec.controlpiso.integrapps.services.impl.GestionProduccionService;
import com.almatec.controlpiso.utils.services.ExcelReportService;

@Controller
@RequestMapping("/informes")
public class InformesController {
	
	@Autowired
	private GestionProduccionService service;
	
	@Autowired
	private ExcelReportService report;
	
	@GetMapping("/produccion/excel-general")
    public ResponseEntity<byte[]> downloadGeneralExcelReport() {
    	List<VistaKgPorCtDTO> vista = service.crearInformeGeneral();
        ByteArrayInputStream byteArrayInputStream = report.exportToExcel(vista);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=informe_general.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(byteArrayInputStream.readAllBytes());
    }
    
    @GetMapping("/produccion/excel-mes")
    public ResponseEntity<byte[]> downloadMonthExcelReport() {
    	List<VistaKgMesDTO> vista = service.obtenerDataMes();
        ByteArrayInputStream byteArrayInputStream = report.exportToExcelReportMes(vista);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=consolidado_mensual.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(byteArrayInputStream.readAllBytes());
    }
    
    @GetMapping("/produccion/sabana")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
    	List<VistaPiezasReportadas> resumen =service.obtenerResumenOps();
    	ByteArrayInputStream excelContent = report.exportToExcelSabana(resumen);
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HH_mm_ss");
        String timestamp = LocalDateTime.now().format(formatter);
        
        // Construir nombre del archivo con fecha y hora
        String filename = "sabana_" + timestamp + ".xlsx";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());
        
        return ResponseEntity
        		.ok()
        		.headers(headers)
        		.body(excelContent.readAllBytes());
    }

}
