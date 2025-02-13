package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.VistaPedidoOp;

public interface VistaPedidoOpRepository extends JpaRepository<VistaPedidoOp, Integer> {

	List<VistaPedidoOp> findByPvRowId(Integer rowIdPV);

}
