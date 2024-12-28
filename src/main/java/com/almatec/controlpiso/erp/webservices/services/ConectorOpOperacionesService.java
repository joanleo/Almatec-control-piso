package com.almatec.controlpiso.erp.webservices.services;

import java.util.List;

import com.almatec.controlpiso.erp.webservices.generated.OrdenesdeproduccionOperacionesVersion01;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;

public interface ConectorOpOperacionesService {

	List<OrdenesdeproduccionOperacionesVersion01> crearConector(Integer idOPI, VistaOrdenPv ordenIntegrapps, Integer idItemOp);

}
