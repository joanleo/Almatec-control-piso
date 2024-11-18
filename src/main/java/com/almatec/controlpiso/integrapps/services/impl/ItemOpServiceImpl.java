package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpId;
import com.almatec.controlpiso.integrapps.dtos.DataItemImprimirDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCTPrioridadDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpDatable;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ConsultaOpIdInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemListaMateriaInterface;
import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;
import com.almatec.controlpiso.integrapps.paging.Column;
import com.almatec.controlpiso.integrapps.paging.Direction;
import com.almatec.controlpiso.integrapps.paging.EntityComparators;
import com.almatec.controlpiso.integrapps.paging.Order;
import com.almatec.controlpiso.integrapps.paging.Page;
import com.almatec.controlpiso.integrapps.paging.PageArray;
import com.almatec.controlpiso.integrapps.paging.PagingRequest;
import com.almatec.controlpiso.integrapps.repositories.ItemOpRepository;
import com.almatec.controlpiso.integrapps.services.EventoService;
import com.almatec.controlpiso.integrapps.services.ItemOpService;



@Service
public class ItemOpServiceImpl implements ItemOpService {
	
	private static final Comparator<ItemOpDatable> EMPTY_COMPARATOR = (e1, e2) -> 0;
	
	@Autowired
	private ItemOpRepository itemOpRepo;
	
	@Autowired
	private EventoService eventoService;

	@Override
	public List<ItemOp> obtenerItemsOp(Integer idOp) {
		List<ItemOpInterface> listaItemsOpInterface = itemOpRepo.obtenerItemsOp(idOp);
		List<ItemOp> itemsOp = new ArrayList<>();
		for(ItemOpInterface itemInterface: listaItemsOpInterface) {
			ItemOp item = new ItemOp(itemInterface);
			itemsOp.add(item);
		}
		return itemsOp;
	}
	
	public PageArray obtenerItemsOpArray(PagingRequest pagingRequest, Integer idOp) {
		pagingRequest.setColumns(Stream.of("itemId", "marca", "descripcion", "cant",
				"peso", "cantPentiente","pesoPendiente","color")
				.map(Column :: new)
                .collect(Collectors.toList()));
		
		Page<ItemOpDatable> itemsOpPage = obtenerItemsOpPage(pagingRequest, idOp);
		
		PageArray pageArray = new PageArray();
		pageArray.setRecordsFiltered(itemsOpPage.getRecordsFiltered());
		pageArray.setRecordsTotal(itemsOpPage.getRecordsTotal());
		pageArray.setDraw(itemsOpPage.getDraw());
		pageArray.setData(itemsOpPage.getData()
                                      .stream()
                                      .map(this::toStringList)
                                      .collect(Collectors.toList()));
        return pageArray;
	}
	
	private List<String> toStringList(ItemOpDatable itemOp) {
        return Arrays.asList(itemOp.getItemId().toString(), itemOp.getMarca(), itemOp.getDescripcion(),
        		itemOp.getCant().toString(), itemOp.getPeso().toString(), itemOp.getCantPentiente().toString(),
        		itemOp.getPesoPendiente().toString(), itemOp.getColor());
    }

	Page<ItemOpDatable> obtenerItemsOpPage(PagingRequest pagingRequest, Integer idOp) {
		List<ItemOpDatable> itemsOp = obtenerItemsOpDataTable(idOp);
		return obtenerPagina(itemsOp, pagingRequest);
	}

	public Page<ItemOpDatable> obtenerPagina(List<ItemOpDatable> itemsOp, PagingRequest pagingRequest) {
		List<ItemOpDatable> filtered = itemsOp.stream()
                .sorted(sortItemsOp(pagingRequest))
                .filter(filterItemsOp(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());
		
		long count = itemsOp.stream()
                .filter(filterItemsOp(pagingRequest))
                .count();

		Page<ItemOpDatable> page = new Page<>(filtered);
		page.setRecordsFiltered((int) count);
		page.setRecordsTotal((int) count);
		page.setDraw(pagingRequest.getDraw());

		return page;
	}

	@Override
	public List<ItemOp> obtenerItemsOpC2(String idGrupo) {
		return itemOpRepo.obtenerItemsOpC2(idGrupo);
	}


	@Override
	public List<ItemOp> buscarItemsOp(Integer numOp) {
		return itemOpRepo.findByIdOpIntegrapps(numOp);
	}
	
	@SuppressWarnings("deprecation")
	private Predicate<ItemOpDatable> filterItemsOp(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                                                                                  .getValue())) {
            return itemOp -> true;
        }

        String value = pagingRequest.getSearch()
                                    .getValue();

        return itemOp -> itemOp.getItemId()
                                   .toString()
                                   .contains(value)
                || itemOp.getMarca()
                           .toLowerCase()
                           .contains(value)
                || itemOp.getDescripcion()
                           .toLowerCase()
                           .contains(value);
    }
	
	private Comparator<ItemOpDatable> sortItemsOp(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                                       .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                                         .get(columnIndex);

            EntityComparators<ItemOpDatable> itemOpComparator = EntityComparators.create();
            itemOpComparator.addComparator("itemId", Direction.asc, ItemOpDatable::getItemId);
            itemOpComparator.addComparator("itemId", Direction.desc, ItemOpDatable::getItemId);

            itemOpComparator.addComparator("marca", Direction.asc, ItemOpDatable::getMarca);
            itemOpComparator.addComparator("marca", Direction.desc, ItemOpDatable::getMarca);

            itemOpComparator.addComparator("descripcion", Direction.asc, ItemOpDatable::getDescripcion);
            itemOpComparator.addComparator("descripcion", Direction.desc, ItemOpDatable::getDescripcion);
            
            Comparator<ItemOpDatable> comparator = itemOpComparator.getComparator(column.getData(), order.getDir());
            if (comparator == null) {
                return EMPTY_COMPARATOR;
            }

            return comparator;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return EMPTY_COMPARATOR;
    }

	@Override
	public List<ItemOp> obtenerItemsOpProduccion(Integer numOp) {
		List<ItemOpInterface> listaItemsOpInterface = itemOpRepo.obtenerItemsOp(numOp);
		List<ItemOp> itemsOp = new ArrayList<>();
		for(ItemOpInterface itemInterface: listaItemsOpInterface) {
			ItemOp item = new ItemOp(itemInterface);
			itemsOp.add(item);
		}
		return itemsOp;
	}

	@Override
	public ItemOp obtenerItemPorId(Long itemId) {
		return itemOpRepo.findById(itemId)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro el item con id: " + itemId));
	}

	@Override
	public ItemOp guardarItemOp(ItemOp item) {
		return itemOpRepo.saveAndFlush(item);		
	}

	@Override
	public String obtenerStringListaMateriales(Integer id) {
		return itemOpRepo.obtenerJsonPorId(id);
	}

	@Override
	public String obtenerStringRuta(Integer id) {
		return itemOpRepo.obtenerJsonPorId(id);
	}

	@Override
	public List<ConsultaOpId> obtenerNumOps() {
		List<ConsultaOpIdInterface> numOpInterface = itemOpRepo.obtenerNumsOps();
		List<ConsultaOpId> numOps = new ArrayList<>();
		numOpInterface.forEach(item->{
			ConsultaOpId nuevo = new ConsultaOpId(item.getid_op_ia(), item.getNum_Op(), item.getDescripcion());
			numOps.add(nuevo);
		});
		return numOps;
	}

	@Override
	public String obtenerStringPorIdOPIntegrappsYTipo(Integer idOPI, String ruta) {
		return itemOpRepo.obtenerJsonPorIdOPIntegrappsYTipo(idOPI, ruta);
	}
	
	@Override
	public Double obtenerValorAplicarTepItemCentroTrabajo(Integer idItemFab, Integer idCentroTrabajo) {
		return itemOpRepo.obtenerValorAplicarTepItemCentroTrabajo(idItemFab, idCentroTrabajo);
	}

	@Override
	public ItemInterface obtenerItemFabricaPorId(Integer idItem) {
		try {
			return itemOpRepo.obtenerItemFabricaPorId(idItem);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ItemListaMateriaInterface> obtenerListaMaterialesItemPorIdItem(Integer idItem, Integer idFab) {
		try {
			return itemOpRepo.obtenerListaMaterialesItemPorIdItem(idItem, idFab);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public void imprimirEtiquetas(List<DataItemImprimirDTO> listData) {
		
		listData.forEach(data->
			eventoService.crearEventoImpresionEtiquetas(data)			
		);
		
	}

	@Override
	public List<ItemOpDatable> obtenerItemsOpDataTable(Integer idOp) {
		List<ItemOpInterface> listaItemsOpInterface = itemOpRepo.obtenerItemsOp(idOp);
		List<ItemOpDatable> itemsOp = new ArrayList<>();
		for(ItemOpInterface itemInterface: listaItemsOpInterface) {
			ItemOpDatable item = new ItemOpDatable(itemInterface);
			itemsOp.add(item);
		}
		return itemsOp;
	}

	@Override
	public List<ItemOpCTPrioridadDTO> findOpsItemsPorCentroTrabajo(Integer idCT) {
		List<ItemOpCTPrioridadDTO> opItems = itemOpRepo.findOpsItemsPorCentroTrabajo(idCT);
		return opItems;
	}

	@Override
	public List<Integer> obtenerCentrosTrabajoPorIdOpIA(Integer idOpIntegrapps) {
		try {
			List<Integer> ids = itemOpRepo.buscarCentrosTrabajoPorIdOpIA(idOpIntegrapps); 
			return ids;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Integer obtenerIdOpIntegrappsPorIdItem(Long idItem) {
		return itemOpRepo.buscarIdOpIntegrappsPorIdItem(idItem);
	}

	

}
