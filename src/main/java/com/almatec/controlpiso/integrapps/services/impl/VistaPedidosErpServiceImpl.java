package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;
import com.almatec.controlpiso.integrapps.repositories.VistaPedidosErpRepository;
import com.almatec.controlpiso.integrapps.services.VistaPedidosErpService;

@Service
public class VistaPedidosErpServiceImpl implements VistaPedidosErpService {
	
	private final VistaPedidosErpRepository vistaPedidosErpRepo;

    public VistaPedidosErpServiceImpl(VistaPedidosErpRepository vistaPedidosErpRepo) {
        this.vistaPedidosErpRepo = vistaPedidosErpRepo;
    }

	@Override
	public List<VistaPedidosErp> buscarPedidosErp() {
		return vistaPedidosErpRepo.findByTipoAndIdEstadoOrderByNoPvDesc("PV", 2);
	}

	@Override
	public List<VistaPedidosErp> buscarPedidosErp(String keyword) {
		return vistaPedidosErpRepo.buscarPedidosErpFilterByKeyword("PV", 2, keyword);
	}

	@Override
	public Page<VistaPedidosErp> buscarPedidosErp(Pageable pageable) {
		return vistaPedidosErpRepo.findByTipoAndIdEstadoNotOrderByFechaDesc("PV", 9, pageable);
	}

	@Override
	public Page<VistaPedidosErp> buscarPedidosErp(String keyword, Pageable pageable) {
		return vistaPedidosErpRepo.findByKeyword(keyword, pageable);
	}

	@Override
	public VistaPedidosErp obtenerPorNoPedido(Integer noPedido) {
		return vistaPedidosErpRepo.findByNoPvAndTipo(noPedido, "PV");
	}

}
