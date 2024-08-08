package com.almatec.controlpiso.integrapps.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.ConfigProceso;
import com.almatec.controlpiso.integrapps.entities.RegistroOperDia;
import com.almatec.controlpiso.integrapps.entities.Turno;
import com.almatec.controlpiso.integrapps.repositories.ConfigProcesoRepository;
import com.almatec.controlpiso.integrapps.repositories.RegistroOperDiaRepository;
import com.almatec.controlpiso.integrapps.repositories.TurnoRepository;
import com.almatec.controlpiso.integrapps.services.TurnoService;

@Service
public class TurnoServiceImpl implements TurnoService {

	@Autowired
    private TurnoRepository turnoRepository;
	
	@Autowired
    private ConfigProcesoRepository configProcesoRepository;
	
	@Autowired
    private RegistroOperDiaRepository regOperXDiaRepository;
	
	@Override
	@Transactional
    @Scheduled(fixedRate = 60000) // Se ejecuta cada minuto
    public void actualizarEstadoOperarios() {
        LocalTime ahora = LocalTime.now();
        LocalDate hoy = LocalDate.now();

        List<Turno> todosLosTurnos = turnoRepository.findAll();

        for (Turno turno : todosLosTurnos) {
            if (esTurnoFinalizado(turno, ahora, hoy)) {
                List<ConfigProceso> configuracionesProceso = configProcesoRepository.findByIdTurno(turno.getId().intValue());
                for (ConfigProceso configProceso : configuracionesProceso) {
                    List<RegistroOperDia> registrosActivos = regOperXDiaRepository.findByIdConfigProcesoAndIsActivoTrue(configProceso.getId());
                    for (RegistroOperDia registro : registrosActivos) {
                    	registro.setIsActivo(false);
                        regOperXDiaRepository.save(registro);
                    }
                }
            }
        }
    }

	private boolean esTurnoFinalizado(Turno turno, LocalTime ahora, LocalDate hoy) {
        LocalTime inicioTurno = LocalTime.parse(turno.getInicio());
        LocalTime finTurno = LocalTime.parse(turno.getFin());
        if (inicioTurno.isBefore(finTurno)) {
            // Turno en el mismo día
            return ahora.isAfter(finTurno) || ahora.isBefore(inicioTurno);
        } else {
            // Turno que cruza la medianoche
            return ahora.isAfter(finTurno) && ahora.isBefore(inicioTurno);
        }
    }

}
