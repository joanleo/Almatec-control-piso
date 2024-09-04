package com.almatec.controlpiso.erp.webservices.services;

import com.almatec.controlpiso.erp.webservices.generated.ItemsParametrosVersion5;

public interface ConectorParametrosService {

	ItemsParametrosVersion5 crearParametros(String idRuta, Integer idItem);
}
