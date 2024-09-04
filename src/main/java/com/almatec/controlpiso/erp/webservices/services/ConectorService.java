package com.almatec.controlpiso.erp.webservices.services;

import java.util.List;

import com.almatec.controlpiso.erp.webservices.generated.ItemsVersion05;
import com.almatec.controlpiso.erp.webservices.generated.RutasRutasV01;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

public interface ConectorService {

	ItemsVersion05 crearConectorItem(Integer idOPI);
	RutasRutasV01 crearConectorRuta(ItemsVersion05 item);
	List<Conector> crearConectorRutasOperaciones(Integer idOPI, RutasRutasV01 ruta);
	List<Conector> crearConectorParametros(String idRuta, Integer idItem);
}
