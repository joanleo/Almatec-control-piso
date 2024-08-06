package com.almatec.controlpiso.calidad.service;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.calidad.dtos.ReporteCalidadDTO;

@Service
public class ExcelExportService {
	
	public byte[] generateExcel(List<ReporteCalidadDTO> reportes) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reporte de Calidad");

        // Crear el encabezado
        Row headerRow = sheet.createRow(0);
        Field[] fields = ReporteCalidadDTO.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fields[i].getName());
        }

        // Llenar los datos
        int rowNum = 1;
        for (ReporteCalidadDTO reporte : reportes) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < fields.length; i++) {
                Cell cell = row.createCell(i);
                fields[i].setAccessible(true);
                Object value = fields[i].get(reporte);
                if (value != null) {
                    cell.setCellValue(value.toString());
                }
            }
        }

        // Autoajustar el ancho de las columnas
        for (int i = 0; i < fields.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }

}
