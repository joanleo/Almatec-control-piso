package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.OpProduccionDTO;
import com.almatec.controlpiso.integrapps.dtos.ProyectoProduccionDTO;
import com.almatec.controlpiso.integrapps.entities.OrdenPv;
import com.almatec.controlpiso.integrapps.repositories.OrdenPvRepository;
import com.almatec.controlpiso.integrapps.services.OrdenPvService;

@Service
public class OrdenPvServiceImpl implements OrdenPvService {
	
	@Autowired
	private OrdenPvRepository ordenPvRepo;

	@Override
	public List<OrdenPv> buscarProyectos() {
		List<OrdenPv> proyectos = ordenPvRepo.findAll();
		return proyectos;
	}

	@Override
	public List<OrdenPv> buscarProyectos(String keyword) {
		List<OrdenPv> proyectos = ordenPvRepo.buscarPorKeyword(keyword);
		return proyectos;
	}

	@Override
	public List<ProyectoProduccionDTO> buscarProyectosOrdenPv() {
		List<Object[]> proyectosOPV = ordenPvRepo.findProyects();
		List<ProyectoProduccionDTO> proyectos = new ArrayList<>(); 
		for(Object[] p: proyectosOPV) {
			ProyectoProduccionDTO project = new ProyectoProduccionDTO();
			project.setProyecto(p[0].toString());
			project.setCliente(p[1].toString());
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
		List<OrdenPv> ordenesBd = ordenPvRepo.findByIdProyecto(idProyecto);
		List<OpProduccionDTO> ordenes = new ArrayList<>();
		for(OrdenPv o: ordenesBd) {
			OpProduccionDTO orden = new OpProduccionDTO();
			orden.setTipoOp(o.getTipoOp());
			orden.setNumOp(o.getNumOp());
			orden.setUn(o.getUnidadNegocio());
			orden.setZona(o.getZonaSistema().split("-")[0]);
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

}
