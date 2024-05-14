package com.almatec.controlpiso.integrapps.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.SolicitudDTO;
import com.almatec.controlpiso.integrapps.entities.DetalleSolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.entities.ListaM;
import com.almatec.controlpiso.integrapps.entities.OrdenPv;
import com.almatec.controlpiso.integrapps.entities.SolicitudMateriaPrima;
import com.almatec.controlpiso.integrapps.interfaces.SolicitudUsuarioInterface;
import com.almatec.controlpiso.integrapps.repositories.SolicitudMateriaPrimaRepository;
import com.almatec.controlpiso.integrapps.services.DetalleSolicitudMateriaPrimaService;
import com.almatec.controlpiso.integrapps.services.ListaMService;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;
import com.almatec.controlpiso.integrapps.services.SolicitudMateriaPrimaService;

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
	
	@Override
	public Integer obtenerConsecutivo() {
		Integer consecutivo = solicitudMateriaPrimaRepo.obtenerConsecutivo();
		return consecutivo;
	}

	@Override
	public SolicitudMateriaPrima crearSolicitud(SolicitudMateriaPrima solicitud) {
		OrdenPv ordenPv = ordenPvService.obtenerOrdenPorId(solicitud.getIdOp());
		System.out.println(ordenPv);
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
			System.out.println(item);
		});
		System.out.println("Actualizando detalles");
		detalleSol = detalleSolService.actualizarDetalleSol(detalleSol);
		List<ListaM> listaMateriales = listaMService.obtenerListaMPorIdOp(solicitud.getIdOp());
		
		System.out.println("Actualizando lista m");
		detalleSol.forEach(detalleItem -> {
	        listaMateriales.stream()
	                .filter(listaItem -> listaItem.getCodigoErp().equals(detalleItem.getCodigoErp()))
	                .findFirst()
	                .ifPresent(listaItem -> {
	                	listaItem.setCantEntregada(listaItem.getCantEntregada().add(new BigDecimal(detalleItem.getCantEntrega())));
	                	System.out.println(listaItem);
	                });
	    });

	    listaMService.actualizarListaM(listaMateriales);
		
	}

	@Override
	public List<SolicitudDTO> obtenerSolicitudes() {
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
			System.err.println(e.toString());// TODO: handle exception
		}
		return null;
	}

}
