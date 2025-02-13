package com.almatec.controlpiso.integrapps.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.PedidoSpecDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosKgCumplidos;
import com.almatec.controlpiso.integrapps.repositories.VistaPedidosKgCumplidosRepository;
import com.almatec.controlpiso.integrapps.services.VistaPedidosKgCumplidosService;
import com.almatec.controlpiso.integrapps.specifications.PedidoSpecification;

@Service
public class VistaPedidosKgCumplidosImpl implements VistaPedidosKgCumplidosService {

	private final VistaPedidosKgCumplidosRepository vistaPedidosKgCumplidosRepo;
	private final PedidoSpecification filter;
	
	
	public VistaPedidosKgCumplidosImpl(VistaPedidosKgCumplidosRepository vistaPedidosKgCumplidosRepo,
			PedidoSpecification filter) {
		super();
		this.vistaPedidosKgCumplidosRepo = vistaPedidosKgCumplidosRepo;
		this.filter = filter;
	}



	@Override
	public Page<VistaPedidosKgCumplidos> searchOrder(PedidoSpecDTO busquedaSpec, Pageable pageable) {
		return vistaPedidosKgCumplidosRepo.findAll(filter.getOrders(busquedaSpec), pageable); 
	}

}
