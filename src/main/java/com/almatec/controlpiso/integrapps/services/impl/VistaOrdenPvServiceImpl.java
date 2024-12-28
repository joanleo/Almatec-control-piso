package com.almatec.controlpiso.integrapps.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.entities.VistaOrdenPv;
import com.almatec.controlpiso.integrapps.repositories.OrdenPvRepository;
import com.almatec.controlpiso.integrapps.services.VistaOrdenPvService;

@Service
public class VistaOrdenPvServiceImpl implements VistaOrdenPvService {
	
	@Autowired
	private OrdenPvRepository ordenPvRepo;

	private final Logger log = LoggerFactory.getLogger(VistaOrdenPvServiceImpl.class);
	
	@Override
	public List<Integer> obtenerCentrosTrabajoTep(Integer idOpIntegrapps) {
		try {
            VistaOrdenPv ordenPv = ordenPvRepo.buscarPorId(idOpIntegrapps);
            if (ordenPv != null && ordenPv.getCentrosTrabajoTepReportado() != null) {
                return Arrays.stream(ordenPv.getCentrosTrabajoTepReportado().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
            }
            return new ArrayList<>();
        } catch (Exception e) {
            log.error("Error al obtener centros TEP para OP {}: {}", idOpIntegrapps, e.getMessage());
            return new ArrayList<>();
        }
	}

	@Transactional
	@Override
	public void actualizarCentrosTep(Integer idOpIntegrapps, List<Integer> centrosTep) {
		try {
            // Validar entrada
            if (idOpIntegrapps == null || centrosTep == null) {
                log.error("Datos de entrada inválidos para actualización de centros TEP");
                throw new IllegalArgumentException("idOpIntegrapps y centrosTep no pueden ser null");
            }

            // Normalizar y ordenar los nuevos centros
            String centrosTepString = centrosTep.stream()
                .distinct()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
            
            log.info("Intentando actualizar centros TEP para OP {} con valores: {}", 
                    idOpIntegrapps, centrosTepString);

            // Ejecutar la actualización
            int filasActualizadas = ordenPvRepo.actualizarCentrosTep(idOpIntegrapps, centrosTepString);
            
            if (filasActualizadas != 1) {
                log.error("La actualización no afectó ninguna fila o afectó múltiples filas: {}", filasActualizadas);
                throw new RuntimeException("Actualización fallida: filas afectadas = " + filasActualizadas);
            }

            

            log.info("Actualización exitosa de centros TEP para OP {}", idOpIntegrapps);
            
        } catch (Exception e) {
            log.error("Error al actualizar centros TEP para OP {}: {}", idOpIntegrapps, e.getMessage());
            throw new RuntimeException("Error al actualizar centros TEP: " + e.getMessage(), e);
        }
		
	}


}
