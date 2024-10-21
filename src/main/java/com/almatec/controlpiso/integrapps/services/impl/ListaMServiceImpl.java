package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;
import com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO;
import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.ListaM;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;
import com.almatec.controlpiso.integrapps.interfaces.ListaMInterface;
import com.almatec.controlpiso.integrapps.repositories.ListaMRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.VistaItemLoteDisponibleService;

@Service
public class ListaMServiceImpl implements ListaMService {
	
	@Autowired
	private ListaMRepository listaMaterialRepo;
	
	@Autowired 
	private ItemOpService itemOpService;
	
	@Autowired
	private VistaItemLoteDisponibleService vistaItemLoteDisponibleService;

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
	public List<LoteConCodigoDTO> obtenerLotesOpPorItem(Long idItem) {
	    ItemOp item = itemOpService.obtenerItemPorId(idItem);
	    List<LoteConCodigoDTO> lotes = listaMaterialRepo.obtenerLotesOpPorItem(item.getIdOpIntegrapps());
	    
	    if (lotes == null || lotes.isEmpty()) {
	        return new ArrayList<>(); // Return an empty list if no lotes are found
	    }
	    
	    lotes.forEach(lote -> {
	        SpecItemLoteDTO filtro = new SpecItemLoteDTO();
	        filtro.setBodega("00102");
	        filtro.setLote(lote.getLoteErp());
	        System.out.println(lote);
	        
	        List<VistaItemLoteDisponible> disponibles = vistaItemLoteDisponibleService.searchItems(filtro, false);
	        
	        if (disponibles.isEmpty()) {
	            lote.setDisponible(BigDecimal.ZERO);
	        } else {
	            VistaItemLoteDisponible disponible = disponibles.get(0);
	            lote.setDisponible(disponible != null ? disponible.getDisponible() : BigDecimal.ZERO);
	        }
	    });

	    return lotes;
	}


}
