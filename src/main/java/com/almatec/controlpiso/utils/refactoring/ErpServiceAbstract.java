package com.almatec.controlpiso.utils.refactoring;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.almatec.controlpiso.erp.webservices.generated.Final;
import com.almatec.controlpiso.erp.webservices.generated.Inicial;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.integrapps.entities.Parametro;
import com.almatec.controlpiso.integrapps.services.ParametroService;

public abstract class ErpServiceAbstract {
	
	@Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ParametroService parametroService;

    protected ParametrosAppService parametros;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    static final String RESPUESTA_OK = "Operacion realizada exitosamente";

    @PostConstruct
    public void init() {
        List<Parametro> listadoParametros = parametroService.obtenerParametros();
        parametros = new ParametrosAppService(listadoParametros);
    }

    public String postImportarXML(List<Conector> conector) {
        HttpHeaders headersRequest = new HttpHeaders();
        headersRequest.setContentType(MediaType.valueOf("text/xml; charset=utf-8"));
        List<Conector> datosObject = new ArrayList<>(conector);
        String planoXml = crearPlanoXml(datosObject);
        String soapRequest = crearBodyRequest(planoXml, 0);
        HttpEntity<String> requestEntity = new HttpEntity<>(soapRequest, headersRequest);
        ResponseEntity<String> response = null;
        String responseBody = null;
        try {
            response = restTemplate.postForEntity(
                    "http://10.75.98.4/WSUNOEE/WSUNOEE.asmx?wsdl", requestEntity, String.class);
            responseBody = response.getBody();
        } catch (RestClientException e) {
            logger.error("Error al realizar la llamada al servicio web: {}", e.getMessage());
        }
        String detalle = descomponerRespuestaXML(responseBody);
        logger.info(detalle);
        return detalle;
    }

    protected String crearPlanoXml(List<Conector> datosObject) {
        String conexion = crearConexion();
        String datos = llenarDatos(datosObject);
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "					<Importar xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n"
                + "						" + conexion + "\n"
                + "						" + datos + "\n"
                + "					</Importar>\n";
    }

    protected String crearConexion() {
        String nombre = "Prueba";
        int cia = parametros.getCompania();
        String usuario = "integrapps";
        String clave = "8888";
        return "<NombreConexion>" + nombre + "</NombreConexion>\n"
                + "						<IdCia>" + cia + "</IdCia>\n"
                + "						<Usuario>" + usuario + "</Usuario>\n"
                + "						<Clave>" + clave + "</Clave>";
    }

    protected String llenarDatos(List<Conector> datos) {
        StringBuilder datosXml = new StringBuilder("<Datos>\n");
        int cont = 1;
        String inicioLinea = "    <Linea>";
        String finLinea = "</Linea>\n";
        Inicial inicio = new Inicial();
        inicio.setF_numero_reg(cont);
        inicio.setF_cia(parametros.getCompania());
        datosXml.append(inicioLinea).append(inicio.getConector()).append(finLinea);
        cont++;
        for (Conector dato : datos) {
            dato.setF_numero_reg(cont);
            datosXml.append(inicioLinea).append(dato.getConector()).append(finLinea);
            cont++;
        }
        Final fin = new Final();
        fin.setF_numero_reg(cont);
        fin.setF_cia(parametros.getCompania());
        datosXml.append(inicioLinea).append(fin.getConector()).append(finLinea);
        datosXml.append("</Datos>");
        return datosXml.toString();
    }

    protected String crearBodyRequest(String planoXml, int error) {
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

    protected String descomponerRespuestaXML(String responseBody) {
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
            } else {
                detalle = RESPUESTA_OK;
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return detalle;
    }
}
