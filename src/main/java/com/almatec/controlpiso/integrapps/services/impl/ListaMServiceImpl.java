package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.ListaM;
import com.almatec.controlpiso.integrapps.interfaces.ListaMInterface;
import com.almatec.controlpiso.integrapps.repositories.ListaMRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ListaMService;

@Service
public class ListaMServiceImpl implements ListaMService {
	
	@Autowired
	private ListaMRepository listaMaterialRepo;
	
	@Autowired 
	private ItemOpService itemOpService;

	@Override
	public List<ListaMDTO> obtenerListaMDTOPorIdOp(Integer idOP) {
		List<ListaMInterface> listaMInterface = listaMaterialRepo.ObtenerListaInterdacePorIdOp(idOP);
		List<ListaMDTO> listaM = new ArrayList<>();
		for(ListaMInterface itemI: listaMInterface) {
			ListaMDTO itemLista = new ListaMDTO(itemI);
			listaM.add(itemLista);
		}
		return listaM;
		}

	@Override
	public List<ListaM> obtenerListaMPorIdOp(Integer idOp) {
		return listaMaterialRepo.findByIdOpIntegrapps(idOp);
	}

	@Override
	public void actualizarListaM(List<ListaM> listaMateriales) {
		listaMaterialRepo.saveAll(listaMateriales);
		
	}

	@Override
	public List<String> obtenerLotesOpPorItem(Long idItem) {
		ItemOp item = itemOpService.obtenerItemPorId(idItem);
		System.out.println("buscando lotes");
		return listaMaterialRepo.obtenerLotesOpPorItem(item.getIdOpIntegrapps());
	}


}
