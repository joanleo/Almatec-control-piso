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
	public Map<String, LoteInfoDTO> buscarDisponibilidadBatch(Set<String> clavesCodigoLote, String bodega) {
	    if (clavesCodigoLote == null || clavesCodigoLote.isEmpty()) {
	        return new HashMap<>();
	    }
	    if (bodega == null || bodega.trim().isEmpty()) {
	        throw new IllegalArgumentException("La bodega no puede ser nula o vacía");
	    }

	    try {
	        // Separar las claves en códigos y lotes
	        Map<String, String> mapaClaves = clavesCodigoLote.stream()
	            .collect(Collectors.toMap(
	                clave -> clave,
	                clave -> {
	                    String[] partes = clave.split("\\|", 2);
	                    return partes.length > 1 ? partes[1] : "";
	                }
	            ));

	        Set<Integer> codigos = mapaClaves.keySet().stream()
	            .map(codigoStr -> {
	                try {
	                    return Integer.parseInt(codigoStr.split("\\|")[0]); // Tomar solo el código antes del |
	                } catch (NumberFormatException e) {
	                    log.error("El código no es un número válido: {}", codigoStr);
	                    throw new IllegalArgumentException("Código inválido: " + codigoStr);
	                }
	            })
	            .collect(Collectors.toSet());
	        
	        codigos.forEach(System.out::println);

	        Set<String> lotes = mapaClaves.values().stream()
	            .map(lote -> (lote != null && !lote.trim().isEmpty()) ? lote.trim() : "")
	            .collect(Collectors.toSet());
	        
	        lotes.forEach(System.out::println);

	        // Verificar si "SIN_LOTE" está presente
	        boolean contieneVacio = lotes.contains("");

	        // Construir la consulta SQL
	        StringBuilder sqlDetalle = new StringBuilder(
	            "SELECT v.f120_id, v.f_id_lote, v.f_cant_disponible_1, v.f120_descripcion "
	            + "FROM Integrapps.dbo.view_items_lote_dispo v "
	            + "WHERE v.f150_id = :bodega "
	            + "AND v.f120_id IN :codigos "
	        );

	        if (contieneVacio) {
	            sqlDetalle.append("AND (v.f_id_lote IN :lotes OR v.f_id_lote IS NULL) ");
	        } else {
	            sqlDetalle.append("AND v.f_id_lote IN :lotes ");
	        }
	        

	        @SuppressWarnings("unchecked")
	        List<Object[]> results = entityManager.createNativeQuery(sqlDetalle.toString())
	            .setParameter("bodega", bodega)
	            .setParameter("codigos", codigos)
	            .setParameter("lotes", lotes)
	            .getResultList();

	        // Log de resultados para debugging
	        if (log.isDebugEnabled()) {
	            results.forEach(row -> {
	                log.debug("Resultado: código={}, lote={}, cantidad={}, descripción={}",
	                    row[0], row[1], row[2], row[3]);
	            });
	        }

	        // Construir el mapa de resultados
	        return results.stream()
	                .collect(Collectors.toMap(
	                    row -> generarClaveCodigoLote(
	                        (Integer) row[0], 
	                        row[1] != null ? (String) row[1] : null
	                    ),
	                    row -> new LoteInfoDTO(
	                        row[2] != null ? (BigDecimal) row[2] : BigDecimal.ZERO,
	                        row[3] != null ? (String) row[3] : ""
	                    ),
	                    (v1, v2) -> v1,
	                    HashMap::new
	                ));

	    } catch (Exception e) {
	        log.error("Error en consulta de disponibilidad para bodega: {} con claves: {}", 
	                  bodega, String.join(", ", clavesCodigoLote), e);
	        return new HashMap<>();
	    }
	}

	private String generarClaveCodigoLote(Integer codigo, String lote) {
	    String loteFormateado = (lote != null && !lote.trim().isEmpty()) ? lote.trim() : "";
	    return codigo + "|" + loteFormateado;
	}
	
	

}
