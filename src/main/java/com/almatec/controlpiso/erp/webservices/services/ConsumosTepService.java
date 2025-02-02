package com.almatec.controlpiso.erp.webservices.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.almatec.controlpiso.integrapps.interfaces.ItemListaMateriaInterface;
import com.almatec.controlpiso.integrapps.services.ItemOpService;

@Service
public class ConsumosTepService {
	
	private final ListaMaterialService listaMaterialService;
	private final SolicitudMateriaPrimaService solicitudMateriaPrimaService;
	private final ItemOpService itemOpService;
	private final ConectorConsumoService conectorConsumoService;
	private final ConectorTepService conectorTepService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
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

	public List<Conector> crearTEPYConsumos(ItemOp itemOp, ReporteDTO reporte, Boolean platinas) {
		List<Conector> tepYConsumosXml = new ArrayList<>();
		//Datos del item
		DataConsumoInterface data = listaMaterialService.obtenerDataParaConsumo(itemOp.getIdOpIntegrapps());
		String idRuta = "0" + data.getf120_id();
		
		String idCTErp = solicitudMateriaPrimaService.obtenerIdctErp(reporte.getIdCentroTrabajo());
		
		//Datos para tep
		DataTEP dataTE = listaMaterialService.obtenerDataTEP(idRuta, idCTErp);
		
		log.info("Creando conector encabezado consumo");
		ConsumosdesdeCompromisosConsumos encabezadoConsumo = conectorConsumoService.crearEncabezadoConsumo(data);		
		tepYConsumosXml.add(encabezadoConsumo);
		
		Integer idItemReportar = reporte.getIdItemFab() != 0 ? reporte.getIdItemFab() : reporte.getIdParte();
		ItemInterface itemFab = itemOpService.obtenerItemFabricaPorId(idItemReportar);
		
		Integer codErpMateriaPrima = 0;
		if(Boolean.FALSE.equals(platinas)) {
			codErpMateriaPrima = solicitudMateriaPrimaService.obtenerCodErpPorLote(reporte.getLote());			
		}else {
			System.out.println("Buscando codigo erp");
			System.out.println("id item reportar: " + idItemReportar);
			System.out.println(itemOp);
			List<ItemListaMateriaInterface> materiaPrima = itemOpService.obtenerListaMaterialesItemPorIdItem(itemOp.getId().intValue(), itemOp.getIdItemFab());
			codErpMateriaPrima = materiaPrima.stream()
			                    .filter(itemM -> {
			                    	if("CONJUNTO".equals(itemM.getitem_op_tipo())) {
			                    		return Objects.equals(itemM.getid_parte(), idItemReportar);
			                    	}
			                    	return Objects.equals(itemM.getid_item_fab(), idItemReportar);
			                    	
			                    })
			                    .map(ItemListaMateriaInterface::getid_materia_prima)
			                    .findFirst()
			                    .orElse(0);  // En caso de no encontrar coincidencia
						}
		
		if (codErpMateriaPrima == 0) {
	        log.error("No se encontró código ERP de materia prima válido");
	        throw new RuntimeException("No se pudo obtener el código ERP de materia prima");
	    }

	    log.info("Código ERP de materia prima obtenido: {}", codErpMateriaPrima);
	    
		log.info("Creando conector movimientos consumo");
		
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movsConsumo = conectorConsumoService.crearDetalleConsumo(
				itemFab, data, reporte, null, codErpMateriaPrima);		
		tepYConsumosXml.addAll(movsConsumo);

		log.info("Creando conector encabezado tep");
		DoctoTEPDocumentosVersion01 encabezadoTep = conectorTepService.crearEncabezadoTEP(itemOp,reporte, idCTErp);
		tepYConsumosXml.add(encabezadoTep);
		
		log.info("creando conector movimientos tep");
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
