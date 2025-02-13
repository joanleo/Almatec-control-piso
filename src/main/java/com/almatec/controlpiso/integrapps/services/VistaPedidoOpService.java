package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.VistaPedidoOp;

public interface VistaPedidoOpService {

	List<VistaPedidoOp> findByPedido(Integer rowIdPV);

}
