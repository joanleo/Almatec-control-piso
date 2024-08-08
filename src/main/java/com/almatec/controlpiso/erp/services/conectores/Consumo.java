package com.almatec.controlpiso.erp.services.conectores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.erp.dto.ItemReporteConsumoDTO;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosConsumos;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosMovtoInventarioV4;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class Consumo {
	
	private final SolicitudMateriaPrimaService solicitudMateriaPrimaService;	
	private final UtilitiesApp util;
	private final XmlService xmlService;
	
	public Consumo(SolicitudMateriaPrimaService solicitudMateriaPrimaService, UtilitiesApp util, XmlService xmlService) {
		super();
		this.solicitudMateriaPrimaService = solicitudMateriaPrimaService;
		this.util = util;
		this.xmlService = xmlService;
	}

	public ConsumosdesdeCompromisosConsumos crearEncabezadoConsumo(DataConsumoInterface data) {
		try {
			ConsumosdesdeCompromisosConsumos encabezado = new ConsumosdesdeCompromisosConsumos();
			encabezado.setF_cia(xmlService.getCIA());
			encabezado.setF_consec_auto_reg(1);
			encabezado.setF350_id_co(xmlService.getC_O());
			encabezado.setF350_id_tipo_docto(xmlService.getTIPO_DOC_CONSUMO());
			encabezado.setF350_consec_docto(0);
			encabezado.setF350_id_fecha(util.obtenerFechaFormateada());
			encabezado.setF350_ind_estado(1);
			encabezado.setF350_ind_impresión(0);
			encabezado.setF350_id_clase_docto(710);
			encabezado.setF350_id_motivo(xmlService.getMOTIVO_CONSUMO());
			encabezado.setF850_tipo_docto(xmlService.getTIPO_DOC_OP_HIJO());
			encabezado.setF850_consec_docto(data.getf850_consec_docto());//Revisar  consecutivo op 850
			return encabezado;
		} catch (Exception e) {
	        e.printStackTrace(); // o registra la excepción con un logger
	    }
		return null;
	}
	
	public List<ConsumosdesdeCompromisosMovtoInventarioV4> crearDetalleConsumo(ItemInterface itemFab, DataConsumoInterface data, 
			ReporteDTO reporte, ItemReporteConsumoDTO itemReporteDTO) {
		System.out.println("Creando movimientos consumo");
		List<ConsumosdesdeCompromisosMovtoInventarioV4> movs = new ArrayList<>();
		ConsumosdesdeCompromisosMovtoInventarioV4 mov = new ConsumosdesdeCompromisosMovtoInventarioV4();
		mov.setF_cia(xmlService.getCIA());
		mov.setF470_id_co(xmlService.getC_O());
		mov.setF470_id_tipo_docto(xmlService.getTIPO_DOC_CONSUMO());
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
		}else {
			mov.setF470_id_item_comp(itemReporteDTO.getId());
			mov.setF470_cant_base(itemReporteDTO.getCant());
		}
		if(mov.getF470_id_lote().isBlank()) mov.setF470_id_lote(reporte.getLote());//revisar item lista materiales			
		mov.setF470_id_bodega(xmlService.getBODEGA_ENTREGA_TRANSFERENCIA());//revisar		
		mov.setF470_id_concepto(701);
		mov.setF470_id_motivo(xmlService.getMOTIVO_CONSUMO());
		mov.setF470_id_co_movto(xmlService.getC_O());
		mov.setF470_id_un_movto("001");
		mov.setF470_id_unidad_medida(data.getf120_id_unidad_inventario());
				
		movs.add(mov);
		
		return movs;
	}

}
