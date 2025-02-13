package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.almatec.controlpiso.integrapps.dtos.ConsultaOpCreadaDTO;
import com.almatec.controlpiso.integrapps.dtos.OpProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.ProyectoProduccionDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.interfaces.OrdenPvEstadoData;
import com.almatec.controlpiso.integrapps.interfaces.VistaOrdenPvDTO;

public interface OrdenPvService {

	List<ProyectoProduccionDTO> buscarProyectosOrdenPv();

	List<ProyectoProduccionDTO> buscarProyectosOrdenPv(String keyword);

	List<OpProduccionDTO> buscarOrdenesPorIdProyecto(String idProyecto);

	VistaOrdenPv obtenerOrdenPorId(Integer idPvIntegrapps);

	void guardarOrden(VistaOrdenPv orden);

	VistaOrdenPv actualizarDatosOp(ConsultaOpCreadaDTO creado, VistaOrdenPv ordenIntegrapps);

	Integer obtenerNumOpPorIdOp(Integer idOpIntegrapps);

	OrdenPvEstadoData obtenerOrdenPorNumPv(Integer noPedido);

	List<VistaOrdenPv> obtenerOpActivas();

	List<VistaOrdenPv> obtenerOrdenes();

	Page<VistaOrdenPvDTO> buscarProyectosPaginados(Pageable pageable);

	Page<VistaOrdenPvDTO> buscarProyectosPaginados(String keyword, Pageable pageable);

	Integer obteneridItemOpPorIdOpIA(Integer idOPI);

}
