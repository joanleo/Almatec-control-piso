package com.almatec.controlpiso.erp.services.conectores;

import java.io.IOException;
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
public class AdministraConectores {
	
	private final Consumo consumo;
	private final ListaMaterialService listaMaterialService;
	private final SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	private final ItemOpService itemOpService;
	private final Tep tep;	
	

	public AdministraConectores(Consumo consumo, ListaMaterialService listaMaterialService, Tep tep,
			SolicitudMateriaPrimaService solicitudMateriaPrimaService,ItemOpService itemOpService) {
		super();
		this.consumo = consumo;
		this.listaMaterialService = listaMaterialService;
		this.solicitudMateriaPrimaService = solicitudMateriaPrimaService;
		this.itemOpService = itemOpService;
		this.tep = tep;
	}



	public List<Conector> crearTEPYConsumos(ItemOp item, ReporteDTO reporte) {
		List<Conector> tepYConsumosXml = new ArrayList<>();
		
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		String idRuta = "0" + data.getf120_id();
		String idCTErp = solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());
		System.out.println("Ruta: "+idRuta+" Centro trabajo: "+idCTErp);
		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);

		ConsumosdesdeCompromisosConsumos encabezadoConsumo = consumo.crearEncabezadoConsumo(data);		
		tepYConsumosXml.add(encabezadoConsumo);
		Integer idItem = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
		ItemInterface itemFab = itemOpService.obtenerItemFabricaPorId(idItem);
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movsConsumo = consumo.crearDetalleConsumo(itemFab, data, reporte, null);		
		tepYConsumosXml.addAll(movsConsumo);

		DoctoTEPDocumentosVersion01 encabezadoTep = tep.crearEncabezadoTEP(item,reporte, idCTErp);
		tepYConsumosXml.add(encabezadoTep);
		
		List<DoctoTEPMovimientosVersion01> movsTep = tep.crearMovTiempos(reporte, data, dataTE, idCTErp, false);
		tepYConsumosXml.addAll(movsTep);
		return tepYConsumosXml;
	}



	public List<Conector> crearTEP(ItemOp item, ReporteDTO reporte, boolean tepFaltante) throws IOException {
		List<Conector> tepXml = new ArrayList<>();
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(item.getIdOpIntegrapps());
		String idRuta = "0" + data.getf120_id();
		String idCTErp = tepFaltante ? reporte.getIdCentroTrabajo().toString() : solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());
		System.out.println("Ruta: " + idRuta + " Centro trabajo: " + idCTErp);
		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);
		System.out.println(dataTE.getf809_numero_operacion());
		DoctoTEPDocumentosVersion01 encabezado = tep.crearEncabezadoTEP(item, reporte, idCTErp);

		tepXml.add(encabezado);

		List<DoctoTEPMovimientosVersion01> movs = tep.crearMovTiempos(reporte, data, dataTE, idCTErp, tepFaltante);
		tepXml.addAll(movs);
		
		return tepXml;

	}

}
