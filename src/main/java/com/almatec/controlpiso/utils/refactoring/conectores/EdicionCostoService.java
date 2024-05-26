package com.almatec.controlpiso.utils.refactoring.conectores;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.generated.ManufacturaedicióndecostositemCostosV02;
import com.almatec.controlpiso.utils.refactoring.ParametrosAppService;

@Service
public class EdicionCostoService {

	public ManufacturaedicióndecostositemCostosV02 crearConectorEdicionCosto(ParametrosAppService parametrosErp, String ref) {
		ManufacturaedicióndecostositemCostosV02 costo = new ManufacturaedicióndecostositemCostosV02();
		costo.setF_cia(parametrosErp.getCompania());
		costo.setF402_referencia_item(ref);
		costo.setF402_id_instalacion(parametrosErp.getInstalacion());
		costo.setF402_id_grupo_costo(2);
		costo.setF402_id_segmento_costo(901);
		costo.setF402_costo_este_nivel_uni(0.0001);
		return costo;
	}
}
