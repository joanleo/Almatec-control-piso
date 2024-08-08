package com.almatec.controlpiso.erp.services.conectores;

import java.math.BigDecimal;

import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasDocumentosVersión02;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasMovimientosVersión01;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.utils.UtilitiesApp;

public class Entrega {
	private final UtilitiesApp util;
	private final XmlService xmlService;
	
	public Entrega(UtilitiesApp util, XmlService xmlService) {
		super();
		this.util = util;
		this.xmlService = xmlService;
	}

	public DoctoentregasDocumentosVersión02 crearEncabezadoEntrega() {
		DoctoentregasDocumentosVersión02 encabezado = new DoctoentregasDocumentosVersión02();
		encabezado.setF_cia(xmlService.getCIA());
		encabezado.setF_consec_auto_reg(1);
		encabezado.setF350_id_co(xmlService.getC_O());
		encabezado.setF350_id_tipo_docto(xmlService.getTIPO_DOC_ENTREGA());
		encabezado.setF350_fecha(util.obtenerFechaFormateada());
		encabezado.setF350_ind_estado(1);
		encabezado.setF350_ind_impresión(0);
		encabezado.setF350_id_clase_docto(720);
		encabezado.setF350_notas("Creado por integrapps");
		return encabezado;
	}

	public DoctoentregasMovimientosVersión01 crearMovsEntrega(DataConsumoInterface data, ItemOp item,
			ReporteDTO reporte) {
		DoctoentregasMovimientosVersión01 mov = new DoctoentregasMovimientosVersión01();
		mov.setF_cia(xmlService.getCIA());
		mov.setF470_id_co(xmlService.getC_O());
		mov.setF470_id_tipo_docto(xmlService.getTIPO_DOC_ENTREGA());
		mov.setF_numero_reg(data.getf851_rowid()); // rowId del item de la op 851
		mov.setF850_id_tipo_docto(xmlService.getTIPO_DOC_OP_HIJO());
		mov.setF850_consec_docto(data.getf850_consec_docto());
		mov.setF470_id_item(data.getf120_id());
		mov.setF470_id_bodega(xmlService.getBODEGA_ENTREGA_ITEM_HIJO());
		mov.setF470_id_concepto(701);
		mov.setF470_id_motivo_entrega(xmlService.getMOTIVO_ENTREGA());
		mov.setF470_id_co_movto(xmlService.getC_O());
		mov.setF470_id_un_movto("001");
		Double cantBase = item.getPeso().multiply(new BigDecimal(reporte.getCantReportar())).doubleValue();
		mov.setF470_cant_base_entrega(cantBase);
		return mov;
	}

}
