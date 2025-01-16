package com.almatec.controlpiso.utils;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCtDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


import java.util.*;
import java.util.stream.Collectors;

public class ExportOpCentroTrabajoToPdf extends ExportToPdf {
    
    private final List<OpCentroTrabajoDTO> opsCt;
    private final List<Integer> opsSeleccionadas;
    private final CentroTrabajo centroTrabajo;
    private static final int[] CENTROS_TRABAJO_PRIORIDAD = {3, 4, 5, 6, 7, 8, 9, 17};
    private static final String LOGO_PATH = "static/imgs/logo-almatec.png";

    public ExportOpCentroTrabajoToPdf(Set<OpCentroTrabajoDTO> opsCt2, List<Integer> opsSeleccionadas, CentroTrabajo centroTrabajo, Rectangle pageSize) {
        super(pageSize);
        this.opsCt = new ArrayList<>(opsCt2);
        this.opsSeleccionadas = opsSeleccionadas;
        this.centroTrabajo = centroTrabajo;
    }
    
    private void tableHeader(PdfPTable table, List<String> columnsName) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(0, 121, 54));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, Color.WHITE);
        
        for(String name : columnsName) {
            cell.setPhrase(new Phrase(name, font));
            table.addCell(cell);            
        }
    }
    
    private void tableData(PdfPTable table, List<RowItemPdf> rows, BigDecimal[] totalPesoTtl, boolean priority) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);
        
        BigDecimal total = BigDecimal.ZERO;
        int count = 1;
        for(RowItemPdf row : rows) {
            addCell(table, String.valueOf(count), Element.ALIGN_CENTER, font);
            addCell(table, row.getOp(), Element.ALIGN_CENTER, font);
            addCell(table, row.getProyecto(), Element.ALIGN_LEFT, font);
            addCell(table, row.getZona(), Element.ALIGN_CENTER, font);
            addCell(table, row.getMarca(), Element.ALIGN_CENTER, font);
            addCell(table, row.getDescripcion(), Element.ALIGN_LEFT, font);
            addCell(table, row.getCant().toString(), Element.ALIGN_CENTER, font);
            
            String longitud = Optional.ofNullable(row.getLongitud())
                .map(l -> l.stripTrailingZeros().toPlainString())
                .orElse("");
            addCell(table, longitud, Element.ALIGN_CENTER, font);
            
            addCell(table, row.getPeso().setScale(2, RoundingMode.HALF_UP).toString(), Element.ALIGN_CENTER, font);
            
            BigDecimal pesoTotal = row.getPeso().multiply(BigDecimal.valueOf(row.getCant()));
            total = total.add(pesoTotal);
            addCell(table, pesoTotal.setScale(2, RoundingMode.HALF_UP).toString(), Element.ALIGN_CENTER, font);
            
            addCell(table, row.getColor(), Element.ALIGN_CENTER, font);
            
            if(priority) {
                String prioridad = Optional.ofNullable(row.getPrioridad())
                    .map(Object::toString)
                    .orElse("");
                addCell(table, prioridad, Element.ALIGN_CENTER, font);
            }
            count++;
        }
        
        totalPesoTtl[0] = total;
    }
    
    private void addCell(PdfPTable table, String content, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
    
    private List<RowItemPdf> obtenerFilas() {
        Map<String, RowItemPdf> uniqueRows = new HashMap<>();

        opsCt.stream()
            .filter(op -> opsSeleccionadas.contains(op.getIdOp()))
            .forEach(op -> obtenerFilasDeOp(op, uniqueRows));

        return uniqueRows.values().stream()
            .sorted(Comparator.comparing(RowItemPdf::getPrioridad, 
                    Comparator.nullsLast(Comparator.naturalOrder())))
            .collect(Collectors.toList());
    }
    
    private void obtenerFilasDeOp(OpCentroTrabajoDTO op, Map<String, RowItemPdf> uniqueRows) {
        for (ItemOpCtDTO item : op.getItems()) {
            if (Objects.equals(item.getItem_centro_t_id(), centroTrabajo.getId())) {
                agregarOActualizarFila(uniqueRows, op, item, item.getItem_desc(), item.getCant_req(), 
                         item.getItem_peso(), item.getLongitud(), item.getItem_id().toString());
            }
            for (ComponenteDTO componente : item.getComponentes()) {
                if (Objects.equals(componente.getMaterial_centro_t_id(), centroTrabajo.getId())) {
                    agregarOActualizarFila(uniqueRows, op, item, componente.getMaterial_desc(), componente.getMaterial_cant(), 
                             componente.getMaterial_peso(), componente.getLongitud(), componente.getMaterial_id().toString());
                }
            }
        }
    }
    
    private void agregarOActualizarFila(Map<String, RowItemPdf> uniqueRows, OpCentroTrabajoDTO op, ItemOpCtDTO item, 
            String descripcion, Integer cantidad, BigDecimal peso, BigDecimal longitud, String ref) {
    	String key = String.format("%s_%s_%s_%s_%s", 
    	        op.getOp(),           // Incluir OP
    	        item.getMarca(),      // Incluir marca
    	        descripcion,          // Descripción actual
    	        cantidad,             // Cantidad actual
    	        ref                   // Referencia del material/item
    	    );
		if (uniqueRows.containsKey(key)) {
			RowItemPdf existingRow = uniqueRows.get(key);
			if (peso != null) {
	            existingRow.setPeso(existingRow.getPeso().add(peso));
	        }
			if (item.getPrioridad() < existingRow.getPrioridad()) {
	            existingRow.setPrioridad(item.getPrioridad());
	        }
		// Actualizar otros campos si es necesario
		} else {
			RowItemPdf newRow = crearRowItemPdf(op, item, descripcion, cantidad, peso, longitud, ref);
			uniqueRows.put(key, newRow);
			}
	}
    
    private RowItemPdf crearRowItemPdf(OpCentroTrabajoDTO op, ItemOpCtDTO item, String descripcion, 
            Integer cantidad, BigDecimal peso, BigDecimal longitud, String ref) {
		RowItemPdf row = new RowItemPdf();
		row.setDescripcion(descripcion != null ? descripcion : "");
		row.setCant(cantidad != null ? cantidad : 0);
		row.setOp(op.getOp() != null ? op.getOp() : "");
		row.setPeso(peso != null ? peso.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO);
		row.setColor(item.getItem_color() != null ? item.getItem_color() : "");
		row.setPrioridad(item.getPrioridad());
		row.setRef(ref != null ? ref : "");
		row.setLongitud(longitud != null ? longitud.setScale(2, RoundingMode.HALF_UP) : null);
		row.setProyecto(op.getProyecto() != null ? op.getProyecto() : "");
		row.setZona(op.getZona() != null ? op.getZona() : "");
		row.setMarca(item.getMarca() != null ? item.getMarca() : "");
		return row;
	}

    @Override
    protected void addContent() throws DocumentException {
        List<RowItemPdf> rows = obtenerFilas();
        List<String> columnsName = new ArrayList<>(Arrays.asList("#", "OP", "PROYECTO", "ZONA", "MARCA", "DESCRIPCION", "CANT", "LONG [ml]", "PESO UN [kg]", "PESO TTL [kg]", "COLOR"));
        
        boolean priority = Arrays.stream(CENTROS_TRABAJO_PRIORIDAD).anyMatch(x -> x == centroTrabajo.getId());
        PdfPTable table = createTable(priority, columnsName);
        
        BigDecimal[] totalPesoTtl = new BigDecimal[1];
        tableData(table, rows, totalPesoTtl, priority);
        document.add(table);
        
        addTotalParagraph(totalPesoTtl[0]);
    }
    
    private PdfPTable createTable(boolean priority, List<String> columnsName) throws DocumentException {
        PdfPTable table;
        if (priority) {
            float[] columnsWidthPriority = {0.4f, 1.0f, 2.4f, 1.1f, 1.1f,2.3f, 0.8f, 0.8f, 0.8f, 1.0f, 1.3f, 1.3f};
            table = new PdfPTable(12);
            columnsName.add("PRIORIDAD");
            table.setWidths(columnsWidthPriority);
        } else {
            float[] columnsWidth = {0.4f, 1.0f, 2.4f, 1.1f, 1.1f,2.3f, 0.8f, 0.8f, 0.8f, 1.0f, 1.3f};            
            table = new PdfPTable(11);
            table.setWidths(columnsWidth);            
        }
        table.setWidthPercentage(100);
        table.setSpacingBefore(40);
        tableHeader(table, columnsName);
        return table;
    }
    
    private void addTotalParagraph(BigDecimal total) throws DocumentException {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        String totalStr = total.stripTrailingZeros().toPlainString();
        Paragraph totalParagraph = new Paragraph("Carga Total: " + totalStr, font);
        totalParagraph.setSpacingBefore(10);
        document.add(totalParagraph);
    }
    
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        try {
            Image logoImage = Image.getInstance(getClass().getClassLoader().getResource(LOGO_PATH));
            float width = logoImage.getWidth() - 56;
            float height = logoImage.getHeight() - 25;
            float xPosition = document.left();
            float yPosition = document.top() - 60;
            logoImage.scaleToFit(width, height);
            logoImage.setAbsolutePosition(xPosition, yPosition);
            
            PdfContentByte cb = writer.getDirectContent();
            cb.addImage(logoImage);
            
            document.add(new Paragraph(" "));
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph(centroTrabajo.getNombre(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(30);
            document.add(title);
           
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        createFooter(writer, document);
    }

    private void createFooter(PdfWriter writer, Document document) {
        PdfPTable footer = new PdfPTable(3);
        footer.setTotalWidth(document.right() - document.left());
        footer.setLockedWidth(true);
        footer.getDefaultCell().setFixedHeight(20);
        footer.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        footer.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        Font footerFont = new Font(Font.HELVETICA, 7);
        
        footer.addCell(createFooterCell("Powered by: www.integrapps.com", Element.ALIGN_LEFT, footerFont));
        
        String pageNumber = String.format("Página %d de %d", writer.getPageNumber(), writer.getPageNumber());
        footer.addCell(createFooterCell(pageNumber, Element.ALIGN_CENTER, footerFont));
        
        DateFormat dateFormatterCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String creationDate = dateFormatterCreacion.format(new Date());
        footer.addCell(createFooterCell(creationDate, Element.ALIGN_RIGHT, footerFont));
        
        try {
            footer.writeSelectedRows(0, -1, document.left(), document.bottom() - 5, writer.getDirectContent());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    
    private PdfPCell createFooterCell(String content, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
}
