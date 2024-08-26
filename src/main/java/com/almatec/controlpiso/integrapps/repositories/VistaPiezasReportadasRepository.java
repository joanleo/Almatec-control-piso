package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.VistaPiezasReportadas;

public interface VistaPiezasReportadasRepository extends JpaRepository<VistaPiezasReportadas, String> {

	List<VistaPiezasReportadas> findByItemCentroTIdOrMaterialCentroTId(Integer idCT, Integer idCT2);

	List<VistaPiezasReportadas> findByItemCentroTIdOrMaterialCentroTIdOrderByIdOpIntegrappsDesc(Integer idCT,
			Integer idCT2);

}
