package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosOpErp;

public interface VistaPedidosOpErpService {

	List<VistaPedidosOpErp> buscarOps();

	List<VistaPedidosOpErp> buscarOps(String keyword);


}
