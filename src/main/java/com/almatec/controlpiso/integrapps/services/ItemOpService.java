package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.ConsultaOpId;
import com.almatec.controlpiso.integrapps.dtos.DataItemImprimirDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCTPrioridadDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpDatable;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemListaMateriaInterface;
import com.almatec.controlpiso.integrapps.paging.PageArray;
import com.almatec.controlpiso.integrapps.paging.PagingRequest;

public interface ItemOpService {

	List<ItemOp> obtenerItemsOp(Integer idOp);

	List<ItemOp> obtenerItemsOpC2(String idGrupo);

	List<ItemOp> buscarItemsOp(Integer numOp);
	
	PageArray obtenerItemsOpArray(PagingRequest pagingRequest, Integer idOp);

	List<ItemOp> obtenerItemsOpProduccion(Integer numOp);

	ItemOp obtenerItemPorId(Long itemId);

	ItemOp guardarItemOp(ItemOp item);

	String obtenerStringListaMateriales(Integer id);

	String obtenerStringRuta(Integer id);

	List<ConsultaOpId> obtenerNumOps();

	String obtenerStringPorIdOPIntegrappsYTipo(Integer idOPI, String ruta);

	Double obtenerValorAplicarTepItemCentroTrabajo(Integer idItemFab, Integer idCentroTrabajo);

	ItemInterface obtenerItemFabricaPorId(Integer idItem);

	List<ItemListaMateriaInterface> obtenerListaMaterialesItemPorIdItem(Integer idItemFab, Integer idFab);

	void imprimirEtiquetas(List<DataItemImprimirDTO> data);

	List<ItemOpDatable> obtenerItemsOpDataTable(Integer numOp);

	List<ItemOpCTPrioridadDTO> findOpsItemsPorCentroTrabajo(Integer idCT);

	List<Integer> obtenerCentrosTrabajoRutaPorIdOpIA(Integer idOpIntegrapps);

	Integer obtenerIdOpIntegrappsPorIdItem(Long idItem);

	List<Integer> obtenerRutaItem(Integer idItemFab);
}
