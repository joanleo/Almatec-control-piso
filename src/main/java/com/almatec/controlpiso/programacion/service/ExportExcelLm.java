package com.almatec.controlpiso.programacion.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;

@Service
public class ExportExcelLm {
	
	private Logger log = LoggerFactory.getLogger(getClass());

    public ByteArrayOutputStream generarExcelListaMateriales(List<ListaMDTO> listaMateriales) {
        // Validamos la entrada
        if (listaMateriales == null || listaMateriales.isEmpty()) {
            throw new IllegalArgumentException("La lista de materiales no puede estar vacía");
        }

        try (Workbook workbook = new XSSFWorkbook(); 
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            
            Sheet sheet = crearYFormatearHoja(workbook);
            crearEncabezados(sheet);
            llenarDatos(sheet, listaMateriales);
            
            // Ajustamos el ancho de las columnas automáticamente
            autoAjustarColumnas(sheet);
            
            workbook.write(outputStream);
            return outputStream;
            
        } catch (IOException e) {
            log.error("Error al generar el archivo Excel", e);
            throw new RuntimeException("Error al generar el archivo Excel", e);
        }
    }

    private Sheet crearYFormatearHoja(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Lista de Materiales");
        // Configuramos el estilo de la hoja
        sheet.setDefaultColumnWidth(15);
        return sheet;
    }

    private void crearEncabezados(Sheet sheet) {
        // Creamos un estilo para los encabezados
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Código", "Descripción", "UM", "Cantidad Requerida", "Cantidad Pendiente"};
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    private void llenarDatos(Sheet sheet, List<ListaMDTO> listaMateriales) {
        CellStyle numberStyle = crearEstiloNumerico(sheet.getWorkbook());
        
        int rowNum = 1;
        for (ListaMDTO material : listaMateriales) {
            Row row = sheet.createRow(rowNum++);
            
            row.createCell(0).setCellValue(material.getCodigoErp());
            row.createCell(1).setCellValue(material.getDescripcion());
            row.createCell(2).setCellValue(material.getUm());
            
            Cell cantRequerida = row.createCell(3);
            cantRequerida.setCellValue(material.getCantRequeridaActualizada().doubleValue());
            cantRequerida.setCellStyle(numberStyle);
            
            Cell cantPendiente = row.createCell(4);
            BigDecimal canPendiente = calcularCantidadPendiente(material);
            cantPendiente.setCellValue(canPendiente.doubleValue());
            cantPendiente.setCellStyle(numberStyle);
        }
    }

    private BigDecimal calcularCantidadPendiente(ListaMDTO material) {
        return material.getCantRequeridaActualizada()
                      .subtract(material.getCantEntregada())
                      .subtract(material.getCantExistencia());
    }

    private void autoAjustarColumnas(Sheet sheet) {
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private CellStyle crearEstiloNumerico(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
        return style;
    }
}
