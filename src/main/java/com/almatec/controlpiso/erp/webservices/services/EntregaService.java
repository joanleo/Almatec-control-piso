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

	static final String RESPUESTA_OK = "Operacion realizada exitosamente";

	public String crearEntrega(ItemOp item, ReporteDTO reporte) throws IOException {

		Integer cantFabItemOpEncontrada = parametroService.buscarCantidadesFabricadasConjunto(item.getId(),
				item.getIdItemFab(), reporte.getIdCentroTrabajo());
		Integer cantFabItemOp = cantFabItemOpEncontrada == null ? 0 : cantFabItemOpEncontrada;
		// Obtengo la lista de materiales del itempOp
		List<ItemListaMateriaInterface> itemMateriales = itemOpService
				.obtenerListaMaterialesItemPorIdItem(item.getId().intValue(), item.getIdItemFab());
		List<ItemReporteConsumoDTO> itemReporta = new ArrayList<>();
		if ("CONJUNTO".equals(itemMateriales.get(0).getitem_op_tipo())) {
			itemMateriales.forEach(lista -> {
				Integer cantFab = parametroService.buscarCantidadesFabricadasPerfil(item.getId(), lista.getid_parte(),
						reporte.getIdCentroTrabajo());

				if (cantFab == null) {
					itemReporta.add(new ItemReporteConsumoDTO(lista.getid_materia_prima(),
							(double) (lista.getcant_parte() * reporte.getCantReportar())));
				} else if (reporte.getCantReportar() <= Math.abs(cantFab - cantFabItemOp)) {
				} else {
					int cantConsumir = Math.abs(cantFab - cantFabItemOp);
					itemReporta.add(new ItemReporteConsumoDTO(lista.getid_materia_prima(),
							(double) (cantConsumir * reporte.getCantReportar())));
				}
			});
		} 
		itemReporta.add(
				new ItemReporteConsumoDTO(item.getCodigoPintura(), item.getPesoPintura() * reporte.getCantReportar()));
		List<Conector> consumoYTep = new ArrayList<>();

		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		String idCTErp = solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());
		String idRuta = "0" + data.getf120_id();
		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);
		if (!itemReporta.isEmpty()) {
			ConsumosdesdeCompromisosConsumos encabezadoConsumo = conectorConsumoService.crearEncabezadoConsumo(data);
			consumoYTep.add(encabezadoConsumo);
			Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
			itemReporta.forEach(i -> {
				ItemInterface itemFab = itemOpService.obtenerItemFabricaPorId(idItem);
				Integer codErpMateriaPrima = solicitudMateriaPrimaService.obtenerCodErpPorLote(reporte.getLote());
				List<ConsumosdesdeCompromisosMovtoInventarioV4> movsConsumo = conectorConsumoService.crearDetalleConsumo(itemFab, data,
						reporte, i, codErpMateriaPrima);
				consumoYTep.addAll(movsConsumo);
			});

		}

		DoctoTEPDocumentosVersion01 encabezadoTep = conectorTepService.crearEncabezadoTEP(item, reporte, idCTErp);
		consumoYTep.add(encabezadoTep);

		List<DoctoTEPMovimientosVersion01> movsTep = conectorTepService.crearMovTiempos(reporte, data, dataTE, idCTErp, false);
		consumoYTep.addAll(movsTep);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime now = LocalDateTime.now();			        
		String dateTime = now.format(formatter);
		String responseConsumoYTep = xmlService.postImportarXML(consumoYTep);
		System.out.println(responseConsumoYTep);
		util.guardarRegistroXml(xmlService.crearPlanoXml(consumoYTep), "SCP_TEP-OP_" + reporte.getNumOp() + "_" + dateTime);
		util.crearArchivoPlano(consumoYTep, "SCP_TEP-OP_" + reporte.getNumOp() + "_" + dateTime , configService.getCIA());

		List<Conector> entregaXml = new ArrayList<>();
		DoctoentregasDocumentosVersión02 encabezado = conectorEntregaService.crearEncabezadoEntrega();

		entregaXml.add(encabezado);

		DoctoentregasMovimientosVersión01 mov = conectorEntregaService.crearMovsEntrega(data, item, reporte);

		entregaXml.add(mov);
		now = LocalDateTime.now();			        
		String responseEntrega = xmlService.postImportarXML(entregaXml);
		util.crearArchivoPlano(entregaXml, "EP" + reporte.getNumOp() + "_" + dateTime, configService.getCIA());
		util.guardarRegistroXml(xmlService.crearPlanoXml(entregaXml), "EP-OP_" + reporte.getNumOp() + "_" + dateTime);
		if (!responseEntrega.equals(RESPUESTA_OK)) {
			return responseEntrega;
		}

		return "Entrega creada Exitosamente";
	}
}
