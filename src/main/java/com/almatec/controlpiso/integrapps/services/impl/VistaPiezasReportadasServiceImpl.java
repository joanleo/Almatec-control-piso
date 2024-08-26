package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.almatec.controlpiso.integrapps.entities.VistaPiezasReportadas;
import com.almatec.controlpiso.integrapps.repositories.VistaPiezasReportadasRepository;
import com.almatec.controlpiso.integrapps.services.VistaPiezasReportadasService;
import com.almatec.controlpiso.programacion.dtos.ComponenteDTO;
import com.almatec.controlpiso.programacion.dtos.ItemDTO;
import com.almatec.controlpiso.programacion.dtos.OrdenProduccionResumen;
import com.almatec.controlpiso.programacion.utils.EstructuraOrdenProduccionResumen;

@Service
public class VistaPiezasReportadasServiceImpl implements VistaPiezasReportadasService {

	@Autowired
	private VistaPiezasReportadasRepository vistaPiezasReportadasRepo;
	
	@Override
	public Set<OrdenProduccionResumen> buscarOps(Integer idCT) {
		List<VistaPiezasReportadas> listaPiezas = vistaPiezasReportadasRepo.findByItemCentroTIdOrMaterialCentroTIdOrderByIdOpIntegrappsDesc(idCT, idCT);
		
		Set<OrdenProduccionResumen> ordenesProducion = new HashSet<>();
		
		if(!listaPiezas.isEmpty()) {
			List<OrdenProduccionResumen> listaOrdenesProduccion = EstructuraOrdenProduccionResumen.crearEstructura(listaPiezas);
			ordenesProducion = new HashSet<>(listaOrdenesProduccion);
			
			for(OrdenProduccionResumen op: ordenesProducion) {
				Set<ItemDTO> setItems = new HashSet<>(op.getItems());
				List<ItemDTO> listaItems = new ArrayList<>(mergeItems(new ArrayList<>(setItems)));
				op.setItems(listaItems);
			}
		}
		
		return ordenesProducion;
	}

	private static Set<ItemDTO> mergeItems(List<ItemDTO> items) {
		Map<String, List<ItemDTO>> groupedItems = items.stream()
				.collect(Collectors.groupingBy(VistaPiezasReportadasServiceImpl::generateKey));
		return groupedItems.values().stream()
				.map(VistaPiezasReportadasServiceImpl::mergeItemsDTO)
				.collect(Collectors.toSet());
	}
	
	private static String generateKey(ItemDTO item) {
				
		return item.getItemOpId() + "_" + item.getItemId() + "_" + item.getItemDescripcion() + "_" + item.getMarca();
	}
	
	private static ItemDTO mergeItemsDTO(List<ItemDTO> registro) {
		ItemDTO item = new ItemDTO();
		item.setItemOpId(registro.get(0).getItemOpId());
		item.setItemId(registro.get(0).getItemId());
		item.setItemDescripcion(registro.get(0).getItemDescripcion());
		item.setItemPeso(registro.get(0).getItemPeso());
		item.setItemLongitud(registro.get(0).getItemLongitud());
		item.setItemColor(registro.get(0).getItemColor());
		item.setItemCentroTId(registro.get(0).getItemCentroTId());
		item.setItemCentroTNombre(registro.get(0).getItemCentroTNombre());
		item.setGrupo(registro.get(0).getGrupo());
		item.setMarca(registro.get(0).getMarca());
		item.setCantReq(registro.get(0).getCantReq());
		item.setCantCumplida(registro.get(0).getCantCumplida());
		item.setCantReportadaPieza(registro.get(0).getCantReportadaPieza());
		item.setCantPendientePieza(registro.get(0).getCantPendientePieza());
		item.setPrioridad(registro.get(0).getPrioridad());
		
		item.setMateriaPrimaId(registro.get(0).getMateriaPrimaId());
		item.setMateriaPrimaDescripcion(registro.get(0).getMateriaPrimaDescripcion());
		item.setMateriaPrimaCant(registro.get(0).getMateriaPrimaCant());
		
		Set<ComponenteDTO> mergedComponentes = registro.stream()
				.flatMap(element -> element.getComponentes().stream())
						.distinct()
						.collect(Collectors.toSet());
		
		item.setComponentes(new HashSet<>(mergedComponentes));
		
		return item;
	}

}
