package com.almatec.controlpiso.utils.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.VistaKgMesDTO;
import com.almatec.controlpiso.integrapps.dtos.VistaKgPorCtDTO;

@Service
public class ExcelReportService {
	
	public ByteArrayInputStream exportToExcel(List<VistaKgPorCtDTO> vista) {
		
		try (
				Workbook workbook = new XSSFWorkbook(); 
				ByteArrayOutputStream out = new ByteArrayOutputStream()){
			
			Map<Date, Map<String, Double>> data = agruparDatosPorFechaYTrabajo(vista);
			
			Set<String> centrosTrabajo = obtenerTrabajosUnicos(vista);
			
			Sheet sheet = workbook.createSheet("Report");
			
			int rowIndex = 0;
			Row headerRow = sheet.createRow(rowIndex++);
			Cell fechaHeaderCell = headerRow.createCell(0);
	        fechaHeaderCell.setCellValue("Fecha");

	        int cellIndex = 1;
	        for (String trabajo : centrosTrabajo) {
	            Cell cell = headerRow.createCell(cellIndex++);
	            cell.setCellValue(trabajo);
	        }
	        
	        for (Map.Entry<Date, Map<String, Double>> entry : data.entrySet()) {
	            Row row = sheet.createRow(rowIndex++);
	            row.createCell(0).setCellValue(entry.getKey().toString());

	            cellIndex = 1;
	            for (String trabajo : centrosTrabajo) {
	                Double valor = entry.getValue().get(trabajo);
	                row.createCell(cellIndex++).setCellValue(valor != null ? valor : 0);
	            }
	        }
	        
	        
	        for (int i = 0; i <= centrosTrabajo.size(); i++) {
	            sheet.autoSizeColumn(i);
	        }
			
	        workbook.write(out);
	        return new ByteArrayInputStream(out.toByteArray());

		} catch (IOException  e) {
			throw new RuntimeException("Failed to generate Excel report", e);
		}
		
	}

	private Set<String> obtenerTrabajosUnicos(List<VistaKgPorCtDTO> vista) {
		return vista.stream()
                .map(VistaKgPorCtDTO::getCentroTrabajo)
                .collect(Collectors.toSet());
	}

	private Map<Date, Map<String, Double>> agruparDatosPorFechaYTrabajo(List<VistaKgPorCtDTO> vista) {
		return vista.stream()
                .collect(Collectors.groupingBy(
                        VistaKgPorCtDTO::getFecha,
                        Collectors.groupingBy(
                                VistaKgPorCtDTO::getCentroTrabajo,
                                Collectors.summingDouble(VistaKgPorCtDTO::getKgsCumplidos)
                        )
                ));
	}

	public ByteArrayInputStream exportToExcelReportMes(List<VistaKgMesDTO> vista) {
		try (
				Workbook workbook = new XSSFWorkbook(); 
				ByteArrayOutputStream out = new ByteArrayOutputStream()){
			
			Sheet sheet = workbook.createSheet("Reporte");
			
			Set<String> uniqueMonths = vista.stream()
                    .map(VistaKgMesDTO::getAnoMes)
                    .collect(Collectors.toSet());
			
			List<YearMonth> sortedMonths = uniqueMonths.stream()
                    .map(ym -> YearMonth.parse(ym, DateTimeFormatter.ofPattern("yyyy-MM")))
                    .sorted()
                    .collect(Collectors.toList());
			
			Row headerRow = sheet.createRow(0);
            String[] staticHeaders = {"op", "proyecto", "total_kg"};
            for (int i = 0; i < staticHeaders.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(staticHeaders[i]);
            }
            
            int monthIndex = staticHeaders.length;
            Map<YearMonth, Integer> monthColumnIndex = new HashMap<>();
            for (YearMonth month : sortedMonths) {
                String monthName = month.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "-" + month.getYear() % 100;
                Cell cell = headerRow.createCell(monthIndex);
                cell.setCellValue(monthName);
                monthColumnIndex.put(month, monthIndex++);
            }
            
            Cell cumplidoCell = headerRow.createCell(monthIndex);
            cumplidoCell.setCellValue("cumplido");
            
            Map<String, List<VistaKgMesDTO>> groupedData = vista.stream().collect(Collectors.groupingBy(
                    d -> d.getOp() + "-" + d.getCentrOperaciones()
            ));
            
            int rowIdx = 1;
            for (Map.Entry<String, List<VistaKgMesDTO>> entry : groupedData.entrySet()) {
                List<VistaKgMesDTO> groupedList = entry.getValue();
                Row row = sheet.createRow(rowIdx++);
                VistaKgMesDTO first = groupedList.get(0);

                row.createCell(0).setCellValue(first.getOp());
                row.createCell(1).setCellValue(first.getCentrOperaciones());
                row.createCell(2).setCellValue(first.getKgSol());

                double totalCumplido = 0;
                for (VistaKgMesDTO record : groupedList) {
                    YearMonth recordMonth = YearMonth.parse(record.getAnoMes(), DateTimeFormatter.ofPattern("yyyy-MM"));
                    Integer columnIndex = monthColumnIndex.get(recordMonth);
                    if (columnIndex != null) {
                        row.createCell(columnIndex).setCellValue(record.getKgMes());
                    }
                    totalCumplido += record.getKgMes();
                }

                row.createCell(monthIndex).setCellValue(totalCumplido);
            }
            
            for (int i = 0; i < monthIndex + 1; i++) {
                sheet.autoSizeColumn(i);
            }
            
            workbook.write(out);
	        return new ByteArrayInputStream(out.toByteArray());
			
		} catch (IOException  e) {
			throw new RuntimeException("Failed to generate Excel report", e);
		}
	}

}
