package com.almatec.controlpiso.integrapps.services.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;
import com.almatec.controlpiso.integrapps.repositories.VistaItemLoteDisponibleRepository;
import com.almatec.controlpiso.integrapps.services.VistaItemLoteDisponibleService;
import com.almatec.controlpiso.integrapps.specifications.VistaItemLoteDisponibleSpecification;
import com.almatec.controlpiso.produccion.dtos.LoteInfoDTO;

@Service
public class VistaItemLoteDisponibleServiceImpl implements VistaItemLoteDisponibleService {
	
	@Autowired
	private VistaItemLoteDisponibleRepository vistaItemLoteDisponibleRepository;
	
	@Autowired
	private VistaItemLoteDisponibleSpecification filter;


	@PersistenceContext
    private EntityManager entityManager;
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public List<VistaItemLoteDisponible> searchItems(SpecItemLoteDTO filtro, boolean transferencia) {
		return vistaItemLoteDisponibleRepository.findAll(filter.getDisponibilidad(filtro, transferencia));
	}
	
	@Override
    public Page<VistaItemLoteDisponible> searchItems(SpecItemLoteDTO filtro, boolean transferencia, Pageable pageable) {
        return vistaItemLoteDisponibleRepository.findAll(filter.getDisponibilidad(filtro, transferencia), pageable);
    }

	@Override
	public Map<String, LoteInfoDTO> buscarDisponibilidadBatch(Set<String> lotes, String bodega) {
        try {
            // Verificar datos específicos de cada lote
            String sqlDetalle = "SELECT v.f_id_lote, v.f_cant_disponible_1, v.f120_descripcion "
            		+ "FROM Integrapps.dbo.view_items_lote_dispo v "
            		+ "WHERE v.f150_id = :bodega "
            		+ "AND v.f_id_lote IN :lotes";

            @SuppressWarnings("unchecked")
            List<Object[]> results = entityManager.createNativeQuery(sqlDetalle)
                .setParameter("bodega", bodega)
                .setParameter("lotes", lotes)
                .getResultList();

            // Construir el mapa de resultados
            Map<String, LoteInfoDTO> disponibilidades = results.stream()
            		.collect(Collectors.toMap(
                            row -> ((String) row[0]).trim(),
                            row -> new LoteInfoDTO(
                                row[1] != null ? (BigDecimal) row[1] : BigDecimal.ZERO,
                                row[2] != null ? (String) row[2] : ""
                            ),
                            (v1, v2) -> v1,
                            HashMap::new
                        ));

            return disponibilidades;

        } catch (Exception e) {
            log.error("Error en consulta de disponibilidad", e);
            log.error("SQL Parameters - bodega: {}, lotes: {}", bodega, lotes);
            
            return new HashMap<>();
        }
    }
	
	

}
