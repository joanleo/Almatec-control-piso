package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCtDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOpItemsMaterialesRuta;
import com.almatec.controlpiso.integrapps.repositories.VistaOpItemsMaterialesRutaRepository;
import com.almatec.controlpiso.integrapps.services.VistaOpItemsMaterialesRutaService;
import com.almatec.controlpiso.integrapps.specifications.PrioridadSpecification;
import com.almatec.controlpiso.programacion.dtos.PrioridadFilterDTO;
import com.almatec.controlpiso.utils.AgrupacionPrioridad;
import com.almatec.controlpiso.utils.EstructuraDatos;

@Service
public class VistaOpItemsMaterialesRutaServiceImpl implements VistaOpItemsMaterialesRutaService {
	
	@Autowired
	private VistaOpItemsMaterialesRutaRepository vistaOpItemsMaterialesRutaRepo;
	
	@Autowired
	private PrioridadSpecification prioridadSpecification;
	
	private static final Logger logger = LoggerFactory.getLogger(VistaOpItemsMaterialesRutaServiceImpl.class);
	
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
        return item.getItem_op_id() + "_" + item.getItem_id() + "_" + item.getItem_desc() + "_" + item.getCant_req() + "_" + item.getMarca();
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
        mergedItem.setMarca(itemDTOs.get(0).getMarca());
        	
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

	@Override
	public Page<VistaOpItemsMaterialesRuta> obtenerItemsOpsPaginados(int page, int size, String sortDir,
			String sortField, PrioridadFilterDTO filtro) {

		Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
	    Pageable pageable = PageRequest.of(page, size, sort);
		//Page<VistaOpItemsMaterialesRuta> prioridades = null;
		try {
			Specification<VistaOpItemsMaterialesRuta> spec = prioridadSpecification.getItemsPrioridades(filtro);
			logger.info("Filtro service: " + filtro);

	        // Obtener todos los elementos
	        List<VistaOpItemsMaterialesRuta> allItems = vistaOpItemsMaterialesRutaRepo.findAll(spec);

	        // Agrupar los elementos
	        List<VistaOpItemsMaterialesRuta> contentAgrupado = agruparItems(allItems);

	        // Aplicar la paginación manualmente
	        int start = (int) pageable.getOffset();
	        int end = Math.min((start + pageable.getPageSize()), contentAgrupado.size());
	        List<VistaOpItemsMaterialesRuta> pageContent = contentAgrupado.subList(start, end);

	        // Crear una nueva Page con el contenido agrupado y paginado
	        return new PageImpl<>(pageContent, pageable, contentAgrupado.size());		
		} catch (Exception e) {
			e.printStackTrace();
			return Page.empty();
		}
	}
	
	private List<VistaOpItemsMaterialesRuta> agruparItems(List<VistaOpItemsMaterialesRuta> items) {
		Map<AgrupacionPrioridad, VistaOpItemsMaterialesRuta> groupedItems = new LinkedHashMap<>();


	    for (VistaOpItemsMaterialesRuta item : items) {
	        AgrupacionPrioridad key = new AgrupacionPrioridad(item.getOp(), item.getItem_id());
	        if (!groupedItems.containsKey(key)) {
	            groupedItems.put(key, item);
	        }
	    }

	    return new ArrayList<>(groupedItems.values());
	}

}
