package com.almatec.controlpiso.erp.webservices.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.dto.RutaDTO;
import com.almatec.controlpiso.erp.webservices.generated.ItemsParametrosVersion5;
import com.almatec.controlpiso.erp.webservices.generated.ItemsVersion05;
import com.almatec.controlpiso.erp.webservices.generated.RutasRutasV01;
import com.almatec.controlpiso.erp.webservices.generated.RutasoperacionesOperacionesV01;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.erp.webservices.services.ConectorService;
import com.almatec.controlpiso.erp.webservices.services.ConectorItemService;
import com.almatec.controlpiso.erp.webservices.services.ConectorParametrosService;
import com.almatec.controlpiso.erp.webservices.services.ConectorRutaService;
import com.almatec.controlpiso.erp.webservices.services.ConectorRutasOperacionesService;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConectorServiceImpl implements ConectorService {
	
	private final OrdenPvService ordenPvService;
	private final ItemOpService itemOpService;
	private final ConectorItemService conectorItemService;
	private final ConectorRutaService conectorRutaService;
	private final ConectorRutasOperacionesService conectorRutasOperacionesService;
	private final ConectorParametrosService conectorParametrosService;
	
	

	public ConectorServiceImpl(OrdenPvService ordenPvService, ItemOpService itemOpService,
			ConectorItemService conectorItemService,
			ConectorRutaService conectorRutaService, ConectorRutasOperacionesService conectorRutasOperacionesService,
			ConectorParametrosService conectorParametrosService) {
		super();
		this.ordenPvService = ordenPvService;
		this.itemOpService = itemOpService;
		this.conectorItemService = conectorItemService;
		this.conectorRutaService = conectorRutaService;
		this.conectorRutasOperacionesService = conectorRutasOperacionesService;
		this.conectorParametrosService = conectorParametrosService;
	}

	@Override
	public ItemsVersion05 crearConectorItem(Integer idOPI) {
		VistaOrdenPv ordenIntegrapps = ordenPvService.obtenerOrdenPorId(idOPI);
		ItemsVersion05 item = conectorItemService.crearConectorItemEntrega(ordenIntegrapps);
		return item;
	}

	@Override
	public RutasRutasV01 crearConectorRuta(ItemsVersion05 item) {
		RutasRutasV01 ruta = conectorRutaService.crearConectorRuta(item);
		return ruta;
	}

	@Override
	public List<Conector> crearConectorRutasOperaciones(Integer idOPI, RutasRutasV01 ruta) {
		String jsonStringRuta = itemOpService.obtenerStringPorIdOPIntegrappsYTipo(idOPI, "RUTA");
		ObjectMapper objectMapper = new ObjectMapper();
		List<RutaDTO> listaRutaDTO = new ArrayList<>();
		try {
			listaRutaDTO = objectMapper.readValue(jsonStringRuta, new TypeReference<List<RutaDTO>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Conector> operacionesXml = new ArrayList<>();
		List<RutasoperacionesOperacionesV01> rutaOperaciones = conectorRutasOperacionesService.crearConectorRutaOperaciones(listaRutaDTO,
				ruta.getF808_id());
		operacionesXml.addAll(rutaOperaciones);

		return operacionesXml;
	}

	@Override
	public List<Conector> crearConectorParametros(String idRuta, Integer idItem) {
		List<Conector> parametrosXml = new ArrayList<>();
		ItemsParametrosVersion5 ruta = conectorParametrosService.crearParametros(idRuta, idItem);
		parametrosXml.add(ruta);
		return parametrosXml;
	}

}
