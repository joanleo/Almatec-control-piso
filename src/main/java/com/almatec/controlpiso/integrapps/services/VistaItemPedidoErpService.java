package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;

public interface VistaItemPedidoErpService {

	List<VistaItemPedidoErp> buscarItemsPedido(Integer idPedido);

	//List<VistaItemPedidoErp> buscarItemPedidoByReferencia(String noPedido, String referencia);

	//List<VistaItemPedidoErp> buscarItemPedidoByReferencia(OrdenPv noPedido, String referencia);

	List<VistaItemPedidoErp> findByRowIdOpAndReferencia(Integer noPedido, String referencia);

	List<VistaItemPedidoErp> findByNoPedidopAndReferencia(Integer idOpI, String ref);

	List<VistaItemPedidoErp> findByRowIdOp(Integer rowIdOp);


}
