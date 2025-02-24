package com.almatec.controlpiso.integrapps.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.Parada;
import com.almatec.controlpiso.integrapps.repositories.ParadaRepository;
import com.almatec.controlpiso.integrapps.services.ParadaService;
import com.almatec.controlpiso.produccion.dtos.ParadaDTO;

@Service
public class ParadaServiceImpl implements ParadaService {
	
	@Autowired 
	private ParadaRepository paradaRepo;

	@Override
	public List<Parada> obtenerParadas() {
		return paradaRepo.findAll();
	}
	
	public List<Parada> listarActivos() {
        return paradaRepo.findByIsActivoTrue();
    }

    public Parada obtenerPorId(Long id) {
        return paradaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parada no encontrada con ID: " + id));
    }

    @Transactional
    public Parada crear(ParadaDTO dto, String usuarioCrea) {

        Parada parada = new Parada();
        BeanUtils.copyProperties(dto, parada);
        parada.setFecha(LocalDateTime.now());
        parada.setUsuarioCrea(usuarioCrea);
        parada = paradaRepo.save(parada);
        String codBarMaq = String.format("PAR%04d", parada.getId());
        parada.setCodBarrasM(codBarMaq);

        return paradaRepo.save(parada);
    }

    @Transactional
    public Parada actualizar(Long id, ParadaDTO dto, String usuarioEdita) {
    	Parada parada = obtenerPorId(id);
        
        // Mantener valores que no deben cambiar
        String usuarioCrea = parada.getUsuarioCrea();
        LocalDateTime fechaCreacion = parada.getFecha();
        String codBarMaq = parada.getCodBarrasM();
        
        // Copiar propiedades del DTO
        BeanUtils.copyProperties(dto, parada);
        
        // Restaurar valores que no deben cambiar
        parada.setId(id);
        parada.setUsuarioCrea(usuarioCrea);
        parada.setFecha(fechaCreacion);
        parada.setCodBarrasM(codBarMaq);
        parada.setFechaEdicion(LocalDateTime.now());
        parada.setUsuarioEdita(usuarioEdita);
        return paradaRepo.save(parada);
    }

    @Transactional
    public void eliminar(Long id) {
        Parada Parada = obtenerPorId(id);
        Parada.setIsActivo(false);
        paradaRepo.save(Parada);
    }

    @Transactional
    public void eliminarDefinitivamente(Long id) {
        if (!paradaRepo.existsById(id)) {
            throw new EntityNotFoundException("Parada no encontrada con ID: " + id);
        }
        paradaRepo.deleteById(id);
    }

}
