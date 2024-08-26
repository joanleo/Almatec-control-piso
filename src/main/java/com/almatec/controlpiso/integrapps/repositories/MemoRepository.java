package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.ingenieria.MemoWithOP;
import com.almatec.controlpiso.integrapps.entities.Memo;

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
			+ "WHERE memos.id_estado = 0", nativeQuery = true)
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

}
