package com.almatec.controlpiso.integrapps.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.ErrorMensaje;
import com.almatec.controlpiso.integrapps.dtos.NovedadDTO;
import com.almatec.controlpiso.integrapps.entities.NovedadCt;
import com.almatec.controlpiso.integrapps.repositories.NovedadCtRepository;
import com.almatec.controlpiso.integrapps.services.NovedadCtService;


@Transactional
@Service
public class NovedadCtServiceImpl implements NovedadCtService {

	@Autowired
	private NovedadCtRepository novedadCtRepository;

	@Override
	public Integer obtenerUltimoConsecutivo() {
		return novedadCtRepository.obtenerUltimoconsecutivo();
	}

	@Override
	public ErrorMensaje guardarNovedad(NovedadDTO novedadDTO) {
		NovedadCt novedad = generarNovedad(novedadDTO);
		ErrorMensaje mensaje = new ErrorMensaje();
		try {
			novedadCtRepository.save(novedad);
			mensaje.setMensaje("Novedad registrada exitosamente");
			mensaje.setError(false);
		} catch (Exception e) {
			mensaje.setMensaje("Se presento un error al tratar de guardar la novedad." + e.getMessage());
			mensaje.setError(true);
		}
		return mensaje;
	}

	private NovedadCt generarNovedad(NovedadDTO novedadDTO) {
		NovedadCt novedad = new NovedadCt();
		if(novedadDTO.getIdPerfil() != null) {
			novedad.setIdPerfil(novedadDTO.getIdPerfil());
		}
		novedad.setIdItem(novedadDTO.getIdItem());
		novedad.setIdItmFab(novedadDTO.getIdItemFab());
		novedad.setIdCentroTrabajo(novedadDTO.getIdCentroTrabajo());
		novedad.setIdOperario(novedadDTO.getOperario().getId());
		novedad.setSobrante(novedadDTO.getSobrante());
		novedad.setNoConforme(novedadDTO.getNoConforme());
		novedad.setDesperdicio(novedadDTO.getDesperdicio());
		return novedad;
	}

}
