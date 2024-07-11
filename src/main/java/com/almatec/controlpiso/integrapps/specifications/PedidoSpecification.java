package com.almatec.controlpiso.integrapps.specifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.almatec.controlpiso.integrapps.dtos.PedidoSpecDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;

@Component
public class PedidoSpecification {

	public Specification<VistaPedidosErp> getOrders(PedidoSpecDTO filtro){
		return (root, query, criteriaBuilder) ->{
			List<Predicate> predicates = new ArrayList<>();
			
			if(filtro.getUn() != null) {
				predicates.add(criteriaBuilder.like(root.get("co"), "%" + filtro.getUn() + "%"));
			}
			if(filtro.getAsesor() != null) {
				predicates.add(criteriaBuilder.like(root.get("vendedor"), "%" + filtro.getAsesor() + "%"));
			}
			if(filtro.getCliente() != null) {
				predicates.add(criteriaBuilder.like(root.get("razonSocial"), "%" + filtro.getCliente() + "%"));
			}
			if(filtro.getEstado() != null) {
				predicates.add(criteriaBuilder.like(root.get("estado"), "%" + filtro.getEstado() + "%"));
			}
			
			if(filtro.getDescripcion() != null) {
				predicates.add(criteriaBuilder.like(root.get("descripcion"), "%" + filtro.getDescripcion() + "%"));
			}			

			if (filtro.getFechaInicio() != null && filtro.getFechaFin() != null) {
                predicates.add(criteriaBuilder.between(root.<Date>get("fecha"), filtro.getFechaInicio(), filtro.getFechaFin()));
            } else if (filtro.getFechaInicio() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("fecha"), filtro.getFechaInicio()));
            } else if (filtro.getFechaFin() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("fecha"), filtro.getFechaFin()));
            }
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
