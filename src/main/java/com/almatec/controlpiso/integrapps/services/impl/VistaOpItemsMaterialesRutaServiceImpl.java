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
        return item.getItem_op_id() + "_" + item.getItem_id() + "_" + item.getItem_desc() + "_" + item.getCant_req() + "_" + item.getMarca() + 
        		"_ " + item.getPrioridad();
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
        
        System.out.println(itemDTOs.get(0).getComponentes());
        
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
		filterOrdenesProduccion.forEach(op->op.getItems().forEach(System.out::println));
		for(OpCentroTrabajoDTO op:filterOrdenesProduccion) {
			Set<ItemOpCtDTO> setItems = new HashSet<>(op.getItems());
			List<ItemOpCtDTO> conjuntoItems = new ArrayList<>(mergeItemsReporte(new ArrayList<>(setItems)));			
			op.setItems(conjuntoItems);
		}
		return filterOrdenesProduccion;
	}

	private Set<ItemOpCtDTO> mergeItemsReporte(List<ItemOpCtDTO> items) {
		Map<String, List<ItemOpCtDTO>> groupedItems = items.stream()
                .collect(Collectors.groupingBy(VistaOpItemsMaterialesRutaServiceImpl::generateKeyReporte));
        return groupedItems.values().stream()
                .map(VistaOpItemsMaterialesRutaServiceImpl::mergeItemDTOs)
                .collect(Collectors.toSet());
	}

	private static String generateKeyReporte(ItemOpCtDTO item) {
        return item.getItem_op_id() + "_" + item.getItem_id() + "_" + item.getItem_desc() + "_" + item.getCant_req() + "_" + item.getItem_centro_t_id();
    }
	
	@Override
	public Page<VistaOpItemsMaterialesRuta> obtenerItemsOpsPaginados(int page, int size, String sortDir,
			String sortField, PrioridadFilterDTO filtro) {

		Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
	    Pageable pageable = PageRequest.of(page, size, sort);
		try {
			Specification<VistaOpItemsMaterialesRuta> spec = prioridadSpecification.getItemsPrioridades(filtro);
			logger.info("Filtro service: " + filtro);

	        // Obtener todos los elementos
	        List<VistaOpItemsMaterialesRuta> allItems = vistaOpItemsMaterialesRutaRepo.findAll(spec);

	        // Agrupar los elementos
	        List<VistaOpItemsMaterialesRuta> contentAgrupado = agruparItems(allItems);
	        
	        contentAgrupado.sort((item1, item2) -> {
	            if (item1 == null || item2 == null) {
	                return 0; // Manejar casos donde los elementos son null
	            }
	            
	            try {
	                switch (sortField) {
	                    case "op":
	                        return compareStrings(item1.getOp(), item2.getOp(), sortDir);
	                    case "marca":
	                        return compareStrings(item1.getMarca(), item2.getMarca(), sortDir);
	                    case "itemDescripcion":
	                        return compareStrings(item1.getItemDescripcion(), item2.getItemDescripcion(), sortDir);
	                    case "cantReq":
	                        return compareNumbers(item1.getCantReq(), item2.getCantReq(), sortDir);
	                    case "un":
	                        return compareStrings(item1.getUn(), item2.getUn(), sortDir);
	                    case "zona":
	                        return compareStrings(item1.getZona(), item2.getZona(), sortDir);
	                    case "prioridad":
	                        return comparePrioridades(item1.getPrioridad(), item2.getPrioridad(), sortDir);
	                    default:
	                        return 0;
	                }
	            } catch (Exception e) {
	                logger.error("Error al comparar campos: {}", e.getMessage());
	                return 0; // En caso de cualquier excepción, considerar los elementos iguales
	            }
	        });

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
	
	private int compareStrings(String s1, String s2, String sortDir) {
	    if (s1 == null && s2 == null) return 0;
	    if (s1 == null) return sortDir.equalsIgnoreCase("asc") ? -1 : 1;
	    if (s2 == null) return sortDir.equalsIgnoreCase("asc") ? 1 : -1;
	    return sortDir.equalsIgnoreCase("asc") ? s1.compareTo(s2) : s2.compareTo(s1);
	}

	// Método auxiliar para comparar números
	private <T extends Number & Comparable<T>> int compareNumbers(T n1, T n2, String sortDir) {
	    if (n1 == null && n2 == null) return 0;
	    if (n1 == null) return sortDir.equalsIgnoreCase("asc") ? -1 : 1;
	    if (n2 == null) return sortDir.equalsIgnoreCase("asc") ? 1 : -1;
	    return sortDir.equalsIgnoreCase("asc") ? n1.compareTo(n2) : n2.compareTo(n1);
	}

	// Método auxiliar para comparar prioridades
	private int comparePrioridades(Integer p1, Integer p2, String sortDir) {
	    int prioridad1 = (p1 == null) ? 0 : p1;
	    int prioridad2 = (p2 == null) ? 0 : p2;
	    return sortDir.equalsIgnoreCase("asc") ? Integer.compare(prioridad1, prioridad2) : Integer.compare(prioridad2, prioridad1);
	}
	
	private List<VistaOpItemsMaterialesRuta> agruparItems(List<VistaOpItemsMaterialesRuta> items) {
		Map<AgrupacionPrioridad, VistaOpItemsMaterialesRuta> groupedItems = new LinkedHashMap<>();

	    for (VistaOpItemsMaterialesRuta item : items) {
	        AgrupacionPrioridad key = new AgrupacionPrioridad(item.getOp(), item.getItem_id(), item.getCantReq());
	        if (!groupedItems.containsKey(key)) {
	            groupedItems.put(key, item);
	        }
	    }

	    return new ArrayList<>(groupedItems.values());
	}

}
