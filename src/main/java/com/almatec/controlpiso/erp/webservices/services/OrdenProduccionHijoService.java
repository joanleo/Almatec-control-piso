package com.almatec.controlpiso.erp.webservices.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.comunicador.services.MensajeServices;
import com.almatec.controlpiso.erp.dto.ListaMaterialesDTO;
import com.almatec.controlpiso.erp.services.ListaMaterialService;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersion01;
import com.almatec.controlpiso.erp.webservices.generated.ItemsVersion05;
import com.almatec.controlpiso.erp.webservices.generated.ListadematerialesListadematerialesv4;
import com.almatec.controlpiso.erp.webservices.generated.RutasRutasV01;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.erp.webservices.interfaces.ConsultaItemOpCreado;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpCreadaDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.utils.UtilitiesApp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrdenProduccionHijoService {
	
	private final ConectorService conectorService;
	private final XmlService xmlService;
	private final ConectorCostoStandarService conectorCostoStandarService;
	private final ItemOpService itemOpService;
	private final ConectorListaMaterialesService conectorListaMaterialesService;
	private final ListaMaterialService listaMaterialService;
	private final OrdenPvService ordenPvService;
	private final ConectorOrdenProduccionHijoService conectorOrdenProduccionHijoService;
	private final MensajeServices mensajeServices;
	
	private final UtilitiesApp util;
	private final ConfigurationService configService;
	static final String RESPUESTA_OK = "Operacion realizada exitosamente";
	
	public OrdenProduccionHijoService(
			ConectorService conectorService,
			XmlService xmlService,
			ConectorCostoStandarService conectorCostoStandarService,
			ItemOpService itemOpService,
			ConectorListaMaterialesService conectorListaMaterialesService,
			ListaMaterialService listaMaterialService,
			OrdenPvService ordenPvService,
			ConectorOrdenProduccionHijoService conectorOrdenProduccionHijoService,
			MensajeServices mensajeServices,
			UtilitiesApp util,
			ConfigurationService configService) {
		super();
		this.conectorService = conectorService;
		this.xmlService = xmlService;
		this.conectorCostoStandarService = conectorCostoStandarService;
		this.itemOpService = itemOpService;
		this.conectorListaMaterialesService = conectorListaMaterialesService;
		this.listaMaterialService = listaMaterialService;
		this.ordenPvService = ordenPvService;
		this.conectorOrdenProduccionHijoService = conectorOrdenProduccionHijoService;
		this.mensajeServices = mensajeServices;
		this.util = util;
		this.configService = configService;
	}

	public String crearOrdenProduccion(Integer idOPI) throws IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		List<Conector> itemRutaXml = new ArrayList<>();
		ItemsVersion05 item = conectorService.crearConectorItem(idOPI);
		itemRutaXml.add(item);
		RutasRutasV01 ruta = conectorService.crearConectorRuta(item);
		itemRutaXml.add(ruta);
		
		LocalDateTime now = LocalDateTime.now();			        
		String dateTime = now.format(formatter);
		String responseItemRutaXml =xmlService.postImportarXML(itemRutaXml);
		util.guardarRegistroXml(xmlService.crearPlanoXml(itemRutaXml), "ITEM_RUTA-OP_IA_" + idOPI + "_" + dateTime);
		util.crearArchivoPlano(itemRutaXml, "SCP_TEP-OP_" + "ITEM_RUTA-OP_IA_" + idOPI  + dateTime , configService.getCIA());
		if (!responseItemRutaXml.equals(RESPUESTA_OK)) {
			return responseItemRutaXml;
		}
		
		List<Conector> operacionesXml = new ArrayList<>();
		operacionesXml.addAll(conectorService.crearConectorRutasOperaciones(idOPI, ruta));
		String responseOperaciones = xmlService.postImportarXML(operacionesXml);
		if (!responseOperaciones.equals(RESPUESTA_OK)) {
			return responseOperaciones;
		}
		
		List<Conector> ordenProduccionXml = new ArrayList<>();
		ordenProduccionXml.addAll(conectorService.crearConectorParametros(ruta.getF808_id(), Integer.valueOf(item.getF120_id())));
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStringLM = itemOpService.obtenerStringPorIdOPIntegrappsYTipo(idOPI, "LM");
		List<ListaMaterialesDTO> listaMaterialesDTO = new ArrayList<>();
		try {
			listaMaterialesDTO = objectMapper.readValue(jsonStringLM, new TypeReference<List<ListaMaterialesDTO>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ordenProduccionXml.addAll(conectorCostoStandarService.creaConectoresEdicionCosto(item.getF120_referencia(), listaMaterialesDTO));
		
		List<ListadematerialesListadematerialesv4> listaMateriales = conectorListaMaterialesService.crearConectorLM(listaMaterialesDTO, Integer.valueOf(item.getF120_id()));
		ordenProduccionXml.addAll(listaMateriales);
		
		VistaOrdenPv ordenIntegrapps = ordenPvService.obtenerOrdenPorId(idOPI);
		VistaOrdenPv ordenPadreIF = ordenPvService.obtenerOrdenPorId(ordenIntegrapps.getIdPadre());
		
		Double cantBase = Double.valueOf(listaMateriales.get(0).getF820_cant_base());
		DoctoordenesdeproduccionDocumentosVersion01 encabezadoOpEntrega = conectorOrdenProduccionHijoService.crearEncabezadoOPEntrega(ordenPadreIF);
		ordenProduccionXml.add(encabezadoOpEntrega);
		DoctoordenesdeproduccionItemsVersion01 detalleOpEntrega = conectorOrdenProduccionHijoService.crearDetalleOpEntrega(item, ordenIntegrapps,
				cantBase);
		ordenProduccionXml.add(detalleOpEntrega);
		
		String responseOpEntrega = xmlService.postImportarXML(ordenProduccionXml);
		if (!responseOpEntrega.equals(RESPUESTA_OK)) {
			return responseOpEntrega;
		}
		
		// Respuesta final
		// Actualizar tabla ordenPV rowid850 y rowid851, Num_Op,op_UnoEE,BarCodeH
		ConsultaItemOpCreado creadoInterface = listaMaterialService.obtenerRowIdOpItemOp(item.getF120_id());
		ConsultaOpCreadaDTO creado = new ConsultaOpCreadaDTO(creadoInterface);
		util.crearArchivoPlano(ordenProduccionXml, "OP-" + creado.getF850_consec_docto(), configService.getCIA());
		ordenIntegrapps = ordenPvService.actualizarDatosOp(creado, ordenIntegrapps);
		mensajeServices.enviarEmailCreacionOrdenProduccion(ordenIntegrapps);
		return "OP Creada Exitosamente";
	}

}
