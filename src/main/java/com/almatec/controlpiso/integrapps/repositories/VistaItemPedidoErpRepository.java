package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;

public interface VistaItemPedidoErpRepository extends JpaRepository<VistaItemPedidoErp, Integer> {

	List<VistaItemPedidoErp> findByTipoPedidoAndNoPedido(String tipoPedido, String idPedido);

	List<VistaItemPedidoErp> findByTipoPedidoAndNoPedidoAndReferencia(String string, String noPedido, String referencia);

}
