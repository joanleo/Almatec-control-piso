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
import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasOperarios;
import com.almatec.controlpiso.integrapps.repositories.VistaPiezasOperariosRepository;
import com.almatec.controlpiso.integrapps.services.VistaPiezasOperariosService;
import com.almatec.controlpiso.utils.ClaveAgrupacion;

@Service
public class VistaPiezasOperariosServiceImpl implements VistaPiezasOperariosService {
	
	@Autowired
	private VistaPiezasOperariosRepository vistaPiezasOperariosRepository;

	@Override
	public Set<OpCentroTrabajoDTO> obtenerPiezasOperarioProceso(OperarioDTO operario) {
		
		List<VistaPiezasOperarios> lista = vistaPiezasOperariosRepository.findPiezasOperariosProceso(operario);
		lista.forEach(System.out::println);
		Map<ClaveAgrupacion, List<VistaPiezasOperarios>> mapPorNumOp = lista.stream()
                .collect(Collectors.groupingBy(
                		vista -> new ClaveAgrupacion(
                        vista.getCliente(),
                        vista.getProyecto())));
		mapPorNumOp.forEach((key, value) -> {
			System.out.println("Key: " + key);
			System.out.println("Values: ");
			value.forEach(System.out::println);
			System.out.println("--------------------------");				
		});
		
		List<OpCentroTrabajoDTO> listaOpsCt = mapPorNumOp.entrySet().stream()
				.map(entry -> {
					List<ItemOpCtDTO> items = entry.getValue().stream()
                            .map(VistaPiezasOperariosServiceImpl::crearItemProduccion)
                            .collect(Collectors.toList());
					OpCentroTrabajoDTO ordenProduccion = new OpCentroTrabajoDTO();
                    ordenProduccion.setIdOp(entry.getValue().get(0).getIdOp());
                    ordenProduccion.setNumOp(entry.getValue().get(0).getNumOp());
                    ordenProduccion.setTipoOp(entry.getValue().get(0).getTipoOp());
                    ordenProduccion.setCliente(entry.getValue().get(0).getCliente());
                    ordenProduccion.setProyecto(entry.getValue().get(0).getProyecto());
                    ordenProduccion.setEsquemaPintura(entry.getValue().get(0).getEsquemaPintura());
                    ordenProduccion.setItems(items);

                    return ordenProduccion;
                })
                .collect(Collectors.toList());

		Set<OpCentroTrabajoDTO> ordenesProduccion = new HashSet<>(listaOpsCt);
		for(OpCentroTrabajoDTO op:ordenesProduccion) {

			Set<ItemOpCtDTO> setItems = new HashSet<>(op.getItems());
			List<ItemOpCtDTO> conjuntoItems = new ArrayList<>(mergeItems(new ArrayList<>(setItems)));
			
			op.setItems(conjuntoItems);
		}
		ordenesProduccion.forEach(System.out::println);
		/*
		Map<String, List<ItemOpCtDTO>> groupedConjunto = listaItemsCt.stream()
				.collect(Collectors.groupingBy(VistaPiezasOperariosServiceImpl::generateKeyConjunto));
		
		
		groupedConjunto.forEach((key, value) -> {
				System.out.println("Key: " + key);
				System.out.println("Values: ");
				value.forEach(System.out::println);
				System.out.println("--------------------------");				
		});
		
		Set<ItemOpCtDTO> setP = groupedConjunto.entrySet().stream()
	            .map(entry -> mergePiezas(entry.getValue()))
	            .collect(Collectors.toSet());
		
		setP.forEach(System.out::println);*/
		
		return ordenesProduccion;
	}
	
	public static Set<ItemOpCtDTO> mergeItems(List<ItemOpCtDTO> items) {
        Map<String, List<ItemOpCtDTO>> groupedItems = items.stream()
                .collect(Collectors.groupingBy(VistaPiezasOperariosServiceImpl::generateKeyConjunto));

        return groupedItems.values().stream()
                .map(VistaPiezasOperariosServiceImpl::mergePiezas)
                .collect(Collectors.toSet());
    }
	
	private static String generateKeyConjunto(ItemOpCtDTO item) {
		return item.getIdItemFab() + "_" + item.getDescripcion() + "_" + item.getCant() ;
    }

	
	private static ItemOpCtDTO mergePiezas(List<ItemOpCtDTO> lista) {
		ItemOpCtDTO mergedItem = new ItemOpCtDTO();
		mergedItem.setIdItem(lista.get(0).getIdItem());
		mergedItem.setIdItemFab(lista.get(0).getIdItemFab());
		mergedItem.setDescripcion(lista.get(0).getDescripcion());
		mergedItem.setCant(lista.get(0).getCant());
		mergedItem.setIdCentroTrabajo(lista.get(0).getIdCentroTrabajo());
		mergedItem.setPeso(lista.get(0).getPeso());
		
		List<ComponenteDTO> mergedComponentes = lista.stream()
                .flatMap(item -> item.getComponentes().stream())
                .distinct()
                .collect(Collectors.toList());

        mergedItem.setComponentes(new HashSet<>(mergedComponentes));
		
		return mergedItem;
	}
	
    private static ItemOpCtDTO crearItemProduccion(VistaPiezasOperarios vista) {
        ComponenteDTO componente = crearComponente(vista);
        ItemOpCtDTO itemProduccion = new ItemOpCtDTO();
        itemProduccion.setIdItem(vista.getIdItem());
        itemProduccion.setIdItemFab(vista.getIdItemFab());
        itemProduccion.setDescripcion(vista.getDescripcionItem());
        itemProduccion.setCant(vista.getCantOp());
        itemProduccion.setIdCentroTrabajo(vista.getCtConjunto());
        //itemProduccion.setCentroTrabajo(vista.getCentroTrabajoConjunto());
        itemProduccion.setPeso(vista.getPesoConjunto());
        //itemProduccion.setPintura(vista.getPintura());
        itemProduccion.setPrioridad(vista.getPrioridad());
        
        itemProduccion.addComponente(componente);
        return itemProduccion;
    }

    private static ComponenteDTO crearComponente(VistaPiezasOperarios vista) {
    	ComponenteDTO componente = new ComponenteDTO();
        componente.setIdPerfil(vista.getIdPerfil());
        componente.setCodErp(vista.getCodErp());
        componente.setDescripcionPerfil(vista.getDescripcionPerfil());
        componente.setCantListaMateriales(vista.getCantListaMateriales() * vista.getCantOp());
        componente.setIdCentroTrabajoPerfil(vista.getCtPerfil());
        //componente.setCentroTrabajoPerfil(vista.getCentroTrabajoPerfil());
        componente.setLongitud(vista.getLongitud());
        componente.setPesoPerfil(vista.getPesoPerfil());

        return componente;
    }

}
