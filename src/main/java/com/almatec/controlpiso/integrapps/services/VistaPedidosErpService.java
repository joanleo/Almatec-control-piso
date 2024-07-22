package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.almatec.controlpiso.integrapps.dtos.PedidoSpecDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;

public interface VistaPedidosErpService {

	List<VistaPedidosErp> buscarPedidosErp();

	List<VistaPedidosErp> buscarPedidosErp(String keyword);

	List<VistaPedidosErp> searchOrder(PedidoSpecDTO busquedaSpec);

	Page<VistaPedidosErp> searchOrder(PedidoSpecDTO busquedaSpec, Pageable pageable);

	Page<VistaPedidosErp> buscarPedidosErp(Pageable pageable);

	Page<VistaPedidosErp> buscarPedidosErp(String keyword, Pageable pageable);

}
