package com.almatec.controlpiso.integrapps.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasOperarios;
import com.almatec.controlpiso.integrapps.repositories.VistaPiezasOperariosRepository;
import com.almatec.controlpiso.integrapps.services.VistaPiezasOperariosService;

@Service
public class VistaPiezasOperariosServiceImpl implements VistaPiezasOperariosService {
	
	@Autowired
	private VistaPiezasOperariosRepository vistaPiezasOperariosRepository;

	@Override
	public List<VistaPiezasOperarios> obtenerPiezasOperarioProceso(OperarioDTO operario) {
		System.out.println("Servicio piezas Operario");
		List<VistaPiezasOperarios> lista = vistaPiezasOperariosRepository.findPiezasOperariosProceso(operario);
		/*Map<ClaveAgrupacion, List<VistaPiezasOperarios>> mapPorProyecto = agruparPorProyecto(lista);
		
		List<OpCentroTrabajoDTO> listaOpsCt = generarEstructuraOp(mapPorProyecto);

		Set<OpCentroTrabajoDTO> ordenesProduccion = agruparItems(listaOpsCt);		
		return ordenesProduccion;*/
		
		return lista;
		
	}

	/*private Map<ClaveAgrupacion, List<VistaPiezasOperarios>> agruparPorProyecto(List<VistaPiezasOperarios> lista) {
		Map<ClaveAgrupacion, List<VistaPiezasOperarios>> mapPorProyecto = lista.stream()
                .collect(Collectors.groupingBy(
                		vista -> new ClaveAgrupacion(
                        vista.getCliente(),
                        vista.getProyecto(),
                        vista.getIdOp())));
		mapPorProyecto.forEach((key, value) -> {
			System.out.println("Key: " + key);
			System.out.println("Values: ");
			value.forEach(System.out::println);
			System.out.println("--------------------------");				
		});
		return mapPorProyecto;
	}

	private Set<OpCentroTrabajoDTO> agruparItems(List<OpCentroTrabajoDTO> listaOpsCt) {
		Set<OpCentroTrabajoDTO> ordenesProduccion = new HashSet<>(listaOpsCt);
		for(OpCentroTrabajoDTO op:ordenesProduccion) {
			Set<ItemOpCtDTO> setItems = new HashSet<>(op.getItems());
			List<ItemOpCtDTO> conjuntoItems = new ArrayList<>(mergeItems(new ArrayList<>(setItems)));			
			op.setItems(conjuntoItems);
		}
		ordenesProduccion.forEach(System.out::println);
		return ordenesProduccion;
	}

	private List<OpCentroTrabajoDTO> generarEstructuraOp(Map<ClaveAgrupacion, List<VistaPiezasOperarios>> mapPorNumOp) {
		return mapPorNumOp.entrySet().stream()
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
    	System.out.println("creando itemDTO");
    	System.out.println(vista);
        ItemOpCtDTO itemProduccion = new ItemOpCtDTO();
        itemProduccion.setIdItem(vista.getIdItem());
        itemProduccion.setIdItemFab(vista.getIdItemFab());
        itemProduccion.setDescripcion(vista.getDescripcionItem());
        itemProduccion.setCant(vista.getCantOp());
        itemProduccion.setIdCentroTrabajo(vista.getCt());
        itemProduccion.setPeso(vista.getPeso());
        itemProduccion.setPrioridad(vista.getPrioridad());
        System.out.println(itemProduccion);
        ComponenteDTO componente = crearComponente(vista);
        itemProduccion.addComponente(componente);
        return itemProduccion;
    }

    private static ComponenteDTO crearComponente(VistaPiezasOperarios vista) {
    	ComponenteDTO componente = new ComponenteDTO();
        //componente.setIdPerfil(vista.getIdPerfil());
        componente.setCodErp(vista.getCodErp());
        //componente.setDescripcionPerfil(vista.getDescripcionPerfil());
        componente.setCantListaMateriales(vista.getCantListaMateriales() * vista.getCantOp());
        //componente.setIdCentroTrabajoPerfil(vista.getCtPerfil());
        componente.setLongitud(vista.getLongitud());
        //componente.setPesoPerfil(vista.getPesoPerfil());

        return componente;
    }*/
    
    @Override
    public List<VistaPiezasOperarios> obtenerPiezasCentroTrabajoProceso(Integer idCT, Integer idConfig) {
		List<VistaPiezasOperarios> lista = vistaPiezasOperariosRepository.findPiezasOperariosProceso(idCT, idConfig);
		/*Set<PiezaCTProcesoDTO> piezas = lista.stream()
				.map(item -> {
					//System.out.println(item);
					PiezaCTProcesoDTO pieza = new PiezaCTProcesoDTO();
					pieza.setCliente(item.getCliente());
					pieza.setProyecto(item.getProyecto().trim());
					pieza.setIsActive(item.getIsActivo());
					pieza.setIdOperario(item.getIdOperario());
					pieza.setIdConfigProceso(item.getIdProceso());
					pieza.setIdItem(item.getIdItem());
					pieza.setTipoOp(item.getTipoOp());
					pieza.setNumOp(item.getNumOp());
					pieza.setTiempoTrancurrido(item.getTiempoTrancurrido());
					pieza.setTiempoReproceso(item.getTiempoReproceso());
					pieza.setNombreOperario(item.getNombreOperario());

					return pieza;
				})
				.collect(Collectors.toSet());
		
		piezas.forEach(System.out::println);*/

		return lista;
    }

}
