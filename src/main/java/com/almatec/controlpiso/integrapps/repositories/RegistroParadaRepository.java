package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.integrapps.entities.RegistroParada;
import com.almatec.controlpiso.integrapps.interfaces.InfoParada;

public interface RegistroParadaRepository extends JpaRepository<RegistroParada, Integer> {

	RegistroParada findByIdConfigProcesoAndIdOperarioAndIdParada(Integer idConfigProceso, Integer idOperario,
			Long idParada);

	@Query(value = "SELECT pro_regparada.C_proconfigproceso_id, pro_regparada.C_proparada_id, pro_parada.A_nombre, SUM(pro_regparada.N_sstranscurrido) AS Tiempo "
			+ "FROM     pro_regparada "
			+ "INNER JOIN pro_parada "
			+ "ON pro_regparada.C_proparada_id = pro_parada.C_proparada_id "
			+ "WHERE  (pro_regparada.C_proconfigproceso_id = :idConfigProceso) "
			+ "GROUP BY pro_regparada.C_proconfigproceso_id, pro_regparada.C_proparada_id, pro_parada.A_nombre ", nativeQuery = true)
	List<InfoParada> obtenerInfoParadasCT(Integer idConfigProceso);

}
