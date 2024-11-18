package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.ingenieria.MemoWithOP;
import com.almatec.controlpiso.ingenieria.dtos.MemoDetalleDTO;
import com.almatec.controlpiso.integrapps.entities.Memo;
import com.almatec.controlpiso.integrapps.entities.MemoDetalle;

public interface MemoRepository extends JpaRepository<Memo, Long> {

	@Query(value = "SELECT   memos.id_memo AS IdMemo, memos.fecha_creacion AS FechaCreacion, "
			+ "memos.id_estado AS IdEstado, web_usuarios.usu_nombre AS UsuarioCrea, memos.id_op_ia AS IdOpIa, "
			+ "RTRIM(view_orden_pv.Tipo_OP)  + '-' + RTRIM(view_orden_pv.Num_Op) AS Op, view_orden_pv.f200_razon_social AS Cliente, "
			+ "RTRIM(view_orden_pv.f285_id)  + '-' + RTRIM(view_orden_pv.f285_descripcion) AS Proyecto "
			+ "FROM      memos "
			+ "INNER JOIN "
			+ "view_orden_pv ON memos.id_op_ia = view_orden_pv.id_op_ia "
			+ "INNER JOIN web_usuarios "
			+ "ON memos.id_usuario_crea = web_usuarios.usu_id "
			+ "WHERE memos.id_estado = 0 "
			+ "ORDER BY memos.id_memo DESC", nativeQuery = true)
	List<MemoWithOP> findByIdEstadoSinAprobar();

	@Query(value = "SELECT   memos.id_memo AS IdMemo, memos.fecha_creacion AS FechaCreacion, "
			+ "memos.id_estado AS IdEstado, web_usuarios.usu_nombre AS UsuarioCrea, memos.id_op_ia AS IdOpIa, "
			+ "RTRIM(view_orden_pv.Tipo_OP)  + '-' + RTRIM(view_orden_pv.Num_Op) AS Op, view_orden_pv.f200_razon_social AS Cliente, "
			+ "RTRIM(view_orden_pv.f285_id)  + '-' + RTRIM(view_orden_pv.f285_descripcion) AS Proyecto "
			+ "FROM      memos "
			+ "INNER JOIN "
			+ "view_orden_pv ON memos.id_op_ia = view_orden_pv.id_op_ia "
			+ "INNER JOIN web_usuarios "
			+ "ON memos.id_usuario_crea = web_usuarios.usu_id ", nativeQuery = true)
	List<MemoWithOP> buscarTodos();

	@Query(value = "SELECT memos.id_memo AS IdMemo, memos.fecha_creacion AS FechaCreacion, "
            + "memos.id_estado AS IdEstado, web_usuarios.usu_nombre AS UsuarioCrea, memos.id_op_ia AS IdOpIa, "
            + "RTRIM(view_orden_pv.Tipo_OP) + '-' + RTRIM(view_orden_pv.Num_Op) AS Op, view_orden_pv.f200_razon_social AS Cliente, "
            + "RTRIM(view_orden_pv.f285_id) + '-' + RTRIM(view_orden_pv.f285_descripcion) AS Proyecto "
            + "FROM memos "
            + "INNER JOIN view_orden_pv ON memos.id_op_ia = view_orden_pv.id_op_ia "
            + "INNER JOIN web_usuarios ON memos.id_usuario_crea = web_usuarios.usu_id "
            + "WHERE (:keyword IS NULL OR "
            + "view_orden_pv.f200_razon_social LIKE %:keyword% OR "
            + "view_orden_pv.f285_descripcion LIKE %:keyword% OR "
            + "web_usuarios.usu_nombre LIKE %:keyword%) "
            + "ORDER BY memos.id_memo DESC",
            countQuery = "SELECT COUNT(*) "
            + "FROM memos "
            + "INNER JOIN view_orden_pv ON memos.id_op_ia = view_orden_pv.id_op_ia "
            + "INNER JOIN web_usuarios ON memos.id_usuario_crea = web_usuarios.usu_id "
            + "WHERE (:keyword IS NULL OR "
            + "view_orden_pv.f200_razon_social LIKE %:keyword% OR "
            + "view_orden_pv.f285_descripcion LIKE %:keyword% OR "
            + "web_usuarios.usu_nombre LIKE %:keyword%)",
            nativeQuery = true)
    Page<MemoWithOP> findAllWithSearch(@Param("keyword") String keyword, Pageable pageable);
	
	
	@Query(value = "SELECT io.item_id, md.cantidad, md.operacion, io.descripcion "
			+ "FROM memo_detalle md "
			+ "LEFT JOIN items_op io ON md.id_item_op = io.item_id "
			+ "WHERE md.id_memo = :idMemo ", nativeQuery = true)
	List<Object[]>findByMemoId(@Param("idMemo") Long idMemo);

}
