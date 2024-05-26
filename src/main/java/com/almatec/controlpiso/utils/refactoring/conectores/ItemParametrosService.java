package com.almatec.controlpiso.utils.refactoring.conectores;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.generated.ItemsParametrosVersion5;
import com.almatec.controlpiso.utils.refactoring.ParametrosAppService;

@Service
public class ItemParametrosService {

	public ItemsParametrosVersion5 crearParametrosItem(ParametrosAppService parametrosErp, String ref, String bodega) {
		ItemsParametrosVersion5 parametrosItem = new ItemsParametrosVersion5();
		parametrosItem.setF_cia(parametrosErp.getCompania());
		parametrosItem.setF_actualiza_reg(1);
		parametrosItem.setF132_id_instalacion(parametrosErp.getInstalacion());
		parametrosItem.setF132_abc_rotacion_veces("A");
		parametrosItem.setF132_abc_rotacion_costo("A");
		parametrosItem.setF132_id_um_venta_suge("KG");
		parametrosItem.setF132_mf_ind_mps(0);
		parametrosItem.setF132_mf_porc_rendimiento(100.0);
		parametrosItem.setF132_mf_ind_tipo_orden(1);
		parametrosItem.setF132_mf_ind_politica_orden(1);		
		parametrosItem.setF132_mf_id_bodega_asigna(bodega);
		parametrosItem.setF132_mf_ind_generar_orden_prod(1);
		parametrosItem.setF132_mf_ind_item_critico(1);
		parametrosItem.setF120_referencia(ref);
		parametrosItem.setF132_mf_ind_genera_ord_pln(0);
		
		return parametrosItem;
	}
}
