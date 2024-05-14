package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.services.VistaItemsRutasService;

@Service
public class VistaItemsRutasServiceImpl implements VistaItemsRutasService {

	@Override
	public Set<OpCentroTrabajoDTO> buscarOpCT(Integer idCT) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OpCentroTrabajoDTO> buscarOp(Integer idOp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<OpCentroTrabajoDTO> buscarItemCt(Long idItem, Integer idCT) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Autowired
	private VistaItemsRutasRepository vistaItemsRutasRepo;

	@Override
	public Set<OpCentroTrabajoDTO> buscarOpCT(Integer idCT) {
		List<VistaItemsRutas> listaRutas = vistaItemsRutasRepo.findByIdCentroTrabajoConjuntoOrIdCentroTrabajoPerfil(idCT, idCT);
		Set<OpCentroTrabajoDTO> ordenesProduccion = new HashSet<>();
		if(!listaRutas.isEmpty()) {

			List<OpCentroTrabajoDTO> listaOrdenesProduccion = EstructuraDatos.crearEstructura(listaRutas);
			ordenesProduccion = new HashSet<>(listaOrdenesProduccion);
			for(OpCentroTrabajoDTO op:ordenesProduccion) {

				Set<ItemOpCtDTO> setItems = new HashSet<>(op.getItems());
				List<ItemOpCtDTO> conjuntoItems = new ArrayList<>(mergeItems(new ArrayList<>(setItems)));
				
				op.setItems(conjuntoItems);
			}
			
			if(Objects.equals(listaRutas.get(0).getIdCentroTrabajoPerfil(), idCT)) {
				System.out.println("En este centro de trabajo se muestra un componente");
			}
			
		}

		return ordenesProduccion;
	}

	@Override
	public List<OpCentroTrabajoDTO> buscarOp(Integer idOp) {
		
		List<VistaItemsRutas> listaRutas = vistaItemsRutasRepo.findByIdOp(idOp);
		List<OpCentroTrabajoDTO> ordenesProduccion = EstructuraDatos.crearEstructura(listaRutas);
		return ordenesProduccion;
	}
	
	public static Set<ItemOpCtDTO> mergeItems(List<ItemOpCtDTO> items) {
        Map<String, List<ItemOpCtDTO>> groupedItems = items.stream()
                .collect(Collectors.groupingBy(VistaItemsRutasServiceImpl::generateKey));

        return groupedItems.values().stream()
                .map(VistaItemsRutasServiceImpl::mergeItemDTOs)
                .collect(Collectors.toSet());
    }
    
    private static String generateKey(ItemOpCtDTO item) {
        return item.getIdItemFab() + "_" + item.getDescripcion() + "_" + item.getCant();
    }

    private static ItemOpCtDTO mergeItemDTOs(List<ItemOpCtDTO> itemDTOs) {
        ItemOpCtDTO mergedItem = new ItemOpCtDTO();
        mergedItem.setIdItem(itemDTOs.get(0).getIdItem());
        mergedItem.setIdItemFab(itemDTOs.get(0).getIdItemFab());
        mergedItem.setDescripcion(itemDTOs.get(0).getDescripcion());
        mergedItem.setCant(itemDTOs.get(0).getCant());
        mergedItem.setIdCentroTrabajo(itemDTOs.get(0).getIdCentroTrabajo());
        mergedItem.setCentroTrabajo(itemDTOs.get(0).getCentroTrabajo());
        mergedItem.setPeso(itemDTOs.get(0).getPeso());
        mergedItem.setPintura(itemDTOs.get(0).getPintura());
        mergedItem.setPrioridad(itemDTOs.get(0).getPrioridad());
        	
        List<ComponenteDTO> mergedComponentes = itemDTOs.stream()
                .flatMap(item -> item.getComponentes().stream())
                .distinct()
                .collect(Collectors.toList());

        mergedItem.setComponentes(new HashSet<>(mergedComponentes));
        
        return mergedItem;
    }

	@Override
	public Set<OpCentroTrabajoDTO> buscarItemCt(Long idItem, Integer idCT) {
		List<VistaItemsRutas> listaRutas = vistaItemsRutasRepo.buscarItemCt(idItem, idCT);
		List<OpCentroTrabajoDTO> ordenesProduccion = EstructuraDatos.crearEstructura(listaRutas);
		Set<OpCentroTrabajoDTO> filiterOrdenesProduccion = new HashSet<>(ordenesProduccion);
		for(OpCentroTrabajoDTO op:filiterOrdenesProduccion) {

			Set<ItemOpCtDTO> setItems = new HashSet<>(op.getItems());
			List<ItemOpCtDTO> conjuntoItems = new ArrayList<>(mergeItems(new ArrayList<>(setItems)));
			
			op.setItems(conjuntoItems);
		}
		filiterOrdenesProduccion.forEach(System.out::println);
		return filiterOrdenesProduccion;
	}*/

}
