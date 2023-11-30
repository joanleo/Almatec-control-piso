package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.entities.ItemOp;

public interface ItemOpService {

	List<ItemOp> obtenerItemsOpC1(String idOp);

	List<ItemOp> obtenerItemsOpC2(String idGrupo);

	ItemOp obtenerItemsOp(String idGrupo);

}
