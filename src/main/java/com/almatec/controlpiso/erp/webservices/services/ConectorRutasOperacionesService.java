package com.almatec.controlpiso.erp.webservices.services;

import java.util.List;

import com.almatec.controlpiso.erp.dto.RutaDTO;
import com.almatec.controlpiso.erp.webservices.generated.RutasoperacionesOperacionesV01;

public interface ConectorRutasOperacionesService {

	List<RutasoperacionesOperacionesV01> crearConectorRutaOperaciones(List<RutaDTO> rutas, String id);
}
