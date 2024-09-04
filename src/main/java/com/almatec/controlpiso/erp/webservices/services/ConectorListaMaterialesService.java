package com.almatec.controlpiso.erp.webservices.services;

import java.util.List;

import com.almatec.controlpiso.erp.dto.ListaMaterialesDTO;
import com.almatec.controlpiso.erp.webservices.generated.ListadematerialesListadematerialesv4;

public interface ConectorListaMaterialesService {

	List<ListadematerialesListadematerialesv4> crearConectorLM(List<ListaMaterialesDTO> listaMateriales,
			Integer idItem);
}
