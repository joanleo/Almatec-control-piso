package com.almatec.controlpiso.erp.webservices.services;

import com.almatec.controlpiso.erp.webservices.generated.ItemsVersion05;
import com.almatec.controlpiso.erp.webservices.generated.RutasRutasV01;

public interface ConectorRutaService {

	RutasRutasV01 crearConectorRuta(ItemsVersion05 item);
}
