package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;

public interface VistaPedidosErpRepository extends JpaRepository<VistaPedidosErp, Long> {

	List<VistaPedidosErp> findByTipoAndEstadoOrderByNoPvDesc(String tipoP, Integer idEstado);

	@Query(value = "SELECT * "
			+ "FROM Pedidos_Erp_Estado "
			+ "WHERE CONCAT(Pedidos_Erp_Estado.fecha, Pedidos_Erp_Estado.Pedido, Pedidos_Erp_Estado.Nit_Cliente, Pedidos_Erp_Estado.Cliente, Pedidos_Erp_Estado.f431_id_proyecto) "
			+ "LIKE %:keyword% "
			+ "AND Tipo_Pv = :tipoP "
			+ "AND Id_estado = :idEstado", nativeQuery = true)
	List<VistaPedidosErp> buscarPedidosErpFilterByKeyword(@Param("tipoP") String tipoP, 
			@Param("idEstado") int idEstado, 
			@Param("keyword") String keyword);

}
