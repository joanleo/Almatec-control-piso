package com.almatec.controlpiso.erp.webservices.services;

import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionDocumentosVersion01;
import com.almatec.controlpiso.erp.webservices.generated.DoctoordenesdeproduccionItemsVersion01;
import com.almatec.controlpiso.erp.webservices.generated.ItemsVersion05;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;

public interface ConectorOrdenProduccionHijoService {

	DoctoordenesdeproduccionDocumentosVersion01 crearEncabezadoOPEntrega(VistaOrdenPv ordenPadreIF);
	DoctoordenesdeproduccionItemsVersion01 crearDetalleOpEntrega(ItemsVersion05 item,
			VistaOrdenPv ordenIntegrapps, Double cantBase);
}
