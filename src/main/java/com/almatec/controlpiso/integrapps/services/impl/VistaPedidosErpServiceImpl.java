package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;
import com.almatec.controlpiso.integrapps.repositories.VistaPedidosErpRepository;
import com.almatec.controlpiso.integrapps.services.VistaPedidosErpService;

@Service
public class VistaPedidosErpServiceImpl implements VistaPedidosErpService {
	
	@Autowired
	private VistaPedidosErpRepository vistaPedidosErpRepo;

	@Override
	public List<VistaPedidosErp> buscarPedidosErp() {
		return vistaPedidosErpRepo.findByTipoAndEstadoOrderByNoPvDesc("PV", 2);
	}

	@Override
	public List<VistaPedidosErp> buscarPedidosErp(String keyword) {
		return vistaPedidosErpRepo.buscarPedidosErpFilterByKeyword("PV", 2, keyword);
	}

}
