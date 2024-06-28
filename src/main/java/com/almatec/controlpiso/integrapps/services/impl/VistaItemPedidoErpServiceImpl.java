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
	public List<VistaItemPedidoErp> buscarItemsPedido(Integer idPedido) {
		return vistaItemPedidoRepo.findByTipoPedidoAndNoPedido("PV", idPedido);
	}



	@Override
	public List<VistaItemPedidoErp> findByRowIdOpAndReferencia(Integer noPedido, String referencia) {
		//return vistaItemPedidoRepo.findByTipoPedidoAndNoPedidoAndReferencia(OrdenPv, referencia);
		//return vistaItemPedidoRepo.findByRowIdOpAndReferencia(noPedido, referencia);
		return vistaItemPedidoRepo.findByNoPedidoAndReferencia(noPedido, referencia);
	}



	@Override
	public List<VistaItemPedidoErp> findByNoPedidopAndReferencia(Integer noPedido, String ref) {
		return vistaItemPedidoRepo.findByNoPedidoAndReferencia(noPedido, ref);
	}



	@Override
	public List<VistaItemPedidoErp> findByRowIdOp(Integer rowIdOp) {
		return vistaItemPedidoRepo.findByRowIdOp(rowIdOp);
	}


/*
	@Override
	public List<VistaItemPedidoErp> buscarItemPedidoByReferencia(String noPedido, String referencia) {
		// TODO Auto-generated method stub
		return null;
	}*/



}
