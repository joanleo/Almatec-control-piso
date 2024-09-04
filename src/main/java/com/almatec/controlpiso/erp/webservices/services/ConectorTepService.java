package com.almatec.controlpiso.erp.webservices.services;

import java.util.List;

import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.interfaces.DataTEP;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoTEPMovimientosVersion01;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;

public interface ConectorTepService {
	
	DoctoTEPDocumentosVersion01 crearEncabezadoTEP(ItemOp item, ReporteDTO reporte, String idCTErp);
	
	List<DoctoTEPMovimientosVersion01> crearMovTiempos(ReporteDTO reporte, DataConsumoInterface data,
			DataTEP dataTE, String idCTErp, boolean tepFaltante);

}
