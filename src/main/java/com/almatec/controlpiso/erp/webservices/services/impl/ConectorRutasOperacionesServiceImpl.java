package com.almatec.controlpiso.erp.webservices.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.dto.RutaDTO;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.RutasoperacionesOperacionesV01;
import com.almatec.controlpiso.erp.webservices.services.ConectorRutasOperacionesService;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class ConectorRutasOperacionesServiceImpl implements ConectorRutasOperacionesService {

	private final ConfigurationService configService;
	private final UtilitiesApp util;
	
	public ConectorRutasOperacionesServiceImpl(ConfigurationService configService, UtilitiesApp util) {
		super();
		this.configService = configService;
		this.util = util;
	}
	
	@Override
	public List<RutasoperacionesOperacionesV01> crearConectorRutaOperaciones(List<RutaDTO> rutas, String id) {
		List<RutasoperacionesOperacionesV01> listaCrear = new ArrayList<>();
		for (RutaDTO item : rutas) {
			RutasoperacionesOperacionesV01 crear = new RutasoperacionesOperacionesV01();
			crear.setF_cia(configService.getCIA());
			crear.setF_actualiza_reg(0);
			crear.setF808_id(id);
			crear.setF808_id_instalacion(configService.getINSTALACION());
			crear.setF809_id_metodo(configService.getMETODO());
			crear.setF809_numero_operacion(item.getF809_numero_operacion());
			crear.setF809_ind_estado(1);
			crear.setF809_descripcion(item.getF809_descripcion());
			crear.setF809_ind_operacion_externa(0);
			crear.setF809_id_ctrabajo(item.getF809_id_ctrabajo());
			crear.setF809_cantidad_base(item.getF809_cantidad_base());
			crear.setF809_horas_maquina(item.getF809_horas_maquina());
			crear.setF809_fecha_activacion(util.obtenerFechaFormateada());
			crear.setF809_num_operarios_alistamiento("001");
			crear.setF809_num_operarios_ejecucion("001");
			crear.setF809_unidades_equivalentes(1.0);
			listaCrear.add(crear);
		}
		return listaCrear;
	}

}
