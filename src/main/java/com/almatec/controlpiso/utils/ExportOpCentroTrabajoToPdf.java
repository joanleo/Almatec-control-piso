package com.almatec.controlpiso.utils;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCtDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.lowagie.text.BadElementException;
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

public class ExportOpCentroTrabajoToPdf extends ExportToPdf {
	
	private List<OpCentroTrabajoDTO> opsCt;
	private List<Integer> opsSeleccionadas;
	private CentroTrabajo centroTrabajo;

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
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        font.setSize(8);
        
        for(String name: columnsName) {
        	cell.setPhrase(new Phrase(name, font));
        	table.addCell(cell);        	
        }
	}
	
	private void tableData(PdfPTable table, List<RowItemPdf> rows) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(7);
		
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
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
			
			BigDecimal pesoTotal = row.getPeso().multiply(row.getCant()).setScale(3, RoundingMode.HALF_UP);			
			phrase = new Phrase(pesoTotal.toString(), font);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);			
						
			phrase = new Phrase(row.getProyecto(), font);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			phrase = new Phrase(row.getColor(), font);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);
			
			String prioridad = row.getPrioridad() == null ? "" : row.getPrioridad().toString();
			phrase = new Phrase(prioridad, font);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(phrase);
			table.addCell(cell);
			count++;
		}
		
	}
	
	private List<RowItemPdf> obtenerFilas() {
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
				if(item.getItem_centro_t_id() == centroTrabajo.getIdCentroTrabajoErp()) {
					RowItemPdf row = new RowItemPdf();
					row.setDescripcion(item.getItem_desc());
					row.setCant(item.getCant_req());
					row.setProyecto(op.getOp());
					row.setPeso(item.getItem_peso());
					row.setColor(item.getItem_color());
					row.setPrioridad(item.getPrioridad());
					row.setRef(item.getItem_id().toString());
					rows.add(row);
					System.out.println(row);
					continue;
				}
				for(ComponenteDTO componente: item.getComponentes()) {
					if(componente.getMaterial_centro_t_id() == centroTrabajo.getIdCentroTrabajoErp()) {
						RowItemPdf row = new RowItemPdf();
		   				row.setDescripcion(componente.getMaterial_desc());
		   				row.setLongitud(componente.getMaterial_long());
		   				row.setCant(componente.getMaterial_cant());
		   				row.setProyecto(op.getOp());
		   				row.setRef(componente.getMaterial_id().toString());
		   				row.setPeso(componente.getMaterial_peso());
		   				row.setPrioridad(item.getPrioridad());
		   				
		   				rows.add(row);
		   				System.out.println(row);
		   				break;
					}
				}
			}
			
		}
		return rows;
	}

	@Override
	protected void addContent() throws DocumentException {

		List<RowItemPdf> rows = obtenerFilas();
		int columnsTable = 10;
       	float[] columnsWidth = {0.4f, 0.7f, 2.5f, 0.7f, 0.8f, 0.7f, 1.0f, 1.2f, 1.3f, 1.2f};	       	
       	List<String> columnsName = new ArrayList<>();
		columnsName.addAll(Arrays.asList("#", "REF", "DESCRIPCION", "CANT", "LONG", "PESO UN", "PESO TTL", "OP", "COLOR", "PRIORIDAD"));
		PdfPTable table = new PdfPTable(columnsTable);
		table.setWidthPercentage(100);
		table.setWidths(columnsWidth);
		table.setSpacingBefore(40);
		tableHeader(table, columnsName);
		tableData(table, rows);
		document.add(table);
	}
	
	
	
	@Override
    public void onOpenDocument(PdfWriter writer, Document document) {
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
	        
	        document.add(new Paragraph(" "));
	        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
	        Paragraph title = new Paragraph(centroTrabajo.getNombre(), titleFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        title.setSpacingBefore(30);
	        document.add(title);
	       
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		} 
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        createFooter(writer, document);
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
}
