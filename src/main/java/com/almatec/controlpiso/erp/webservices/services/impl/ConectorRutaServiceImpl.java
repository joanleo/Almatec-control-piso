package com.almatec.controlpiso.erp.webservices.services.impl;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.ItemsVersion05;
import com.almatec.controlpiso.erp.webservices.generated.RutasRutasV01;
import com.almatec.controlpiso.erp.webservices.services.ConectorRutaService;

@Service
public class ConectorRutaServiceImpl implements ConectorRutaService {
	
	private final ConfigurationService configService;

	public ConectorRutaServiceImpl(ConfigurationService configService) {
		super();
		this.configService = configService;
	}

	@Override
	public RutasRutasV01 crearConectorRuta(ItemsVersion05 item) {
		RutasRutasV01 ruta = new RutasRutasV01();
		ruta.setF_cia(configService.getCIA());
		ruta.setF_actualiza_reg(0);
		ruta.setF808_id(item.getF120_id());
		ruta.setF808_id_instalacion(configService.getINSTALACION());
		ruta.setF808_descripcion(item.getF120_descripcion());
		ruta.setF808_ind_estado(1);
		ruta.setF808_ind_controla_sec_piso(0);

		return ruta;
	}

}
