package com.almatec.controlpiso.security.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.almatec.controlpiso.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r WHERE r.isActivo = true")
    @EntityGraph(attributePaths = {"permissions"})
    List<Role> findByIsActivoTrue();
    
    @Query("SELECT r FROM Role r LEFT JOIN FETCH r.permissions WHERE r.idRole = :id")
    Optional<Role> findByIdWithPermissions(@Param("id") Long id);

	Optional<Role> findByIdRole(Long idRole);

}
