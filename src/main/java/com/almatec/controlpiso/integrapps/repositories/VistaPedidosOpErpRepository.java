package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosOpErp;

public interface VistaPedidosOpErpRepository extends JpaRepository<VistaPedidosOpErp, Integer> {

	List<VistaPedidosOpErp> findByTipoPv(String tipoPv);

	List<VistaPedidosOpErp> findByTipoOp(String tipoOp);

	@Query(value = "SELECT * "
			+ "FROM pedidos_ops "
			+ "WHERE CONCAT(pedidos_ops.estado_op, pedidos_ops.nit, pedidos_ops.razon_social, pedidos_ops.fec_ent_pv) "
			+ "LIKE %:keyword% "
			+ "AND pedidos_ops.tipo_op = :tipoOp "
			+ "ORDER BY numPv ASC", nativeQuery = true)
	List<VistaPedidosOpErp> findByTipoOpFilterByKeyword(@Param("tipoOp") String tipoOp, @Param("keyword")String keyword);

	List<VistaPedidosOpErp> findByTipoOpOrderByNumPv(String string);

}
