package com.almatec.controlpiso.integrapps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.RegistroPieza;

public interface RegistroPiezaRepository extends JpaRepository<RegistroPieza, Integer> {

	RegistroPieza findByIdCTAndIdConfigAndIdOperarioAndIdItem(Integer idCT, Integer idProceso, Integer idOperario,
			Integer idPieza);

	RegistroPieza findByIdCTAndIdConfigAndIdOperarioAndIdItemAndIdItemParte(Integer idCT, Integer idProceso,
			Integer idOperario, Integer idItemOp, Integer idItem);

}
