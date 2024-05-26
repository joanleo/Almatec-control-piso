package com.almatec.controlpiso.integrapps.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.DataItemImprimirDTO;
import com.almatec.controlpiso.integrapps.entities.Evento;
import com.almatec.controlpiso.integrapps.repositories.EventoRepository;
import com.almatec.controlpiso.integrapps.services.EventoService;
import com.almatec.controlpiso.utils.UtilitiesApp;

@Service
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	private EventoRepository eventoRepo;
	
	@Autowired
	private UtilitiesApp util;

	@Override
	public void crearEventoImpresionEtiquetas(DataItemImprimirDTO data) {
		Evento evento = new Evento();
		evento.setTipoEvento(data.getTipoEtiqueta());
		evento.setParam1(data.getIdItemFab().toString());
		evento.setParam2(data.getIdOp().toString());
		evento.setParam4(data.getCantEtiquetas());
		evento.setParam5(Integer.valueOf(util.obtenerFechaFormateada()));
		evento.setParam6(data.getIdItemOp());
		
		try {
			eventoRepo.save(evento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
