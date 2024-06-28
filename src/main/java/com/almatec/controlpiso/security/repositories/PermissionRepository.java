package com.almatec.controlpiso.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almatec.controlpiso.security.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Permission findByIdPermiso(Long id);

}
