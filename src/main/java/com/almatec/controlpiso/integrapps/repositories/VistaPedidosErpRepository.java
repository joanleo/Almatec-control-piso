package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.VistaPedidosErp;

public interface VistaPedidosErpRepository extends JpaRepository<VistaPedidosErp, Long>, JpaSpecificationExecutor<VistaPedidosErp> {

	List<VistaPedidosErp> findByTipoAndEstadoOrderByNoPvDesc(String tipoP, Integer idEstado);
	@Query(value = "SELECT * "
			+ "FROM view_pedidos_estado_erp "
			+ "WHERE CONCAT(pv_fecha, pv_estado, pv_cliente_nit, pv_cliente_razon_social, pv_cliente_co_id_descripcion) "
			+ "LIKE %:keyword% "
			+ "AND pv_tipo = :tipoP "
			+ "AND pv_id_estado = :idEstado", nativeQuery = true)
	List<VistaPedidosErp> buscarPedidosErpFilterByKeyword(@Param("tipoP") String tipoP, 
			@Param("idEstado") int idEstado, 
			@Param("keyword") String keyword);

	List<VistaPedidosErp> findAll(Specification<VistaPedidosErp> pedidos);

	List<VistaPedidosErp> findByTipoAndIdEstadoOrderByNoPvDesc(String tipo, int numPv);
	
	@Query("SELECT p FROM VistaPedidosErp p WHERE " +
		       "p.tipo = 'PV' AND " +
		       "(" +
		       "   CAST(p.pedidoNo AS string) LIKE %:keyword% OR " +
		       "   p.numOp LIKE %:keyword% OR " +
		       "   p.nit LIKE %:keyword% OR " +
		       "   p.razonSocial LIKE %:keyword% OR " +
		       "   p.co LIKE %:keyword% OR " +
		       "   CAST(p.valor AS string) LIKE %:keyword% " +
		       ") " +
		       "ORDER BY p.fecha DESC, p.pedidoNo DESC")
    Page<VistaPedidosErp> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("SELECT p "
			+ "FROM VistaPedidosErp p "
			+ "WHERE (p.tipo= :tipo AND p.idEstado <> :idEstado) " +
		    "ORDER BY p.fecha DESC, p.pedidoNo DESC")
	Page<VistaPedidosErp> findByTipoAndIdEstadoOrderByFechaDesc(String tipo, Integer idEstado, Pageable pageable);

}
