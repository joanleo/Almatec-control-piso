package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;

public interface VistaPedidosErpService {

	List<VistaPedidosErp> buscarPedidosErp();

	List<VistaPedidosErp> buscarPedidosErp(String keyword);

	Page<VistaPedidosErp> buscarPedidosErp(Pageable pageable);

	Page<VistaPedidosErp> buscarPedidosErp(String keyword, Pageable pageable);

	VistaPedidosErp obtenerPorNoPedido(Integer noPedido);

}
