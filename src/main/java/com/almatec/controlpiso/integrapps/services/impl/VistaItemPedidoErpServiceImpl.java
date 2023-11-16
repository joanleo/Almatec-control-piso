package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.integrapps.repositories.VistaItemPedidoErpRepository;
import com.almatec.controlpiso.integrapps.services.VistaItemPedidoErpService;

@Service
public class VistaItemPedidoErpServiceImpl implements VistaItemPedidoErpService {
	
	@Autowired
	private VistaItemPedidoErpRepository vistaItemPedidoRepo;
	
	

	@Override
	public List<VistaItemPedidoErp> buscarItemsPedido(String idPedido) {
		return vistaItemPedidoRepo.findByTipoPedidoAndNoPedido("PV", idPedido);
	}



	@Override
	public List<VistaItemPedidoErp> buscarItemPedidoByReferencia(String noPedido, String referencia) {
		return vistaItemPedidoRepo.findByTipoPedidoAndNoPedidoAndReferencia("PV", noPedido, referencia);
	}



}
