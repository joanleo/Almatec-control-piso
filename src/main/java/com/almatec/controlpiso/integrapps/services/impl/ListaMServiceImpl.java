package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;
import com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.ListaM;
import com.almatec.controlpiso.integrapps.interfaces.ListaMInterface;
import com.almatec.controlpiso.integrapps.repositories.ListaMRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.VistaItemLoteDisponibleService;
import com.almatec.controlpiso.produccion.dtos.LoteInfoDTO;

@Service
public class ListaMServiceImpl implements ListaMService {
	
	@Autowired
	private ListaMRepository listaMaterialRepo;
	
	@Autowired 
	private ItemOpService itemOpService;
	
	@Autowired
	private VistaItemLoteDisponibleService vistaItemLoteDisponibleService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

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
		StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
	    //ItemOp item = itemOpService.obtenerItemPorId(idItem);
        System.out.println(idItem);
	    Integer idOpIntegrapps = itemOpService.obtenerIdOpIntegrappsPorIdItem(idItem);
	    System.out.println(idOpIntegrapps);
	    List<LoteConCodigoDTO> lotes = listaMaterialRepo.obtenerLotesOpPorItem(idOpIntegrapps);
	    
	    if (lotes == null || lotes.isEmpty()) {
	        return new ArrayList<>(); // Return an empty list if no lotes are found
	    }
	    
	    Set<String> lotesErp = lotes.stream()
	            .map(LoteConCodigoDTO::getLoteErp)
	            .collect(Collectors.toSet());
	    
	    Map<String, LoteInfoDTO> disponibilidades = obtenerDisponibilidadesBatch(lotesErp);
	    
	    lotes.forEach(lote -> {
        	LoteInfoDTO info = disponibilidades.getOrDefault(lote.getLoteErp().trim(), 
                    new LoteInfoDTO(BigDecimal.ZERO, ""));
            lote.setDisponible(info.getDisponible());
            lote.setDescripcion(info.getDescripcion());
	    });
	    
	    
	    /*lotes.forEach(lote -> {
	        SpecItemLoteDTO filtro = new SpecItemLoteDTO();
	        filtro.setBodega("00102");
	        filtro.setLote(lote.getLoteErp());
	        
	        List<VistaItemLoteDisponible> disponibles = vistaItemLoteDisponibleService.searchItems(filtro, false);
	        
	        if (disponibles.isEmpty()) {
	            lote.setDisponible(BigDecimal.ZERO);
	        } else {
	            VistaItemLoteDisponible disponible = disponibles.get(0);
	            lote.setDisponible(disponible != null ? disponible.getDisponible() : BigDecimal.ZERO);
	        }
	    });*/
	    
	    stopWatch.stop();
		logger.info("Tiempo de ejecución lotes: {} ms", stopWatch.getTotalTimeMillis());

	    return lotes;
	}

	private Map<String, LoteInfoDTO> obtenerDisponibilidadesBatch(Set<String> lotesErp) {
		return vistaItemLoteDisponibleService.buscarDisponibilidadBatch(lotesErp, "00102");
	}


}
