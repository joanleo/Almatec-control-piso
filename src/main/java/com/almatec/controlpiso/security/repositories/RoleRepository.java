package com.almatec.controlpiso.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	List<Role> findByIsActivoTrue();

}
