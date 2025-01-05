package com.almatec.controlpiso.erp.webservices.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.dto.ItemReporteConsumoDTO;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosConsumos;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosMovtoInventarioV4;
import com.almatec.controlpiso.erp.webservices.services.ConectorConsumoService;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorConsumoServiceImpl implements ConectorConsumoService {
	
	private final ConfigurationService configService;
	private final UtilitiesApp util;

	public ConectorConsumoServiceImpl(ConfigurationService configService, UtilitiesApp util) {
		super();
		this.configService = configService;
		this.util = util;
	}

	@Override
	public ConsumosdesdeCompromisosConsumos crearEncabezadoConsumo(DataConsumoInterface data) {
		try {
			ConsumosdesdeCompromisosConsumos encabezado = new ConsumosdesdeCompromisosConsumos();
			encabezado.setF_cia(configService.getCIA());
			encabezado.setF_consec_auto_reg(1);
			encabezado.setF350_id_co(configService.getC_O());
			encabezado.setF350_id_tipo_docto(configService.getTIPO_DOC_CONSUMO());
			encabezado.setF350_consec_docto(0);
			encabezado.setF350_id_fecha(util.obtenerFechaFormateada());
			encabezado.setF350_ind_estado(1);
			encabezado.setF350_ind_impresión(0);
			encabezado.setF350_id_clase_docto(710);
			encabezado.setF350_id_motivo(configService.getMOTIVO_CONSUMO());
			encabezado.setF850_tipo_docto(configService.getTIPO_DOC_OP_HIJO());
			encabezado.setF850_consec_docto(data.getf850_consec_docto());//Revisar  consecutivo op 850
			return encabezado;
		} catch (Exception e) {
	        e.printStackTrace(); // o registra la excepción con un logger
	    }
		return null;
	}

	@Override
	public List<ConsumosdesdeCompromisosMovtoInventarioV4> crearDetalleConsumo(ItemInterface itemFab,
			DataConsumoInterface data, ReporteDTO reporte, ItemReporteConsumoDTO itemReporteDTO,
			Integer codErpMateriaPrima) {

		List<ConsumosdesdeCompromisosMovtoInventarioV4> movs = new ArrayList<>();
		ConsumosdesdeCompromisosMovtoInventarioV4 mov = new ConsumosdesdeCompromisosMovtoInventarioV4();
		mov.setF_cia(configService.getCIA());
		mov.setF470_id_co(configService.getC_O());
		mov.setF470_id_tipo_docto(configService.getTIPO_DOC_CONSUMO());
		mov.setF470_consec_docto(0);
		mov.setF470_nro_registro(data.getf851_rowid());//data.getf851_rowid()revisar Rowid item padre, item de la op de etrega o hijo 851
		mov.setF470_id_item_padre(data.getf120_id());//revisar id item padre, item de la op de etrega o hijo 120
		if(itemReporteDTO == null) {
			mov.setF470_id_item_comp(codErpMateriaPrima);//revisar id item lista materiales **************************			
			BigDecimal cantConsumir = itemFab.getitem_peso_b().multiply(new BigDecimal(reporte.getCantReportar()));
			mov.setF470_cant_base(cantConsumir.doubleValue());
		}else {
			mov.setF470_id_item_comp(itemReporteDTO.getId());
			mov.setF470_cant_base(itemReporteDTO.getCant());
			mov.setF470_notas("Creado desde Guayacan, consumo de para: " + itemFab.getitem_desc() + " Cant: " + itemReporteDTO.getCant() + " und(s)." );
		}
		if(mov.getF470_id_lote().isBlank()) {
			String loteReporte = reporte.getLote();
			if (mov.getF470_id_lote().isBlank() && 
			    loteReporte != null && 
			    !loteReporte.trim().isEmpty()) {
			    mov.setF470_id_lote(loteReporte);
			}			
		}
		mov.setF470_id_bodega(configService.getBODEGA_ENTREGA_TRANSFERENCIA());//revisar		
		mov.setF470_id_concepto(701);
		mov.setF470_id_motivo(configService.getMOTIVO_CONSUMO());
		mov.setF470_id_co_movto(configService.getC_O());
		mov.setF470_id_un_movto("001");
		mov.setF470_id_unidad_medida(data.getf120_id_unidad_inventario());
		mov.setF470_notas("Creado desde Guayacan, consumo de para: " + itemFab.getitem_desc() + " Cant: " + reporte.getCantReportar() + " und(s)." );	
				
		movs.add(mov);
		
		return movs;
	}

}
