package com.almatec.controlpiso.integrapps.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.VistaNovedades;

public interface VistaNovedadesRepository extends JpaRepository<VistaNovedades, Integer>{

	List<VistaNovedades> findByEnviadoErpFalse();


}
