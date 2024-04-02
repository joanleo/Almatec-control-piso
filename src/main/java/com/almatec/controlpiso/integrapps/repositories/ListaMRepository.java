package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.integrapps.entities.ListaM;

public interface ListaMRepository extends JpaRepository<ListaM, Integer> {

	List<ListaM> findByIdOpIntegrapps(Integer idOP);


}
