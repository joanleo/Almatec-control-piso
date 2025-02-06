package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.exceptions.ResourceNotFoundException;
import com.almatec.controlpiso.integrapps.dtos.ConsultaOpCreadaDTO;
import com.almatec.controlpiso.integrapps.dtos.OpProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.ProyectoProduccionDTO;
import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.interfaces.OrdenPvEstadoData;
import com.almatec.controlpiso.integrapps.repositories.OrdenPvRepository;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;

@Service
public class OrdenPvServiceImpl implements OrdenPvService {
	
	@Autowired
	private OrdenPvRepository ordenPvRepo;
	
	@Value("${schema.unoee}")
	private String schemaUnoee;

	@Override
	public List<ProyectoProduccionDTO> buscarProyectosOrdenPv() {
		List<Object[]> proyectosOPV = ordenPvRepo.findProyects();
		List<ProyectoProduccionDTO> proyectos = new ArrayList<>(); 
		for(Object[] p: proyectosOPV) {
			ProyectoProduccionDTO project = new ProyectoProduccionDTO();
			project.setProyecto(p[0] != null ? p[0].toString() : "");
			project.setCliente(p[1] != null ? p[1].toString() : "");
			//project.setUn(p[2].toString());
			proyectos.add(project);
		}
		return proyectos;
	}

	@Override
	public List<ProyectoProduccionDTO> buscarProyectosOrdenPv(String keyword) {
		List<Object[]> proyectosOPV = ordenPvRepo.findProyects(keyword);
		List<ProyectoProduccionDTO> proyectos = new ArrayList<>(); 
		for(Object[] p: proyectosOPV) {
			ProyectoProduccionDTO project = new ProyectoProduccionDTO();
			project.setProyecto(p[0].toString());
			project.setCliente(p[1].toString());
			project.setUn(p[2].toString());
			proyectos.add(project);
		}
		return proyectos;
	}

	@Override
	public List<OpProduccionDTO> buscarOrdenesPorIdProyecto(String idProyecto) {
		List<VistaOrdenPv> ordenesBd = ordenPvRepo.findByIdProyecto(idProyecto);
		List<OpProduccionDTO> ordenes = new ArrayList<>();
		for(VistaOrdenPv o: ordenesBd) {
			OpProduccionDTO orden = new OpProduccionDTO();
			orden.setTipoOp(o.getTipoOp());
			orden.setNumOp(o.getNumOp());
			orden.setUn(o.getUnidadNegocio());
			orden.setZona(o.getZona());
			orden.setEsquemaPintura(o.getEsquemaPintura());
			orden.setIdProyecto(o.getIdProyecto());
			orden.setCliente(o.getCliente());
			orden.setItemDescripcion(o.getItemDescripcion());
			orden.setItemRef(o.getItemRef());
			orden.setCant(o.getCant());
			ordenes.add(orden);
		}
		return ordenes;
	}

	@Override
	public VistaOrdenPv obtenerOrdenPorId(Integer idPvIntegrapps) {
		return ordenPvRepo.findById(idPvIntegrapps)
				.orElseThrow(()-> new ResourceNotFoundException("No se encontro la orden PV con id: " + idPvIntegrapps));
	}

	@Override
	public void guardarOrden(VistaOrdenPv orden) {
		ordenPvRepo.save(orden);
		
	}

	@Override
	public Integer obtenerNumOpPorIdOp(Integer idOpIntegrapps) {
		return ordenPvRepo.obtenerNumOpPorIdOp(idOpIntegrapps);
	}

	@Override
	public VistaOrdenPv actualizarDatosOp(ConsultaOpCreadaDTO creado, VistaOrdenPv ordenIntegrapps) {
		String opUnoEE = creado.getF850_id_tipo_docto()+"-"+creado.getF850_consec_docto();
		ordenPvRepo.actualizarOp(creado, opUnoEE, ordenIntegrapps);
		return obtenerOrdenPorId(ordenIntegrapps.getId());
	}

	@Override
	public OrdenPvEstadoData obtenerOrdenPorNumPv(Integer noPedido) {
		return ordenPvRepo.findByNumOp(noPedido);
	}

	@Override
	public List<VistaOrdenPv> obtenerOpActivas() {
		
		return ordenPvRepo.findByTipoOpAndIdEstadoOpOrderByNumOpDesc("OP", 1);
	}

	@Override
	public List<VistaOrdenPv> obtenerOrdenes() {
		return ordenPvRepo.findAll();
	}

	@Override
    public Page<VistaOrdenPv> buscarProyectosPaginados(Pageable pageable) {
        return ordenPvRepo.findByTipoOpAndIdEstadoDocOrderByNumOpDesc("OP", 1, pageable);
    }

    @Override
    public Page<VistaOrdenPv> buscarProyectosPaginados(String keyword, Pageable pageable) {
        return ordenPvRepo.buscarPorKeywordPaginado(keyword, pageable);
    }

	@Override
	public Integer obteneridItemOpPorIdOpIA(Integer idOPI) {
		return ordenPvRepo.obteneridItemOpPorIdOpIA(schemaUnoee, idOPI);
	}

}
