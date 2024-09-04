package com.almatec.controlpiso.erp.webservices.services.impl;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.ItemsParametrosVersion5;
import com.almatec.controlpiso.erp.webservices.services.ConectorParametrosService;

@Service
public class ConectorParametrosServiceImpl implements ConectorParametrosService{

	private final ConfigurationService configService;
	
	public ConectorParametrosServiceImpl(ConfigurationService configService) {
		super();
		this.configService = configService;
	}

	@Override
	public ItemsParametrosVersion5 crearParametros(String idRuta, Integer idItem) {
		ItemsParametrosVersion5 parametros = new ItemsParametrosVersion5();
		parametros.setF_cia(configService.getCIA());
		parametros.setF_actualiza_reg(1);
		parametros.setF132_id_instalacion(configService.getINSTALACION());
		parametros.setF132_abc_rotacion_veces("A");
		parametros.setF132_abc_rotacion_costo("A");
		parametros.setF132_id_um_venta_suge("KG");
		parametros.setF132_mf_ind_mps(0);
		parametros.setF132_mf_porc_rendimiento(100.0);
		parametros.setF132_mf_ind_tipo_orden(1);
		parametros.setF132_mf_ind_politica_orden(1);

		parametros.setF132_mf_id_ruta(idRuta);
		parametros.setF132_mf_id_bodega_asigna(configService.getBODEGA_ENTREGA_ITEM_HIJO());
		parametros.setF132_mf_ind_generar_orden_prod(1);
		parametros.setF132_mf_ind_item_critico(1);
		parametros.setF120_id(idItem);
		parametros.setF132_mf_ind_genera_ord_pln(0);

		return parametros;
	}

}
