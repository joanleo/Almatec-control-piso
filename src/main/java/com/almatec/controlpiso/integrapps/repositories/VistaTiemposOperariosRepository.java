package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.VistaTiemposOperarios;

public interface VistaTiemposOperariosRepository extends JpaRepository<VistaTiemposOperarios, Integer> {

	List<VistaTiemposOperarios> findByIdConfigProceso(Integer idProceso);

}
