package com.almatec.controlpiso.programacion.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.almatec.controlpiso.integrapps.entities.VistaPiezasReportadas;
import com.almatec.controlpiso.programacion.dtos.ComponenteDTO;
import com.almatec.controlpiso.programacion.dtos.ItemDTO;
import com.almatec.controlpiso.programacion.dtos.OrdenProduccionResumen;
import com.almatec.controlpiso.utils.ClaveAgrupacion;

public class EstructuraOrdenProduccionResumen {
	
	public static List<OrdenProduccionResumen> crearEstructura(List<VistaPiezasReportadas> lista){
		
		Map<ClaveAgrupacion, List<VistaPiezasReportadas>> mapLista = lista.stream()
				.collect(Collectors.groupingBy(
						registro -> new ClaveAgrupacion(
								registro.getCliente(),
								registro.getUn(),
								registro.getIdOpIntegrapps())));
		return mapLista.entrySet().stream()
				.map(entry -> {
					List<ItemDTO> items = entry.getValue().stream()
							.map(EstructuraOrdenProduccionResumen::crearItem)
							.collect(Collectors.toList());
					
					OrdenProduccionResumen ordenProduccion = new OrdenProduccionResumen();
					ordenProduccion.setIdOpIntegrapps(entry.getValue().get(0).getIdOpIntegrapps());
					ordenProduccion.setCliente(entry.getValue().get(0).getCliente());
					ordenProduccion.setOp(entry.getValue().get(0).getOp());
					ordenProduccion.setEsquemaPintura(entry.getValue().get(0).getEsquemaPintura());
					ordenProduccion.setUn(entry.getValue().get(0).getUn());
					ordenProduccion.setZona(entry.getValue().get(0).getZona());
					ordenProduccion.setItems(items);
					
					return ordenProduccion;
				})
				.collect(Collectors.toList());
	}
	
	private static ItemDTO crearItem(VistaPiezasReportadas registro) {
		ItemDTO item = new ItemDTO();
		if(registro.getMaterialId() != null) {
			ComponenteDTO componente = crearComponente(registro);
			item.addComponente(componente);
		}else {
			item.setMateriaPrimaId(registro.getMateriaPrimaId());
			item.setMateriaPrimaDescripcion(registro.getMateriaPrimaDescripcion());
			item.setMateriaPrimaCant(registro.getMateriaPrimaCant());			
		}
		item.setItemOpId(registro.getItemOpId());
		item.setItemId(registro.getItemId());
		item.setItemDescripcion(registro.getItemDescripcion());
		item.setItemPeso(registro.getItemPeso());
		item.setItemLongitud(registro.getItemLongitud());
		item.setItemColor(registro.getItemColor());
		item.setItemCentroTId(registro.getItemCentroTId());
		item.setItemCentroTNombre(registro.getItemCentroTNombre());
		item.setGrupo(registro.getGrupo());
		item.setMarca(registro.getMarca());
		item.setCantReq(registro.getCantReq());
		item.setCantCumplida(registro.getCantCumplida());
		item.setCantReportadaPieza(registro.getCantReportadaPieza());
		item.setCantPendientePieza(registro.getCantPendientePieza());
		item.setPrioridad(registro.getPrioridad());
		
		return item;
	}

	private static ComponenteDTO crearComponente(VistaPiezasReportadas registro) {
		ComponenteDTO componente = new ComponenteDTO();
		componente.setMaterialId(registro.getMaterialId());
		componente.setMaterialDescripcion(registro.getMaterialDescripcion());
		componente.setMaterialPeso(registro.getMaterialPeso());
		componente.setMaterialLongitud(registro.getMaterialLongitud());
		componente.setMaterialCant(registro.getMaterialCant());
		componente.setMaterialCentroTId(registro.getMaterialCentroTId());
		componente.setMaterialCentroTNombre(registro.getMaterialCentroTNombre());
		componente.setMateriaPrimaId(registro.getMateriaPrimaId());
		componente.setMateriaPrimaDescripcion(registro.getMateriaPrimaDescripcion());
		componente.setMateriaPrimaCant(registro.getMateriaPrimaCant());
		componente.setCantReportadaMaterial(registro.getCantReportadaMaterial());
		componente.setCantPendienteMaterial(registro.getCantPendienteMaterial());
		return componente;
	}

}
