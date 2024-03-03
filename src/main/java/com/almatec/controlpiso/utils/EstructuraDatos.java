package com.almatec.controlpiso.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCtDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemsRutas;

public class EstructuraDatos {
    public static List<OpCentroTrabajoDTO> crearEstructura(List<VistaItemsRutas> vistas) {
    	//vistas.forEach(vista->System.out.println(vista));
        Map<ClaveAgrupacion, List<VistaItemsRutas>> mapPorNumOp = vistas.stream()
                .collect(Collectors.groupingBy(
                		vista -> new ClaveAgrupacion(
                        vista.getCliente(),
                        vista.getProyecto())));
        //mapPorNumOp.forEach(vista->System.out.println(vista));
        return mapPorNumOp.entrySet().stream()
                .map(entry -> {
                    List<ItemOpCtDTO> items = entry.getValue().stream()
                            .map(EstructuraDatos::crearItemProduccion)
                            .collect(Collectors.toList());

                    //Set<ItemDTO> setItems = new HashSet<>(items);                    

                    OpCentroTrabajoDTO ordenProduccion = new OpCentroTrabajoDTO();
                    ordenProduccion.setIdOp(entry.getValue().get(0).getId().getIdOp());
                    ordenProduccion.setNumOp(entry.getValue().get(0).getNumOp());
                    ordenProduccion.setTipoOp(entry.getValue().get(0).getTipoOp());
                    ordenProduccion.setCliente(entry.getValue().get(0).getCliente());
                    ordenProduccion.setProyecto(entry.getValue().get(0).getProyecto());
                    ordenProduccion.setFechaContraActual(entry.getValue().get(0).getFechaContraActual());
                    ordenProduccion.setEsquemaPintura(entry.getValue().get(0).getEsquemaPintura());
                    ordenProduccion.setItems(items);

                    return ordenProduccion;
                })
                .collect(Collectors.toList());
    }

    private static ItemOpCtDTO crearItemProduccion(VistaItemsRutas vista) {
        ComponenteDTO componente = crearComponente(vista);
        ItemOpCtDTO itemProduccion = new ItemOpCtDTO();
        itemProduccion.setIdItem(vista.getIdItem());
        itemProduccion.setIdItemFab(vista.getItemFabId());
        itemProduccion.setDescripcion(vista.getDescripcionConjunto());
        itemProduccion.setCant(vista.getCantOp());
        itemProduccion.setIdCentroTrabajo(vista.getIdCentroTrabajoConjunto());
        itemProduccion.setCentroTrabajo(vista.getCentroTrabajoConjunto());
        itemProduccion.setPeso(vista.getPesoConjunto());
        itemProduccion.setPintura(vista.getPintura());
        itemProduccion.setPrioridad(vista.getPrioridad());
        
        itemProduccion.addComponente(componente);
        return itemProduccion;
    }

    private static ComponenteDTO crearComponente(VistaItemsRutas vista) {
    	ComponenteDTO componente = new ComponenteDTO();
        componente.setIdPerfil(vista.getIdPerfil());
        componente.setCodErp(vista.getCodErp());
        componente.setDescripcionPerfil(vista.getDescripcionPerfil());
        componente.setCantListaMateriales(vista.getCantListaMateriales() * vista.getCantOp());
        componente.setIdCentroTrabajoPerfil(vista.getIdCentroTrabajoPerfil());
        componente.setCentroTrabajoPerfil(vista.getCentroTrabajoPerfil());
        componente.setLongitud(vista.getLongitud());
        componente.setPesoPerfil(vista.getPesoPerfil());

        return componente;
    }
}

