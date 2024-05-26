package com.almatec.controlpiso.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almatec.controlpiso.integrapps.dtos.VistaKgMesDTO;
import com.almatec.controlpiso.integrapps.dtos.VistaKgPorCtDTO;
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
        headers.add("Content-Disposition", "attachment; filename=report.xlsx");

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
        headers.add("Content-Disposition", "attachment; filename=report.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(byteArrayInputStream.readAllBytes());
    }

}
