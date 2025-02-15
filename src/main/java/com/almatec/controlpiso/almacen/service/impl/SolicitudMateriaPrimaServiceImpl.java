package com.almatec.controlpiso.almacen.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.dto.SolicitudDTO;
import com.almatec.controlpiso.almacen.service.DetalleSolicitudMateriaPrimaService;
import com.almatec.controlpiso.almacen.service.SolicitudMateriaPrimaService;
import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.ListaM;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.interfaces.SolicitudUsuarioInterface;
import com.almatec.controlpiso.integrapps.repositories.SolicitudMateriaPrimaRepository;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;

@Service
public class SolicitudMateriaPrimaServiceImpl implements SolicitudMateriaPrimaService {
	
	@Autowired
	private SolicitudMateriaPrimaRepository solicitudMateriaPrimaRepo;
	
	@Autowired
	private OrdenPvService ordenPvService;
	
	@Autowired
	private DetalleSolicitudMateriaPrimaService detalleSolService;
	
	@Autowired
	private ListaMService listaMService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public Integer obtenerConsecutivo() {
		try {
			Integer consecutivo = solicitudMateriaPrimaRepo.obtenerConsecutivo();
			if(consecutivo == null) {
				return 0;
			}
			return consecutivo;			
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	@Override
	public SolicitudMateriaPrima crearSolicitud(SolicitudMateriaPrima solicitud) {
		VistaOrdenPv ordenPv = ordenPvService.obtenerOrdenPorId(solicitud.getIdOp());
		solicitud.setTipoOp(ordenPv.getTipoOp());
		solicitud.setNumOp(ordenPv.getNumOp());
		return solicitudMateriaPrimaRepo.saveAndFlush(solicitud);
	}

	/**
	 *Actualiza solicitud, detalle de solicitud y listado de materia prima totalizado
	 *Tablas Mp_Sol, Mp_Sol_Det, Lista_MP_Ops
	 */
	@Override
	public void actualizaSolicitud(SolicitudMateriaPrima solicitud) {		
		solicitudMateriaPrimaRepo.save(solicitud);
		List<DetalleSolicitudMateriaPrima> detalleSol = detalleSolService.obtenerDetallePorIdSol(solicitud.getId());
		detalleSol.forEach(item->{
			item.setCantEntrega(item.getCantSol());
			item.setIdEstadoItem(1);
		});
		detalleSol = detalleSolService.actualizarDetalleSol(detalleSol);
		List<ListaM> listaMateriales = listaMService.obtenerListaMPorIdOp(solicitud.getIdOp());
		
		detalleSol.forEach(detalleItem -> 
	        listaMateriales.stream()
	                .filter(listaItem -> listaItem.getCodigoErp().equals(detalleItem.getCodigoErp()))
	                .findFirst()
	                .ifPresent(listaItem -> 
	                	listaItem.setCantEntregada(listaItem.getCantEntregada().add(BigDecimal.valueOf(detalleItem.getCantEntrega()))))
	                );

	    listaMService.actualizarListaM(listaMateriales);
		
	}

	@Override
	public List<SolicitudDTO> obtenerSolicitudesPendientes() {
		List<SolicitudUsuarioInterface> solcEncabezado = solicitudMateriaPrimaRepo.findByIdEstado(0);
		List<SolicitudDTO> solicitudesDTO = new ArrayList<>();
		solcEncabezado.forEach(sol->{
			SolicitudMateriaPrima solicitud = new SolicitudMateriaPrima(sol);
			SolicitudDTO solDTO = new SolicitudDTO(solicitud, sol.getusu_nombre());
			solicitudesDTO.add(solDTO);
		});
		return solicitudesDTO;
	}
	
	@Override
	public List<SolicitudDTO> obtenerSolicitudes() {
		List<SolicitudUsuarioInterface> solcEncabezado = solicitudMateriaPrimaRepo.findByAllEstados();
		List<SolicitudDTO> solicitudesDTO = new ArrayList<>();
		solcEncabezado.forEach(sol->{
			SolicitudMateriaPrima solicitud = new SolicitudMateriaPrima(sol);
			SolicitudDTO solDTO = new SolicitudDTO(solicitud, sol.getusu_nombre());
			solicitudesDTO.add(solDTO);
		});
		return solicitudesDTO;
	}

	@Override
	public SolicitudMateriaPrima obtenerSolicitudPorId(Integer idSol) {
		return solicitudMateriaPrimaRepo.findById(idSol)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro Solicitud con id: " + idSol));
	}

	@Override
	public Integer obtenerCodErpPorLote(String lote) {
		return solicitudMateriaPrimaRepo.obtenerCodErpPorLote(lote);
	}

	@Override
	public String obtenerIdctErp(Integer idCentroTrabajo) {
		try {
			return solicitudMateriaPrimaRepo.obtenerIdctErp(idCentroTrabajo);
		}catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}

	@Override
	public void rechazarSolicitud(Integer idSol) {
        SolicitudMateriaPrima solicitud = solicitudMateriaPrimaRepo.findById(idSol)
            .orElseThrow(() -> new ResourceNotFoundException("No se encontró Solicitud con id: " + idSol));
        solicitud.setIdEstado(2); 
        solicitudMateriaPrimaRepo.save(solicitud);
    }

	@Override
	public Page<SolicitudDTO> obtenerSolicitudesPendientesPaginadas(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
	    Page<SolicitudUsuarioInterface> solicitudesPage = solicitudMateriaPrimaRepo.findByIdEstadoPaginado(0, pageable);
	    
	    return solicitudesPage.map(sol -> {
	        SolicitudMateriaPrima solicitud = new SolicitudMateriaPrima(sol);
	        return new SolicitudDTO(solicitud, sol.getusu_nombre());
	    });
	}

	@Override
	public Page<SolicitudDTO> obtenerTodasSolicitudesPaginadas(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
	    Page<SolicitudUsuarioInterface> solicitudesPage = solicitudMateriaPrimaRepo.findByAllEstadosPaginado(pageable);
	    
	    return solicitudesPage.map(sol -> {
	        SolicitudMateriaPrima solicitud = new SolicitudMateriaPrima(sol);
	        return new SolicitudDTO(solicitud, sol.getusu_nombre());
	    });
	}

	

}
