package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.repositories.ListaMaterialRepository;
import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.ingenieria.dtos.ComponenteDTO;
import com.almatec.controlpiso.ingenieria.dtos.ItemDetalladoDTO;
import com.almatec.controlpiso.ingenieria.dtos.ItemErpI;
import com.almatec.controlpiso.ingenieria.dtos.MateriaPrimaDTO;
import com.almatec.controlpiso.integrapps.entities.Item;
import com.almatec.controlpiso.integrapps.entities.ItemMaterial;
import com.almatec.controlpiso.integrapps.repositories.ItemMaterialRepository;
import com.almatec.controlpiso.integrapps.repositories.ItemRepository;
import com.almatec.controlpiso.integrapps.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private ItemMaterialRepository itemMaterialRepo;
	
	@Autowired
	private ListaMaterialRepository lmRepo;

	@Override
	public Item buscarItemFabrica(Integer idItemFab) {
		
		return itemRepo.findById(idItemFab)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro el item con id: " + idItemFab));
	}

	@Override
	public Item buscarItemFabricaPorIdItem(Integer idItemMAteriaPrima) {
		return itemRepo.findByIdItem(idItemMAteriaPrima);
	}

	@Override
	public Item guardarItem(Item itemReporte) {
		return itemRepo.saveAndFlush(itemReporte);
		
	}

	@Override
	public List<Item> buscarItems(String query, String tipo) {
		if (tipo != null && !tipo.isEmpty()) {
	        return itemRepo.searchWithType(query, tipo);
	    }
	    return itemRepo.search(query);
	}

	@Override
	public Page<Item> buscarItems(String query, String tipo, Pageable pageable) {
	    if (tipo != null && !tipo.isEmpty()) {
	        return itemRepo.searchWithType(query, tipo, pageable);
	    }
	    return itemRepo.search(query, pageable);
	}

	@Override
	public ItemDetalladoDTO buscarItemConDetalles(Integer id) {
		Item item = itemRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro el item con id: " + id));
	            
        return construirItemDetallado(item);
	}

	private ItemDetalladoDTO construirItemDetallado(Item item) {
		ItemDetalladoDTO dto = new ItemDetalladoDTO();
        dto.setIdItem(item.getIdItem());
        dto.setDescripcion(item.getDescripcion());
        dto.setTipo(item.getTipo());
        dto.setPlano(item.getPlano());
        dto.setPesoBruto(item.getPesoBruto());
        dto.setPesoNeto(item.getPesoNeto());
        
        if ("CONJUNTO".equals(item.getTipo())) {
        	System.out.println("Conjunto: " + item.getDescripcion());
            dto.setComponentes(buscarComponentes(item.getIdItem()));
        } else {
            // Si es ELEMENTO o PARTE, buscar materia prima
        	System.out.println("Elemento o parte: " + item.getDescripcion()+" " +item.getIdItem());
            dto.setMateriaPrima(buscarMateriaPrima(item.getIdItem()));
        }
        
        //dto.setRutaFabricacion(buscarRutaFabricacion(item.getIdItem()));
        
        return dto;        
	}

	private List<ComponenteDTO> buscarComponentes(Integer idItem) {
		List<ItemMaterial> materiales = itemMaterialRepo.findComponentes(idItem);
		System.out.println("  Componentes: ");
		//materiales.forEach(System.out::println);
        List<ComponenteDTO> componentes = new ArrayList<>();
        
        for (ItemMaterial material : materiales) {
            Item componenteItem = itemRepo.findById(material.getIdMateriaPrima())
            		.orElseThrow(()-> new ResourceNotFoundException("No se encontro el item con id: " + material.getIdMateriaPrima()));
                
            ComponenteDTO comp = new ComponenteDTO();
            comp.setIdItem(componenteItem.getIdItem());
            comp.setDescripcion(componenteItem.getDescripcion());
            comp.setTipo(componenteItem.getTipo());
            comp.setPlano(componenteItem.getPlano());
            comp.setCantidad(material.getCantidad());
            
            System.out.println("    "+componenteItem);
            // Recursivamente buscar subcomponentes si es CONJUNTO
            if ("CONJUNTO".equals(componenteItem.getTipo())) {
                comp.setSubcomponentes(buscarComponentes(componenteItem.getIdItem()));
            } else {
                comp.setMateriaPrima(buscarMateriaPrima(componenteItem.getIdItem()));
            }
            
            componentes.add(comp);
        }
        
        return componentes;
	}

	private List<MateriaPrimaDTO> buscarMateriaPrima(Integer idItem) {
        List<ItemMaterial> materiales = itemMaterialRepo.findComponentes(idItem);
        System.out.println("      Materia prima: " + materiales.get(0));
        return materiales.stream()
            .map(mat -> {
                ItemErpI mp = lmRepo.obtenerItemERp(mat.getIdMateriaPrima());
                    
                MateriaPrimaDTO mpDto = new MateriaPrimaDTO();
                mpDto.setCodErp(mat.getIdMateriaPrima());
                mpDto.setDescripcion(mp.getDescripcion());
                mpDto.setCantidad(mat.getCantidad());
                mpDto.setUnidad(mp.getUm());
                return mpDto;
            })
            .collect(Collectors.toList());
    }

}
