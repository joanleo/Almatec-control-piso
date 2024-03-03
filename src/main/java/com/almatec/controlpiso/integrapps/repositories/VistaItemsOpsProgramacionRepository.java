package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.almatec.controlpiso.integrapps.entities.VistaItemsOpsProgramacion;

public interface VistaItemsOpsProgramacionRepository extends JpaRepository<VistaItemsOpsProgramacion, Integer> {

	@Query(value = "SELECT * "
			+ "FROM view_items_op_programacion "
			+ "WHERE Item_fab_Id <> 0 ", nativeQuery = true)
	List<VistaItemsOpsProgramacion> findAllItemsToMake();

}
