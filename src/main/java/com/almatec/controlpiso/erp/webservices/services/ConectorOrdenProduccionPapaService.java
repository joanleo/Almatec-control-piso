package com.almatec.controlpiso.erp.webservices.services;

import java.util.List;

import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersion01;
import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;
import com.almatec.controlpiso.integrapps.interfaces.OrdenPvEstadoData;

public interface ConectorOrdenProduccionPapaService {
	
	DoctoordenesdeproduccionDocumentosVersion01 crearEncabezadoOpPapa(Integer noPedido, OrdenPvEstadoData ordenPv);
    List<DoctoordenesdeproduccionItemsVersion01> crearDetalleOpPapa(List<VistaItemPedidoErp> items);

}
