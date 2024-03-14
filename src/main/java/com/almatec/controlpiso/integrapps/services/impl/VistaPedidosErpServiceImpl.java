package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.PedidoSpecDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;
import com.almatec.controlpiso.integrapps.repositories.VistaPedidosErpRepository;
import com.almatec.controlpiso.integrapps.services.VistaPedidosErpService;
import com.almatec.controlpiso.integrapps.specifications.PedidoSpecification;

@Service
public class VistaPedidosErpServiceImpl implements VistaPedidosErpService {
	
	@Autowired
	private VistaPedidosErpRepository vistaPedidosErpRepo;
	
	@Autowired
	private PedidoSpecification filter;

	@Override
	public List<VistaPedidosErp> buscarPedidosErp() {
		return vistaPedidosErpRepo.findByTipoAndIdEstadoOrderByNoPvDesc("PV", 2);
	}

	@Override
	public List<VistaPedidosErp> buscarPedidosErp(String keyword) {
		return vistaPedidosErpRepo.buscarPedidosErpFilterByKeyword("PV", 2, keyword);
	}

	@Override
	public List<VistaPedidosErp> searchOrder(PedidoSpecDTO busquedaSpec) {
		List<VistaPedidosErp> orders = vistaPedidosErpRepo.findAll(filter.getOrders(busquedaSpec));
		return orders; 
	}

}
