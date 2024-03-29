package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.interfaces.ItemOpInterface;
import com.almatec.controlpiso.integrapps.paging.Column;
import com.almatec.controlpiso.integrapps.paging.Direction;
import com.almatec.controlpiso.integrapps.paging.EntityComparators;
import com.almatec.controlpiso.integrapps.paging.Order;
import com.almatec.controlpiso.integrapps.paging.Page;
import com.almatec.controlpiso.integrapps.paging.PageArray;
import com.almatec.controlpiso.integrapps.paging.PagingRequest;
import com.almatec.controlpiso.integrapps.repositories.ItemOpRepository;
import com.almatec.controlpiso.integrapps.services.ItemOpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemOpServiceImpl implements ItemOpService {
	
	private static final Comparator<ItemOp> EMPTY_COMPARATOR = (e1, e2) -> 0;
	
	@Autowired
	private ItemOpRepository itemOpRepo;

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
		pagingRequest.setColumns(Stream.of("codigoErp", "marca", "descripcion", "cant",
				"peso", "codigoErp","codigoErp","codigoErp")
				.map(Column :: new)
                .collect(Collectors.toList()));
		
		Page<ItemOp> itemsOpPage = obtenerItemsOpPage(pagingRequest, idOp);
		
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
	
	private List<String> toStringList(ItemOp itemOp) {
        return Arrays.asList(itemOp.getCodigoErp(), itemOp.getMarca(), itemOp.getDescripcion(),
        		itemOp.getCant().toString(), itemOp.getPeso().toString(), itemOp.getCodigoErp(),
        		itemOp.getCodigoErp(), itemOp.getCodigoErp());
    }

	Page<ItemOp> obtenerItemsOpPage(PagingRequest pagingRequest, Integer idOp) {
		List<ItemOp> itemsOp = obtenerItemsOp(idOp);
		return obtenerPagina(itemsOp, pagingRequest);
	}

	public Page<ItemOp> obtenerPagina(List<ItemOp> itemsOp, PagingRequest pagingRequest) {
		List<ItemOp> filtered = itemsOp.stream()
                .sorted(sortEmployees(pagingRequest))
                .filter(filterItemsOp(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());
		
		long count = itemsOp.stream()
                .filter(filterItemsOp(pagingRequest))
                .count();

		Page<ItemOp> page = new Page<>(filtered);
		page.setRecordsFiltered((int) count);
		page.setRecordsTotal((int) count);
		page.setDraw(pagingRequest.getDraw());

		return page;
	}

	@Override
	public List<ItemOp> obtenerItemsOpC2(String idGrupo) {
		System.out.println("Padre: " + idGrupo);
		return itemOpRepo.obtenerItemsOpC2(idGrupo);
	}

	/*@Override
	public ItemOp obtenerItemsOp(String idGrupo) {
		return itemOpRepo.findById(Long.valueOf(idGrupo))
				.orElseThrow(()-> new ResourceNotFoundException("Item no encontrado"));
	}*/

	@Override
	public List<ItemOp> buscarItemsOp(Integer numOp) {
		return itemOpRepo.findByIdPvIntegrapps(numOp);
	}
	
	@SuppressWarnings("deprecation")
	private Predicate<ItemOp> filterItemsOp(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                                                                                  .getValue())) {
            return itemOp -> true;
        }

        String value = pagingRequest.getSearch()
                                    .getValue();

        return itemOp -> itemOp.getCodigoErp()
                                   .toLowerCase()
                                   .contains(value)
                || itemOp.getMarca()
                           .toLowerCase()
                           .contains(value)
                || itemOp.getDescripcion()
                           .toLowerCase()
                           .contains(value);
    }
	
	private Comparator<ItemOp> sortEmployees(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                                       .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                                         .get(columnIndex);

            EntityComparators<ItemOp> itemOpComparator = EntityComparators.create();
            itemOpComparator.addComparator("codigoErp", Direction.asc, ItemOp::getCodigoErp);
            itemOpComparator.addComparator("codigoErp", Direction.desc, ItemOp::getCodigoErp);

            itemOpComparator.addComparator("marca", Direction.asc, ItemOp::getMarca);
            itemOpComparator.addComparator("marca", Direction.desc, ItemOp::getMarca);

            itemOpComparator.addComparator("descripcion", Direction.asc, ItemOp::getDescripcion);
            itemOpComparator.addComparator("descripcion", Direction.desc, ItemOp::getDescripcion);
            
            Comparator<ItemOp> comparator = itemOpComparator.getComparator(column.getData(), order.getDir());
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
	public void guardarItemOp(ItemOp item) {
		itemOpRepo.save(item);		
	}

}
