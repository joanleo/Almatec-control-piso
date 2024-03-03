package com.almatec.controlpiso.utils;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ExportOpCentroTrabajoToPdf {
	
	private List<OpCentroTrabajoDTO> opsCt;
	private List<Integer> opsSeleccionadas;
	private CentroTrabajo centroTrabajo;
	
	public ExportOpCentroTrabajoToPdf(List<OpCentroTrabajoDTO> opsCt, List<Integer> opsSeleccionadas, CentroTrabajo centroTrabajo) {
		this.opsCt = opsCt;
		this.opsSeleccionadas = opsSeleccionadas;
		this.centroTrabajo = centroTrabajo;
	}
	
	private void tableHeader(PdfPTable table, List<String> columnsName) {
		PdfPCell cell = new PdfPCell();
	 	cell.setBackgroundColor(new Color(0, 121, 54));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        font.setSize(9);
        
        for(String name: columnsName) {
        	cell.setPhrase(new Phrase(name, font));
        	table.addCell(cell);        	
        }
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Rectangle letter = PageSize.LETTER;
		float halfLetterHeight = letter.getHeight() / 2;
		Rectangle halfLetter = new Rectangle(letter.getWidth(), halfLetterHeight);
		Document documento = new Document(halfLetter);
		
		try {
			PdfWriter writer = PdfWriter.getInstance(documento, response.getOutputStream());
			
			documento.setMargins(30, 20, 20, 20);	
			
			documento.open();
			
			String path = "static/imgs/logo-almatec.png";
			Image logoImage = Image.getInstance(getClass().getClassLoader().getResource(path));
			
			DateFormat dateFormatterCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String creationDate = dateFormatterCreacion.format(new Date());
			
			//DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			//String fechaContracActualString = "";//dateFormatter.format(opsCt.get(0).getFechaContraActual());
				        
	        int pageNumber = writer.getPageNumber();
	        
	        Font poweredByFont = new Font();
	        poweredByFont.setSize(8); 
	        Phrase poweredByPhrase = new Phrase("Powered by: www.integrapps.com", poweredByFont);
	        ColumnText.showTextAligned(writer.getDirectContentUnder(),
	                Element.ALIGN_LEFT, poweredByPhrase,
	                documento.left(), documento.bottom(), 0);
	        
	        Phrase pageNumberPhrase = new Phrase("Página " + pageNumber, poweredByFont);        
	        ColumnText.showTextAligned(writer.getDirectContent(),
	                Element.ALIGN_CENTER, pageNumberPhrase,
	                (documento.left() + documento.right()) / 2, documento.bottom(), 0);
	        
	        float originalWidth = logoImage.getWidth();
			float originalHeight = logoImage.getHeight();
			//System.out.println("Dimensiones originales de la imagen: " + originalWidth + " x " + originalHeight + " puntos.");
			float width = originalWidth - 56;
			float height = originalHeight - 25;
	        float xPosition = documento.left();
	        float yPosition = documento.top() - 60;
	        logoImage.scaleToFit(width, height);
	        logoImage.setAbsolutePosition(xPosition, yPosition);
	        documento.add(logoImage);
	        
	        PdfPTable dateTable = new PdfPTable(1);
	        dateTable.setWidthPercentage(100);
	        Paragraph dateParagraph = new Paragraph(creationDate, poweredByFont);
	        PdfPCell dateCell = new PdfPCell(dateParagraph);
	        dateCell.setBorder(Rectangle.NO_BORDER);
	        dateTable.addCell(dateCell);
	        
	        float dateXPosition = documento.right() - 90;
	        float dateYPosition = documento.bottom() + 10;
	        dateTable.setTotalWidth(100);
	        dateTable.writeSelectedRows(0, -1, dateXPosition, dateYPosition, writer.getDirectContent());
	        
	        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
	        Paragraph title = new Paragraph(centroTrabajo.getNombre(), titleFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        title.setSpacingBefore(30);
	        documento.add(title);
	        
	        List<OpCentroTrabajoDTO> opsToPdf = new ArrayList<>();
	       	for(OpCentroTrabajoDTO op: opsCt) {
	       		if(opsSeleccionadas.contains(op.getIdOp())) {
	       			System.out.println(op);
	       			opsToPdf.add(op);
	       		}
	       	}
	        List<RowItemPdf> rows = new ArrayList<>();
	       	for(OpCentroTrabajoDTO op: opsToPdf) {
	       		for(ItemOpCtDTO item: op.getItems()) {
	       			if(item.getIdCentroTrabajo() == centroTrabajo.getId()) {
	       				RowItemPdf row = new RowItemPdf();
	       				row.setDescripcion(item.getDescripcion());
	       				row.setCant(item.getCant());
	       				row.setProyecto(op.getProyecto());
	       				row.setPeso(item.getPeso());
	       				row.setPrioridad(item.getPrioridad());
	       				rows.add(row);
	       				System.out.println(row);
	       				break;
	       			}
	       			for(ComponenteDTO componente: item.getComponentes()) {
	       				if(componente.getIdCentroTrabajoPerfil() == centroTrabajo.getId()) {
	       					RowItemPdf row = new RowItemPdf();
		       				row.setDescripcion(componente.getDescripcionPerfil());
		       				row.setLongitud(componente.getLongitud());
		       				row.setCant(componente.getCant());
		       				row.setProyecto(op.getProyecto());
		       				row.setRef(componente.getCodErp().toString());
		       				row.setPeso(componente.getPesoPerfil());
		       				row.setPrioridad(item.getPrioridad());
		       				
		       				rows.add(row);
		       				System.out.println(row);
		       				break;
	       				}
	       			}
	       		}
	       		
	       	}
	       	
	       	int columnsTable = 9;
	       	float[] columnsWidth = {0.4f, 0.7f, 2.5f, 0.7f, 0.7f, 0.7f, 0.9f, 1.1f, 1.1f};	       	
	       	List<String> columnsName = new ArrayList<>();
			columnsName.addAll(Arrays.asList("#", "REF", "DESCRIPCION", "CANT", "LONG", "PESO UN", "PESO TTL", "PROYECTO", "PRIORIDAD"));
			PdfPTable table = new PdfPTable(columnsTable);
			table.setWidthPercentage(90);
			table.setWidths(columnsWidth);
			table.setSpacingBefore(40);
			tableHeader(table, columnsName);
			tableData(table, rows);
			
			
			documento.add(table);
	        documento.close();
		}catch (DocumentException | IOException e) {
			e.printStackTrace();
		}finally {
	        if (documento.isOpen()) {
	            documento.close();
	        }
	    }
	}

	private void tableData(PdfPTable table, List<RowItemPdf> rows) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(8);
		
		int count =  1;
		for(RowItemPdf row: rows) {
			Phrase phrase = new Phrase(String.valueOf(count), font);
			PdfPCell cell = new PdfPCell(phrase);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			phrase = new Phrase(row.getRef(), font);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			phrase = new Phrase(row.getDescripcion(), font);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			phrase = new Phrase(row.getCant().toString(), font);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			BigDecimal longitudBig = row.getLongitud();
			String longitud = longitudBig != null ? longitudBig.toString(): "";			
			phrase = new Phrase(longitud, font);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			phrase = new Phrase(row.getPeso().toString(), font);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			BigDecimal pesoTotal = row.getPeso().multiply(new BigDecimal(row.getCant()));			
			phrase = new Phrase(pesoTotal.toString(), font);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);			
						
			phrase = new Phrase(row.getProyecto(), font);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			String prioridad = row.getPrioridad() == null ? "" : row.getPrioridad().toString();
			phrase = new Phrase(prioridad, font);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPhrase(phrase);
			table.addCell(cell);
			count++;
		}
		
	}

}
