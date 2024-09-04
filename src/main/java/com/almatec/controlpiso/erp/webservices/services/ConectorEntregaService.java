package com.almatec.controlpiso.erp.webservices.services;

import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasDocumentosVersión02;
import com.almatec.controlpiso.erp.webservices.generated.DoctoentregasMovimientosVersión01;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;

public interface ConectorEntregaService {
	
	DoctoentregasDocumentosVersión02 crearEncabezadoEntrega();
	
	DoctoentregasMovimientosVersión01 crearMovsEntrega(DataConsumoInterface data, ItemOp item,
			ReporteDTO reporte);

}
