package com.almatec.controlpiso.erp.webservices.services;

import java.util.List;

import com.almatec.controlpiso.erp.dto.ListaMaterialesDTO;
import com.almatec.controlpiso.erp.webservices.generated.ManufacturaedicióndecostositemCostosV02;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;

public interface ConectorCostoStandarService {

	List<Conector> calcularCostoStandarItem(VistaItemPedidoErp item);
	List<ManufacturaedicióndecostositemCostosV02> creaConectoresEdicionCosto(String ref,
			List<ListaMaterialesDTO> listaMaterialesDTO);
	ManufacturaedicióndecostositemCostosV02 crearConectorCosto(String ref, Double costoCalculado, int segmento,
			Double cantBase);
}
