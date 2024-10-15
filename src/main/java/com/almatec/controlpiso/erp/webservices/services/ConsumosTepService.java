package com.almatec.controlpiso.erp.webservices.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.interfaces.DataTEP;
import com.almatec.controlpiso.erp.services.ListaMaterialService;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosConsumos;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosMovtoInventarioV4;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPMovimientosVersion01;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.services.ItemOpService;

@Service
public class ConsumosTepService {
	
	private final ListaMaterialService listaMaterialService;
	private final SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	private final ItemOpService itemOpService;
	private final ConectorConsumoService conectorConsumoService;
	private final ConectorTepService conectorTepService;
	
	
	public ConsumosTepService(ListaMaterialService listaMaterialService,
			SolicitudMateriaPrimaService solicitudMateriaPrimaService, ItemOpService itemOpService,
			ConectorConsumoService conectorConsumoService, ConectorTepService conectorTepService) {
		super();
		this.listaMaterialService = listaMaterialService;
		this.solicitudMateriaPrimaService = solicitudMateriaPrimaService;
		this.itemOpService = itemOpService;
		this.conectorConsumoService = conectorConsumoService;
		this.conectorTepService = conectorTepService;
	}

	public List<Conector> crearTEPYConsumos(ItemOp item, ReporteDTO reporte) {
		List<Conector> tepYConsumosXml = new ArrayList<>();
		
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		String idRuta = "0" + data.getf120_id();
		String idCTErp = solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());

		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);

		ConsumosdesdeCompromisosConsumos encabezadoConsumo = conectorConsumoService.crearEncabezadoConsumo(data);		
		tepYConsumosXml.add(encabezadoConsumo);
		Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
		ItemInterface itemFab = itemOpService.obtenerItemFabricaPorId(idItem);
		
		Integer codErpMateriaPrima = solicitudMateriaPrimaService.obtenerCodErpPorLote(reporte.getLote());
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movsConsumo = conectorConsumoService.crearDetalleConsumo(
				itemFab, data, reporte, null, codErpMateriaPrima);		
		tepYConsumosXml.addAll(movsConsumo);

		DoctoTEPDocumentosVersion01 encabezadoTep = conectorTepService.crearEncabezadoTEP(item,reporte, idCTErp);
		tepYConsumosXml.add(encabezadoTep);
		
		List<DoctoTEPMovimientosVersion01> movsTep = conectorTepService.crearMovTiempos(reporte, data, dataTE, idCTErp, false);
		tepYConsumosXml.addAll(movsTep);
		return tepYConsumosXml;
	}

	public List<Conector> crearTEP(ItemOp item, ReporteDTO reporte, boolean tepFaltante) {
		List<Conector> tepXml = new ArrayList<>();
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		String idRuta = "0" + data.getf120_id();
		String idCTErp = tepFaltante ? reporte.getIdCentroTrabajo().toString() : solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());
		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);
		DoctoTEPDocumentosVersion01 encabezado = conectorTepService.crearEncabezadoTEP(item, reporte, idCTErp);
		tepXml.add(encabezado);

		List<DoctoTEPMovimientosVersion01> movs = conectorTepService.crearMovTiempos(reporte, data, dataTE, idCTErp, tepFaltante);
		tepXml.addAll(movs);
		
		return tepXml;

	}	

}
