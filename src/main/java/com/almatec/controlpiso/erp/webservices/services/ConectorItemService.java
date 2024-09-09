package com.almatec.controlpiso.erp.webservices.services;

import com.almatec.controlpiso.erp.webservices.generated.ItemsVersion05;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;

public interface ConectorItemService {

	ItemsVersion05 crearConectorItemEntrega(VistaOrdenPv ordenIntegrapps, VistaOrdenPv ordenPapa);
}
