package com.almatec.controlpiso.utils;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class HeaderAndFooter extends PdfPageEventHelper {

	private Image logoImage;
    private String barcodeString;
    private String creationDate;
    private String clientName;
    private String contractualDate;
    private String projectName;
    private String paintScheme;

    public HeaderAndFooter(Image logoImage, String barcodeString, String creationDate, String clientName, 
    		String contractualDate, String projectName, String paintScheme) {
        this.logoImage = logoImage;
        this.barcodeString = barcodeString;
        this.creationDate = creationDate;
        this.clientName = clientName;
        this.contractualDate = contractualDate;
        this.projectName = projectName;
        this.paintScheme = paintScheme;
    }
    
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        // Aquí puedes configurar y agregar el encabezado en cada página
        PdfContentByte cb = writer.getDirectContent();
        
        int pageNumber = writer.getPageNumber();

        
        Font poweredByFont = new Font();
        poweredByFont.setSize(8); // Puedes ajustar el tamaño de fuente si es necesario
        Phrase poweredByPhrase = new Phrase("Powered by: www.integrapps.com", poweredByFont);
        ColumnText.showTextAligned(writer.getDirectContentUnder(),
                Element.ALIGN_LEFT, poweredByPhrase,
                document.left(), document.bottom(), 0);

        // Agregar la numeración centrada
        Phrase pageNumberPhrase = new Phrase("Página " + pageNumber, poweredByFont);        
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_CENTER, pageNumberPhrase,
                (document.left() + document.right()) / 2, document.bottom(), 0);


        float originalWidth = logoImage.getWidth();
		float originalHeight = logoImage.getHeight();
		//System.out.println("Dimensiones originales de la imagen: " + originalWidth + " x " + originalHeight + " puntos.");
		float width = originalWidth - 56;
		float height = originalHeight - 25;
        float xPosition = document.left();
        float yPosition = document.top() - 60;
        logoImage.scaleToFit(width, height);
        logoImage.setAbsolutePosition(xPosition, yPosition);
        document.add(logoImage);

        // Agregar el código de barras
        Barcode128 barcode = new Barcode128();
        barcode.setCode(barcodeString);
        barcode.setCodeType(Barcode128.CODE128);
        Image barcodeImage = barcode.createImageWithBarcode(cb, null, null);
        barcodeImage.setAbsolutePosition(document.right() - 120, document.top() - 60);
        barcodeImage.scalePercent(100);
        document.add(barcodeImage);

        // Agregar el título
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("ORDEN DE PRODUCCION", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingBefore(30);
        document.add(title);

        // Agregar la fecha de creación
        PdfPTable dateTable = new PdfPTable(1);
        dateTable.setWidthPercentage(100);
        Paragraph dateParagraph = new Paragraph(creationDate, poweredByFont);
        PdfPCell dateCell = new PdfPCell(dateParagraph);
        dateCell.setBorder(Rectangle.NO_BORDER);
        dateTable.addCell(dateCell);
        
        float dateXPosition = document.right() - 90;
        float dateYPosition = document.bottom() + 10;
        dateTable.setTotalWidth(100);
        dateTable.writeSelectedRows(0, -1, dateXPosition, dateYPosition, writer.getDirectContent());

        Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
     // // Agregar la información del cliente y la fecha contractual en una tabla para mejor control de la alineación
        PdfPTable clientInfoTable = new PdfPTable(2);
        clientInfoTable.setSpacingBefore(20);
        clientInfoTable.setWidthPercentage(100);
        clientInfoTable.setWidths(new float[]{1, 1}); // Distribución del ancho de las columnas

        // Celda para el nombre del cliente
        PdfPCell clientCell = new PdfPCell(new Phrase("     Cliente:      " + clientName, infoFont));
        clientCell.setBorder(Rectangle.NO_BORDER);
        clientCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        // Celda para la fecha contractual
        PdfPCell contractualDateCell = new PdfPCell(new Phrase("     Fecha contractual:        " + contractualDate, infoFont));
        contractualDateCell.setBorder(Rectangle.NO_BORDER);
        contractualDateCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        clientInfoTable.addCell(clientCell);
        clientInfoTable.addCell(contractualDateCell);

        document.add(clientInfoTable);

        // Agregar el nombre del proyecto y el esquema de pintura en una tabla para mejor control de la alineación
        PdfPTable projectInfoTable = new PdfPTable(2);
        projectInfoTable.setWidthPercentage(100);
        projectInfoTable.setWidths(new float[]{1, 1}); // Distribución del ancho de las columnas

        // Celda para el nombre del proyecto
        PdfPCell projectCell = new PdfPCell(new Phrase("     Proyecto:   " + projectName, infoFont));
        projectCell.setBorder(Rectangle.NO_BORDER);
        projectCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        // Celda para el esquema de pintura
        PdfPCell paintSchemeCell = new PdfPCell(new Phrase("     Esquema de pintura:     " + paintScheme, infoFont));
        paintSchemeCell.setBorder(Rectangle.NO_BORDER);
        paintSchemeCell.setHorizontalAlignment(Element.ALIGN_LEFT);

        projectInfoTable.addCell(projectCell);
        projectInfoTable.addCell(paintSchemeCell);

        document.add(projectInfoTable);

    }

}
