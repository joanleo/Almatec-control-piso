package com.almatec.controlpiso.utils.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.VistaKgMesDTO;
import com.almatec.controlpiso.integrapps.dtos.VistaKgPorCtDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasReportadas;


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

	public ByteArrayInputStream exportToExcelSabana(List<VistaPiezasReportadas> data) throws IOException {
		try (Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Resumen OP - CT - Kg ");
			Drawing<?> drawing = sheet.createDrawingPatriarch();
			CreationHelper factory = workbook.getCreationHelper();
			
			class CommentHelper {
	            void addNoteToCell(Cell cell, String noteText, Drawing<?> drawing, CreationHelper factory) {
	                // Crear un nuevo anchor para cada comentario
	                ClientAnchor anchor = factory.createClientAnchor();
	                
	                // Configurar la posición del comentario
	                anchor.setCol1(cell.getColumnIndex());
	                anchor.setCol2(cell.getColumnIndex() + 5);
	                anchor.setRow1(cell.getRowIndex());
	                anchor.setRow2(cell.getRowIndex() + 4);
	                
	                // Crear y configurar el comentario
	                Comment comment = drawing.createCellComment(anchor);
	                RichTextString str = factory.createRichTextString(noteText);
	                comment.setString(str);
	                
	                // Asignar el comentario a la celda
	                cell.setCellComment(comment);
	            }
	        }
	        
	        CommentHelper commentHelper = new CommentHelper();
            
            // Create header styles
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            
            DataFormat dataFormat = workbook.createDataFormat();
            CellStyle decimalStyle = workbook.createCellStyle();
            decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
            
            CellStyle intStyle = workbook.createCellStyle();
            intStyle.setDataFormat(dataFormat.getFormat("#,##0"));
            
            Map<String, CellStyle> workCenterStyles = new HashMap<>();
            
            Map<String, CellStyle> workCenterNumericStyles = new HashMap<>();
            
            // Define colors for each workCenter
            Map<String, IndexedColors> workCenterColors = new HashMap<>();
            workCenterColors.put("FORMADORA OMEGAS", IndexedColors.LIGHT_GREEN);
            workCenterColors.put("FORMADORA VIGAS", IndexedColors.LIGHT_BLUE);
            workCenterColors.put("FORMADORA PERFIL C", IndexedColors.LIGHT_ORANGE);
            workCenterColors.put("FORMADORA COMBINADA PERLINES", IndexedColors.LIGHT_YELLOW);
            workCenterColors.put("FORMADORA  DE PERLINES  C-Z", IndexedColors.LIGHT_TURQUOISE);
            workCenterColors.put("TROQUELADORA", IndexedColors.ROSE);
            workCenterColors.put("SOLDADURA (KG)", IndexedColors.TAN);
            workCenterColors.put("LINEA PINTURA ESTACIONARIA", IndexedColors.LIGHT_CORNFLOWER_BLUE);
            
            for (Map.Entry<String, IndexedColors> entry : workCenterColors.entrySet()) {
                CellStyle style = workbook.createCellStyle();
                style.setFillForegroundColor(entry.getValue().getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setBorderBottom(BorderStyle.THIN);
                style.setBorderTop(BorderStyle.THIN);
                style.setBorderLeft(BorderStyle.THIN);
                style.setBorderRight(BorderStyle.THIN);
                workCenterStyles.put(entry.getKey(), style);
                
                CellStyle numericStyleWithColor = workbook.createCellStyle();
                numericStyleWithColor.cloneStyleFrom(style);
                numericStyleWithColor.setDataFormat(dataFormat.getFormat("#,##0.00"));
                workCenterNumericStyles.put(entry.getKey(), numericStyleWithColor);
            }
            
            // Create headers
            Row headerRow1 = sheet.createRow(0);
            Row headerRow2 = sheet.createRow(1);
            
            // First row headers
            createCell(headerRow2, 0, "proyecto (CO y nombre proyecto)", headerStyle);
            createCell(headerRow2, 1, "# OP", headerStyle);
            createCell(headerRow2, 2, "Item", headerStyle);
            createCell(headerRow2, 3, "Marca", headerStyle);
            createCell(headerRow2, 4, "Descripción", headerStyle);
            createCell(headerRow2, 5, "Peso Unit", headerStyle);
            createCell(headerRow2, 6, "Cant Req", headerStyle);
            
            // Work centers columns
            String[] workCenters = {
            	"FORMADORA OMEGAS", "FORMADORA VIGAS",  "FORMADORA PERFIL C", 
                "FORMADORA COMBINADA PERLINES", "FORMADORA  DE PERLINES  C-Z", "TROQUELADORA",
                "SOLDADURA (KG)", "LINEA PINTURA ESTACIONARIA"
            };
            
            int colIndex = 7;
            for (String center : workCenters) {
                Cell cell = headerRow1.createCell(colIndex);
                cell.setCellValue(center);
                // Apply the corresponding style
                CellStyle centerStyle = workCenterStyles.get(center);
                cell.setCellStyle(centerStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, colIndex, colIndex + 1));
                
                // Sub-headers with the same color
                createCell(headerRow2, colIndex, "Cant fab", headerStyle);
                createCell(headerRow2, colIndex + 1, "Cant Pend", headerStyle);
                
                colIndex += 2;
            }
            
            int rowNum = 2;
            Map<String, Map<String, RoutingInfo>> routingMap = processRoutingData(data);
            for (Map.Entry<String, Map<String, RoutingInfo>> entry : routingMap.entrySet()) {
                
            	String[] opParts = entry.getKey().split("-");
                String idOpIntegrapps = opParts[0];
                String itemOpId = opParts[1];
                //String itemOrMaterialId = opParts[2];
                
                VistaPiezasReportadas firstRecord = data.stream()
                		.filter(d -> {
                			String idOpIntegraApps = String.valueOf(d.getIdOpIntegrapps()).trim();
                            String currentItemOpId = String.valueOf(d.getItemOpId()).trim();
                            /*boolean esMaterial = d.getCantReportadaMaterial() != null && d.getCantReportadaMaterial() > 0;
                            String currentId = esMaterial ? 
                                (d.getMaterialId() != null ? String.valueOf(d.getMaterialId()).trim() : "") :
                                String.valueOf(d.getItemId()).trim();*/

                            return idOpIntegraApps.equals(idOpIntegrapps) &&
                                   currentItemOpId.equals(itemOpId) ;
                        })
                        .findFirst()
                        .orElse(null);
                
                
                //System.out.println(firstRecord);
                if (firstRecord != null) {
                	Row row = sheet.createRow(rowNum++);
                    boolean esMaterial = firstRecord.getCantReportadaMaterial() != null && 
                                       firstRecord.getCantReportadaMaterial() > 0;
                    
                    String proyecto = firstRecord.getUn().split("-")[1];
                    row.createCell(0).setCellValue(proyecto);
                    row.createCell(1).setCellValue(firstRecord.getOp());
                    if (esMaterial) {
                    	//System.out.println(firstRecord);
                        row.createCell(2).setCellValue(firstRecord.getItemOpId() + "-" + firstRecord.getMaterialId());
                    } else {
                        row.createCell(2).setCellValue(firstRecord.getItemOpId() + "-" + firstRecord.getItemId());
                    }
                    row.createCell(3).setCellValue(firstRecord.getMarca());
                    row.createCell(4).setCellValue(firstRecord.getItemDescripcion());
                    
                    Cell pesoCell = row.createCell(5);
                    pesoCell.setCellValue(firstRecord.getItemPeso().doubleValue());
                    pesoCell.setCellStyle(decimalStyle);
                    
                    Cell cantReqCell = row.createCell(6);
                    cantReqCell.setCellValue(firstRecord.getCantReq().intValue());
                    cantReqCell.setCellStyle(intStyle);
                    
                    
                    // Procesar cantidades por centro de trabajo
                    Map<String, RoutingInfo> centerData = entry.getValue();
                    int centerColIndex = 7;
                    for (String center : workCenters) {
                        RoutingInfo info = centerData.getOrDefault(center, new RoutingInfo());
                        Cell cantFabCell = row.createCell(centerColIndex++);
                        Cell cantPendCell = row.createCell(centerColIndex++);
                        
                        cantFabCell.setCellValue(info.getCantidadReportada().doubleValue());
                        cantPendCell.setCellValue(info.getCantidadPendiente().doubleValue());
                        
                        // Apply the corresponding style
                        CellStyle centerNumericStyle = workCenterNumericStyles.get(center);
                        cantFabCell.setCellStyle(centerNumericStyle);
                        cantPendCell.setCellStyle(centerNumericStyle);
                        
                        if (info.getCentroTrabajoId() != null && 
                            info.getCentroTrabajoId() <= 12) {
                            
                            String noteText = String.format("Material: %s\nPeso: %.2f kg", 
                            		info.getDescripcion(),
                            		info.getPeso().doubleValue());
                                
                            // Agregar comentario a ambas celdas
                            commentHelper.addNoteToCell(cantFabCell, noteText, drawing, factory);
                            commentHelper.addNoteToCell(cantPendCell, noteText, drawing, factory);
                        }
                    }
                }
            } 
            
            for (int i = 0; i < headerRow1.getLastCellNum(); i++) {
            	sheet.autoSizeColumn(i);
            }
            
            // Write to byte array
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            	workbook.write(outputStream);
            	return new ByteArrayInputStream(outputStream.toByteArray());
            }
		}

	}
	
	private Map<String, Map<String, RoutingInfo>> processRoutingData(List<VistaPiezasReportadas> data) {
		Map<String, Map<String, RoutingInfo>> routingMap = new HashMap<>();
		for (VistaPiezasReportadas registro : data) {
			boolean esMaterial = registro.getCantReportadaMaterial() != null && registro.getCantReportadaMaterial() > 0;
	        
	        // Construimos la clave única para el item/material
	        String key = String.format("%s-%s",
	            registro.getIdOpIntegrapps(),
	            registro.getItemOpId()
	           // esMaterial ? registro.getMaterialId() : registro.getItemId()
	        );
	        if(registro.getIdOpIntegrapps() == 2 && registro.getItemId() == 8) {
	        	System.out.println("\n-------- Procesando registro --------");
	        	System.out.println("OP-Item: " + key);
	        	System.out.println("Es Material: " + esMaterial);	        	
	        }
	        
	        routingMap.computeIfAbsent(key, k -> new HashMap<>());
	        Map<String, RoutingInfo> centerMap = routingMap.get(key);
	        
	        String nombreCentro = esMaterial ? 
	            registro.getMaterialCentroTNombre() : 
	            registro.getItemCentroTNombre();
	        if(registro.getIdOpIntegrapps() == 2 && registro.getItemId() == 2) {
	        	System.out.println("Centro de Trabajo: " + nombreCentro);
	        	System.out.println("ID Centro Material: " + registro.getMaterialCentroTId());
	        	System.out.println("ID Centro Item: " + registro.getItemCentroTId());	        	
	        }
	        
	        //RoutingInfo info = centerMap.computeIfAbsent(nombreCentro, k -> new RoutingInfo());
	        
	        if (registro.getMaterialCentroTId() != null && registro.getMaterialCentroTId() <= 12) {
	        	String nombreCentroMaterial = registro.getMaterialCentroTNombre();
	        	if(registro.getIdOpIntegrapps() == 2 && registro.getItemId() == 8) {
	        		System.out.println("Procesando Material:");
	        		System.out.println("Material Peso: " + registro.getMaterialPeso());
	        		System.out.println("Cantidad Reportada Material: " + registro.getCantReportadaMaterial());
	        		System.out.println("Cantidad Pendiente Material: " + registro.getCantPendienteMaterial());	        		
	        	}
	            
	            BigDecimal cantidadReportada = registro.getMaterialPeso()
	                .multiply(BigDecimal.valueOf(registro.getCantReportadaMaterial()));
	            BigDecimal cantidadPendiente = registro.getMaterialPeso()
	                .multiply(registro.getCantPendienteMaterial());
	            RoutingInfo infoMaterial = centerMap.computeIfAbsent(nombreCentroMaterial, k -> new RoutingInfo());    
	            infoMaterial.setCantidadReportada(cantidadReportada);
	            infoMaterial.setCantidadPendiente(cantidadPendiente);
	            infoMaterial.setDescripcion(registro.getMaterialDescripcion());
	            infoMaterial.setPeso(registro.getMaterialPeso());
	            infoMaterial.setCentroTrabajoId(registro.getMaterialCentroTId());
	            if(registro.getIdOpIntegrapps() == 2 && registro.getItemId() == 8) {
	            	System.out.println("Kilos Reportados: " + cantidadReportada);
	            	System.out.println("Kilos Pendientes: " + cantidadPendiente);
	            }
	            	
            }
	        
	        // Agregar esta nueva condición para items en centros < 12
	        if (registro.getItemCentroTId() != null && registro.getItemCentroTId() <= 12) {
	            String nombreCentroItem = registro.getItemCentroTNombre();
	            if(registro.getIdOpIntegrapps() == 2 && registro.getItemId() == 8) {
	                System.out.println("Procesando Item en centro < 12:");
	                System.out.println("Item Peso: " + registro.getItemPeso());
	                System.out.println("Cantidad Reportada Item: " + registro.getCantReportadaPieza());
	                System.out.println("Cantidad Pendiente Item: " + registro.getCantPendientePieza());           
	            }
	            
	            BigDecimal cantidadReportada = registro.getItemPeso()
	                .multiply(BigDecimal.valueOf(registro.getCantReportadaPieza()));
	            BigDecimal cantidadPendiente = registro.getItemPeso()
	                .multiply(registro.getCantPendientePieza());
	                    
	            RoutingInfo infoItem = centerMap.computeIfAbsent(nombreCentroItem, k -> new RoutingInfo());
	            infoItem.setCantidadReportada(cantidadReportada);
	            infoItem.setCantidadPendiente(cantidadPendiente);
	            infoItem.setDescripcion(registro.getItemDescripcion());
	            infoItem.setPeso(registro.getItemPeso());
	            infoItem.setCentroTrabajoId(registro.getItemCentroTId());
	            if(registro.getIdOpIntegrapps() == 2 && registro.getItemId() == 8) {
	                System.out.println("Kilos Reportados: " + cantidadReportada);
	                System.out.println("Kilos Pendientes: " + cantidadPendiente);             
	            }
	        }
	        
	        if (registro.getItemCentroTId() != null && registro.getItemCentroTId() >= 13) {
	        	String nombreCentroItem = registro.getItemCentroTNombre();
	        	if(registro.getIdOpIntegrapps() == 2 && registro.getItemId() == 8) {
	        		System.out.println("Procesando Item:");
	        		System.out.println("Item Peso: " + registro.getItemPeso());
	        		System.out.println("Cantidad Reportada Pieza: " + registro.getCantReportadaPieza());
	        		System.out.println("Cantidad Pendiente Pieza: " + registro.getCantPendientePieza());	        		
	        	}
	            
	            BigDecimal cantidadReportada = registro.getItemPeso()
	                .multiply(BigDecimal.valueOf(registro.getCantReportadaPieza()));
	            BigDecimal cantidadPendiente = registro.getItemPeso()
	                .multiply(registro.getCantPendientePieza());
	                
	            RoutingInfo infoItem = centerMap.computeIfAbsent(nombreCentroItem, k -> new RoutingInfo());
	            infoItem.setCantidadReportada(cantidadReportada);
	            infoItem.setCantidadPendiente(cantidadPendiente);
	            infoItem.setDescripcion(registro.getItemDescripcion());
	            infoItem.setPeso(registro.getItemPeso());
	            infoItem.setCentroTrabajoId(registro.getItemCentroTId());
	            if(registro.getIdOpIntegrapps() == 2 && registro.getItemId() == 8) {
	            	System.out.println("Kilos Reportados: " + cantidadReportada);
	            	System.out.println("Kilos Pendientes: " + cantidadPendiente);	            	
	            }
	        }

        }
        return routingMap;
	}

	private void createCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

	
	private static class RoutingInfo {
	    private BigDecimal cantidadReportada = BigDecimal.ZERO;
	    private BigDecimal cantidadPendiente = BigDecimal.ZERO;
	    private String descripcion;
	    private BigDecimal peso = BigDecimal.ZERO;
	    private Integer centroTrabajoId;

	    public RoutingInfo() {
	    }

	    public BigDecimal getCantidadReportada() {
	        return cantidadReportada;
	    }

	    public BigDecimal getCantidadPendiente() {
	        return cantidadPendiente;
	    }

	    public void setCantidadReportada(BigDecimal bigDecimal) {
	        this.cantidadReportada = bigDecimal;
	    }

	    public void setCantidadPendiente(BigDecimal bigDecimal) {
	        this.cantidadPendiente = bigDecimal;
	    }

	    public String getDescripcion() {
			return descripcion;
		}

		public BigDecimal getPeso() {
			return peso;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public void setPeso(BigDecimal peso) {
			this.peso = peso;
		}

		public Integer getCentroTrabajoId() {
			return centroTrabajoId;
		}

		public void setCentroTrabajoId(Integer centroTrabajoId) {
			this.centroTrabajoId = centroTrabajoId;
		}

		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        RoutingInfo that = (RoutingInfo) o;
	        return Objects.equals(cantidadReportada, that.cantidadReportada) 
	                && Objects.equals(cantidadPendiente, that.cantidadPendiente)
	                && Objects.equals(descripcion, that.descripcion)
	                && Objects.equals(peso, that.peso)
	                && Objects.equals(centroTrabajoId, that.centroTrabajoId);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(cantidadReportada, cantidadPendiente, descripcion, peso, centroTrabajoId);
	    }

		@Override
		public String toString() {
			return "RoutingInfo [cantidadReportada=" + cantidadReportada + ", cantidadPendiente=" + cantidadPendiente
					+ ", descripcion=" + descripcion + ", peso=" + peso + ", centroTrabajoId=" + centroTrabajoId + "]";
		}

	}
		
}
