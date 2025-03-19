package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.almacen.interfaces.EncabezadoRemision;
import com.almatec.controlpiso.integrapps.entities.Remision;

public interface RemisionRepository extends JpaRepository<Remision, Long> {

	@Query(value = "SELECT  remision.id_remision AS IdRemision, remision.fechaCreacion AS FechaCreacion, RTRIM(view_orden_pv.Tipo_OP) + '-' + RTRIM(view_orden_pv.Num_Op) AS Op, "
			+ "view_orden_pv.f200_razon_social AS Cliente, RTRIM(view_orden_pv.f285_id) + '-' + RTRIM(view_orden_pv.f285_descripcion) AS Proyecto, "
			+ "web_usuarios.usu_nombre AS UsuarioCrea "
			+ "FROM   remision "
			+ "INNER JOIN view_orden_pv "
			+ "ON remision.id_op_ia = view_orden_pv.id_op_ia "
			+ "INNER JOIN web_usuarios "
			+ "ON remision.id_usuario_crea = web_usuarios.usu_id", nativeQuery = true)
	List<EncabezadoRemision> buscarTodasRemisiones();

	@Query(value =
			"DECLARE @schema NVARCHAR(100) = :schema\\; " +
			"DECLARE @idRemision BIGINT = :idRemision\\; " +
			"DECLARE @sql NVARCHAR(MAX) = ' " +
			"SELECT r.id_remision AS IdRemision, " + 
				"r.fechaCreacion AS FechaCreacion, " + 
				"RTRIM(op.Tipo_OP) + ''-'' + RTRIM(op.Num_Op) AS Op, " + 
				"op.f200_razon_social AS Cliente, " +
				"RTRIM(op.f285_id) + ''-'' + RTRIM(op.f285_descripcion) AS Proyecto, " + 
				"usr.usu_nombre AS UsuarioCrea, " + 
				"cont.f015_contacto AS Contacto, " + 
				"cont.f015_direccion1 AS Direccion, " +
				"cont.f015_celular AS Celular, " + 
				"ciudad.f013_descripcion AS Ciudad, " + 
				"r.id_op_ia AS IdOpIa " +
			"FROM remision r " +
			"INNER JOIN view_orden_pv op " +
			"    ON r.id_op_ia = op.id_op_ia " +
			"INNER JOIN web_usuarios usr " +
			"    ON r.id_usuario_crea = usr.usu_id " +
			"INNER JOIN ' + @schema + '.t430_cm_pv_docto doc " +
			"    ON op.Row430_id = doc.f430_rowid " +
			"INNER JOIN ' + @schema + '.t215_mm_puntos_envio_cliente env " +
			"    ON doc.f430_rowid_punto_envio_rem = env.f215_rowid " +
			"INNER JOIN ' + @schema + '.t015_mm_contactos cont " +
			"    ON env.f215_rowid_contacto = cont.f015_rowid " +
			"INNER JOIN ' + @schema + '.t013_mm_ciudades ciudad " +
			"    ON cont.f015_id_pais = ciudad.f013_id_pais " +
			"    AND cont.f015_id_depto = ciudad.f013_id_depto " +
			"    AND cont.f015_id_ciudad = ciudad.f013_id " +
			"WHERE r.id_remision = @idRemision'; " +
			"EXEC sp_executesql @sql, N'@idRemision BIGINT', @idRemision",
			nativeQuery = true)
			EncabezadoRemision obtenerEncabezadoRemisionPorId(@Param("schema") String schema, @Param("idRemision") Long idRemision);

	@Query(value = "SELECT  remision.id_remision AS IdRemision, remision.fechaCreacion AS FechaCreacion, RTRIM(view_orden_pv.Tipo_OP) + '-' + RTRIM(view_orden_pv.Num_Op) AS Op, "
			+ "view_orden_pv.f200_razon_social AS Cliente, RTRIM(view_orden_pv.f285_id) + '-' + RTRIM(view_orden_pv.f285_descripcion) AS Proyecto, "
			+ "web_usuarios.usu_nombre AS UsuarioCrea "
			+ "FROM   remision "
			+ "INNER JOIN view_orden_pv "
			+ "ON remision.id_op_ia = view_orden_pv.id_op_ia "
			+ "INNER JOIN web_usuarios "
			+ "ON remision.id_usuario_crea = web_usuarios.usu_id "
			+ "ORDER BY remision.id_remision DESC ", nativeQuery = true)
	Page<EncabezadoRemision> buscarTodasRemisiones(Pageable pageable);

	@Query(value = "SELECT remision.id_remision AS IdRemision, remision.fechaCreacion AS FechaCreacion, " +
	           "RTRIM(view_orden_pv.Tipo_OP) + '-' + RTRIM(view_orden_pv.Num_Op) AS Op, " +
	           "view_orden_pv.f200_razon_social AS Cliente, " +
	           "RTRIM(view_orden_pv.f285_id) + '-' + RTRIM(view_orden_pv.f285_descripcion) AS Proyecto, " +
	           "web_usuarios.usu_nombre AS UsuarioCrea " +
	           "FROM remision " +
	           "INNER JOIN view_orden_pv ON remision.id_op_ia = view_orden_pv.id_op_ia " +
	           "INNER JOIN web_usuarios ON remision.id_usuario_crea = web_usuarios.usu_id " +
	           "WHERE LOWER(CAST(remision.id_remision AS VARCHAR)) LIKE LOWER(CONCAT('%', :termino, '%')) " +
	           "OR LOWER(RTRIM(view_orden_pv.Tipo_OP) + '-' + RTRIM(view_orden_pv.Num_Op)) LIKE LOWER(CONCAT('%', :termino, '%')) " +
	           "OR LOWER(view_orden_pv.f200_razon_social) LIKE LOWER(CONCAT('%', :termino, '%')) " +
	           "OR LOWER(RTRIM(view_orden_pv.f285_id) + '-' + RTRIM(view_orden_pv.f285_descripcion)) LIKE LOWER(CONCAT('%', :termino, '%')) " + 
	           "ORDER BY remision.id_remision DESC",
	           nativeQuery = true,
	           countQuery = "SELECT COUNT(*) " +
	           "FROM remision " +
	           "INNER JOIN view_orden_pv ON remision.id_op_ia = view_orden_pv.id_op_ia " +
	           "INNER JOIN web_usuarios ON remision.id_usuario_crea = web_usuarios.usu_id " +
	           "WHERE LOWER(CAST(remision.id_remision AS VARCHAR)) LIKE LOWER(CONCAT('%', :termino, '%')) " +
	           "OR LOWER(RTRIM(view_orden_pv.Tipo_OP) + '-' + RTRIM(view_orden_pv.Num_Op)) LIKE LOWER(CONCAT('%', :termino, '%')) " +
	           "OR LOWER(view_orden_pv.f200_razon_social) LIKE LOWER(CONCAT('%', :termino, '%')) " +
	           "OR LOWER(RTRIM(view_orden_pv.f285_id) + '-' + RTRIM(view_orden_pv.f285_descripcion)) LIKE LOWER(CONCAT('%', :termino, '%'))")
    Page<EncabezadoRemision> buscarRemisiones(@Param("termino") String termino, Pageable pageable);

	@Query("SELECT r FROM Remision r LEFT JOIN FETCH r.detalles WHERE r.idRemision = :idRemision")
	Remision findByIdWithDetails(@Param("idRemision") Long idRemision);

}
