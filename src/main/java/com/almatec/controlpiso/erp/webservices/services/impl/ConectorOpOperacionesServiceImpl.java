package com.almatec.controlpiso.erp.webservices.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.dto.RutaDTO;
import com.almatec.controlpiso.erp.webservices.ConfigurationService;
import com.almatec.controlpiso.erp.webservices.generated.OrdenesdeproduccionOperacionesVersion01;
import com.almatec.controlpiso.erp.webservices.services.ConectorOpOperacionesService;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.utils.UtilitiesApp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ConectorOpOperacionesServiceImpl implements ConectorOpOperacionesService {
	
	private final ItemOpService itemOpService;
	private final ConfigurationService configService;
	private final UtilitiesApp util;

	
	public ConectorOpOperacionesServiceImpl(ItemOpService itemOpService,
			ConfigurationService configService,
			UtilitiesApp util) {
		super();
		this.itemOpService = itemOpService;
		this.configService = configService;
		this.util = util;
	}


	@Override
	public List<OrdenesdeproduccionOperacionesVersion01> crearConector(Integer idOPI, VistaOrdenPv ordenIntegrapps, Integer idItemOp) {
		String jsonStringRuta = itemOpService.obtenerStringPorIdOPIntegrappsYTipo(idOPI, "RUTA");
		ObjectMapper objectMapper = new ObjectMapper();
		List<RutaDTO> listaRutaDTO = new ArrayList<>();
		try {
			listaRutaDTO = objectMapper.readValue(jsonStringRuta, new TypeReference<List<RutaDTO>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<OrdenesdeproduccionOperacionesVersion01> listaCrear = new ArrayList<>();
		
		for(RutaDTO ruta: listaRutaDTO) {
			OrdenesdeproduccionOperacionesVersion01 operacion = new OrdenesdeproduccionOperacionesVersion01();
			operacion.setF_cia(configService.getCIA());
			operacion.setF_actualiza_reg(1);
			operacion.setF850_id_tipo_docto_op(ordenIntegrapps.getTipoOp());
			operacion.setF850_consec_docto_op(ordenIntegrapps.getNumOp());
			operacion.setF865_id_item_op(idItemOp);
			operacion.setF865_numero_operacion(ruta.getF809_numero_operacion());
			operacion.setF865_id_centro_trabajo(ruta.getF809_id_ctrabajo());
			operacion.setF865_cant_ejecutar_base(ruta.getF809_horas_maquina());
			operacion.setF865_cant_base(ruta.getF809_cantidad_base());
			operacion.setF865_descripcion_operacion(ruta.getF809_descripcion());
			operacion.setF865_ind_operacion_externa(0);
			operacion.setF865_fecha_inicio(util.obtenerFechaFormateada(ordenIntegrapps.getFechaAProduccion()));
			operacion.setF865_fecha_terminacion(util.obtenerFechaFormateada(ordenIntegrapps.getFechaEntrega()));
			operacion.setF865_rt_horas_maquina(ruta.getF809_horas_maquina().intValue());
			operacion.setF850_id_co_op(configService.getC_O());
			operacion.setF865_rt_unidades_equivalentes(1.0);
			operacion.setF865_rt_numero_operarios(1);
			operacion.setF865_rt_numero_operarios_alis(1);

			listaCrear.add(operacion);
		}
		
		
		return listaCrear;
	}

}
