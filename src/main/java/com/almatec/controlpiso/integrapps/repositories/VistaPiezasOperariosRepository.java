package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.dtos.OperarioDTO;
import com.almatec.controlpiso.integrapps.entities.VistaPiezasOperarios;

public interface VistaPiezasOperariosRepository extends JpaRepository<VistaPiezasOperarios, String> {

	@Query(value = "SELECT * "
			+ "FROM view_piezas_operarios_proceso "
			+ "WHERE id_config_proceso = :#{#operario.idConfigProceso} "
			+ "AND id_centro_trabajo = :#{#operario.idCentroTrabajo} "
			+ "AND id_operario = :#{#operario.idOperario} "
			+ "AND is_pieza_activa = 1 ", nativeQuery = true)
	List<VistaPiezasOperarios> findPiezasOperariosProceso(OperarioDTO operario);

	@Query(value = "SELECT id, id_reg_pieza, id_config_proceso, id_centro_trabajo, "
			+ "id_operario, is_operario_activo, cliente, c_o, id_item_op, id_item, descripcion, cant_req, peso_item, "
			+ "codigo_erp, long_item, prioridad, id_op_ia, tipo_op_erp, num_op_erp, esquema_pintura, N_sstranscurrido, N_ssreproceso, "
			+ "nombre_operario, color, id_item_parte, cant_cumplida, ruta_plano, cant_fabricada  "
			+ "FROM view_piezas_operarios_proceso "
			+ "WHERE id_config_proceso = :idConfig "
			+ "AND id_centro_trabajo = :idCT "
			+ "AND is_pieza_activa = 1 ", nativeQuery = true)
	List<VistaPiezasOperarios> findPiezasOperariosProceso(Integer idCT, Integer idConfig);

	@Query(value = "SELECT COALESCE(SUM(cant_fabricada), 0) " +
            "FROM view_piezas_operarios_proceso " +
            "WHERE id_centro_trabajo = :idCT " +
            "AND id_item_op = :idItemOp", nativeQuery = true)
    Integer obtenerTotalPiezasFabricadas(@Param("idCT") Integer idCT, @Param("idItemOp") Integer idItemOp);

}
