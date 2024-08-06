package com.almatec.controlpiso.utils;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.services.CentroTrabajoService;
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
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class CentrosTrabajoPDFService {
	
	@Autowired
	private CentroTrabajoService centroTrabajoService;

	public void generarBarCodeCentrosTrabajo(HttpServletResponse response) throws DocumentException, IOException {
		setResponseHeaders(response);
		List<CentroTrabajo> centrosTrabajo = centroTrabajoService.buscarCentrosTrabajo(22);
		
		Document document = new Document(PageSize.A4);
        PdfWriter writer =PdfWriter.getInstance(document, response.getOutputStream());
        
        document.open();
        
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.WHITE);
        Font tableContentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);

        Paragraph title = new Paragraph("Listado de Centros de trabajo y Códigos de Barras", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3f});
        table.setSpacingBefore(10);

        writeTableHeader(table, tableHeaderFont);
        writeTableData(writer, table, centrosTrabajo, tableContentFont);

        document.add(table);
        document.close();

        document.open();
		
	}
	
	private void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=centros_trabajo_bar_code_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
    }
	
	private void writeTableHeader(PdfPTable table, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(0, 121, 54));
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Nombre", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Código de Barras", font));
        table.addCell(cell);

    }
	
	private void writeTableData(PdfWriter writer, PdfPTable table, List<CentroTrabajo> centrosTrabajo, Font font) throws DocumentException {
        for (CentroTrabajo centroTrabajo : centrosTrabajo) {
        	PdfContentByte cb = writer.getDirectContent();
        	
        	PdfPCell nombreOperCell = new PdfPCell();
        	nombreOperCell.setVerticalAlignment(Element.ALIGN_CENTER);
        	nombreOperCell.setPadding(10);
        	nombreOperCell.setPhrase(new Phrase( centroTrabajo.getNombre(), font));
            table.addCell(nombreOperCell);

            PdfPCell barcodeCell = new PdfPCell();
            Barcode128 barcode = new Barcode128();
            barcode.setCode(centroTrabajo.getCodigoBarraHum());
            barcode.setCodeType(Barcode.CODE128);
            Image barcodeImage = barcode.createImageWithBarcode(cb, null, null);
            
            barcodeImage.scalePercent(150);
            barcodeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            barcodeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            barcodeCell.setPadding(10);
            
            barcodeImage.setAlignment(Element.ALIGN_CENTER);
            
            barcodeCell.addElement(barcodeImage);
            table.addCell(barcodeCell);
        }
    }

}
