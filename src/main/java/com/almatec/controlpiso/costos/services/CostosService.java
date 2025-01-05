package com.almatec.controlpiso.costos.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.XmlService;
import com.almatec.controlpiso.erp.webservices.generated.OrdenesdeproduccionOperacionesVersion01;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.erp.webservices.services.ConectorOpOperacionesService;
import com.almatec.controlpiso.integrapps.dtos.ResponseMessage;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;

@Service
public class CostosService {
	
	@Autowired
	private OrdenPvService ordenPvService;
	
	@Autowired
	private XmlService xmlService;
	
	@Autowired
	private ConectorOpOperacionesService conectorOpOperacionesService;
	
	static final String RESPUESTA_OK = "Operacion realizada exitosamente";
	
	private Logger log = LoggerFactory.getLogger(getClass());

	public ResponseMessage actualizarCantidadesEjecutarOp(Integer idOPI) {
		VistaOrdenPv ordenIntegrapps = ordenPvService.obtenerOrdenPorId(idOPI);
		Integer idItemOp = ordenPvService.obteneridItemOpPorIdOpIA(idOPI);
		List<Conector> opOperaciones = new ArrayList<>();
		List<OrdenesdeproduccionOperacionesVersion01> conectorOpOperaciones = conectorOpOperacionesService.crearConector(idOPI, ordenIntegrapps, idItemOp);
		opOperaciones.addAll(conectorOpOperaciones);
		String response = xmlService.postImportarXML(opOperaciones);

		if (!response.equals(RESPUESTA_OK)) {
			return new ResponseMessage(true, response);
		}
		
		
		log.info("Actualizacion de ruta ok. {}",response);
		return new ResponseMessage(false, response);
	}

}
