package com.almatec.controlpiso.integrapps.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.almatec.controlpiso.integrapps.dtos.PedidoSpecDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPedidosKgCumplidos;

public interface VistaPedidosKgCumplidosService {

	Page<VistaPedidosKgCumplidos> searchOrder(PedidoSpecDTO busquedaSpec, Pageable pageable);

}
