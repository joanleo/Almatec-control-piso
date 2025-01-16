package com.almatec.controlpiso.almacen.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.dto.DetalleSolicitudDTO;
import com.almatec.controlpiso.almacen.service.DetalleSolicitudMateriaPrimaService;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.Parametro;
import com.almatec.controlpiso.integrapps.interfaces.DetalleSolicDesItemInterface;
import com.almatec.controlpiso.integrapps.repositories.DetalleSolicitudMateriaPrimaRepository;
import com.almatec.controlpiso.integrapps.services.ParametroService;

@Service
public class DetalleSolicitudMateriaPrimaServiceImpl implements DetalleSolicitudMateriaPrimaService {
	
	@Autowired
	private DetalleSolicitudMateriaPrimaRepository detalleSolicitudMateriaPrimaRepo;
	
	@Autowired
	private ParametroService parametroService;
	
	@Value("${schema.unoee}")
	private String schemaUnoee;

	@Override
	public List<DetalleSolicitudMateriaPrima> crearDetalleSolicitud(Integer numDoc, List<DetalleSolicitudMateriaPrima> detalles) {
		for(DetalleSolicitudMateriaPrima item: detalles) {
			item.setIdSolicitud(numDoc);
		}
		return detalleSolicitudMateriaPrimaRepo.saveAllAndFlush(detalles);
		
	}

	@Override
	public List<DetalleSolicitudDTO> obtenerDetalleDTOPorIdSolic(Integer idSolic) {
		List<DetalleSolicDesItemInterface> detallesInterface = detalleSolicitudMateriaPrimaRepo.obtenerInterfacePorIdSolicitud(schemaUnoee, idSolic);
		List<Parametro> parametros = parametroService.obtenerParametros();
		List<DetalleSolicitudDTO> detallesDTO = new ArrayList<>();

	    for (DetalleSolicDesItemInterface item : detallesInterface) {
	        String bodegaOrigen = "";
	        String bodegaDestino = "";

	        for (Parametro parametro : parametros) {
	            if (parametro.getNombre().contains("bodega de material")) {
	                bodegaOrigen = parametro.getValor();
	            }

	            if (parametro.getNombre().contains("bodega entrega transferencia")) {
	                bodegaDestino = parametro.getValor();
	            }
	        }

	        DetalleSolicitudDTO detalle = new DetalleSolicitudDTO(item);
	        detalle.setBodegaOrigen(bodegaOrigen);
	        detalle.setBodegaDestino(bodegaDestino);
	        detallesDTO.add(detalle);
	    }
		
		return detallesDTO;
	}

	@Override
	public List<DetalleSolicitudMateriaPrima> obtenerDetallePorIdSol(Integer idSol) {
		return detalleSolicitudMateriaPrimaRepo.findByIdSolicitud(idSol);
	}

	@Override
	public List<DetalleSolicitudMateriaPrima> actualizarDetalleSol(List<DetalleSolicitudMateriaPrima> detalleSol) {
		return detalleSolicitudMateriaPrimaRepo.saveAllAndFlush(detalleSol);		
	}

}
