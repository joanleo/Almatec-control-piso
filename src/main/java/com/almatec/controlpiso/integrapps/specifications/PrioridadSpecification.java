package com.almatec.controlpiso.integrapps.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;
import com.almatec.controlpiso.programacion.dtos.PrioridadFilterDTO;

@Component
public class PrioridadSpecification {
	
	public Specification<VistaItemsOpsProgramacion> getItemsPrioridades(PrioridadFilterDTO filtro){
		return (root, query, criteriaBuilder) ->{
			List<Predicate> predicates = new ArrayList<>();
			
			predicates.add(criteriaBuilder.notEqual(root.get("idItemFab"), 0));
			
			if(filtro.getDescripcion() != null && !filtro.getDescripcion().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("descripcion"), "%" + filtro.getDescripcion() + "%"));
			}
			if(filtro.getCantidad() != null) {
				predicates.add(criteriaBuilder.equal(root.get("cant"),filtro.getCantidad()));
			}
			if(filtro.getProyecto() != null && !filtro.getProyecto().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("centroOperacionNombre"), "%" + filtro.getProyecto() + "%"));
			}
			if(filtro.getZona() != null && !filtro.getZona().isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("zona"), "%" + filtro.getZona() + "%"));
			}
			if(filtro.getPrioridad() != null) {
				predicates.add(criteriaBuilder.equal(root.get("prioridad"), filtro.getPrioridad()));
			}
			
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
