package com.almatec.controlpiso.erp.webservices.services;

import java.util.List;

import com.almatec.controlpiso.erp.dto.ItemReporteConsumoDTO;
import com.almatec.controlpiso.erp.interfaces.DataConsumoInterface;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosConsumos;
import com.almatec.controlpiso.erp.webservices.generated.ConsumosdesdeCompromisosMovtoInventarioV4;
import com.almatec.controlpiso.integrapps.dtos.ReporteDTO;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;

public interface ConectorConsumoService {

	ConsumosdesdeCompromisosConsumos crearEncabezadoConsumo(DataConsumoInterface data);
	
	List<ConsumosdesdeCompromisosMovtoInventarioV4> crearDetalleConsumo(ItemInterface itemFab, DataConsumoInterface data, 
			ReporteDTO reporte, ItemReporteConsumoDTO itemReporteDTO,
			Integer codErpMateriaPrima);
}
