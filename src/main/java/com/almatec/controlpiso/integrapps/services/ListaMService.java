package com.almatec.controlpiso.integrapps.services;

import java.util.List;

import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;
import com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO;
import com.almatec.controlpiso.integrapps.entities.ListaM;

public interface ListaMService {

	List<ListaMDTO> obtenerListaMDTOPorIdOp(Integer idOP);

	List<ListaM> obtenerListaMPorIdOp(Integer idOp);

	void actualizarListaM(List<ListaM> listaMateriales);

	List<LoteConCodigoDTO> obtenerLotesOpPorItem(Long idItem);

	Integer obtenerCodMateriaPrimaItemReporte(Integer idItem);

	List<LoteConCodigoDTO> obtenerLotesOpPorItemReporte(Long idItemOp, Integer codErpMp);



}
