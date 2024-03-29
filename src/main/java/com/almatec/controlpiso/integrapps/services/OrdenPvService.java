package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.OpProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.ProyectoProduccionDTO;
import com.almatec.controlpiso.integrapps.entities.OrdenPv;

public interface OrdenPvService {

	List<OrdenPv> buscarProyectos();

	List<OrdenPv> buscarProyectos(String keyword);

	List<ProyectoProduccionDTO> buscarProyectosOrdenPv();

	List<ProyectoProduccionDTO> buscarProyectosOrdenPv(String keyword);

	List<OpProduccionDTO> buscarOrdenesPorIdProyecto(String idProyecto);

	OrdenPv obtenerOrdenPorId(Integer idPvIntegrapps);

	void guardarOrden(OrdenPv orden);

}
