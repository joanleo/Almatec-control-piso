package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.paging.Page;
import com.almatec.controlpiso.integrapps.paging.PageArray;
import com.almatec.controlpiso.integrapps.paging.PagingRequest;

public interface ItemOpService {

	List<ItemOp> obtenerItemsOp(Integer idOp);

	List<ItemOp> obtenerItemsOpC2(String idGrupo);

	//ItemOp obtenerItemsOp(String idGrupo);

	List<ItemOp> buscarItemsOp(Integer numOp);
	
	PageArray obtenerItemsOpArray(PagingRequest pagingRequest, Integer idOp);

	Page<ItemOp> obtenerPagina(List<ItemOp> itemsOp, PagingRequest pagingRequest);

	List<ItemOp> obtenerItemsOpProduccion(Integer numOp);
}
