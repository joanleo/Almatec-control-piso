package com.almatec.controlpiso.almacen.util;

import java.awt.Color;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.almatec.controlpiso.almacen.dto.DataTransportadoraDTO;
import com.almatec.controlpiso.almacen.interfaces.DetalleRemisionInterface;
import com.almatec.controlpiso.almacen.interfaces.EncabezadoRemision;
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
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;



public class RemisionPdfService extends PdfPageEventHelper{
	
	private final EncabezadoRemision encabezado;
	private final List<DetalleRemisionInterface> detalles;
	private final DataTransportadoraDTO dataTransportadora;
	
	public RemisionPdfService(EncabezadoRemision encabezado, List<DetalleRemisionInterface> detalle, DataTransportadoraDTO dataTransportadora) {
		this.encabezado = encabezado;
		this.detalles = detalle;
		this.dataTransportadora = dataTransportadora;
	}

	public void createPdf(HttpServletResponse response) throws DocumentException, IOException {
		
		Document document = new Document(PageSize.A4);
		document.setMargins(36, 36, 54, 54);
		PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
		writer.setPageEvent(this);
        document.open();
        
    	datosTitulo(document);
        
        datosCliente(document);
        
        datosTransportadora(document);
        
        detalleRemision(document);
        
        notasYObservaciones(document);
        
        materialEmpaque(document);
        
        
        PdfPTable firmasTable = new PdfPTable(5);        
        firmasTable.setWidthPercentage(100);
        try {
            firmasTable.setWidths(new float[] { 30f, 10f, 30, 10f, 30f});
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        PdfPCell supervisorCell = new PdfPCell(new Phrase("Supervisor"));
        supervisorCell.setBorder(Rectangle.TOP); 
        supervisorCell.setFixedHeight(30); 
        supervisorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        firmasTable.addCell(supervisorCell);
        
        PdfPCell emptyCell = new PdfPCell();
		emptyCell.setBorder(Rectangle.NO_BORDER);
		firmasTable.addCell(emptyCell);

        PdfPCell conductorCell = new PdfPCell(new Phrase("Conductor"));
        conductorCell.setBorder(Rectangle.TOP);
        conductorCell.setFixedHeight(30);
        conductorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        firmasTable.addCell(conductorCell);
        
        firmasTable.addCell(emptyCell);

        PdfPCell recibidoCell = new PdfPCell(new Phrase("Recepción"));
        recibidoCell.setBorder(Rectangle.TOP);
        recibidoCell.setFixedHeight(30);
        recibidoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        firmasTable.addCell(recibidoCell);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        float yPos = writer.getVerticalPosition(true);
        float tableHeight = firmasTable.getTotalHeight() ;
        float bottomMargin = document.bottomMargin() ;
        
        // Si la posición actual más la altura de la tabla es menor que el margen inferior,
        // entonces añadir la tabla, si no, añadir una nueva página.
        if (yPos - tableHeight - bottomMargin < 0) {
        	System.out.println("Operacion calculo altura: " + (yPos - tableHeight - bottomMargin));
        	document.add(Chunk.NEWLINE);
            document.newPage();
        }
        
        document.add(Chunk.NEWLINE);
        document.add(firmasTable);
        document.close();
        writer.close();
	}

	private void materialEmpaque(Document document) {
		Font empaqueFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
		PdfPTable tableEmpaque = new PdfPTable(4);
		tableEmpaque.setSpacingBefore(20);
		tableEmpaque.setWidthPercentage(100f);
		tableEmpaque.setWidths(new float[] { 1, 1, 1, 1});
		List<String> columnsName = new ArrayList<>();
		columnsName.addAll(Arrays.asList("Material Empaque", "Cantidad", "Peso", "Total"));
		
		tableHeader(tableEmpaque, columnsName, false);
		
		PdfPCell material1Cell = new PdfPCell(new Phrase("Palos", empaqueFont));
		material1Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableEmpaque.addCell(material1Cell);
		
		PdfPCell emptyCell = new PdfPCell();
		
		tableEmpaque.addCell(emptyCell);
		tableEmpaque.addCell(emptyCell);
		tableEmpaque.addCell(emptyCell);
		
		PdfPCell material2Cell = new PdfPCell(new Phrase("Estibas", empaqueFont));
		//material2Cell.setBorder(Rectangle.NO_BORDER);
		material2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableEmpaque.addCell(material2Cell);
		
		tableEmpaque.addCell(emptyCell);
		tableEmpaque.addCell(emptyCell);
		tableEmpaque.addCell(emptyCell);
		    		
		PdfPCell totalCell = new PdfPCell(new Phrase("Total", empaqueFont));
		//totalCell.setBorder(Rectangle.NO_BORDER);
		totalCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableEmpaque.addCell(totalCell);
		
		tableEmpaque.addCell(emptyCell);
		tableEmpaque.addCell(emptyCell);
		tableEmpaque.addCell(emptyCell);
		
		document.add(tableEmpaque);
	}

	private void notasYObservaciones(Document document) {
		Font notaFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
		PdfPTable tableNota = new PdfPTable(2);
		tableNota.setSpacingBefore(20);
		tableNota.setWidthPercentage(100f);
		tableNota.setWidths(new float[] { .18f, 1});
		
		PdfPCell notaCell = new PdfPCell(new Phrase("Nota: ", notaFont));
		notaCell.setBorder(Rectangle.NO_BORDER);
		notaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableNota.addCell(notaCell);
		
		PdfPCell descNotaCell = new PdfPCell(new Phrase("Despues de recibido el material se cuenta con 48 horas "
				+ "para reportar novedades, de lo contrario se da por entendido que todo el materia esta conforme "
				+ "a lo remisionado", notaFont));
		descNotaCell.setBorder(Rectangle.NO_BORDER);
		descNotaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableNota.addCell(descNotaCell);
		
		PdfPCell obsevacinesCell = new PdfPCell(new Phrase("Observaciones: ", notaFont));
		obsevacinesCell.setBorder(Rectangle.NO_BORDER);
		obsevacinesCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableNota.addCell(obsevacinesCell);
		
		PdfPCell emptyCell = new PdfPCell();
		emptyCell.setBorder(Rectangle.NO_BORDER);
		tableNota.addCell(emptyCell);
		
		document.add(tableNota);
	}

	private void detalleRemision(Document document) {
		PdfPTable detalleTable = new PdfPTable(8);
		detalleTable.setSpacingBefore(20);
		detalleTable.setWidthPercentage(100);
		detalleTable.setWidths(new float[] {0.5f, 0.7f, 0.7f, 2.8f, 1, 1, 1, 1});
		List<String> columnsName = new ArrayList<>();
		columnsName.addAll(Arrays.asList("#", "ITEM", "MARCA", "DESCRIPCION", "CANT", "# PAQUETES", "PESO UN [KG]", "PESO TTL [KG]"));
		
		tableHeader(detalleTable, columnsName, true);
		
		tableData(detalleTable);
		
		document.add(detalleTable);
	}
	
	private void tableData(PdfPTable detalleTable) {

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(7);
		
		DecimalFormat df = new DecimalFormat("#.###");
		int count =  1;
		
		double totalPeso = 0.0;
		
		for(DetalleRemisionInterface row: detalles) {
			detalleTable.addCell(createCell(String.valueOf(count), font));
	        detalleTable.addCell(createCell(row.getIdItemOp().toString(), font));
	        detalleTable.addCell(createCell(row.getMarca(), font));
	        detalleTable.addCell(createCell(row.getDescripcion(), font));
	        detalleTable.addCell(createCell(row.getCant().toString(), font));
	        detalleTable.addCell(createCell(row.getUndEmpaque(), font));
	        
	        String formattedPeso = df.format(row.getPeso());
	        detalleTable.addCell(createCell(formattedPeso, font));

	        Double pesoTtl = row.getPeso() * row.getCant();
	        String formattedPesoTtl = df.format(pesoTtl);
	        detalleTable.addCell(createCell(formattedPesoTtl, font));
	        
	        totalPeso += pesoTtl;
	        
	        count++;
		}
		
		PdfPCell totalLabelCell = new PdfPCell(new Phrase("Total Peso [KG]", font));
	    totalLabelCell.setColspan(7); // Combina las celdas para abarcar toda la fila excepto la celda del total
	    totalLabelCell.setBorder(Rectangle.NO_BORDER);
	    totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    detalleTable.addCell(totalLabelCell);

	    String formattedTotalPeso = df.format(totalPeso);
	    PdfPCell totalValueCell = new PdfPCell(new Phrase(formattedTotalPeso, font));
	    totalValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    detalleTable.addCell(totalValueCell);
	}
	
	private PdfPCell createCell(String content, Font font) {
	    Phrase phrase = new Phrase(content, font);
	    PdfPCell cell = new PdfPCell(phrase);
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setVerticalAlignment(Element.ALIGN_CENTER);
	    return cell;
	}
	
	private void tableHeader(PdfPTable table, List<String> columnsName, boolean isBackgorungColor) {
		PdfPCell cell = new PdfPCell();
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(8);
		
		if(isBackgorungColor) {
			cell.setBackgroundColor(new Color(0, 121, 54));			
			font.setColor(Color.WHITE);
		}
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		
        
        for(String name: columnsName) {
        	cell.setPhrase(new Phrase(name, font));
        	table.addCell(cell);        	
        }
	}

	private void datosTitulo(Document document) {
		Font titleFont = new Font();
		titleFont.setSize(20);
		titleFont.setStyle(Font.BOLD);
		Paragraph title = new Paragraph("LISTA DE EMPAQUE", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingBefore(25);
		document.add(title);
		
		Font companyFont = new Font();
		companyFont.setSize(16);
		companyFont.setStyle(Font.BOLD);
		Paragraph company = new Paragraph("ALMATEC LOGISTICA INTELIGENTE S.A.S.", companyFont);
		company.setAlignment(Element.ALIGN_CENTER);
		company.setSpacingBefore(10);
		document.add(company);
		
		Font nitFont = new Font();
		nitFont.setSize(14);
		nitFont.setStyle(Font.BOLD);
		Paragraph nit = new Paragraph("Nit: 900951036-1", nitFont);
		nit.setAlignment(Element.ALIGN_CENTER);
		document.add(nit);
	}

	private void datosTransportadora(Document document) {
		Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
		Font headerInfoFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
		headerInfoFont.setStyle(Font.BOLD);
		PdfPTable tableTransporteCliente = new PdfPTable(6);
		PdfPCell emptyCell = new PdfPCell();
		emptyCell.setBorder(Rectangle.NO_BORDER);

		tableTransporteCliente.setSpacingBefore(20);
		tableTransporteCliente.setWidthPercentage(100f);
		try {
		    tableTransporteCliente.setWidths(new float[] {1, 1, 1, 1, 1, 1});
		} catch (DocumentException e) {
		    e.printStackTrace();
		}
		
		// Safe get methods for dataTransportadora
		String nombreTransportadora = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getNombre()).orElse("") : "";
		String vehiculo = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getVehiculo()).orElse("") : "";
		String conductor = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getConductor()).orElse("") : "";
		String placa = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getPlaca()).orElse("") : "";
		String cedula = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getCedula()).orElse("") : "";
		String pase = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getPase()).orElse("") : "";
		String celular = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getCelular()).orElse("") : "";
		Double pesoEntrada = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getPesoEntrada()).orElse(0.0) : 0.0;
		Double pesoSalida = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getPesoSalida()).orElse(0.0) : 0.0;
		String horaEntrada = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getHoraEntrada()).orElse("") : "";
		String horaSalida = dataTransportadora != null ? 
		    Optional.ofNullable(dataTransportadora.getHoraSalida()).orElse("") : "";
		
		PdfPCell trasportadoraCell = new PdfPCell(new Phrase("Transportadora: ", headerInfoFont));
		trasportadoraCell.setBorder(Rectangle.NO_BORDER);
		trasportadoraCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(trasportadoraCell);
		
		PdfPCell nombreTransportadoraCell = new PdfPCell(new Phrase(nombreTransportadora, infoFont));
		nombreTransportadoraCell.setBorder(Rectangle.NO_BORDER);
		nombreTransportadoraCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(nombreTransportadoraCell);
		
		tableTransporteCliente.addCell(emptyCell);
		tableTransporteCliente.addCell(emptyCell);
		
		PdfPCell vehiculoCell = new PdfPCell(new Phrase("Vehiculo: ", headerInfoFont));
		vehiculoCell.setBorder(Rectangle.NO_BORDER);
		vehiculoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableTransporteCliente.addCell(vehiculoCell);
		
		PdfPCell descVehiculoCell = new PdfPCell(new Phrase(vehiculo, infoFont));
		descVehiculoCell.setBorder(Rectangle.NO_BORDER);
		descVehiculoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(descVehiculoCell);
		
		PdfPCell conductorCell = new PdfPCell(new Phrase("Conductor: ", headerInfoFont));
		conductorCell.setBorder(Rectangle.NO_BORDER);
		conductorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(conductorCell);
		
		PdfPCell nombreConductorCell = new PdfPCell(new Phrase(conductor, infoFont));
		nombreConductorCell.setBorder(Rectangle.NO_BORDER);
		nombreConductorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(nombreConductorCell);
		
		tableTransporteCliente.addCell(emptyCell);
		tableTransporteCliente.addCell(emptyCell);
		
		PdfPCell placaCell = new PdfPCell(new Phrase("Placa: ", headerInfoFont));
		placaCell.setBorder(Rectangle.NO_BORDER);
		placaCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableTransporteCliente.addCell(placaCell);
		
		PdfPCell numPlaca = new PdfPCell(new Phrase(placa, infoFont));
		numPlaca.setBorder(Rectangle.NO_BORDER);
		numPlaca.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(numPlaca);
		
		PdfPCell cedulaCell = new PdfPCell(new Phrase("Cedula: ", headerInfoFont));
		cedulaCell.setBorder(Rectangle.NO_BORDER);
		cedulaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(cedulaCell);
		
		PdfPCell numeroCedula = new PdfPCell(new Phrase(cedula, infoFont));
		numeroCedula.setBorder(Rectangle.NO_BORDER);
		numeroCedula.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(numeroCedula);
		
		PdfPCell paseCell = new PdfPCell(new Phrase("Pase: ", headerInfoFont));
		paseCell.setBorder(Rectangle.NO_BORDER);
		paseCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(paseCell);
		
		PdfPCell numPase = new PdfPCell(new Phrase(pase, infoFont));
		numPase.setBorder(Rectangle.NO_BORDER);
		numPase.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(numPase);
		
		tableTransporteCliente.addCell(emptyCell);
		tableTransporteCliente.addCell(emptyCell);
		
		PdfPCell celularCell = new PdfPCell(new Phrase("Celular: ", headerInfoFont));
		celularCell.setBorder(Rectangle.NO_BORDER);
		celularCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(celularCell);
		
		PdfPCell numeroCelular = new PdfPCell(new Phrase(celular, infoFont));
		numeroCelular.setBorder(Rectangle.NO_BORDER);
		numeroCelular.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(numeroCelular);
		
		PdfPCell pesoEntradaCell = new PdfPCell(new Phrase("Peso Entrada [Kg]: ", headerInfoFont));
		pesoEntradaCell.setBorder(Rectangle.NO_BORDER);
		pesoEntradaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(pesoEntradaCell);
		
		PdfPCell valorPesoEntradaCell = new PdfPCell(new Phrase(pesoEntrada.toString(), infoFont));
		valorPesoEntradaCell.setBorder(Rectangle.NO_BORDER);
		valorPesoEntradaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(valorPesoEntradaCell);
		
		PdfPCell pesoSalidaCell = new PdfPCell(new Phrase("Peso Salida [Kg]: ", headerInfoFont));
		pesoSalidaCell.setBorder(Rectangle.NO_BORDER);
		pesoSalidaCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableTransporteCliente.addCell(pesoSalidaCell);
		
		PdfPCell valorPespSalidaCell = new PdfPCell(new Phrase(pesoSalida.toString(), infoFont));
		valorPespSalidaCell.setBorder(Rectangle.NO_BORDER);
		valorPespSalidaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(valorPespSalidaCell);
		
		PdfPCell horaIngresoCell = new PdfPCell(new Phrase("Hora Ingreso: ", headerInfoFont));
		horaIngresoCell.setBorder(Rectangle.NO_BORDER);
		horaIngresoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(horaIngresoCell);
		
		PdfPCell valorHoraIngresoCell = new PdfPCell(new Phrase(horaEntrada, infoFont));
		valorHoraIngresoCell.setBorder(Rectangle.NO_BORDER);
		valorHoraIngresoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(valorHoraIngresoCell);
		
		PdfPCell horaSalidaCell = new PdfPCell(new Phrase("Hora Salida: ", headerInfoFont));
		horaSalidaCell.setBorder(Rectangle.NO_BORDER);
		horaSalidaCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(horaSalidaCell);
		
		PdfPCell valorHoraSalida = new PdfPCell(new Phrase(horaSalida, infoFont));
		valorHoraSalida.setBorder(Rectangle.NO_BORDER);
		valorHoraSalida.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(valorHoraSalida);
		
		PdfPCell pesoNetoCell = new PdfPCell(new Phrase("Peso Neto: ", headerInfoFont));
		pesoNetoCell.setBorder(Rectangle.NO_BORDER);
		pesoNetoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableTransporteCliente.addCell(pesoNetoCell);
				
		Double pesoNeto = pesoSalida - pesoEntrada;
		PdfPCell valorPesoNetoCell = new PdfPCell(new Phrase(pesoNeto.toString(), infoFont));
		valorPesoNetoCell.setBorder(Rectangle.NO_BORDER);
		valorPesoNetoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableTransporteCliente.addCell(valorPesoNetoCell);
		tableTransporteCliente.addCell(emptyCell);
		
		document.add(tableTransporteCliente);
	}
	
	private void datosCliente(Document document) {
		Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
		Font headerInfoFont = FontFactory.getFont(FontFactory.HELVETICA, 9);
		headerInfoFont.setStyle(Font.BOLD);
		PdfPTable tableDataCliente = new PdfPTable(5);
		tableDataCliente.setSpacingBefore(30);
		tableDataCliente.setWidthPercentage(100f);
		tableDataCliente.setWidths(new float[] {1, 2, 1, 1, 1});
		
		PdfPCell clientCell = new PdfPCell(new Phrase("Cliente: ", headerInfoFont));
		clientCell.setBorder(Rectangle.NO_BORDER);
		clientCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(clientCell);
		
		PdfPCell clientInfoCell = new PdfPCell(new Phrase(encabezado.getCliente(), infoFont));
		clientInfoCell.setBorder(Rectangle.NO_BORDER);
		clientInfoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(clientInfoCell);
		
		PdfPCell emptyCell = new PdfPCell();
		emptyCell.setBorder(Rectangle.NO_BORDER);
		tableDataCliente.addCell(emptyCell);
		//tableDataCliente.addCell(emptyCell);
		
		PdfPCell opCell = new PdfPCell(new Phrase("Num OP: ", headerInfoFont));
		opCell.setBorder(Rectangle.NO_BORDER);
		opCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableDataCliente.addCell(opCell);
		
		PdfPCell numOpCell = new PdfPCell(new Phrase(encabezado.getOp(), infoFont));
		numOpCell.setBorder(Rectangle.NO_BORDER);
		numOpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(numOpCell);
		
		PdfPCell proyectoCell = new PdfPCell(new Phrase("Proyecto: ", headerInfoFont));
		proyectoCell.setBorder(Rectangle.NO_BORDER);
		proyectoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(proyectoCell);
		
		PdfPCell descProyectoCell = new PdfPCell(new Phrase(encabezado.getProyecto().split("-")[1], infoFont));
		descProyectoCell.setBorder(Rectangle.NO_BORDER);
		descProyectoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(descProyectoCell);
		
		
		//tableDataCliente.addCell(emptyCell);
		tableDataCliente.addCell(emptyCell);
		tableDataCliente.addCell(emptyCell);
		tableDataCliente.addCell(emptyCell);
		
		PdfPCell dirCell = new PdfPCell(new Phrase("Direccion Despacho: ", headerInfoFont));
		dirCell.setBorder(Rectangle.NO_BORDER);
		dirCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(dirCell);
		
		PdfPCell dataDirCell = new PdfPCell(new Phrase(encabezado.getDireccion(), infoFont));
		dataDirCell.setBorder(Rectangle.NO_BORDER);
		dataDirCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		dataDirCell.setVerticalAlignment(Element.ALIGN_CENTER);
		tableDataCliente.addCell(dataDirCell);
		
		//tableDataCliente.addCell(emptyCell);
		tableDataCliente.addCell(emptyCell);
		
		PdfPCell ciudadCell = new PdfPCell(new Phrase("Ciudad Despacho: ", headerInfoFont));
		ciudadCell.setBorder(Rectangle.NO_BORDER);
		ciudadCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableDataCliente.addCell(ciudadCell);
		
		PdfPCell dataCiudadCell = new PdfPCell(new Phrase(encabezado.getCiudad(), infoFont));
		dataCiudadCell.setBorder(Rectangle.NO_BORDER);
		dataCiudadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(dataCiudadCell);
		
		PdfPCell contactoCell = new PdfPCell(new Phrase("Contacto: ", headerInfoFont));
		contactoCell.setBorder(Rectangle.NO_BORDER);
		contactoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(contactoCell);
		
		PdfPCell nombreContactoCell = new PdfPCell(new Phrase(encabezado.getContacto(), infoFont));
		nombreContactoCell.setBorder(Rectangle.NO_BORDER);
		nombreContactoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(nombreContactoCell);
		
		//tableDataCliente.addCell(emptyCell);
		tableDataCliente.addCell(emptyCell);
		
		PdfPCell telCell = new PdfPCell(new Phrase("Telefono: ", headerInfoFont));
		telCell.setBorder(Rectangle.NO_BORDER);
		telCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableDataCliente.addCell(telCell);
		
		PdfPCell NumTelCell = new PdfPCell(new Phrase(encabezado.getCelular(), infoFont));
		NumTelCell.setBorder(Rectangle.NO_BORDER);
		NumTelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableDataCliente.addCell(NumTelCell);
		
		document.add(tableDataCliente);
	}
	
	@Override
    public void onStartPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		String path = "static/imgs/logo-almatec.png";
		try {
			Image image = Image.getInstance(getClass().getClassLoader().getResource(path));
			float originalWidth = image.getWidth();
			float originalHeight = image.getHeight();
			float width = originalWidth - 56;
			float height = originalHeight - 25;
	        float xPosition = document.left() - 20;
	        float yPosition = document.top() - 30;
	        image.scaleToFit(width, height);
	        image.setAbsolutePosition(xPosition, yPosition);
	        document.add(image);
	        
	     // Agregar el código de barras
	        Barcode128 barcode = new Barcode128();
	        barcode.setCode("LE-" + encabezado.getIdRemision());
	        barcode.setCodeType(Barcode128.CODE128);
	        Image barcodeImage = barcode.createImageWithBarcode(cb, null, null);
	        barcodeImage.setAbsolutePosition(document.right() - 60, document.top() - 30);
	        barcodeImage.scalePercent(100);
	        document.add(barcodeImage);
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	@Override
    public void onEndPage(PdfWriter writer, Document document) {
		
		int pageNumber = writer.getPageNumber();
		
		Font poweredByFont = new Font();
        poweredByFont.setSize(7); 
        Phrase poweredByPhrase = new Phrase("Powered by: www.integrapps.com", poweredByFont);
        ColumnText.showTextAligned(writer.getDirectContentUnder(),
                Element.ALIGN_LEFT, poweredByPhrase,
                document.left(), document.bottom(), 0);

        Phrase pageNumberPhrase = new Phrase("Página " + pageNumber, poweredByFont);        
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_CENTER, pageNumberPhrase,
                (document.left() + document.right()) / 2, document.bottom(), 0);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Phrase datePhrase = new Phrase("Fecha Impresión: " + sdf.format(new Date()), poweredByFont);
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_RIGHT, datePhrase,
                document.right(), document.bottom(), 0);
	}

}
