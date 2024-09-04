package com.almatec.controlpiso.integrapps.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.CentroTrabajo;
import com.almatec.controlpiso.integrapps.entities.Prioridad;
import com.almatec.controlpiso.integrapps.repositories.CentroTrabajoRepository;
import com.almatec.controlpiso.integrapps.repositories.PrioridadRepository;
import com.almatec.controlpiso.integrapps.services.PrioridadSevice;
import com.almatec.controlpiso.programacion.dtos.PrioridadItemsDTO;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.repositories.UsuarioRepository;

@Service
public class PrioridadServiceImpl implements PrioridadSevice {
	
	@Autowired
	private CentroTrabajoRepository centroTrabajoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private PrioridadRepository prioridadRepo;

	@Override
	public List<Prioridad> guardarActualizarPrioridades(PrioridadItemsDTO dto) {
		CentroTrabajo centroTrabajo = centroTrabajoRepo.findById(dto.getCentroTrabajoId())
                .orElseThrow(() -> new IllegalArgumentException("Centro de trabajo no encontrado"));

        Usuario usuarioActual = obtenerUsuarioActual(); 

        List<Prioridad> prioridades = new ArrayList<>();
        
        for (Integer itemId : dto.getItemsId()) {
            Prioridad prioridad = prioridadRepo.findByIdItemAndCentroTrabajo(itemId.longValue(), centroTrabajo)
                    .orElse(new Prioridad());

            prioridad.setIdItem(itemId.longValue());
            prioridad.setItemPrioridad(dto.getPrioridad());
            prioridad.setCentroTrabajo(centroTrabajo);

            if (prioridad.getId() == null) {
                prioridad.setFechaCrea(LocalDateTime.now());
                prioridad.setUsuarioCrea(usuarioActual);
            } else {
                prioridad.setFechaEdita(LocalDateTime.now());
                prioridad.setUsuarioEdita(usuarioActual);
            }
            prioridades.add(prioridad);
        }
        
		return prioridadRepo.saveAllAndFlush(prioridades);
		
	}

	private Usuario obtenerUsuarioActual() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioRepo.findByNombreUsuario(auth.getName())
                .orElseThrow(() -> new IllegalStateException("Usuario no autenticado"));
	}

}
