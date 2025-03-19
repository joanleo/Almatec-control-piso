package com.almatec.controlpiso.almacen.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.almacen.interfaces.EncabezadoRemision;
import com.almatec.controlpiso.almacen.service.RemisionService;
import com.almatec.controlpiso.integrapps.entities.Remision;
import com.almatec.controlpiso.integrapps.repositories.RemisionRepository;
import java.util.Collections;

@Service
public class RemisionServiceImpl implements RemisionService {
	
	@Autowired
	private RemisionRepository remisionRepo;
	
	@Value("${schema.unoee}")
	private String schemaUnoee;

	@Override
	public Remision guardarRemision(Remision remision) {
		try {
			return remisionRepo.saveAndFlush(remision);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EncabezadoRemision> obtenerTodasLasRemisiones() {
		try {
			return remisionRepo.buscarTodasRemisiones();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public EncabezadoRemision obtenerEncabezadoRemisionPorId(Long idRemision) {
		try {
			return remisionRepo.obtenerEncabezadoRemisionPorId(schemaUnoee, idRemision);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Page<EncabezadoRemision> obtenerRemisionesPaginadas(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		try {
			return remisionRepo.buscarTodasRemisiones(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Page<EncabezadoRemision> buscarRemisiones(String termino, PageRequest pageable) {
		return remisionRepo.buscarRemisiones(termino, pageable);
	}

	@Transactional
	@Override
	public Remision buscarRemisionPorId(Long idRemision) {
		return remisionRepo.findByIdWithDetails(idRemision);
	}

}
