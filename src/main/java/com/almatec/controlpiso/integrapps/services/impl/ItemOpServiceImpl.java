package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.almatec.controlpiso.integrapps.dtos.ItemDTO;
import com.almatec.controlpiso.integrapps.dtos.ItemOpDTO;
import com.almatec.controlpiso.integrapps.dtos.OpDTO;
import com.almatec.controlpiso.integrapps.entities.Item;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.OrdenPv;
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
import com.almatec.controlpiso.integrapps.services.ItemOpService;
import com.almatec.controlpiso.integrapps.services.ItemService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.integrapps.services.RutaItemService;


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
		return itemOpRepo.findByIdOpIntegrapps(numOp);
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
					OrdenPv ordenPv = ordenPvService.obtenerOrdenPorId(opDTO.getIdOp());
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
		opsDTO.forEach(System.out::println);
		
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
		System.out.println("Obteniendo itemFa");
		try {
			return itemOpRepo.obtenerItemFabricaPorId(idItem);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ItemListaMateriaInterface> obtenerListaMaterialesItemPorIdItem(Integer idItem) {
		try {
			return itemOpRepo.obtenerListaMaterialesItemPorIdItem(idItem);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
