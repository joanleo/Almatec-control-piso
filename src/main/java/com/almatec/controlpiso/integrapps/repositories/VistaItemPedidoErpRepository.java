package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.VistaItemPedidoErp;

public interface VistaItemPedidoErpRepository extends JpaRepository<VistaItemPedidoErp, Integer> {

	List<VistaItemPedidoErp> findByTipoPedidoAndNoPedido(String tipoPedido, Integer idPedido);

	
	//List<VistaItemPedidoErp> findByTipoPedidoAndNoPedidoAndReferencia(OrdenPv orden, String referencia);


	List<VistaItemPedidoErp> findByRowIdOpAndReferencia(Integer rowIdOp, String referencia);


	List<VistaItemPedidoErp> findByNoPedidoAndReferencia(Integer noPedido, String referencia);


	List<VistaItemPedidoErp> findByRowIdOp(Integer rowIdOp);

}
