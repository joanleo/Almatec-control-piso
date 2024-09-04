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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCtDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.interfaces.CommonDTO;
import com.almatec.controlpiso.integrapps.services.impl.VistaOpItemsMaterialesRutaServiceImpl;
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

public class ExportOptoPdf {

	private List<OpCentroTrabajoDTO> itemsOp;
	
	
	
	public ExportOptoPdf(List<OpCentroTrabajoDTO> itemsOp) {
		super();
		this.itemsOp = itemsOp;
	}
	
	private void tableCustom(Document document, int columns, float[] columnsWidth, List<String> columnsName, Boolean total) {
		PdfPTable table = new PdfPTable(columns);
		table.setWidthPercentage(100f);
		table.setWidths(columnsWidth);
		table.setSpacingBefore(30);
		
		tableHeader(table, columnsName);
		tableData(table, total, document);
				
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
	
	
	private void tableData(PdfPTable table, Boolean total, Document document) {

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(8);
		
		int count =  1;
		BigDecimal pesoTotal = BigDecimal.ZERO;
		
		OpCentroTrabajoDTO op = itemsOp.get(0);
		List<ItemOpCtDTO> itemsOprod = op.getItems();
		Set<ItemOpCtDTO> conjuntoItems = VistaOpItemsMaterialesRutaServiceImpl.mergeItems(new ArrayList<>(itemsOprod));
		
		Map<Integer, List<ItemOpCtDTO>> itemsGroupedByCentroTrabajo = conjuntoItems.stream()
	            .collect(Collectors.groupingBy(ItemOpCtDTO::getItem_centro_t_id));
		
		
		List<ComponenteDTO> componentes = new ArrayList<>();
		for(ItemOpCtDTO item: itemsOprod) {
			componentes.addAll(new ArrayList<>(item.getComponentes()));
		}
		
		

		for (Map.Entry<Integer, List<ItemOpCtDTO>> entry : itemsGroupedByCentroTrabajo.entrySet()) {
			List<ItemOpCtDTO> items = entry.getValue();
			for(ItemOpCtDTO item: items) {
				Phrase phrase = new Phrase(String.valueOf(count), font);
				PdfPCell cell = new PdfPCell(phrase);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);			
				table.addCell(cell);
				
				phrase = new Phrase(item.getItem_desc(), font);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPhrase(phrase);
				table.addCell(cell);	
				
				phrase = new Phrase(item.getItem_color(), font);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPhrase(phrase);
				table.addCell(cell);
				
				phrase = new Phrase("UND", font);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPhrase(phrase);
				table.addCell(cell);
				
				//cell = new PdfPCell(new Phrase(String.valueOf(item.getCant_req().setScale(0, RoundingMode.HALF_UP).intValue()), font));
				Integer cant = item.getCant_req().setScale(0, RoundingMode.HALF_UP).intValue();
				System.out.println("Cantidad: " + cant);
				phrase = new Phrase(String.valueOf(cant), font);
				cell.setPhrase(phrase);
				table.addCell(cell);
				
				BigDecimal peso = (item.getItem_peso() == null) ? BigDecimal.ZERO : item.getItem_peso();
				BigDecimal pesoTotalCell = peso.multiply(item.getCant_req());
				pesoTotal = pesoTotal.add(pesoTotalCell);
				phrase = new Phrase(pesoTotalCell.toString(), font);
				cell.setPhrase(phrase);
				table.addCell(cell);

				count++;
			}
			
		}
		if(total) {
			PdfPCell emptyCell = new PdfPCell();
			emptyCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(emptyCell);
			table.addCell(emptyCell);
			table.addCell(emptyCell);
			table.addCell(emptyCell);
			
			Phrase phrase = new Phrase("Total Kg", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(255, 255, 255)));
			PdfPCell cell = new PdfPCell(phrase);
			cell.setBackgroundColor(new Color(0, 121, 54));
			cell.setPadding(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell.setPhrase(new Phrase(pesoTotal.toString(), font));
			cell.setBackgroundColor(new Color(255, 255, 255));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);			
		}
		document.add(table);
		
		for (Map.Entry<Integer, List<ItemOpCtDTO>> entry : itemsGroupedByCentroTrabajo.entrySet()) {
		    List<ItemOpCtDTO> itemsParaCentroTrabajo = entry.getValue();
		    List<CommonDTO> commonDTOList = new ArrayList<>(itemsParaCentroTrabajo);
		    addTableForComponent(commonDTOList, document);
		}
		
		Map<Integer, Set<ComponenteDTO>> componentsGroupedByCentroTrabajo = componentes.stream()
				.filter(componente -> componente.getMaterial_centro_t_id() != 1 && componente.getMaterial_centro_t_id() != 19)
	            .collect(Collectors.groupingBy(ComponenteDTO::getMaterial_centro_t_id,
	            		Collectors.toSet()));
		
		for (Entry<Integer, Set<ComponenteDTO>> entry : componentsGroupedByCentroTrabajo.entrySet()) {
		    Set<ComponenteDTO> itemsParaCentroTrabajo = entry.getValue();
		    List<CommonDTO> commonDTOList = new ArrayList<>(itemsParaCentroTrabajo);
		    addTableForComponent(commonDTOList, document);
		}
	}
	
	private void tableDataCentroTrabajo(PdfPTable table, Boolean total, List<CommonDTO> componentes, Document document) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(8);
		
		int count =  1;	
		for(CommonDTO item: componentes) {
			//System.out.println(item);
			Phrase phrase = new Phrase(String.valueOf(count), font);
			PdfPCell cell = new PdfPCell(phrase);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);			
			table.addCell(cell);
			
			phrase = new Phrase(item.getDescripcion(), font);
			cell.setPhrase(phrase);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			PdfPCell emptyCell = new PdfPCell();
			table.addCell(emptyCell);

			table.addCell(emptyCell);

			phrase = new Phrase(String.valueOf(item.getCant()), font);
			cell.setPhrase(phrase);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			table.addCell(emptyCell);
		}
	}
	
	private void addTableForComponent(List<CommonDTO> componente, Document document) {
	    document.newPage();
	    
	    Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Paragraph subTitle = new Paragraph(componente.get(0).getCentroTrabajo(), subTitleFont);
        subTitle.setAlignment(Element.ALIGN_CENTER);
        subTitle.setSpacingBefore(20);
        document.add(subTitle);
        
        
	    int columnsTable = 6;
	    float[] columnsWidth = {0.4f, 3.0f, 0.9f, 0.4f, 0.7f, 0.7f};
	    List<String> columnsName = Arrays.asList("No", "DESCRIPCION", "COLOR", "UM", "CANT", "Total Kg");

	    PdfPTable table = new PdfPTable(columnsTable);
	    table.setWidthPercentage(100f);
	    table.setWidths(columnsWidth);
	    table.setSpacingBefore(30);

	    // Agregar el encabezado
	    tableHeader(table, columnsName);

	    Boolean isTotal = false;
	    tableDataCentroTrabajo(table, isTotal, componente, document);

	    // Agregar la tabla al documento
	    try {
	        document.add(table);
	    } catch (DocumentException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {	
		Rectangle letter = PageSize.LETTER;
		float halfLetterHeight = letter.getHeight() / 2;
		Rectangle halfLetter = new Rectangle(letter.getWidth(), halfLetterHeight);
		Document documento = new Document(halfLetter);
		//Document documento = new Document(PageSize..LETTER);
		try {
			PdfWriter pdfWriter = PdfWriter.getInstance(documento, response.getOutputStream());
			
			documento.setMargins(30, 20, 20, 20);		
			
			String path = "static/imgs/logo-almatec.png";
			Image image = Image.getInstance(getClass().getClassLoader().getResource(path));
			
			String barcodeString = itemsOp.get(0).getOp();
			
			DateFormat dateFormatterCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentDateTime = dateFormatterCreacion.format(new Date());
			
			DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			String fechaContracActualString = dateFormatter.format(itemsOp.get(0).getFechaContraActual());
			
			HeaderAndFooter event = new HeaderAndFooter(image, barcodeString, currentDateTime, 
					itemsOp.get(0).getCliente(), fechaContracActualString, itemsOp.get(0).getProyecto(),
					itemsOp.get(0).getEsquemaPintura());
	        pdfWriter.setPageEvent(event);
			
	        documento.open();
			int columnsTable = 6;
			float[] columnsWidth = {0.4f, 3.0f, 0.9f, 0.4f, 0.7f, 0.7f};
			List<String> columnsName = new ArrayList<>();
			columnsName.addAll(Arrays.asList("No", "DESCRIPCION", "COLOR", "UM", "CANT", "Total Kg"));
			Boolean isTotal = true;
			tableCustom(documento, columnsTable, columnsWidth, columnsName, isTotal);
			
			
			documento.close();			
		}catch (DocumentException | IOException e) {
			e.printStackTrace();
		}finally {
	        if (documento.isOpen()) {
	            documento.close();
	        }
	    }
		
	}
}
