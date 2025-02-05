package com.almatec.controlpiso.programacion.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;

@Service
public class ExportExcelLm {

    public ByteArrayOutputStream generarExcelListaMateriales(List<ListaMDTO> listaMateriales) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Lista de Materiales");
        
        // Crear el encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("Descripción");
        headerRow.createCell(2).setCellValue("UM");
        headerRow.createCell(3).setCellValue("Cantidad Requerida");
        headerRow.createCell(3).setCellValue("Cantidad Pendiente");
        
        // Llenar los datos
        int rowNum = 1;
        for (ListaMDTO material : listaMateriales) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(material.getCodigoErp());
            row.createCell(1).setCellValue(material.getDescripcion());
            row.createCell(2).setCellValue(material.getUm());
            row.createCell(3).setCellValue(material.getCantRequeridaActualizada().toString());
            BigDecimal canPendiente = material.getCantRequeridaActualizada().subtract(material.getCantEntregada()).subtract(material.getCantExistencia());
            row.createCell(4).setCellValue(canPendiente.toString());
        }
        
        // Escribir el workbook en un ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        
        return outputStream;
    }
}
