package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpId;
import com.almatec.controlpiso.integrapps.dtos.DataItemImprimirDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpCTPrioridadDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpDatable;
import com.almatec.controlpiso.integrapps.dtos.OpDTO;
import com.almatec.controlpiso.integrapps.entities.Item;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.entities.RutaItem;
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
import com.almatec.controlpiso.integrapps.services.ItemService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.integrapps.services.RutaItemService;


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
			ConsultaOpId nuevo = new ConsultaOpId(item.getid_op_ia(), item.getNum_Op());
			numOps.add(nuevo);
		});
		return numOps;
	}

	@Override
	public String obtenerStringPorIdOPIntegrappsYTipo(Integer idOPI, String ruta) {
		return itemOpRepo.obtenerJsonPorIdOPIntegrappsYTipo(idOPI, ruta);
	}
	
	
	
	
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private RutaItemService rutaItemService;
	
	@Autowired 
	private OrdenPvService ordenPvService;

	@Override
	public List<OpDTO> buscarItemsOpActivos() {
		List<ItemOp> itemsOpActivos = itemOpRepo.findByIsActivoTrueAndIdItemFabIsNot(0);
		
		Map<Integer, List<ItemOp>> mapPorIdOp = itemsOpActivos.stream()
				.collect(Collectors.groupingBy(ItemOp::getIdOpIntegrapps));
		
		List<ItemOpDTO> itemsDTO = new ArrayList<>();
		List<OpDTO> opsDTO = mapPorIdOp.entrySet().stream()
				.map(entry ->{					
					OpDTO opDTO = new OpDTO();
					opDTO.setIdOp(entry.getKey());
					VistaOrdenPv ordenPv = ordenPvService.obtenerOrdenPorId(opDTO.getIdOp());
					opDTO.setTipoOp(ordenPv.getTipoOp());
					opDTO.setNumOp(ordenPv.getNumOp());
					opDTO.setCliente(ordenPv.getCliente());
					opDTO.setProyecto(ordenPv.getIdProyecto());
					opDTO.setFechaContraActual(ordenPv.getFechaContractual());
					opDTO.setEsquemaPintura(ordenPv.getEsquemaPintura());
					/*opDTO.setItemsOps(entry.getValue().stream()
		                        .map(this::crearItemsOpDTO)
		                        .collect(Collectors.toList()));
		                return opDTO;*/
					List<ItemOpDTO> filteredItemsDTO = itemsOpActivos.stream()
		                    .filter(item -> Objects.equals(item.getIdOpIntegrapps(), entry.getKey()))
		                    .map(item -> crearItemsOpDTO(item, itemsDTO))
		                    .collect(Collectors.toList());
					itemsDTO.addAll(filteredItemsDTO);
		            
		            opDTO.setItemsOps(filteredItemsDTO);
		            return opDTO;
					})
					
				.collect(Collectors.toList());
		
		return opsDTO;
		
	}
	
	private ItemOpDTO crearItemsOpDTO(ItemOp itemOp, List<ItemOpDTO> itemsDTO) {
	    // Verificar si ya existe un ItemDTO con el mismo idItemOp
	    Optional<ItemOpDTO> existingItemDTO = itemsDTO.stream()
	            .filter(dto -> Objects.equals(dto.getIdItemOp(), itemOp.getId()))
	            .findFirst();

	    if (existingItemDTO.isPresent()) {
	        // Si ya existe, sumar las cantidades
	        ItemOpDTO itemOpDTO = existingItemDTO.get();
	        itemOpDTO.setCant(itemOpDTO.getCant() + itemOp.getCant());
	        itemOpDTO.setCantCumplida(itemOpDTO.getCantCumplida() + itemOp.getCantCumplida());
	        return itemOpDTO;
	    } else {
	        // Si no existe, crear un nuevo ItemDTO
	        ItemOpDTO newItemOpDTO = new ItemOpDTO();
	        newItemOpDTO.setIdItemOp(itemOp.getId());
	        newItemOpDTO.setCant(itemOp.getCant());
	        newItemOpDTO.setCantCumplida(itemOp.getCantCumplida());
	        newItemOpDTO.setColor(itemOp.getColor());

	        // Obtener el item y su ruta
	        Item itemFabrica = itemService.buscarItemFabrica(itemOp.getIdItemFab());
	        List<RutaItem> ruta = rutaItemService.buscarRutaItem(itemFabrica.getIdItem());

	        newItemOpDTO.setItemDTO(new ItemDTO(itemFabrica, ruta));
	        return newItemOpDTO;
	    }
	}

	/*private ItemOpDTO crearItemsOpDTO(ItemOp itemOp) {
	    ItemOpDTO itemOpDTO = new ItemOpDTO();
	    itemOpDTO.setIdItemOp(itemOp.getId());
	    itemOpDTO.setCant(itemOp.getCant());
	    itemOpDTO.setCantCumplida(itemOp.getCantCumplida());
	    itemOpDTO.setColor(itemOp.getColor());

	    Item itemFabrica = itemService.buscarItemFabrica(itemOp.getIdItemFab());
	    List<RutaItem> ruta = rutaItemService.buscarRutaItem(itemFabrica.getIdItem());
	    itemOpDTO.setItemDTO(new ItemDTO(itemFabrica, ruta));

	    return itemOpDTO;
	}*/


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
		
		listData.forEach(data->{
			eventoService.crearEventoImpresionEtiquetas(data);			
		});
		
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

	

}
