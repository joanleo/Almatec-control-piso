package com.almatec.controlpiso.erp.webservices.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.erp.dto.ItemReporteConsumoDTO;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.interfaces.DataTEP;
import com.almatec.controlpiso.erp.services.ListaMaterialService;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosConsumos;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosMovtoInventarioV4;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPMovimientosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasDocumentosVersión02;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasMovimientosVersión01;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemListaMateriaInterface;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ParametroService;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class EntregaService {
	
	// Constants
    private static final String RESPUESTA_OK = "Operacion realizada exitosamente";
    private static final String DATE_TIME_FORMAT = "yyyyMMdd_HHmmss";
    private static final String CONJUNTO_TYPE = "CONJUNTO";
    private static final String RESPUESTA_ENTREGA_EXITOSA = "Consumo y TEP creado exitosamente. Entrega creada Exitosamente. ";
	
	private final ParametroService parametroService;
	private final ItemOpService itemOpService;
	private final ListaMaterialService listaMaterialService;
	private final SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	private final ConectorConsumoService conectorConsumoService;
	private final ConectorTepService conectorTepService;
	private final XmlService xmlService;
	private final ConectorEntregaService conectorEntregaService;
	private final UtilitiesApp util;
	private final ConfigurationService configService;
	
	public EntregaService(ParametroService parametroService, ItemOpService itemOpService,
			ListaMaterialService listaMaterialService, SolicitudMateriaPrimaService solicitudMateriaPrimaService,
			ConectorConsumoService conectorConsumoService, ConectorTepService conectorTepService, XmlService xmlService,
			ConectorEntregaService conectorEntregaService, UtilitiesApp util, ConfigurationService configService) {
		super();
		this.parametroService = parametroService;
		this.itemOpService = itemOpService;
		this.listaMaterialService = listaMaterialService;
		this.solicitudMateriaPrimaService = solicitudMateriaPrimaService;
		this.conectorConsumoService = conectorConsumoService;
		this.conectorTepService = conectorTepService;
		this.xmlService = xmlService;
		this.conectorEntregaService = conectorEntregaService;
		this.util = util;
		this.configService = configService;
	}

	private Integer getCantidadFabricada(ItemOp item, ReporteDTO reporte) {
        Integer cantFabItemOpEncontrada = parametroService.buscarCantidadesFabricadasConjunto(
            item.getId(),
            item.getIdItemFab(), 
            reporte.getIdCentroTrabajo()
        );
        return cantFabItemOpEncontrada == null ? 0 : cantFabItemOpEncontrada;
    }
	
	private List<ItemReporteConsumoDTO> processarListaMateriales(ItemOp item, ReporteDTO reporte, Integer cantFabItemOp, Integer piezasSinConsumo) {
        List<ItemListaMateriaInterface> listaMateriales = itemOpService
            .obtenerListaMaterialesItemPorIdItem(item.getId().intValue(), item.getIdItemFab());
        
        List<ItemReporteConsumoDTO> itemsConsumo = new ArrayList<>();
        
        if (CONJUNTO_TYPE.equals(listaMateriales.get(0).getitem_op_tipo())) {
            procesarMaterialesConjunto(item, reporte, cantFabItemOp, listaMateriales, itemsConsumo, piezasSinConsumo);
        }
        
        return itemsConsumo;
    }
	
	
	private void procesarMaterialesConjunto(ItemOp item, ReporteDTO reporte, Integer cantFabItemOp,
            List<ItemListaMateriaInterface> listaMateriales,
            List<ItemReporteConsumoDTO> itemsConsumo, Integer piezasSinConsumo) {
		
		listaMateriales.forEach(pieza -> {
			Integer cantFab = parametroService.buscarCantidadesFabricadasPerfil(
				item.getId(), 
				pieza.getid_parte(),
				reporte.getIdCentroTrabajo()
			);
		
			if (cantFab == null) {
				agregarItemConsumo(pieza, reporte.getCantReportar(), itemsConsumo);
			} else if (reporte.getCantReportar() > Math.abs(cantFab - cantFabItemOp)) {
				int cantConsumir = Math.abs(cantFab - cantFabItemOp);
				agregarItemConsumo(pieza, cantConsumir, itemsConsumo);
			}
		});
	}
	
	private void agregarItemConsumo(ItemListaMateriaInterface pieza, int cantidad,
            List<ItemReporteConsumoDTO> itemsConsumo) {
		
		itemsConsumo.add(new ItemReporteConsumoDTO(
			pieza.getid_materia_prima(),
			(double) (pieza.getcant_parte() * cantidad)
		));
	}
	
	private void agregarConsumoPintura(ItemOp item, ReporteDTO reporte,
            List<ItemReporteConsumoDTO> itemsConsumo) {
		
		Double pesoPinturaItem = item.getPesoPintura() / item.getCant();
		itemsConsumo.add(new ItemReporteConsumoDTO(
		item.getCodigoPintura(),
		pesoPinturaItem * reporte.getCantReportar()
		));
	}
	
	private List<Conector> generarDocumentosConsumoYTep(ItemOp item, ReporteDTO reporte,
            List<ItemReporteConsumoDTO> itemsConsumo) {
		List<Conector> consumoYTep = new ArrayList<>();
		
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		String idCTErp = solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());
		String idRuta = "0" + data.getf120_id();
		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);
		
		// Generate consumption documents if needed
		if (!itemsConsumo.isEmpty()) {
			generarDocumentosConsumo(consumoYTep, item, reporte, data, itemsConsumo);
		}
		
		// Generate TEP documents
		generarDocumentosTep(consumoYTep, item, reporte, data, dataTE, idCTErp);
		
		return consumoYTep;
	}
	
	private void generarDocumentosConsumo(List<Conector> consumoYTep, ItemOp item, ReporteDTO reporte,
            DataConsumoInterface data, List<ItemReporteConsumoDTO> itemsConsumo) {
		ConsumosdesdeCompromisosConsumos encabezadoConsumo = conectorConsumoService.crearEncabezadoConsumo(data);
		consumoYTep.add(encabezadoConsumo);
		
		Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
		itemsConsumo.forEach(i -> {
		ItemInterface itemFab = itemOpService.obtenerItemFabricaPorId(idItem);
		Integer codErpMateriaPrima = solicitudMateriaPrimaService.obtenerCodErpPorLote(reporte.getLote());
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movsConsumo = 
		conectorConsumoService.crearDetalleConsumo(itemFab, data, reporte, i, codErpMateriaPrima);
		consumoYTep.addAll(movsConsumo);
		});
	}
	
	private void generarDocumentosTep(List<Conector> consumoYTep, ItemOp item, ReporteDTO reporte,
            DataConsumoInterface data, DataTEP dataTE, String idCTErp) {
		DoctoTEPDocumentosVersion01 encabezadoTep = conectorTepService.crearEncabezadoTEP(item, reporte, idCTErp);
		consumoYTep.add(encabezadoTep);
		
		List<DoctoTEPMovimientosVersion01> movsTep = 
		conectorTepService.crearMovTiempos(reporte, data, dataTE, idCTErp, false);
		consumoYTep.addAll(movsTep);
	}
	
	private String procesarXmlConsumoYTep(List<Conector> consumoYTep, ReporteDTO reporte, String dateTime) 
            throws IOException {
        
        String nombreArchivo = String.format("SCP_TEP-OP_%s_%s", reporte.getNumOp(), dateTime);
        util.guardarRegistroXml(xmlService.crearPlanoXml(consumoYTep), nombreArchivo);
        util.crearArchivoPlano(consumoYTep, nombreArchivo, configService.getCIA());

        return xmlService.postImportarXML(consumoYTep);
        
    }
	
	private String procesarDocumentosEntrega(ItemOp item, ReporteDTO reporte, String dateTime) 
            throws IOException {
        List<Conector> entregaXml = new ArrayList<>();
        
        DoctoentregasDocumentosVersión02 encabezado = conectorEntregaService.crearEncabezadoEntrega();
        entregaXml.add(encabezado);

        DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
        DoctoentregasMovimientosVersión01 mov = conectorEntregaService.crearMovsEntrega(data, item, reporte);
        entregaXml.add(mov);

        String nombreArchivo = String.format("EP%s_%s", reporte.getNumOp(), dateTime);
        String responseEntrega = xmlService.postImportarXML(entregaXml);
        
        util.crearArchivoPlano(entregaXml, nombreArchivo, configService.getCIA());
        util.guardarRegistroXml(xmlService.crearPlanoXml(entregaXml), 
            String.format("EP-OP_%s_%s", reporte.getNumOp(), dateTime));
        
        return responseEntrega;
    }
	
	/**
	 * Crea la entrega para el item y reporte recibido
     * 
     * @param item el ItemOp para entregar
     * @param reporte El reporte contiene los datos de la entrega
     * @return String indica el resultado de la entrega
     * @throws IOException si existe un error procesando el XML
     */
	public String crearEntrega(ItemOp item, ReporteDTO reporte) throws IOException {
		
		Integer piezasSinConsumo = 0;

		Integer cantFabItemOp = getCantidadFabricada(item, reporte);		
		
		//se agregan consumos de materia prima que no ha sido reportada en la lista de materiales del item
		//List<ItemReporteConsumoDTO> itemsConsumo = processarListaMateriales(item, reporte, cantFabItemOp, piezasSinConsumo);
		List<ItemReporteConsumoDTO> itemsConsumo = new ArrayList<>();
		
		//Se agrega consumo de pintura
		agregarConsumoPintura(item, reporte, itemsConsumo);

		//se generan los conectores de consumo y tep
		List<Conector> consumoYTep = generarDocumentosConsumoYTep(item, reporte, itemsConsumo);
		
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
		//se envia al erp consumos y tep
        String responseConsumoTep = procesarXmlConsumoYTep(consumoYTep, reporte, dateTime);
        
        if(!responseConsumoTep.equals(RESPUESTA_OK)) {
        	return responseConsumoTep;
        }
        
        //Se procesa la entrega
        String resultado = procesarDocumentosEntrega(item, reporte, dateTime);
        
        return resultado.equals(RESPUESTA_OK) ? RESPUESTA_ENTREGA_EXITOSA : resultado;

	}
}
