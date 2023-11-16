package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;

public interface VistaPedidosErpService {

	List<VistaPedidosErp> buscarPedidosErp();

	List<VistaPedidosErp> buscarPedidosErp(String keyword);

}
