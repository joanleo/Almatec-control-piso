package com.almatec.controlpiso.comunicador.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.comunicador.dtos.TipoNotificacionDTO;
import com.almatec.controlpiso.comunicador.entities.TipoNotificacion;
import com.almatec.controlpiso.comunicador.repositories.TipoNotificacionRepository;

@Service
public class TipoNotificacionService {
	
	@Autowired
	private TipoNotificacionRepository tipoNotificacionRepo;
	
	public List<TipoNotificacionDTO> getAllConfigs() {
        return tipoNotificacionRepo.findAll().stream()
                .map(TipoNotificacionDTO::fromEntity)
                .collect(Collectors.toList());
    }
    
    public TipoNotificacionDTO getConfig(Integer id) {
        return tipoNotificacionRepo.findById(id)
                .map(TipoNotificacionDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Configuración no encontrada"));
    }
    
    public TipoNotificacionDTO saveConfig(TipoNotificacionDTO dto) {
    	TipoNotificacion entity = dto.toEntity();
        return TipoNotificacionDTO.fromEntity(tipoNotificacionRepo.save(entity));
    }
    
    public void deleteConfig(Integer id) {
    	tipoNotificacionRepo.deleteById(id);
    }

}
