package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;

public interface VistaItemPedidoErpService {

	List<VistaItemPedidoErp> buscarItemsPedido(String idPedido);

	List<VistaItemPedidoErp> buscarItemPedidoByReferencia(String noPedido, String referencia);


}
