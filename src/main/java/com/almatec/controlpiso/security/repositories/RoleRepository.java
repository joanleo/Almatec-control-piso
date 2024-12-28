package com.almatec.controlpiso.security.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	List<Role> findByIsActivoTrue();
	
    Optional<Role> findById(Long id);

}
