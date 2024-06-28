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

	@Query(value = "SELECT  remision.id_remision AS IdRemision, remision.fechaCreacion AS FechaCreacion, RTRIM(view_orden_pv.Tipo_OP) + '-' + RTRIM(view_orden_pv.Num_Op) AS Op, "
			+ "view_orden_pv.f200_razon_social AS Cliente, RTRIM(view_orden_pv.f285_id) + '-' + RTRIM(view_orden_pv.f285_descripcion) AS Proyecto, "
			+ "web_usuarios.usu_nombre AS UsuarioCrea, UnoEE_Prueba.dbo.t015_mm_contactos.f015_contacto AS Contacto, "
			+ "UnoEE_Prueba.dbo.t015_mm_contactos.f015_direccion1 AS Direccion, UnoEE_Prueba.dbo.t015_mm_contactos.f015_celular AS Celular, "
			+ "UnoEE_Prueba.dbo.t013_mm_ciudades.f013_descripcion AS Ciudad "
			+ "FROM   remision "
			+ "INNER JOIN view_orden_pv "
			+ "ON remision.id_op_ia = view_orden_pv.id_op_ia "
			+ "INNER JOIN web_usuarios "
			+ "ON remision.id_usuario_crea = web_usuarios.usu_id "
			+ "INNER JOIN UnoEE_Prueba.dbo.t430_cm_pv_docto "
			+ "ON view_orden_pv.Row430_id = UnoEE_Prueba.dbo.t430_cm_pv_docto.f430_rowid "
			+ "INNER JOIN UnoEE_Prueba.dbo.t215_mm_puntos_envio_cliente "
			+ "ON UnoEE_Prueba.dbo.t430_cm_pv_docto.f430_rowid_punto_envio_rem = UnoEE_Prueba.dbo.t215_mm_puntos_envio_cliente.f215_rowid "
			+ "INNER JOIN UnoEE_Prueba.dbo.t015_mm_contactos "
			+ "ON UnoEE_Prueba.dbo.t215_mm_puntos_envio_cliente.f215_rowid_contacto = UnoEE_Prueba.dbo.t015_mm_contactos.f015_rowid "
			+ "INNER JOIN UnoEE_Prueba.dbo.t013_mm_ciudades "
			+ "ON UnoEE_Prueba.dbo.t015_mm_contactos.f015_id_pais = UnoEE_Prueba.dbo.t013_mm_ciudades.f013_id_pais "
			+ "AND UnoEE_Prueba.dbo.t015_mm_contactos.f015_id_depto = UnoEE_Prueba.dbo.t013_mm_ciudades.f013_id_depto "
			+ "AND UnoEE_Prueba.dbo.t015_mm_contactos.f015_id_ciudad = UnoEE_Prueba.dbo.t013_mm_ciudades.f013_id "
			+ "WHERE remision.id_remision = :idRemision", nativeQuery = true)
	EncabezadoRemision obtenerEncabezadoRemisionPorId(@Param("idRemision") Long idRemision);

	@Query(value = "SELECT  remision.id_remision AS IdRemision, remision.fechaCreacion AS FechaCreacion, RTRIM(view_orden_pv.Tipo_OP) + '-' + RTRIM(view_orden_pv.Num_Op) AS Op, "
			+ "view_orden_pv.f200_razon_social AS Cliente, RTRIM(view_orden_pv.f285_id) + '-' + RTRIM(view_orden_pv.f285_descripcion) AS Proyecto, "
			+ "web_usuarios.usu_nombre AS UsuarioCrea "
			+ "FROM   remision "
			+ "INNER JOIN view_orden_pv "
			+ "ON remision.id_op_ia = view_orden_pv.id_op_ia "
			+ "INNER JOIN web_usuarios "
			+ "ON remision.id_usuario_crea = web_usuarios.usu_id ", nativeQuery = true)
	Page<EncabezadoRemision> buscarTodasRemisiones(Pageable pageable);

}
