package com.almatec.controlpiso.erp.webservices;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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

import com.almatec.controlpiso.almacen.interfaces.DetalleRemisionInterface;
import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.comunicador.services.MensajeServices;
import com.almatec.controlpiso.erp.dto.ListaMaterialesDTO;
import com.almatec.controlpiso.erp.dto.RutaDTO;
import com.almatec.controlpiso.erp.entities.ListaMaterial;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.interfaces.DataCostoStandarInterface;
import com.almatec.controlpiso.erp.interfaces.DataTEP;
import com.almatec.controlpiso.erp.interfaces.DetalleTransferenciaInterface;
import com.almatec.controlpiso.erp.interfaces.TarifaCostosSegmentoItem;
import com.almatec.controlpiso.erp.services.ListaMaterialService;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosConsumos;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosMovtoInventarioV4;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPDocumentosVersión01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPMovimientosVersión01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasDocumentosVersión01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasDocumentosVersión02;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasMovimientosVersión01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoinventariosDocumentosVersion02;
import com.almatec.controlpiso.erp.webservices.generated.DoctoinventariosMovimientosVersion06;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoremisionescomercialMovtoventascomercialV9;
import com.almatec.controlpiso.erp.webservices.generated.DoctoremisionescomercialRemisiónversión03;
import com.almatec.controlpiso.erp.webservices.generated.Final;
import com.almatec.controlpiso.erp.webservices.generated.Inicial;
import com.almatec.controlpiso.erp.webservices.generated.ItemsParametrosVersion5;
import com.almatec.controlpiso.erp.webservices.generated.ItemsVersión05;
import com.almatec.controlpiso.erp.webservices.generated.ListadematerialesListadematerialesv3;
import com.almatec.controlpiso.erp.webservices.generated.ListadematerialesListadematerialesv4;
import com.almatec.controlpiso.erp.webservices.generated.ManufacturaedicióndecostositemCostosV02;
import com.almatec.controlpiso.erp.webservices.generated.RutasRutasV01;
import com.almatec.controlpiso.erp.webservices.generated.RutasoperacionesOperacionesV01;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.erp.webservices.interfaces.ConsultaItemOpCreado;
import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpCreadaDTO;
import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.entities.Parametro;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemListaMateriaInterface;
import com.almatec.controlpiso.integrapps.interfaces.OrdenPvEstadoData;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.integrapps.services.ParametroService;
import com.almatec.controlpiso.utils.ItemReporteConsumoDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Transactional
@Component
public class XmlService {
	
	@Autowired 
	private RestTemplate restTemplate;
	
	@Autowired
	private ListaMaterialService listaMaterialService;
	
	@Autowired
	private ItemOpService itemOpService;
	
	@Autowired
	private OrdenPvService ordenPvService;
	
	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	
	@Autowired
	private MensajeServices mensajeService;

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	static final String RESPUESTA_OK = "Operacion realizada exitosamente";
	
	private Integer CIA;
	private String C_O;
	private String INSTALACION;
	private String METODO;
	private String TIPO_DOC_OP_PAPA;
	private String TIPO_DOC_OP_HIJO;
	private String CLASE_OP_PAPA;
	private String CLASE_OP_HIJO;
	private String TIPO_DOC_CONSUMO;
	private String TIPO_DOC_TEP;
	private String TIPO_DOC_TRANSFERENCIA;
	private String TIPO_DOC_ENTREGA;
	private String BODEGA_MATERIAL;
	private String BODEGA_ENTREGA_ITEM_FACTURABLE;
	private String BODEGA_ENTREGA_ITEM_HIJO;
	private String BODEGA_ENTREGA_TRANSFERENCIA;
	private String MOTIVO_TRANSFERENCIA;
	private String MOTIVO_CONSUMO;
	private String MOTIVO_ENTREGA;
	private String GRUPO_IMPOSITIVO;
	private String PLANIFICADOR;
	private String TIPO_SERVICIO;
	
	
	public void asignarParametros() {
		Map<String, Consumer<Parametro>> acciones = new HashMap<>();
		
		acciones.put("compania", parametro -> this.CIA = Integer.valueOf(parametro.getValor()));
	    acciones.put("centro operacion", parametro -> this.C_O = parametro.getValor());
	    acciones.put("instalacion", parametro -> this.INSTALACION = parametro.getValor());
	    acciones.put("metodo", parametro -> this.METODO = parametro.getValor());
	    acciones.put("tipo doc op papa", parametro -> this.TIPO_DOC_OP_PAPA = parametro.getValor());
	    acciones.put("tipo doc op hijo", parametro -> this.TIPO_DOC_OP_HIJO = parametro.getValor());
	    acciones.put("clase op papa", parametro -> this.CLASE_OP_PAPA = parametro.getValor());
	    acciones.put("clase op hijo", parametro -> this.CLASE_OP_HIJO = parametro.getValor());
	    acciones.put("tipo doc consumo", parametro -> this.TIPO_DOC_CONSUMO = parametro.getValor());
	    acciones.put("tipo doc tep", parametro -> this.TIPO_DOC_TEP = parametro.getValor());
	    acciones.put("tipo doc transferencia", parametro -> this.TIPO_DOC_TRANSFERENCIA = parametro.getValor());
	    acciones.put("tipo doc entrega", parametro -> this.TIPO_DOC_ENTREGA = parametro.getValor());
	    acciones.put("bodega de material", parametro -> this.BODEGA_MATERIAL = parametro.getValor());
	    acciones.put("bodega de entrega de item facturable", parametro -> this.BODEGA_ENTREGA_ITEM_FACTURABLE = parametro.getValor());
	    acciones.put("bodega entrega item hijo", parametro -> this.BODEGA_ENTREGA_ITEM_HIJO = parametro.getValor());
	    acciones.put("bodega entrega transferencia", parametro -> this.BODEGA_ENTREGA_TRANSFERENCIA = parametro.getValor());
	    acciones.put("motivo transferencia", parametro -> this.MOTIVO_TRANSFERENCIA = parametro.getValor());
	    acciones.put("motivo consumo", parametro -> this.MOTIVO_CONSUMO = parametro.getValor());
	    acciones.put("motivo entrega", parametro -> this.MOTIVO_ENTREGA = parametro.getValor());
	    acciones.put("grupo impositivo", parametro -> this.GRUPO_IMPOSITIVO = parametro.getValor());
	    acciones.put("planificador", parametro -> this.PLANIFICADOR = parametro.getValor());
	    acciones.put("tipo servicio", parametro -> this.TIPO_SERVICIO = parametro.getValor());

	    List<Parametro> parametros = parametroService.obtenerParametros();
	    parametros.forEach(parametro -> 
	        acciones.entrySet().stream()
	            .filter(entry -> parametro.getNombre().contains(entry.getKey()))
	            .forEach(entry -> entry.getValue().accept(parametro))
	    );
	}


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
			System.out.println(printTipoError);
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
		System.out.println((this.CIA));
		int cia = this.CIA;
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
	    inicio.setF_cia(this.CIA);
	    datosXml.append(inicioLinea).append(inicio.getConector()).append(finLinea);
	    cont++;

	    for (Conector dato : datos) {
	        dato.setF_numero_reg(cont);
	        datosXml.append(inicioLinea).append(dato.getConector()).append(finLinea);
	        cont++;
	    }

	    Final fin = new Final();
	    fin.setF_numero_reg(cont);
	    fin.setF_cia(this.CIA);
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
			inicio.setF_cia(this.CIA);
			writer.write(inicio.getConector()+"\n");
			cont ++;

			for(Conector dato: datos) {
				dato.setF_numero_reg(cont);
				writer.write(dato.getConector()+"\n");
				cont ++;
			}
			
			Final fin = new Final();
			fin.setF_numero_reg(cont);
			fin.setF_cia(this.CIA);
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


	public String crearOrdenProduccionPapa(List<VistaItemPedidoErp> items, Integer orden) throws IOException {
		
		List<Conector> costosParametros = calcularCostoStandarItem(items.get(0), this.BODEGA_ENTREGA_ITEM_FACTURABLE);
		
		List<Conector> ordenProduccion = new ArrayList<>();
		
		ordenProduccion.addAll(costosParametros);
		OrdenPvEstadoData ordenPv = ordenPvService.obtenerOrdenPorNumPv(items.get(0).getNoPedido());
		DoctoordenesdeproduccionDocumentosVersion01 encabezadoOp = crearEncabezadoOpPapa(items.get(0).getNoPedido(), ordenPv);
		ordenProduccion.add(encabezadoOp);
		List<DoctoordenesdeproduccionItemsVersion01> detallesOp = crearDetalleOpPapa(items);
		ordenProduccion.addAll(detallesOp);
		
		String response = postImportarXML(ordenProduccion);
		String consecutivo = "IF-" + items.get(0).getNoPedido();
		crearArchivo(ordenProduccion, consecutivo);
		
		if(RESPUESTA_OK.equals(response)) {
			mensajeService.enviarEmailAprobacionPedidoVenta("PV-"+items.get(0).getNoPedido());
			return "Orden creada exitosamente";
		}else {
			return response;
		}
	}

	private DoctoordenesdeproduccionDocumentosVersion01 crearEncabezadoOpPapa(Integer noPedido, OrdenPvEstadoData ordenPv) {
		DoctoordenesdeproduccionDocumentosVersion01 encabezado = new DoctoordenesdeproduccionDocumentosVersion01();
		encabezado.setF_cia(this.CIA);
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF850_id_co(this.C_O);
		encabezado.setF850_id_tipo_docto(this.TIPO_DOC_OP_PAPA);
		encabezado.setF850_fecha(obtenerFechaFormateada());
		encabezado.setF850_ind_estado(1);
		encabezado.setF850_id_clase_docto(701);
		encabezado.setF850_tercero_planificador(this.PLANIFICADOR);
		encabezado.setF850_id_instalacion(this.INSTALACION);
		encabezado.setF850_clase_op(this.CLASE_OP_PAPA);				
		encabezado.setF850_id_co_pv(this.C_O);
		encabezado.setF850_id_tipo_docto_pv("PV");
		encabezado.setF850_consec_docto_pv(noPedido);
		encabezado.setF850_referencia_1(ordenPv.getCliente());
		encabezado.setF850_referencia_2(ordenPv.getIdCo() + "-" + ordenPv.getCoDescripcion());
		return encabezado;
	}

	private List<DoctoordenesdeproduccionItemsVersion01> crearDetalleOpPapa(List<VistaItemPedidoErp> items) {
		List<DoctoordenesdeproduccionItemsVersion01> detalle = new ArrayList<>();
		
		int cont = 1;
		for(VistaItemPedidoErp item: items) {
			DoctoordenesdeproduccionItemsVersion01 movimiento = new DoctoordenesdeproduccionItemsVersion01();
			movimiento.setF_cia(this.CIA);
			movimiento.setF851_id_co(this.C_O);
			movimiento.setF851_nro_registro(cont);
			movimiento.setF851_id_item(Integer.valueOf(item.getReferencia()));
			movimiento.setF851_id_unidad_medida("KG"); 
			movimiento.setF851_porc_rendimiento(100.00);
			movimiento.setF851_cant_planeada_base(item.getCantidad());
			movimiento.setF851_fecha_inicio(obtenerFechaFormateada());
			movimiento.setF851_fecha_terminacion(obtenerFechaFormateada(item.getFechaEntrega()));
			movimiento.setF851_id_tipo_docto(this.TIPO_DOC_OP_PAPA);
			movimiento.setF851_id_bodega(this.BODEGA_ENTREGA_ITEM_FACTURABLE);//00190
			detalle.add(movimiento);
			cont++;
		}
		return detalle;
	}


	public String crearListaMAteriales(List<ListaMaterialesDTO> listaMateriales) {
		List<Conector> listaBorarXml = new ArrayList<>();
		List<ListaMaterial> lista = listaMaterialService.obtenerListaActual(listaMateriales.get(0).getF820_id());
		List<ListadematerialesListadematerialesv3> listaBorrar = new ArrayList<>();
		for(ListaMaterial item: lista){
			ListadematerialesListadematerialesv3 borrar = new ListadematerialesListadematerialesv3();
			borrar.setF_cia(this.CIA);
			borrar.setF820_id(listaMateriales.get(0).getF820_id());
			borrar.setF820_id_instalacion(this.INSTALACION);
			borrar.setF820_id_metodo(item.getMetodo());
			borrar.setF820_secuencia(item.getSecuencia());
			borrar.setF820_id_hijo(item.getIdHijo());
			listaBorrar.add(borrar);
		}
		listaBorarXml.addAll(listaBorrar);
		
		String response = postImportarXML(listaBorarXml);
		if(!response.equals(RESPUESTA_OK)) {
			return response;
		}

		List<Conector> listaCrearXml = new ArrayList<>();
		List<ListadematerialesListadematerialesv4> listaCrear = new ArrayList<>();
		for(ListaMaterialesDTO item: listaMateriales) {
			ListadematerialesListadematerialesv4 crear = new ListadematerialesListadematerialesv4();
			crear.setF_cia(this.CIA);
			crear.setF820_id(item.getF820_id());
			crear.setF820_id_instalacion(this.INSTALACION);
			crear.setF820_id_metodo(this.METODO);
			crear.setF820_secuencia(item.getF820_secuencia());
			crear.setF820_id_hijo(item.getF820_id_hijo());
			crear.setF820_cant_base(item.getF820_cant_base());
			crear.setF820_cant_requerida(item.getF820_cant_requerida());
			crear.setF820_fecha_activacion(obtenerFechaFormateada());
			crear.setF820_codigo_uso(0);
			crear.setF820_ind_no_costea(0);
			crear.setF820_id_bodega(item.getF820_id_bodega());
			
			listaCrear.add(crear);
		}
		
		listaCrearXml.addAll(listaCrear);
		
		String responseCrear = postImportarXML(listaCrearXml);
		
		return responseCrear;
	}

	public List<ListadematerialesListadematerialesv4> crearConectorLM(List<ListaMaterialesDTO> listaMateriales, Integer idItem){
		List<ListadematerialesListadematerialesv4> listaCrear = new ArrayList<>();
		for(ListaMaterialesDTO item: listaMateriales) {
			ListadematerialesListadematerialesv4 crear = new ListadematerialesListadematerialesv4();
			crear.setF_cia(this.CIA);
			crear.setF820_id(idItem);
			crear.setF820_id_instalacion(this.INSTALACION);
			crear.setF820_id_metodo(this.METODO);
			crear.setF820_secuencia(item.getF820_secuencia());
			crear.setF820_id_hijo(item.getF820_id_hijo());
			crear.setF820_cant_base(item.getF820_cant_base());
			crear.setF820_cant_requerida(item.getF820_cant_requerida());
			crear.setF820_fecha_activacion(obtenerFechaFormateada());
			crear.setF820_codigo_uso(0);
			crear.setF820_ind_no_costea(0);
			crear.setF820_id_bodega(item.getF820_id_bodega());
			
			listaCrear.add(crear);
		}
		return listaCrear;
	}

	
	public List<RutasoperacionesOperacionesV01> crearConectorRutaOperaciones(List<RutaDTO> rutas, String id) {
				
		List<RutasoperacionesOperacionesV01> listaCrear = new ArrayList<>();
		for(RutaDTO item: rutas) {
			RutasoperacionesOperacionesV01 crear = new RutasoperacionesOperacionesV01();
			crear.setF_cia(this.CIA);
			crear.setF_actualiza_reg(0);
			crear.setF808_id(id);
			crear.setF808_id_instalacion(this.INSTALACION);
			crear.setF809_id_metodo(this.METODO);
			crear.setF809_numero_operacion(item.getF809_numero_operacion());
			crear.setF809_ind_estado(1);
			crear.setF809_descripcion(item.getF809_descripcion());
			crear.setF809_ind_operacion_externa(0);
			crear.setF809_id_ctrabajo(item.getF809_id_ctrabajo());
			crear.setF809_cantidad_base(item.getF809_cantidad_base());
			crear.setF809_horas_maquina(item.getF809_horas_maquina());
			crear.setF809_fecha_activacion(obtenerFechaFormateada());
			crear.setF809_num_operarios_alistamiento("001");
			crear.setF809_num_operarios_ejecucion("001");
			crear.setF809_unidades_equivalentes(1.0);
			listaCrear.add(crear);
		}

		return listaCrear;
	}


	public String crearListaMaterialesPorIdTabla(Integer id) throws IOException {
		String response = null;
		String jsonString = itemOpService.obtenerStringListaMateriales(id);
		ObjectMapper objectMapper = new ObjectMapper();
		List<ListaMaterialesDTO> miDTOList = new ArrayList<>();
		try {
            miDTOList = objectMapper.readValue(jsonString, new TypeReference<List<ListaMaterialesDTO>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
		response = crearListaMAteriales(miDTOList);
		return response;
	}


	public ErrorMensaje crearTransferencia(SolicitudMateriaPrima solicitud, List<DetalleSolicitudMateriaPrima> detalles, Integer idSolIntegrapps) {
		List<Conector> transferenciaXml = new ArrayList<>();
		String nota = listaMaterialService.obtenerConsecutivoNotasTransferencia(idSolIntegrapps.toString());
		if(nota != null) {
			if(nota.split("-").length > 1) {
				Integer consec = Integer.valueOf(nota.split("-")[1]);
				nota = idSolIntegrapps + "-" + (consec+1);
			}else {
				nota = idSolIntegrapps + "-" + 1;
			}			
		}else {
			nota = idSolIntegrapps.toString();
		}
		DoctoinventariosDocumentosVersion02 encabezado = crearEncabezadoTransferencia(solicitud, idSolIntegrapps, nota);		
		transferenciaXml.add(encabezado);
		List<DoctoinventariosMovimientosVersion06> movs = crearMovimientosTransferencia(detalles, solicitud.getBodegaErp());		
		transferenciaXml.addAll(movs);
		
		String responseCrear = postImportarXML(transferenciaXml);
		if(!responseCrear.equals(RESPUESTA_OK)) {
			return new ErrorMensaje(true,responseCrear);
		}
		DetalleTransferenciaInterface transferencia = listaMaterialService.obtenerDetalleTransferencia(nota);
		SolicitudMateriaPrima solicitudIntegrapps = solicitudMateriaPrimaService.obtenerSolicitudPorId(idSolIntegrapps);
		solicitudIntegrapps.setNumDocErp(transferencia.getf350_consec_docto());
		solicitudIntegrapps.setIdEstado(1);
		solicitudIntegrapps.setFechaDocEp(transferencia.getf350_fecha_ts_creacion());
		solicitudMateriaPrimaService.actualizaSolicitud(solicitudIntegrapps);
		return new ErrorMensaje(false,responseCrear + "  se creo el documento de transferencia TRS-" + transferencia.getf350_consec_docto());
	}


	private DoctoinventariosDocumentosVersion02 crearEncabezadoTransferencia(SolicitudMateriaPrima solicitud, Integer idSolIntegrapps, String nota) {
		DoctoinventariosDocumentosVersion02 encabezado = new DoctoinventariosDocumentosVersion02();
		encabezado.setF_cia(this.CIA);
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF350_id_co(this.C_O);
		encabezado.setF350_id_tipo_docto(this.TIPO_DOC_TRANSFERENCIA);
		encabezado.setF350_consec_docto(0);
		encabezado.setF350_fecha(obtenerFechaFormateada());
		encabezado.setF350_id_clase_docto(67);
		encabezado.setF350_ind_estado(1);
		encabezado.setF350_ind_impresión(0);
		encabezado.setF450_id_concepto(607);
		encabezado.setF450_id_bodega_salida(this.BODEGA_MATERIAL);
		encabezado.setF450_id_bodega_entrada(this.BODEGA_ENTREGA_TRANSFERENCIA);
		encabezado.setF350_notas(nota);
		return encabezado;
	}


	private List<DoctoinventariosMovimientosVersion06> crearMovimientosTransferencia(List<DetalleSolicitudMateriaPrima> detalles, String bodegaSalida) {
		List<DoctoinventariosMovimientosVersion06> movs = new ArrayList<>();
		int count = 1;
		for(DetalleSolicitudMateriaPrima item: detalles) {
				DoctoinventariosMovimientosVersion06 mov = new DoctoinventariosMovimientosVersion06();
				mov.setF_cia(this.CIA);
				mov.setF470_id_co(this.C_O);
				mov.setF470_id_tipo_docto(this.TIPO_DOC_TRANSFERENCIA);
				mov.setF470_consec_docto(0);
				mov.setF_numero_reg(count);
				mov.setF470_id_bodega(this.BODEGA_MATERIAL);
				mov.setF470_id_lote(item.getLoteErp());
				mov.setF470_id_concepto(607);
				mov.setF470_id_motivo(this.MOTIVO_TRANSFERENCIA);
				mov.setF470_id_co_movto(this.C_O);
				mov.setF470_id_unidad_medida(item.getUndErp());
				mov.setF470_cant_base(item.getCantSol());
				mov.setF470_id_lote_ent(item.getLoteErp());
				mov.setF470_id_item(item.getCodigoErp());
				mov.setF470_id_un_movto("001");
				mov.setF470_rowid_movto(0);				
				
				movs.add(mov);	
				count++;
		}
		return movs;
	}
	
	public String crearConsumos(ItemOp item, ReporteDTO reporte) throws Exception {
		System.out.println("Creando consumo");
		List<Conector> consumosXml = new ArrayList<>();
		System.out.println("Numero op integrapps: " + item.getIdOpIntegrapps());
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		ConsumosdesdeCompromisosConsumos encabezado = crearEncabezadoConsumo(data);		
		consumosXml.add(encabezado);
		Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
		ItemInterface itemFab = itemOpService.obtenerItemFabricaPorId(idItem);
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movs = crearDetalleConsumo(itemFab, data, reporte, null);		
		consumosXml.addAll(movs);
		
		String responseCrearConsumo = postImportarXML(consumosXml);
		if(!responseCrearConsumo.equals(RESPUESTA_OK)) {
			throw new ResourceNotFoundException("Error al crear consumo: " + responseCrearConsumo);
		}
		
		return "Consumo creado Exitosamente";
		
	}


	private ConsumosdesdeCompromisosConsumos crearEncabezadoConsumo(DataConsumoInterface data) {
		try {
			ConsumosdesdeCompromisosConsumos encabezado = new ConsumosdesdeCompromisosConsumos();
			encabezado.setF_cia(this.CIA);
			encabezado.setF_consec_auto_reg(1);
			encabezado.setF350_id_co(this.C_O);
			encabezado.setF350_id_tipo_docto(this.TIPO_DOC_CONSUMO);
			encabezado.setF350_consec_docto(0);
			encabezado.setF350_id_fecha(obtenerFechaFormateada());
			encabezado.setF350_ind_estado(1);
			encabezado.setF350_ind_impresión(0);
			encabezado.setF350_id_clase_docto(710);
			encabezado.setF350_id_motivo(this.MOTIVO_CONSUMO);
			encabezado.setF850_tipo_docto(this.TIPO_DOC_OP_HIJO);
			encabezado.setF850_consec_docto(data.getf850_consec_docto());//Revisar  consecutivo op 850
			return encabezado;
		} catch (Exception e) {
	        e.printStackTrace(); // o registra la excepción con un logger
	    }
	    return null;
	}


	private List<ConsumosdesdeCompromisosMovtoInventarioV4> crearDetalleConsumo(ItemInterface itemFab, DataConsumoInterface data, ReporteDTO reporte,
			ItemReporteConsumoDTO itemReporteDTO) {
		System.out.println("Creando movimientos consumo");
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movs = new ArrayList<>();
		ConsumosdesdeCompromisosMovtoInventarioV4 mov = new ConsumosdesdeCompromisosMovtoInventarioV4();
		mov.setF_cia(this.CIA);
		mov.setF470_id_co(this.C_O);
		mov.setF470_id_tipo_docto(this.TIPO_DOC_CONSUMO);
		mov.setF470_consec_docto(0);
		mov.setF470_nro_registro(data.getf851_rowid());//data.getf851_rowid()revisar Rowid item padre, item de la op de etrega o hijo 851
		mov.setF470_id_item_padre(data.getf120_id());//revisar id item padre, item de la op de etrega o hijo 120
		Integer codErpMateriaPrima = 0;
		if(itemReporteDTO == null) {
			codErpMateriaPrima = solicitudMateriaPrimaService.obtenerCodErpPorLote(reporte.getLote());
			System.out.println(codErpMateriaPrima);
			mov.setF470_id_item_comp(codErpMateriaPrima);//revisar id item lista materiales **************************			
			System.out.println(itemFab);
			BigDecimal cantConsumir = itemFab.getitem_peso_b().multiply(new BigDecimal(reporte.getCantReportar()));
			mov.setF470_cant_base(cantConsumir.doubleValue());
			if(codErpMateriaPrima == 26403) mov.setF470_id_lote("123456789");//Esta informacion esta quemada para pruebas 
			if(codErpMateriaPrima == 18894) mov.setF470_id_lote("R1012522-2");//Esta informacion esta quemada para pruebas
			if(codErpMateriaPrima == 26399) mov.setF470_id_lote("9876");//Esta informacion esta quemada para pruebas 
			if(codErpMateriaPrima == 21703) mov.setF470_id_lote("654");//Esta informacion esta quemada para pruebas 
			if(codErpMateriaPrima == 19330) mov.setF470_id_lote("E1032722-733");//Esta informacion esta quemada para pruebas 
		}else {
			mov.setF470_id_item_comp(itemReporteDTO.getId());
			mov.setF470_cant_base(itemReporteDTO.getCant());
			if(itemReporteDTO.getId() == 26403) mov.setF470_id_lote("123456789");//Esta informacion esta quemada para pruebas 
			if(itemReporteDTO.getId() == 18894) mov.setF470_id_lote("R1012522-2");//Esta informacion esta quemada para pruebas
			if(itemReporteDTO.getId() == 26399) mov.setF470_id_lote("9876");//Esta informacion esta quemada para pruebas
			if(itemReporteDTO.getId() == 21703) mov.setF470_id_lote("654");//Esta informacion esta quemada para pruebas
			if(itemReporteDTO.getId() == 19330) mov.setF470_id_lote("E1032722-733");//Esta informacion esta quemada para pruebas
		}
		if(mov.getF470_id_lote().isBlank()) mov.setF470_id_lote(reporte.getLote());//revisar item lista materiales			
		mov.setF470_id_bodega(this.BODEGA_ENTREGA_TRANSFERENCIA);//revisar		
		mov.setF470_id_concepto(701);
		mov.setF470_id_motivo(this.MOTIVO_CONSUMO);
		mov.setF470_id_co_movto(this.C_O);
		mov.setF470_id_un_movto("001");
		mov.setF470_id_unidad_medida(data.getf120_id_unidad_inventario());
				
		movs.add(mov);
		
		return movs;
	}
	
	public String crearTEP(ItemOp item, ReporteDTO reporte) throws IOException {
		List<Conector> tepXml = new ArrayList<>();
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		String idRuta = "0" + data.getf120_id();
		String idCTErp = solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());
		System.out.println("Ruta: "+idRuta+" Centro trabajo: "+idCTErp);
		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);
		DoctoTEPDocumentosVersión01 encabezado = crearEncabezadoTEP(item, reporte, idCTErp);
		
		tepXml.add(encabezado);
		
		List<DoctoTEPMovimientosVersión01> movs = crearMovTiempos(reporte, data, dataTE, idCTErp);
		tepXml.addAll(movs);
		System.out.println("Creando TEP");
		String responseCrearTep = postImportarXML(tepXml);
		System.out.println(responseCrearTep);
		crearArchivo(tepXml, "TEP");
		if(!responseCrearTep.equals(RESPUESTA_OK)) {
			return responseCrearTep;
		}
		return "TEP creado Exitosamente";
	}


	private DoctoTEPDocumentosVersión01 crearEncabezadoTEP(ItemOp item, ReporteDTO reporte, String idCTErp) {
		DoctoTEPDocumentosVersión01 encabezado = new DoctoTEPDocumentosVersión01();
		encabezado.setF_cia(this.CIA);
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF350_id_co(this.C_O);
		encabezado.setF350_id_tipo_docto(this.TIPO_DOC_TEP);
		encabezado.setF350_consec_docto(0);
		encabezado.setF350_fecha(obtenerFechaFormateada());
		encabezado.setF350_ind_estado(1);
		encabezado.setF350_ind_impresión(0);
		encabezado.setF350_id_clase_docto(731);
		
		encabezado.setF450_id_centro_trabajo(idCTErp);//revisar
		encabezado.setF450_turno(1);//revisar
		encabezado.setF350_notas("Reporte de: " + item.getDescripcion() + " Cant: " +reporte.getCantReportar());
		return encabezado;
	}


	private List<DoctoTEPMovimientosVersión01> crearMovTiempos(ReporteDTO reporte, DataConsumoInterface data, DataTEP dataTE,String idCTErp) {
		System.out.println("Creando mov tep ");
		List<DoctoTEPMovimientosVersión01> movs = new ArrayList<>();
		System.out.println(reporte);
		Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
		System.out.println("Consumo idItem: "+idItem);
		ItemInterface item = itemOpService.obtenerItemFabricaPorId(idItem);
		System.out.println(item);
		Double valorAplicar = itemOpService.obtenerValorAplicarTepItemCentroTrabajo(idItem, reporte.getIdCentroTrabajo());
		Integer cantPiezasReportar = reporte.getCantReportar();
		System.out.println("piezas a reportar: "+ cantPiezasReportar);
		System.out.println("Valor aplicar: "+valorAplicar);

	    BigDecimal cantReportarTotalHoras =  BigDecimal.valueOf(valorAplicar * reporte.getCantReportar());
	    System.out.println(cantReportarTotalHoras);
	    BigDecimal maxPorIteracion = new BigDecimal(99);
	    if (cantReportarTotalHoras.compareTo(maxPorIteracion) > 0) {
	        int iteraciones = (int) Math.ceil(cantReportarTotalHoras.divide(maxPorIteracion, RoundingMode.UP).doubleValue());
	        BigDecimal cantidadPorIteracion = cantReportarTotalHoras.divide(new BigDecimal(iteraciones), 2, RoundingMode.HALF_UP);
	        System.out.println("cANTIDAD POR ITERACION: "+cantidadPorIteracion);
	        Double kilosTotales = item.getitem_peso_b().multiply(new BigDecimal(cantPiezasReportar)).doubleValue();
	        System.out.println("kILOS TOTALES " +kilosTotales);
	        Double kiloIteracion = kilosTotales / iteraciones;
	        System.out.println("KILOS POR ITERACION: " + kiloIteracion);

	        for (int i = 0; i < iteraciones; i++) {
	            BigDecimal cantReportarActual = (i == iteraciones - 1) ? cantReportarTotalHoras.subtract(cantidadPorIteracion.multiply(new BigDecimal(i))) : cantidadPorIteracion;
	            crearMovimiento(data, dataTE, idCTErp, cantReportarActual, movs, kiloIteracion);
	        }
	    } else {
	    	Double kilosTotales = item.getitem_peso_b().multiply(new BigDecimal(reporte.getCantReportar())).doubleValue();
	        crearMovimiento(data, dataTE, idCTErp, cantReportarTotalHoras, movs, kilosTotales);
	    }	    
		
		return movs;
	}
	
	private void crearMovimiento(DataConsumoInterface data, DataTEP dataTE, String idCTErp, 
			BigDecimal cantidadReportar, List<DoctoTEPMovimientosVersión01> movs, Double kilosReportar) {
		System.out.println("Creando conector movs tep");
		System.out.println(data.getf120_id());
		System.out.println(data.getf851_rowid());
		System.out.println(data.getf850_consec_docto());
		System.out.println(dataTE.getf809_numero_operacion());
		DoctoTEPMovimientosVersión01 mov = new DoctoTEPMovimientosVersión01();
	    mov.setF_cia(this.CIA);
	    mov.setF880_id_co(this.C_O);
    	mov.setF880_id_tipo_docto(this.TIPO_DOC_TEP);
    	mov.setF880_consec_docto(0);
    	mov.setF880_nro_registro(data.getf851_rowid());//Revisar rowid itemop 851
    	mov.setF880_id_tipo_docto_op(this.TIPO_DOC_OP_HIJO);
    	mov.setF880_consec_docto_op(data.getf850_consec_docto());//revisar 850 consecutivo
    	mov.setF880_id_item(data.getf120_id());//Revisar id 120
    	mov.setF880_numero_operacion(dataTE.getf809_numero_operacion());//Revisar
    	System.out.println(cantidadReportar);
    	mov.setF880_rowid_ctrabajo(idCTErp);//id centro trabajo
    	mov.setF880_ind_tipo_hora(1);//Revisar
    	mov.setF880_id_maquina(dataTE.getf807_id());//Revisar
    	mov.setF880_ind_operacion_completa(0);
	    mov.setF880_cant_completa_base(kilosReportar);
	    //MODIFICAR LA PARTE DECIMAL A MINUTOS DIVIDIR ENTRE 60
	    double cantidad = cantidadReportar.doubleValue();
	    int pEntera = (int) (cantidad / 1);
	    double pDecimal = cantidad - pEntera;
	    double min = pDecimal / 60.0;
	    double horas = pEntera + min;
	    //BigInteger parteEntera = cantidadReportar.toBigInteger();
	    //BigDecimal parteDecimal = cantidadReportar.subtract(new BigDecimal(parteEntera));
	    System.out.println("Parte decimal: " +pDecimal);
	    //BigDecimal minutos = parteDecimal.divide(BigDecimal.valueOf(60), 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
	    System.out.println("Minutos: " +min);
	    //BigDecimal resultadoFinal = new BigDecimal(parteEntera).add(minutos);
	    System.out.println("Horas reportar: " +horas);
	    
	    mov.setF880_horas(horas);
	    movs.add(mov);
		
	}


	public ItemsVersión05 crearConectorItemEntrega(VistaOrdenPv ordenIntegrapps) {
		
		ItemsVersión05 item = new ItemsVersión05();
		item.setF_cia(this.CIA);
		item.setF_actualiza_reg(0);
		Integer idLast = listaMaterialService.obtenerUltimoIdRef();
		Integer id = idLast +1;
		item.setF120_id(id);
		String stringId = String.valueOf(id);
		item.setF120_referencia("0"+ stringId);
		if(!ordenIntegrapps.getZona().isEmpty() && ordenIntegrapps.getZona().length() > 1) {
			item.setF120_descripcion(ordenIntegrapps.getIdCentroOperaciones()+ " " + ordenIntegrapps.getCentroOperaciones());			
			item.setF120_descripcion_corta(ordenIntegrapps.getIdCentroOperaciones() + " " + ordenIntegrapps.getCentroOperaciones());
		}
		item.setF120_id_grupo_impositivo(this.GRUPO_IMPOSITIVO);
		item.setF120_id_tipo_inv_serv(this.TIPO_SERVICIO);
		item.setF120_ind_tipo_item(1);
		item.setF120_ind_compra(0);
		item.setF120_ind_venta(0);
		item.setF120_ind_manufactura(1);
		item.setF120_ind_lote(0);
		item.setF120_ind_lote_asignacion(0);
		item.setF120_id_unidad_inventario("KG");
		item.setF120_factor_peso_inventario(1.0);
		item.setF120_id_unidad_orden("KG");
		item.setF120_ind_lista_precios_ext(0);
		item.setF121_ind_estado(1);
		item.setF121_fecha_creacion(obtenerFechaFormateada());
		item.setF120_ind_serial(0);
		item.setF120_ind_paquete(0);
		item.setF120_ind_exento(0);
		item.setF120_factor_orden(1.0);

		return item;
	}


	private ItemsParametrosVersion5 crearParametros(String idRuta, Integer idItem) {
		ItemsParametrosVersion5 parametros = new ItemsParametrosVersion5();
		parametros.setF_cia(this.CIA);
		parametros.setF_actualiza_reg(1);
		parametros.setF132_id_instalacion(this.INSTALACION);
		parametros.setF132_abc_rotacion_veces("A");
		parametros.setF132_abc_rotacion_costo("A");
		parametros.setF132_id_um_venta_suge("KG");
		parametros.setF132_mf_ind_mps(0);
		parametros.setF132_mf_porc_rendimiento(100.0);
		parametros.setF132_mf_ind_tipo_orden(1);
		parametros.setF132_mf_ind_politica_orden(1);
		
		parametros.setF132_mf_id_ruta(idRuta);
		parametros.setF132_mf_id_bodega_asigna(this.BODEGA_ENTREGA_ITEM_HIJO);
		parametros.setF132_mf_ind_generar_orden_prod(1);
		parametros.setF132_mf_ind_item_critico(1);
		parametros.setF120_id(idItem); 
		parametros.setF132_mf_ind_genera_ord_pln(0);
		
		return parametros;
	}
	
	public List<Conector> calcularCostoStandarItem(VistaItemPedidoErp item, String bodega) {
		List<Conector> costoXml = new ArrayList<>();
		
		ItemsParametrosVersion5 parametros = new ItemsParametrosVersion5();
		parametros.setF_cia(this.CIA);
		parametros.setF_actualiza_reg(1);
		parametros.setF132_id_instalacion(this.INSTALACION);
		parametros.setF132_abc_rotacion_veces("A");
		parametros.setF132_abc_rotacion_costo("A");
		parametros.setF132_id_um_venta_suge(item.getUnidadMedidaInventario());
		parametros.setF132_mf_ind_mps(0);
		parametros.setF132_mf_porc_rendimiento(100.0);
		parametros.setF132_mf_ind_tipo_orden(1);
		parametros.setF132_mf_ind_politica_orden(1);		
		parametros.setF132_mf_id_bodega_asigna(bodega);
		parametros.setF132_mf_ind_generar_orden_prod(1);
		parametros.setF132_mf_ind_item_critico(1);
		parametros.setF120_referencia(item.getReferencia());
		parametros.setF132_mf_ind_genera_ord_pln(0);
		
		costoXml.add(parametros);
		
		List<ManufacturaedicióndecostositemCostosV02>  costo = creaConectoresEdicionCosto(item.getReferencia(), null);
		
		costoXml.addAll(costo);
		
		return costoXml;
	}


	private List<ManufacturaedicióndecostositemCostosV02> creaConectoresEdicionCosto(String ref, List<ListaMaterialesDTO> listaMaterialesDTO) {
		List<ManufacturaedicióndecostositemCostosV02> costos = new ArrayList<>();
		Double costoStantar = 0.0001;
		if(listaMaterialesDTO == null) {
			costos.add(crearConectorCosto(ref, costoStantar, 901, null));
			return costos;
		}else {
			costoStantar = calcularCostoStandar(listaMaterialesDTO);			
			ManufacturaedicióndecostositemCostosV02 costo = crearConectorCosto(ref, costoStantar, 1, listaMaterialesDTO.get(0).getF820_cant_base());
			costos.add(costo);
			/*List<TarifaCostosSegmentoItem> segmentos = listaMaterialService.obtenerCostosSegmentos(ref);
			segmentos.forEach(segmento->{
				ManufacturaedicióndecostositemCostosV02 costoSegmento = crearConectorCosto(ref, segmento.getCostoTarifa(), segmento.getSegmento(), listaMaterialesDTO.get(0).getF820_cant_base());
				costos.add(costoSegmento);
			});*/
			return costos;
		}

	}


	private ManufacturaedicióndecostositemCostosV02 crearConectorCosto(String ref,
			 Double costoCalculado, int segmento, Double cantBase) {
		ManufacturaedicióndecostositemCostosV02 costo = new ManufacturaedicióndecostositemCostosV02();
		costo.setF_cia(this.CIA);
		costo.setF402_referencia_item(ref);
		costo.setF402_id_instalacion(this.INSTALACION);
		costo.setF402_id_grupo_costo(2);
		costo.setF402_id_segmento_costo(segmento);
		if(cantBase == null) {
			costo.setF402_costo_este_nivel_uni(costoCalculado);
		}else {
			Double costoUnidad = costoCalculado / cantBase;
			costo.setF402_costo_nivel_previo_uni(costoUnidad);			
		}
		return costo;
	}
	
	private Double calcularCostoStandar(List<ListaMaterialesDTO> listaMaterialesDTO) {
		return listaMaterialesDTO.stream()
				.map(item->{
					System.out.println(item);
					DataCostoStandarInterface dataCostoItem = listaMaterialService.obtenerCostoStandar(item.getF820_id_hijo());
					System.out.println(dataCostoItem.getIdItem()+" "+dataCostoItem.getTotalCostoStandar()+" "+dataCostoItem.getTotalCostoPrevio());
					Double totalCosto = dataCostoItem.getTotalCostoPrevio() + dataCostoItem.getTotalCostoStandar(); 
					return item.getF820_cant_requerida() * totalCosto;
				})
				.reduce(0.0, Double:: sum);		
	}


	private RutasRutasV01 crearConectorRuta(ItemsVersión05 item) {
		RutasRutasV01 ruta = new RutasRutasV01();
		ruta.setF_cia(this.CIA);
		ruta.setF_actualiza_reg(0);
		ruta.setF808_id(item.getF120_id());
		ruta.setF808_id_instalacion(this.INSTALACION);
		ruta.setF808_descripcion(item.getF120_descripcion());
		ruta.setF808_ind_estado(1);
		ruta.setF808_ind_controla_sec_piso(0);
		
		return ruta;
	} 

	public String crearOPEntrega(Integer idOPI) throws IOException{
		List<Conector> opXml = new ArrayList<>();
		VistaOrdenPv ordenIntegrapps = ordenPvService.obtenerOrdenPorId(idOPI);
		VistaOrdenPv ordenPadreIF = ordenPvService.obtenerOrdenPorId(ordenIntegrapps.getIdPadre());
		
		List<Conector> itemRutaXml = new ArrayList<>();
		ItemsVersión05 item = crearConectorItemEntrega(ordenIntegrapps);
		itemRutaXml.add(item);
		
		RutasRutasV01 ruta = crearConectorRuta(item);
		itemRutaXml.add(ruta);		
		String responseitemRuta = postImportarXML(itemRutaXml);		
		if(!responseitemRuta.equals(RESPUESTA_OK)) {
			return responseitemRuta;
		}

		String jsonStringRuta = itemOpService.obtenerStringPorIdOPIntegrappsYTipo(idOPI, "RUTA");
		ObjectMapper objectMapper = new ObjectMapper();
		List<RutaDTO> listaRutaDTO = new ArrayList<>();
		try {
			listaRutaDTO = objectMapper.readValue(jsonStringRuta, new TypeReference<List<RutaDTO>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		List<Conector> operacionesXml = new ArrayList<>();
		List<RutasoperacionesOperacionesV01> rutaOperaciones = crearConectorRutaOperaciones(listaRutaDTO, ruta.getF808_id());
		operacionesXml.addAll(rutaOperaciones);
		String responseOperaciones = postImportarXML(operacionesXml);		
		if(!responseOperaciones.equals(RESPUESTA_OK)) {
			return responseOperaciones;
		}
		
		ItemsParametrosVersion5 parametros = crearParametros(ruta.getF808_id(), Integer.valueOf(item.getF120_id()));
		opXml.add(parametros);
		
		
		String jsonStringLM = itemOpService.obtenerStringPorIdOPIntegrappsYTipo(idOPI, "LM");
		List<ListaMaterialesDTO> listaMaterialesDTO = new ArrayList<>();
		try {
			listaMaterialesDTO = objectMapper.readValue(jsonStringLM, new TypeReference<List<ListaMaterialesDTO>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

		List<ManufacturaedicióndecostositemCostosV02> costos = creaConectoresEdicionCosto(item.getF120_referencia(), listaMaterialesDTO);
		opXml.addAll(costos);
		
		List<ListadematerialesListadematerialesv4> listaMateriales = crearConectorLM(listaMaterialesDTO, Integer.valueOf(item.getF120_id()));
		opXml.addAll(listaMateriales);
				
		Double cantBase = Double.valueOf(listaMateriales.get(0).getF820_cant_base());
		DoctoordenesdeproduccionDocumentosVersion01 encabezadoOpEntrega = crearEncabezadoOPEntrega(ordenPadreIF);
		opXml.add(encabezadoOpEntrega);
		
		DoctoordenesdeproduccionItemsVersion01 detalleOpEntrega = crearDetalleOpEntrega(item, ordenIntegrapps, cantBase);
		opXml.add(detalleOpEntrega);
		
		String responseOpEntrega = postImportarXML(opXml);		
		if(!responseOpEntrega.equals(RESPUESTA_OK)) {
			return responseOpEntrega;
		}
		//Respuesta final
		//Actualizar tabla ordenPV rowid850 y rowid851, Num_Op,op_UnoEE,BarCodeH
		ConsultaItemOpCreado creadoInterface = listaMaterialService.obtenerRowIdOpItemOp(item.getF120_id());
		ConsultaOpCreadaDTO creado = new ConsultaOpCreadaDTO(creadoInterface);
		crearArchivo(opXml, "OP-" + creado.getF850_consec_docto());
		ordenPvService.actualizarDatosOp(creado,ordenIntegrapps);
		mensajeService.enviarEmailCreacionOrdenProduccion(creado.getF850_id_tipo_docto() + '-' + creado.getF850_consec_docto());
		return "OP Creada Exitosamente";
	}

	private DoctoordenesdeproduccionDocumentosVersion01 crearEncabezadoOPEntrega(VistaOrdenPv ordenPadreIF) {
		DoctoordenesdeproduccionDocumentosVersion01 encabezado = new DoctoordenesdeproduccionDocumentosVersion01();
		encabezado.setF_cia(this.CIA);
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF850_id_co(this.C_O);
		encabezado.setF850_id_tipo_docto(this.TIPO_DOC_OP_HIJO);
		encabezado.setF850_fecha(obtenerFechaFormateada());
		encabezado.setF850_ind_estado(0);
		encabezado.setF850_id_clase_docto(701);
		encabezado.setF850_tercero_planificador(this.PLANIFICADOR);
		encabezado.setF850_id_instalacion("001");
		encabezado.setF850_clase_op(this.CLASE_OP_HIJO);				
		encabezado.setF850_id_co_pv(this.C_O);
		encabezado.setF850_id_tipo_docto_op_padre(ordenPadreIF.getTipoOp());
		encabezado.setF850_consec_docto_op_padre(ordenPadreIF.getNumOp());
		encabezado.setF850_referencia_1(ordenPadreIF.getCliente());
		encabezado.setF850_referencia_2(ordenPadreIF.getIdCentroOperaciones() + "-" + ordenPadreIF.getCentroOperaciones() + "-" + ordenPadreIF.getZona());
		return encabezado;
	}
	
	private DoctoordenesdeproduccionItemsVersion01 crearDetalleOpEntrega(ItemsVersión05 item, VistaOrdenPv ordenIntegrapps, Double cantBase) {

		DoctoordenesdeproduccionItemsVersion01 movimiento = new DoctoordenesdeproduccionItemsVersion01();
		movimiento.setF_cia(this.CIA);
		movimiento.setF851_id_co(this.C_O);
		movimiento.setF851_nro_registro(0);
		movimiento.setF851_id_item(Integer.valueOf(item.getF120_id()));
		movimiento.setF851_id_unidad_medida("KG"); 
		movimiento.setF851_porc_rendimiento(100.00);
		movimiento.setF851_cant_planeada_base(cantBase);//ordenIntegrapps.getCant()
		movimiento.setF851_fecha_inicio(obtenerFechaFormateada());
		movimiento.setF851_fecha_terminacion(obtenerFechaFormateada(ordenIntegrapps.getFechaEntrega()));
		movimiento.setF851_id_tipo_docto(ordenIntegrapps.getTipoOp());
		movimiento.setF851_id_bodega(ordenIntegrapps.getBodega());
		movimiento.setF851_id_metodo_lista(this.METODO);
		movimiento.setF851_id_metodo_ruta(this.METODO);
		
		return movimiento;	

	}
	
	public String crearEntrega(ItemOp item, ReporteDTO reporte) throws Exception {
		System.out.println("Item op: " + item.getDescripcion());
		System.out.println("id item fab: " + item.getIdItemFab());
		System.out.println("Cantidades a reportar del item op: " + reporte.getCantReportar());
		Integer cantFabItemOpEncontrada = parametroService.buscarCantidadesFabricadasConjunto(item.getId(), item.getIdItemFab(), reporte.getIdCentroTrabajo());
		Integer cantFabItemOp = cantFabItemOpEncontrada == null ? 0: cantFabItemOpEncontrada;
		System.out.println("Cantidades reportadas: "+ cantFabItemOp);
		//Obtengo la lista de materiales del itempOp
		List<ItemListaMateriaInterface> itemMateriales = itemOpService.obtenerListaMaterialesItemPorIdItem(item.getId().intValue(), item.getIdItemFab());
		System.out.println("Tipo item: " + itemMateriales.get(0).getitem_op_tipo());
		List<ItemReporteConsumoDTO> itemReporta = new ArrayList<>();
		if("CONJUNTO".equals(itemMateriales.get(0).getitem_op_tipo())) {
			System.out.println("Componentes o partes del item: ");			
			itemMateriales.forEach(lista->{
				System.out.println("	Id item fab: " + lista.getid_parte()+" cantidad necesaria: "+lista.getcant_parte()+ " cantidad total: " + item.getCant() * lista.getcant_parte());
				Integer cantFab = parametroService.buscarCantidadesFabricadasPerfil(item.getId(), lista.getid_parte(), reporte.getIdCentroTrabajo());
				System.out.println("    	Cantidades fabricadas y reportadas: " + cantFab);
				System.out.println("    	Materia prima para crear el componente o parte: "+ lista.getid_parte());
				System.out.println("		   Id erp:" + lista.getid_materia_prima()+" cantidad necesaria: "+lista.getcan_materia_prima());
				
				if(cantFab == null) {
					System.out.println("	Se debe realizar el consumo de codigo materia prima: "+lista.getid_materia_prima()+" para unidades "+lista.getcant_parte() * reporte.getCantReportar()+" unidades de "+lista.getid_parte());
					itemReporta.add(new ItemReporteConsumoDTO(lista.getid_materia_prima(), (double)(lista.getcant_parte() * reporte.getCantReportar())) );
				}
				else if(reporte.getCantReportar() <= Math.abs(cantFab - cantFabItemOp)) {
					System.out.println(" 	No se debe realizar ningun consumo de materia prima");
				}else {
					int cantConsumir =  Math.abs(cantFab - cantFabItemOp);
					System.out.println(" 	Se debe realizar el consumo de codigo materia prima: "+lista.getid_materia_prima()+" para unidades "+cantConsumir * reporte.getCantReportar()+" unidades de "+lista.getid_parte());
					itemReporta.add(new ItemReporteConsumoDTO(lista.getid_materia_prima(), (double)(cantConsumir * reporte.getCantReportar())));
				}
				System.out.println("");
				System.out.println("");
			});
		}else {
			System.out.println("Materia prima para crear el item: ");
			itemMateriales.forEach(lista->{
				System.out.println("	Id erp:" + lista.getid_materia_prima()+" cantidad necesaria: "+lista.getcan_materia_prima());
			});
		}
		System.out.println("Se debe realizar el consumo de pintura id:"+item.getCodigoPintura()+" cantidad necesaria: "+item.getPesoPintura());
		itemReporta.add(new ItemReporteConsumoDTO(item.getCodigoPintura(), item.getPesoPintura() * reporte.getCantReportar()));
		List<Conector> consumoYTep = new ArrayList<>();

		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		String idCTErp = solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());
		String idRuta = "0" + data.getf120_id();
		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);
		if(!itemReporta.isEmpty()) {
			System.out.println("Ids Items a consumir: ");
			System.out.println("Ruta: "+idRuta+" Centro trabajo: "+idCTErp);

			ConsumosdesdeCompromisosConsumos encabezadoConsumo = crearEncabezadoConsumo(data);
			consumoYTep.add(encabezadoConsumo);
			Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
			itemReporta.forEach(i->{
				System.out.println(i);
				ItemInterface itemFab = itemOpService.obtenerItemFabricaPorId(idItem);
				List<ConsumosdesdeCompromisosMovtoInventarioV4> movsConsumo = crearDetalleConsumo(itemFab, data, reporte, i);
				consumoYTep.addAll(movsConsumo);
			});
			
		}
		
		DoctoTEPDocumentosVersión01 encabezadoTep = crearEncabezadoTEP(item,reporte, idCTErp);
		consumoYTep.add(encabezadoTep);
		
		List<DoctoTEPMovimientosVersión01> movsTep = crearMovTiempos(reporte, data, dataTE, idCTErp);
		consumoYTep.addAll(movsTep);
		String responseConsumoYTep = postImportarXML(consumoYTep);
		System.out.println(responseConsumoYTep 	);
		
		List<Conector> entregaXml = new ArrayList<>();
		DoctoentregasDocumentosVersión02 encabezado = crearEncabezadoEntrega();
		
		entregaXml.add(encabezado);
		
		DoctoentregasMovimientosVersión01 mov = crearMovsEntrega(data, item, reporte);
		
		entregaXml.add(mov);
		String responseEntrega = postImportarXML(entregaXml);
		System.out.println(responseEntrega);
		crearArchivo(entregaXml, "EP" + reporte.getNumOp());
		if(!responseEntrega.equals(RESPUESTA_OK)) {
			return responseEntrega;
		}
		
		return "Entrega creada Exitosamente";
	}

	private DoctoentregasDocumentosVersión02 crearEncabezadoEntrega() {
		DoctoentregasDocumentosVersión02 encabezado = new DoctoentregasDocumentosVersión02();
		encabezado.setF_cia(this.CIA);
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF350_id_co(this.C_O);
		encabezado.setF350_id_tipo_docto(this.TIPO_DOC_ENTREGA);
		encabezado.setF350_fecha(obtenerFechaFormateada());
		encabezado.setF350_ind_estado(1);
		encabezado.setF350_ind_impresión(0);
		encabezado.setF350_id_clase_docto(720);
		encabezado.setF350_notas("Creado por integrapps");
		return encabezado;
	}

	private DoctoentregasMovimientosVersión01 crearMovsEntrega(DataConsumoInterface data, ItemOp item, ReporteDTO reporte) {
		DoctoentregasMovimientosVersión01 mov = new  DoctoentregasMovimientosVersión01();
		mov.setF_cia(this.CIA);
		mov.setF470_id_co(this.C_O);
		mov.setF470_id_tipo_docto(this.TIPO_DOC_ENTREGA);
		mov.setF_numero_reg(data.getf851_rowid()); //rowId del item de la op 851
		mov.setF850_id_tipo_docto(this.TIPO_DOC_OP_HIJO);
		mov.setF850_consec_docto(data.getf850_consec_docto());
		mov.setF470_id_item(data.getf120_id());
		mov.setF470_id_bodega(this.BODEGA_ENTREGA_ITEM_HIJO);
		mov.setF470_id_concepto(701);
		mov.setF470_id_motivo_entrega(this.MOTIVO_ENTREGA);
		mov.setF470_id_co_movto(this.C_O);
		mov.setF470_id_un_movto("001");
		Double cantBase = item.getPeso().multiply(new BigDecimal(reporte.getCantReportar())).doubleValue();
		mov.setF470_cant_base_entrega(cantBase);
		return mov;
	}
	

	public String crearTEPYConsumos(ItemOp item, ReporteDTO reporte) throws IOException {
		System.out.println("Creando consumo y tep");
		List<Conector> tepYConsumosXml = new ArrayList<>();
		
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		String idRuta = "0" + data.getf120_id();
		String idCTErp = solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());
		System.out.println("Ruta: "+idRuta+" Centro trabajo: "+idCTErp);
		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);

		ConsumosdesdeCompromisosConsumos encabezadoConsumo = crearEncabezadoConsumo(data);		
		tepYConsumosXml.add(encabezadoConsumo);
		Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
		ItemInterface itemFab = itemOpService.obtenerItemFabricaPorId(idItem);
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movsConsumo = crearDetalleConsumo(itemFab, data, reporte, null);		
		tepYConsumosXml.addAll(movsConsumo);

		DoctoTEPDocumentosVersión01 encabezadoTep = crearEncabezadoTEP(item,reporte, idCTErp);
		tepYConsumosXml.add(encabezadoTep);
		
		List<DoctoTEPMovimientosVersión01> movsTep = crearMovTiempos(reporte, data, dataTE, idCTErp);
		tepYConsumosXml.addAll(movsTep);
		
		
		String responseCrearTepYConsumo = postImportarXML(tepYConsumosXml);
		System.out.println(responseCrearTepYConsumo);
		crearArchivo(tepYConsumosXml, "SCP_TEP");
		if(!responseCrearTepYConsumo.equals(RESPUESTA_OK)) {
			return responseCrearTepYConsumo;
		}
		return responseCrearTepYConsumo;
		
	}
	
	public String crearRemision(Integer idOpIa, List<DetalleRemisionInterface> detallesRemision) throws Exception {
		System.out.println("Creando xml");
		
		VistaOrdenPv ordenPv = ordenPvService.obtenerOrdenPorId(idOpIa);
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(ordenPv.getIdPadre());
		Integer idItem = listaMaterialService.obtenerItemOp(ordenPv.getNumOp());
		//crearConsumoOpPapa(idOpIa, detallesRemision, data, idItem);
		
		crearEntregaOpPapa(data, idItem, detallesRemision);
		
		/*List<Conector> remisionXml = new ArrayList<>();
		
		DoctoremisionescomercialRemisiónversión03 encabezadoRemision = crearEncabezado();
		remisionXml.add(encabezadoRemision);
		
		List<DoctoremisionescomercialMovtoventascomercialV9> movRemision = crearMovRemision();
		remisionXml.addAll(movRemision);
		
		String respuestaRemision = postImportarXML(remisionXml);
		
		System.out.println(respuestaRemision);*/
		return null;
	}


	private String crearEntregaOpPapa(DataConsumoInterface data, Integer idItem, List<DetalleRemisionInterface> detallesRemision) throws IOException {
		List<Conector> entregaXml = new ArrayList<>();
		DoctoentregasDocumentosVersión01 encabezado = crearEncabezadoEntregaOpPapa(true);
		
		entregaXml.add(encabezado);
		
		DoctoentregasMovimientosVersión01 mov = crearMovsEntregaOpPapa(data, idItem, detallesRemision);
		
		entregaXml.add(mov);
		String responseEntrega = postImportarXML(entregaXml);
		System.out.println(responseEntrega);
		crearArchivo(entregaXml, "EP");
		if(!responseEntrega.equals(RESPUESTA_OK)) {
			return responseEntrega;
		}
		
		return "Entrega creada Exitosamente";
		
	}


	private DoctoentregasDocumentosVersión01 crearEncabezadoEntregaOpPapa(boolean b) {
		DoctoentregasDocumentosVersión01 encabezado = new DoctoentregasDocumentosVersión01();
		encabezado.setF_cia(this.CIA);
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF350_id_co(this.C_O);
		encabezado.setF350_id_tipo_docto(this.TIPO_DOC_ENTREGA);
		encabezado.setF350_fecha(obtenerFechaFormateada());
		encabezado.setF350_ind_estado(1);
		encabezado.setF350_ind_impresión(0);
		encabezado.setF350_id_clase_docto(720);
		encabezado.setF350_notas("Creado por integrapps");
		
		return encabezado;
	}


	private DoctoentregasMovimientosVersión01 crearMovsEntregaOpPapa(DataConsumoInterface data, Integer idItem, List<DetalleRemisionInterface> detallesRemision) {
		DoctoentregasMovimientosVersión01 mov = new  DoctoentregasMovimientosVersión01();
		mov.setF_cia(this.CIA);
		mov.setF470_id_co(this.C_O);
		mov.setF470_id_tipo_docto(this.TIPO_DOC_ENTREGA);
		mov.setF_numero_reg(data.getf851_rowid()); //rowId del item de la op 851
		mov.setF850_id_tipo_docto(this.TIPO_DOC_OP_PAPA);
		mov.setF850_consec_docto(data.getf850_consec_docto());
		mov.setF470_id_item(data.getf120_id());
		mov.setF470_id_bodega(this.BODEGA_ENTREGA_ITEM_FACTURABLE);
		mov.setF470_id_concepto(701);
		mov.setF470_id_motivo_entrega(this.MOTIVO_ENTREGA);
		mov.setF470_id_co_movto(this.C_O);
		mov.setF470_id_un_movto("001");
		
		Double cantConsumir = detallesRemision.stream()
			    .map((DetalleRemisionInterface detalle) -> {
			        return detalle.getCant() * detalle.getPeso();
			    })
			    .reduce(0.0, Double::sum);
		
		mov.setF470_cant_base_entrega(3500.0);
		mov.setF470_id_ccosto_movto("160114");
		return mov;
	}


	private List<DoctoremisionescomercialMovtoventascomercialV9> crearMovRemision() {
		System.out.println("Creando movimentos");
		List<DoctoremisionescomercialMovtoventascomercialV9> movs = new ArrayList<>();
		DoctoremisionescomercialMovtoventascomercialV9 mov = new DoctoremisionescomercialMovtoventascomercialV9();
		mov.setF_cia(22);
		mov.setF470_id_co("001");
		mov.setF470_id_tipo_docto("RM");
		mov.setF470_nro_registro(42436); //Rowid item pedido 431
		mov.setF470_id_bodega(BODEGA_ENTREGA_ITEM_FACTURABLE);//definir el pedido en que bodega se monta, aqui deberia entregar el producto termino
		mov.setF470_id_concepto(501);
		mov.setF470_id_motivo("01");
		mov.setF470_ind_obsequio(0);
		mov.setF470_id_co_movto("001"); //Movimient del pedido 431
		mov.setF470_id_lista_precio("001"); // Del pedido
		mov.setF470_id_unidad_medida("KG");
		mov.setF470_cant_base(2000.0); //Cantidad a remisionar kg 
		mov.setF470_ind_naturaleza(1);
		mov.setF470_id_un_movto("001");// del pedido
		mov.setF470_id_item(CIA); // del pedido
		
		movs.add(mov);
		return movs;
	}


	private DoctoremisionescomercialRemisiónversión03 crearEncabezado() {
		System.out.println("Creando encabezado");
		DoctoremisionescomercialRemisiónversión03 encabezado = new DoctoremisionescomercialRemisiónversión03();
		encabezado.setF_cia(22);
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF350_id_co("001");
		encabezado.setF350_id_tipo_docto("RM");
		encabezado.setF350_fecha(obtenerFechaFormateada());
		encabezado.setF350_ind_estado(1);
		encabezado.setF350_ind_impresion(0);
		encabezado.setF430_id_tipo_docto("PV");
		encabezado.setF430_consec_docto(29); //consecutivo pedido
		
		
		return encabezado;
	}
	
	public String crearConsumoOpPapa(Integer idOpIa, List<DetalleRemisionInterface> detallesRemision, DataConsumoInterface data, Integer idItem) throws Exception {
		System.out.println("Creando consumo");
		List<Conector> consumosXml = new ArrayList<>();
		System.out.println("Numero op integrapps: " + idOpIa);
		
		ConsumosdesdeCompromisosConsumos encabezado = crearEncabezadoConsumoOpPapa(data);		
		consumosXml.add(encabezado);
		
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movs = crearDetalleConsumoOpPapa(data, idItem, detallesRemision);		
		consumosXml.addAll(movs);
		
		String responseCrearConsumo = postImportarXML(consumosXml);
		if(!responseCrearConsumo.equals(RESPUESTA_OK)) {
			throw new ResourceNotFoundException("Error al crear consumo: " + responseCrearConsumo);
		}
		System.out.println(responseCrearConsumo);
		return "Consumo creado Exitosamente";
		
	}

	private ConsumosdesdeCompromisosConsumos crearEncabezadoConsumoOpPapa(DataConsumoInterface data) {
		try {
			ConsumosdesdeCompromisosConsumos encabezado = new ConsumosdesdeCompromisosConsumos();
			encabezado.setF_cia(this.CIA);
			encabezado.setF_consec_auto_reg(1);
			encabezado.setF350_id_co(this.C_O);
			encabezado.setF350_id_tipo_docto(this.TIPO_DOC_CONSUMO);
			encabezado.setF350_consec_docto(0);
			encabezado.setF350_id_fecha(obtenerFechaFormateada());
			encabezado.setF350_ind_estado(1);
			encabezado.setF350_ind_impresión(0);
			encabezado.setF350_id_clase_docto(710);
			encabezado.setF350_id_motivo(this.MOTIVO_CONSUMO);
			encabezado.setF850_tipo_docto(this.TIPO_DOC_OP_PAPA);
			encabezado.setF850_consec_docto(data.getf850_consec_docto());//Revisar  consecutivo op 850
			return encabezado;
		} catch (Exception e) {
	        e.printStackTrace(); // o registra la excepción con un logger
	    }
	    return null;
	}


	private List<ConsumosdesdeCompromisosMovtoInventarioV4> crearDetalleConsumoOpPapa(DataConsumoInterface data, Integer idItem, List<DetalleRemisionInterface> detallesRemision) {
		System.out.println("Creando movimientos consumo");
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movs = new ArrayList<>();
		ConsumosdesdeCompromisosMovtoInventarioV4 mov = new ConsumosdesdeCompromisosMovtoInventarioV4();
		mov.setF_cia(this.CIA);
		mov.setF470_id_co(this.C_O);
		mov.setF470_id_tipo_docto(this.TIPO_DOC_CONSUMO);
		mov.setF470_consec_docto(0);
		mov.setF470_nro_registro(data.getf851_rowid());//data.getf851_rowid()revisar Rowid item padre, item de la op de etrega o hijo 851
		mov.setF470_id_item_padre(data.getf120_id());//revisar id item padre, item de la op de etrega o hijo 120

		mov.setF470_id_item_comp(idItem);//revisar id item lista materiales **************************			
		Double cantConsumir = detallesRemision.stream()
			    .map((DetalleRemisionInterface detalle) -> {
			        return detalle.getCant() * detalle.getPeso();
			    })
			    .reduce(0.0, Double::sum);

		mov.setF470_cant_base(cantConsumir);

		mov.setF470_id_bodega(this.BODEGA_ENTREGA_ITEM_FACTURABLE);//revisar		
		mov.setF470_id_concepto(701);
		mov.setF470_id_motivo(this.MOTIVO_CONSUMO);
		mov.setF470_id_co_movto(this.C_O);
		mov.setF470_id_un_movto("001");
		mov.setF470_id_unidad_medida(data.getf120_id_unidad_inventario());
				
		movs.add(mov);
		
		return movs;
	}
	
	
}

