package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.VistaPedidoOp;
import com.almatec.controlpiso.integrapps.repositories.VistaPedidoOpRepository;
import com.almatec.controlpiso.integrapps.services.VistaPedidoOpService;

@Service
public class VistaPedidoOpServiceImpl implements VistaPedidoOpService {
	
	private final VistaPedidoOpRepository vistaPedidoOpRepo;
	
	
	public VistaPedidoOpServiceImpl(VistaPedidoOpRepository vistaPedidoOpRepo) {
		super();
		this.vistaPedidoOpRepo = vistaPedidoOpRepo;
	}

	@Override
	public List<VistaPedidoOp> findByPedido(Integer rowIdPV) {
		return vistaPedidoOpRepo.findByPvRowId(rowIdPV);
	}

}
