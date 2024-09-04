package com.almatec.controlpiso.integrapps.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.almatec.controlpiso.integrapps.entities.VistaOpItemsMaterialesRuta;
import com.almatec.controlpiso.programacion.dtos.PrioridadFilterDTO;

@Component
public class PrioridadSpecification {
	
	private static final Logger logger = LoggerFactory.getLogger(PrioridadSpecification.class);
	
	public Specification<VistaOpItemsMaterialesRuta> getItemsPrioridades(PrioridadFilterDTO filtro){
		return (root, query, criteriaBuilder) ->{
			
			logger.info("Iniciando specification");
			
			List<Predicate> predicates = new ArrayList<>();
			
			predicates.add(criteriaBuilder.notEqual(root.get("item_id"), 0));
			
			if(filtro.getCentroTrabajoId() != null) {
                Predicate materialCentroTIdPredicate = criteriaBuilder.equal(root.get("materialCentroTId"), filtro.getCentroTrabajoId());
                Predicate itemCentroTIdPredicate = criteriaBuilder.equal(root.get("itemCentroTId"), filtro.getCentroTrabajoId());
                predicates.add(criteriaBuilder.or(materialCentroTIdPredicate, itemCentroTIdPredicate));
            }
			
			if(filtro.getDescripcion() != null && !filtro.getDescripcion().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("itemDescripcion"), "%" + filtro.getDescripcion() + "%"));
			}
			if(filtro.getCantidad() != null) {
				predicates.add(criteriaBuilder.equal(root.get("cantReq"),filtro.getCantidad()));
			}
			if(filtro.getProyecto() != null && !filtro.getProyecto().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("un"), "%" + filtro.getProyecto() + "%"));
			}
			if(filtro.getZona() != null && !filtro.getZona().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("zona"), "%" + filtro.getZona() + "%"));
			}
			if(filtro.getOp() != null && !filtro.getOp().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("op"), "%" + filtro.getOp() + "%"));
			}
			if(filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("marca"), "%" + filtro.getMarca() + "%"));
			}
			if(filtro.getPrioridad() != null) {
				predicates.add(criteriaBuilder.equal(root.get("prioridad"), filtro.getPrioridad()));
			}
			
			logger.info("Predicates: {}", predicates.toString());
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
