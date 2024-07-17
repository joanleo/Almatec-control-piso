package com.almatec.controlpiso.almacen.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.dto.DetalleRemisionDTO;
import com.almatec.controlpiso.almacen.dto.RemisionDTO;
import com.almatec.controlpiso.almacen.interfaces.DetalleRemisionInterface;
import com.almatec.controlpiso.almacen.interfaces.EncabezadoRemision;
import com.almatec.controlpiso.almacen.service.DetalleRemisionService;
import com.almatec.controlpiso.almacen.service.RemisionService;
import com.almatec.controlpiso.integrapps.entities.DetalleRemision;
import com.almatec.controlpiso.integrapps.entities.ItemOp;
import com.almatec.controlpiso.integrapps.entities.Remision;
import com.almatec.controlpiso.integrapps.interfaces.OpConItemPendientePorRemision;
import com.almatec.controlpiso.integrapps.repositories.ItemOpRepository;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;
import java.util.Collections;

@Service
public class AlmacenService {
	
	@Autowired
	private ItemOpRepository itemOpRepo;
	
	@Autowired
	private RemisionService remisionService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DetalleRemisionService detalleRemisionService;

	public List<OpConItemPendientePorRemision> obtenerOpActivasConItemsPendientesPorEntregar() {
		try {
			return itemOpRepo.buscarOpActivasConItemsPendientesPorEntregar();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public List<ItemOp> obtenerItemsARemisionarPorIdOpIa(Integer idOpIa) {
		try {
			List<ItemOp> itemsOp = itemOpRepo.buscarItemsARemisionarPorIdOpIa(idOpIa);
			return itemsOp;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public RemisionDTO guardarRemision(RemisionDTO remisionDTO) {
		try {
			Usuario usuario = usuarioService.buscarUsuarioPorId(remisionDTO.getIdUsuario());
			
			ModelMapper mapper = new ModelMapper();
			Remision remision = mapper.map(remisionDTO, Remision.class);
			
			List<DetalleRemision> detalles = remisionDTO.getDetalles().stream()
					.map(detalleDTO -> mapDetalleDTOToEntity(detalleDTO, remision))
					.collect(Collectors.toList());
			
			remision.setUsuarioCreaRemision(usuario);
			remision.setDetalles(detalles);
			
			Remision remisionSaved = remisionService.guardarRemision(remision);
			
			actualizarCantidadesDespachadas(remisionSaved);
			
			PropertyMap<DetalleRemision, DetalleRemisionDTO> detalleMap = new PropertyMap<DetalleRemision, DetalleRemisionDTO>() {				
				@Override
				protected void configure() {
					map().setItemOp(source.getItemOp().getId());
					map().setCantidad(source.getCantidad());					
				}
			};
			mapper.addMappings(detalleMap);
			
			return mapper.map(remisionSaved, RemisionDTO.class);
		} catch (IllegalArgumentException | NoSuchElementException e) {
            throw new RuntimeException("Error al guardar la remisión: " + e.getMessage(), e);
        }
	}
	
	private DetalleRemision mapDetalleDTOToEntity(DetalleRemisionDTO detalleDTO, Remision remision) {
        DetalleRemision detalle = new DetalleRemision();
        detalle.setCantidad(detalleDTO.getCantidad());
        ItemOp item = itemOpRepo.findById(detalleDTO.getItemOp())
                .orElseThrow(() -> new NoSuchElementException("ItemOp no encontrado: " + detalleDTO.getItemOp()));
        detalle.setItemOp(item);
        detalle.setRemision(remision);
        return detalle;
    }
	
	private void actualizarCantidadesDespachadas(Remision remisionSaved) {
        remisionSaved.getDetalles().forEach(detalle -> {
            ItemOp item = detalle.getItemOp();
            item.setCantDespachada(item.getCantDespachada() + detalle.getCantidad());
            itemOpRepo.save(item);
        });
    }

	public List<EncabezadoRemision> obtenerRemisiones() {
		try {
			List<EncabezadoRemision> remisiones = remisionService.obtenerTodasLasRemisiones();
			return remisiones;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public List<DetalleRemisionInterface> obtenerDetalleRemision(Long idRemision) {
			return detalleRemisionService.obtenerDetallesPorIdRemision(idRemision);
	}

	public EncabezadoRemision obtenerEncabezadoRemisionPorId(Long idRemision) {
		return remisionService.obtenerEncabezadoRemisionPorId(idRemision);
	}

	public Page<EncabezadoRemision> obtenerRemisionesPaginadas(int page, int size) {

		return remisionService.obtenerRemisionesPaginadas(page, size);

	}

}


