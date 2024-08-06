package com.almatec.controlpiso.calidad.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.calidad.dtos.ReporteCalidadDTO;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfExportService {
	
	private static final Color DARK_GREEN = new Color(0, 121, 54);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);

	public ByteArrayOutputStream generarPdfFormulario(ReporteCalidadDTO formulario) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        addLogo(writer, document);
        addContent(document, formulario);
        
        createFooter(writer, document);
        
        document.close();
        return outputStream;
    }
	
	public void addLogo(PdfWriter writer, Document document) {
		String path = "static/imgs/logo-almatec.png";
		try {
			Image logoImage = Image.getInstance(getClass().getClassLoader().getResource(path));
			float originalWidth = logoImage.getWidth();
			float originalHeight = logoImage.getHeight();
			//System.out.println("Dimensiones originales de la imagen: " + originalWidth + " x " + originalHeight + " puntos.");
			float width = originalWidth - 56;
			float height = originalHeight - 25;
	        float xPosition = document.left();
	        float yPosition = document.top() - 60;
	        logoImage.scaleToFit(width, height);
	        logoImage.setAbsolutePosition(xPosition, yPosition);
	        
	        PdfContentByte cb = writer.getDirectContent();
	        //logoImage.setAbsolutePosition(xPosition, yPosition - document.topMargin());
	        cb.addImage(logoImage);       
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		} 
    }
	
	private void addContent(Document document, ReporteCalidadDTO formulario) throws DocumentException {
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);        
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        headerFont.setColor(Color.WHITE);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 8);
        
        Paragraph numDoc = new Paragraph(formulario.getPrefijo() + "-" + formulario.getId(), titleFont);
        numDoc.setAlignment(Element.ALIGN_RIGHT);
        document.add(numDoc);
        
        document.add(Chunk.NEWLINE);
        titleFont.setSize(18);
        Paragraph title = new Paragraph("REPORTE CALIDAD " + formulario.getCentroTrabajo(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingBefore(20);
        document.add(title);
        document.add(Chunk.NEWLINE);

        PdfPTable mainTable = new PdfPTable(4);
        mainTable.setWidthPercentage(100);
        
        addStyledRow(mainTable, "FECHA Y HORA:", formulario.getFechaDoc(), "OP #:", formulario.getNumOp(), headerFont, normalFont);
        addStyledRow(mainTable, "PROYECTO:", formulario.getProyecto(), "PEDIDO/ZONA:", formulario.getZona(), headerFont, normalFont);
        addStyledRow(mainTable, "NOMBRE ITEM:", formulario.getDescripcionItem(), "MARCA:", formulario.getMarca(), headerFont, normalFont);
        addStyledRow(mainTable, "CANTIDAD:", formulario.getCantSol(), "LOTE:", formulario.getLote(), headerFont, normalFont);

        document.add(mainTable);
        document.add(Chunk.NEWLINE);

        // Parámetros
        headerFont.setColor(Color.BLACK);
        headerFont.setSize(14);
        Paragraph parametersTitle = new Paragraph("PARÁMETROS", headerFont);
        parametersTitle.setAlignment(Element.ALIGN_LEFT);
        document.add(parametersTitle);

        PdfPTable parametersTable = new PdfPTable(4);
        parametersTable.setWidthPercentage(100);
        parametersTable.setSpacingBefore(10);
        headerFont.setColor(Color.WHITE);
        headerFont.setSize(10);
        addStyledRow(parametersTable, "ANCHO [mm]:", formulario.getAncho(), "ALTURA [mm]:", formulario.getAltura(), headerFont, normalFont);
        addStyledRow(parametersTable, "LONGITUD [mm]:", formulario.getLongitud(), "RAL:", formulario.getRal(), headerFont, normalFont);

        document.add(parametersTable);

        PdfPTable booleanTable = new PdfPTable(4);
        booleanTable.setWidthPercentage(100);

        addBooleanRow(booleanTable, "PESTAÑA:", formulario.getPestana(), "ALETA:", formulario.getAleta(), headerFont, normalFont);
        addBooleanRow(booleanTable, "PERF. BORDE:", formulario.getPerfBorde(), "PERFORACIONES:", formulario.getPerforaciones(), headerFont, normalFont);
        addBooleanRow(booleanTable, "TROQUELADO:", formulario.getTroquelado(), "CUADRATURA:", formulario.getCuadratura(), headerFont, normalFont);
        addBooleanRow(booleanTable, "CORONA:", formulario.getCorona(), "FLECHA H:", formulario.getFlechaH(), headerFont, normalFont);
        addBooleanRow(booleanTable, "FLECHA V:", formulario.getFlechaV(), "CORTE:", formulario.getCorte(), headerFont, normalFont);
        addBooleanRow(booleanTable, "PUNZONADO:", formulario.getPunzonado(), "GRANALLADO:", formulario.getGranallado(), headerFont, normalFont);
        addBooleanRow(booleanTable, "PASA PRUEBA CUADRICULA:", formulario.getCorte(), "", null, headerFont, normalFont);

        document.add(booleanTable);
        document.add(Chunk.NEWLINE);
        
        headerFont.setColor(Color.BLACK);
        headerFont.setSize(14);
        Paragraph espesoresTitle = new Paragraph("REGISTRO ESPESORES", headerFont);
        espesoresTitle.setAlignment(Element.ALIGN_LEFT);
        document.add(espesoresTitle);
        
        PdfPTable espesoresTable = new PdfPTable(4);
        espesoresTable.setWidthPercentage(100);
        espesoresTable.setSpacingBefore(10);
        headerFont.setColor(Color.WHITE);
        headerFont.setSize(10);
        addStyledRow(espesoresTable, "1 Media [μm]:", formulario.getMedia1(), "2 Media [μm]:", formulario.getMedia2(), headerFont, normalFont);
        addStyledRow(espesoresTable, "3 Media [μm]:", formulario.getMedia3(), "4 Media [μm]:", formulario.getMedia4(), headerFont, normalFont);
        addStyledRow(espesoresTable, "5 Media [μm]:", formulario.getMedia5(), "6 Media [μm]:", formulario.getMedia6(), headerFont, normalFont);
        addStyledRow(espesoresTable, "7 Media [μm]:", formulario.getMedia7(), "8 Media [μm]:", formulario.getMedia8(), headerFont, normalFont);
        addStyledRow(espesoresTable, "9 Media [μm]:", formulario.getMedia9(), "10 Media [μm]:", formulario.getMedia10(), headerFont, normalFont);
        addStyledRow(espesoresTable, "11 Media [μm]:", formulario.getMedia11(), "12 Media [μm]:", formulario.getMedia12(), headerFont, normalFont);
        
        document.add(espesoresTable);
    }
	
	private void addStyledRow(PdfPTable table, String label1, Object value1, String label2, Object value2, Font labelFont, Font valueFont) {
        addStyledCell(table, label1, labelFont, true);
        addStyledCell(table, value1 != null ? value1.toString() : "", valueFont, false);
        addStyledCell(table, label2, labelFont, true);
        addStyledCell(table, value2 != null ? value2.toString() : "", valueFont, false);
    }
	
	private void addStyledCell(PdfPTable table, String text, Font font, boolean isHeader) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        if (isHeader) {
            cell.setBackgroundColor(DARK_GREEN);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        } else {
            cell.setBackgroundColor(LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        }
        table.addCell(cell);
    }
	
	private void addBooleanRow(PdfPTable table, String label1, Boolean value1, String label2, Boolean value2, Font labelFont, Font valueFont) {
        addStyledCell(table, label1, labelFont, true);
        addStyledCell(table, formatBoolean(value1), valueFont, false);
        addStyledCell(table, label2, labelFont, true);
        addStyledCell(table, formatBoolean(value2), valueFont, false);
    }
	
	private String formatBoolean(Boolean value) {
        return value != null ? (value ? "SI" : "NO") : "";
    }
	
	private void createFooter(PdfWriter writer, Document document) {
    	
    	int totalPages = writer.getPageNumber();
    	
    	PdfPTable footer = new PdfPTable(3);
    	footer.setTotalWidth(document.right() - document.left());
    	footer.setLockedWidth(true);
    	footer.getDefaultCell().setFixedHeight(20);
    	footer.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    	footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
    	footer.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        try {
        	
        	Font poweredByFont = new Font();
	        poweredByFont.setSize(7); 
	        
	        PdfPCell poweredByCell = new PdfPCell(new Phrase("Powered by: www.integrapps.com", poweredByFont));
	        poweredByCell.setBorder(Rectangle.NO_BORDER);
	        poweredByCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        footer.addCell(poweredByCell);

	        String pageNumber = "Página " + writer.getPageNumber() + " de " + totalPages;
	        PdfPCell pageNumberCell = new PdfPCell(new Phrase(pageNumber, poweredByFont));
	        pageNumberCell.setBorder(Rectangle.NO_BORDER);
	        pageNumberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        footer.addCell(pageNumberCell); 
        	
	        DateFormat dateFormatterCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String creationDate = dateFormatterCreacion.format(new Date());
	        PdfPCell dateCell = new PdfPCell(new Phrase(creationDate, poweredByFont));
	        dateCell.setBorder(Rectangle.NO_BORDER);
	        dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        footer.addCell(dateCell);
            
	        footer.writeSelectedRows(0, -1, document.left(), document.bottom() - 5, writer.getDirectContent());
                        
        } catch (DocumentException e){
		e.printStackTrace();
		}
    }
	
	private void addBooleanRowIfNotNull(PdfPTable table, String label, Boolean value, Font labelFont, Font valueFont) {
        if (value != null) {
            PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
            labelCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(labelCell);

            String booleanValue = value ? "SI" : "NO";
            PdfPCell valueCell = new PdfPCell(new Phrase(booleanValue, valueFont));
            valueCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(valueCell);
        }
    }
	
	private void addTableRowIfNotNull(PdfPTable table, String label, Object value, Font labelFont, Font valueFont) {
        if (value != null) {
            PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
            labelCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(labelCell);

            PdfPCell valueCell = new PdfPCell(new Phrase(value.toString(), valueFont));
            valueCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(valueCell);
        }
    }
	
	private void addTableRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(valueCell);
    }
}
