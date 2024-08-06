package com.almatec.controlpiso.integrapps.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.almatec.controlpiso.integrapps.entities.VistaItemLoteDisponible;

public interface VistaItemLoteDisponibleRepository extends JpaRepository<VistaItemLoteDisponible, String>, JpaSpecificationExecutor<VistaItemLoteDisponible> {

	Page<VistaItemLoteDisponible> findAll(Specification<VistaItemLoteDisponible> disponibilidad, Pageable pageable);

}
