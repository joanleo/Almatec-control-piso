package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCtDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOpItemsMaterialesRuta;
import com.almatec.controlpiso.integrapps.repositories.VistaOpItemsMaterialesRutaRepository;
import com.almatec.controlpiso.integrapps.services.VistaOpItemsMaterialesRutaService;
import com.almatec.controlpiso.utils.EstructuraDatos;

@Service
public class VistaOpItemsMaterialesRutaServiceImpl implements VistaOpItemsMaterialesRutaService {
	
	@Autowired
	private VistaOpItemsMaterialesRutaRepository vistaOpItemsMaterialesRutaRepo;

	@Override
	public Set<OpCentroTrabajoDTO> buscarOpCt(Integer idCT) {
		List<VistaOpItemsMaterialesRuta> listaRutas = vistaOpItemsMaterialesRutaRepo.findByItemCentroTIdOrMaterialCentroTId(idCT, idCT);
		Set<OpCentroTrabajoDTO> ordenesProduccion = new HashSet<>();
		if(!listaRutas.isEmpty()) {
			List<OpCentroTrabajoDTO> listaOrdenesProduccion = EstructuraDatos.crearEstructura(listaRutas);
			ordenesProduccion = new HashSet<>(listaOrdenesProduccion);
			for(OpCentroTrabajoDTO op:ordenesProduccion) {
				Set<ItemOpCtDTO> setItems = new HashSet<>(op.getItems());
				List<ItemOpCtDTO> conjuntoItems = new ArrayList<>(mergeItems(new ArrayList<>(setItems)));
				op.setItems(conjuntoItems);
			}			
		}
		
		return ordenesProduccion;
	}
	
	public static Set<ItemOpCtDTO> mergeItems(List<ItemOpCtDTO> items) {
        Map<String, List<ItemOpCtDTO>> groupedItems = items.stream()
                .collect(Collectors.groupingBy(VistaOpItemsMaterialesRutaServiceImpl::generateKey));

        return groupedItems.values().stream()
                .map(VistaOpItemsMaterialesRutaServiceImpl::mergeItemDTOs)
                .collect(Collectors.toSet());
    }
	
	private static String generateKey(ItemOpCtDTO item) {
        return item.getItem_op_id() + "_" + item.getItem_id() + "_" + item.getItem_desc() + "_" + item.getCant_req();
    }
	
	private static ItemOpCtDTO mergeItemDTOs(List<ItemOpCtDTO> itemDTOs) {
        ItemOpCtDTO mergedItem = new ItemOpCtDTO();
        mergedItem.setItem_op_id(itemDTOs.get(0).getItem_op_id());
        mergedItem.setItem_id(itemDTOs.get(0).getItem_id());
        mergedItem.setItem_desc(itemDTOs.get(0).getItem_desc());
        mergedItem.setCant_req(itemDTOs.get(0).getCant_req());
        mergedItem.setItem_centro_t_id(itemDTOs.get(0).getItem_centro_t_id());
        mergedItem.setItem_centro_t_nombre(itemDTOs.get(0).getItem_centro_t_nombre());
        mergedItem.setItem_peso(itemDTOs.get(0).getItem_peso());
        mergedItem.setItem_color(itemDTOs.get(0).getItem_color());
        mergedItem.setPrioridad(itemDTOs.get(0).getPrioridad());
        mergedItem.setLongitud(itemDTOs.get(0).getLongitud());
        mergedItem.setCant_cumplida(itemDTOs.get(0).getCant_cumplida());
        	
        List<ComponenteDTO> mergedComponentes = itemDTOs.stream()
                .flatMap(item -> item.getComponentes().stream())
                .distinct()
                .collect(Collectors.toList());

        mergedItem.setComponentes(new HashSet<>(mergedComponentes));
        
        return mergedItem;
    }

	@Override
	public Set<OpCentroTrabajoDTO> buscarItemCt(Long idItem, Integer idCT) {
		List<VistaOpItemsMaterialesRuta> listaRutas = vistaOpItemsMaterialesRutaRepo.buscarItemCt(idItem, idCT);
		List<OpCentroTrabajoDTO> ordenesProduccion = EstructuraDatos.crearEstructura(listaRutas);
		Set<OpCentroTrabajoDTO> filterOrdenesProduccion = new HashSet<>(ordenesProduccion);
		for(OpCentroTrabajoDTO op:filterOrdenesProduccion) {
			Set<ItemOpCtDTO> setItems = new HashSet<>(op.getItems());
			List<ItemOpCtDTO> conjuntoItems = new ArrayList<>(mergeItems(new ArrayList<>(setItems)));			
			op.setItems(conjuntoItems);
		}

		return filterOrdenesProduccion;
	}

}
