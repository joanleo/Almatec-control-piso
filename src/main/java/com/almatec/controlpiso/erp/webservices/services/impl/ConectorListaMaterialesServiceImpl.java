package com.almatec.controlpiso.erp.webservices.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.dto.ListaMaterialesDTO;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.ListadematerialesListadematerialesv4;
import com.almatec.controlpiso.erp.webservices.services.ConectorListaMaterialesService;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorListaMaterialesServiceImpl implements ConectorListaMaterialesService {
	
	private final ConfigurationService configService;
	private final UtilitiesApp util;

	public ConectorListaMaterialesServiceImpl(ConfigurationService configService, UtilitiesApp util) {
		super();
		this.configService = configService;
		this.util = util;
	}

	@Override
	public List<ListadematerialesListadematerialesv4> crearConectorLM(List<ListaMaterialesDTO> listaMateriales,
			Integer idItem) {
		List<ListadematerialesListadematerialesv4> listaCrear = new ArrayList<>();
		for (ListaMaterialesDTO item : listaMateriales) {
			ListadematerialesListadematerialesv4 crear = new ListadematerialesListadematerialesv4();
			crear.setF_cia(configService.getCIA());
			crear.setF820_id(idItem);
			crear.setF820_id_instalacion(configService.getINSTALACION());
			crear.setF820_id_metodo(configService.getMETODO());
			crear.setF820_secuencia(item.getF820_secuencia());
			crear.setF820_id_hijo(item.getF820_id_hijo());
			crear.setF820_cant_base(item.getF820_cant_base());
			crear.setF820_cant_requerida(item.getF820_cant_requerida());
			crear.setF820_fecha_activacion(util.obtenerFechaFormateada());
			crear.setF820_codigo_uso(0);
			crear.setF820_ind_no_costea(0);
			crear.setF820_id_bodega(item.getF820_id_bodega());

			listaCrear.add(crear);
		}
		return listaCrear;
	}

}
