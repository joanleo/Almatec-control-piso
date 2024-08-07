package com.almatec.controlpiso.erp.webservices;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersión01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersión01;
import com.almatec.controlpiso.erp.webservices.generated.Final;
import com.almatec.controlpiso.erp.webservices.generated.Inicial;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;



@Transactional
@Component
public class XmlService {
	
	@Autowired 
	private RestTemplate restTemplate;
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	static final String RESPUESTA_OK = "Operacion realizada exitosamente";
	
	
	/**
	 * @return
	 */
	public String postImportarXML(List<Conector> conector) {
		
		HttpHeaders headersRequest = new HttpHeaders();
		headersRequest.setContentType(MediaType.valueOf("text/xml; charset=utf-8"));
		List<Conector> datosObject = new ArrayList<>(conector);
		
		String planoXml = crearPlanoXml(datosObject);
		System.out.println(planoXml);
		String soapRequest = crearBodyRequest(planoXml, 0);
		
		HttpEntity<String> requestEntity = new HttpEntity<>(soapRequest, headersRequest);
	    ResponseEntity<String> response = null;
	    /*Este bloque mostrara el xml para revision 
	     * si (logger.isInfoEnabled()) 
	        logger.info(requestEntity.toString())
	    */
	    String responseBody = null;
	    try {
	    	response = restTemplate.postForEntity(
	    			"http://10.75.98.4/WSUNOEE/WSUNOEE.asmx?wsdl", requestEntity, String.class);	
	    	// Obtener el contenido de la respuesta
	    	responseBody = response.getBody();
	    }catch (RestClientException e) {
	        logger.error("Error al realizar la llamada al servicio web: {}", e.getMessage());

	    }
	    String detalle = descomponerRespuestaXML(responseBody);
	     
		logger.info(detalle);
		return detalle;
		
		
	}

	
	private String crearBodyRequest(String planoXml, int error) {
		
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
				+ "	<soap:Body>\n"
				+ "		<ImportarXML xmlns=\"http://tempuri.org/\">\n"
				+ "			<pvstrDatos><![CDATA[" + planoXml + "]]></pvstrDatos>\n"
				+ "			<printTipoError>" + error + "</printTipoError>\n"
				+ "		</ImportarXML>\n"
				+ "	</soap:Body>\n"
				+ "</soap:Envelope>";
	}


	private String descomponerRespuestaXML(String responseBody) {
		
		// Convertir el contenido a un objeto Document
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;
	    String detalle = "";
		try {
			factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(responseBody));
			Document doc = builder.parse(is);
			Node printTipoErrorNode = doc.getElementsByTagName("printTipoError").item(0);
			// Obtener el valor de printTipoError
			int printTipoError = Integer.parseInt(printTipoErrorNode.getTextContent());
			System.out.println("errores " + printTipoError);
			// Si printTipoError es diferente de cero, obtener los detalles de NewDataSet
			if (printTipoError != 0) {
				NodeList tableNodes = doc.getElementsByTagName("Table");
				StringBuilder detalleBuilder = new StringBuilder();
	            detalleBuilder.append("Se presentaron ").append(tableNodes.getLength()).append(" errores ").append(System.lineSeparator());
	            for (int i = 0; i < tableNodes.getLength(); i++) {
	                Element table = (Element) tableNodes.item(i);
	                String detalleLinea = (i + 1) + "  Linea " + table.getElementsByTagName("f_nro_linea").item(0).getTextContent();
	                detalleLinea += " tipo de registro " + table.getElementsByTagName("f_tipo_reg").item(0).getTextContent();
	                detalleLinea += " Subtipo de registro " + table.getElementsByTagName("f_subtipo_reg").item(0).getTextContent();
	                detalleLinea += " Version " + table.getElementsByTagName("f_version").item(0).getTextContent();
	                detalleLinea += " nivel " + table.getElementsByTagName("f_nivel").item(0).getTextContent();
	                detalleLinea += " valor " + table.getElementsByTagName("f_valor").item(0).getTextContent();
	                detalleLinea += " " + table.getElementsByTagName("f_detalle").item(0).getTextContent();
	                detalleBuilder.append(detalleLinea).append(System.lineSeparator());
	            }
	            detalle = detalleBuilder.toString();
			}else {
				detalle = RESPUESTA_OK;
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		} 
		return detalle;
	}


	private String crearPlanoXml(List<Conector> datosObject) {
		String conexion = crearConexion();
		String datos = llenarDatos(datosObject);
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
		+ "					<Importar xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n"
		+ "						"+ conexion + "\n"
		+ "						"+ datos + "\n"
		+ "					</Importar>\n";
	}

	private String crearConexion() {
		String nombre = "Prueba";
		int cia = 22;
		String usuario = "integrapps";
		String clave = "8888";
		return "<NombreConexion>" + nombre + "</NombreConexion>\n"
		+ "						<IdCia>" + cia + "</IdCia>\n"
		+ "						<Usuario>" + usuario + "</Usuario>\n"
		+ "						<Clave>" + clave + "</Clave>";
	}

	private String llenarDatos(List<Conector> datos) {
	    StringBuilder datosXml = new StringBuilder("<Datos>\n");
	    int cont = 1;
	    
	    String inicioLinea = "    <Linea>";
	    String finLinea = "</Linea>\n";
	    Inicial inicio = new Inicial();
	    inicio.setF_numero_reg(cont);
	    inicio.setF_cia(22);
	    datosXml.append(inicioLinea).append(inicio.getConector()).append(finLinea);
	    cont++;

	    for (Conector dato : datos) {
	        dato.setF_numero_reg(cont);
	        datosXml.append(inicioLinea).append(dato.getConector()).append(finLinea);
	        cont++;
	    }

	    Final fin = new Final();
	    fin.setF_numero_reg(cont);
	    fin.setF_cia(22);
	    datosXml.append(inicioLinea).append(fin.getConector()).append(finLinea);
	    datosXml.append("</Datos>");

	    return datosXml.toString();
	}


	
	private void crearArchivo(List<Conector> datos, String nombre) throws IOException {
		String folderPath = "C:/IntegrApps/Almatec/xml";
		File folder = new File(folderPath );
		
		if (!folder.exists() && !folder.mkdirs()) {
		     throw new IOException("No se pudo crear la carpeta " + folderPath);
		}
		String fileName = nombre+".txt";

		File file = new File(folderPath, fileName);
		
		if (!file.exists() && !file.createNewFile()) {
	        throw new IOException("No se pudo crear el archivo " + fileName);
	    }
		
		FileWriter writer = new FileWriter(file);
		try {
			
			int cont = 1;
			Inicial inicio = new Inicial();
			inicio.setF_numero_reg(cont);
			inicio.setF_cia(22);
			writer.write(inicio.getConector()+"\n");
			cont ++;

			for(Conector dato: datos) {
				dato.setF_numero_reg(cont);
				writer.write(dato.getConector()+"\n");
				cont ++;
			}
			
			Final fin = new Final();
			fin.setF_numero_reg(cont);
			fin.setF_cia(22);
			writer.write(fin.getConector()+"\n");
						
		} catch (IOException e) {
    		logger.error("Error writing file: {}", fileName);
		}finally {
			writer.close();			
		}
	}
	
	public String obtenerFechaFormateada() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
		return formatoFecha.format(new Date());
	}
	
	public String obtenerFechaFormateada(Date fecha) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
		return formatoFecha.format(fecha);
	}


	public void crearOrdenProduccion(List<VistaItemPedidoErp> items) throws IOException {
		
		List<Conector> ordenProduccion = new ArrayList<>();
		
		DoctoordenesdeproduccionDocumentosVersión01 encabezadoOp = crearEncabezado(items.get(0).getNoPedido());
		ordenProduccion.add(encabezadoOp);
		List<DoctoordenesdeproduccionItemsVersión01> detallesOp = crearDetalleOp(items);
		ordenProduccion.addAll(detallesOp);
		
		String response = postImportarXML(ordenProduccion);
		String consecutivo = "PV-" + items.get(0).getNoPedido();
		crearArchivo(ordenProduccion, consecutivo);
		
		if(RESPUESTA_OK.equals(response)) {
			System.out.println("Pedido creado");
		}else {
			System.out.println(response);
		}
	}

	private DoctoordenesdeproduccionDocumentosVersión01 crearEncabezado(String noPedido) {
		DoctoordenesdeproduccionDocumentosVersión01 encabezado = new DoctoordenesdeproduccionDocumentosVersión01();
		encabezado.setF_cia(22);
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF850_id_co("001");
		encabezado.setF850_id_tipo_docto("IF");
		encabezado.setF850_fecha(obtenerFechaFormateada());
		encabezado.setF850_ind_estado(1);
		encabezado.setF850_id_clase_docto(701);
		encabezado.setF850_tercero_planificador("14701147");
		encabezado.setF850_id_instalacion("001");
		encabezado.setF850_clase_op("IF");
				
		encabezado.setF850_id_co_pv("001");
		encabezado.setF850_id_tipo_docto_pv("PV");
		encabezado.setF850_consec_docto_pv(Integer.valueOf(noPedido));
		System.out.println(encabezado);
		return encabezado;
	}

	private List<DoctoordenesdeproduccionItemsVersión01> crearDetalleOp(List<VistaItemPedidoErp> items) {
		List<DoctoordenesdeproduccionItemsVersión01> detalle = new ArrayList<>();
		
		int cont = 1;
		for(VistaItemPedidoErp item: items) {
			DoctoordenesdeproduccionItemsVersión01 movimiento = new DoctoordenesdeproduccionItemsVersión01();
			movimiento.setF_cia(22);
			movimiento.setF851_id_co("001");
			movimiento.setF851_nro_registro(cont);
			movimiento.setF851_id_item(Integer.valueOf(item.getReferencia()));
			movimiento.setF851_id_unidad_medida("POS"); 
			movimiento.setF851_porc_rendimiento(100.00);
			movimiento.setF851_cant_planeada_base(item.getCantidad());
			movimiento.setF851_fecha_inicio(obtenerFechaFormateada());
			movimiento.setF851_fecha_terminacion("20231106");
			movimiento.setF851_id_tipo_docto("IF");
			movimiento.setF851_id_bodega("00190");
			detalle.add(movimiento);
			cont++;
		}
		return detalle;
	}

	
}

