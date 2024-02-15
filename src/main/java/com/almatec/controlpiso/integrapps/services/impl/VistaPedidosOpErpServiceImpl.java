package com.almatec.controlpiso.integrapps.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosOpErp;
import com.almatec.controlpiso.integrapps.repositories.VistaPedidosOpErpRepository;
import com.almatec.controlpiso.integrapps.services.VistaPedidosOpErpService;

@Service
public class VistaPedidosOpErpServiceImpl implements VistaPedidosOpErpService {

	@Autowired
	private VistaPedidosOpErpRepository vistaPedidosOpErpRepo;

	@Override
	public List<VistaPedidosOpErp> buscarOps() {
		return vistaPedidosOpErpRepo.findByTipoOpOrTipoOpOrderByNumPv("IF", "OP");
	}

	@Override
	public List<VistaPedidosOpErp> buscarOps(String keyword) {
		return vistaPedidosOpErpRepo.findByTipoOpFilterByKeyword("IF", keyword);
	}
	


}
