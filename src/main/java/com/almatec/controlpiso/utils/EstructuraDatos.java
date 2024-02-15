package com.almatec.controlpiso.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemsRutas;

public class EstructuraDatos {
    public static List<OpCentroTrabajoDTO> crearEstructura(List<VistaItemsRutas> vistas) {
        Map<Integer, List<VistaItemsRutas>> mapPorNumOp = vistas.stream()
                .collect(Collectors.groupingBy(VistaItemsRutas::getNumOp));
        return mapPorNumOp.entrySet().stream()
                .map(entry -> {
                    List<ItemDTO> items = entry.getValue().stream()
                            .map(EstructuraDatos::crearItemProduccion)
                            .collect(Collectors.toList());

                    Set<ItemDTO> setItems = new HashSet<>(items);                    
                    Set<ItemDTO> conjuntoItems = mergeItems(new ArrayList<>(setItems));

                    OpCentroTrabajoDTO ordenProduccion = new OpCentroTrabajoDTO();
                    ordenProduccion.setIdOp(entry.getValue().get(0).getIdOp());
                    ordenProduccion.setNumOp(entry.getKey());
                    ordenProduccion.setTipoOp(entry.getValue().get(0).getTipoOp());
                    ordenProduccion.setCliente(entry.getValue().get(0).getCliente());
                    ordenProduccion.setProyecto(entry.getValue().get(0).getProyecto());
                    ordenProduccion.setItems(conjuntoItems);

                    return ordenProduccion;
                })
                .collect(Collectors.toList());
    }

    private static ItemDTO crearItemProduccion(VistaItemsRutas vista) {
        ComponenteDTO componente = crearComponente(vista);
        ItemDTO itemProduccion = new ItemDTO();
        itemProduccion.setId(vista.getItemFabId());
        itemProduccion.setDescripcion(vista.getDescripcionConjunto());
        itemProduccion.setCant(vista.getCantOp());
        itemProduccion.setIdCentroTrabajo(vista.getIdCentroTrabajoConjunto());
        itemProduccion.setCentroTrabajo(vista.getCentroTrabajoConjunto());
        itemProduccion.addComponente(componente);
        return itemProduccion;
    }

    private static ComponenteDTO crearComponente(VistaItemsRutas vista) {
    	ComponenteDTO componente = new ComponenteDTO();
        componente.setIdPerfil(vista.getIdPerfil());
        componente.setCodErp(vista.getCodErp());
        componente.setDescripcionPerfil(vista.getDescripcionPerfil());
        componente.setCantListaMateriales(vista.getCantListaMateriales());
        componente.setIdCentroTrabajoPerfil(vista.getIdCentroTrabajoPerfil());
        componente.setCentroTrabajoPerfil(vista.getCentroTrabajoPerfil());
        componente.setLongitud(vista.getLongitud());

        return componente;
    }
    
    public static Set<ItemDTO> mergeItems(List<ItemDTO> items) {
        // Agrupa los elementos por la clave que define la igualdad
        Map<String, List<ItemDTO>> groupedItems = items.stream()
                .collect(Collectors.groupingBy(EstructuraDatos::generateKey));

        return groupedItems.values().stream()
                .map(EstructuraDatos::mergeItemDTOs)
                .collect(Collectors.toSet());
    }
    
    private static String generateKey(ItemDTO item) {
        // Genera una clave basada en los campos que deben ser iguales
        return item.getId() + "_" + item.getDescripcion() + "_" + item.getCant() + "_" +
                item.getIdCentroTrabajo() + "_" + item.getCentroTrabajo();
    }

    private static ItemDTO mergeItemDTOs(List<ItemDTO> itemDTOs) {
        // Combina los componentes de los elementos en la lista
        ItemDTO mergedItem = new ItemDTO();
        mergedItem.setId(itemDTOs.get(0).getId());
        mergedItem.setDescripcion(itemDTOs.get(0).getDescripcion());
        mergedItem.setCant(itemDTOs.get(0).getCant());
        mergedItem.setIdCentroTrabajo(itemDTOs.get(0).getIdCentroTrabajo());
        mergedItem.setCentroTrabajo(itemDTOs.get(0).getCentroTrabajo());

        // Combina los componentes de todos los elementos
        List<ComponenteDTO> mergedComponentes = itemDTOs.stream()
                .flatMap(item -> item.getComponentes().stream())
                .distinct()
                .collect(Collectors.toList());

        mergedItem.setComponentes(new HashSet<>(mergedComponentes));
        return mergedItem;
    }
}

