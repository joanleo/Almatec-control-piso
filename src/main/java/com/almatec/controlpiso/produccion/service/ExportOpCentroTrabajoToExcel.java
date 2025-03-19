package com.almatec.controlpiso.produccion.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.programacion.dtos.ComponenteDTO;
import com.almatec.controlpiso.programacion.dtos.ItemDTO;
import com.almatec.controlpiso.programacion.dtos.OrdenProduccionResumen;
import com.almatec.controlpiso.utils.RowItemPdf;

import org.apache.poi.ss.util.CellRangeAddress;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

public class ExportOpCentroTrabajoToExcel {
    
    private final List<OrdenProduccionResumen> opsCt;
    private final List<Integer> opsSeleccionadas;
    private final CentroTrabajo centroTrabajo;
    private static final String[] HEADERS = {
        "#", "OP", "PROYECTO", "ZONA", "MARCA", "DESCRIPCION", 
        "CANT SOL", "CANT FAB", "LONG [ml]", "PESO UN [kg]", 
        "PESO TTL [kg]", "COLOR", "PRIORIDAD"
    };

    public ExportOpCentroTrabajoToExcel(Set<OrdenProduccionResumen> opsCt2, 
            List<Integer> opsSeleccionadas, CentroTrabajo centroTrabajo) {
        this.opsCt = new ArrayList<>(opsCt2);
        this.opsSeleccionadas = opsSeleccionadas;
        this.centroTrabajo = centroTrabajo;
    }

    public void export(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(centroTrabajo.getNombre());

        // Crear estilos
        CellStyle headerStyle = createHeaderStyle(workbook);
        CellStyle bodyStyle = createBodyStyle(workbook);
        CellStyle titleStyle = createTitleStyle(workbook);
        CellStyle totalStyle = createTotalStyle(workbook);
        CellStyle dateStyle = createDateStyle(workbook);
        
        // Agregar título
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(centroTrabajo.getNombre());
        titleCell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, HEADERS.length - 1));

        // Crear encabezados
        Row headerRow = sheet.createRow(2);
        for (int col = 0; col < HEADERS.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(HEADERS[col]);
            cell.setCellStyle(headerStyle);
        }

        // Obtener y agregar datos
        List<RowItemPdf> rows = obtenerFilas();
        int rowNum = 3;
        BigDecimal totalPeso = BigDecimal.ZERO;

        for (RowItemPdf row : rows) {
            Row dataRow = sheet.createRow(rowNum++);
            
            // Número de fila
            createCell(dataRow, 0, String.valueOf(rowNum - 3), bodyStyle);
            
            // Datos principales
            createCell(dataRow, 1, row.getOp(), bodyStyle);
            createCell(dataRow, 2, row.getProyecto(), bodyStyle);
            createCell(dataRow, 3, row.getZona(), bodyStyle);
            createCell(dataRow, 4, row.getMarca(), bodyStyle);
            createCell(dataRow, 5, row.getDescripcion(), bodyStyle);
            
            // Cantidades
            createNumericCell(dataRow, 6, row.getCant(), bodyStyle);
            createNumericCell(dataRow, 7, row.getCantFab(), bodyStyle);
            
            // Longitud
            if (row.getLongitud() != null) {
                createNumericCell(dataRow, 8, row.getLongitud().doubleValue(), bodyStyle);
            } else {
                createCell(dataRow, 8, "", bodyStyle);
            }
            
            // Peso unitario
            createNumericCell(dataRow, 9, row.getPeso().setScale(2, RoundingMode.HALF_UP).doubleValue(), bodyStyle);
            
            // Peso total
            BigDecimal pesoTotal = row.getPeso().multiply(BigDecimal.valueOf(row.getCant()));
            totalPeso = totalPeso.add(pesoTotal);
            createNumericCell(dataRow, 10, pesoTotal.setScale(2, RoundingMode.HALF_UP).doubleValue(), bodyStyle);
            
            // Color y prioridad
            createCell(dataRow, 11, row.getColor(), bodyStyle);
            if (row.getPrioridad() != null) {
                createNumericCell(dataRow, 12, row.getPrioridad(), bodyStyle);
            } else {
                createCell(dataRow, 12, "", bodyStyle);
            }
        }

        // Agregar total
        rowNum += 1; // Dejar una fila en blanco
        Row totalRow = sheet.createRow(rowNum);
        Cell totalLabelCell = totalRow.createCell(0);
        totalLabelCell.setCellValue("Carga Total [kg]:");
        totalLabelCell.setCellStyle(totalStyle);
        
        Cell totalValueCell = totalRow.createCell(1);
        totalValueCell.setCellValue(totalPeso.setScale(2, RoundingMode.HALF_UP).doubleValue());
        totalValueCell.setCellStyle(totalStyle);
        
        // Agregar fecha de generación
        rowNum += 2;
        Row dateRow = sheet.createRow(rowNum);
        Cell dateCell = dateRow.createCell(0);
        dateCell.setCellValue(new Date());
        dateCell.setCellStyle(dateStyle);

        // Autoajustar columnas
        for (int i = 0; i < HEADERS.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Congelar panel superior
        sheet.createFreezePane(0, 3);

        // Escribir el archivo
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    private List<RowItemPdf> obtenerFilas() {
        Map<String, RowItemPdf> uniqueRows = new HashMap<>();

        opsCt.stream()
            .filter(op -> opsSeleccionadas.contains(op.getIdOpIntegrapps()))
            .forEach(op -> obtenerFilasDeOp(op, uniqueRows));

        return uniqueRows.values().stream()
            .sorted(
                Comparator.comparing(RowItemPdf::getIdOpIntegrapps, 
                    Comparator.nullsLast(Comparator.reverseOrder()))
                .thenComparing(RowItemPdf::getPrioridad, 
                    Comparator.nullsLast(Comparator.naturalOrder()))
            )
            .collect(Collectors.toList());
    }

    private void obtenerFilasDeOp(OrdenProduccionResumen op, Map<String, RowItemPdf> uniqueRows) {
        for (ItemDTO item : op.getItems()) {
        	System.out.println(item);
            if (Objects.equals(item.getItemCentroTId(), centroTrabajo.getId())) {
                agregarOActualizarFila(uniqueRows, op, item, item.getItemDescripcion(), 
                    item.getCantReq(), item.getItemPeso(), item.getItemLongitud(), 
                    item.getItemId().toString(), item.getCantReportadaPieza());
            }
            
            for (ComponenteDTO componente : item.getComponentes()) {
                if (Objects.equals(componente.getMaterialCentroTId(), centroTrabajo.getId())) {
                    agregarOActualizarFila(uniqueRows, op, item, 
                        componente.getMaterialDescripcion(),
                        componente.getMaterialCant().multiply(item.getCantReq()),
                        componente.getMaterialPeso(), 
                        componente.getMaterialLongitud(),
                        componente.getMaterialId().toString(),
                        componente.getCantReportadaMaterial());
                }
            }
        }
    }

    private void agregarOActualizarFila(Map<String, RowItemPdf> uniqueRows, 
            OrdenProduccionResumen op, ItemDTO item, String descripcion, 
            BigDecimal cant, BigDecimal peso, BigDecimal longitud, 
            String ref, Integer cantFab) {
            
        String key = String.format("%s_%s_%s_%s_%s", 
            op.getOp(), item.getMarca(), descripcion, cant, ref);
            
        if (uniqueRows.containsKey(key)) {
            RowItemPdf existingRow = uniqueRows.get(key);
            if (peso != null) {
                existingRow.setPeso(existingRow.getPeso().add(peso));
            }
            if (item.getPrioridad() < existingRow.getPrioridad()) {
                existingRow.setPrioridad(item.getPrioridad());
            }
        } else {
            RowItemPdf newRow = new RowItemPdf();
            newRow.setDescripcion(descripcion != null ? descripcion : "");
            newRow.setCant(cant != null ? cant.intValue() : 0);
            newRow.setOp(op.getOp() != null ? op.getOp() : "");
            newRow.setPeso(peso != null ? peso.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO);
            newRow.setColor(item.getItemColor() != null ? item.getItemColor() : "");
            newRow.setPrioridad(item.getPrioridad());
            newRow.setRef(ref != null ? ref : "");
            newRow.setLongitud(longitud != null ? longitud.setScale(3, RoundingMode.HALF_UP) : null);
            newRow.setProyecto(op.getUn() != null ? op.getUn() : "");
            newRow.setZona(op.getZona() != null ? op.getZona() : "");
            newRow.setMarca(item.getMarca() != null ? item.getMarca() : "");
            newRow.setCantFab(cantFab != null ? cantFab : 0);
            newRow.setIdOpIntegrapps(op.getIdOpIntegrapps());
            uniqueRows.put(key, newRow);
        }
    }

    private CellStyle createHeaderStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private CellStyle createBodyStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private CellStyle createTitleStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createTotalStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    private CellStyle createDateStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
        return style;
    }

    private void createCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void createNumericCell(Row row, int column, Number value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value.doubleValue());
        cell.setCellStyle(style);
    }
}
