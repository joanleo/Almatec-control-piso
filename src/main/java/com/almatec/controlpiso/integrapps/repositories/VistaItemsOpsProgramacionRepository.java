package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;

public interface VistaItemsOpsProgramacionRepository extends JpaRepository<VistaItemsOpsProgramacion, String> {

	@Query(value = "SELECT * "
			+ "FROM view_items_op_programacion "
			+ "WHERE Item_fab_Id <> 0 ", nativeQuery = true)
	List<VistaItemsOpsProgramacion> findAllItemsToMake();

	@Query(value = "SELECT * "
			+ "FROM view_items_op_programacion "
			+ "WHERE Item_fab_Id <> 0 ", nativeQuery = true)
	Page<VistaItemsOpsProgramacion> buscarTodosItemsActivosPrioridad(Pageable pageable);

	Page<VistaItemsOpsProgramacion> findAll(Specification<VistaItemsOpsProgramacion> itemsPrioridades,
			Pageable pageable);

}
