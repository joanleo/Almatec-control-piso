package com.almatec.controlpiso.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.services.OperarioService;
import com.almatec.controlpiso.utils.dto.OperarioBarCodeDTO;
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

import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OperarioPDFService {

    @Autowired
    private OperarioService operarioService;

    public void generarBarCodeOperarios(HttpServletResponse response) throws IOException, DocumentException {
        setResponseHeaders(response);
        List<OperarioBarCodeDTO> operarios = operarioService.obtenerDataBarCodeOperarios();

        Document document = new Document(PageSize.A4);
        PdfWriter writer =PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.WHITE);
        Font tableContentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);


        Paragraph title = new Paragraph("Listado de Operarios y Códigos de Barras", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3f});
        table.setSpacingBefore(10);

        writeTableHeader(table, tableHeaderFont);
        writeTableData(writer, table, operarios, tableContentFont);

        document.add(table);
        document.close();
    }

    private void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=operarios_bar_code_" + currentDateTime + ".pdf";
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

    private void writeTableData(PdfWriter writer, PdfPTable table, List<OperarioBarCodeDTO> operarios, Font font) throws DocumentException {
        for (OperarioBarCodeDTO operario : operarios) {
        	PdfContentByte cb = writer.getDirectContent();
        	
        	PdfPCell nombreOperCell = new PdfPCell();
        	nombreOperCell.setVerticalAlignment(Element.ALIGN_CENTER);
        	nombreOperCell.setPadding(10);
        	nombreOperCell.setPhrase(new Phrase( operario.getNombres(), font));
            table.addCell(nombreOperCell);

            PdfPCell barcodeCell = new PdfPCell();
            Barcode128 barcode = new Barcode128();
            barcode.setCode(operario.getBarcode());
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
