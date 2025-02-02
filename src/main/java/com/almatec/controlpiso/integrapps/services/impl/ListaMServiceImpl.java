package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ListaMDTO;
import com.almatec.controlpiso.integrapps.dtos.LoteConCodigoDTO;
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
	

	@Value("${schema.unoee}")
	private String schemaUnoee;
	
	@Override
	public List<ListaMDTO> obtenerListaMDTOPorIdOp(Integer idOP) {
		List<ListaMInterface> listaMInterface = listaMaterialRepo.ObtenerListaInterdacePorIdOp(schemaUnoee, idOP);
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
		//StopWatch stopWatch = new StopWatch();
        //stopWatch.start();
        
	    //ItemOp item = itemOpService.obtenerItemPorId(idItem);
	    Integer idOpIntegrapps = itemOpService.obtenerIdOpIntegrappsPorIdItem(idItem);
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
	    
	    return lotes;
	}

	private Map<String, LoteInfoDTO> obtenerDisponibilidadesBatch(Set<String> lotesErp) {
		return vistaItemLoteDisponibleService.buscarDisponibilidadBatch(lotesErp, "00102");
	}

	@Override
	public Integer obtenerCodMateriaPrimaItemReporte(Integer idItem) {
		return listaMaterialRepo.obtenerCodMateriaPrimaItemReporte( idItem);
	}

	@Override
	public List<LoteConCodigoDTO> obtenerLotesOpPorItemReporte(Long idItemOp, Integer codErpMp) {
	    Integer idOpIntegrapps = itemOpService.obtenerIdOpIntegrappsPorIdItem(idItemOp);
	    List<LoteConCodigoDTO> lotes = listaMaterialRepo.obtenerLotesOpPorItemYCodErp(idOpIntegrapps, codErpMp);
	    
	    if (lotes == null || lotes.isEmpty()) {
	        return new ArrayList<>();
	    }


	    Set<String> clavesCodigoLote = lotes.stream()
	        .map(lote -> {
	            String clave = generarClaveCodigoLote(lote.getCodErp().toString(), lote.getLoteErp());
	            return clave;
	        })
	        .collect(Collectors.toSet());

	    Map<String, LoteInfoDTO> disponibilidades = obtenerDisponibilidadesBatch(clavesCodigoLote);

	    lotes.forEach(lote -> {
	        String key = generarClaveCodigoLote(lote.getCodErp().toString(), lote.getLoteErp());
	        
	        LoteInfoDTO info = disponibilidades.getOrDefault(key, new LoteInfoDTO(BigDecimal.ZERO, ""));       
	        lote.setDisponible(info.getDisponible());
	        lote.setDescripcion(info.getDescripcion());
	    });

	    return lotes;
	}
	
	private String generarClaveCodigoLote(String codigo, String lote) {
		String loteFormateado = (lote != null && !lote.trim().isEmpty()) ? lote.trim() : "";
	    return codigo + "|" + loteFormateado;
	}

}
