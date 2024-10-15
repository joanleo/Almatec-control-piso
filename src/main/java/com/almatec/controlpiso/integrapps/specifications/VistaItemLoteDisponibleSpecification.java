package com.almatec.controlpiso.integrapps.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.almatec.controlpiso.integrapps.dtos.SpecItemLoteDTO;
import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;

@Component
public class VistaItemLoteDisponibleSpecification {
	
	public Specification<VistaItemLoteDisponible> getDisponibilidad(SpecItemLoteDTO filtro, boolean transferencia){
		return(root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(transferencia) {
				predicates.add(criteriaBuilder.equal(root.get("idBodega"), "00101"));				
			}
			
			if(filtro.getCodigo() != null) {
				predicates.add(criteriaBuilder.equal(root.get("idItem"), filtro.getCodigo()));
			}
			if(filtro.getBodega() != null) {
				predicates.add(criteriaBuilder.like(root.get("idBodega"), "%"  +filtro.getBodega() + "%" ));
			}
			if(filtro.getUm() != null) {
				predicates.add(criteriaBuilder.like(root.get("um"), "%" + filtro.getUm() + "%"));
			}
			if(filtro.getLote() != null) {
				predicates.add(criteriaBuilder.like(root.get("lote"), "%" + filtro.getLote() + "%"));
			}
			if(filtro.getDescripcion() != null) {
				predicates.add(criteriaBuilder.like(root.get("descripcionItem"), "%" + filtro.getDescripcion() + "%"));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
