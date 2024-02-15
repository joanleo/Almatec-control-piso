package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.OrdenPv;

public interface OrdenPvService {

	List<OrdenPv> buscarProyectos();

	List<OrdenPv> buscarProyectos(String keyword);

}
