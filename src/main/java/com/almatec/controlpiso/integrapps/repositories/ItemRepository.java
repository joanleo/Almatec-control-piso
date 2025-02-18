package com.almatec.controlpiso.integrapps.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.integrapps.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	Item findByIdItem(Integer idItemMAteriaPrima);

	@Query("SELECT i FROM Item i WHERE " +
	           "LOWER(i.descripcion) LIKE LOWER(CONCAT('%', :query, '%')) "
	           + "OR CAST(i.idItem AS string) LIKE CONCAT('%', :query, '%') "
	           + "OR CAST(i.tipo AS string) LIKE CONCAT ('%', :query, '%') ")
    List<Item> search(@Param("query") String query);

	@Query("SELECT i FROM Item i WHERE " +
		    "(LOWER(i.descripcion) LIKE LOWER(CONCAT('%', :query, '%')) " +
		    "OR CAST(i.idItem AS string) LIKE CONCAT('%', :query, '%'))" +
		    "AND (:tipo IS NULL OR i.tipo = :tipo)")
	List<Item> searchWithType(@Param("query") String query, @Param("tipo") String tipo);

	@Query("SELECT i FROM Item i WHERE " +
		    "(LOWER(i.descripcion) LIKE LOWER(CONCAT('%', :query, '%')) " +
		    "OR CAST(i.idItem AS string) LIKE CONCAT('%', :query, '%'))" +
		    "AND (:tipo IS NULL OR i.tipo = :tipo)")
	Page<Item> searchWithType(@Param("query") String query, @Param("tipo") String tipo, Pageable pageable);

	@Query("SELECT i FROM Item i WHERE " +
		    "LOWER(i.descripcion) LIKE LOWER(CONCAT('%', :query, '%')) " +
		    "OR CAST(i.idItem AS string) LIKE CONCAT('%', :query, '%')")
		Page<Item> search(@Param("query") String query, Pageable pageable);

}
