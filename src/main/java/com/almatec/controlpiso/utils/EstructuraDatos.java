package com.almatec.controlpiso.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.almatec.controlpiso.integrapps.dtos.ComponenteDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCtDTO;
import com.almatec.controlpiso.integrapps.dtos.OpCentroTrabajoDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOpItemsMaterialesRuta;

public class EstructuraDatos {
    public static List<OpCentroTrabajoDTO> crearEstructura(List<VistaOpItemsMaterialesRuta> vistas) {
        Map<ClaveAgrupacion, List<VistaOpItemsMaterialesRuta>> mapPorNumOp = vistas.stream()
                .collect(Collectors.groupingBy(
                		vista -> new ClaveAgrupacion(
                        vista.getCliente(),
                        vista.getUn(),
                        vista.getIdOpIntegrapps())));
        return mapPorNumOp.entrySet().stream()
                .map(entry -> {
                    List<ItemOpCtDTO> items = entry.getValue().stream()
                            .map(EstructuraDatos::crearItemProduccion)
                            .collect(Collectors.toList());


                    OpCentroTrabajoDTO ordenProduccion = new OpCentroTrabajoDTO();
                    ordenProduccion.setIdOp(entry.getValue().get(0).getIdOpIntegrapps());
                    ordenProduccion.setOp(entry.getValue().get(0).getOp());
                    ordenProduccion.setCliente(entry.getValue().get(0).getCliente());
                    ordenProduccion.setProyecto(entry.getValue().get(0).getUn());
                    ordenProduccion.setZona(entry.getValue().get(0).getZona());
                    ordenProduccion.setEsquemaPintura(entry.getValue().get(0).getEsquema_pintura());
                    ordenProduccion.setItems(items);

                    return ordenProduccion;
                })
                .collect(Collectors.toList());
    }

    private static ItemOpCtDTO crearItemProduccion(VistaOpItemsMaterialesRuta vista) {
    	ItemOpCtDTO itemProduccion = new ItemOpCtDTO();
    	if(vista.getMaterial_id() != null) {
    		ComponenteDTO componente = crearComponente(vista);
    		itemProduccion.addComponente(componente);
    		
    	}
        itemProduccion.setItem_op_id(vista.getItem_op_id());
        itemProduccion.setItem_id(vista.getItem_id());
        itemProduccion.setItem_desc(vista.getItemDescripcion());
        itemProduccion.setCant_req(vista.getCantReq());
        itemProduccion.setItem_centro_t_id(vista.getItemCentroTId());
        itemProduccion.setItem_centro_t_nombre(vista.getItem_centro_t_nombre());
        itemProduccion.setItem_peso(vista.getItem_peso());
        itemProduccion.setItem_color(vista.getItem_color());
        itemProduccion.setPrioridad(vista.getPrioridad());
        itemProduccion.setLongitud(vista.getItem_long());
        itemProduccion.setCant_cumplida(vista.getCant_cumplida());
        itemProduccion.setMarca(vista.getMarca());
        itemProduccion.setPesoPintura(vista.getPesoPintura());
        return itemProduccion;
    }

    private static ComponenteDTO crearComponente(VistaOpItemsMaterialesRuta vista) {
    	ComponenteDTO componente = new ComponenteDTO();
        componente.setMaterial_id(vista.getMaterial_id());
        componente.setMaterial_desc(vista.getMaterial_desc());
        componente.setMaterial_cant(vista.getMaterial_cant()*(vista.getCantReq()));
        componente.setMaterial_centro_t_id(vista.getMaterialCentroTId());
        componente.setMaterial_centro_t_nombre(vista.getMaterial_centro_t_nombre());
        componente.setMaterial_peso(vista.getMaterial_peso());
        componente.setLongitud(vista.getMaterial_long());

        return componente;
    }
}

