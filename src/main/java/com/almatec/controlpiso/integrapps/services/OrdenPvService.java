package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.ConsultaOpCreadaDTO;
import com.almatec.controlpiso.integrapps.dtos.OpProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.ProyectoProduccionDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.interfaces.OrdenPvEstadoData;

public interface OrdenPvService {

	List<VistaOrdenPv> buscarProyectos();

	List<VistaOrdenPv> buscarProyectos(String keyword);

	List<ProyectoProduccionDTO> buscarProyectosOrdenPv();

	List<ProyectoProduccionDTO> buscarProyectosOrdenPv(String keyword);

	List<OpProduccionDTO> buscarOrdenesPorIdProyecto(String idProyecto);

	VistaOrdenPv obtenerOrdenPorId(Integer idPvIntegrapps);

	void guardarOrden(VistaOrdenPv orden);

	void actualizarDatosOp(ConsultaOpCreadaDTO creado, VistaOrdenPv ordenIntegrapps);

	Integer obtenerNumOpPorIdOp(Integer idOpIntegrapps);

	OrdenPvEstadoData obtenerOrdenPorNumPv(Integer noPedido);

	List<VistaOrdenPv> obtenerOpActivas();

	List<VistaOrdenPv> obtenerOrdenes();

}
