package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosKgCumplidos;

public interface VistaPedidosKgCumplidosRepository extends JpaRepository<VistaPedidosKgCumplidos, Integer>, JpaSpecificationExecutor<VistaPedidosKgCumplidos> {

	Page<VistaPedidosKgCumplidos> findAll(Specification<VistaPedidosKgCumplidos> orders, Pageable pageable);

}
