package com.almatec.controlpiso.erp.webservices.services.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasDocumentosVersión02;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasMovimientosVersión01;
import com.almatec.controlpiso.erp.webservices.services.ConectorEntregaService;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorEntregaServiceImpl implements ConectorEntregaService {

	private final ConfigurationService configService;
	private final UtilitiesApp util;
	
	public ConectorEntregaServiceImpl(ConfigurationService configService, UtilitiesApp util) {
		super();
		this.configService = configService;
		this.util = util;
	}

	@Override
	public DoctoentregasDocumentosVersión02 crearEncabezadoEntrega() {
		DoctoentregasDocumentosVersión02 encabezado = new DoctoentregasDocumentosVersión02();
		encabezado.setF_cia(configService.getCIA());
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF350_id_co(configService.getC_O());
		encabezado.setF350_id_tipo_docto(configService.getTIPO_DOC_ENTREGA());
		encabezado.setF350_fecha(util.obtenerFechaFormateada());
		encabezado.setF350_ind_estado(1);
		encabezado.setF350_ind_impresión(0);
		encabezado.setF350_id_clase_docto(720);
		encabezado.setF350_notas("Creado por integrapps");
		return encabezado;
	}

	@Override
	public DoctoentregasMovimientosVersión01 crearMovsEntrega(DataConsumoInterface data, ItemOp item,
			ReporteDTO reporte) {
		DoctoentregasMovimientosVersión01 mov = new DoctoentregasMovimientosVersión01();
		mov.setF_cia(configService.getCIA());
		mov.setF470_id_co(configService.getC_O());
		mov.setF470_id_tipo_docto(configService.getTIPO_DOC_ENTREGA());
		mov.setF_numero_reg(data.getf851_rowid()); // rowId del item de la op 851
		mov.setF850_id_tipo_docto(configService.getTIPO_DOC_OP_HIJO());
		mov.setF850_consec_docto(data.getf850_consec_docto());
		mov.setF470_id_item(data.getf120_id());
		mov.setF470_id_bodega(configService.getBODEGA_ENTREGA_ITEM_HIJO());
		mov.setF470_id_concepto(701);
		mov.setF470_id_motivo_entrega(configService.getMOTIVO_ENTREGA());
		mov.setF470_id_co_movto(configService.getC_O());
		mov.setF470_id_un_movto("001");
		Double cantBase = item.getPeso().multiply(new BigDecimal(reporte.getCantReportar())).doubleValue();
		mov.setF470_cant_base_entrega(cantBase);
		return mov;
	}

}
