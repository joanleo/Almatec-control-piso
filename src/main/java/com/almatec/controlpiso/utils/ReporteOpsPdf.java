package com.almatec.controlpiso.utils;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class ReporteOpsPdf {
	
	@Autowired
	private ItemOpService itemOpService;
	
	List<ItemOp> itemsOp;

	public ReporteOpsPdf(List<ItemOp> itemsOp, ItemOpService itemOpService) {
		super();
		this.itemsOp = itemsOp;
		this.itemOpService = itemOpService;
	}
	
	private void tableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
	 	cell.setBackgroundColor(new Color(0, 121, 54));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        font.setSize(9);
        
        cell.setPhrase(new Phrase("No", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("DESCRIPCION", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("UM", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("CANT", font));
        table.addCell(cell);
	}
	
	private void tableData(PdfPTable table) {
		List<ItemOp> itemsC2 = new ArrayList<>();
		for(ItemOp item: itemsOp) {
			System.out.println(item.getId());
			List<ItemOp> items = itemOpService.obtenerItemsOpC2(String.valueOf(item.getId()));
			itemsC2.addAll(items);
			System.out.println(items);
		}
		int count = 1;
		for(ItemOp item: itemsC2) {
			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			font.setSize(8);
			
			Phrase phrase = new Phrase(String.valueOf(count), font);
			PdfPCell cell = new PdfPCell(phrase);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);			
			table.addCell(cell);
			
			phrase = new Phrase(item.getDescripcion(), font);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			phrase = new Phrase(item.getUm(), font);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			phrase = new Phrase(item.getCant(), font);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			count++;
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		
		
		Document documento = new Document(PageSize.LETTER);
		PdfWriter.getInstance(documento, response.getOutputStream());
		Rectangle box = documento.getPageSize();
		
		String path = "static/imgs/logo-almatec.png";
		Image image = Image.getInstance(getClass().getClassLoader().getResource(path));
		image.setAbsolutePosition(box.getLeft() + 20, box.getTop() - 110);
		
		
		documento.setMargins(30, 20, 20, 20);		
		documento.open();
		documento.add(image);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);
        font.setColor(new Color(0,0,0));
         
        Paragraph titulo = new Paragraph("REPORTE DE OP", font);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingBefore(70);
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
        Paragraph fechaCreacion = new Paragraph("Fecha de creacion: " + currentDateTime, font1);
        fechaCreacion.setAlignment(Element.ALIGN_RIGHT);
        
        documento.add(fechaCreacion);
        documento.add(titulo);
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {0.4f, 3.0f, 0.4f, 0.7f});
        table.setSpacingBefore(30);
         
        tableHeader(table);
        tableData(table);
        
        documento.add(table);
        
        documento.close();
	}

}
